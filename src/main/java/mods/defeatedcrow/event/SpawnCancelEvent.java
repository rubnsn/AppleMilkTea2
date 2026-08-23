package mods.defeatedcrow.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.util.Mth;
import net.minecraft.world.Level;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import net.minecraftforge.eventbus.api.Event$Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.handler.Coord;
import mods.defeatedcrow.handler.CoordListRegister;

public class SpawnCancelEvent {

    @SubscribeEvent
    public void onSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        Entity entity = event.entity;
        Level world = event.world;

        if (!world.isClientSide && entity != null && entity instanceof LivingEntity && entity instanceof Enemy) {
            int x = Mth.floor_double(entity.posX);
            int y = Mth.floor_double(entity.posY);
            int z = Mth.floor_double(entity.posZ);
            if (entity instanceof LivingEntity && ((LivingEntity) entity).hasCustomNameTag()) {
                return;
            }

            int cX = x >> 4;
            int cZ = z >> 4;
            Coord cood = new Coord(cX, cZ, level.dimension().location().toString().hashCode());
            if (CoordListRegister.isCoodIncluded(cood)) {
                if (entity.ridingEntity != null) {
                    Entity ride = entity.ridingEntity;
                    ride.riddenByEntity = null;
                    entity.ridingEntity = null;
                    ride.discard();

                }
                if (entity.riddenByEntity != null) {
                    Entity rider = entity.riddenByEntity;
                    rider.ridingEntity = null;
                    entity.riddenByEntity = null;
                    rider.discard();
                }
                event.setResult(Result.DENY);
            }
        }
    }

}
