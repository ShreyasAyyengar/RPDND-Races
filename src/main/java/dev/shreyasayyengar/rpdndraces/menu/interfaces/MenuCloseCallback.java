package dev.shreyasayyengar.rpdndraces.menu.interfaces;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface MenuCloseCallback {
    void onClose(@NotNull Player player);
}
