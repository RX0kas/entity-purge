package fr.rxokas.entitypurge.neoforge;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class ModConfig extends MidnightConfig {
    public static final String ENTITIES = "entities";
    @Entry(category = ENTITIES) public static boolean clearItem = true;
    @Entry(category = ENTITIES) public static int minutesBetweenEachPurge = 10;
    @Entry(category = ENTITIES) public static List<String> entitiesToClear = new ArrayList<>();


    public static final String BROADCAST = "broadcast";
    @Entry(category = BROADCAST) public static boolean warning10s = true;
    @Entry(category = BROADCAST) public static String warning10sMessage = "§cCaution, the clear lag will append in 10 seconds!";
    @Entry(category = BROADCAST) public static boolean warninghappend = true;
    @Entry(category = BROADCAST) public static String warninghappendMessage = "§2Clear lag done!";

}
