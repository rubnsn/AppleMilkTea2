package mods.defeatedcrow.common.block.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * WT-A 1.20.1 mojmap migration for BlockVegiBag.
 * V1 Variant: EnumProperty VEGI_TYPE 10種 + 側面差分を上/側/底で忠実再現。
 * 1.7.10: bagVegi 10 _leaves.._sugar、wheatBagTop[i]が上、wheatBagSideが側、bottomは bag_wheat_b。
 * 1.20.1: vegi_type 10 variants、上のみ可変、側/底は共通で cube 上下面差分を再現。
 */
public class BlockVegiBag extends Block implements EntityBlock {

    public enum VegiType implements StringRepresentable {
        LEAVES("leaves"), POTATO("potato"), CARROT("carrot"), PUMPKIN("pumpkin"), SEED("seed"),
        REED("reed"), CACTUS("cactus"), COCOA("cocoa"), WART("wart"), SUGAR("sugar");
        private final String name; VegiType(String n){this.name=n;}
        @Override public String getSerializedName(){return name;}
    }
    public static final EnumProperty<VegiType> VEGI_TYPE = EnumProperty.create("vegi_type", VegiType.class);

    public BlockVegiBag(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(VEGI_TYPE, VegiType.LEAVES));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(VEGI_TYPE); }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx){
        ItemStack stack = ctx.getItemInHand();
        if (stack.hasTag() && stack.getTag()!=null && stack.getTag().contains("BlockStateTag")){
            var tag = stack.getTag().getCompound("BlockStateTag");
            if (tag.contains("vegi_type")){
                String s = tag.getString("vegi_type");
                for (VegiType t: VegiType.values()) if (t.getSerializedName().equals(s)) return this.defaultBlockState().setValue(VEGI_TYPE, t);
            }
        }
        return this.defaultBlockState();
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
        return new mods.defeatedcrow.common.tile.TileVegiBag(pos, state);
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
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.tile.TileVegiBag;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockVegiBag extends Block {
     * 
     *     private static final String[] bagVegi = new String[] { "_leaves", "_potato", "_carrot", "_pumpkin", "_seed",
     *         "_reed", "_cactus", "_cocoa", "_wart", "_sugar" };
     *     public static final String[] bagTexType = new String[] { "LeavesBag_T", "PotatoBag_T", "CarrotBag_T",
     *         "PumpkinBag_T", "SeedBag_T", "ReedBag_T", "CactusBag_T", "CocoaBag_T", "WartBag_T", "SugarBag_T" };
     * 
     *     
     *     private BlockTexture[] wheatBagTop;
     *     
     *     private BlockTexture wheatBagSide;
     * 
     *     public BlockVegiBag() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.1F);
     *         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int i = par2;
     *         if (i > 9) i = 9;
     *         if (par1 == 1) {
     *             return this.wheatBagTop[i];
     *         } else if (par1 == 0) {
     *             return this.blockIcon;
     *         } else {
     *             return this.wheatBagSide;
     *         }
     * 
     *     }
     * 
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     
     *     public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(par1, 1, 0));
     *         par3List.add(new ItemStack(par1, 1, 1));
     *         par3List.add(new ItemStack(par1, 1, 2));
     *         par3List.add(new ItemStack(par1, 1, 3));
     *         par3List.add(new ItemStack(par1, 1, 4));
     *         par3List.add(new ItemStack(par1, 1, 5));
     *         par3List.add(new ItemStack(par1, 1, 6));
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockVegiBag.java")
     */
}
