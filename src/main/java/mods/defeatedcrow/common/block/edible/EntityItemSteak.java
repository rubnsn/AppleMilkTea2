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
 * WT-A 1.20.1 mojmap migration for EntityItemSteak.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemSteak extends Block {

    public EntityItemSteak(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.potion.PotionEffect;
     * import net.minecraft.world.World;
     * 
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.entity.edible.PlaceableSteak;
     * 
     * public class EntityItemSteak extends EdibleEntityItemBlock2 {
     * 
     *     private static final String[] type = new String[] { "_beef", "_pork", "_chicken", "_clam" };
     * 
     *     public EntityItemSteak(Block block) {
     *         super(block, true, false);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int m = (par1ItemStack.getDamageValue());
     *         if (m < 4) return super.getUnlocalizedName() + type[m];
     *         else return super.getUnlocalizedName() + m;
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         int meta = par1ItemStack.getDamageValue();
     *         int heal = (meta == 3) ? 1 : 2;
     * 
     *         if (!par2World.isRemote) {
     *             this.addSSMoisture(-2, 3F, par3EntityPlayer);
     *             this.addSSStamina(20, 3F, par3EntityPlayer);
     * 
     *             if (meta < 3 && DCsAppleMilk.suffocation != null) {
     *                 boolean flag = par3EntityPlayer.isPotionActive(DCsAppleMilk.suffocation)
     *                     && (par3EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation)
     *                         .getDuration() < 150);
     * 
     *                 if (flag) {
     *                     int dur = par3EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation)
     *                         .getDuration();
     *                     par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, dur + 100, 1));
     *                 } else {
     *                     par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, 200, 0));
     *                 }
     *             }
     *         }
     * 
     *         return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return meta < 3 ? new int[] { 12, 6 } : new int[] { 7, 3 };
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
     *         PlaceableSteak entity = new PlaceableSteak(world, item, x, y, z);
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
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemSteak.java")
     */
}
