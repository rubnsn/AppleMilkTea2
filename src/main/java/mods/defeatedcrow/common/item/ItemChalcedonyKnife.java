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
 * WT-A 1.20.1 mojmap migration for ItemChalcedonyKnife.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemChalcedonyKnife extends Item {
    public ItemChalcedonyKnife(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item;
     * 
     * import java.util.ArrayList;
     * import java.util.Random;
     * import java.util.Set;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.enchantment.EnchantmentHelper;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.world.item.ItemTool;
     * import net.minecraft.stats.StatList;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.IShearable;
     * import net.minecraftforge.common.MinecraftForge;
     * 
     * import com.google.common.collect.Sets;
     * import mods.defeatedcrow.api.events.KnifeCutEvent;
     * 
     * public class ItemChalcedonyKnife extends ItemTool {
     * 
     *     public static final Set blocksEffectiveAgainst = Sets
     *         .newHashSet(new Block[] { Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.wool });
     * 
     *     public ItemChalcedonyKnife(ToolMaterial par2) {
     *         super(3.0F, par2, blocksEffectiveAgainst);
     *         this.setMaxStackSize(1);
     *     }
     * 
     *     public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
     *         EntityLivingBase par7EntityLivingBase) {
     *         Block ID = par2World.getBlock(par3, par4, par5);
     *         int meta = par2World.getBlockMetadata(par3, par4, par5);
     * 
     *         // event
     *         KnifeCutEvent event = new KnifeCutEvent(par2World, par7EntityLivingBase, ID, meta, par3, par4, par5);
     * 
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.hasResult() && event.getResult() == Result.ALLOW) {
     *             return true;
     *         }
     * 
     *         if (event.isCanceled()) {
     *             return false;
     *         }
     * 
     *         if (ID != Blocks.leaves && ID != Blocks.web
     *             && ID != Blocks.tallgrass
     *             && ID != Blocks.vine
     *             && ID != Blocks.tripwire
     *             && ID != Blocks.pumpkin
     *             && ID != Blocks.melon_block
     *             && !(ID instanceof IShearable)) {
     *             return false;
     */
}
