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
 * WT-A 1.20.1 mojmap migration for BlockWipeBox2.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockWipeBox2 extends Block implements EntityBlock {

    public BlockWipeBox2(BlockBehaviour.Properties properties) {
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new mods.defeatedcrow.common.tile.TileWipeBox2(pos, state);
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
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.common.tile.TileWipeBox2;
     * 
     * public class BlockWipeBox2 extends Block {
     * 
     *     public BlockWipeBox2() {
     *         super(Material.cloth);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *         this.setStepSound(Block.soundTypeCloth);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         TileWipeBox2 tile = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);
     * 
     *         if (itemstack == null) {
     *             if (tile != null) {
     *                 short remain = tile.getRemainShort();
     * 
     *                 if (remain == -1) {
     *                     if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
     *                         if (!par1World.isRemote) par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
     *                     }
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                 } else if (remain == 0 || remain < -1) {
     *                     if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
     *                         if (!par1World.isRemote) par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
     *                     }
     *                     par1World.setBlockToAir(par2, par3, par4);
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                 } else if (remain > 0) {
     *                     if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
     *                         if (!par1World.isRemote) par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
     *                     }
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
     *                     tile.setRemainShort((short) (remain - 1));
     *                 }
     * 
     *             } else {
     * 
     *             }
     *             return true;
     *         } else if (itemstack.getItem() == Items.paper
     *             || itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.wipeBox)) {
     *                 if (tile != null) {
     *                     int r = tile.getRemainShort();
     *                     int set = 0;
     *                     int m = itemstack.getDamageValue();
     * 
     *                     if (itemstack.getItem() == Items.paper) {
     *                         set = 1;
     *                     } else {
     *                         set = (m == 0) ? 9 : 81;
     *                     }
     * 
     *                     set = r + set;
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockWipeBox2.java")
     */
}
