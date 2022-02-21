package com.blackoutburst.bossbarapi;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntityDestroy {

    public static void send(Player player, int id) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutEntityDestroy");

            final Constructor<?> packetConstructor = packetClass.getConstructors()[0];

            final Object packet = packetConstructor.newInstance();

            NMS.setField(packet, "a", new int[]{id});
            NMS.sendPacket(player, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
