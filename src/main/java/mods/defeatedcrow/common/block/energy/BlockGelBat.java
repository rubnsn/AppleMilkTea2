package mods.defeatedcrow.common.block.energy;

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
 * WT-A 1.20.1 mojmap migration for BlockGelBat.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockGelBat extends Block implements EntityBlock {

    public BlockGelBat(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.energy;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.nbt.NBTTagCompound;
     * import net.minecraft.tileentity.TileEntity;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.energy.TileGelBat;
     * 
     * public class BlockGelBat extends Block {
     * 
     *     
     *     private BlockTexture texTop;
     *     
     *     private BlockTexture texSide;
     * 
     *     protected Random rand = new Random();
     * 
     *     public BlockGelBat() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypePiston);
     *         this.setHardness(1.0F);
     *         this.setResistance(2.0F);
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return null;
     *     }
     * 
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         return par1 == 1 ? this.texTop : this.texSide;
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
     *     public int getRenderType() {
     *         return DCsAppleMilk.modelGelBat;
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1BlockIconRegister) {
     *         this.blockIcon = par1BlockIconRegister.registerIcon("defeatedcrow:porcelain");
     *         this.texTop = par1BlockIconRegister.registerIcon("defeatedcrow:porcelain");
     *         this.texSide = par1BlockIconRegister.registerIcon("defeatedcrow:redgel");
     * 
     *     }
     * 
     *     // 以下は設置、破壊時処理
     *     @Override
     *     public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
     *         ItemStack par6ItemStack) {
     *         int charge = 0;
     * 
     *         if (par6ItemStack.getItem() == Item.getItemFromBlock(this)) {
     *             if (par6ItemStack.hasTagCompound() && par6ItemStack.getTagCompound()
     *                 .hasKey("charge")) {
     *                 NBTTagCompound tag = par6ItemStack.getTagCompound();
     *                 charge = tag.getInteger("charge");
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/energy/BlockGelBat.java")
     */
}
