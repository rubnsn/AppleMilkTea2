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

    // 1.20.1 use: container/edible right-click (insert/extract with sound)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        ItemStack held = player.getItemInHand(hand);
        var be = level.getBlockEntity(pos);
        // If tile has WorldlyContainer, delegate to it (e.g., Barrel, Cordial)
        if (be instanceof net.minecraft.world.WorldlyContainer wc) {
            if (held.isEmpty()) {
                for (int i = wc.getContainerSize() - 1; i >= 0; i--) {
                    ItemStack s = wc.getItem(i);
                    if (!s.isEmpty()) {
                        ItemStack copy = s.copy(); copy.setCount(1);
                        if (!player.getInventory().add(copy)) player.drop(copy, false);
                        s.shrink(1);
                        if (s.isEmpty()) wc.setItem(i, net.minecraft.world.item.ItemStack.EMPTY);
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
        // HasRemaining tiles (BowlRack, WipeBox etc.)
        if (be instanceof mods.defeatedcrow.common.tile.TileHasRemaining hr) {
            byte remain = hr.getRemainByte();
            if (held.isEmpty()) {
                if (remain > 0) {
                    // give one item back - try to find matching Item from block's item
                    ItemStack toGive = new ItemStack(level.getBlockState(pos).getBlock().asItem());
                    if (toGive.isEmpty()) toGive = new ItemStack(net.minecraft.world.item.Items.BOWL);
                    toGive.setCount(1);
                    if (!player.getInventory().add(toGive)) player.drop(toGive, false);
                    hr.setRemainByte((byte)(remain - 1));
                    hr.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
                    return InteractionResult.sidedSuccess(false);
                }
            } else {
                if (remain < 7) {
                    hr.setRemainByte((byte)(remain + 1));
                    if (!player.getAbilities().instabuild) held.shrink(1);
                    hr.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
                    return InteractionResult.sidedSuccess(false);
                }
            }
            return InteractionResult.sidedSuccess(false);
        }
        if (be instanceof mods.defeatedcrow.common.tile.TileHasRemain2 hr2) {
            short remain = hr2.getRemainShort();
            if (held.isEmpty()) {
                if (remain > 0) {
                    ItemStack toGive = new ItemStack(level.getBlockState(pos).getBlock().asItem());
                    if (toGive.isEmpty()) toGive = new ItemStack(net.minecraft.world.item.Items.BOWL);
                    toGive.setCount(1);
                    if (!player.getInventory().add(toGive)) player.drop(toGive, false);
                    hr2.setRemainShort((short)(remain - 1));
                    hr2.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
                    return InteractionResult.sidedSuccess(false);
                }
            } else {
                if (remain < 7) {
                    hr2.setRemainShort((short)(remain + 1));
                    if (!player.getAbilities().instabuild) held.shrink(1);
                    hr2.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
                    return InteractionResult.sidedSuccess(false);
                }
            }
            return InteractionResult.sidedSuccess(false);
        }
        // Generic fallback (HasDirection, Dummy, or no tile): just succeed with sound
        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
        return InteractionResult.sidedSuccess(false);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new mods.defeatedcrow.common.tile.TileWipeBox(pos, state);
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
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.src.*;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
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
