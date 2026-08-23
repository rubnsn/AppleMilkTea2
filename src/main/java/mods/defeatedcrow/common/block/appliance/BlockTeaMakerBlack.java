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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockTeaMakerBlack.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockTeaMakerBlack extends Block {

    public BlockTeaMakerBlack(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.appliance;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.boss.EntityDragon;
     * import net.minecraft.entity.boss.EntityWither;
     * import net.minecraft.world.IBlockAccess;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class BlockTeaMakerBlack extends BlockTeaMakerNext {
     * 
     *     public BlockTeaMakerBlack() {
     *         super();
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setHardness(1.0F);
     *         this.setResistance(6000000.0F);
     *         this.setHarvestLevel("pickaxe", 1);
     *     }
     * 
     *     @Override
     *     public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
     *         if ((entity instanceof EntityWither) || (entity instanceof EntityDragon)) {
     *             return this == DCsAppleMilk.teaMakerBlack ? false : super.canEntityDestroy(world, x, y, z, entity);
     *         }
     * 
     *         return super.canEntityDestroy(world, x, y, z, entity);
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.blockIcon = par1IconRegister.registerIcon("obsidian");
     *     }
     * 
     * }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/appliance/BlockTeaMakerBlack.java")
     */
}
