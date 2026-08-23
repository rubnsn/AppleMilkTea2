package mods.defeatedcrow.common.item.edible;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemBaseSoupBowl.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemBaseSoupBowl extends Item {
    public ItemBaseSoupBowl(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    // 1.7.10 ItemFood -> 1.20.1 FoodProperties in ModItems registration (Item.Properties.food(...))
    // finishUsingItem replaces onEaten/onFoodEaten
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {
        // TODO: restore onItemUse logic with BlockPos/Level/Player
        return super.useOn(ctx);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.edible;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net/minecraft/init/Items;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.api.appliance.SoupType;
     * import mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup;
     * import mods.defeatedcrow.plugin.AddonIntegration;
     * 
     * /**
     *  * フォンデュベースのスープを汲んだもの。
     *  * ベースなので回復量は少ないが、クラフト素材になる。
     *  * /
     * public class ItemBaseSoupBowl extends EdibleEntityItem2 {
     * 
     *     
     *     private BlockTexture iconType[];
     *     
     *     private BlockTexture innerType[];
     * 
     *     public final SoupType[] types = new SoupType[] { SoupType.WATER, SoupType.CHOCO, SoupType.OIL, SoupType.DASHI,
     *         SoupType.SHOYU, SoupType.TONKOTU, SoupType.BLOOD, SoupType.PURPLE, SoupType.CHEESE };
     * 
     *     public ItemBaseSoupBowl() {
     *         super(true, false);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     *         this.setContainerItem(Items.bowl);
     *     }
     * 
     *     @Override
     *     public ItemStack getReturnContainer(int meta) {
     *         return new ItemStack(Items.bowl, 1, 0);
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return meta == 0 ? new int[] { 0, 0 } : new int[] { 2, 2 };
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int m = par1 & 15;
     *         int j = MathHelper.clamp_int(m, 0, types.length - 1);
     *         return par1 > 15 ? this.innerType[j] : this.iconType[j];
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1 & 15;
     */
}
