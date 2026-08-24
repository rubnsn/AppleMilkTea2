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
 * WT-A 1.20.1 mojmap migration for ItemIncenseVanilla.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIncenseVanilla extends Item {
    public ItemIncenseVanilla(Properties properties) {
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
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.api.charm.EffectType;
     * import mods.defeatedcrow.api.charm.IIncenseEffect;
     * import mods.defeatedcrow.common.tile.TileBrewingBarrel;
     * import mods.defeatedcrow.common.tile.TileCordial;
     * import mods.defeatedcrow.plugin.AddonIntegration;
     * import mods.defeatedcrow.plugin.HandleDryingRack;
     * 
     * // 熟成のインセンス
     * public class ItemIncenseVanilla extends Item implements IIncenseEffect {
     * 
     *     public ItemIncenseVanilla() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_vanilla");
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
     *         return EffectType.Block;
     *     }
     * 
     *     @Override
     *     public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {
     * 
     *         if (incense.getEffectType() == this.getEffectType()) {
     *             Block block = world.getBlock(x, y, z);
     *             int meta = world.getBlockMetadata(x, y, z);
     *             TileEntity tile = world.getTileEntity(x, y, z);
     *             boolean flag = false;
     *             if (tile != null) {
     *                 if (tile instanceof TileBrewingBarrel) {
     *                     TileBrewingBarrel barrel = (TileBrewingBarrel) tile;
     *                     int age = barrel.getAgingStage();
     *                     if (age < 4) {
     *                         barrel.setAgingStage(age + 1);
     *                         flag = true;
     */
}
