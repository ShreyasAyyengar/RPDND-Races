package me.shreyasayyengar.rpdndraces.objects.races.aasimar;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractAasimar;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class AasimarProtector extends AbstractAasimar {

    public AasimarProtector(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }


    @Override
    public void onSwap() {
        if (isOnCooldown()) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return;
        }

        Utils.setElytra(player);
        player.setVelocity(player.getVelocity().add(new Vector(0, 4.5, 0)));
        new BukkitRunnable() {
            @Override
            public void run() {
                runUnsafeActions(() -> player.getInventory().setChestplate(null));
            }
        }.runTaskLater(RacesPlugin.getInstance(), 60 * 20);

        setCooldown();
    }

    @Override
    public void activatePassiveAbilities() {
    }

    @Override
    public String getName() {
        return "Aasimar-Protector";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }
}
