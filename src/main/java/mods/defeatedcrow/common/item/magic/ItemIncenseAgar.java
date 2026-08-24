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
 * WT-A 1.20.1 mojmap migration for ItemIncenseAgar.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIncenseAgar extends Item {
    public ItemIncenseAgar(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLiving;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.api.charm.EffectType;
     * import mods.defeatedcrow.api.charm.IIncenseEffect;
     * import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;
     * 
     * // 鎮静のインセンス
     * public class ItemIncenseAgar extends Item implements IIncenseEffect {
     * 
     *     public ItemIncenseAgar() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_aloeswood");
     *     }
     * 
     *     /*
     *      * 以下はIncenseの効果を定義する部分。
     *      * Item側に実装したほうが追加が容易だと思う。
     *      * /
     * 
     *     @Override
     *     public int effectAreaRange() {
     *         return 5;
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
     *             if (entity instanceof EntityLiving)// 対象はEntityLiving
     *             {
     *                 EntityLiving living = (EntityLiving) entity;
     * 
     *                 // if (DCsAppleMilk.confinement != null)//ポーション効果が追加されているか
     *                 // {
     *                 // living.addPotionEffect(new PotionEffect(DCsAppleMilk.confinement.id, 200, 0));
     *                 // }
     * 
     *                 /* 動きを止めてしまう効果に変更。 * /
     *                 EntityStunEffect stun = new EntityStunEffect(world, living, null, 20);
     *                 if (!world.isRemote) {
     *                     world.spawnEntityInWorld(stun);
     *                 }
     * 
     */
}
