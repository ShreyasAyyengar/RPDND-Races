package me.shreyasayyengar.rpdndraces.utils.sql;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLUtils {

    private SQLUtils() {
    }

    public static boolean hasRow(UUID uuid) throws SQLException {
        ResultSet resultSet = RacesPlugin.getDatabase().preparedStatement("select count(uuid) from races_info where uuid = '" + uuid.toString() + "'").executeQuery();
        resultSet.next();

        return resultSet.getInt(1) != 0;
    }

    public static void addRow(UUID uuid) throws SQLException {
        RacesPlugin.getDatabase().preparedStatement("insert into races_info (uuid, current_race) values ('" + uuid + "', null);").executeUpdate();
    }

    public static String getRace(UUID uuid) throws SQLException {
        ResultSet resultSet = RacesPlugin.getDatabase().preparedStatement("select * from races_info where uuid = '" + uuid + "';").executeQuery();
        resultSet.next();

        if (resultSet.getString("current_race") == null || resultSet.getString("current_race").isEmpty()) {
            return "";
        }

        return resultSet.getString("current_race");
    }

    public static void updateCurrentRace(UUID uuid, AbstractRace race) {

        String name = race.getName().toLowerCase();
        int cooldown = race.getCooldownTime();

        try {
            RacesPlugin.getDatabase().preparedStatement("update races_info set current_race = '" + name + "', cooldown = '" + cooldown + "' where uuid ='" + uuid + "';").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
