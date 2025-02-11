package fr.rxokas.entitypurge.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;

public class Command {
    public static void execute(MinecraftServer server, String command) {
        if (server != null && server.isRunning()) {
            // Crée un CommandSourceStack avec des permissions élevées
            CommandSourceStack source = server.createCommandSourceStack()
                    .withPermission(4) // Niveau de permission élevé (4 = op level 4)
                    .withSuppressedOutput(); // Désactive les notifications


            // Exécute la commande
            server.getCommands().performPrefixedCommand(source, command);
        } else {
            System.err.println("Server is not running or not available.");
        }
    }
}
