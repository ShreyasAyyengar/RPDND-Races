package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Appetitless;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;
        if (!RaceManager.hasRace(player.getUniqueId())) return;

        AbstractRace race = RaceManager.getRace(player.getUniqueId());

        if (race instanceof Appetitless) {
            event.setCancelled(true);
        }
    }
}
