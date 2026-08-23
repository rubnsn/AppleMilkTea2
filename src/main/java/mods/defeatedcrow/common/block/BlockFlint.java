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
 * WT-A 1.20.1 mojmap migration for BlockFlint.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockFlint extends Block {

    public BlockFlint(BlockBehaviour.Properties properties) {
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
     * import static net.minecraftforge.common.util.ForgeDirection.UP;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.BlockBreakable;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.src.*;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.util.ForgeDirection;
     * 
     * public class BlockFlint extends BlockBreakable {
     * 
     *     public BlockFlint(Material material, boolean flag) {
     *         super("stoneSlab", material, flag);
     *         this.setHardness(1.5F);
     *         this.setResistance(2.0F);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setLightLevel(0.0F);
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     
     *     public int getRenderBlockPass() {
     *         return 0;
     *     }
     * 
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
     *     public boolean canPlaceTorchOnTop(World par1World, int par2, int par3, int par4) {
     *         return true;
     *     }
     * 
     *     @Override
     *     
     *     public int getBlockColor() {
     *         return 0x808080;
     *     }
     * 
     *     
     *     public int getRenderColor(int par1) {
     *         return 0x808080;
     *     }
     * 
     *     public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
     *         return 0x808080;
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.blockIcon = Blocks.stone_slab.getBlockTextureFromSide(1);
     *     }
     * 
     *     public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
     *         return side != UP;
     *     }
     * 
     * }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockFlint.java")
     */
}
