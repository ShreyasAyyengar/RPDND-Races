package me.shreyasayyengar.rpdndraces.inventory.menu.interfaces;

import me.shreyasayyengar.rpdndraces.inventory.menu.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for when you click the item
 */
public interface MenuClickCallback {

    /**
     * @param player    - {@link Player} who clicked item
     * @param menuItem  - {@link MenuItem} that was clicked
     * @param clickType - {@link ClickType} how the {@link MenuItem} was clicked
     */
    void onClick(@NotNull Player player, @NotNull MenuItem menuItem, @NotNull ClickType clickType);
}
