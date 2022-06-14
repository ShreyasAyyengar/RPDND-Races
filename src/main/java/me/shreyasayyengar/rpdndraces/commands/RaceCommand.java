package me.shreyasayyengar.rpdndraces.commands;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
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
                    player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou already have a race selected. &c&nPlease contact an administrator to have it reset"));
                    return false;
                }

                try {
                    RacesPlugin.getRaceMenuManager().openRacesMenu(player, 0);
                } catch (Exception x) {
                    x.printStackTrace();
                }

                return false;
            }

            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "on", "off" -> {
                        if (!RaceManager.hasRace(player.getUniqueId())) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou do not have a race selected!"));
                            return false;
                        }

                        AbstractRace race = RaceManager.getRace(player.getUniqueId());
                        race.setHandSwapEnabled(!race.isHandSwapEnabled());

                        player.sendMessage(Utils.colourise(race.isHandSwapEnabled() ? RacesPlugin.PREFIX + " &cYou have &aenabled &cyour Hand-Swap ability!" : RacesPlugin.PREFIX + " &cYou have &cdisabled your Hand-Swap ability!"));
                    }
                }
            }

            if (args.length == 2) {

                if ("reset".equalsIgnoreCase(args[0])) {
                    if (sender.hasPermission("races.manage")) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (target == null) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cThat player is not online"));
                            return false;
                        }

                        RaceManager.removeRace(target.getUniqueId());
                        target.getActivePotionEffects().clear(); // TODO not resetting

                        if (player.getUniqueId().equals(target.getUniqueId())) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYour race has been &areset&c!"));
                        } else {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &aResetting " + target.getName() + "'s current race."));
                            target.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYour race has been &areset&c!"));
                        }

                    } else sender.sendMessage(Utils.colourise("&aYou do not have permission to execute this command!"));
                }
            }

            if (args.length == 3) {
                if (args[2].equalsIgnoreCase("forceset")) {
                    if (sender.hasPermission("races.manage")) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (target == null) {
                            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cThat player is not online"));
                            return false;
                        }

                        RaceManager.removeRace(target.getUniqueId());
                        target.getActivePotionEffects().clear();

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