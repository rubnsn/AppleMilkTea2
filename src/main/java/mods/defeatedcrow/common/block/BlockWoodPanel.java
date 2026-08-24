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
 * WT-A 1.20.1 mojmap migration for BlockWoodPanel.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockWoodPanel extends Block {

    public BlockWoodPanel(BlockBehaviour.Properties properties) {
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

    // 1.20.1 use: no inventory - decorative, pass through to item
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
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
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * /*
     *  * meta 0-3 向き情報
     *  * /
     * public class BlockWoodPanel extends Block {
     * 
     *     
     *     private BlockTexture[] cover;
     *     
     *     private BlockTexture inner;
     * 
     *     public BlockWoodPanel() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     public int damageDropped(int par1) {
     *         return 0;
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
     *     @Override
     *     public int getRenderType() {
     *         return DCsAppleMilk.modelWoodPanel;
     *     }
     * 
     *     public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
     *         this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
     *         return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
     *     }
     * 
     *     
     *     public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
     *         this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
     *         return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
     *     }
     * 
     *     public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
     *         this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
     *     }
     * 
     *     public void thisBoundingBox(int par1) {
     *         int meta = par1 & 3;
     *         int dirMeta = meta + 2;
     *         ForgeDirection dir = ForgeDirection.getOrientation(dirMeta);
     *         float f = 0.5F;
     * 
     *         if (dir == NORTH) this.setBlockBounds(0.0F, 0.0F, f, 1.0F, 1.0F, 1.0F);
     *         else if (dir == SOUTH) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
     *         else if (dir == WEST) this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
     *         else if (dir == EAST) this.setBlockBounds(f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
     *     }
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockWoodPanel.java")
     */
}
