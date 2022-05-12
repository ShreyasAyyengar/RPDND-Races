package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class ElfWood extends AbstractElf {

    public ElfWood(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.INVISIBILITY, 15, 10);
    }

    @Override
    public String getName() {
        return "Elf-Wood";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 30;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
