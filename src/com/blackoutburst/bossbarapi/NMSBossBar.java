package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

public class NMSBossBar {

    protected int ID;

    public NMSBossBar(Player player, String text, int lifePercentage) {
        NMSEntities entity = new NMSEntities().getDragon();
        NMSSpawnEntityLiving.send(player, entity);
    }

}
