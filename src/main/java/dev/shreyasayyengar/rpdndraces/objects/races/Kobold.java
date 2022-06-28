package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Kobold extends AbstractRace {

    private boolean nightVision = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("A small, lizard-like race, kobolds rely on numbers and packs rather than individual strength, much like the tactics of wolves.");
        List<String> active = List.of("Nightvision");
        List<String> passive = List.of("Scale Defense");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Kobold(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }
    }

    @Override
    public String getName() {
        return "Kobold";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 0;
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
