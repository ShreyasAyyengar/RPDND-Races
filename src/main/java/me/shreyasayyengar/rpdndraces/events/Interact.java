package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interact implements Listener {

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
