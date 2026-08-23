package mods.defeatedcrow.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.MathHelper;
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

        if (!world.isClientSide && entity != null && entity instanceof EntityLivingBase && entity instanceof IMob) {
            int x = MathHelper.floor_double(entity.posX);
            int y = MathHelper.floor_double(entity.posY);
            int z = MathHelper.floor_double(entity.posZ);
            if (entity instanceof EntityLiving && ((EntityLiving) entity).hasCustomNameTag()) {
                return;
            }

            int cX = x >> 4;
            int cZ = z >> 4;
            Coord cood = new Coord(cX, cZ, world.provider.dimensionId);
            if (CoordListRegister.isCoodIncluded(cood)) {
                if (entity.ridingEntity != null) {
                    Entity ride = entity.ridingEntity;
                    ride.riddenByEntity = null;
                    entity.ridingEntity = null;
                    ride.setDead();

                }
                if (entity.riddenByEntity != null) {
                    Entity rider = entity.riddenByEntity;
                    rider.ridingEntity = null;
                    entity.riddenByEntity = null;
                    rider.setDead();
                }
                event.setResult(Result.DENY);
            }
        }
    }

}
