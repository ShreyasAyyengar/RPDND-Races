package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Shifter extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Shifters are sometimes called the weretouched, as many believe that they are the descendants of humans and lycanthropes.");
        List<String> active = List.of("Hunter");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Shifter(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 10, 2);
    }

    @Override
    public String getName() {
        return "Shifter";
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
        return Sound.ENTITY_FOX_SNIFF;
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}