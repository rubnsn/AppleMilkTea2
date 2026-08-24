package mods.defeatedcrow.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 fix: restore 1.7.10 fence (normal fence, not pane) - thin walls 0.5 thick at 8, height 16, plus bottom slab 6-10,0-2.
 * Original ISBRH: walls at 8,0,0-8,16,16 and 0,0,8-16,16,8 (thin), bottom stone_slab 6-10,0-2,6-10 + per-direction bottom segments.
 * Spike is collision only (22.4-24 when air above), not visual.
 */
public class BlockYuzuFence extends FenceBlock {

    private static final VoxelShape POST_SHAPE = Block.box(6, 0, 6, 10, 16, 10);
    private static final VoxelShape SPIKE_SHAPE = Block.box(4, 22.4, 4, 12, 24, 12);
    // Visual height is 16, but collision was 19.2 - keep collision taller for gameplay (1.2 blocks) to match old, but visual is 16
    private static final VoxelShape COLLISION_POST = Block.box(6, 0, 6, 10, 19.2, 10);
    private static final VoxelShape COLLISION_NORTH = Block.box(6, 0, 0, 10, 19.2, 6);
    private static final VoxelShape COLLISION_SOUTH = Block.box(6, 0, 10, 10, 19.2, 16);
    private static final VoxelShape COLLISION_WEST = Block.box(0, 0, 6, 6, 19.2, 10);
    private static final VoxelShape COLLISION_EAST = Block.box(10, 0, 6, 16, 19.2, 10);

    public BlockYuzuFence(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        // Visual shape is handled by JSON multipart (fence_post/side). Return full for outline - use same as collision without spike for outline
        return getCollisionShape(state, level, pos, ctx);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        boolean north = state.getValue(NORTH);
        boolean south = state.getValue(SOUTH);
        boolean west = state.getValue(WEST);
        boolean east = state.getValue(EAST);
        VoxelShape shape = COLLISION_POST;
        if (north) shape = Shapes.or(shape, COLLISION_NORTH);
        if (south) shape = Shapes.or(shape, COLLISION_SOUTH);
        if (west) shape = Shapes.or(shape, COLLISION_WEST);
        if (east) shape = Shapes.or(shape, COLLISION_EAST);
        if (level instanceof Level l && l.isEmptyBlock(pos.above())) {
            shape = Shapes.or(shape, SPIKE_SHAPE);
        }
        return shape;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !(entity instanceof Player) && !(entity instanceof Villager) && !(entity instanceof AbstractHorse)) {
            entity.hurt(level.damageSources().cactus(), 2.0F);
        }
        super.entityInside(state, level, pos, entity);
    }
}
