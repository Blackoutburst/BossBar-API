package com.blackoutburst.bossbarapi;

import org.bukkit.Bukkit;

import java.io.File;
import java.lang.reflect.Constructor;

public class NMSWorld {

    private static Object getDataManager(Class<?> iDataManagerClass) {
        try {
            final String worldName = Bukkit.getWorlds().get(0).getName();

            final Constructor<?> iDataManagerConstruction = iDataManagerClass.getConstructor(File.class, String.class, boolean.class);

            return iDataManagerConstruction.newInstance(new File(worldName), worldName, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getMethodProfiler(Class<?> methodProfilerClass) {
        try {
            final Constructor<?> methodProfilerConstructor = methodProfilerClass.getConstructor();

            return methodProfilerConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getWorldProvider(Class<?> worldProviderClass) {
        try {
            final Constructor<?> worldProviderConstructor = worldProviderClass.getConstructor();

            return worldProviderConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getWorldData(Class<?> worldDataClass) {
        try {
            final Constructor<?> worldDataConstructor = worldDataClass.getConstructor();

            return worldDataConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getWorld() {
        try {
            final Class<?> iDataManagerClass = NMS.getClass("IDataManager");
            final Class<?> worldDataClass = NMS.getClass("WorldData");
            final Class<?> worldProviderClass = NMS.getClass("WorldProvider");
            final Class<?> methodProfilerClass = NMS.getClass("MethodProfiler");
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> worldConstructor = worldClass.getConstructor(iDataManagerClass, worldDataClass, worldProviderClass, methodProfilerClass);

            final Object iDataManager = getDataManager(iDataManagerClass);
            final Object worldData = getWorldData(worldDataClass);
            final Object worldProvider = getWorldProvider(worldProviderClass);
            final Object methodProfiler = getMethodProfiler(methodProfilerClass);

            return worldConstructor.newInstance(iDataManager, worldData, worldProvider, methodProfiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
