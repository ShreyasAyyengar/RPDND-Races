package me.shreyasayyengar.rpdndraces.utils;

import me.shreyasayyengar.rpdndraces.RacesPlugin;

public class Config {

    private static RacesPlugin main;

    public static void init(RacesPlugin main) {
        Config.main = main;
        main.getConfig().options().configuration();
        main.saveDefaultConfig();
    }

    public static String getSQL(String path) {
        return main.getConfig().getString("MySQL." + path);
    }



}
