package mods.defeatedcrow.common.entity.edible;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 1.20.1 stub for PlaceableFoods — base for 13 edible placeable entities (cup/bowl/steak etc.).
 * Original 1.7.10 extended Entity with custom NBT, DataWatcher, isItemEqual, DCsAppleMilk references, etc.
 * 1.20.1: EntityType + SynchedEntityData + ItemStack NBT + BlockPos. Full interaction logic is TODO.
 * This stub retains the inheritance chain so all 13 subclasses compile via ModEntities registry.
 * See doc/entities/migration-guide.md:87
 */
public abstract class PlaceableFoods extends Entity {

    protected int containerMeta = 0;
    protected boolean allowChops = true;

    public PlaceableFoods(EntityType<?> type, Level level) {
        super(type, level);
    }

    // Legacy constructors for 1.7.10 subclass calls — delegate to new type
    @Deprecated
    public PlaceableFoods(Level level) {
        this(getDefaultType(), level);
    }

    @Deprecated
    public PlaceableFoods(Level level, boolean flag, ItemStack item) {
        this(getDefaultType(), level);
        if (item != null) this.containerMeta = item.getDamageValue();
    }

    @Deprecated
    public PlaceableFoods(Level level, boolean flag, ItemStack item, double x, double y, double z) {
        this(getDefaultType(), level);
        if (item != null) this.containerMeta = item.getDamageValue();
        this.moveTo(x, y, z);
    }

    private static EntityType<?> getDefaultType() {
        // Fallback for legacy constructors — registry not yet available at static init, use generic
        try {
            return mods.defeatedcrow.common.registry.ModEntities.PLACEABLE_CUP1.get();
        } catch (Exception e) {
            return null;
        }
    }

    public int getItemMetadata() { return containerMeta; }
    public void setContainerMeta(int meta) { this.containerMeta = meta; }
    public void setAllowChops(boolean b) { this.allowChops = b; }

    protected abstract ItemStack returnItem();
    protected float getScale() { return 1.0F; }
    protected float getSize() { return 0.5F; }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.containerMeta = tag.getInt("ContainerMeta");
        this.allowChops = tag.getBoolean("AllowChops");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("ContainerMeta", containerMeta);
        tag.putBoolean("AllowChops", allowChops);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() { return super.getAddEntityPacket(); }

    @Override
    public void tick() {
        super.tick();
        // TODO: restore placement + hopper + chopsticks logic
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) return false;
        // Simplified: drop item and discard when hurt by player
        if (!this.level().isClientSide && source.getEntity() instanceof Player) {
            ItemStack drop = this.returnItem();
            if (drop != null && !drop.isEmpty()) {
                this.spawnAtLocation(drop, 0.2F);
            }
            this.discard();
            return true;
        }
        return false;
    }

    @Override
    public boolean isPickable() { return true; }
}
