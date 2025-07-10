package fr.rxokas.entitypurge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.HashSet;

public class Clear {
    /**
     *
     * @param entityclass the class of the entity who want to destroy
     * @param level The server level can be given with TickEvent.SERVER_LEVEL_POST.register
     */
    public static void clearEntity(Class<? extends Entity> entityclass, ServerLevel level) {
        if (!level.isClientSide) {
            HashSet<Entity> entities = new HashSet<>();
            for (Entity entity : level.getAllEntities()) {
                if (entityclass.isInstance(entity)) {
                    if (entity instanceof ItemEntity itemEntity) {
                        if (itemEntity.getTags().contains("playerItem"))
                            continue;
                    }
                    entities.add(entity);
                }

            }

            Entitypurge.LOGGER.info(String.format("Found %d entities to remove", entities.size()));
            entities.forEach(Entity::discard);
        } else {
            Entitypurge.LOGGER.warn(String.format("Couldn't clear %s because level is client side", entityclass.getSimpleName()));
        }
    }
}
