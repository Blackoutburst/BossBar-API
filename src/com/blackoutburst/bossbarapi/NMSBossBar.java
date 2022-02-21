package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class NMSBossBar {

    protected int ID;

    private float map(float value, float min1, float max1, float min2, float max2) {
        return min2 + (value - min1) * (max2 - min2) / (max1 - min1);
    }

    public NMSBossBar(Player player, String text, int lifePercentage) {
        try {
            final NMSEntities entity = new NMSEntities().getDragon();

            final Method setHealth = entity.entity.getClass().getMethod("setHealth", float.class);
            final Method getMaxHealth = entity.entity.getClass().getMethod("getMaxHealth");

            final float maxHealth = (float) getMaxHealth.invoke(entity.entity);

            setHealth.invoke(entity.entity, map(lifePercentage * 2, 0, maxHealth, 0, maxHealth));

            NMSSpawnEntityLiving.send(player, entity);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
