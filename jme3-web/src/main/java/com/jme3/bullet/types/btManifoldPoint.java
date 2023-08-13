package com.jme3.bullet.types;

import org.teavm.jso.JSMethod;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public interface btManifoldPoint extends JSObject {
    

    public float getAppliedImpulse();

    @JSMethod("get_m_appliedImpulseLateral1")
    public float getAppliedImpulseLateral1();

    @JSMethod("set_m_appliedImpulseLateral1")
    public void setAppliedImpulseLateral1(float appliedImpulseLateral1);

    @JSMethod("get_m_appliedImpulseLateral2")
    public float getAppliedImpulseLateral2();

    @JSMethod("set_m_appliedImpulseLateral2")
    public void setAppliedImpulseLateral2(float appliedImpulseLateral2);

    @JSMethod("get_m_combinedFriction")
    public float getCombinedFriction();

    @JSMethod("set_m_combinedFriction")
    public void setCombinedFriction(float combinedFriction);

    @JSMethod("get_m_combinedRestitution")
    public float getCombinedRestitution();

    @JSMethod("set_m_combinedRestitution")
    public void setCombinedRestitution(float combinedRestitution);

    @JSMethod("get_m_distance1")
    public float getDistance1();

    @JSMethod("set_m_distance1")
    public void setDistance1(float distance1);

    @JSMethod("get_m_index0")
    public int getIndex0();

    @JSMethod("set_m_index0")
    public void setIndex0(int index0);

    @JSMethod("get_m_index1")
    public int getIndex1();

    @JSMethod("set_m_index1")
    public void setIndex1(int index1);

    @JSMethod("get_m_lateralFrictionDir1")
    public btVector3 getLateralFrictionDir1();

    @JSMethod("set_m_lateralFrictionDir1")
    public void setLateralFrictionDir1(btVector3 lateralFrictionDir1);

    @JSMethod("get_m_lateralFrictionDir2")
    public btVector3 getLateralFrictionDir2();

    @JSMethod("set_m_lateralFrictionDir2")
    public void setLateralFrictionDir2(btVector3 lateralFrictionDir2);

    @JSMethod("is_m_lateralFrictionInitialized")
    public boolean isLateralFrictionInitialized();

    @JSMethod("set_m_lateralFrictionInitialized")
    public void setLateralFrictionInitialized(boolean lateralFrictionInitialized);

    @JSMethod("get_m_lifeTime")
    public int getLifeTime();

    @JSMethod("set_m_lifeTime")
    public void setLifeTime(int lifeTime);

    @JSMethod("get_m_localPointA")
    public btVector3 getLocalPointA();

    @JSMethod("set_m_localPointA")
    public void setLocalPointA(btVector3 localPointA);

    @JSMethod("get_m_localPointB")
    public btVector3 getLocalPointB();

    @JSMethod("set_m_localPointB")
    public void setLocalPointB(btVector3 localPointB);

    @JSMethod("get_m_normalWorldOnB")
    public btVector3 getNormalWorldOnB();

    @JSMethod("set_m_normalWorldOnB")
    public void setNormalWorldOnB(btVector3 normalWorldOnB);

    @JSMethod("get_m_userPersistentData")
    public JSObject getUserPersistentData();

    @JSMethod("set_m_userPersistentData")
    public void setUserPersistentData(JSObject userPersistentData);

    @JSMethod("get_m_partId0")
    public int getPartId0();

    @JSMethod("set_m_partId0")
    public void setPartId0(int partId0);

    @JSMethod("get_m_partId1")
    public int getPartId1();

    @JSMethod("set_m_partId1")
    public void setPartId1(int partId1);

    @JSMethod("get_m_positionWorldOnA")
    public btVector3 getPositionWorldOnA();

    @JSMethod("set_m_positionWorldOnA")
    public void setPositionWorldOnA(btVector3 positionWorldOnA);

    @JSMethod("get_m_positionWorldOnB")
    public btVector3 getPositionWorldOnB();

    @JSMethod("set_m_positionWorldOnB")
    public void setPositionWorldOnB(btVector3 positionWorldOnB);

}
