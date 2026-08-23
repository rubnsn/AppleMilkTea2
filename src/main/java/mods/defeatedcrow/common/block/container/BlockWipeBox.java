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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * WT-A 1.20.1 mojmap migration for BlockWipeBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockWipeBox extends Block implements EntityBlock {

    public BlockWipeBox(BlockBehaviour.Properties properties) {
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        // TODO: return new Tile* (pos, state) — requires WT-B BlockEntityType registration
        return null;
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
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Items;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.src.*;
     * import net.minecraft.tileentity.TileEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.*;
     * import mods.defeatedcrow.common.tile.TileWipeBox;
     * import mods.defeatedcrow.common.tile.TileWipeBox2;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockWipeBox extends Block {
     * 
     *     private static final String[] boxType = new String[] { "_B", "_T", "_S1", "_S2", "_C", "_C" };
     * 
     *     
     *     private BlockTexture[] KimTex;
     *     
     *     private BlockTexture boxTex;
     * 
     *     public BlockWipeBox() {
     *         super(Material.cloth);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *         this.setStepSound(Block.soundTypeCloth);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
     *         TileWipeBox tile = (TileWipeBox) par1World.getTileEntity(par2, par3, par4);
     * 
     *         if (itemstack == null) {
     *             if (tile != null) {
     *                 if (currentMeta == 0) {
     *                     par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
     *                     tile.setRemainByte((byte) 8);
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                 } else if (currentMeta == 1) {
     *                     par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
     *                     tile.setRemainByte((byte) 80);
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                 } else {
     *                     byte remain = tile.getRemainByte();
     * 
     *                     if (remain < 1) {
     *                         if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
     *                             if (!par1World.isRemote)
     *                                 par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
     *                         }
     *                         par1World.setBlockToAir(par2, par3, par4);
     *                         par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                     } else if (remain > 0) {
     *                         if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
     *                             if (!par1World.isRemote) par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1), 1);
     *                         }
     *                         par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                         tile.setRemainByte((byte) (remain - 1));
     *                     }
     *                 }
     * 
     *             } else {
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockWipeBox.java")
     */
}
