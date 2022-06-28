package dev.shreyasayyengar.rpdndraces.commands;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import dev.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class KenkuCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (!RaceManager.hasRace(player.getUniqueId())) return false;
            if (!RaceManager.getRace(player.getUniqueId()).getName().equalsIgnoreCase("kenku")) return false;
            if (args.length == 0) {
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + "&aUse /k <sound> to mimic a sound!"));
                return false;
            }

            try {
                String uncheckedSound = args[0].toLowerCase();
                Sound sound;
                switch (uncheckedSound) {
                    case "hiss" -> sound = Sound.ENTITY_CAT_HISS;
                    case "crunch" -> sound = Sound.ENTITY_GENERIC_EAT;
                    case "screech" -> sound = Sound.ENTITY_FOX_SCREECH;
                    case "worry" -> sound = Sound.ENTITY_PANDA_WORRIED_AMBIENT;
                    case "caw" -> sound = Sound.ENTITY_PHANTOM_AMBIENT;
                    case "warn" -> sound = Sound.ENTITY_POLAR_BEAR_WARNING;
                    case "roar" -> sound = Sound.ENTITY_RAVAGER_ROAR;
                    case "happy" -> sound = Sound.ENTITY_STRIDER_HAPPY;
                    case "meow" -> sound = Sound.ENTITY_CAT_PURREOW;
                    case "purr" -> sound = Sound.ENTITY_CAT_PURR;
                    case "door" -> sound = Sound.BLOCK_WOODEN_DOOR_CLOSE;
                    case "bell" -> sound = Sound.BLOCK_BELL_USE;
                    case "water" -> sound = Sound.BLOCK_WATER_AMBIENT;
                    case "click" -> sound = Sound.BLOCK_STONE_BUTTON_CLICK_ON;
                    case "sniff" -> sound = Sound.ENTITY_FOX_SNIFF;
                    case "laugh" -> sound = Sound.ENTITY_WITCH_CELEBRATE;
                    case "snap" -> sound = Sound.ENTITY_EVOKER_FANGS_ATTACK;
                    case "bloop" -> sound = Sound.BLOCK_BEEHIVE_ENTER;
                    case "sizzle" -> sound = Sound.BLOCK_LAVA_EXTINGUISH;
                    case "whine" -> sound = Sound.ENTITY_WOLF_WHINE;
                    case "rain" -> sound = Sound.WEATHER_RAIN;
                    case "howl" -> sound = Sound.ENTITY_WOLF_HOWL;
                    case "yes" -> sound = Sound.ENTITY_WANDERING_TRADER_YES;
                    case "no" -> sound = Sound.ENTITY_WANDERING_TRADER_NO;
                    case "potion" -> sound = Sound.ENTITY_SPLASH_POTION_BREAK;
                    default -> {
                        player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + "&cInvalid sound!"));
                        return false;
                    }
                }

                player.getWorld().playSound(player.getLocation(), sound, 1, 1);
            } catch (IllegalArgumentException e) {
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + "&cThat is not a valid sound!"));
                return false;
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return Stream.of("Hiss", "Crunch", "Screech", "Worry", "Caw", "Warn", "Roar", "Happy", "meow", "purr", "door",
                        "bell", "water", "click", "Sniff", "laugh", "snap", "Bloop", "Sizzle", "whine", "Rain", "howl", "yes", "no", "potion").map(String::toUpperCase).toList();
    }
}