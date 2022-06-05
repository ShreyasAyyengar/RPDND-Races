package me.shreyasayyengar.rpdndraces;

import me.shreyasayyengar.rpdndraces.commands.RaceCommand;
import me.shreyasayyengar.rpdndraces.commands.TestCommand;
import me.shreyasayyengar.rpdndraces.events.*;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.Config;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import me.shreyasayyengar.rpdndraces.utils.sql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public final class RacesPlugin extends JavaPlugin {

    public static final String PREFIX = Utils.colourise("&6&l[&c&lRaces&6&l]");

    private List<Class<?>> raceClasses;
    private List<Class<?>> abstClasses;

    private MySQL database;

    public static RacesPlugin getInstance() {
        return RacesPlugin.getPlugin(RacesPlugin.class);
    }

    public static MySQL getDatabase() {
        return getInstance().database;
    }

    @Override
    public void onEnable() {
        this.getLogger().info(PREFIX + "Races Plugin Starting Up...");

        registerCommands();
        registerEvents();
        registerRaceClasses();

        Config.init(this);
        initMySQL();

        loadRaces();
        AbstractRace.startTask();
    }

    private void registerCommands() {
        this.getCommand("test").setExecutor(new TestCommand());
        this.getCommand("race").setExecutor(new RaceCommand());
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

    private void registerRaceClasses() {
        this.raceClasses = Utils.getClassesInPackage("me.shreyasayyengar.rpdndraces.objects.races");
        this.abstClasses = Utils.getClassesInPackage("me.shreyasayyengar.rpdndraces.objects.abst");
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

    public List<Class<?>> getRaceClasses() {
        return raceClasses;
    }

    public List<Class<?>> getAbstractClasses() {
        return abstClasses;
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