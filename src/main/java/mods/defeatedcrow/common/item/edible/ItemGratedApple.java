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
 * WT-A 1.20.1 mojmap migration for ItemGratedApple.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemGratedApple extends Item {
    public ItemGratedApple(Properties properties) {
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

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.edible;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemFood;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.src.*;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import mods.defeatedcrow.common.*;
     * 
     * public class ItemGratedApple extends ItemFood {
     * 
     *     
     *     private BlockTexture iconType[];
     * 
     *     private static final String[] itemType = new String[] { "gratedapple", "gratedpeach", "honeylemon",
     *         "roastedcoffeepowder", "ganache", "gratedlime", "gratedtomato", "gratedberry", "gratedgrape", "orangeslice" };
     * 
     *     public ItemGratedApple(int reco, int sat, boolean flag) {
     *         super(reco, sat, flag);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     * 
     *     }
     * 
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 9);
     *         return this.iconType[j];
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         return super.getUnlocalizedName() + "_" + par1ItemStack.getDamageValue();
     *     }
     * 
     *     @Override
     *     
     *     public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(this, 1, 0));
     *         par3List.add(new ItemStack(this, 1, 1));
     *         par3List.add(new ItemStack(this, 1, 2));
     *         par3List.add(new ItemStack(this, 1, 9));
     *         par3List.add(new ItemStack(this, 1, 3));
     *         par3List.add(new ItemStack(this, 1, 4));
     *         par3List.add(new ItemStack(this, 1, 5));
     *         par3List.add(new ItemStack(this, 1, 6));
     *         par3List.add(new ItemStack(this, 1, 7));
     *         par3List.add(new ItemStack(this, 1, 8));
     *     }
     */
}
