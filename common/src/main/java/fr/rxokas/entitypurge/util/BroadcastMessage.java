package fr.rxokas.entitypurge.util;

import fr.rxokas.entitypurge.Entitypurge;
import net.minecraft.server.MinecraftServer;

public class BroadcastMessage {

    public static void broadcastToAllPlayers(MinecraftServer server, String message) {
        if (server != null && server.isRunning()) {
            Command.execute(server, String.format("/tellraw @a {\"text\":\"%s \"}",message));
        } else {
            Entitypurge.LOGGER.warn("Couldn't broadcast a message to all players: Server is not running or not available.");
        }
    }
}
