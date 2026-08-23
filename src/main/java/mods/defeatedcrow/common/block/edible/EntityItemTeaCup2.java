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
 * WT-A 1.20.1 mojmap migration for EntityItemTeaCup2.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemTeaCup2 extends Block {

    public EntityItemTeaCup2(BlockBehaviour.Properties properties) {
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
     * import java.util.Iterator;
     * import java.util.List;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.potion.PotionEffect;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.api.potion.AMTPotionManager;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.entity.edible.PlaceableCup2;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class EntityItemTeaCup2 extends EdibleEntityItemBlock2 {
     * 
     *     public static final String[] teaType = new String[] { "_earlgray", "_earlgray_milk", "_appletea", "_appletea_milk",
     *         "_lime", "_tomato", "_berry", "_berry_milk", "_grape", "_mint", "_yuzu", "_orange", "_soda" };
     * 
     *     private int healAmount = 0;
     * 
     *     public EntityItemTeaCup2(Block block) {
     *         super(block, true, true);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *         this.setMaxStackSize(Util.getCupStacksize());
     *         setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyCup));
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         int meta = par1ItemStack.getItemDamage();
     * 
     *         if (!par2World.isRemote) {
     *             this.setPotionWithTea(par3EntityPlayer, meta);
     *             this.addSSMoisture(6, 1.5F, par3EntityPlayer);
     *         }
     * 
     *         return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
     *     }
     * 
     *     @Override
     *     public ItemStack getReturnContainer(int meta) {
     * 
     *         return new ItemStack(DCsAppleMilk.emptyCup, 1, 0);
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return new int[] { 0, 0 };
     *     }
     * 
     *     @Override
     *     public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
     * 
     *         ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
     *         int dur = 600;
     * 
     *         if ((meta & 1) == 1) {
     *             dur = 1200;
     *         }
     * 
     *         int id[] = { Potion.regeneration.id, dur, 0 };
     * 
     *         if (meta == 0 || meta == 1) {
     *             id[0] = AMTPotionManager.manager.AMTgetPotion("immunization")
     *                 .getId();
     *             id[2] = 1;
     *         } else if (meta == 2 || meta == 3) {
     *             id[0] = AMTPotionManager.manager.AMTgetPotion("immunization")
     *                 .getId();
     *         } else if (meta == 5) {
     *             id[0] = Potion.damageBoost.id;
     *         } else if (meta == 6 || meta == 7) {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemTeaCup2.java")
     */
}
