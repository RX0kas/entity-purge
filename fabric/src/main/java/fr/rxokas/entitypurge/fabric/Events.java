package fr.rxokas.entitypurge.fabric;

import fr.rxokas.entitypurge.Entitypurge;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.item.ItemStack;

public class Events {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource,damageAmount) -> {
            if (entity instanceof ServerPlayer player) {
                for (ItemStack stack : player.getInventory()) {
                    if (!stack.isEmpty()) {
                        try {
                            ItemEntity droppedItem = new ItemEntity(entity.level(), player.getX(), player.getY(), player.getZ(), stack.copy());

                            droppedItem.addTag("playerItem");
                            System.out.println("add tag");
                            entity.level().addFreshEntity(droppedItem);
                        } catch (Exception e) {
                            Entitypurge.LOGGER.error(e.getMessage());
                        }
                    }
                }
                player.getInventory().clearContent();
            }

            return true;
        });
    }
}
