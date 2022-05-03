package me.shreyasayyengar.rpdndraces.objects.races.elf;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class ElfSea extends AbstractElf {

    private boolean potion = false;

    public ElfSea(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (checkCooldown()) {

            if (!potion) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 5));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1000000, 5));
                potion = true;
            } else {
                player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
                potion = false;
            }

            setCooldown();
        }
    }

    @Override
    public BukkitTask getTask() {
        return null;
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
}
