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
 * WT-A 1.20.1 mojmap migration for BlockCassisTree.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockCassisTree extends Block {

    public BlockCassisTree(BlockBehaviour.Properties properties) {
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
     * import static net.minecraftforge.common.EnumPlantType.Plains;
     * 
     * import java.util.ArrayList;
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.entity.player.InventoryPlayer;
     * import net.minecraft.world.Container;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.EnumPlantType;
     * import net.minecraftforge.common.IPlantable;
     * import net.minecraftforge.common.IShearable;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.plants.IRightClickHarvestable;
     * import mods.defeatedcrow.api.plants.PlantsClickEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockCassisTree extends Block implements IShearable, IPlantable, IRightClickHarvestable {
     * 
     *     
     *     private BlockTexture leafBlockTexture;// 内側
     *     
     *     private BlockTexture[] newleafBlockTexture;// 外側
     *     
     *     private BlockTexture logBlockTexture;
     * 
     *     public BlockCassisTree() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeGrass);
     *         this.setTickRandomly(true);
     *         this.setHardness(0.1F);
     *     }
     * 
     *     @Override
     *     public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
     *         if (!par1World.isRemote && par1World.rand.nextInt(8) == 0) {
     *             int meta = par1World.getBlockMetadata(par2, par3, par4);
     *             int growth = meta & 3;
     * 
     *             /* 4段階成長する。成長し切ると実を採取できる。 * /
     *             if (growth < 3 && (par1World.getBlockLightValue(par2, par3, par4) > 11)) {
     *                 par1World.setBlockMetadataWithNotify(par2, par3, par4, (meta + 1), 3);
     *             }
     *         }
     *     }
     * 
     *     public boolean fertilize(World par1World, int par2, int par3, int par4) {
     *         int meta = par1World.getBlockMetadata(par2, par3, par4);
     *         if (meta < 3) {
     *             par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
     *             return true;
     *         } else if (meta > 3 && meta < 7) {
     *             par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 3);
     *             return true;
     *         } else {
     *             return false;
     *         }
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     *         Block block = par1World.getBlock(par2, par3, par4);
     *         int meta = par1World.getBlockMetadata(par2, par3, par4);
     * 
     *         // event
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/plants/BlockCassisTree.java")
     */
}
