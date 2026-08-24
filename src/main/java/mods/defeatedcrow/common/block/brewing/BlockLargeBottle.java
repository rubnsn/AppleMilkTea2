package mods.defeatedcrow.common.block.brewing;

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
 * WT-A 1.20.1 mojmap migration for BlockLargeBottle.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockLargeBottle extends Block implements EntityBlock {

    public BlockLargeBottle(BlockBehaviour.Properties properties) {
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

    // 1.20.1 use: right-click handling (insert/extract with sound)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        var be = level.getBlockEntity(pos);
        if (be instanceof net.minecraft.world.WorldlyContainer wc) {
            ItemStack held = player.getItemInHand(hand);
            if (held.isEmpty()) {
                for (int i = wc.getContainerSize() - 1; i >= 0; i--) {
                    ItemStack s = wc.getItem(i);
                    if (!s.isEmpty()) {
                        ItemStack copy = s.copy(); copy.setCount(1);
                        if (!player.getInventory().add(copy)) player.drop(copy, false);
                        s.shrink(1);
                        if (s.isEmpty()) wc.setItem(i, ItemStack.EMPTY);
                        wc.setChanged();
                        level.sendBlockUpdated(pos, state, state, 3);
                        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                        return InteractionResult.sidedSuccess(false);
                    }
                }
            } else {
                for (int i = 0; i < wc.getContainerSize(); i++) {
                    if (wc.canPlaceItemThroughFace(i, held, hit.getDirection()) || wc.getItem(i).isEmpty()) {
                        ItemStack existing = wc.getItem(i);
                        if (existing.isEmpty()) {
                            ItemStack toPut = held.copy(); toPut.setCount(1);
                            wc.setItem(i, toPut);
                            if (!player.getAbilities().instabuild) held.shrink(1);
                            wc.setChanged();
                            level.sendBlockUpdated(pos, state, state, 3);
                            level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                            return InteractionResult.sidedSuccess(false);
                        } else if (existing.is(held.getItem()) && existing.getCount() < existing.getMaxStackSize()) {
                            existing.grow(1);
                            if (!player.getAbilities().instabuild) held.shrink(1);
                            wc.setChanged();
                            level.sendBlockUpdated(pos, state, state, 3);
                            level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                            return InteractionResult.sidedSuccess(false);
                        }
                    }
                }
            }
            return InteractionResult.sidedSuccess(false);
        }
        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
        return InteractionResult.sidedSuccess(false);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new mods.defeatedcrow.common.tile.TileLargeBottle(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.brewing;
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
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.TileLargeBottle;
     * 
     * /**
     *  * 酒瓶。空カップを持って右クリックすると、ロック・ストレートで頂ける <br>
     *  * キャニスターを別ブロックに分離。
     *  * /
     * public class BlockLargeBottle extends Block {
     * 
     *     private static final String[] contents = new String[] { "_shothu", "_sake", "_beer", "_wine", "_gin", "_rum",
     *         "_vodka", "_whiskey", "_brandy" };
     * 
     *     
     *     private BlockTexture[] boxTex;
     *     
     *     private BlockTexture[] sideTex;
     *     
     *     private BlockTexture[] itemTex;
     * 
     *     public BlockLargeBottle() {
     *         super(Material.circuits);
     *         this.setStepSound(Block.soundTypeGlass);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *     }
     * 
     *     // 回収動作
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
     *         TileLargeBottle tile = (TileLargeBottle) par1World.getTileEntity(par2, par3, par4);
     * 
     *         if (itemstack == null)// 素手では何もしない
     *         {
     *             return false;
     *         } else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyCup))// カップでストレートのお酒を汲む
     *         {
     *             short i = tile.getRemainShort();
     *             int type = currentMeta;
     *             int rem = checkRemain(i);
     * 
     *             boolean flag = false;
     * 
     *             if (i > 0) {
     *                 tile.setRemainShort((short) (i - 1));
     *                 flag = true;
     *             } else {
     * 
     *             }
     * 
     *             if (flag) {
     *                 int meta = 0;
     *                 if (type == 0) meta = 11;
     *                 else if (type == 8) meta = 12;
     *                 else {
     *                     meta = type - 1;
     *                 }
     * 
     *                 if (!par5EntityPlayer.capabilities.isCreativeMode) {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/brewing/BlockLargeBottle.java")
     */
}
