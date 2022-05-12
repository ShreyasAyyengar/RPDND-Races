package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;

import java.util.UUID;

public class ElfShadarKai extends AbstractElf {

    public ElfShadarKai(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.boostForward(player, 10);
    }

    @Override
    public String getName() {
        return "Elf-Shadar-Kai";
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
