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
 * WT-A 1.20.1 mojmap migration for BlockClamSand.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockClamSand extends Block {

    public BlockClamSand(BlockBehaviour.Properties properties) {
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
     * import java.util.ArrayList;
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.entity.player.InventoryPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.inventory.IInventory;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraft.world.biome.BiomeGenBase;
     * import net.minecraftforge.common.BiomeDictionary;
     * import net.minecraftforge.common.BiomeDictionary.Type;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.plants.IRightClickHarvestable;
     * import mods.defeatedcrow.api.plants.PlantsClickEvent;
     * import mods.defeatedcrow.client.particle.EntityOrbFX;
     * import mods.defeatedcrow.client.particle.ParticleTex;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockClamSand extends Block implements IRightClickHarvestable {
     * 
     *     private final int[] sideX = new int[] { 1, -1, 0, 0 };
     *     private final int[] sideZ = new int[] { 0, 0, 1, -1 };
     * 
     *     public BlockClamSand() {
     *         super(Material.ground);
     *         this.setStepSound(Block.soundTypeSand);
     *         this.setHardness(0.5F);
     *         this.setResistance(1.0F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6,
     *         int par7) {
     *         super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
     *     }
     * 
     *     @Override
     *     public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
     *         ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
     * 
     *         if (metadata == 2) {
     *             ret.add(new ItemStack(DCsAppleMilk.princessClam, 1, 0));
     *         } else {
     *             ret.add(new ItemStack(DCsAppleMilk.clam, 1, 0));
     *         }
     * 
     *         return ret;
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int par1, Random par2Random, int par3) {
     *         return Item.getItemFromBlock(Blocks.sand);
     *     }
     * 
     *     @Override
     *     public int quantityDropped(Random par1Random) {
     *         return 1;
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         Block block = par1World.getBlock(par2, par3, par4);
     *         int meta = par1World.getBlockMetadata(par2, par3, par4);
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/plants/BlockClamSand.java")
     */
}
