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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for EntityItemBowl.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemBowl extends Block {

    public EntityItemBowl(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.edible;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Items;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.Level;
     * 
     * import mods.defeatedcrow.common.entity.edible.*;
     * 
     * public class EntityItemBowl extends EdibleEntityItemBlock2 {
     * 
     *     public static final String[] type = new String[] { "_rice", "_mushroom", "_stew", "_zousui", "_kayaku", "_soi",
     *         "_pumpkin", "_BLTsoup", "_misosoup", "_clamsoup" };
     * 
     *     public EntityItemBowl(Block block) {
     *         super(block, true, false);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *         setContainerItem(Items.bowl);
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return new int[] { 4, 2 };
     *     }
     * 
     *     @Override
     *     public ItemStack getReturnContainer(int meta) {
     * 
     *         return new ItemStack(Items.bowl, 1, 0);
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int m = (par1ItemStack.getDamageValue());
     *         if (m < 10) return super.getUnlocalizedName() + type[m];
     *         else return super.getUnlocalizedName() + m;
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
     *         PlaceableBowl entity = new PlaceableBowl(world, item, x, y, z);
     *         entity.rotationYaw = player.rotationYaw - 180.0F;
     * 
     *         if (!world.isRemote && item != null) {
     *             return world.spawnEntityInWorld(entity);
     *         }
     * 
     *         return false;
     *     }
     * 
     * }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemBowl.java")
     */
}
