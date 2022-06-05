package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Hexblood extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("This race are infused with eldritch magic, fey energy, or mysterious witchcraft.", "Many are created from deals with hags and others discover their heritage as they age.");
        List<String> active = List.of("Movement");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Hexblood(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 1);
        RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 15, 1);
    }

    @Override
    public String getName() {
        return "Hexblood";
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

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}
