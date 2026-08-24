package mods.defeatedcrow.common.block.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockYuzuLight.
 * V1 Variant: DirectionProperty FACING 6方向 + 薄型 VoxelShape 0.375-0.625 / 厚さ 0.0625 を忠実再現。
 * 1.7.10: setThisBound(meta&7)で UP 6,15,6→10,16,10 / DOWN 6,0,6→10,1,10 / NORTH 6,6,0→10,10,1 etc、onBlockPlacedで side opposite。
 */
public class BlockYuzuLight extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static final VoxelShape SHAPE_UP = Block.box(6, 15, 6, 10, 16, 10);
    private static final VoxelShape SHAPE_DOWN = Block.box(6, 0, 6, 10, 1, 10);
    private static final VoxelShape SHAPE_NORTH = Block.box(6, 6, 0, 10, 10, 1);
    private static final VoxelShape SHAPE_SOUTH = Block.box(6, 6, 15, 10, 10, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(0, 6, 6, 1, 10, 10);
    private static final VoxelShape SHAPE_EAST = Block.box(15, 6, 6, 16, 10, 10);

    public BlockYuzuLight(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(FACING); }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx){
        Direction dir = ctx.getClickedFace().getOpposite();
        // 1.7.10 onBlockPlaced logic: side opposite -> newMeta 0 DOWN,1 UP,2 NORTH,3 SOUTH,4 WEST,5 EAST
        // Map directly to Direction
        return this.defaultBlockState().setValue(FACING, dir);
    }

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos){
        Direction dir = state.getValue(FACING);
        Direction attach = dir.getOpposite();
        BlockPos nb = pos.relative(attach);
        BlockState ns = level.getBlockState(nb);
        return ns.isFaceSturdy(level, nb, dir);
    }

    @Override public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos){
        return dir == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos) ? net.minecraft.world.level.block.Blocks.AIR.defaultBlockState() : super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    // 1.20.1: VoxelShape replaces AxisAlignedBB / setBlockBounds / getSelectedBoundingBox
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(FACING)) {
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.empty();
    }

    // 1.20.1 use: no inventory - just handle right-click success
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
        return InteractionResult.sidedSuccess(false);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.energy;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.AMTLogger;
     * 
     * /*
     *  * 貼るタイプのライト。
     *  * 赤石ジェルのライト版で、後発だけど機能が少ない。
     *  * 向きはメタデータで格納。0-6:6面分
     *  * /
     * public class BlockYuzuLight extends Block {
     * 
     *     public BlockYuzuLight() {
     *         super(Material.clay);
     *         this.setStepSound(Block.soundTypePiston);
     *         this.setLightLevel(1.0F);
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     public int damageDropped(int p_149692_1_) {
     *         return 0;
     *     }
     * 
     *     /* 以下はレンダー用のメソッド群 * /
     * 
     *     // 当たり判定はなし
     *     @Override
     *     public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
     *         return null;
     *     }
     * 
     *     // 光透過関係
     *     @Override
     *     public boolean isOpaqueCube() {
     *         return false;
     *     }
     * 
     *     @Override
     *     public boolean renderAsNormalBlock() {
     *         return false;
     *     }
     * 
     *     // アイテムアイコン用ボックス
     *     @Override
     *     public void setBlockBoundsForItemRender() {
     *         float f = 0.35F;
     *         float f1 = 0.35F;
     *         float f2 = 0.35F;
     *         this.setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
     *     }
     * 
     *     // 設置時のボックス
     *     @Override
     *     public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_,
     *         int p_149719_4_) {
     *         this.setThisBound(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
     *     }
     * 
     *     // メタデータと向き情報を交換、向きごとのブロックを取得。
     *     // 今回は試験的にForgeDirectionを使用。いずれ他のクラスもForgeDirection使用に切り替えていく。
     *     protected void setThisBound(int meta) {
     *         int m = meta & 7;
     *         float f = 0.0625F;
     *         float f1 = 0.375F;
     *         float f2 = 0.625F;
     *         ForgeDirection dir = ForgeDirection.getOrientation(m);
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/energy/BlockYuzuLight.java")
     */
}
