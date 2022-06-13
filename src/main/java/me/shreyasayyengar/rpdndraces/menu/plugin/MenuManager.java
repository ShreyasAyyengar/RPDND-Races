package me.shreyasayyengar.rpdndraces.menu.plugin;

import me.shreyasayyengar.rpdndraces.menu.interfaces.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class MenuManager implements Listener {

    private final Map<UUID, Menu> openedMenu;

    public MenuManager(JavaPlugin plugin) {
        this.openedMenu = new ConcurrentHashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openMenu(@NotNull Menu menu, @NotNull Player player, int size) {
        menu.open(player, size);
        this.openedMenu.put(player.getUniqueId(), menu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null || event.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if (openedMenu.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            Menu menu = openedMenu.get(player.getUniqueId());
            menu.getItem(event.getSlot()).ifPresent(item -> item.getCallback().onClick(player, item, event.getClick()));
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu menu = this.openedMenu.remove(player.getUniqueId());
        if (menu != null) {
            menu.getDisplay().getCloseCallback().ifPresent(callback -> callback.onClose(player));
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory() == null) {
            return;
        }

        if (this.openedMenu.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.openedMenu.remove(player.getUniqueId());
    }

}
