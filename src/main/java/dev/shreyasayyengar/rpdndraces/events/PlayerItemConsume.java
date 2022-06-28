package dev.shreyasayyengar.rpdndraces.events;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.UUID;

public class PlayerItemConsume implements Listener {

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!RaceManager.hasRace(uuid)) return;

        AbstractRace race = RaceManager.getRace(uuid);

        if (race instanceof BoostedDiet diet) {

            if (diet.allowsRaw()) {

                if (event.getItem().getI18NDisplayName() == null) return;

                if (event.getItem().getI18NDisplayName().toLowerCase().contains("raw")) {
                    player.setFoodLevel(player.getFoodLevel() + 5);
                    return;
                }
            }

            if (diet.getBoostedFoods().contains(event.getItem().getType())) {
                event.getItem().subtract();
                player.setFoodLevel(player.getFoodLevel() + 5);
            }
        }
    }
}
