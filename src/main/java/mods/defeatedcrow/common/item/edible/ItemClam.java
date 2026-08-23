package mods.defeatedcrow.common.item.edible;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemClam.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemClam extends Item {
    public ItemClam(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    // 1.7.10 ItemFood -> 1.20.1 FoodProperties in ModItems registration (Item.Properties.food(...))
    // finishUsingItem replaces onEaten/onFoodEaten
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        return super.finishUsingItem(stack, level, entity);
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
     * import net.minecraft.block.Block;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemFood;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class ItemClam extends ItemFood {
     * 
     *     private static final String[] clamType = new String[] { "clam", "clam_cooked", "burntmeat", "blackegg" };
     * 
     *     
     *     private BlockTexture iconclamType[];
     * 
     *     public ItemClam() {
     *         super(5, 5, false);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
     *         int par5, int par6, int par7, float par8, float par9, float par10) {
     *         Block i1 = par3World.getBlock(par4, par5, par6);
     *         Block block = Blocks.sand;
     *         if (i1 == Blocks.sand && par1ItemStack.getItemDamage() == 0) {
     *             par3World.setBlock(par4, par5, par6, DCsAppleMilk.clamSand, 0, 3);
     *             par3World.playSoundEffect(
     *                 par4 + 0.5F,
     *                 par5 + 0.5F,
     *                 par6 + 0.5F,
     *                 block.stepSound.getBreakSound(),
     *                 (block.stepSound.getVolume() + 1.0F) / 2.0F,
     *                 block.stepSound.getPitch() * 0.8F);
     *             --par1ItemStack.stackSize;
     *             return true;
     *         } else if (i1 == DCsAppleMilk.wipeBox && par1ItemStack.getItemDamage() == 0) {
     *             Block under = par3World.getBlock(par4, par5 - 1, par6);
     *             int meta = par3World.getBlockMetadata(par4, par5, par6);
     *             int underMeta = par3World.getBlockMetadata(par4, par5 - 1, par6);
     *             if (under == DCsAppleMilk.cLamp && (meta & 1) == 1 && underMeta > 3) {
     *                 if (par3World.setBlock(par4, par5, par6, DCsAppleMilk.crowDoll)) {
     *                     par3World.playSoundAtEntity(par2EntityPlayer, "defeatedcrow:suzu", 1.0F, 1.2F);
     *                     --par1ItemStack.stackSize;
     *                     return true;
     *                 }
     *             }
     *             return false;
     *         } else {
     */
}
