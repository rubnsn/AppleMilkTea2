package mods.defeatedcrow.common.block.appliance;

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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.common.registry.ModBlockEntities;

/**
 * WT-A 1.20.1 mojmap migration for BlockTeaMakerNext.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockTeaMakerNext extends Block implements EntityBlock {

    public BlockTeaMakerNext(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // 1.20.1: master TeaMakerBoundingBox 0.1875,0,0.1875 - 0.8125,1,0.8125
    private static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 16, 13);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
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
        return new mods.defeatedcrow.common.tile.appliance.TileMakerNext(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.appliance;
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
     * import net/minecraft/init/Blocks;
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.events.TeamakerRightClickEvent;
     * import mods.defeatedcrow.api.recipe.ITeaRecipe;
     * import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
     * import mods.defeatedcrow.plugin.IC2.LoadIC2Plugin;
     * 
     * public class BlockTeaMakerNext extends Block {
     * 
     *     public BlockTeaMakerNext() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         Random rand = new Random();
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         TileMakerNext tile = (TileMakerNext) par1World.getTileEntity(par2, par3, par4);
     *         if (tile == null) return false;
     * 
     *         ItemStack tileItem = tile.getItemStack();// tileが保持しているアイテム
     *         int remain = tile.getRemain();// 残量
     *         ITeaRecipe tileRecipe = null;
     *         if (tileItem != null) RecipeRegisterManager.teaRecipe.getRecipe(tileItem);
     * 
     *         TeamakerRightClickEvent event = new TeamakerRightClickEvent(
     *             par5EntityPlayer,
     *             par2,
     *             par3,
     *             par4,
     *             tile,
     *             remain,
     *             tileRecipe);
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.isCanceled()) {
     *             return true;
     *         }
     * 
     *         ITeaRecipe recipe = null;// itemのほうのレシピ
     *         if (itemstack != null) recipe = RecipeRegisterManager.teaRecipe.getRecipe(itemstack);
     * 
     *         if (itemstack == null) {
     *             AMTLogger.debugInfo("Checking tile... ");
     *             if (tileItem != null) AMTLogger.debugInfo("tile hold item: " + tileItem.getHoverName());
     *             if (tile.getOutput() != null) AMTLogger.debugInfo(
     *                 "tile hold recipe: " + tile.getOutput()
     *                     .getHoverName());
     *             if (tile.getMilked()) AMTLogger.debugInfo("milk recipe");
     *             AMTLogger.debugInfo("tile remaining: " + tile.getRemain());
     *             AMTLogger.debugInfo("tile texture: " + tile.getCurrentTexture());
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/appliance/BlockTeaMakerNext.java")
     */

        @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type == ModBlockEntities.TILE_MAKER_NEXT.get()) {
            return (lvl, pos, st, be) -> TileMakerNext.tick(lvl, pos, st, (TileMakerNext)be);
        }
        return null;
    }
}
