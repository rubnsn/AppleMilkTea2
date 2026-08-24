package mods.defeatedcrow.common.block.container;

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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockCharcoalBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockCharcoalBox extends Block {

    public BlockCharcoalBox(BlockBehaviour.Properties properties) {
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
        if (level.isClientSide) return InteractionResult.SUCCESS;
        var be = level.getBlockEntity(pos);
        if (be != null) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.container;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockCharcoalBox extends Block implements IFuelHandler {
     * 
     *     
     *     private BlockTexture charcoalBoxTop;
     *     
     *     private BlockTexture charcoalBoxSide;
     * 
     *     public BlockCharcoalBox() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setHardness(1.0F);
     *         this.setResistance(2.0F);
     *         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
     *     }
     * 
     *     @Override
     *     public int getBurnTime(ItemStack fuel) {
     * 
     *         Item i = fuel.getItem();
     *         return i == Item.getItemFromBlock(this) ? 16000 : 0;
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         return par1 == 1 ? this.charcoalBoxTop
     *             : (par1 == 0 ? this.charcoalBoxSide : (par1 != 2 && par1 != 4 ? this.blockIcon : this.charcoalBoxSide));
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1BlockIconRegister) {
     *         this.blockIcon = par1BlockIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
     *         this.charcoalBoxTop = par1BlockIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_charcoal_T");
     *         this.charcoalBoxSide = par1BlockIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
     *     }
     * 
     *     // ハーフブロック化
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
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1 & 3;
     *     }
     * 
     *     @Override
     *     public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
     *         ItemStack par6ItemStack) {
     *         int meta = par6ItemStack.getDamageValue();
     *         int next = meta;
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockCharcoalBox.java")
     */
}
