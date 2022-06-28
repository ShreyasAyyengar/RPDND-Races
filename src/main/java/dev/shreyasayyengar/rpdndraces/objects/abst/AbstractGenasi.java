package dev.shreyasayyengar.rpdndraces.objects.abst;

import dev.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class AbstractGenasi extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("A rare humanoid touched by the elemental planes, Genasi are typically offsprings of genies and mortals. &e&lClick &e&lto &e&lview &e&lmore!");
        List<String> active = List.of();
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

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
    public void onDisable() {
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
