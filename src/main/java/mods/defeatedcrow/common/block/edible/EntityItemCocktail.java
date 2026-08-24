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
 * WT-A 1.20.1 mojmap migration for EntityItemCocktail.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemCocktail extends Block {

    public EntityItemCocktail(BlockBehaviour.Properties properties) {
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

    // 1.20.1 use: right-click handling (insert/extract with sound)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);
        var be = level.getBlockEntity(pos);
        if (be instanceof net.minecraft.world.WorldlyContainer wc) {
            ItemStack held = player.getItemInHand(hand);
            if (held.isEmpty()) {
                for (int i = wc.getContainerSize() - 1; i >= 0; i--) {
                    ItemStack s = wc.getItem(i);
                    if (!s.isEmpty()) {
                        ItemStack copy = s.copy(); copy.setCount(1);
                        if (!player.getInventory().add(copy)) player.drop(copy, false);
                        s.shrink(1);
                        if (s.isEmpty()) wc.setItem(i, ItemStack.EMPTY);
                        wc.setChanged();
                        level.sendBlockUpdated(pos, state, state, 3);
                        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                        return InteractionResult.sidedSuccess(false);
                    }
                }
            } else {
                for (int i = 0; i < wc.getContainerSize(); i++) {
                    if (wc.canPlaceItemThroughFace(i, held, hit.getDirection()) || wc.getItem(i).isEmpty()) {
                        ItemStack existing = wc.getItem(i);
                        if (existing.isEmpty()) {
                            ItemStack toPut = held.copy(); toPut.setCount(1);
                            wc.setItem(i, toPut);
                            if (!player.getAbilities().instabuild) held.shrink(1);
                            wc.setChanged();
                            level.sendBlockUpdated(pos, state, state, 3);
                            level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                            return InteractionResult.sidedSuccess(false);
                        } else if (existing.is(held.getItem()) && existing.getCount() < existing.getMaxStackSize()) {
                            existing.grow(1);
                            if (!player.getAbilities().instabuild) held.shrink(1);
                            wc.setChanged();
                            level.sendBlockUpdated(pos, state, state, 3);
                            level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ITEM_PICKUP, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.8F);
                            return InteractionResult.sidedSuccess(false);
                        }
                    }
                }
            }
            return InteractionResult.sidedSuccess(false);
        }
        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WOOD_PLACE, net.minecraft.sounds.SoundSource.BLOCKS, 0.4F, 1.2F);
        return InteractionResult.sidedSuccess(false);
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
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.world.level.Level;
     * 
     * import mods.defeatedcrow.common.*;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.common.entity.edible.*;
     * 
     * public class EntityItemCocktail extends EdibleEntityItemBlock2 {
     * 
     *     private static final String[] type = new String[] { "_frozen_daiquiri", "_frozen_sake", "_saketini", "_gimlet",
     *         "_blackrose", "_redeye", "_pinacolada", "_americanlemonade", "_moscowmule", "_mintjulep", "_kir", "_cassismilk",
     *         "_bloodymary", "_cassistea", "_doubleapple", "_plumsoymilk" };
     * 
     *     public EntityItemCocktail(Block block) {
     *         super(block, false, true);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int m = (par1ItemStack.getDamageValue());
     *         if (m < 17) return super.getUnlocalizedName() + type[m];
     *         else return super.getUnlocalizedName() + m;
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         if (!par2World.isRemote) {
     *             this.addSSMoisture(4, 3F, par3EntityPlayer);
     *         }
     *         par3EntityPlayer.triggerAchievement(AchievementRegister.drinkCocktail);
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
     *         if (meta == 0)// frozen daiquiri
     *         {
     *             if (par1EntityPlayer.isPotionActive(Potion.invisibility.id)) {
     *                 tick = par1EntityPlayer.getActivePotionEffect(Potion.invisibility)
     *                     .getDuration() + 2400;
     *                 potion = new PotionEffect(Potion.invisibility.id, tick, 0);
     *                 flag = true;
     *             } else {
     *                 potion = new PotionEffect(Potion.invisibility.id, 2400, 0);
     *             }
     *         } else if (meta == 1 && DCsAppleMilk.reflex != null)// frozen sake
     *         {
     *             if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDReflex)) {
     *                 tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.reflex)
     *                     .getDuration() + 600;
     *                 potion = new PotionEffect(DCsConfig.potionIDReflex, tick, 2);
     *                 flag = false;
     *             } else {
     *                 potion = new PotionEffect(DCsConfig.potionIDReflex, 600, 2);
     *             }
     *         } else if (meta == 2)// sake-tini
     *         {
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemCocktail.java")
     */
}
