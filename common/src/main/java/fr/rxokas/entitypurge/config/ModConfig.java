package fr.rxokas.entitypurge.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    public static final String ITEMS = "items";
    @Entry(category = ITEMS) public static boolean clearItem = true;
    @Entry(category = ITEMS) public static int minutesBetweenItemPurge = 1;

    public static final String WARN = "warn";
    @Entry(category = WARN) public static boolean warning10s = true;
    @Entry(category = WARN) public static String warning10sMessage = "§cCaution, the clear lag will append in 10 seconds!";
}
