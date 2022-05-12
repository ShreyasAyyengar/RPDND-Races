package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class Leave implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        if (RaceManager.hasRace(uuid)) {

            AbstractRace race = RaceManager.getRace(uuid);

            if (race instanceof TaskedRace) {
                race.getRegisteredTask().cancel();
            }
        }
    }
}
