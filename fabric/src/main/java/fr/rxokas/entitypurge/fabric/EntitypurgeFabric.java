package fr.rxokas.entitypurge.fabric;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import static fr.rxokas.entitypurge.Entitypurge.MOD_ID;

public final class EntitypurgeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TickHandler.init();

        MidnightConfig.init(MOD_ID, ModConfig.class);

        Events.register();
    }
}
