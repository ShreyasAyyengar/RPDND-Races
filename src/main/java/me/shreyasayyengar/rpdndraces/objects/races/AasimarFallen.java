package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractAasimar;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class AasimarFallen extends AbstractAasimar {

    public AasimarFallen(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        Utils.setElytra(player);
        player.setVelocity(player.getVelocity().add(new Vector(0, 4.5, 0)));
        new BukkitRunnable() {
            @Override
            public void run() {
                runUnsafeActions(() -> player.getInventory().setChestplate(null));
            }
        }.runTaskLater(RacesPlugin.getInstance(), 60 * 20);
    }

    @Override
    public String getName() {
        return "Aasimar-Fallen";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
