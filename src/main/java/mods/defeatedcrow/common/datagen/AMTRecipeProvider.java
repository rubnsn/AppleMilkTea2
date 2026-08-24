package mods.defeatedcrow.common.datagen;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;

import mods.defeatedcrow.common.registry.ModRecipes;

/**
 * 1.20.1 datagen - generates data/defeatedcrow/recipes/** /*.json
 * Replaces old ore-dict + ShapedRecipe manual registration in DCsRecipeRegister with PackOutput + HolderLookup.
 * See doc/recipes/migration-guide.md:78 and plan-wt-d.md:4-2.
 * NBT is kept via ItemStack.CODEC (CompoundTag), DataComponents are 1.20.5+ so not used.
 */
public class AMTRecipeProvider extends RecipeProvider {

    public AMTRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // --- WT-D: SHAPED / SHAPELESS (migrated from DCsRecipeRegister addRecipe / addShapelessRecipe) ---
        // Old: RegistryHelper.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.teaMakerNext), "ingotSilver", ...))
        // New: ShapedRecipeBuilder with TagKey and Ingredient.of(TagKey)
        TagKey<Item> ingotSilver = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "ingots/silver"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.STONE) // placeholder - real is ModBlocks.TEA_MAKER_NEXT.get()
            .pattern("XYX").pattern("ZYZ").pattern("XWX")
            .define('X', Ingredient.of(ingotSilver))
            .define('Y', Items.WATER_BUCKET)
            .define('Z', Blocks.GLASS)
            .define('W', Items.BLAZE_ROD)
            .unlockedBy("has_silver", has(ingotSilver))
            .save(consumer, new ResourceLocation("defeatedcrow", "tea_maker_next_silver"));

        // Processor tier-0 example (gravel -> sand) - from ProcessorRecipeRegister
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.SAND)
            .requires(Blocks.GRAVEL)
            .unlockedBy("has_gravel", has(Blocks.GRAVEL))
            .save(consumer, new ResourceLocation("defeatedcrow", "processor/gravel_to_sand"));

        // 11 custom RecipeType datapack examples (type: defeatedcrow:tea etc.)
        // 1.20.1 Forge 47 still uses fromJson/fromNetwork (MapCodec is 1.20.5+), so ModRecipes uses DummySerializer.
        // Real JSON (data/defeatedcrow/recipes/tea/green_tea.json etc.) is emitted via custom FinishedRecipe helpers below.
        // Each custom type has DeferredRegister<RecipeType> + RecipeSerializer via ModRecipes (see ModRecipes.java:51).
        saveCustom(consumer, "tea", "tea/green_tea", customTeaJson());
        saveCustom(consumer, "ice", "ice/condensed_milk_to_ice", customIceJson());
        saveCustom(consumer, "pan", "pan/baked_apple", customPanJson());
        saveCustom(consumer, "plate", "plate/baked_apple", customPlateJson());
        saveCustom(consumer, "processor", "processor/gravel_to_sand_custom", customProcessorJson());
        saveCustom(consumer, "adv_processor", "adv_processor/iron_to_steel", customAdvProcessorJson());
        saveCustom(consumer, "evaporator", "evaporator/sugar_to_salt", customEvaporatorJson());
        saveCustom(consumer, "brewing", "brewing/young_to_aged", customBrewingJson());
        saveCustom(consumer, "fondue", "fondue/chocolate_fondue", customFondueJson());
        saveCustom(consumer, "chocolate", "chocolate/grated_apple_to_chocolate_custom", customChocolateJson());
        saveCustom(consumer, "charge", "charge/battery_charge", customChargeJson());

        // Smelting - replaces RegistryHelper.addSmelting + OreCrushRecipe TODO datapack
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.APPLE), RecipeCategory.FOOD, Items.BAKED_POTATO, 0.3F, 200)
            .unlockedBy("has_apple", has(Items.APPLE))
            .save(consumer, new ResourceLocation("defeatedcrow", "smelting/baked_apple"));

        // Fondue/Chocolate - SoupType.CHOCO via TagKey ingredient (vanilla shapeless example)
        TagKey<Item> cropCacao = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "crops/cocoa"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKIE)
            .requires(Ingredient.of(cropCacao))
            .requires(Items.SUGAR)
            .unlockedBy("has_cocoa", has(cropCacao))
            .save(consumer, new ResourceLocation("defeatedcrow", "grated_apple_to_chocolate"));
    }

    // --- helpers for 11 custom RecipeType JSON (data/defeatedcrow/recipes/<type>/*.json) ---
    private void saveCustom(Consumer<FinishedRecipe> consumer, String type, String path, JsonObject data) {
        ResourceLocation id = new ResourceLocation("defeatedcrow", path);
        consumer.accept(new FinishedRecipe() {
            @Override public void serializeRecipeData(JsonObject json) {
                // ensure type field matches DeferredRegister type (defeatedcrow:<type>)
                json.addProperty("type", "defeatedcrow:" + type);
                for (var e : data.entrySet()) {
                    json.add(e.getKey(), e.getValue());
                }
            }
            @Override public ResourceLocation getId() { return id; }
            @Override public RecipeSerializer<?> getType() {
                return switch (type) {
                    case "tea" -> ModRecipes.TEA_SERIALIZER.get();
                    case "ice" -> ModRecipes.ICE_SERIALIZER.get();
                    case "pan" -> ModRecipes.PAN_SERIALIZER.get();
                    case "plate" -> ModRecipes.PLATE_SERIALIZER.get();
                    case "processor" -> ModRecipes.PROCESSOR_SERIALIZER.get();
                    case "adv_processor" -> ModRecipes.ADV_PROCESSOR_SERIALIZER.get();
                    case "evaporator" -> ModRecipes.EVAPORATOR_SERIALIZER.get();
                    case "brewing" -> ModRecipes.BREWING_SERIALIZER.get();
                    case "fondue" -> ModRecipes.FONDUE_SERIALIZER.get();
                    case "chocolate" -> ModRecipes.CHOCOLATE_SERIALIZER.get();
                    case "charge" -> ModRecipes.CHARGE_SERIALIZER.get();
                    default -> ModRecipes.TEA_SERIALIZER.get();
                };
            }
            @Override public JsonObject serializeAdvancement() { return null; }
            @Override public ResourceLocation getAdvancementId() { return null; }
        });
    }

    private static JsonObject customTeaJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "defeatedcrow:leaf_tea");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:filled_cup"); res.addProperty("count", 1);
        json.add("ingredient", ing); json.add("result", res);
        com.google.gson.JsonArray tex = new com.google.gson.JsonArray();
        tex.add("defeatedcrow:textures/block/contents_greentea.png");
        tex.add("defeatedcrow:textures/block/contents_greentea_milk.png");
        json.add("textures", tex); json.addProperty("canMilk", true);
        return json;
    }
    private static JsonObject customIceJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "defeatedcrow:condensed_milk");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:ice_cream_block"); res.addProperty("count", 1);
        JsonObject cont = new JsonObject(); cont.addProperty("item", "minecraft:bowl");
        json.add("ingredient", ing); json.add("result", res); json.add("container", cont);
        return json;
    }
    private static JsonObject customPanJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "minecraft:apple");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:baked_apple");
        json.add("ingredient", ing); json.add("result", res); json.addProperty("cookingTime", 200);
        return json;
    }
    private static JsonObject customPlateJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "minecraft:beef");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:food_plate");
        json.add("ingredient", ing); json.add("result", res);
        return json;
    }
    private static JsonObject customProcessorJson() {
        JsonObject json = new JsonObject();
        com.google.gson.JsonArray ings = new com.google.gson.JsonArray();
        JsonObject o = new JsonObject(); o.addProperty("item", "minecraft:gravel"); ings.add(o);
        JsonObject res = new JsonObject(); res.addProperty("item", "minecraft:sand");
        JsonObject sec = new JsonObject(); sec.addProperty("item", "minecraft:flint");
        json.add("ingredients", ings); json.add("result", res); json.add("secondary", sec);
        json.addProperty("secondaryChance", 0.1); json.addProperty("tier", 0); json.addProperty("isFood", false);
        return json;
    }
    private static JsonObject customAdvProcessorJson() {
        JsonObject json = new JsonObject();
        com.google.gson.JsonArray ings = new com.google.gson.JsonArray();
        JsonObject o = new JsonObject(); o.addProperty("item", "minecraft:iron_ore"); ings.add(o);
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:ore_dust");
        json.add("ingredients", ings); json.add("result", res); json.addProperty("tier", 1);
        return json;
    }
    private static JsonObject customEvaporatorJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "minecraft:sugar");
        JsonObject res = new JsonObject(); res.addProperty("item", "minecraft:gunpowder");
        json.add("ingredient", ing); json.add("result", res); json.addProperty("time", 100);
        return json;
    }
    private static JsonObject customBrewingJson() {
        JsonObject json = new JsonObject();
        JsonObject in = new JsonObject(); in.addProperty("fluid", "defeatedcrow:shothu_young"); in.addProperty("amount", 1000);
        JsonObject out = new JsonObject(); out.addProperty("fluid", "defeatedcrow:shothu"); out.addProperty("amount", 1000);
        json.add("input", in); json.add("output", out);
        return json;
    }
    private static JsonObject customFondueJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "minecraft:milk_bucket");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:food_plate");
        json.add("ingredient", ing); json.add("result", res);
        return json;
    }
    private static JsonObject customChocolateJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "defeatedcrow:grated_apple");
        JsonObject res = new JsonObject(); res.addProperty("item", "defeatedcrow:choco_fruits");
        json.add("ingredient", ing); json.add("result", res);
        return json;
    }
    private static JsonObject customChargeJson() {
        JsonObject json = new JsonObject();
        JsonObject ing = new JsonObject(); ing.addProperty("item", "defeatedcrow:battery");
        json.add("ingredient", ing); json.addProperty("amount", 500);
        return json;
    }
}
