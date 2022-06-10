package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Firbolg extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The reclusive Firbolg tends to live in the quiet harmony of the woods. They have strong ties to nature and druidic magic.");
        List<String> active = List.of("Hidden Step");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    private boolean nightVision = false;

    public Firbolg(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {

        RaceUtils.addPotionEffect(player, PotionEffectType.INVISIBILITY, 10, 1);
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 10, 1);

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
        return "Firbolg";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 15;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_EVOKER_CAST_SPELL;
    }
}
