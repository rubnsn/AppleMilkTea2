package mods.defeatedcrow.common.block.plants;

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
 * WT-A 1.20.1 mojmap migration for BlockYuzuSapling.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockYuzuSapling extends Block {

    public BlockYuzuSapling(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.plants;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.BlockBush;
     * import net.minecraft.block.IGrowable;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.world.*;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockYuzuSapling extends BlockBush implements IGrowable {
     * 
     *     public BlockYuzuSapling() {
     *         super(Material.plants);
     *         float f = 0.4F;
     *         this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
     *         this.setCreativeTab(DCsAppleMilk.applemilk);
     *     }
     * 
     *     @Override
     *     protected boolean canPlaceBlockOn(Block block) {
     *         return block.getMaterial() == Material.ground;
     *     }
     * 
     *     /**
     *      * Ticks the block if it's been scheduled
     *      * /
     *     public void updateTick(World world, int par2, int par3, int par4, Random rand) {
     *         if (!world.isRemote) {
     *             super.updateTick(world, par2, par3, par4, rand);
     * 
     *             if (world.getBlockLightValue(par2, par3 + 1, par4) >= 9 && rand.nextInt(7) == 0) {
     *                 this.grow(world, par2, par3, par4, rand);
     *             }
     *         }
     *     }
     * 
     *     public void grow(World world, int x, int y, int z, Random rand) {
     *         int l = world.getBlockMetadata(x, y, z);
     * 
     *         if ((l & 8) == 0) {
     *             world.setBlockMetadataWithNotify(x, y, z, l | 8, 3);
     *         } else {
     *             this.growTree(world, x, y, z, rand);
     *         }
     * 
     *     }
     * 
     *     public boolean fertilize(World world, int x, int y, int z) {
     *         int l = world.getBlockMetadata(x, y, z);
     * 
     *         if (!world.isRemote) {
     *             if ((l & 8) == 0) {
     *                 return world.setBlockMetadataWithNotify(x, y, z, l | 8, 3);
     *             } else {
     *                 this.growTree(world, x, y, z, world.rand);
     *                 return true;
     *             }
     *         }
     *         return true;
     *     }
     * 
     *     public void growTree(World world, int x, int y, int z, Random rand) {
     *         if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;
     * 
     *         int l = world.getBlockMetadata(x, y, z);
     *         WorldGenYuzuTrees object = new WorldGenYuzuTrees(true);
     * 
     *         if (!object.generate(world, rand, x, y, z)) {
     *             AMTLogger.debugInfo("Failed to growing this tree.");
     *             world.setBlock(x, y, z, this, l, 4);
     *         }
     *     }
     * 
     *     public boolean growable(World world, int x, int y, int z, int meta) {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/plants/BlockYuzuSapling.java")
     */
}
