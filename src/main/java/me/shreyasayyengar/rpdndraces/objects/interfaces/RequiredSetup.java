package me.shreyasayyengar.rpdndraces.objects.interfaces;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * This interface is used to define the required setup for a race in relation to a player. The
 * {@link RequiredSetup#setupPlayer()} method is called when a player selects the race (and is online)
 * or when a player rejoins the server. {@link BukkitTask}/{@link BukkitRunnable} should <strong>NOT</strong> use this
 * method to retrieve BukkitTasks or Runnables, and to start them subsequently. If a race wishes to do this, they <storng>must implement</storng> {@link TaskedRace}
 * interface and delegate their methods to that class.
 *
 * @author Shreyas Ayyengar
 * @since 1.0
 */
public interface RequiredSetup {
    void setupPlayer();
}
