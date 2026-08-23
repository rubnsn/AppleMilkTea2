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
 * WT-A 1.20.1 mojmap migration for ItemIncenseMint.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIncenseMint extends Item {
    public ItemIncenseMint(Properties properties) {
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
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.api.charm.EffectType;
     * import mods.defeatedcrow.api.charm.IIncenseEffect;
     * 
     * // 採掘速度上昇のインセンス
     * public class ItemIncenseMint extends Item implements IIncenseEffect {
     * 
     *     public ItemIncenseMint() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_mint");
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
     *         return EffectType.Player;
     *     }
     * 
     *     @Override
     *     public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {
     * 
     *         if (incense.getEffectType() == this.getEffectType() && entity != null) {
     *             if (!entity.isPotionActive(Potion.digSpeed.id)) {
     *                 entity.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 200, 0));
     *                 return true;
     *             }
     *         }
     *         return false;
     *     }
     * 
     *     @Override
     *     public String particleIcon() {
     *         return "blink";
     *     }
     * 
     *     @Override
     *     public float particleColorR() {
     */
}
