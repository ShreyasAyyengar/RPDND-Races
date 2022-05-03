package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class Firbolg extends AbstractRace {

    private boolean nightVision = false;

    public Firbolg(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (checkCooldown()) {

            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1, 15, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 15, false, false));


            if (!nightVision) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1, 15, false, false));
                nightVision = true;
            } else {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                nightVision = false;
            }

            setCooldown();
        }
    }

    @Override
    public void activatePassiveAbilities() {
    }

    @Override
    public BukkitTask getTask() {
        return null;
    }

    @Override
    public String getName() {
        return "Firbolg";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 10;
    }
}
