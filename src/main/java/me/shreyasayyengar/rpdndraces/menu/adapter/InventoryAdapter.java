package me.shreyasayyengar.rpdndraces.menu.adapter;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to adapt inventories
 */
public interface InventoryAdapter {

    /**
     * Allows users to adapt the menu to another inventory
     * you can think of this like a copy function, but it will copy
     * the display format to the new inventory and render it.
     *
     * @param inventory {@link Inventory}
     */
    void adapt(@NotNull Inventory inventory);
}
