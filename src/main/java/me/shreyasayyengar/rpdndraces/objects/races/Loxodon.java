package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Loxodon extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("While they look like brutes, these gentle giants prefer peace and", "wisdom over chaos and carnage, At least, until provoked.");
        List<String> active = List.of("Instinct");
        List<String> passive = List.of("Tough Hide");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Loxodon(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);
    }

    @Override
    public String getName() {
        return "Loxodon";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return null;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {

                if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
