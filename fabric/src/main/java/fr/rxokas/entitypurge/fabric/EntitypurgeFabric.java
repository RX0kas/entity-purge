package fr.rxokas.entitypurge.fabric;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import static fr.rxokas.entitypurge.Entitypurge.MOD_ID;

public final class EntitypurgeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TickHandler.init();

        MidnightConfig.init(MOD_ID, ModConfig.class);
    }
}
