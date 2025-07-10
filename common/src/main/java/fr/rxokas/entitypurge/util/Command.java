package fr.rxokas.entitypurge.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;

public class Command {
    public static void execute(MinecraftServer server, String command) {
        if (server != null && server.isRunning()) {
            CommandSourceStack source = server.createCommandSourceStack()
                    .withPermission(4)
                    .withSuppressedOutput();
            
            server.getCommands().performPrefixedCommand(source, command);
        } else {
            System.err.println("Server is not running or not available.");
        }
    }
}
