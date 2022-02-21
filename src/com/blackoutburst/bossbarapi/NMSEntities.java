package com.blackoutburst.bossbarapi;

import java.lang.reflect.Constructor;

public class NMSEntities {

    protected Object entity;

    public NMSEntities() {
        this.entity = null;
    }

    public void getDragon() {
        try {
            final Class<?> dragonClass = NMS.getClass("EntityEnderDragon");
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> dragonConstructor = dragonClass.getConstructor(worldClass);

            entity = dragonConstructor.newInstance(NMSWorld.getWorld());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
