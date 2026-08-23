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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockAppleBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockAppleBox extends Block {

    public BlockAppleBox(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.container;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockAppleBox extends Block {
     * 
     *     
     *     private BlockTexture appleBoxTop;
     *     
     *     private BlockTexture appleBoxSide;
     * 
     *     public BlockAppleBox() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.1F);
     *         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
     *         return par1 == 1 ? this.appleBoxTop
     *             : (par1 == 0 ? this.appleBoxSide : (par1 != 2 && par1 != 4 ? this.blockIcon : this.appleBoxSide));
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox");
     *         this.appleBoxTop = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "applebox");
     *         this.appleBoxSide = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox");
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
     *         int meta = par6ItemStack.getItemDamage();
     *         int next = meta;
     * 
     *         if (par5EntityLivingBase.isSneaking()) {
     *             next = next | 4;
     *         }
     * 
     *         par1World.setBlockMetadataWithNotify(par2, par3, par4, next, 3);
     *     }
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockAppleBox.java")
     */
}
