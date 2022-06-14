package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Orc extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Born tall and strong, orc society produces formidable, self sufficient warriors that pursue their target with relentless endurance and aggression.");
        List<String> active = List.of("Ground Stomp");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Orc(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 10, 2);

        RaceUtils.stomp(player);
    }

    @Override
    public String getName() {
        return "Orc";
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
        return Sound.ENTITY_RAVAGER_ATTACK;
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}
