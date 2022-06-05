package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Tortle extends AbstractRace implements PassiveAbilities {

    private boolean potions = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("An intelligent humanoid tortoise-like race. Once they are able to walk they", "seek the wanders of the world, adventure, or a simple life. ");
        List<String> active = List.of("Aquatic Travel");
        List<String> passive = List.of("Raw Diet", "Shell Defense");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Tortle(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!potions) {
            RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 255);
            RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 1000000, 5);
            potions = true;
        } else {
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            potions = false;
        }
    }

    @Override
    public String getName() {
        return "Tortle";
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

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 1000000, 3);
    }

    // TODO implement boosteddiet
}
