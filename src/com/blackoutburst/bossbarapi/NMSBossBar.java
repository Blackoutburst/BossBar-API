package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class NMSBossBar {

    protected String text;

    private float map(float value, float min1, float max1, float min2, float max2) {
        return min2 + (value - min1) * (max2 - min2) / (max1 - min1);
    }

    public void editHealth(BossBarApiPlayer player, int lifePercentage) {
        try {
            final Method setHealth = player.getBossbar().entity.getClass().getMethod("setHealth", float.class);
            final Method getMaxHealth = player.getBossbar().entity.getClass().getMethod("getMaxHealth");

            final float maxHealth = (float) getMaxHealth.invoke(player.getBossbar().entity);

            setHealth.invoke(player.getBossbar().entity, map(lifePercentage, 0, 100, 0, maxHealth));

            NMSEntityMetadata.send(player.getPlayer(), player.getBossbar());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editText(BossBarApiPlayer player, String text) {
        try {
            final Method setCustomName = player.getBossbar().entity.getClass().getMethod("setCustomName", String.class);

            setCustomName.invoke(player.getBossbar().entity, text);

            NMSEntityMetadata.send(player.getPlayer(), player.getBossbar());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Player player) {
        NMSEntityDestroy.send(player, BossBarApiPlayer.get(player).bossbar.getID());
    }

    public NMSBossBar(Player player, String text, int lifePercentage) {
        try {
            final double x = player.getLocation().getX() + player.getLocation().getDirection().getX() * 50;
            final double y = player.getLocation().getY() + player.getLocation().getDirection().getY() * 50;
            final double z = player.getLocation().getZ() + player.getLocation().getDirection().getZ() * 50;

            final NMSEntities entity = new NMSEntities(player, NMSEntities.EntityType.WITHER);

            final Method setHealth = entity.entity.getClass().getMethod("setHealth", float.class);
            final Method getMaxHealth = entity.entity.getClass().getMethod("getMaxHealth");
            final Method setCustomName = entity.entity.getClass().getMethod("setCustomName", String.class);
            final Method setCustomNameVisible = entity.entity.getClass().getMethod("setCustomNameVisible", boolean.class);
            final Method setPosition = entity.entity.getClass().getMethod("setPosition", double.class, double.class, double.class);
            final Method setInvisible = entity.entity.getClass().getMethod("setInvisible", boolean.class);

            final float maxHealth = (float) getMaxHealth.invoke(entity.entity);

            setCustomName.invoke(entity.entity, text);
            setCustomNameVisible.invoke(entity.entity, false);
            setPosition.invoke(entity.entity, x, y, z);
            setInvisible.invoke(entity.entity, true);
            setHealth.invoke(entity.entity, map(lifePercentage, 0, 100, 0, maxHealth));

            NMSSpawnEntityLiving.send(player, entity);
            this.text = text;
            BossBarApiPlayer.get(player).bossbar = entity;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
