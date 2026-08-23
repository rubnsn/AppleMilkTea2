package mods.defeatedcrow.common.block.container;

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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockGunpowderContainer.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockGunpowderContainer extends Block {

    public BlockGunpowderContainer(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.block.Block;
     * import net.minecraft.block.IGrowable;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.EntityLivingBase;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraft.world.biome.BiomeGenBase;
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
