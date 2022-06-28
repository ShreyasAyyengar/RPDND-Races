package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class HalfElf extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The child of an elf and a human, they have the grace of elves and the durability of humans.");
        List<String> active = List.of("Perception");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public HalfElf(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.getPerception(player);
    }

    @Override
    public String getName() {
        return "Half-Elf";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 30;
    }

    @Override
    public Sound getSound() {
        return Sound.BLOCK_BELL_RESONATE;
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}
