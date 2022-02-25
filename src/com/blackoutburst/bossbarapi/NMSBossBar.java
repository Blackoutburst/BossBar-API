package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class NMSBossBar {

    protected String text;

    private float map(float value, float min1, float max1, float min2, float max2) {
        return min2 + (value - min1) * (max2 - min2) / (max1 - min1);
    }

    /**
     * Set the bossbar health
     *
     * @param player the player affiliated with this bossbar
     * @param lifePercentage the new life of the bossbar
     */
    public void setHealth(Player player, int lifePercentage) {
        try {
            final BossBarApiPlayer bp = BossBarApiPlayer.get(player);

            final Method setHealth = bp.getBossbar().entity.getClass().getMethod("setHealth", float.class);
            final Method getMaxHealth = bp.getBossbar().entity.getClass().getMethod("getMaxHealth");

            final float maxHealth = (float) getMaxHealth.invoke(bp.getBossbar().entity);

            setHealth.invoke(bp.getBossbar().entity, map(lifePercentage, 0, 100, 0, maxHealth));

            NMSEntityMetadata.send(player, bp.getBossbar());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the bossbar text
     *
     * @param player the player affiliated with this bossbar
     * @param text the new text
     */
    public void setText(Player player, String text) {
        try {
            final BossBarApiPlayer bp = BossBarApiPlayer.get(player);

            final Method setCustomName = bp.getBossbar().entity.getClass().getMethod("setCustomName", String.class);

            setCustomName.invoke(bp.getBossbar().entity, text);

            NMSEntityMetadata.send(player, bp.getBossbar());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the bossbar
     *
     * @param player the player affiliated with this bossbar
     */
    public void delete(Player player) {
        NMSEntityDestroy.send(player, BossBarApiPlayer.get(player).bossbar.getID());
    }

    /**
     * Create a new bossbar using default parameters
     *
     * @param player the player that will see the bossbar
     * @param text the bossbar text
     * @param lifePercentage the bossbar health
     */
    public NMSBossBar(Player player, String text, int lifePercentage) {
        try {
            final double x = player.getLocation().getX() + player.getLocation().getDirection().getX() * 50;
            final double y = player.getLocation().getY() + player.getLocation().getDirection().getY() * 50;
            final double z = player.getLocation().getZ() + player.getLocation().getDirection().getZ() * 50;

            final NMSEntities entity = new NMSEntities(player.getWorld(), NMSEntities.EntityType.WITHER);

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
