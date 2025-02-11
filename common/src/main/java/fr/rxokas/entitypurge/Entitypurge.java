package fr.rxokas.entitypurge;

import com.mojang.logging.LogUtils;
import eu.midnightdust.lib.config.MidnightConfig;
import fr.rxokas.entitypurge.config.ModConfig;
import org.slf4j.Logger;

public final class Entitypurge {
    public static final String MOD_ID = "entitypurge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        MidnightConfig.init(MOD_ID, ModConfig.class);
        TickHandler.init();
    }


}
