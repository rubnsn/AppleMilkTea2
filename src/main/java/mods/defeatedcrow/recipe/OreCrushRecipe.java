package mods.defeatedcrow.recipe;

import java.util.ArrayList;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import mods.defeatedcrow.handler.TagHelper;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;

/*
 * 縺薙％縺ｧ縺ｯ莉邦OD縺ｮ驩ｱ遏ｳ霎樊嶌逋ｻ骭ｲ蜀・ｮｹ繧定ｵｰ譟ｻ縺励・ * 繧ｯ繝ｩ繝・す繝｣縺ｸ縺ｮ邊臥輔Ξ繧ｷ繝皮匳骭ｲ繧・∝･・ｦ吶↑繝懊ち縺九ｉ縺ｮ繝峨Ο繝・・逋ｻ骭ｲ繧定｡後≧縲・ */
public class OreCrushRecipe {

    private OreCrushRecipe() {}

    // 蝨溽・55%
    public static ArrayList<ItemStack> tier1 = new ArrayList<ItemStack>();
    // 驩ｱ遏ｳ繝翫ご繝・ヨ 30%
    public static ArrayList<ItemStack> tier2 = new ArrayList<ItemStack>();
    // 驩ｱ遏ｳ遐・10%
    public static ArrayList<ItemStack> tier3 = new ArrayList<ItemStack>();
    // 繧ｸ繧ｧ繝 4%
    public static ArrayList<ItemStack> tier4 = new ArrayList<ItemStack>();
    // 繝ｬ繧｢繧｢繧､繝・Β 1%
    public static ArrayList<ItemStack> tier5 = new ArrayList<ItemStack>();

    public static void searchOreName() {
        String[] ores1 = new String[] { "Iron", "Tin", "Copper", "Zinc" };
        String[] ores2 = new String[] { "Silver", "Lead", "Gold", "Redstone" };
        String[] ores3 = new String[] { "Nickel", "Platinum", "Magnetite" };
        String[] gems = new String[] { "Coal", "Quartz" };
        String[] gems2 = new String[] { "Diamond", "Ruby", "Sapphire", "Peridot", "Emerald" };

        int[] d = PropertyHandler.getDustGen();

        // 縺昴・1
        for (int i = 0; i < ores1.length; i++) {
            String ore = "ore" + ores1[i];
            ItemStack nugget = null;
            ItemStack dust = null;

            if (TagHelper.getTagItems("nugget" + ores1[i]) != null && !TagHelper.getTagItems("nugget" + ores1[i])
                .isEmpty())
                nugget = TagHelper.getTagItems("nugget" + ores1[i])
                    .get(0);
            if (TagHelper.getTagItems("dust" + ores1[i]) != null && !TagHelper.getTagItems("dust" + ores1[i])
                .isEmpty())
                dust = TagHelper.getTagItems("dust" + ores1[i])
                    .get(0);

            if (TagHelper.getTagItems(ore) != null && !TagHelper.getTagItems(ore)
                .isEmpty() && dust != null) {
                RecipeRegisterManager.processorRecipe.addRecipe(
                    new ItemStack(dust.getItem(), d[0], dust.getItemDamage()),
                    false,
                    1,
                    new ItemStack(dust.getItem(), d[1], dust.getItemDamage()),
                    0.5F,
                    new Object[] { ore });
            }

            if (nugget != null) {
                tier2.add(nugget);
            }
        }

        // 縺昴・2
        for (int i = 0; i < ores2.length; i++) {
            String ore = "ore" + ores2[i];
            ItemStack dust = null;
            ItemStack ingot = null;
            if (TagHelper.getTagItems("dust" + ores2[i]) != null && !TagHelper.getTagItems("dust" + ores2[i])
                .isEmpty())
                dust = TagHelper.getTagItems("dust" + ores2[i])
                    .get(0);

            if (TagHelper.getTagItems(ore) != null && !TagHelper.getTagItems(ore)
                .isEmpty() && dust != null) {
                RecipeRegisterManager.processorRecipe.addRecipe(
                    new ItemStack(dust.getItem(), d[0], dust.getItemDamage()),
                    false,
                    2,
                    new ItemStack(DCsAppleMilk.strangeSlag, 1, 0),
                    0.5F,
                    new Object[] { ore });
            }

            if (TagHelper.getTagItems("ingot" + ores2[i]) != null && !TagHelper.getTagItems("ingot" + ores2[i])
                .isEmpty()) {
                ingot = TagHelper.getTagItems("ingot" + ores2[i])
                    .get(0);
            }

            if (dust != null) {
                tier3.add(dust);
            }
        }

        // 縺昴・3
        for (int i = 0; i < ores3.length; i++) {
            String ore = "ore" + ores3[i];
            ItemStack dust = null;
            ItemStack ingot = null;
            if (TagHelper.getTagItems("dust" + ores3[i]) != null && !TagHelper.getTagItems("dust" + ores3[i])
                .isEmpty())
                dust = TagHelper.getTagItems("dust" + ores3[i])
                    .get(0);

            if (TagHelper.getTagItems(ore) != null && !TagHelper.getTagItems(ore)
                .isEmpty() && dust != null) {
                RecipeRegisterManager.processorRecipe.addRecipe(
                    new ItemStack(dust.getItem(), d[0], dust.getItemDamage()),
                    false,
                    3,
                    new ItemStack(DCsAppleMilk.strangeSlag, 1, 0),
                    0.5F,
                    new Object[] { ore });
            }

            if (TagHelper.getTagItems("ingot" + ores3[i]) != null && !TagHelper.getTagItems("ingot" + ores3[i])
                .isEmpty()) {
                ingot = TagHelper.getTagItems("ingot" + ores3[i])
                    .get(0);
            }

            if (dust != null) {
                tier3.add(dust);
            }
        }

        // 縺昴・4
        for (int i = 0; i < gems.length; i++) {
            String ore = "ore" + gems[i];
            ItemStack gem = null;
            if (TagHelper.getTagItems("gem" + gems[i]) != null && !TagHelper.getTagItems("gem" + gems[i])
                .isEmpty())
                gem = TagHelper.getTagItems("gem" + gems[i])
                    .get(0);

            if (TagHelper.getTagItems(ore) != null && !TagHelper.getTagItems(ore)
                .isEmpty() && gem != null) {
                RecipeRegisterManager.processorRecipe.addRecipe(
                    new ItemStack(gem.getItem(), d[0], gem.getItemDamage()),
                    false,
                    1,
                    new ItemStack(gem.getItem(), 1, gem.getItemDamage()),
                    0.5F,
                    new Object[] { ore });
            }

            if (gem != null) {
                tier2.add(gem);
            }
        }

        // 縺昴・5
        for (int i = 0; i < gems2.length; i++) {
            String ore = "ore" + gems2[i];
            ItemStack gem = null;
            if (TagHelper.getTagItems("gem" + gems2[i]) != null && !TagHelper.getTagItems("gem" + gems2[i])
                .isEmpty())
                gem = TagHelper.getTagItems("gem" + gems2[i])
                    .get(0);

            if (TagHelper.getTagItems(ore) != null && !TagHelper.getTagItems(ore)
                .isEmpty() && gem != null) {
                RecipeRegisterManager.processorRecipe.addRecipe(
                    new ItemStack(gem.getItem(), d[0], gem.getItemDamage()),
                    false,
                    3,
                    new ItemStack(DCsAppleMilk.strangeSlag, 1, 0),
                    0.5F,
                    new Object[] { ore });
            }

            if (gem != null) {
                tier4.add(gem);
            }
        }

        // 谿九ｊ縺ｮ繝懊ち譫繧貞沂繧√ｋ
        tier1.add(new ItemStack(Items.bone));
        tier1.add(new ItemStack(Items.clay_ball));
        tier1.add(new ItemStack(Blocks.dirt));
        tier1.add(new ItemStack(Blocks.cobblestone));
        tier1.add(new ItemStack(Blocks.gravel));

        tier2.add(new ItemStack(Blocks.end_stone));
        tier2.add(new ItemStack(Blocks.ice));
        tier2.add(new ItemStack(Items.flint));

        tier3.add(new ItemStack(Items.glowstone_dust));

        tier4.add(new ItemStack(DCsAppleMilk.chalcedony));
        tier4.add(new ItemStack(Items.ender_pearl));

        tier5.add(new ItemStack(Items.spawn_egg, 1, 120));
        tier5.add(new ItemStack(DCsAppleMilk.fossilScale));

        // 繝懊ち螻ｱ險ｭ螳壻ｻ･螟悶・dust蜿門ｾ・        // ingot縺悟ｭ伜惠縺励↑縺・→辟ｼ縺上Ξ繧ｷ繝斐ｂ蟄伜惠縺励↑縺・        // 1.20.1: 譌ｧGameRegistry.addSmelting縺ｯ蟒・ｭ｢縲り｣ｽ骭ｬ繝ｬ繧ｷ繝斐・ data/dcsapplemilk/recipes/smelting/*.json 縺ｸ遘ｻ陦後☆繧九・        // TODO(datapack): oreDust meta i 竊・ingotX 縺ｮ陬ｽ骭ｬ繝ｬ繧ｷ繝寧SON繧堤函謌舌☆繧具ｼ育┌縺代ｌ縺ｰ驩・う繝ｳ繧ｴ繝・ヨ縺ｸ・峨・
        String[] ores4 = new String[] { "Iron", "Tin", "Copper", "Silver", "Lead", "Gold", "Nickel", "Platinum" };
        for (int i = 0; i < ores4.length; i++) {
            if (TagHelper.getTagItems("ingot" + ores4[i]) != null && !TagHelper.getTagItems("ingot" + ores4[i])
                .isEmpty()) {
                AMTLogger.debugInfo("smelting recipe (datapack pending): oreDust:" + i + " -> ingot" + ores4[i]);
            } else {
                AMTLogger.debugInfo("smelting recipe (datapack pending): oreDust:" + i + " -> iron_ingot");
            }
        }

    }

}

