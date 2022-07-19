package dev.shreyasayyengar.rpdndraces.utils;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.utils.sql.SQLUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RaceManager {

    private RaceManager() {
    }

    private static final Map<UUID, AbstractRace> RACE_MAP = new HashMap<>();

    public static AbstractRace getRace(UUID uuid) {
        return RACE_MAP.get(uuid);
    }

    public static void setRace(UUID uuid, AbstractRace race) {
        RACE_MAP.put(uuid, race);
    }

    public static void removeRace(UUID uuid) {
        if (hasRace(uuid)) {
            RACE_MAP.get(uuid).onDisable();

            if (RACE_MAP.get(uuid).getRegisteredTask() != null) {
                RACE_MAP.get(uuid).getRegisteredTask().cancel();
            }

            RACE_MAP.remove(uuid);

            RacesPlugin.getDatabase().preparedStatementBuilder("delete from rpdnd_races where uuid = '" + uuid + "';").executeUpdate();
        }
    }

    public static boolean hasRace(UUID uuid) {
        return RACE_MAP.containsKey(uuid);
    }

    public static void writeToSQL() throws SQLException {

        for (UUID uuid : RACE_MAP.keySet()) {

            AbstractRace race = getRace(uuid);

            if (SQLUtils.hasRow(uuid)) {
                RacesPlugin.getDatabase().preparedStatementBuilder("update rpdnd_races set current_race = '" + race.getName() + "' where uuid = '" + uuid + "';").executeUpdate();
            } else {
                RacesPlugin.getDatabase().preparedStatementBuilder("insert into rpdnd_races (uuid, current_race) values ('" + uuid + "', '" + race.getName() + "');").executeUpdate();
            }
        }
    }

    public static void readFromSQL() throws SQLException {
        RacesPlugin.getDatabase().preparedStatementBuilder("select * from rpdnd_races;").executeQuery(resultSet -> {
            try {
                while (resultSet.next()) {

                    UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                    String race = resultSet.getString("current_race");

                    if (race == null) return;

                    try {
                        Class<?> subClasses = Class.forName("dev.shreyasayyengar.rpdndraces.objects.races." + race.replace("-", ""));
                        subClasses.getDeclaredConstructor(UUID.class).newInstance(uuid);
                    } catch (ReflectiveOperationException x) {
                        x.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}