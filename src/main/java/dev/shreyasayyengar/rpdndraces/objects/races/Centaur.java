package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.SpecialMovement;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class Centaur extends AbstractRace implements SpecialMovement {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Roamers at heart, centaurs love open spaces and the freedom to travel. They race the wind, hooves thundering and tails streaming behind them.");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Centaur(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.pushForward(player);
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
    public void onDisable() {
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
