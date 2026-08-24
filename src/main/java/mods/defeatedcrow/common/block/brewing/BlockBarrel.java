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
 * WT-A 1.20.1 mojmap migration for BlockBarrel.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockBarrel extends Block implements EntityBlock {

    public BlockBarrel(BlockBehaviour.Properties properties) {
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
        return new mods.defeatedcrow.common.tile.TileBrewingBarrel(pos, state);
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
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.ChatComponentText;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraft.world.level.material.Fluid;
     * import net.minecraft.world.level.material.FluidHandlerHelper;
     * import net.minecraftforge.fluids.FluidStack;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.TileBrewingBarrel;
     * import mods.defeatedcrow.handler.Util;
     * import mods.defeatedcrow.recipe.BrewingRecipe;
     * 
     * /*
     *  * 基本仕様はCordialの流用で、但し外見から熟成段階が見えない。
     *  * Cordialとの違いは、樽のままではレシピに使用できず、いったん瓶に移さないとならない点である。
     *  * /
     * public class BlockBarrel extends Block {
     * 
     *     public BlockBarrel() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.3F);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
     *         int meta = par1World.getBlockMetadata(par2, par3, par4);
     *         TileBrewingBarrel tile = (TileBrewingBarrel) par1World.getTileEntity(par2, par3, par4);
     * 
     *         if (tile == null) {
     *             return true;
     *         }
     * 
     *         if (Util.notEmptyItem(item) && tile.getAged()) {
     *             if (tile.productTank.isEmpty()) {
     *                 tile.setAged(false);
     *                 tile.setAgingStage(0);
     *                 return true;
     *             }
     * 
     *             FluidStack fluid = tile.productTank.getFluid()
     *                 .copy();
     *             int drainAmount = 0;
     *             ItemStack ret = null;
     * 
     *             if (item.getItem() == Items.bucket && fluid.amount > 1000)// バケツ
     *             {
     *                 ret = FluidHandlerHelper
     *                     .fillFluidContainer(new FluidStack(fluid.getFluid(), 1000), new ItemStack(Items.bucket));
     *                 if (Util.notEmptyItem(ret)) drainAmount = 1000;
     *             } else if (item.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyBottle) && fluid.amount > 200)// ビン
     *             {
     *                 ret = FluidHandlerHelper
     *                     .fillFluidContainer(new FluidStack(fluid.getFluid(), 200), new ItemStack(DCsAppleMilk.emptyBottle));
     *                 if (Util.notEmptyItem(ret)) drainAmount = 200;
     *             } else {
     *                 ret = FluidHandlerHelper.fillFluidContainer(
     *                     new FluidStack(fluid.getFluid(), 1000),
     *                     new ItemStack(item.getItem(), 1, item.getDamageValue()));
     *                 if (Util.notEmptyItem(ret)) drainAmount = 1000;
     *             }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/brewing/BlockBarrel.java")
     */
}
