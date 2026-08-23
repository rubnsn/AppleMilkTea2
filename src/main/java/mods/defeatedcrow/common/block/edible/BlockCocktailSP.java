package mods.defeatedcrow.common.block.edible;

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
 * WT-A 1.20.1 mojmap migration for BlockCocktailSP.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockCocktailSP extends Block implements EntityBlock {

    public BlockCocktailSP(BlockBehaviour.Properties properties) {
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
        // TODO: return new Tile* (pos, state) — requires WT-B BlockEntityType registration
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.edible;
     * 
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.tileentity.TileEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfigCocktail;
     * import mods.defeatedcrow.common.tile.TileCocktailSP;
     * 
     * public class BlockCocktailSP extends Block {
     * 
     *     
     *     private BlockTexture boxTex;
     *     
     *     private BlockTexture contentsTex;
     * 
     *     public BlockCocktailSP() {
     *         super(Material.glass);
     *         this.setStepSound(Block.soundTypeGlass);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *         this.setLightLevel(0.4F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
     *         Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);
     * 
     *         AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(
     *             par1World,
     *             par5EntityPlayer,
     *             itemstack,
     *             par2,
     *             par3,
     *             par4);
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.isCanceled()) {
     *             return true;
     *         }
     * 
     *         if (itemstack == null) {
     *             ItemStack ret = new ItemStack(this, 1, currentMeta);
     *             if (!par1World.isRemote) {
     *                 EntityItem entity = new EntityItem(
     *                     par1World,
     *                     par5EntityPlayer.posX,
     *                     par5EntityPlayer.posY,
     *                     par5EntityPlayer.posZ,
     *                     ret);
     *                 par1World.spawnEntityInWorld(entity);
     *             }
     * 
     *             par1World.setBlockToAir(par2, par3, par4);
     *             par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
     *             return true;
     *         } else if (itemstack.getItem() == Item.getItemFromBlock(this)) {
     *             ItemStack ret = new ItemStack(this, 1, currentMeta);
     *             if (!par1World.isRemote) {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/BlockCocktailSP.java")
     */
}
