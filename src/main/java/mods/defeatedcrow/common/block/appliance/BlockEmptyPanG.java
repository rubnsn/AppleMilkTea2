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

/**
 * WT-A 1.20.1 mojmap migration for BlockEmptyPanG.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockEmptyPanG extends Block implements EntityBlock {

    public BlockEmptyPanG(BlockBehaviour.Properties properties) {
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
        // TODO: return new Tile* (pos, state) - requires WT-B BlockEntityType registration
        return null;
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
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
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
     * import mods.defeatedcrow.api.recipe.IFondueSource;
     * import mods.defeatedcrow.api.recipe.IPanRecipe;
     * import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
     * import mods.defeatedcrow.client.particle.EntityDCCloudFX;
     * import mods.defeatedcrow.client.particle.ParticleTex;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
     * import mods.defeatedcrow.common.tile.appliance.TilePanG;
     * import mods.defeatedcrow.handler.Util;
     * import mods.defeatedcrow.plugin.LoadBambooPlugin;
     * 
     * public class BlockEmptyPanG extends Block {
     * 
     *     
     *     private BlockTexture boxTex;
     *     
     *     private BlockTexture chainTex;
     * 
     *     public BlockEmptyPanG() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
     *         TilePanG tile = null;
     *         if (tileentity instanceof TilePanG) {
     *             tile = (TilePanG) tileentity;
     *         } else {
     *             return false;
     *         }
     * 
     *         if (itemstack == null || itemstack.getItem() == Item.getItemFromBlock(this)) {
     *             ItemStack drop = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
     * 
     *             ItemStack input = tile.getItemStack();
     *             byte rem = tile.getRemainByte();
     *             String disp = tile.getDisplayName();
     * 
     *             if (input != null) {
     *                 CompoundTag tag = new CompoundTag();
     * 
     *                 tag.setByte("remain", rem);
     *                 tag.setString("display", disp);
     *                 tag.setTag("input", input.writeToNBT(new CompoundTag()));
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/appliance/BlockEmptyPanG.java")
     */
}
