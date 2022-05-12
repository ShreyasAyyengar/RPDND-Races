package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class HandSwap implements Listener {

    @EventHandler
    private void onSwap(PlayerSwapHandItemsEvent event) {

        Player player = event.getPlayer();
        if (RaceManager.hasRace(player.getUniqueId())) {
            event.setCancelled(true);

            AbstractRace race = RaceManager.getRace(player.getUniqueId());
            if (race.checkCooldown()) {
                race.onSwap();
                race.setCooldown();
                player.getWorld().playSound(player.getLocation(), race.getSound(), 1, 1);
            }
        }
    }
}
