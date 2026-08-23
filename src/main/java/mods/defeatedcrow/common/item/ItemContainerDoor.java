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
 * WT-A 1.20.1 mojmap migration for ItemContainerDoor.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemContainerDoor extends Item {
    public ItemContainerDoor(Properties properties) {
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
     * import java.util.List;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemDoor;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * 
     * public class ItemContainerDoor extends Item {
     * 
     *     public final Block output;
     * 
     *     public ItemContainerDoor(Block b) {
     *         super();
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *         output = b;
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         return super.getUnlocalizedName();
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     
     *     public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(this, 1, 7));
     *     }
     * 
     *     @Override
     *     
     *     // マウスオーバー時の表示情報
     *     public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
     *         super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
     *         int l = par1ItemStack.getDamageValue();
     *         int rem = (l & 7) + 1;
     *         par3List.add(new String("Number: " + rem));
     *     }
     * 
     *     // 以下は設置時の動作
     * 
     *     @Override
     *     public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float fx,
     *         float fy, float fz) {
     *         Block block = world.getBlock(x, y, z);
     * 
     *         if (side != 1) {
     *             return false;
     */
}
