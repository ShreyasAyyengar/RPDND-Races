package dev.shreyasayyengar.rpdndraces.events;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (!RaceManager.hasRace(event.getPlayer().getUniqueId())) return;
        if (event.getItem() == null) return;

        AbstractRace race = RaceManager.getRace(event.getPlayer().getUniqueId());
        ItemStack item = event.getItem();

        if (item.getType() != Material.FIREWORK_ROCKET) return;

        if (race instanceof Glideable) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(RacesPlugin.PREFIX + " You cannot use that here!");
        }
    }
}
