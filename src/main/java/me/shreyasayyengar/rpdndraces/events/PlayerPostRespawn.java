package me.shreyasayyengar.rpdndraces.events;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPostRespawn implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerPostRespawnEvent event) {

        Player player = event.getPlayer();

        if (RaceManager.hasRace(player.getUniqueId())) {

            AbstractRace race = RaceManager.getRace(player.getUniqueId());

            if (race instanceof PassiveAbilities abilities) {
                abilities.activatePassiveAbilities();
            }

        }
    }
}
