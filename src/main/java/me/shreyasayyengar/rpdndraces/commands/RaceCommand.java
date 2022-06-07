package me.shreyasayyengar.rpdndraces.commands;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.menu.race.RaceMenuManager;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (RaceManager.hasRace(player.getUniqueId())) {
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou already have a race selected. Please contact an administrator to have it reset"));
                return false;
            }

            if (args.length == 0) {
                try {
                    RaceMenuManager.openRacesMenu(player);
                } catch (Exception x) {
                    x.printStackTrace();
                }

                return false;
            }

            if (sender.hasPermission("races.manage")) {




            } else sender.sendMessage(Utils.colourise("&aYou do not have permission to execute this command!"));
        }

        return false;
    }

}
