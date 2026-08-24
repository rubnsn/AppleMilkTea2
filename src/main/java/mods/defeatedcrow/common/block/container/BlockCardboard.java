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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * WT-A 1.20.1 mojmap migration for BlockCardboard.
 * V1 Variant: EnumProperty CARDBOARD_TYPE 8種 (_mint.._grape) + BooleanProperty FRONT_NS + 側面差分を上/底/前/側で再現。
 * 1.7.10: bagVegi 8、texTop cardboard_T、texBottom cardboard_B、texFront cardboard_F、texSide[i] cardboard_S_{type}
 *         getIcon par1==1 top, 0 bottom, 2/3 flag?front:side[i], else flag?side:front、flag=par2>7、damageDropped &7
 *         onBlockPlacedBy yaw 0/2 -> meta|8 (flag true = front north/south), 1/3 -> meta (front east/west) + TileCardBoard directionByte 0/1/2/4。
 */
public class BlockCardboard extends Block implements EntityBlock {

    public enum CardboardType implements StringRepresentable {
        MINT("mint"), CASSIS("cassis"), YUZU("yuzu"), CAMELLIA("camellia"), COFFEE("coffee"), BAMBOO("bamboo"), TOMATO("tomato"), GRAPE("grape");
        private final String n; CardboardType(String n){this.n=n;}
        @Override public String getSerializedName(){return n;}
    }
    public static final EnumProperty<CardboardType> CARDBOARD_TYPE = EnumProperty.create("cardboard_type", CardboardType.class);
    public static final BooleanProperty FRONT_NS = BooleanProperty.create("front_ns");

    public BlockCardboard(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CARDBOARD_TYPE, CardboardType.MINT).setValue(FRONT_NS, Boolean.valueOf(true)));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(CARDBOARD_TYPE, FRONT_NS); }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx){
        ItemStack stack = ctx.getItemInHand();
        CardboardType type = CardboardType.MINT;
        if (stack.hasTag() && stack.getTag()!=null && stack.getTag().contains("BlockStateTag")){
            var tag=stack.getTag().getCompound("BlockStateTag");
            if (tag.contains("cardboard_type")){
                String s=tag.getString("cardboard_type");
                for (CardboardType t: CardboardType.values()) if (t.getSerializedName().equals(s)) type=t;
            }
        } else {
            // fallback from ItemStack damage? not needed
        }
        int l = net.minecraft.util.Mth.floor(ctx.getRotation() * 4.0F / 360.0F + 0.5D) & 3;
        boolean frontNs = (l==0 || l==2);
        return this.defaultBlockState().setValue(CARDBOARD_TYPE, type).setValue(FRONT_NS, frontNs);
    }

    // 1.20.1: VoxelShape replaces AxisAlignedBB / setBlockBounds / getSelectedBoundingBox
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.block();
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
        return new mods.defeatedcrow.common.tile.TileCardBoard(pos, state);
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
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.tile.TileCardBoard;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockCardboard extends Block {
     * 
     *     private static final String[] bagVegi = new String[] { "_mint", "_cassis", "_yuzu", "_camellia", "_coffee",
     *         "_bamboo", "_tomato", "_grape" };
     * 
     *     
     *     private BlockTexture texTop;
     *     
     *     private BlockTexture texBottom;
     *     
     *     private BlockTexture texFront;
     *     
     *     private BlockTexture[] texSide;
     * 
     *     public BlockCardboard() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.1F);
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
     *         int i = par2 & 7;
     *         boolean flag = par2 > 7;
     *         if (par1 == 1) {
     *             return this.texTop;
     *         } else if (par1 == 0) {
     *             return this.texBottom;
     *         } else if (par1 == 2 || par1 == 3) {
     *             return flag ? this.texFront : this.texSide[i];
     *         } else {
     *             return flag ? this.texSide[i] : this.texFront;
     *         }
     * 
     *     }
     * 
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1 & 7;
     *     }
     * 
     *     @Override
     *     public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
     *         ItemStack par6ItemStack) {
     *         int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
     *         int meta = par6ItemStack.getDamageValue();
     *         byte facing = 0;
     * 
     *         if (l == 0) {
     *             par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockCardboard.java")
     */
}
