package me.shreyasayyengar.rpdndraces.objects.interfaces;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.entity.Player;

/**
 * This interface is used to define the passive abilities of an {@link AbstractRace}. If the race requires
 * any type of consistent potion effects, inventory modification, or data related to {@link Player}, this interface
 * can be implemented. <strong>BukkitTasks or BukkitRunnables should not be created/started from this method</strong>.
 * @see TaskedRace
 *
 * @author Shreyas Ayyengar
 * @since 1.0
 */
public interface PassiveAbilities {
    void activatePassiveAbilities();
}
