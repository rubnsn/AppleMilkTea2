package mods.defeatedcrow.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockYuzuFence.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockYuzuFence extends Block {

    public BlockYuzuFence(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // 1.20.1: VoxelShape replaces AxisAlignedBB / setBlockBounds / getSelectedBoundingBox
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.block(); // TODO: restore original bounds via Block.box() per meta/state
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return getShape(state, level, pos, ctx);
    }

    // 1.7.10 onBlockActivated -> 1.20.1 use (BlockPos + BlockHitResult)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // TODO: restore original onBlockActivated logic
        // Original used: world.getBlockMetadata(x,y,z), player.inventory, MinecraftForge.EVENT_BUS.post(AMTBlockRightClickEvent)
        // Migration: use state, level.getBlockEntity(pos), player.getItemInHand(hand), Component
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block;
     * 
     * import static net.minecraftforge.common.util.ForgeDirection.*;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLiving;
     * import net.minecraft.entity.passive.EntityHorse;
     * import net.minecraft.entity.passive.EntityTameable;
     * import net.minecraft.entity.passive.EntityVillager;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.DamageSource;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockYuzuFence extends Block {
     * 
     *     public BlockYuzuFence() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.2F);
     *         this.setResistance(3.0F);
     *     }
     * 
     *     public boolean isOpaqueCube() {
     *         return false;
     *     }
     * 
     *     public boolean renderAsNormalBlock() {
     *         return false;
     *     }
     * 
     *     public int damageDropped(int par1) {
     *         return 0;
     *     }
     * 
     *     @Override
     *     public int getRenderType() {
     *         return DCsAppleMilk.modelYuzuFence;
     *     }
     * 
     *     // 当たり判定の設定
     * 
     *     public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list,
     *         Entity entity) {
     *         // 隣接チェック
     *         boolean north = this.canConnectBlock(world, x, y, z - 1, NORTH);
     *         boolean south = this.canConnectBlock(world, x, y, z + 1, SOUTH);
     *         boolean west = this.canConnectBlock(world, x - 1, y, z, WEST);
     *         boolean east = this.canConnectBlock(world, x + 1, y, z, EAST);
     *         boolean up = world.isAirBlock(x, y + 1, z);
     *         float f1 = 0.375F;
     *         float f2 = 0.625F;
     * 
     *         // 中央の柱と柱上部は常に当たり判定がある
     *         AxisAlignedBB aabb1 = AxisAlignedBB.getBoundingBox(
     *             (double) (x + f1),
     *             (double) y,
     *             (double) (z + f1),
     *             (double) (x + f2),
     *             (double) (y + 1.2),
     *             (double) (z + f2));
     *         if (aabb1 != null && aabb1.intersectsWith(aabb)) {
     *             list.add(aabb1);
     *         }
     * 
     *         if (up) {
     *             AxisAlignedBB aabbU = AxisAlignedBB.getBoundingBox(
     *                 (double) (x + 0.25F),
     *                 (double) (y + 1.4),
     *                 (double) (z + 0.25F),
     *                 (double) (x + 0.75F),
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockYuzuFence.java")
     */
}
