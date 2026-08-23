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
 * WT-A 1.20.1 mojmap migration for EntityItemCocktail2.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemCocktail2 extends Block {

    public EntityItemCocktail2(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.block.material.Material;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.monster.EntityMob;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.item.ItemTool;
     * import net.minecraft.nbt.NBTTagCompound;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.potion.PotionEffect;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import net.minecraft.world.storage.WorldInfo;
     * import mods.defeatedcrow.api.energy.IBattery;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.block.appliance.ItemMachineBlock;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.common.entity.edible.PlaceableCocktail2;
     * 
     * public class EntityItemCocktail2 extends EdibleEntityItemBlock2 {
     * 
     *     private static final String[] type = new String[] { "_mako_tyuhai", "_panache", "_spritzer", "_screw_driver",
     *         "_god_farther", "_tom_and_jerry", "_alexander", "_zoom", "_amaretto_milktea", "_snow_saronno" };
     * 
     *     public EntityItemCocktail2(Block block) {
     *         super(block, false, true);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int m = (par1ItemStack.getItemDamage());
     *         if (m < 10) return super.getUnlocalizedName() + type[m];
     *         else return super.getUnlocalizedName() + m;
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         if (!par2World.isRemote) {
     *             this.addSSMoisture(4, 3F, par3EntityPlayer);
     *         }
     *         par3EntityPlayer.triggerAchievement(AchievementRegister.drinkCocktail);
     * 
     *         this.formEffect(par2World, par3EntityPlayer, par1ItemStack.getItemDamage());
     *         return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return new int[] { 0, 0 };
     *     }
     * 
     *     @Override
     *     public ArrayList<PotionEffect> effectOnEaten(EntityPlayer par1EntityPlayer, int meta) {
     *         PotionEffect potion = new PotionEffect(Potion.digSpeed.id, 2400, 2);
     *         int tick = 2400;
     *         boolean flag = false;
     * 
     *         ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
     *         ret.add(new PotionEffect(Potion.hunger.id, 300, 1));
     * 
     *         if (meta == 0 && DCsAppleMilk.hallucinations != null)// mako
     *         {
     *             if (par1EntityPlayer.isPotionActive(DCsAppleMilk.hallucinations.id)) {
     *                 tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.hallucinations)
     *                     .getDuration() + 1200;
     *                 potion = new PotionEffect(DCsAppleMilk.hallucinations.id, tick, 0);
     *                 flag = true;
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemCocktail2.java")
     */
}
