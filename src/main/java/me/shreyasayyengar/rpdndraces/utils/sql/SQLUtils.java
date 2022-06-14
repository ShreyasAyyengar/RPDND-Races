package me.shreyasayyengar.rpdndraces.utils.sql;

import me.shreyasayyengar.rpdndraces.RacesPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLUtils {

    private SQLUtils() {
    }

    public static boolean hasRow(UUID uuid) throws SQLException {

        try (PreparedStatement preparedStatement = RacesPlugin.getDatabase().preparedStatement("select count(uuid) from races_info where uuid = '" + uuid.toString() + "'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1) != 0;
        }
    }

    public static void addRow(UUID uuid) throws SQLException {

        try (PreparedStatement preparedStatement = RacesPlugin.getDatabase().preparedStatement("insert into races_info (uuid, current_race) values ('" + uuid + "', null);")) {
            preparedStatement.executeUpdate();
        }
    }

    public static String getRace(UUID uuid) throws SQLException {

        try (PreparedStatement preparedStatement = RacesPlugin.getDatabase().preparedStatement("select * from races_info where uuid = '" + uuid + "';")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.getString("current_race") == null || resultSet.getString("current_race").isEmpty()) {
                return "";
            }

            return resultSet.getString("current_race");
        }
    }
}
