package mods.defeatedcrow.api;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

/**
 * 1.20.1: リフレクションによる static フィールド参照は廃止。 <br>
 * BuiltInRegistries / RegistryObject 経由の取得に置換した互換スタブ。 <br>
 * 推奨: <code>ModItems.LEAF_TEA.get()</code> のような RegistryObject 直接参照 (doc/api/migration-guide.md)。
 */
@Deprecated
public class ItemAPI {

    public static final Logger APILogger = LogManager.getLogger("AppleMilkTeaAPI");

    /** registry namespace used by this mod ("defeatedcrow"). */
    private static final String MODID = "defeatedcrow";

    /**
     * 登録名からアイテムを取得する。旧meta引数はdamage値として扱う。
     */
    public static ItemStack getItem(String itemName, int meta) {
        try {
            ResourceLocation rl = new ResourceLocation(MODID, itemName.toLowerCase(Locale.ROOT));
            Item item = BuiltInRegistries.ITEM.get(rl);
            if (item == null || item == Items.AIR) {
                APILogger.info("Failed to get item: " + itemName);
                return ItemStack.EMPTY;
            }
            ItemStack stack = new ItemStack(item);
            if (meta > 0) {
                stack.setDamageValue(meta);
            }
            return stack;
        } catch (Exception e) {
            APILogger.info("Failed to get item: " + itemName);
            return ItemStack.EMPTY;
        }
    }

    /**
     * 登録名からブロックの BlockItem を取得する。旧meta引数はdamage値として扱う。
     */
    public static ItemStack getBlock(String blockName, int meta) {
        try {
            ResourceLocation rl = new ResourceLocation(MODID, blockName.toLowerCase(Locale.ROOT));
            Block block = BuiltInRegistries.BLOCK.get(rl);
            if (block == null || block.asItem() == Items.AIR) {
                APILogger.info("Failed to get block: " + blockName);
                return ItemStack.EMPTY;
            }
            ItemStack stack = new ItemStack(block.asItem());
            if (meta > 0) {
                stack.setDamageValue(meta);
            }
            return stack;
        } catch (Exception e) {
            APILogger.info("Failed to get block: " + blockName);
            return ItemStack.EMPTY;
        }
    }
}

