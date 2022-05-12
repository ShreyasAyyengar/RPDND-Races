package me.shreyasayyengar.rpdndraces.inventory.menu;

import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.MenuCloseCallback;
import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.MenuDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleDisplay implements MenuDisplay {

    private final String title;
    private final Map<Integer, MenuItem> contents;

    public SimpleDisplay(String title) {
        this.title = title;
        this.contents = new LinkedHashMap<>();
    }

    @Override
    public @NotNull String getTitle() {
        return this.title;
    }

    @Override
    public @NotNull Map<Integer, MenuItem> getContents() {
        return this.contents;
    }

    @Override
    public @NotNull Optional<MenuCloseCallback> getCloseCallback() {
        return Optional.empty();
    }

    @Override
    public void decorate(@NotNull String... lines) {
        /// TODO: 2022-01-21  
    }
}
