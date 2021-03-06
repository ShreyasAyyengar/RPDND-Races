package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Triton extends AbstractRace implements BoostedDiet {

    private boolean potions = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("Tritons guard the ocean depths, portals to the elemental planes, and other dangerous spots far from the eyes of land-bound folk.");
        List<String> active = List.of("Aquatic Travel");
        List<String> passive = List.of("Raw Diet");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Triton(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!potions) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 255);
            RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 1000000, 5);
            potions = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            potions = false;
        }
    }

    @Override
    public String getName() {
        return "Triton";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
    }

    @Override
    public Collection<Material> getBoostedFoods() {
        return null;
    }

    @Override
    public boolean allowsRaw() {
        return false;
    }
}
