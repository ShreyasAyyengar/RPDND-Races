package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public abstract class AbstractAasimar extends AbstractRace {

    public AbstractAasimar(UUID uuid) {
        super(uuid);

        this.task = getTask();
    }

    @Override
    public BukkitTask getTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                player.getLocation().getNearbyPlayers(3.3).forEach(nearbyPlayer -> {
                    if (nearbyPlayer.getHealth() + 5 < 20) {
                        nearbyPlayer.setHealth(nearbyPlayer.getHealth() + 5);
                    }

                    // TODO: Check if this includes the player itself
                });
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 1200);
    }
}
