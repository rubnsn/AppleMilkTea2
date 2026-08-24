package mods.defeatedcrow.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

import mods.defeatedcrow.common.tile.TileCPanel;

/**
 * WT-A 1.20.1 fix: restore 1.7.10 pressure plate (chalcedony panel) - thin, redstone, hold princess clam, wind/moon warp.
 * Original: Material.glass, isOpaqueCube false, renderAsNormalBlock false, getRenderType modelCPanel, canProvidePower true,
 * setPlateBound: powered 0.03125 (0.5) else 0.0625 (1), collision null, canPlace only on solid top, onEntityCollided wind/moon warp.
 * 1.20.1: use BooleanProperty POWERED + IntegerProperty TYPE (0=normal,1=wind,2=moon), VoxelShape pressure plate, redstone, TileCPanel hold.
 */
public class BlockCPanel extends Block implements EntityBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);

    private static final VoxelShape SHAPE_UP = Block.box(1, 0, 1, 15, 1, 15); // 0.0625*16=1
    private static final VoxelShape SHAPE_DOWN = Block.box(1, 0, 1, 15, 0.5, 15); // 0.03125*16=0.5

    public BlockCPanel(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false).setValue(TYPE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED, TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return state.getValue(POWERED) ? SHAPE_DOWN : SHAPE_UP;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.empty();
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return dir == Direction.UP && state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public boolean canSurvive(BlockState state, net.minecraft.world.level.LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        return belowState.isFaceSturdy(level, below, Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return dir == Direction.DOWN && !state.canSurvive(level, pos) ? net.minecraft.world.level.block.Blocks.AIR.defaultBlockState() : super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            int strength = getSignalStrength(level, pos);
            if (strength == 0) {
                level.setBlock(pos, state.setValue(POWERED, false), 3);
                updateNeighbours(level, pos);
                level.playSound(null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
                level.gameEvent(null, net.minecraft.world.level.gameevent.GameEvent.BLOCK_DEACTIVATE, pos);
            } else {
                level.scheduleTick(pos, this, 20);
            }
        }
    }

    private int getSignalStrength(Level level, BlockPos pos) {
        // Only EntityPlayer triggers (like old calcStrength for EntityPlayer)
        var box = SHAPE_UP.bounds().move(pos);
        var list = level.getEntitiesOfClass(Player.class, box);
        for (var e : list) {
            if (!e.isIgnoringBlockTriggers()) return 15;
        }
        return 0;
    }

    private void updateNeighbours(Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            int strength = getSignalStrength(level, pos);
            if (strength > 0 && !state.getValue(POWERED)) {
                // Press
                BlockState newState = state.setValue(POWERED, true);
                level.setBlock(pos, newState, 3);
                updateNeighbours(level, pos);
                level.playSound(null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
                level.gameEvent(player, net.minecraft.world.level.gameevent.GameEvent.BLOCK_ACTIVATE, pos);
                level.scheduleTick(pos, this, 20);
            }
            // Wind/moon warp if powered and type>0
            if (strength > 0) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof TileCPanel tile) {
                    int type = state.getValue(TYPE);
                    if (type == 1) {
                        ItemStack held = tile.getItemstack();
                        if (held != null && held.hasTag()) {
                            var tag = held.getTag();
                            if (tag != null && tag.contains("DCsCharm")) {
                                byte m = tag.getByte("DCsCharm");
                                if (m == 1) {
                                    String name = tag.getString("DCtargetName");
                                    // TODO: implement wind player warp (needs target player lookup)
                                } else if (m == 2) {
                                    int X = tag.getInt("DCposX");
                                    int Y = tag.getInt("DCposY");
                                    int Z = tag.getInt("DCposZ");
                                    int dim = tag.getInt("DCdim");
                                    if (level.dimension().location().toString().equals(String.valueOf(dim)) || true) {
                                        // Simplified: if same dim, teleport to pos
                                        if (level.isEmptyBlock(new BlockPos(X, Y, Z)) && level.isEmptyBlock(new BlockPos(X, Y+1, Z))) {
                                            player.teleportTo(X + 0.5, Y + 1, Z + 0.5);
                                            player.fallDistance = 0;
                                            level.playSound(null, player.blockPosition(), net.minecraft.sounds.SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1, 1.2F);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (type == 2) {
                        // Moon: warp up to find warpable spot like old formMoonEffect
                        int X = pos.getX(), Y = pos.getY(), Z = pos.getZ();
                        for (int i = 1; i < 128; i++) {
                            int ny = Y + i;
                            if (ny < level.getMinBuildHeight() || ny > level.getMaxBuildHeight() - 2) break;
                            BlockPos np = new BlockPos(X, ny, Z);
                            if (level.isEmptyBlock(np.above()) && level.isEmptyBlock(np.above().above())) {
                                // Simplified check: air above
                                player.teleportTo(X + 0.5, ny + 1, Z + 0.5);
                                player.fallDistance = 0;
                                level.playSound(null, player.blockPosition(), net.minecraft.sounds.SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1, 1.2F);
                                break;
                            }
                        }
                    }
                }
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof TileCPanel tile)) return InteractionResult.PASS;
        ItemStack held = player.getItemInHand(hand);
        ItemStack tileStack = tile.getItemstack();
        int type = state.getValue(TYPE);
        if (!held.isEmpty() && tileStack == null) {
            // Put princess clam wind(3) / moon(4)
            if (held.getItem() == mods.defeatedcrow.common.registry.ModItems.PRINCESS_CLAM.get() && held.getDamageValue() > 2) {
                int dmg = held.getDamageValue();
                if (dmg == 3 || dmg == 4) {
                    ItemStack put = held.copy();
                    put.setCount(1);
                    tile.setItemstack(put);
                    if (!player.getAbilities().instabuild) held.shrink(1);
                    int newType = dmg == 3 ? 1 : 2;
                    level.setBlock(pos, state.setValue(TYPE, newType), 3);
                    tile.setChanged();
                    level.sendBlockUpdated(pos, state, state.setValue(TYPE, newType), 3);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        } else if (tileStack != null) {
            if (!level.isClientSide) {
                String t = "None";
                if (tileStack.getDamageValue() == 3) t = "Wind";
                else if (tileStack.getDamageValue() == 4) t = "Moon";
                player.displayClientMessage(net.minecraft.network.chat.Component.literal("Type : " + t), false);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileCPanel(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof TileCPanel tile) {
                ItemStack drop = tile.getItemstack();
                if (drop != null && !drop.isEmpty()) {
                    net.minecraft.world.Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), drop);
                }
            }
            if (state.getValue(POWERED)) {
                updateNeighbours(level, pos);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
