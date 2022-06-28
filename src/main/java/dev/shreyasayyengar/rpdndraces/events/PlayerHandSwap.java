package dev.shreyasayyengar.rpdndraces.events;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import dev.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerHandSwap implements Listener {

    @EventHandler
    private void onSwap(PlayerSwapHandItemsEvent event) {

        Player player = event.getPlayer();
        if (RaceManager.hasRace(player.getUniqueId())) {
            event.setCancelled(true);

            AbstractRace race = RaceManager.getRace(player.getUniqueId());

            if (!race.isHandSwapEnabled()) return;

            if (race.checkCooldown()) {
                race.onSwap();
                race.setCooldown();
                player.playSound(player.getLocation(), race.getSound(), 1, 1);

                if (race.getRaceCooldown() == 0) return;
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &aYou activated your " + race.getDisplayName() + " skill!"));
            }
        }
    }
}
