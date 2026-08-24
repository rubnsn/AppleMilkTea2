package mods.defeatedcrow.common.block;

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
 * WT-A 1.20.1 mojmap migration for BlockBasket.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockBasket extends Block implements EntityBlock {

    public BlockBasket(BlockBehaviour.Properties properties) {
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
        return new mods.defeatedcrow.common.tile.TileBread(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block;
     * 
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.EntityLivingBase;
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
     * import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.TileBread;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockBasket extends Block {
     * 
     *     
     *     private BlockTexture[] basketTex;
     *     
     *     private BlockTexture[] basketSideTex;
     *     
     *     private BlockTexture[] basketTopTex;
     *     
     *     private BlockTexture[] breadTex;
     * 
     *     public BlockBasket() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.2F);
     *         this.setResistance(1.0F);
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,
     *         float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         int currentMeta = par1World.getBlockMetadata(x, y, z);
     *         Block bottomBlockID = par1World.getBlock(x, y - 1, z);
     * 
     *         AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, x, y, z);
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.isCanceled()) {
     *             return true;
     *         }
     * 
     *         if (itemstack == null) {
     *             if (currentMeta <= 0) {
     *                 return false;
     *             } else {
     *                 if (currentMeta < 6) {
     *                     this.getBowl(par1World, par5EntityPlayer, currentMeta);
     *                     par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta - 1), 3);
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
     *                     return true;
     *                 } else {
     *                     this.getBottle(par1World, par5EntityPlayer, currentMeta);
     *                     if (currentMeta == 6) {
     *                         par1World.setBlockToAir(x, y, z);
     *                     } else {
     *                         par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta - 1), 3);
     *                     }
     *                     par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
     *                     return true;
     *                 }
     *             }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockBasket.java")
     */
}
