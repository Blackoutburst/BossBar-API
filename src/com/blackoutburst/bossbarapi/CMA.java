package com.blackoutburst.bossbarapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class CMA {

    public static Class<?> getClass(String name) {
        try {
            return Class.forName("com.mojang.authlib." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
