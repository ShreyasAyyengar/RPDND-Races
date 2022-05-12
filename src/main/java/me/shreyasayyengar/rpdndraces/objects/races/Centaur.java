package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Centaur extends AbstractRace {

    public Centaur(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.boostForward(player, 3);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 3, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 3 * 20);
    }

    @Override
    public String getName() {
        return "Centaur";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 25;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
