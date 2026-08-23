package mods.defeatedcrow.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class EntityKinoko extends Mob {
    public EntityKinoko(EntityType<? extends Mob> type, Level level) { super(type, level); }
    public EntityKinoko(Level level, double x, double y, double z) { super((EntityType)mods.defeatedcrow.common.registry.ModEntities.KINOKO.get(), level); this.setPos(x,y,z); }
    @Override protected void defineSynchedData() { super.defineSynchedData(); }
}
