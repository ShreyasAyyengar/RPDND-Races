package me.shreyasayyengar.rpdndraces.inventory.menu;

import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.Menu;
import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.MenuDisplay;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SimpleMenu implements Menu {

    private MenuDisplay display;

    public SimpleMenu(@NotNull MenuDisplay display) {
        this.display = display;
    }

    @Override
    public void apply(@NotNull MenuDisplay display) {
        this.display = display;
    }

    @Override
    public void adapt(@NotNull Inventory inventory) {
        this.display.getContents().forEach((slot, item) -> inventory.setItem(slot, item.getItemStack()));
    }

    @Override
    public void open(@NotNull Player player, int size) {
        Inventory inventory = Bukkit.createInventory(player, size, Utils.colourise(display.getTitle()));
        adapt(inventory);
        player.openInventory(inventory);
    }

    @Override
    public @NotNull Optional<MenuItem> getItem(int slot) {
        return Optional.of(this.display.getContents().get(slot));
    }

    @NotNull
    @Override
    public MenuDisplay getDisplay() {
        return this.display;
    }
}
