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
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import mods.defeatedcrow.common.registry.ModBlockEntities;

/**
 * WT-A 1.20.1 mojmap migration for BlockFilledSoupPan.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockFilledSoupPan extends Block implements EntityBlock {

    public BlockFilledSoupPan(BlockBehaviour.Properties properties) {
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
        return new mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan(pos, state);
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
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.ChatComponentText;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.util.StatCollector;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraft.world.level.material.FluidHandlerHelper;
     * import mods.defeatedcrow.api.appliance.SoupType;
     * import mods.defeatedcrow.api.recipe.IFondueRecipe;
     * import mods.defeatedcrow.api.recipe.IFondueSource;
     * import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
     * import mods.defeatedcrow.client.particle.EntityDCCloudFX;
     * import mods.defeatedcrow.client.particle.ParticleTex;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
     * 
     * public class BlockFilledSoupPan extends Block {
     * 
     *     
     *     private BlockTexture[] contentsTex;
     * 
     *     public BlockFilledSoupPan() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6,
     *         float par7, float par8, float par9) {
     *         ItemStack hold = player.inventory.getCurrentItem();
     *         int currentMeta = world.getBlockMetadata(par2, par3, par4);
     *         Block bottomBlock = world.getBlock(par2, par3 - 1, par4);
     *         TileEntity tile = world.getTileEntity(par2, par3, par4);
     *         TileFilledSoupPan pan = null;
     *         if (tile != null && tile instanceof TileFilledSoupPan) {
     *             pan = (TileFilledSoupPan) tile;
     *         } else {
     *             return false;
     *         }
     * 
     *         if (hold == null) {
     *             return false;
     *         } else {
     *             // old recipe api
     *             ItemStack chocolate = RecipeRegisterManager.chocoRecipe.getOutput(hold);
     *             // new recipe
     *             IFondueRecipe food = RecipeRegisterManager.fondueRecipe.getRecipe(hold, pan.getType());
     * 
     *             if (pan.getType() == SoupType.CHOCO && chocolate != null && chocolate.getItem() != null) {
     *                 this.getRecipeFood(world, par2, par3, par4, player, hold, chocolate);
     *                 this.reduceRemain(world, par2, par3, par4, pan);
     *                 return true;
     *             } else if (food != null && food.getOutput() != null && food.getType() == pan.getType()) {
     *                 this.getRecipeFood(world, par2, par3, par4, player, hold, food.getOutput());
     *                 this.reduceRemain(world, par2, par3, par4, pan);
     *                 return true;
     *             } else {
     *                 if (world.isRemote) player.addChatMessage(
     *                     new ChatComponentText(StatCollector.translateToLocal("dc.panMessage.noFondueRecipe")));
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/appliance/BlockFilledSoupPan.java")
     */

        @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type == ModBlockEntities.TILE_FILLED_SOUP_PAN.get()) {
            return (lvl, pos, st, be) -> TileFilledSoupPan.tick(lvl, pos, st, (TileFilledSoupPan)be);
        }
        return null;
    }
}
