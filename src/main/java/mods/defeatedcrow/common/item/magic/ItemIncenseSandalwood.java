package mods.defeatedcrow.common.item.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemIncenseSandalwood.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIncenseSandalwood extends Item {
    public ItemIncenseSandalwood(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityAgeable;
     * import net.minecraft.entity.EntityLiving;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.passive.EntityTameable;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.api.charm.EffectType;
     * import mods.defeatedcrow.api.charm.IIncenseEffect;
     * import mods.defeatedcrow.handler.Util;
     * 
     * // 惑乱のインセンス
     * public class ItemIncenseSandalwood extends Item implements IIncenseEffect {
     * 
     *     public ItemIncenseSandalwood() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_sandalwood");
     *     }
     * 
     *     /*
     *      * 以下はIncenseの効果を定義する部分。
     *      * Item側に実装したほうが追加が容易だと思う。
     *      * /
     * 
     *     @Override
     *     public int effectAreaRange() {
     *         return 3;
     *     }
     * 
     *     @Override
     *     public EffectType getEffectType() {
     *         return EffectType.EntityLiving;
     *     }
     * 
     *     @Override
     *     public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {
     * 
     *         if (incense.getEffectType() == this.getEffectType() && entity != null) {
     *             if (entity instanceof EntityLiving)// 装備情報はEntityLivingにある
     *             {
     *                 EntityLiving living = (EntityLiving) entity;
     * 
     *                 if (living instanceof EntityTameable || living instanceof EntityAgeable) {
     *                     return false;// 味方の可能性があるので何もしない
     *                 } else {// 装備剥がし
     *                     int slot = world.rand.nextInt(5);// 0~4、手持ちと防具スロット
     *                     ItemStack equip = living.getEquipmentInSlot(slot);
     *                     if (Util.notEmptyItem(equip)) {
     *                         ItemStack drop = equip.copy();
     */
}
