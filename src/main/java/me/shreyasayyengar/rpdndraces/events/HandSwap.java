package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class HandSwap implements Listener {

    @EventHandler
    private void onSwap(PlayerSwapHandItemsEvent event) {

        if (RaceManager.hasRace(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            RaceManager.getRace(event.getPlayer().getUniqueId()).onSwap();
        }
    }
}
