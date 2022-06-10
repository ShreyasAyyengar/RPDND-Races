package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public abstract class AbstractAasimar extends AbstractRace implements TaskedRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The descendents of humans marked by Celestials.", "They often champion the cause of their Celestial parent.", "Click to view more!");
        List<String> active = List.of();
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public AbstractAasimar(UUID uuid) {
        super(uuid);
    }

    private void heal(Player player) {
        if (player.getHealth() + 10 < 20) {
            player.setHealth(player.getHealth() + 10);
        } else {
            player.setHealth(20);
        }
    }

    @Override
    public void onSwap() {
        player.setVelocity(player.getVelocity().add(new Vector(0, 4.5, 0)));
        new BukkitRunnable() {
            @Override
            public void run() {
                runUnsafeActions(() -> player.getInventory().setChestplate(null));
            }
        }.runTaskLater(RacesPlugin.getInstance(), 60 * 20);
    }

    @Override
    public BukkitTask getRaceTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                player.getLocation().getNearbyPlayers(3.3).forEach(nearbyPlayer -> heal(nearbyPlayer));
                heal(player);
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1, 1);
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 1200);
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_PHANTOM_FLAP;
    }
}
