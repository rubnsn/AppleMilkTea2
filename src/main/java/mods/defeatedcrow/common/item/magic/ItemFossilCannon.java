package mods.defeatedcrow.common.item.magic;

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
 * WT-A 1.20.1 mojmap migration for ItemFossilCannon.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemFossilCannon extends Item {
    public ItemFossilCannon(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {
        return super.useOn(ctx);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.enchantment.EnchantmentHelper;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.passive.EntityHorse;
     * import net.minecraft.entity.passive.EntityTameable;
     * import net.minecraft.entity.passive.EntityVillager;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.EnumRarity;
     * import net.minecraft.world.item.ItemBow;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.api.energy.IBattery;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.entity.EntityAnchorMissile;
     * 
     * public class ItemFossilCannon extends ItemBow implements IBattery {
     * 
     *     public ItemFossilCannon() {
     *         super();
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(16);
     *         this.setNoRepair();
     *     }
     * 
     *     // IBatteryのメソッド
     *     @Override
     *     public int getMaxAmount(ItemStack item) {
     *         return 12800;
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
     *     }
     * 
     *     // 文字色
     *     public EnumRarity getRarity(ItemStack par1ItemStack) {
     *         return EnumRarity.rare;
     *     }
     * 
     *     /*
     *      * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
     *      * /
     *     public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer,
     *         int par4) {
     *         boolean ff = par3EntityPlayer.capabilities.isCreativeMode
     *             || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
     *         boolean flag2 = false;
     */
}