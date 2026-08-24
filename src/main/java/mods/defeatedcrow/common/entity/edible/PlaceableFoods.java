package mods.defeatedcrow.common.entity.edible;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/**
 * 1.20.1 base for 13 Placeable food entities (edible blocks placed as entities).
 * Simplified from 1.7.10 PlaceableFoods (boat physics removed, kept NBT/synchedData/interact/hurt).
 * Each subclass returns its ItemStack via returnItem().
 */
public abstract class PlaceableFoods extends Entity {

    private static final EntityDataAccessor<Integer> DATA_META = SynchedEntityData.defineId(PlaceableFoods.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_DIR = SynchedEntityData.defineId(PlaceableFoods.class, EntityDataSerializers.INT);

    public PlaceableFoods(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_META, 0);
        this.entityData.define(DATA_DIR, 1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setContainerMeta(tag.getInt("Meta"));
        if (tag.contains("Dir")) this.setForwardDirection(tag.getInt("Dir"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("Meta", this.getItemMetadata());
        tag.putInt("Dir", this.getForwardDirection());
    }

    public int getItemMetadata() {
        return this.entityData.get(DATA_META);
    }

    public void setContainerMeta(int m) {
        this.entityData.set(DATA_META, m);
    }

    public void setForwardDirection(int dir) {
        this.entityData.set(DATA_DIR, dir);
    }

    public int getForwardDirection() {
        return this.entityData.get(DATA_DIR);
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) { return false; }

    @Override
    public boolean canBeCollidedWith() { return !this.isRemoved(); }

    @Override
    public boolean isPickable() { return !this.isRemoved(); }

    @Override
    public boolean isPushable() { return true; }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) return false;
        if (this.level().isClientSide || this.isRemoved()) return true;
        // drop and discard on any damage (left click)
        ItemStack drop = this.returnItem();
        if (drop != null && !drop.isEmpty()) {
            this.spawnAtLocation(drop, 0.2F);
        }
        this.discard();
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        // chopsticks eating omitted for test; right-click picks up or eats via IEdibleItem if held is empty
        // If player is shift+right click with empty hand, try to eat if returnItem is edible
        // For P3, right-click always picks up (drops) and discards
        if (!this.level().isClientSide) {
            ItemStack drop = this.returnItem();
            if (drop != null && !drop.isEmpty()) {
                // if edible, try to handle via IEdibleItem (simplified: just pick up)
                if (!player.getInventory().add(drop)) {
                    player.drop(drop, false);
                }
            }
            this.discard();
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public void tick() {
        super.tick();
        // minimal physics: apply gravity, move, and check hopper below
        if (!this.level().isClientSide) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.98, 0.98, 0.98));
            // hopper below -> drop as item
            BlockPos below = BlockPos.containing(this.getX(), this.getY() - 0.5, this.getZ());
            var be = this.level().getBlockEntity(below);
            if (be instanceof net.minecraft.world.level.block.entity.HopperBlockEntity) {
                ItemStack drop = this.returnItem();
                if (drop != null && !drop.isEmpty()) {
                    this.spawnAtLocation(drop, 0.1F);
                    this.discard();
                }
            }
            if (this.tickCount > 6000) this.discard(); // despawn after 5min
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    protected abstract ItemStack returnItem();

    protected byte particleNumber() { return 0; }
    protected float getScale() { return 1.0F; }
    protected float getSize() { return 1.0F; }
}
