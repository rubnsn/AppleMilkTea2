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
 * WT-A 1.20.1 mojmap migration for ItemChalcedonyHammer.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemChalcedonyHammer extends Item {
    public ItemChalcedonyHammer(Properties properties) {
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
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.enchantment.Enchantment;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemPickaxe;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.nbt.NBTTagList;
     * import net.minecraft.world.World;
     * 
     * import com.google.common.collect.Sets;
     * import mods.defeatedcrow.common.AMTLogger;
     * 
     * public class ItemChalcedonyHammer extends ItemPickaxe {
     * 
     *     /** an array of the blocks this pickaxe is effective against * /
     *     public static final Set blocksEffectiveAgainst = Sets.newHashSet(
     *         new Block[] { Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone,
     *             Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block,
     *             Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore,
     *             Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail,
     *             Blocks.golden_rail, Blocks.activator_rail });
     * 
     *     public ItemChalcedonyHammer(ToolMaterial par2EnumToolMaterial) {
     *         super(par2EnumToolMaterial);
     *         this.setHarvestLevel("pickaxe", 2);
     *     }
     * 
     *     @Override
     *     public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6,
     *         EntityLivingBase par7EntityLivingBase) {
     *         if (par3 != Blocks.ice) {
     *             return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
     *         } else {
     *             return true;
     *         }
     *     }
     * 
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
     *         int par5, int par6, int par7, float par8, float par9, float par10) {
     *         Block i1 = par3World.getBlock(par4, par5, par6);
     *         int meta = par3World.getBlockMetadata(par4, par5, par6);
     *         if (par1ItemStack == null || i1 == null) return false;
     * 
     *         int damage = 0;
     *         boolean deco = false;
     *         boolean brk = false;
     *         int eff = 0;
     *         int unb = 0;
     * 
     *         // エンチャントのチェック
     *         if (par1ItemStack.hasTagCompound()) {
     */
}
