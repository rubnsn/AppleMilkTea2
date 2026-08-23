package mods.defeatedcrow.plugin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fml.ModList;

import mods.defeatedcrow.handler.Util;

/**
 * PPC (PeacePlantCraft) 連携。
 *
 * 1.20.1対応版なし (Omit確定)。外部MOD APIへの直接依存はなく文字列lookupのみのため、
 * ModListガード付きの発見処理として維持する。
 * TagKey.registerOre による辞書登録は廃止 (TagKey/datapack 側へ移管)。
 */
public class LoadPPCPlugin {

    // TODO: 実modID要確認 (旧 Util.getModItem の第1引数はmod名)
    private static final String PPC_MODID = "peaceplantcraft";

    private LoadPPCPlugin() {}

    public static void load() {
        if (!ModList.get().isLoaded(PPC_MODID)) {
            return;
        }

        registerCrop("DAIKON", "PPCradish");
        registerCrop("PIMAN", "PPCgreenpepper");
        registerCrop("TOMATO", "PPCtomato");
        registerCrop("KOME", "PPCrice");
        registerCrop("RETASU", "PPClettuce");
        registerCrop("KABU", "PPCturnip");
        registerCrop("HAKUSAI", "PPCnapacabbage");
        registerCrop("TAMANEGI", "PPConion");
        registerCrop("NINNIKU", "PPCgarlic");
        registerCrop("EDAMAME", "PPsoy");
        registerCrop("NEGI", "PPleek");
        registerCrop("KYABETSU", "PPcabbage");
        registerCrop("KYURI", "PPcucumber");
        registerCrop("NASU", "PPeggplant");
    }

    private static void registerCrop(String itemName, String alias) {
        Item item = Util.getModItem("peaceplantcraft", itemName);
        if (item == null) return;
        ItemStack register = new ItemStack(item);
        LoadModHandler.registerModItems(alias, register);
    }

}
