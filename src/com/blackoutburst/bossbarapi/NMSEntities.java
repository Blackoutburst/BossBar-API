package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntities {

    protected Object entity;

    public enum EntityType {
        WITHER("EntityWither");
        
        public String className;

        EntityType(String className) {
            this.className = className;
        }
    }

    public Object getDataWatcher() {
        try {
            final Method getId = entity.getClass().getMethod("getDataWatcher");
            return getId.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getID() {
        try {
            final Method getId = entity.getClass().getMethod("getId");
            return (int) getId.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public NMSEntities(Player player, EntityType type) {
        try {
            final Class<?> entityClass = NMS.getClass(type.className);
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> dragonConstructor = entityClass.getConstructor(worldClass);

            entity = dragonConstructor.newInstance(NMSWorld.getWorld(player));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
