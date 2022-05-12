package me.shreyasayyengar.rpdndraces.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    private Utils() {
    }

    public static String colourise(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void setElytra(Player player) {
        ItemStack itemStack = new ItemStack(Material.ELYTRA);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLocalizedName("not-removable");
        itemStack.setItemMeta(itemMeta);

        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == null) {

                if (player.getInventory().getChestplate() != null) {

                    ItemStack chestplate = player.getInventory().getChestplate();

                    player.getInventory().setItem(i, chestplate);
                    player.getInventory().setChestplate(itemStack);
                } else player.getInventory().setChestplate(itemStack);

                break;
            }
        }
    }
}