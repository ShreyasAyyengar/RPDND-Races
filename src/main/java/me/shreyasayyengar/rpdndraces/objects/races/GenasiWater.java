package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiWater extends AbstractGenasi {

    public GenasiWater(UUID uuid) {
        super(uuid);
    }

    private boolean nightVision = false;

    @Override
    public Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses() {
        return List.of();
    }

    @Override
    public boolean canWaterBreath() {
        return true;
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 3, 3);
        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 3, 3);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 3, 1);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 20 * 3);
    }

    @Override
    public String getName() {
        return "Genasi-Water";
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
