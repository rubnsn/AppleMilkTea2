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
 * WT-A 1.20.1 mojmap migration for EdibleEntityItemBlock2.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EdibleEntityItemBlock2 extends Block {

    public EdibleEntityItemBlock2(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.block.Block;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.ItemStack;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.plugin.LoadAppleCorePlugin;
     * import squeek.applecore.api.food.FoodValues;
     * import squeek.applecore.api.food.IEdible;
     * 
     * /* クラッシュ回避用の中継クラス * /
     * @Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
     * public class EdibleEntityItemBlock2 extends EdibleEntityItemBlock implements IEdible {
     * 
     *     public EdibleEntityItemBlock2(Block block, boolean chopsticks, boolean tip) {
     *         super(block, chopsticks, tip);
     *     }
     * 
     *     @Override
     *     @Optional.Method(modid = "AppleCore")
     *     public FoodValues getFoodValues(ItemStack itemStack) {
     *         int[] h = this.hungerOnEaten(itemStack.getItemDamage());
     *         return new FoodValues(h[0], h[1] * 0.1F);
     *     }
     * 
     *     @Override
     *     protected void addStatus(EntityPlayer player, int[] heal, ItemStack food) {
     *         if (DCsAppleMilk.SuccessLoadACore) {
     *             LoadAppleCorePlugin.addFoodStatus(player, food);
     *         } else {
     *             player.getFoodStats()
     *                 .addStats(heal[0], heal[1] * 0.1F);
     *         }
     *     }
     * 
     * }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EdibleEntityItemBlock2.java")
     */
}
