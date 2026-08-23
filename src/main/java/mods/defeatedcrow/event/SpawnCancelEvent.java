package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.handler.Coord;
import mods.defeatedcrow.handler.CoordListRegister;

/**
 * 1.20.1 stub for SpawnCancelEvent — cancels spawns in CoordListRegister chunks.
 * Original used MobSpawnEvent.FinalizeSpawn (old CheckSpawn), level-old, getX()/getY()/getZ(), ridingEntity, Mth.floor_double.
 * 1.20.1: MobSpawnEvent.FinalizeSpawn / PositionCheck, Level, BlockPos, Mth.floor, getVehicle/getPassengers, discard().
 * See doc/events/migration-guide.md:41
 */
public class SpawnCancelEvent {

    @SubscribeEvent
    public void onSpawnFinalize(MobSpawnEvent.FinalizeSpawn event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel().getLevel(); // LevelAccessor -> Level
        if (level.isClientSide || !(entity instanceof LivingEntity le) || !(entity instanceof Enemy)) return;
        if (le.hasCustomName()) return;
        BlockPos pos = entity.blockPosition();
        int cX = pos.getX() >> 4;
        int cZ = pos.getZ() >> 4;
        Coord coord = new Coord(cX, cZ, level.dimension().location().toString().hashCode());
        if (CoordListRegister.isCoodIncluded(coord)) {
            if (entity.getVehicle() != null) {
                Entity ride = entity.getVehicle();
                entity.stopRiding();
                ride.discard();
            }
            if (!entity.getPassengers().isEmpty()) {
                for (Entity rider : entity.getPassengers()) {
                    rider.stopRiding();
                    rider.discard();
                }
            }
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onSpawnPositionCheck(MobSpawnEvent.PositionCheck event) {
        // Fallback for older callers that used CheckSpawn — deny same coords
        Entity entity = event.getEntity();
        Level level = event.getLevel().getLevel();
        if (level.isClientSide || !(entity instanceof Enemy)) return;
        BlockPos pos = event.getEntity().blockPosition();
        Coord coord = new Coord(pos.getX() >> 4, pos.getZ() >> 4, level.dimension().location().toString().hashCode());
        if (CoordListRegister.isCoodIncluded(coord)) {
            event.setResult(Event.Result.DENY);
        }
    }
}
