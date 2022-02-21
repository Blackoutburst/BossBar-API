package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class NMSBossBar {

    protected int ID;
    protected String text;

    private float map(float value, float min1, float max1, float min2, float max2) {
        return min2 + (value - min1) * (max2 - min2) / (max1 - min1);
    }

    public NMSBossBar(Player player, String text, int lifePercentage) {
        try {
            final NMSEntities entity = new NMSEntities(player, NMSEntities.EntityType.WITHER);

            final Method getId = entity.entity.getClass().getMethod("getId");
            final Method setHealth = entity.entity.getClass().getMethod("setHealth", float.class);
            final Method getMaxHealth = entity.entity.getClass().getMethod("getMaxHealth");
            final Method setCustomName = entity.entity.getClass().getMethod("setCustomName", String.class);
            final Method setCustomNameVisible = entity.entity.getClass().getMethod("setCustomNameVisible", boolean.class);
            final Method setInvisible = entity.entity.getClass().getMethod("setInvisible", boolean.class);

            final float maxHealth = (float) getMaxHealth.invoke(entity.entity);

            setCustomName.invoke(entity.entity, text);
            setCustomNameVisible.invoke(entity.entity, false);
            setInvisible.invoke(entity.entity, true);
            setHealth.invoke(entity.entity, map(lifePercentage * 2, 0, maxHealth, 0, maxHealth));

            NMSSpawnEntityLiving.send(player, entity);
            this.text = text;
            this.ID = (int) getId.invoke(entity.entity);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
