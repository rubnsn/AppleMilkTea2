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
 * WT-A 1.20.1 mojmap migration for ItemChalcedonyShears.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemChalcedonyShears extends Item {
    public ItemChalcedonyShears(Properties properties) {
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
     * import java.util.Set;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.BlockStem;
     * import net.minecraft.block.IGrowable;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.item.ItemTool;
     * import net.minecraft.nbt.NBTTagList;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.IShearable;
     * 
     * import com.google.common.collect.Sets;
     * import mods.defeatedcrow.api.plants.IRightClickHarvestable;
     * 
     * public class ItemChalcedonyShears extends ItemTool {
     * 
     *     public static final Set blocksEffectiveAgainst = Sets
     *         .newHashSet(new Block[] { Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.wool });
     * 
     *     public ItemChalcedonyShears(ToolMaterial par2) {
     *         super(3.0F, par2, blocksEffectiveAgainst);
     *         this.setMaxStackSize(1);
     *     }
     * 
     *     // 多少はハサミと同機能もある
     *     public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
     *         EntityLivingBase par7EntityLivingBase) {
     *         Block ID = par2World.getBlock(par3, par4, par5);
     *         if (ID != Blocks.leaves && ID != Blocks.web
     *             && ID != Blocks.tallgrass
     *             && ID != Blocks.vine
     *             && ID != Blocks.tripwire
     *             && ID != Blocks.pumpkin
     *             && ID != Blocks.melon_block
     *             && !(ID instanceof IShearable)) {
     *             return false;
     *         } else {
     *             return true;
     *         }
     *     }
     * 
     *     @Override
     *     public boolean func_150897_b(Block par1Block) {
     *         return par1Block == Blocks.web || par1Block == Blocks.redstone_wire || par1Block == Blocks.tripwire;
     *     }
     * 
     *     @Override
     *     public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
     *         return par2Block != Blocks.web && par2Block != Blocks.leaves
     *             ? (par2Block == Blocks.wool ? 5.0F : super.func_150893_a(par1ItemStack, par2Block))
     *             : 15.0F;
     *     }
     * 
     */
}
