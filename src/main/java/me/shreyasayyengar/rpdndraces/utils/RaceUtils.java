package me.shreyasayyengar.rpdndraces.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RaceUtils {

    private RaceUtils() {
    }

    public static void boostForward(Player player, int blocks) {
        Location teleportLoc = player.getLocation();
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            teleportLoc.add(blocks, 0, 0);
        } else if (67.5 <= rotation && rotation < 112.5) {
            teleportLoc.add(0, 0, blocks);
        } else if (157.5 <= rotation && rotation < 202.5) {
            teleportLoc.add(-blocks, 0, 0);
        } else if (247.5 <= rotation && rotation < 292.5) {
            teleportLoc.add(0, 0, teleportLoc.getZ() - blocks);
        } else if (337.5 <= rotation && rotation < 360.0) {
            teleportLoc.add(blocks, 0, 0);
        }

        player.teleport(teleportLoc);
    }
}
