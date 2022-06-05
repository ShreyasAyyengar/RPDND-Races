package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Gnome extends AbstractRace {

    private boolean nightVision = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("Gnomes take delight in life, enjoying every moment of invention,", "exploration, investigation, creation and play.");
        List<String> active = List.of("Nightvision");
        List<String> passive = List.of("Hardy Body");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Gnome(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 1);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }
    }

    @Override
    public String getName() {
        return "Gnome";
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
        if (event.getEntityType() == EntityType.PLAYER) {
            if (isThisRace(((Player) event.getEntity()))) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setDamage(event.getDamage() / 2);
                }
            }
        }
    }
}
