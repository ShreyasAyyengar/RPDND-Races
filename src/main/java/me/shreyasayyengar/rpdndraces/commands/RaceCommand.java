package me.shreyasayyengar.rpdndraces.commands;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.menu.race.RaceMenuManager;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length == 0) {
                if (RaceManager.hasRace(player.getUniqueId())) {
                    player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou already have a race selected. Please contact an administrator to have it reset"));
                    return false;
                }

                try {
                    RaceMenuManager.openRacesMenu(player);
                } catch (Exception x) {
                    x.printStackTrace();
                }

                return false;
            }

            if (args.length != 2) {
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cInvalid syntax. Please use /race <reset|forceset> <player>"));
            }

            switch (args[0].toLowerCase()) {
                case "reset" -> {
                    if (sender.hasPermission("races.manage")) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (target == null) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cThat player is not online"));
                            return false;
                        }

                        RaceManager.removeRace(target.getUniqueId());

                    } else sender.sendMessage(Utils.colourise("&aYou do not have permission to execute this command!"));
                }


                case "forceset" -> {
                    if (sender.hasPermission("races.manage")) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (target == null) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cThat player is not online"));
                            return false;
                        }

                        RaceManager.removeRace(target.getUniqueId());

                        try {
                            Class<?> subClasses = Class.forName("me.shreyasayyengar.rpdndraces.objects.races." + args[2]);
                            subClasses.getDeclaredConstructor(UUID.class).newInstance(player.getUniqueId());
                        } catch (ReflectiveOperationException ignored) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cThat race does not exist!"));
                        }

                    } else sender.sendMessage(Utils.colourise("&aYou do not have permission to execute this command!"));

                }

            }

        }
        return false;
    }
}