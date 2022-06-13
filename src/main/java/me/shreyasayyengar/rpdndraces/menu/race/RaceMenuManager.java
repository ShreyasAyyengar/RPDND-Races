package me.shreyasayyengar.rpdndraces.menu.race;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.menu.objects.MenuItem;
import me.shreyasayyengar.rpdndraces.menu.interfaces.Menu;
import me.shreyasayyengar.rpdndraces.menu.interfaces.MenuDisplay;
import me.shreyasayyengar.rpdndraces.objects.abst.*;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RaceMenuManager {

    private static final List<Material> MATERIALS = Arrays.stream(Material.values()).filter(material -> material.name().contains("SPAWN_EGG")).toList();
    private static final List<Class<?>> CLEAN_RACES_CLASSES = RacesPlugin.getInstance().getCleanRaces();
    private static final List<Class<?>> ALL_RACE_CLASSES;

    private static final List<Integer> CORNERS = List.of(0, 1, 7, 8, 9, 17, 36, 44, 45, 46, 52, 53);
    private static final List<Integer> ALLOWED_SLOTS = List.of(11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33, 38, 39, 40, 41, 42);
    private static final List<Integer> SUBRACE_PLACEMENT = List.of(13, 31, 21, 23, 20, 24);

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
    public static void openRacesMenu(Player player, int pageComingFrom) throws ReflectiveOperationException {

        MenuDisplay.DisplayBuilder menu = MenuDisplay.create("&4&l &k  &r  &9&lChoose your race  &4&l&k  ");

        List<Integer> redPanels = List.of(9, 17, 36, 44);
        List<Integer> bluePanels = List.of(0, 8, 45, 53);
        List<Integer> yellowPanels = List.of(1, 7, 46, 52);

        for (Integer redPanel : redPanels) {
            menu.set(redPanel, new MenuItem(pink(), (whoClicked, menuItem, clickType) -> {
            }));
        }

        for (Integer bluePanel : bluePanels) {
            menu.set(bluePanel, new MenuItem(blue(), (whoClicked, menuItem, clickType) -> {
            }));
        }

        for (Integer yellowPanel : yellowPanels) {
            menu.set(yellowPanel, new MenuItem(yellow(), (whoClicked, menuItem, clickType) -> {
            }));
        }

        menu.set(48, new MenuItem(previousPage(), (player1, menuItem, clickType) -> {
        }));
        menu.set(50, new MenuItem(nextPage(), (player1, menuItem, clickType) -> {
        }));

        for (int i = 11; i < 43; i++) {
            if (!ALLOWED_SLOTS.contains(i)) continue;

            List<Class<?>> concentratedClasses = new ArrayList<>(CLEAN_RACES_CLASSES.subList(20 * pageComingFrom, CLEAN_RACES_CLASSES.size())); // Contains the races needed only for the GUi

            // Essentially a predicate to say if the GUI will contain empty slots at the bottom
            if (concentratedClasses.size() <= i - 11) continue;

            Class<?> raceClass = concentratedClasses.get(i - 11);
            String presentableName = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(raceClass.getSimpleName()), " ")).replace("Abstract ", "");

            ItemStack raceItem = new ItemStack(MATERIALS.get(i - 11));
            ItemMeta itemMeta = raceItem.getItemMeta();
            itemMeta.setDisplayName(Utils.colourise("&6&l" + presentableName));
            itemMeta.setLore((List<String>) raceClass.getMethod("getItemLore").invoke(null));
            itemMeta.setLocalizedName("rpdndraces." + raceClass.getSimpleName());
            raceItem.setItemMeta(itemMeta);

            menu.set(i, new MenuItem(raceItem, (whoClicked, menuItem, clickType) -> {
                ItemStack clickedItem = menuItem.getItemStack();

                if (clickedItem.getItemMeta().getLocalizedName().equalsIgnoreCase("rpdndraces.filler")) {
                    return;
                }

                if (clickedItem.getItemMeta().getLocalizedName().toLowerCase().contains("abstract")) {

                    Class<?> abstractClass = SubRace.valueOf(clickedItem.getItemMeta().getLocalizedName().replace("rpdndraces.Abstract", "").toUpperCase()).getAbstractClass();
                    List<Class<?>> subRaceClasses = ALL_RACE_CLASSES.stream().filter(abstractClass::isAssignableFrom).toList();

                    try {
                        buildSubraceMenu(player, subRaceClasses);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Utils.handleSelection(whoClicked, clickedItem.getItemMeta().getLocalizedName().split("\\.")[1]);
                }
            }));

            menu.set(48, new MenuItem(previousPage(), (whoClicked, menuItem, clickType) -> {
                if (pageComingFrom == 0) return;

                try {
                    openRacesMenu(whoClicked, pageComingFrom - 1);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }));

            menu.set(50, new MenuItem(nextPage(), (whoClicked, menuItem, clickType) -> {
                try {
                    if (20 * (pageComingFrom + 1) < CLEAN_RACES_CLASSES.size()) {
                        openRacesMenu(player, pageComingFrom + 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }

        RacesPlugin.getMenuManager().openMenu(Menu.create(menu.build()), player, 54);
    }

    @SuppressWarnings("unchecked")
    private static void buildSubraceMenu(Player player, List<Class<?>> subRaceClasses) throws ReflectiveOperationException {

        MenuDisplay.DisplayBuilder menu = MenuDisplay.create("&0&l&kD&r&9&l Choose your Sub-race &0&l&kD");

        for (Integer slot : CORNERS) {
            menu.set(slot, new MenuItem(blue(), (whoClicked, menuItem, clickType) -> {
            }));
        }

        for (int i = 0; i < subRaceClasses.size(); i++) {
            ItemStack raceItem = new ItemStack(MATERIALS.get(i));
            ItemMeta itemMeta = raceItem.getItemMeta();

            Class<?> raceClass = subRaceClasses.get(i);
            String presentableName = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(raceClass.getSimpleName()), " "));

            itemMeta.setDisplayName(Utils.colourise("&6&l" + presentableName));
            itemMeta.setLore((List<String>) raceClass.getMethod("getItemLore").invoke(null));
            itemMeta.setLocalizedName("rpdndraces." + raceClass.getSimpleName());
            raceItem.setItemMeta(itemMeta);

            menu.set(SUBRACE_PLACEMENT.get(i), new MenuItem(raceItem, (whoClicked, menuItem, clickType) -> {
                String chosenRace = menuItem.getItemStack().getItemMeta().getLocalizedName().split("\\.")[1];
                Utils.handleSelection(whoClicked, chosenRace);
            }));
        }

        RacesPlugin.getMenuManager().openMenu(Menu.create(menu.build()), player, 54);
    }

    private static ItemStack blue() {
        return filler(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
    }

    private static ItemStack pink() {
        return filler(new ItemStack(Material.PINK_STAINED_GLASS_PANE));
    }

    private static ItemStack yellow() {
        return filler(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
    }

    private static ItemStack nextPage() {
        ItemStack stack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(Utils.colourise("&aNext Page"));
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack previousPage() {
        ItemStack stack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(Utils.colourise("&cPrevious Page"));
        stack.setItemMeta(meta);
        return stack;
    }

    @NotNull
    private static ItemStack filler(ItemStack stack) {
        stack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(" ");
        itemMeta.setLocalizedName("rpdndraces.filler");
        stack.setItemMeta(itemMeta);
        return stack;
    }
}
