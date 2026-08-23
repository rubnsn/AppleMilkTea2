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
 * WT-A 1.20.1 mojmap migration for ItemIncenseFrankincense.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIncenseFrankincense extends Item {
    public ItemIncenseFrankincense(Properties properties) {
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
     * import net.minecraft.entity.EntityCreature;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.monster.EntityMob;
     * import net.minecraft.item.Item;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.api.charm.EffectType;
     * import mods.defeatedcrow.api.charm.IIncenseEffect;
     * 
     * // 浄化のインセンス
     * public class ItemIncenseFrankincense extends Item implements IIncenseEffect {
     * 
     *     public ItemIncenseFrankincense() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_frankincense");
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
     *             if (entity instanceof EntityCreature)// 対象はEntityCreature
     *             {
     *                 EntityCreature living = (EntityCreature) entity;
     * 
     *                 if (living instanceof EntityMob)// 対象はバニラ敵モブであるEntityMob、及びスライムだけ。
     *                 {
     *                     living.setDead();
     *                     return true;
     *                 } else {
     *                     return false;
     *                 }
     * 
     *                 /*
     *                  * 条件に一致するEntityは問答無用で消してしまうので注意。
     *                  * /
     */
}
