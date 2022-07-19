package dev.shreyasayyengar.rpdndraces.utils.sql;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class SQLUtils {

    private SQLUtils() {
    }

    public static boolean hasRow(UUID uuid) {
        AtomicBoolean hasRow = new AtomicBoolean(false);

        RacesPlugin.getDatabase().preparedStatementBuilder("SELECT COUNT(uuid) FROM rpdnd_races WHERE uuid = '" + uuid + "'").executeQuery(resultSet -> {
            try {
                if (resultSet.getInt(1) != 0) {
                    hasRow.set(true);
                }
            } catch (SQLException ignored) {
            }
        }, true);

        return hasRow.get();
    }

    public static void addRow(UUID uuid) {
        RacesPlugin.getDatabase().preparedStatementBuilder("insert into rpdnd_races (uuid, current_race) values ('" + uuid + "', null);").executeUpdate();
    }
}
