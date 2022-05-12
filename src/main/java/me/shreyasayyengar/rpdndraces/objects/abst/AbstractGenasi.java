package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.UUID;

public abstract class AbstractGenasi extends AbstractRace implements PassiveAbilities {

    public AbstractGenasi(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        runUnsafeActions(() -> {
            if (canWaterBreath()) {
                RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 1);
            }
        });
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }

    @Override
    public Sound getSound() {
        return null;
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