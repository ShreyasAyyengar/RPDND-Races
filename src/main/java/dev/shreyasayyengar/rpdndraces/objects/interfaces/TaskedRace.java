package dev.shreyasayyengar.rpdndraces.objects.interfaces;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.scheduler.BukkitTask;

/**
 * The TaskedRace interface is used to define a BukkitTask that is tied to an {@link AbstractRace}. If a certain race
 * requires actions every certain ticks, this interface can be implemented with the method {@link TaskedRace#getRaceTask()}. (For example, particles following a player,
 * consistent health regeneration every few seconds). The method {@link TaskedRace#getRaceTask()} should return a concrete BukkitTask that is tied to the specific {@link AbstractRace}.<p></p>
 * This method should only return the <strong>BukkitTask needed for the race</strong> to continue working, and no other information. This method should not be called by the implementing class
 * either
 *
 * @author Shreyas Ayyengar
 * @since 1.0
 */
public interface TaskedRace {
    BukkitTask getRaceTask();
}
