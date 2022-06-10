package me.shreyasayyengar.rpdndraces;

import me.shreyasayyengar.rpdndraces.commands.KenkuCommand;
import me.shreyasayyengar.rpdndraces.commands.RaceCommand;
import me.shreyasayyengar.rpdndraces.events.*;
import me.shreyasayyengar.rpdndraces.menu.plugin.MenuManager;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.Config;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import me.shreyasayyengar.rpdndraces.utils.sql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public final class RacesPlugin extends JavaPlugin {

    public static final String PREFIX = Utils.colourise("&6&l[&c&lRaces&6&l]");

    private MySQL database;
    private MenuManager menuManager;

    public static RacesPlugin getInstance() {
        return RacesPlugin.getPlugin(RacesPlugin.class);
    }
    public static MySQL getDatabase() {
        return getInstance().database;
    }
    public static MenuManager getMenuManager() {
        return getInstance().menuManager;
    }

    @Override
    public void onEnable() {
        this.getLogger().info(PREFIX + "Races Plugin Starting Up...");

        registerCommands();
        registerEvents();

        Config.init(this);
        initMySQL();

        loadRaces();
        AbstractRace.startTask();

        this.menuManager = new MenuManager(this);

        System.gc();
    }

    private void registerCommands() {
        this.getCommand("races").setExecutor(new RaceCommand());
        this.getCommand("kenku").setExecutor(new KenkuCommand());
    }

    private void registerEvents() {
        Stream.of(
                new Join(),
                new HandSwap(),
                new Leave(),
                new FoodLevelChange(),
                new ItemConsume()
        ).forEach(event -> this.getServer().getPluginManager().registerEvents(event, this));
    }

    private void initMySQL() {
        this.database = new MySQL(Config.getSQL("username"), Config.getSQL("password"), Config.getSQL("database"), Config.getSQL("host"), Integer.parseInt(Config.getSQL("port")));

        try {
            //noinspection resource
            this.database.preparedStatement("create table if not exists races_info(" +
                    "    uuid         varchar(36) null," +
                    "    current_race tinytext    null" +
                    ");").executeUpdate();
        } catch (SQLException e) {
            getLogger().severe("There was an issue creating MySQL tables, please send this error for support\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadRaces() {
        try {
            RaceManager.readFromSQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Class<?>> getCleanRaces() {
        try {

            List<Class<?>> races = Utils.getClassesForPackage("me.shreyasayyengar.rpdndraces.objects.races");
            List<Class<?>> abstRaces = Utils.getClassesForPackage("me.shreyasayyengar.rpdndraces.objects.abst");
            List<Class<?>> allRaces = new ArrayList<>();

            List<Class<?>> sortedAbstRaces = new ArrayList<>();

            List<Class<?>> classesToRemove = new ArrayList<>();

            allRaces.addAll(races);
            allRaces.addAll(abstRaces);

            allRaces.removeIf(aClass -> aClass.getSimpleName().isEmpty()); // remove classes like [' ', ' ']
            allRaces.removeIf(aClass -> aClass.getSimpleName().equalsIgnoreCase("AbstractRace")); // removes base abstract class
            allRaces.removeIf(aClass -> !AbstractRace.class.isAssignableFrom(aClass)); // Remove classes that aren't subclasses of AbstractRace

            allRaces.forEach(raceClass -> {
                if (raceClass.getSimpleName().contains("Abstract")) {
                    sortedAbstRaces.add(raceClass);
                }
            });
            allRaces.forEach(raceClass -> {
                sortedAbstRaces.forEach(abstRaceClass -> {
                    if (abstRaceClass.isAssignableFrom(raceClass) && !raceClass.getSimpleName().equalsIgnoreCase(abstRaceClass.getSimpleName())) {
                        classesToRemove.add(raceClass);
                    }
                });
            });

            allRaces.removeAll(classesToRemove);
            allRaces.sort(Comparator.comparing(aClass -> aClass.getSimpleName().toLowerCase()));

            return allRaces;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onDisable() {
        this.getLogger().info(PREFIX + " Plugin Shutting Down...");

        try {
            RaceManager.writeToSQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

// TODO: Perhaps rethink the task system in AbstractRace and its impls; it's a bit messy;
// TODO: find a fix for dupe code in Centaur, Satyr and Minotaur (create an interface with SpecialMovement?