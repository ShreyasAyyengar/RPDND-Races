package me.shreyasayyengar.rpdndraces.utils;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.sql.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        RACE_MAP.remove(uuid);
    }

    public static boolean hasRace(UUID uuid) {
        return RACE_MAP.containsKey(uuid);
    }

    public static void writeToSQL() throws SQLException {

        PreparedStatement prepBuilder;
        for (UUID uuid : RACE_MAP.keySet()) {

            AbstractRace race = getRace(uuid);

            if (SQLUtils.hasRow(uuid)) {
                prepBuilder = RacesPlugin.getDatabase().preparedStatement("update races_info set current_race = '" + race.getName().toLowerCase() + "' where uuid = '" + uuid + "';");
            } else {
                prepBuilder = RacesPlugin.getDatabase().preparedStatement("insert into races_info (uuid, current_race) values ('" + uuid + "', '" + race.getName().toLowerCase() + "');");
            }

            prepBuilder.executeUpdate();
        }
    }

    public static void readFromSQL() throws SQLException {

        ResultSet resultSet = RacesPlugin.getDatabase().preparedStatement("select * from races_info").executeQuery();

        while (resultSet.next()) {

            UUID uuid = UUID.fromString(resultSet.getString("uuid"));
            String race = resultSet.getString("current_race");

//            switch (race.toLowerCase()) {
//                case "aarakocra" -> new Aarakocra(uuid);
//            }
        }
    }
}
