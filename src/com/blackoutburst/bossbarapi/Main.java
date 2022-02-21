package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public static List<BossBarApiPlayer> players = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        final BossBarApiPlayer ap = BossBarApiPlayer.get(p);

        final double x = p.getLocation().getX() + p.getLocation().getDirection().getX() * 50;
        final double y = p.getLocation().getY();
        final double z = p.getLocation().getZ() + p.getLocation().getDirection().getZ() * 50;

        NMSEntityTeleport.send(ap.getPlayer(), ap.bossbar, x, y, z);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        players.add(new BossBarApiPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        players.remove(BossBarApiPlayer.get(event.getPlayer()));
    }
}