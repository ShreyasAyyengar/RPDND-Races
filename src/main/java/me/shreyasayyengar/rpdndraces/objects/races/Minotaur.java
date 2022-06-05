package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class Minotaur extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Part beast, part man, all savage. These brutes have the brutal recklessness of a charging bull.");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Minotaur(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.pushForward(player, 3);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 3, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
            }
        }.runTaskLater(RacesPlugin.getInstance(), (3 * 20) + 1);
    }

    @Override
    public String getName() {
        return "Minotaur";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
