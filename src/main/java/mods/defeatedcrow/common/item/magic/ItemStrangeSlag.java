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
 * WT-A 1.20.1 mojmap migration for ItemStrangeSlag.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemStrangeSlag extends Item {
    public ItemStrangeSlag(Properties properties) {
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
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import java.util.ArrayList;
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net/minecraft/init/Items;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.EnumChatFormatting;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.events.UseSlagEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.recipe.OreCrushRecipe;
     * 
     * public class ItemStrangeSlag extends Item {
     * 
     *     private Random rand = new Random();
     * 
     *     public ItemStrangeSlag() {
     *         super();
     *         this.setMaxStackSize(64);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:strange_slag");
     *     }
     * 
     *     /* 使用効果 * /
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
     *         int par5, int par6, int par7, float par8, float par9, float par10) {
     *         if (par2EntityPlayer == null) return true;
     *         this.onItemRightClick(par1ItemStack, par3World, par2EntityPlayer);
     *         return true;
     *     }
     * 
     *     @Override
     *     public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
     *         if (entityplayer == null) return itemstack;
     * 
     *         ItemStack ret = this.returnItem(itemstack, world, entityplayer);
     *         boolean flag = false;
     * 
     *         UseSlagEvent event = new UseSlagEvent(world, entityplayer, ret);
     *         MinecraftForge.EVENT_BUS.post(event);
     *         boolean res = false;
     * 
     *         if (event.hasResult() && event.getResult() == Result.ALLOW) {
     *             ret = event.returnItem;
     *         }
     * 
     */
}
