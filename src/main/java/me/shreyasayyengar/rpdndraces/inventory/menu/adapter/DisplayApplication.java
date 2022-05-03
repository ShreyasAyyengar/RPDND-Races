package me.shreyasayyengar.rpdndraces.inventory.menu.adapter;

import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.MenuDisplay;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to apply new displays
 */
public interface DisplayApplication {

    /**
     * This is the most 'reactive' component of the application
     * It works similar to how ReactJS works, it will update the current
     * inventory with new display, not creating a new inventory.
     *
     * @param display {@link MenuDisplay}
     */
    void apply(@NotNull MenuDisplay display);
}
