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
 * WT-A 1.20.1 mojmap migration for ItemPrincessClam.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemPrincessClam extends Item {
    public ItemPrincessClam(Properties properties) {
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
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.world.entity.player.PlayerMP;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.EnumRarity;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * import net.minecraft.util.ChatComponentText;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * 
     * public class ItemPrincessClam extends Item {
     * 
     *     private static final String[] clamType = new String[] { "princessclam", "raden_flower", "raden_butterfly",
     *         "raden_wing", "raden_moon" };
     * 
     *     
     *     private BlockTexture iconclamType[];
     * 
     *     public ItemPrincessClam() {
     *         super();
     *         this.setHasSubtypes(true);
     *         this.setMaxDamage(0);
     *         this.setMaxStackSize(1);
     *     }
     * 
     *     // ブロックに使った
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int posX,
     *         int posY, int posZ, int side, float fx, float fy, float fz) {
     *         Block i1 = par3World.getBlock(posX, posY, posZ);
     *         int meta = par1ItemStack.getDamageValue();
     *         Block block = Blocks.sand;
     *         if (i1 == Blocks.sand && meta == 0)// ハマグリを植えるときの処理
     *         {
     *             par3World.setBlock(posX, posY, posZ, DCsAppleMilk.clamSand, 2, 3);
     *             par3World.playSoundEffect(
     *                 posX + 0.5F,
     *                 posY + 0.5F,
     *                 posZ + 0.5F,
     *                 block.stepSound.getStepResourcePath(),
     *                 (block.stepSound.getVolume() + 1.0F) / 2.0F,
     *                 block.stepSound.getPitch() * 0.8F);
     *             --par1ItemStack.getCount();
     *             return true;
     *         } else if (meta == 3)// 風のチャーム
     */
}