package fr.rxokas.entitypurge.neoforge;

import eu.midnightdust.lib.config.MidnightConfig;
import fr.rxokas.entitypurge.Entitypurge;
import net.neoforged.fml.common.Mod;

import static fr.rxokas.entitypurge.Entitypurge.MOD_ID;

@Mod(Entitypurge.MOD_ID)
public final class EntitypurgeNeoForge {
    public EntitypurgeNeoForge() {
        // Run our common setup.
        TickHandler.init();

        MidnightConfig.init(MOD_ID, ModConfig.class);
    }
}
