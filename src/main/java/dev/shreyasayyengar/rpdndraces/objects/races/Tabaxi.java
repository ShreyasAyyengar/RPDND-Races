package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Tabaxi extends AbstractRace implements BoostedDiet {

    public static List<String> getItemLore() {

        List<String> lore = List.of("These cat-like humanoids are as flexible and agile as they look, as well as fast and deadly predators with razor-like claws.");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of("Raw Diet");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Tabaxi(UUID uuid) {
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
        }.runTaskLater(RacesPlugin.getInstance(), (3 * 20) + 2);
    }

    @Override
    public String getName() {
        return "Tabaxi";
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

    @Override
    public Collection<Material> getBoostedFoods() {
        return List.of(
                Material.COD,
                Material.SALMON,
                Material.PUFFERFISH,
                Material.TROPICAL_FISH
        );
    }

    @Override
    public boolean allowsRaw() {
        return true;
    }
}
