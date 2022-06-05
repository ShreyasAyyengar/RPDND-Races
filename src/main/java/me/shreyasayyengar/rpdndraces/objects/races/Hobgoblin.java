package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Hobgoblin extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("War is the lifeblood of hobgoblins. They are fierce warriors and combatants.");
        List<String> active = List.of("Relentless");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Hobgoblin(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        // Nightvision 2, Strength 2, Regeneration 2 for 15
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 2);
    }

    @Override
    public String getName() {
        return "Hobgoblin";
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
