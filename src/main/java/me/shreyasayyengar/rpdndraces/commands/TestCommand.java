package me.shreyasayyengar.rpdndraces.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 1) {
                String arg = args[0];

                try {
                    Class<?> subClasses = Class.forName("me.shreyasayyengar.rpdndraces.objects.races." + arg);
                    subClasses.getDeclaredConstructor(UUID.class).newInstance(player.getUniqueId());
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return false;
    }
}
