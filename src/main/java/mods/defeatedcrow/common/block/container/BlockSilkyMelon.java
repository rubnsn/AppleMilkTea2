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
 * WT-A 1.20.1 mojmap migration for BlockSilkyMelon.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockSilkyMelon extends Block {

    public BlockSilkyMelon(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.entity.EntitySilkyMelon;
     * 
     * public class BlockSilkyMelon extends BlockMelonBomb {
     * 
     *     public BlockSilkyMelon() {
     *         super();
     *     }
     * 
     *     @Override
     *     protected void setEntityMelon(World par1World, int par2, int par3, int par4) {
     *         EntitySilkyMelon entityMelon = new EntitySilkyMelon(
     *             par1World,
     *             (double) ((float) par2 + 0.5F),
     *             (double) ((float) par3 + 1.0F),
     *             (double) ((float) par4 + 0.5F));
     *         entityMelon.rotationYaw = 0.0F;
     * 
     *         if (!par1World.isRemote) {
     *             if (entityMelon != null) AMTLogger.debugInfo("melon " + entityMelon.getEntityId());
     *             par1World.spawnEntityInWorld(entityMelon);
     *         }
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky");
     *         this.boxIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky_top");
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky");
     *     }
     * 
     * }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockSilkyMelon.java")
     */
}
