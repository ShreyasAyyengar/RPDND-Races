package me.shreyasayyengar.rpdndraces.menu.race;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.menu.MenuItem;
import me.shreyasayyengar.rpdndraces.menu.interfaces.Menu;
import me.shreyasayyengar.rpdndraces.menu.interfaces.MenuDisplay;
import me.shreyasayyengar.rpdndraces.objects.abst.*;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class RaceMenuManager {

    private static final List<Material> MATERIALS = Arrays.stream(Material.values()).filter(material -> material.name().contains("SPAWN_EGG")).toList();
    private static final List<Class<?>> CLEAN_RACES_CLASSES = RacesPlugin.getInstance().getCleanRaces();
    private static final List<Class<?>> ALL_RACE_CLASSES;

    static {
        try {
            ALL_RACE_CLASSES = Utils.getClassesForPackage("me.shreyasayyengar.rpdndraces.objects.races").stream()
                    .filter(AbstractRace.class::isAssignableFrom)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void openRacesMenu(Player player) throws ReflectiveOperationException {

        MenuDisplay.DisplayBuilder menu = MenuDisplay.create(RacesPlugin.PREFIX);

        for (int i = 0; i < CLEAN_RACES_CLASSES.size(); i++) {

            ItemStack raceItem = new ItemStack(MATERIALS.get(i));
            ItemMeta itemMeta = raceItem.getItemMeta();

            Class<?> raceClass = CLEAN_RACES_CLASSES.get(i);
            String presentableName = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(raceClass.getSimpleName()), " ")).replace("Abstract ", "");

            itemMeta.setDisplayName(Utils.colourise("&6&l" + presentableName));
            itemMeta.setLore((List<String>) raceClass.getMethod("getItemLore").invoke(null));
            itemMeta.setLocalizedName("rpdndraces." + raceClass.getSimpleName());
            raceItem.setItemMeta(itemMeta);

            menu.set(i, new MenuItem(raceItem, (whoClicked, menuItem, clickType) -> {

                ItemStack clickedItem = menuItem.getItemStack();

                if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("abstract")) {

                    if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("aasimar")) {
                        try {
                            supplyAasimarClasses(player);
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }

                    if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("elf")) {
                        try {
                            supplyElfClasses(player);
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }

                    if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("genasi")) {
                        try {
                            supplyGenasiClasses(player);
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }

                    if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("tiefling")) {
                        try {
                            supplyTieflingClasses(player);
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    Utils.handleSelection(whoClicked, clickedItem.getItemMeta().getLocalizedName().split("\\.")[1]);
                }

            }));
        }

        RacesPlugin.getMenuManager().openMenu(Menu.create(menu.build()), player, 54);
    }

    @SuppressWarnings("unchecked")
    private static void buildSubraceMenu(Player player, List<Class<?>> subRaceClasses) throws ReflectiveOperationException {

        MenuDisplay.DisplayBuilder menu = MenuDisplay.create(RacesPlugin.PREFIX);
        for (int i = 0; i < subRaceClasses.size(); i++) {
            ItemStack raceItem = new ItemStack(MATERIALS.get(i));
            ItemMeta itemMeta = raceItem.getItemMeta();

            Class<?> raceClass = subRaceClasses.get(i);
            String presentableName = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(raceClass.getSimpleName()), " "));

            itemMeta.setDisplayName(Utils.colourise("&6&l" + presentableName));
            itemMeta.setLore((List<String>) raceClass.getMethod("getItemLore").invoke(null));
            itemMeta.setLocalizedName("rpdndraces." + raceClass.getSimpleName());
            raceItem.setItemMeta(itemMeta);

            menu.set(i, new MenuItem(raceItem, (whoClicked, menuItem, clickType) -> {
                String chosenRace = menuItem.getItemStack().getItemMeta().getLocalizedName().split("\\.")[1];
                Utils.handleSelection(whoClicked, chosenRace);
            }));
        }

        RacesPlugin.getMenuManager().openMenu(Menu.create(menu.build()), player, 54);
    }

    private static void supplyAasimarClasses(Player player) throws ReflectiveOperationException {
        List<Class<?>> aasimarClasses = ALL_RACE_CLASSES.stream().filter(AbstractAasimar.class::isAssignableFrom).toList();
        buildSubraceMenu(player, aasimarClasses);
    }

    private static void supplyElfClasses(Player player) throws ReflectiveOperationException {
        List<Class<?>> elfClasses = ALL_RACE_CLASSES.stream().filter(AbstractElf.class::isAssignableFrom).toList();
        buildSubraceMenu(player, elfClasses);
    }

    private static void supplyGenasiClasses(Player player) throws ReflectiveOperationException {
        List<Class<?>> genasiClasses = ALL_RACE_CLASSES.stream().filter(AbstractGenasi.class::isAssignableFrom).toList();

        buildSubraceMenu(player, genasiClasses);
    }

    private static void supplyTieflingClasses(Player player) throws ReflectiveOperationException {
        List<Class<?>> tieflingClasses = ALL_RACE_CLASSES.stream().filter(AbstractTiefling.class::isAssignableFrom).toList();

        buildSubraceMenu(player, tieflingClasses);
    }
}
