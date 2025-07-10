package fr.rxokas.entitypurge.neoforge;

import eu.midnightdust.lib.config.MidnightConfig;
import fr.rxokas.entitypurge.Entitypurge;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import static fr.rxokas.entitypurge.Entitypurge.MOD_ID;

@Mod(Entitypurge.MOD_ID)
public final class EntitypurgeNeoForge {
    public EntitypurgeNeoForge() {
        // Run our common setup.
        TickHandler.init();

        MidnightConfig.init(MOD_ID, ModConfig.class);
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDropsEvent e) {
        if (e.getEntity() instanceof ServerPlayer) {
            for (ItemEntity i : e.getDrops()) {
                i.addTag("playerItem");
            }
        }
    }
}
