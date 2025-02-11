package fr.rxokas.entitypurge.neoforge;

import fr.rxokas.entitypurge.Entitypurge;
import net.neoforged.fml.common.Mod;

@Mod(Entitypurge.MOD_ID)
public final class EntitypurgeNeoForge {
    public EntitypurgeNeoForge() {
        // Run our common setup.
        Entitypurge.init();
    }
}
