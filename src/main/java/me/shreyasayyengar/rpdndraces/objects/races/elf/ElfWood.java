package me.shreyasayyengar.rpdndraces.objects.races.elf;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class ElfWood extends AbstractElf {

    public ElfWood(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (checkCooldown()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15, 10));
            setCooldown();
        }
    }

    @Override
    public BukkitTask getTask() {
        return null;
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
}
