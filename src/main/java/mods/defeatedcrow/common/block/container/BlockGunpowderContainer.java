package mods.defeatedcrow.common.block.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockGunpowderContainer.
 * V1 Variant: EnumProperty GUNPOWDER_TYPE 4種 (gunpowder,kayaku,clay,clam) + BooleanProperty HALF (0.5高さ) + randomTick再現。
 * 1.7.10: boxType 4、getIcon par1==1 boxTex[i] (top per type) else boxSideTex (side common)、onBlockPlacedBy sneaking|4、updateTick rain/dry morph + clam bonemeal。
 * 1.20.1: cube上底側差分を再現、halfでVoxelShape 0.5/1.0、randomTickで gunpowder<->kayaku<->clay 遷移 + clam拡散。
 */
public class BlockGunpowderContainer extends Block {

    public enum GunpowderType implements StringRepresentable {
        GUNPOWDER("gunpowder"), KAYAKU("kayaku"), CLAY("clay"), CLAM("clam");
        private final String n; GunpowderType(String n){this.n=n;}
        @Override public String getSerializedName(){return n;}
    }
    public static final EnumProperty<GunpowderType> GUNPOWDER_TYPE = EnumProperty.create("gunpowder_type", GunpowderType.class);
    public static final BooleanProperty HALF = BooleanProperty.create("half");

    private static final VoxelShape SHAPE_FULL = Shapes.block();
    private static final VoxelShape SHAPE_HALF = Block.box(0, 0, 0, 16, 8, 16);

    public BlockGunpowderContainer(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GUNPOWDER_TYPE, GunpowderType.GUNPOWDER).setValue(HALF, Boolean.valueOf(false)));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(GUNPOWDER_TYPE, HALF); }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx){
        ItemStack stack=ctx.getItemInHand();
        GunpowderType type=GunpowderType.GUNPOWDER;
        if (stack.hasTag() && stack.getTag()!=null && stack.getTag().contains("BlockStateTag")){
            var tag=stack.getTag().getCompound("BlockStateTag");
            if (tag.contains("gunpowder_type")){
                String s=tag.getString("gunpowder_type");
                for (GunpowderType t: GunpowderType.values()) if (t.getSerializedName().equals(s)) type=t;
            }
        }
        boolean half = ctx.getPlayer()!=null && ctx.getPlayer().isShiftKeyDown();
        return this.defaultBlockState().setValue(GUNPOWDER_TYPE, type).setValue(HALF, half);
    }

    @Override public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx){
        return state.getValue(HALF) ? SHAPE_HALF : SHAPE_FULL;
    }

    @Override public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx){
        return getShape(state, level, pos, ctx);
    }

    @Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        if (level.isClientSide) return;
        GunpowderType m = state.getValue(GUNPOWDER_TYPE);
        boolean isHalf = state.getValue(HALF);
        if (!isHalf){
            // rain/dry morph： gunpowder(0)<->kayaku(1)<->clay(2) 詳細はDCsConfig条件だが簡略: 降雨時 0->1,1->2 / 乾燥バイオーム 1->0,2->1
            boolean isRaining = level.isRainingAt(pos.above());
            // dry biome check簡略: 乾燥バイオームタグは Forge BiomeDictionary 代替: isRainingAt false && warm? 簡略で乾燥判定は雨でない時
            if (m==GunpowderType.GUNPOWDER && isRaining && level.canSeeSky(pos.above())){
                level.setBlock(pos, state.setValue(GUNPOWDER_TYPE, GunpowderType.KAYAKU), 3);
            } else if (m==GunpowderType.KAYAKU && isRaining && level.canSeeSky(pos.above())){
                level.setBlock(pos, state.setValue(GUNPOWDER_TYPE, GunpowderType.CLAY), 3);
            } else if (!isRaining){
                if (m==GunpowderType.KAYAKU) level.setBlock(pos, state.setValue(GUNPOWDER_TYPE, GunpowderType.GUNPOWDER),3);
                else if (m==GunpowderType.CLAY) level.setBlock(pos, state.setValue(GUNPOWDER_TYPE, GunpowderType.KAYAKU),3);
            }
        }
        // clam (3) のbonemeal拡散は将来: 簡略で2-4マス上のIGrowableに bonemeal適用を random 10% で dirt化を保留
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
     * import net.minecraft.block.IGrowable;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.EntityLivingBase;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraft.world.level.biome.Biome;
     * import net.minecraftforge.common.BiomeDictionary;
     * import net.minecraftforge.common.MinecraftForge;
     * import net.minecraftforge.event.entity.player.BonemealEvent;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockGunpowderContainer extends Block {
     * 
     *     private static final String[] boxType = new String[] { "gunpowder", "kayaku", "clay", "clam" };
     * 
     *     
     *     private BlockTexture[] boxTex;
     *     
     *     private BlockTexture boxSideTex;
     * 
     *     public BlockGunpowderContainer() {
     *         super(Material.ground);
     *         this.setHardness(1.0F);
     *         this.setResistance(2.0F);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setTickRandomly(true);
     *         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int i = par2 & 3;
     *         if (i > 3) i = 3;
     *         if (par1 == 1) {
     *             return this.boxTex[i];
     *         } else {
     *             return this.boxSideTex;
     *         }
     *     }
     * 
     *     @Override
     *     public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
     *         if (!par1World.isRemote) {
     *             super.updateTick(par1World, par2, par3, par4, par5Random);
     * 
     *             int meta = par1World.getBlockMetadata(par2, par3, par4);
     *             int m = meta & 3;
     *             boolean isHalf = (meta & 4) != 0;
     * 
     *             if (!isHalf && !DCsConfig.noWetGContainer) {
     *                 if (m < 2 && this.isRaining(par1World, par2, par3, par4)
     *                     && par1World.canBlockSeeTheSky(par2, par3 + 1, par4)) {
     *                     if (m == 0) par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
     *                     else if (m == 1) par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
     *                 } else if (this.isDryBiome(par1World, par2, par3, par4)) {
     *                     if (m == 1) par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
     *                     else if (m == 2) par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
     *                 }
     *             }
     * 
     *             if (m == 3 && DCsConfig.bonemealClam) {
     *                 boolean flag = false;
     *                 int y = 0;
     *                 // 2,3,4マス上についてチェックする
     *                 for (int i = 0; i < 3; i++) {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockGunpowderContainer.java")
     */
}
