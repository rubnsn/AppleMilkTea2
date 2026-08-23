package mods.defeatedcrow.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemFireStarter.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemFireStarter extends Item {
    public ItemFireStarter(Properties properties) {
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
     * import net.minecraft.entity.player.EntityPlayer;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.world.World;
     * 
     * public class ItemFireStarter extends Item {
     * 
     *     public ItemFireStarter() {
     *         super();
     *         this.maxStackSize = 1;
     *         this.setMaxDamage(128);
     *     }
     * 
     *     /**
     *      * Callback for item usage. If the item does something special on right clicking, he will have
     *      * one of those. Return
     *      * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     *      * /
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
     *         int par5, int par6, int par7, float par8, float par9, float par10) {
     *         if (par7 == 0) {
     *             --par5;
     *         }
     * 
     *         if (par7 == 1) {
     *             ++par5;
     *         }
     * 
     *         if (par7 == 2) {
     *             --par6;
     *         }
     * 
     *         if (par7 == 3) {
     *             ++par6;
     *         }
     * 
     *         if (par7 == 4) {
     *             --par4;
     *         }
     * 
     *         if (par7 == 5) {
     *             ++par4;
     *         }
     * 
     *         if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
     *             return false;
     *         } else {
     *             if (par3World.isAirBlock(par4, par5, par6)) {
     *                 par3World.playSoundEffect(
     *                     par4 + 0.5D,
     *                     par5 + 0.5D,
     *                     par6 + 0.5D,
     *                     "fire.ignite",
     *                     1.0F,
     *                     itemRand.nextFloat() * 0.4F + 0.8F);
     */
}
