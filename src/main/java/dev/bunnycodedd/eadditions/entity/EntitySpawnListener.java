package dev.bunnycodedd.eadditions.entity;


import dev.bunnycodedd.eadditions.Config;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySpawnListener {

    @SubscribeEvent
    public static void onEntitySpawning(MobSpawnEvent.FinalizeSpawn event) {
        int levelHeight = event.getLevel().getHeight(Heightmap.Types.WORLD_SURFACE, event.getEntity().blockPosition().getX(), event.getEntity().blockPosition().getZ());
        if(event.getEntity().blockPosition().getY() >= levelHeight - 1
                && event.getSpawnType() != MobSpawnType.SPAWN_EGG
                && event.getEntity() instanceof Monster
                && Config.DISABLE_SURFACE_SPAWNING.get()
        ) {
            event.setSpawnCancelled(true);
        }
    }


}