package dev.shreyasayyengar.rpdndraces.events;

import me.libraryaddict.disguise.DisguiseAPI;
import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.Appetitless;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import dev.shreyasayyengar.rpdndraces.objects.races.Changeling;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import dev.shreyasayyengar.rpdndraces.utils.Utils;
import dev.shreyasayyengar.rpdndraces.utils.sql.SQLUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import java.sql.SQLException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (!SQLUtils.hasRow(player.getUniqueId())) {
            SQLUtils.addRow(player.getUniqueId());
        }

        if (RaceManager.hasRace(player.getUniqueId())) {
            AbstractRace race = RaceManager.getRace(player.getUniqueId());
            race.setPlayer(player);

            if (race instanceof PassiveAbilities passiveAbilities) {
                passiveAbilities.activatePassiveAbilities();
            }

            if (race instanceof TaskedRace task) {
                BukkitTask raceTask = task.getRaceTask();
                race.setRegisteredTask(raceTask);
            }

            if (race instanceof Appetitless) {
                player.setFoodLevel(20);
            }

            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou have chosen " + race.getDisplayName() + "!"));

            if (race.getName().equalsIgnoreCase("changeling")) {
                if (Changeling.TO_REMOVE.contains(player.getUniqueId())) {
                    DisguiseAPI.undisguiseToAll(player);
                    Changeling.TO_REMOVE.remove(player.getUniqueId());
                }
            }
        }
    }
}
