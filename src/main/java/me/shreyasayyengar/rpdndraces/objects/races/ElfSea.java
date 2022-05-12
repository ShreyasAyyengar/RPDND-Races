package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class ElfSea extends AbstractElf {

    private boolean potion = false;

    public ElfSea(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!potion) {
            RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 5);
            RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 1000000, 5);
            potion = true;
        } else {
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            potion = false;
        }
    }

    @Override
    public String getName() {
        return "Elf-Sea";
    }

    @Override
    public void deactivate() {

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
