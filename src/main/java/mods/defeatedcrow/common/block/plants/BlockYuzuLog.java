package mods.defeatedcrow.common.block.plants;

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
 * WT-A 1.20.1 mojmap migration for BlockYuzuLog.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockYuzuLog extends Block {

    public BlockYuzuLog(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.plants;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.block.BlockRotatedPillar;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockYuzuLog extends BlockRotatedPillar {
     * 
     *     
     *     protected BlockTexture sideIcon;
     *     
     *     protected BlockTexture topIcon;
     * 
     *     public BlockYuzuLog() {
     *         super(Material.wood);
     *         this.setCreativeTab(DCsAppleMilk.applemilk);
     *         this.setHardness(2.0F);
     *         this.setStepSound(soundTypeWood);
     *     }
     * 
     *     public static int func_150165_c(int p_150165_0_) {
     *         return 0;
     *     }
     * 
     *     @Override
     *     public int quantityDropped(Random p_149745_1_) {
     *         return 1;
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     public void breakBlock(World world, int x, int y, int z, Block par5Block, int par6Meta) {
     *         byte b0 = 4;
     *         int i1 = b0 + 1;
     * 
     *         if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
     *             for (int j1 = -b0; j1 <= b0; ++j1) {
     *                 for (int k1 = -b0; k1 <= b0; ++k1) {
     *                     for (int l1 = -b0; l1 <= b0; ++l1) {
     *                         Block block = world.getBlock(x + j1, y + k1, z + l1);
     *                         if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
     *                             block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
     *                         }
     *                     }
     *                 }
     *             }
     *         }
     *     }
     * 
     *     
     *     protected BlockTexture getSideIcon(int p_150163_1_) {
     *         return this.sideIcon;
     *     }
     * 
     *     
     *     protected BlockTexture getTopIcon(int p_150161_1_) {
     *         return this.topIcon;
     *     }
     * 
     *     @Override
     *     public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
     *         return true;
     *     }
     * 
     *     @Override
     *     public boolean isWood(IBlockAccess world, int x, int y, int z) {
     *         return true;
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/plants/BlockYuzuLog.java")
     */
}
