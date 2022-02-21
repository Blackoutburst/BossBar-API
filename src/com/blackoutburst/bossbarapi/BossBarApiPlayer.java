package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

public class BossBarApiPlayer {

    protected Player player;
    protected NMSEntities bossbar;

    public BossBarApiPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public NMSEntities getBossbar() {
        return bossbar;
    }

    public static BossBarApiPlayer get(Player player) {
        for (BossBarApiPlayer p : Main.players)
            if (p.player.getUniqueId().equals(player.getUniqueId()))
                return (p);
        return (null);
    }
}
