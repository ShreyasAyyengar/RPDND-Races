package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.sql.SQLUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (!SQLUtils.hasRow(player.getUniqueId())) {
            SQLUtils.addRow(player.getUniqueId());
        }

        if (RaceManager.hasRace(player.getUniqueId())) {
            AbstractRace race = RaceManager.getRace(player.getUniqueId());
            race.setPlayer(player);
            race.setupPlayer();
            race.activatePassiveAbilities();
            race.getTask();
        }
    }
}
