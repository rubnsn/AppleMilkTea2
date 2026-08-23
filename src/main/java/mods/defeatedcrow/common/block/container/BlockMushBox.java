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
 * WT-A 1.20.1 mojmap migration for BlockMushBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockMushBox extends Block implements EntityBlock {

    public BlockMushBox(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class BlockMushBox extends Block {
     * 
     *     
     *     private BlockTexture itemIcon;
     *     
     *     private BlockTexture boxIcon;
     * 
     *     public BlockMushBox() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeStone);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         int meta = par1World.getBlockMetadata(par2, par3, par4);
     * 
     *         if (itemstack == null) {
     *             ItemStack ret = new ItemStack(this, 1, meta);
     *             if (!par1World.isRemote) {
     *                 EntityItem entity = new EntityItem(
     *                     par1World,
     *                     par5EntityPlayer.posX,
     *                     par5EntityPlayer.posY,
     *                     par5EntityPlayer.posZ,
     *                     ret);
     *                 par1World.spawnEntityInWorld(entity);
     *             }
     * 
     *             par1World.setBlockToAir(par2, par3, par4);
     *             par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
     *             return true;
     *         } else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.mushroomBox)) {
     *             ItemStack ret = new ItemStack(this, 1, meta);
     *             if (!par1World.isRemote) {
     *                 EntityItem entity = new EntityItem(
     *                     par1World,
     *                     par5EntityPlayer.posX,
     *                     par5EntityPlayer.posY,
     *                     par5EntityPlayer.posZ,
     *                     ret);
     *                 par1World.spawnEntityInWorld(entity);
     *             }
     * 
     *             par1World.setBlockToAir(par2, par3, par4);
     *             par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
     *             return true;
     *         } else {
     *             return false;
     *         }
     *     }
     * 
     *     @Override
     *     public boolean isOpaqueCube() {
     *         return false;
     *     }
     * 
     *     @Override
     *     public boolean renderAsNormalBlock() {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockMushBox.java")
     */
}
