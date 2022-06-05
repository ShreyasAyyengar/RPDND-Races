package me.shreyasayyengar.rpdndraces.events;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Appetitless;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import me.shreyasayyengar.rpdndraces.objects.interfaces.RequiredSetup;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.sql.SQLUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

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


            if (race instanceof RequiredSetup setup) {
                setup.setupPlayer();
            }

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

//            race.setupPlayer();
//            race.activatePassiveAbilities(); // TODO passive abils
//            race.getTask();
        }
    }
}
