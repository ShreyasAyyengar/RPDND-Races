package me.shreyasayyengar.rpdndraces.commands;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.inventory.menu.interfaces.MenuDisplay;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (RaceManager.hasRace(player.getUniqueId())) {
                player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " You already have a race selected. Please contact an administrator to have it reset"));
                return false;
            }

            try {

                MenuDisplay.DisplayBuilder menu = MenuDisplay.create(RacesPlugin.PREFIX);

                Collection<Material> materials = Arrays.stream(Material.values()).filter(material -> material.name().contains("SPAWN_EGG")).toList();


                for (int i = 0; i < RacesPlugin.getInstance().getRaceClasses().size(); i++) {

                    Class<?> raceClass = RacesPlugin.getInstance().getRaceClasses().get(i);
                    String presentableName = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(raceClass.getSimpleName()), " "));

                    Material material = materials.stream().skip(new Random().nextInt(materials.size())).findFirst().get();
                    ItemStack raceItem = new ItemStack(material);
                    ItemMeta itemMeta = raceItem.getItemMeta();

                    itemMeta.setDisplayName(Utils.colourise(presentableName));
                    itemMeta.setLore((List<String>) raceClass.getMethod("getItemLore").invoke(null));
                    itemMeta.setLocalizedName("rpdndraces." + raceClass.getSimpleName());


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }
}
