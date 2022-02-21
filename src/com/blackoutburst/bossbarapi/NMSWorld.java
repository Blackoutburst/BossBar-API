package com.blackoutburst.bossbarapi;

import java.lang.reflect.Constructor;

public class NMSWorld {

    private Object getMethodProfiler(Class<?> methodProfilerClass) {
        try {
            final Constructor<?> methodProfilerConstructor = methodProfilerClass.getConstructor();

            return methodProfilerConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object getWorldProvider(Class<?> worldProviderClass) {
        try {
            final Constructor<?> worldProviderConstructor = worldProviderClass.getConstructor();

            return worldProviderConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object getWorldData(Class<?> worldDataClass) {
        try {
            final Constructor<?> worldDataConstructor = worldDataClass.getConstructor();

            return worldDataConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getWorld() {
        try {
            final Class<?> iDataManagerClass = NMS.getClass("IDataManager");
            final Class<?> worldDataClass = NMS.getClass("WorldData");
            final Class<?> worldProviderClass = NMS.getClass("WorldProvider");
            final Class<?> methodProfilerClass = NMS.getClass("MethodProfiler");
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> worldConstuctor = worldClass.getConstructor(iDataManagerClass, worldDataClass, worldProviderClass, methodProfilerClass);

            final Object worldData = getWorldData(worldDataClass);
            final Object worldProvider = getWorldProvider(worldProviderClass);
            final Object methodProfiler = getMethodProfiler(methodProfilerClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
