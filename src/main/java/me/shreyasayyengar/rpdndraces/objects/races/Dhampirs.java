package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Dhampirs extends AbstractRace implements PassiveAbilities {

    public Dhampirs(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 1);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }

    @Override
    public String getName() {
        return "Dhampirs";
    }

    @Override
    public void deactivate() {
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
    public void onEntityDamageByEntity(EntityDeathEvent event) {

        if (!(event.getEntity() instanceof Animals)) {
            return;
        }

        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();

            if (isThisRace(killer)) {
                event.getDrops().clear();
                killer.setFoodLevel(killer.getFoodLevel() + 8);
            }
        }
    }
}
