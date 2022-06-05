package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.UUID;

public class ItemConsume implements Listener {

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!RaceManager.hasRace(uuid)) return;

        AbstractRace race = RaceManager.getRace(uuid);

        System.out.println(player.getFoodLevel() + "<- food level");

        if (race instanceof BoostedDiet diet) {

            if (diet.justRaw()) {
                if (event.getItem().getI18NDisplayName().toLowerCase().contains("raw")) {
                    player.setFoodLevel(player.getFoodLevel() + 5);
                    return;
                }
            }

            if (diet.getBoostedFoods().contains(event.getItem().getType())) {
                player.setFoodLevel(diet.getFoodPoints());
            }
        }
    }
}
