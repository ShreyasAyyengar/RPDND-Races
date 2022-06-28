package dev.shreyasayyengar.rpdndraces.utils.sql;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLUtils {

    private SQLUtils() {
    }

    public static boolean hasRow(UUID uuid) throws SQLException {

        try (PreparedStatement preparedStatement = RacesPlugin.getDatabase().preparedStatement("select count(uuid) from rpdnd_races where uuid = '" + uuid.toString() + "'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1) != 0;
        }
    }

    public static void addRow(UUID uuid) throws SQLException {

        try (PreparedStatement preparedStatement = RacesPlugin.getDatabase().preparedStatement("insert into rpdnd_races (uuid, current_race) values ('" + uuid + "', null);")) {
            preparedStatement.executeUpdate();
        }
    }
}
