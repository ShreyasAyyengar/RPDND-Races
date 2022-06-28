package dev.shreyasayyengar.rpdndraces.events;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

public class EntityGlide implements Listener {

    @EventHandler
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (!RaceManager.hasRace(player.getUniqueId())) return;

        AbstractRace race = RaceManager.getRace(player.getUniqueId());

        if (race instanceof Glideable) {
            if (!player.isOnGround()) {
                event.setCancelled(true);
            }
        }
    }
}
