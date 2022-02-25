package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

public class BossBarApiPlayer {

    protected Player player;
    protected NMSEntities bossbar;

    public BossBarApiPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the player associated with the bossbar api player
     *
     * @return the default Minecraft player object associated to the current bossbar api player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the bossbar object
     *
     * @return the bossbar obejct
     */
    public NMSEntities getBossbar() {
        return bossbar;
    }

    /**
     * Return a bossbar api player using a regular player object
     *
     * @param player the player used to find a bossbar api player
     * @return the bossbar api player
     */
    public static BossBarApiPlayer get(Player player) {
        for (BossBarApiPlayer p : Main.players)
            if (p.player.getUniqueId().equals(player.getUniqueId()))
                return (p);
        return (null);
    }
}
