package me.shreyasayyengar.rpdndraces.objects.races.genasi;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.UUID;

public abstract class AbstractGenasi extends AbstractRace {

    public AbstractGenasi(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        runUnsafeActions(() -> {
            if (canWaterBreath()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 1, false, false, false));
            }
        });
    }

    @Override
    public BukkitTask getTask() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }

    public abstract Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses();

    public abstract boolean canWaterBreath();

    // -----------------------------------------------------------------------------------------

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {
                if (getImmuneDamageCauses().contains(event.getCause())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
