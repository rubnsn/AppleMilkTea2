package mods.defeatedcrow.common.item;

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
 * WT-A 1.20.1 mojmap migration for ItemOnixSword.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemOnixSword extends Item {
    public ItemOnixSword(Properties properties) {
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
     * package mods.defeatedcrow.common.item;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.enchantment.EnchantmentHelper;
     * import net.minecraft.entity.SharedMonsterAttributes;
     * import net.minecraft.entity.ai.attributes.AttributeModifier;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.item.ItemSword;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.world.level.Level;
     * 
     * import com.google.common.collect.Multimap;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class ItemOnixSword extends ItemSword {
     * 
     *     private float damage;
     * 
     *     public ItemOnixSword() {
     *         super(DCsAppleMilk.enumToolMaterialChalcedony);
     * 
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(192);
     *         this.damage = 7.0F;
     *     }
     * 
     *     @Override
     *     public Multimap getItemAttributeModifiers() {
     *         Multimap multimap = super.getItemAttributeModifiers();
     *         multimap.put(
     *             SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
     *             new AttributeModifier(field_111210_e, "Weapon modifier", this.damage, 0));
     *         return multimap;
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/onixsword");
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         return par1ItemStack;
     *     }
     * 
     *     @Override
     *     public int getMaxItemUseDuration(ItemStack par1ItemStack) {
     *         return 72000;
     *     }
     * 
     *     @Override
     *     public EnumAction getItemUseAction(ItemStack par1ItemStack) {
     *         return EnumAction.bow;
     *     }
     * 
     */
}