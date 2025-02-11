package fr.rxokas.entitypurge;

import dev.architectury.event.events.common.TickEvent;
import fr.rxokas.entitypurge.config.ModConfig;
import fr.rxokas.entitypurge.util.Accumulator;
import fr.rxokas.entitypurge.util.BroadcastMessage;
import fr.rxokas.entitypurge.util.Command;
import net.minecraft.server.level.ServerLevel;

public class TickHandler {
    private static Accumulator itemAccumulator = new Accumulator(ModConfig.minutesBetweenItemPurge);
    private static boolean alreadyWarn10s = false;

    public static void init() {
        // Register a server tick event
        TickEvent.SERVER_LEVEL_POST.register(TickHandler::onServerTick);
    }

    private static void onServerTick(ServerLevel level) {
        // Increment the tick counter
        if (ModConfig.clearItem) itemAccumulator.add();
        // Check if 1000 ticks have passed
        if (itemAccumulator.canProcess()) {
            itemAccumulator.reduce();
            alreadyWarn10s = false;
            //Clear.clearEntity(ItemEntity.class,level);
            Command.execute(level.getServer(),"kill @e[type=item]");
            Entitypurge.LOGGER.info("Items cleared");
        } else if (ModConfig.warning10s && itemAccumulator.lessThan10SecRemaining() && !alreadyWarn10s) {
            BroadcastMessage.warn10sec(level.getServer());
            alreadyWarn10s = true;
        }
    }
}
