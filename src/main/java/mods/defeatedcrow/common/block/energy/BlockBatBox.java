package mods.defeatedcrow.common.block.energy;

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
 * WT-A 1.20.1 mojmap migration for BlockBatBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockBatBox extends Block implements EntityBlock {

    public BlockBatBox(BlockBehaviour.Properties properties) {
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
        return new mods.defeatedcrow.common.tile.energy.TileChargerDevice(pos, state);
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
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.energy.TileChargerBase;
     * import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
     * 
     * public class BlockBatBox extends Block {
     * 
     *     protected Random rand = new Random();
     * 
     *     public BlockBatBox() {
     *         super(Material.ground);
     *         this.setHardness(2.0F);
     *         this.setResistance(2.0F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     * 
     *         ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
     *         TileChargerBase tile = (TileChargerBase) par1World.getTileEntity(par2, par3, par4);
     *         if (tile != null) {
     *             if (par1World.isRemote) {
     *                 return true;
     *             } else {
     *                 par5EntityPlayer
     *                     .openGui(DCsAppleMilk.instance, DCsAppleMilk.instance.guiBatBox, par1World, par2, par3, par4);
     *                 return true;
     *             }
     *         }
     *         return true;
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
     *     @Override
     *     public int getRenderType() {
     *         return DCsAppleMilk.modelBatBox;
     *     }
     * 
     *     @Override
     *     public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
     *         ItemStack par6ItemStack) {
     *         int playerFacing = MathHelper.floor_double((par5EntityLivingBase.rotationYaw * 4F) / 360F + 0.5D) & 3;
     * 
     *         byte facing = 0;
     *         if (playerFacing == 0) {
     *             facing = 0;
     *         }
     *         if (playerFacing == 1) {
     *             facing = 1;
     *         }
     *         if (playerFacing == 2) {
     *             facing = 2;
     *         }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/energy/BlockBatBox.java")
     */
}
