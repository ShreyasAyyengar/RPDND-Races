package me.shreyasayyengar.rpdndraces.inventory.menu.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MenuPlugin extends JavaPlugin {

    private MenuManager menuManager;

    @Override
    public void onEnable() {
        this.menuManager = new MenuManager(this);
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }
}
