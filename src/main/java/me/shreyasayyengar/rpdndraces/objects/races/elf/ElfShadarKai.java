package me.shreyasayyengar.rpdndraces.objects.races.elf;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import me.shreyasayyengar.rpdndraces.utils.RaceUtils;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class ElfShadarKai extends AbstractElf {

    public ElfShadarKai(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (checkCooldown()) {
            RaceUtils.boostForward(player, 10);

            setCooldown();
        }
    }

    @Override
    public BukkitTask getTask() {
        return null;
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
}
