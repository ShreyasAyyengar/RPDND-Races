package me.shreyasayyengar.rpdndraces.menu.objects;

import me.shreyasayyengar.rpdndraces.menu.interfaces.MenuClickCallback;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public record MenuItem(@NotNull ItemStack itemStack, @NotNull MenuClickCallback callback) {

    public ItemStack getItemStack() {
        return itemStack;
    }

    public MenuClickCallback getCallback() {
        return callback;
    }
}
