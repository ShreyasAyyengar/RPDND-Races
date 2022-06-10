package me.shreyasayyengar.rpdndraces.utils;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.interfaces.InventoryRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
    }

    public static String colourise(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<Class<?>> getClassesForPackage(final String pkgName) throws IOException, URISyntaxException {
        final String pkgPath = pkgName.replace('.', '/');
        final URI pkg = Objects.requireNonNull(RacesPlugin.class.getClassLoader().getResource(pkgPath)).toURI();
        final ArrayList<Class<?>> allClasses = new ArrayList<>();

        Path root;
        if (pkg.toString().startsWith("jar:")) {
            try {
                root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
            } catch (final FileSystemNotFoundException e) {
                root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
            }
        } else {
            root = Paths.get(pkg);
        }

        final String extension = ".class";
        try (final Stream<Path> allPaths = Files.walk(root)) {
            allPaths.filter(Files::isRegularFile).forEach(file -> {
                try {
                    final String path = file.toString().replace('/', '.');
                    final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
                    allClasses.add(Class.forName(name));
                } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
                }
            });
        }
        return allClasses;
    }

    public static void handleSelection(Player player, String chosenRace) {

        player.closeInventory();

        try {
            Class<?> raceClass = Class.forName("me.shreyasayyengar.rpdndraces.objects.races." + chosenRace);

            if (InventoryRequirement.class.isAssignableFrom(raceClass)) {
                ItemStack itemStack = new ItemStack(Material.ELYTRA);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLocalizedName("not-removable");
                itemStack.setItemMeta(itemMeta);

                ItemStack[] contents = player.getInventory().getContents();

                boolean success = false;
                for (int i = 0; i < contents.length; i++) {
                    if (contents[i] == null) {

                        if (player.getInventory().getChestplate() != null) {

                            ItemStack chestplate = player.getInventory().getChestplate();
                            player.getInventory().setItem(i, chestplate);
                        }

                        player.getInventory().setChestplate(itemStack);
                        success = true;
                        break;
                    }
                }

                if (!success) {
                    player.sendMessage(colourise("&cYou do not have enough space in your inventory to equip the necessary items (chestplate)!"));
                }
            }

            raceClass.getDeclaredConstructor(UUID.class).newInstance(player.getUniqueId());

        } catch (ReflectiveOperationException ignored) {
            ignored.printStackTrace();
        }
    }
}