package me.shreyasayyengar.rpdndraces.menu.interfaces;

import me.shreyasayyengar.rpdndraces.menu.MenuItem;
import me.shreyasayyengar.rpdndraces.menu.SimpleDisplay;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

/**
 * More important component
 */
public interface MenuDisplay {

    /**
     * Use the displayerBuilder!
     *
     * @param title - title
     * @return builder
     */
    static DisplayBuilder create(@NotNull String title) {
        return new DisplayBuilder(title);
    }

    /**
     * Title of inventory
     *
     * @return title
     */
    @NotNull String getTitle();

    /**
     * Items in the inventory with index's
     *
     * @return contents
     */
    @NotNull Map<Integer, MenuItem> getContents();

    @NotNull Optional<MenuCloseCallback> getCloseCallback();

    void decorate(@NotNull String... lines);

    /**
     * Sets the item to said index slot
     *
     * @param slot - slot
     * @param item - item
     */
    default void setContent(int slot, @NotNull MenuItem item) {
        getContents().put(slot, item);
    }

    final class DisplayBuilder {

        private final MenuDisplay display;

        public DisplayBuilder(@NotNull String title) {
            this.display = new SimpleDisplay(title);
        }

        public DisplayBuilder set(int slot, @NotNull MenuItem item) {
            this.display.getContents().put(slot, item);
            return this;
        }

        public DisplayBuilder set(int slot, @NotNull ItemStack itemStack, @NotNull MenuClickCallback callback) {
            set(slot, new MenuItem(itemStack, callback));
            return this;
        }

        public MenuDisplay build() {
            return this.display;
        }
    }

}
