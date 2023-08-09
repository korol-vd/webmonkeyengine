package com.jme3.environment.baker;

import com.jme3.asset.AssetManager;
import com.jme3.environment.baker.IBLEnvBakerLight;
import com.jme3.environment.util.EnvMapUtils;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.TextureCubeMap;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.texture.image.ColorSpace;

/**
 *  An env baker for IBL that runs on the GPU 
 * 
 * @author Riccardo Balbo
 */
public class IBLGLEnvBakerLight extends GenericEnvBaker implements IBLEnvBakerLight{
    protected TextureCubeMap specular;
    protected Vector3f[] shCoef;
    public IBLGLEnvBakerLight(RenderManager rm,AssetManager am,
                        Format format,
                        Format depthFormat,
                        int env_size,
                        int specular_size
                     
    ){
        super(rm,am,format,depthFormat,env_size,true);

        specular=new TextureCubeMap(specular_size,specular_size,format);
        specular.setMagFilter(MagFilter.Bilinear);
        specular.setMinFilter(MinFilter.BilinearNoMipMaps);
        specular.setWrap(WrapMode.EdgeClamp);
        specular.getImage().setColorSpace(ColorSpace.Linear);
        int nbMipMaps=(int)(Math.log(specular_size)/Math.log(2)+1);
        if(nbMipMaps>6)nbMipMaps=6;
        int[] sizes=new int[nbMipMaps];
        for(int i=0;i<nbMipMaps;i++){
            int size=(int)FastMath.pow(2,nbMipMaps-1-i);
            sizes[i]=size*size*(specular.getImage().getFormat().getBitsPerPixel()/8);
        }
        specular.getImage().setMipMapSizes(sizes);
	}


    @Override
    public void bakeSpecularIBL() {
        Box boxm=new Box(1,1,1);
        Geometry screen=new Geometry("BakeBox",boxm);
    
        Material mat=new Material(assetManager,"Common/IBL/IBLKernels.j3md");
        mat.setBoolean("UseSpecularIBL",true);
        mat.setTexture("EnvMap",env);
        screen.setMaterial(mat);
    
        for(int mip=0;mip<specular.getImage().getMipMapSizes().length;mip++){
            int mipWidth=(int)(specular.getImage().getWidth()*FastMath.pow(0.5f,mip));
            int mipHeight=(int)(specular.getImage().getHeight()*FastMath.pow(0.5f,mip));

            FrameBuffer specularbaker=new FrameBuffer(mipWidth,mipHeight,1);
            specularbaker.setSrgb(false);
            for(int i=0;i<6;i++)specularbaker.addColorTarget( FrameBuffer.newTarget(specular).level(mip).face(i) );
            
            float roughness=(float)mip/(float)(specular.getImage().getMipMapSizes().length-1);
            mat.setFloat("Roughness",roughness);

            for(int i=0;i<6;i++){
                specularbaker.setTargetIndex(i);
                mat.setInt("FaceId",i);

                screen.updateLogicalState(0);
                screen.updateGeometricState();

                renderManager.setCamera(getCam(i,specularbaker.getWidth(),specularbaker.getHeight(),Vector3f.ZERO,1,1000),false);
                renderManager.getRenderer().setFrameBuffer(specularbaker);
                renderManager.renderGeometry(screen);
            }
            specularbaker.dispose();
        }        
        specular.setMinFilter(MinFilter.Trilinear);        
    }

    @Override
    public TextureCubeMap getSpecularIBL() {
        return specular;
    }
    
    @Override
    public void bakeSphericalHarmonicsCoefficients() {
		shCoef=EnvMapUtils.getSphericalHarmonicsCoefficents(getEnvMap());
	}

    @Override
    public Vector3f[] getSphericalHarmonicsCoefficients(){
        return shCoef;
    }
}