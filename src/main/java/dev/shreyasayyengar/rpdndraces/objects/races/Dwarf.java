package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Dwarf extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("As durable as the mountains they live within, these squat, hairy creatures know the earth like no other race.");
        List<String> active = List.of("Mine Life");
        List<String> passive = List.of("");

        return RaceUtils.formatLore(lore, active, passive);
    }

    private boolean nightVision = false;

    public Dwarf(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {

        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);

        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }
    }

    @Override
    public String getName() {
        return "Dwarf";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 10;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
