package fr.rxokas.entitypurge.util;

import fr.rxokas.entitypurge.Entitypurge;
import fr.rxokas.entitypurge.config.ModConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class BroadcastMessage {
    public static void broadcastToAllPlayers(MinecraftServer server, String message) {
        if (server != null && server.isRunning()) {
            Component textComponent = Component.literal(message);

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                player.sendSystemMessage(textComponent);
            }
        } else {
            Entitypurge.LOGGER.warn("Couldn't broadcast a message to all players: Server is not running or not available.");
        }
    }

    public static void warn10sec(MinecraftServer server) {
        broadcastToAllPlayers(server, ModConfig.warning10sMessage+"§r");
    }

    public static void tellHappend(MinecraftServer server) {
        broadcastToAllPlayers(server, ModConfig.warninghappendMessage+"§r");
    }
}
