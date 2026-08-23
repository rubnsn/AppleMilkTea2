package mods.defeatedcrow.common.item.edible;

import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemWallMug.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemWallMug extends Item {
    public ItemWallMug(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
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
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.level.Level;
     * 
     * public class ItemWallMug extends Item {
     * 
     *     private static final String[] contents = new String[] { "_tea", "_green", "_cocoa", "_coffee" };
     *     private static final String[] contentsFruit = new String[] { "_foam", "_foam", "_nuts", "_berry" };
     * 
     *     private static final String[] drinkType = new String[] { "Tea", "Green Tea", "Cocoa", "Coffee" };
     *     private static final String[] milkType = new String[] { "None", "Milk", "Condenced Milk", "Soy" };
     *     private static final String[] sugarType = new String[] { "None", "Sugar", "Maple", "Honey" };
     *     private static final String[] fruitType = new String[] { "None", "Foam", "Nuts", "Berry" };
     *     private static final String[] potionType = new String[] { "Regeneration", "Dig Speed", "Resistance",
     *         "Damage Boost" };
     *     private static final String[] timeType = new String[] { "(60sec)", "(2min)", "(4min)", "(5min)" };
     * 
     *     
     *     private BlockTexture[] thisTex;
     *     
     *     private BlockTexture[] topTex;
     * 
     *     public ItemWallMug() {
     *         super();
     *         this.setMaxStackSize(8);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *     }
     * 
     *     // 飲食時の処理
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         if (!par3EntityPlayer.capabilities.isCreativeMode) {
     *             --par1ItemStack.getCount();
     *         }
     *         if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this))) {
     *             par3EntityPlayer.entityDropItem(new ItemStack(this), 1);
     *         }
     * 
     *         int meta = par1ItemStack.getDamageValue();
     *         int type = checkType(meta);
     *         int milk = checkMilkType(meta);
     *         int sugar = checkSugarType(meta);
     *         int fruit = checkFruitType(meta);
     *         int[] time = { 600, 1800, 3000, 5400 };
     *         int m = 600 + time[milk];
     * 
     *         if (!par2World.isRemote) {
     *             if (type == 0)// Tea
     */
}