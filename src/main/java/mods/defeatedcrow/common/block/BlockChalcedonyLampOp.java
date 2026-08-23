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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockChalcedonyLampOp.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockChalcedonyLampOp extends Block {

    public BlockChalcedonyLampOp(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block;
     * 
     * import static net.minecraftforge.common.util.ForgeDirection.UP;
     * 
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class BlockChalcedonyLampOp extends Block {
     * 
     *     
     *     private BlockTexture[] color;
     * 
     *     private String[] name = { "", "_orange", "_white", "_black" };
     * 
     *     public BlockChalcedonyLampOp(Material material, boolean flag) {
     *         super(Material.glass);
     *         this.setHardness(0.5F);
     *         this.setResistance(1.0F);
     *         this.setStepSound(Block.soundTypeGlass);
     *         this.setLightLevel(1.0F);
     *     }
     * 
     *     @Override
     *     public int getRenderType() {
     *         return DCsAppleMilk.modelCLampOp;
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     public boolean isOpaqueCube() {
     *         return true;
     *     }
     * 
     *     @Override
     *     public boolean renderAsNormalBlock() {
     *         return false;
     *     }
     * 
     *     @Override
     *     public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
     *         return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
     *     }
     * 
     *     @Override
     *     public int getMobilityFlag() {
     *         return 0;
     *     }
     * 
     *     @Override
     *     public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
     *         return side != UP;
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int type = par2 & 3;
     *         return this.color[type];
     *     }
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockChalcedonyLampOp.java")
     */
}
