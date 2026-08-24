package mods.defeatedcrow.test;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.registries.ForgeRegistries;

import mods.defeatedcrow.common.registry.ModRecipes;

/**
 * 登録存在性テスト（自動）。
 * wiki「AppleMilkTea ver2」の主要要素が 1.20.1 レジストリに登録されていることを検証する。
 * 対応計画: test/TEST_PLAN.md の A〜J 各カテゴリ共通の前提条件。
 *
 * 実行: gradlew runGameTestServer
 */
@GameTestHolder("dcsapplemilk")
public class RegistrationGameTests {

    private static final String EMPTY = "forge:empty3x3x3";

    private static ResourceLocation rl(String path) {
        return new ResourceLocation("defeatedcrow", path);
    }

    private void assertRegistered(GameTestHelper helper, net.minecraft.core.Registry<Block> reg,
            String path, String category) {
        helper.assertTrue(reg.containsKey(rl(path)),
                "[" + category + "] missing block: defeatedcrow:" + path);
    }

    // --- 調理装置ブロック (TEST_PLAN C) ---
    @GameTest(template = EMPTY)
    public void applianceBlocksRegistered(GameTestHelper helper) {
        List<String> ids = List.of(
                "tea_maker_next", "tea_maker_black", "empty_cup", "empty_pan_g", "filled_soup_pan",
                "ice_maker", "teppan_ii", "processor", "adv_processor", "evaporator", "incense_base");
        ids.forEach(id -> assertRegistered(helper, BuiltInRegistries.BLOCK, id, "appliance"));
        helper.succeed();
    }

    // --- 圧縮・収納ブロック (TEST_PLAN G) ---
    @GameTest(template = EMPTY)
    public void containerBlocksRegistered(GameTestHelper helper) {
        List<String> ids = List.of(
                "wood_box", "apple_box", "vegi_bag", "cardboard", "charcoal_box",
                "gunpowder_container", "egg_basket", "mushroom_box", "melon_bomb",
                "wipe_box", "wipe_box2", "mob_block", "silky_melon");
        ids.forEach(id -> assertRegistered(helper, BuiltInRegistries.BLOCK, id, "container"));
        helper.succeed();
    }

    // --- エネルギー系ブロック (TEST_PLAN E) ---
    @GameTest(template = EMPTY)
    public void energyBlocksRegistered(GameTestHelper helper) {
        List<String> ids = List.of("bat_box", "red_gel", "yuzu_bat", "gel_bat");
        ids.forEach(id -> assertRegistered(helper, BuiltInRegistries.BLOCK, id, "energy"));
        helper.succeed();
    }

    // --- 電池アイテム (TEST_PLAN E-1) ---
    @GameTest(template = EMPTY)
    public void batteryItemRegistered(GameTestHelper helper) {
        helper.assertTrue(BuiltInRegistries.ITEM.containsKey(rl("battery")),
                "missing item: defeatedcrow:battery");
        helper.assertTrue(BuiltInRegistries.ITEM.containsKey(rl("yuzu_bat")),
                "missing item: defeatedcrow:yuzu_bat");
        helper.assertTrue(BuiltInRegistries.ITEM.containsKey(rl("leaf_tea")),
                "missing item: defeatedcrow:leaf_tea");
        helper.succeed();
    }

    // --- カスタムRecipeType 11種 (TEST_PLAN C/D 共通基盤) ---
    @GameTest(template = EMPTY)
    public void customRecipeTypesRegistered(GameTestHelper helper) {
        List<RegistryObjectProbe> types = List.of(
                new RegistryObjectProbe("tea", ModRecipes.TEA_TYPE),
                new RegistryObjectProbe("ice", ModRecipes.ICE_TYPE),
                new RegistryObjectProbe("pan", ModRecipes.PAN_TYPE),
                new RegistryObjectProbe("plate", ModRecipes.PLATE_TYPE),
                new RegistryObjectProbe("processor", ModRecipes.PROCESSOR_TYPE),
                new RegistryObjectProbe("adv_processor", ModRecipes.ADV_PROCESSOR_TYPE),
                new RegistryObjectProbe("evaporator", ModRecipes.EVAPORATOR_TYPE),
                new RegistryObjectProbe("brewing", ModRecipes.BREWING_TYPE),
                new RegistryObjectProbe("fondue", ModRecipes.FONDUE_TYPE),
                new RegistryObjectProbe("chocolate", ModRecipes.CHOCOLATE_TYPE),
                new RegistryObjectProbe("charge", ModRecipes.CHARGE_TYPE));
        for (RegistryObjectProbe p : types) {
            helper.assertTrue(p.isPresent(),
                    "[recipe-type] not registered: " + p.name());
            helper.assertTrue(ForgeRegistries.RECIPE_TYPES.containsKey(rl(p.name())),
                    "[recipe-type] missing from ForgeRegistries: " + p.name());
        }
        helper.succeed();
    }

    /** RegistryObject 参照をラムダ外で安全に扱うためのプローブ。 */
    private record RegistryObjectProbe(String name, net.minecraftforge.registries.RegistryObject<?> ro) {
        boolean isPresent() {
            return ro != null && ro.isPresent();
        }
    }
}
