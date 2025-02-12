package fr.rxokas.entitypurge.neoforge;

import dev.architectury.event.events.common.TickEvent;
import fr.rxokas.entitypurge.util.Command;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static fr.rxokas.entitypurge.util.BroadcastMessage.broadcastToAllPlayers;

public class TickHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TickHandler.class);
    private static final Accumulator accumulator = new Accumulator(ModConfig.minutesBetweenEachPurge);
    private static final Map<String, EntityType<?>> entityTypeCache = new HashMap<>();

    private static boolean alreadyWarn10s = false;

    public static void init() {
        TickEvent.SERVER_POST.register(TickHandler::onServerTick);
        cacheEntityTypes();
    }

    private static void cacheEntityTypes() {
        for (String entityId : ModConfig.entitiesToClear) {
            ResourceLocation entityResource = ResourceLocation.parse(entityId);
            BuiltInRegistries.ENTITY_TYPE.getOptional(entityResource).ifPresent(entityType ->
                    entityTypeCache.put(entityId, entityType)
            );
        }
    }

    private static void onServerTick(MinecraftServer server) {
        accumulator.add();

        if (accumulator.canProcess()) {
            LOGGER.debug("Starting entity purge...");
            accumulator.reduce();
            alreadyWarn10s = false;

            if (ModConfig.clearItem) Command.execute(server, "kill @e[type=item]");

            if (!ModConfig.entitiesToClear.isEmpty()) {
                for (ServerLevel serverLevel : server.getAllLevels()) {
                    for (String entityId : ModConfig.entitiesToClear) {
                        EntityType<?> entityType = entityTypeCache.get(entityId);
                        if (entityType == null) continue;

                        serverLevel.getEntities(entityType, Entity::isAlive).forEach(entity -> entity.remove(Entity.RemovalReason.DISCARDED));
                    }
                }
            }
            if (ModConfig.warninghappend) broadcastToAllPlayers(server, ModConfig.warninghappendMessage + "§r");

            LOGGER.debug("Entity purge Done");
        } else if (ModConfig.warning10s && accumulator.lessThan10SecRemaining() && !alreadyWarn10s) {
            broadcastToAllPlayers(server, ModConfig.warning10sMessage + "§r");
            alreadyWarn10s = true;
        }
    }

    private static class Accumulator {
        private static final float TEN_SECONDS_IN_TICKS = 200; // 10 seconds in ticks (20 ticks per second)
        float total;
        float threshold;
        private final float tenSecondThreshold;

        public Accumulator(float threshold) {
            this.threshold = threshold * 1200; // Convert minutes to ticks
            this.tenSecondThreshold = this.threshold - TEN_SECONDS_IN_TICKS;
        }

        public boolean canProcess() {
            return total >= threshold;
        }

        public void reduce() {
            total -= threshold;
        }

        public void add() {
            total += 1;
        }

        public boolean lessThan10SecRemaining() {
            return total >= tenSecondThreshold;
        }
    }
}