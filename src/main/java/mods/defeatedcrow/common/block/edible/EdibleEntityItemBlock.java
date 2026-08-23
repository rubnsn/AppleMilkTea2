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
 * WT-A 1.20.1 mojmap migration for EdibleEntityItemBlock.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EdibleEntityItemBlock extends Block {

    public EdibleEntityItemBlock(BlockBehaviour.Properties properties) {
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
     * import java.util.ArrayList;
     * import java.util.List;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.ItemBlock;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.potion.PotionEffect;
     * import net.minecraft.util.StatCollector;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.edibles.IEdibleItem;
     * import mods.defeatedcrow.api.events.EatEdiblesEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
     * 
     * public abstract class EdibleEntityItemBlock extends ItemBlock implements IEdibleItem {
     * 
     *     public boolean allowChopstacks = true;
     *     public boolean showTooltip = true;
     * 
     *     public EdibleEntityItemBlock(Block block, boolean chopsticks, boolean tip) {
     *         super(block);
     *         this.allowChopstacks = chopsticks;
     *         this.showTooltip = tip;
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         int meta = par1ItemStack.getItemDamage();
     *         boolean flag = false;
     *         EatEdiblesEvent event = new EatEdiblesEvent(par2World, par3EntityPlayer, par1ItemStack);
     * 
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.hasResult() && event.getResult() == Result.ALLOW) {
     *             if (!par3EntityPlayer.capabilities.isCreativeMode) {
     *                 --par1ItemStack.stackSize;
     *                 this.returnItemStack(par3EntityPlayer, meta);
     *             }
     *             flag = true;
     *         }
     * 
     *         if (event.isCanceled()) {
     *             return par1ItemStack;
     *         }
     * 
     *         if (!flag && !par3EntityPlayer.capabilities.isCreativeMode) {
     *             --par1ItemStack.stackSize;
     *             this.returnItemStack(par3EntityPlayer, meta);
     *         }
     * 
     *         if (!par2World.isRemote) {
     *             if (this.effectOnEaten(par3EntityPlayer, meta) != null) {
     *                 ArrayList<PotionEffect> potion = this.effectOnEaten(par3EntityPlayer, meta);
     *                 if (potion != null && !potion.isEmpty()) {
     *                     for (PotionEffect ret : potion) {
     *                         par3EntityPlayer.addPotionEffect(ret);
     *                     }
     *                 }
     *             }
     * 
     *             if (this.hungerOnEaten(meta) != null) {
     *                 int[] h = this.hungerOnEaten(meta);
     *                 addStatus(par3EntityPlayer, h, par1ItemStack);
     *             }
     *         }
     * 
     *         return par1ItemStack;
     *     }
     * 
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EdibleEntityItemBlock.java")
     */
}
