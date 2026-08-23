package mods.defeatedcrow.common.item.appliance;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemYuzuGatling.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemYuzuGatling extends Item {
    public ItemYuzuGatling(Properties properties) {
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
     * package mods.defeatedcrow.common.item.appliance;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.enchantment.EnchantmentHelper;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.EnumRarity;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemBow;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.energy.IBattery;
     * import mods.defeatedcrow.api.events.ShootingGunEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.entity.EntityYuzuBullet;
     * 
     * public class ItemYuzuGatling extends ItemBow implements IBattery {
     * 
     *     public ItemYuzuGatling() {
     *         super();
     *         this.setMaxStackSize(1);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:yuzu");
     *     }
     * 
     *     // 文字色
     *     @Override
     *     public EnumRarity getRarity(ItemStack par1ItemStack) {
     *         return EnumRarity.rare;
     *     }
     * 
     *     // IBatteryのメソッド
     *     @Override
     *     public int getMaxAmount(ItemStack item) {
     *         return 6400;
     *     }
     * 
     *     // 右クリ使用時
     *     @Override
     *     public int getMaxItemUseDuration(ItemStack par1ItemStack) {
     *         return 16;
     *     }
     * 
     *     @Override
     *     public EnumAction getItemUseAction(ItemStack par1ItemStack) {
     *         return EnumAction.bow;
     *     }
     * 
     *     @Override
     */
}
