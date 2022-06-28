package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Vedalken extends AbstractRace {

    private boolean potions = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("A race with a strong sense of curiosity and logica and a drive for perfection, for constant improvement.");
        List<String> active = List.of("Partially Amphibious");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Vedalken(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!potions) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 255);
            potions = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
            potions = false;
        }
    }

    @Override
    public String getName() {
        return "Vedalken";
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
        return null;
    }
}