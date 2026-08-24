package mods.defeatedcrow.common.item.edible;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import mods.defeatedcrow.api.edibles.IEdibleItem;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.20.1 base for edible items that can be placed as PlaceableFoods entities.
 * Simplified from 1.7.10 EdibleEntityItem (onItemUse -> useOn, PotionEffect -> MobEffectInstance).
 */
public class EdibleEntityItem extends Item implements IEdibleItem {

    public boolean allowChopstacks = true;
    public boolean showTooltip = true;

    public EdibleEntityItem(Properties properties) {
        super(properties);
    }

    public EdibleEntityItem(Properties properties, boolean chopsticks, boolean tip) {
        super(properties);
        this.allowChopstacks = chopsticks;
        this.showTooltip = tip;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        if (showTooltip) {
            var eff = effectOnEaten(null, 0);
            if (eff != null && !eff.isEmpty()) {
                for (var e : eff) {
                    tooltip.add(Component.literal(e.getDescriptionId()));
                }
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        if (entity instanceof Player player) {
            var eff = effectOnEaten(player, 0);
            if (eff != null) {
                for (var e : eff) player.addEffect(new MobEffectInstance(e));
            }
            var hunger = hungerOnEaten(0);
            if (hunger != null && hunger.length >= 2) {
                player.getFoodData().eat(hunger[0], hunger[1] * 0.1F);
            }
            ItemStack ret = getReturnContainer(0);
            if (!ret.isEmpty() && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(ret)) player.drop(ret, false);
            }
        }
        if (stack.getCount() > 1) {
            stack.shrink(1);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getUseDuration(ItemStack stack) { return 32; }

    @Override
    public net.minecraft.world.item.UseAnim getUseAnimation(ItemStack stack) { return net.minecraft.world.item.UseAnim.EAT; }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.canEat(stack.getFoodProperties(player) != null ? stack.getFoodProperties(player).canAlwaysEat() : false)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        Direction side = ctx.getClickedFace();
        BlockPos target = pos;
        // adjust target like 1.7.10 onItemUse (snow layer, replaceable)
        if (block == Blocks.SNOW && state.getValue(net.minecraft.world.level.block.SnowLayerBlock.LAYERS) < 1) {
            // keep same
        } else if (block != Blocks.VINE && block != Blocks.TALL_GRASS && block != Blocks.DEAD_BUSH && !state.canBeReplaced()) {
            target = pos.relative(side);
        }
        Player player = ctx.getPlayer();
        ItemStack stack = ctx.getItemInHand();
        if (stack.isEmpty() || player == null) return InteractionResult.PASS;
        if (!player.mayUseItemAt(target, side, stack)) return InteractionResult.FAIL;
        if (target.getY() >= 255 || target.getY() < -64) return InteractionResult.FAIL;
        // allow placement only if config allows (always true for test)
        if (!level.isClientSide) {
            ItemStack toPlace = new ItemStack(this, 1);
            // copy NBT? simplified
            if (this.spownEntityFoods(level, player, toPlace, target.getX() + 0.5, target.getY(), target.getZ() + 0.5)) {
                level.playSound(null, target, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 0.5F, 0.8F);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    protected boolean spownEntityFoods(Level level, Player player, ItemStack item, double x, double y, double z) {
        return false;
    }

    @Override
    public ItemStack getReturnContainer(int meta) { return ItemStack.EMPTY; }

    @Override
    public List<MobEffectInstance> effectOnEaten(Player player, int meta) { return new ArrayList<>(); }

    @Override
    public int[] hungerOnEaten(int meta) { return new int[]{4, 2}; }
}
