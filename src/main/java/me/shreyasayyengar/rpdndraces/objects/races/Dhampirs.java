package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class Dhampirs extends AbstractRace {

    public Dhampirs(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {

        if (isOnCooldown()) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2, false, false, false));
    }

    @Override
    public void activatePassiveAbilities() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false, false));
    }

    @Override
    public BukkitTask getTask() {
        return null;
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

    @EventHandler
    public void onEntityDamageByEntity(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();

            if (isThisRace(killer)) {
                event.getDrops().clear();
                killer.setFoodLevel(killer.getFoodLevel() + 4);
            }
        }
    }
}
