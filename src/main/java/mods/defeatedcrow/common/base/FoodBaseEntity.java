package mods.defeatedcrow.common.base;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
public class FoodBaseEntity extends Entity {
    public FoodBaseEntity(EntityType<?> t, Level l){ super(t,l); }
    @Override protected void defineSynchedData(){}
    @Override protected void readAdditionalSaveData(CompoundTag c){}
    @Override protected void addAdditionalSaveData(CompoundTag c){}
}
