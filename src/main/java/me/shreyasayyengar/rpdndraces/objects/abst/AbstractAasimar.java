package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public abstract class AbstractAasimar extends AbstractRace implements TaskedRace {

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
    public BukkitTask getRaceTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                player.getLocation().getNearbyPlayers(3.3).forEach(nearbyPlayer -> heal(nearbyPlayer));
                heal(player);
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 1200);
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
