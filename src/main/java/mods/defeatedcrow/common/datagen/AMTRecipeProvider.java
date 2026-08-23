package mods.defeatedcrow.common.datagen;

import java.util.function.Consumer;

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
import net.minecraft.world.level.block.Blocks;

/**
 * 1.20.1 datagen — generates data/defeatedcrow/recipes/**/*.json
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

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.STONE) // placeholder — real is ModBlocks.TEA_MAKER_NEXT.get()
            .pattern("XYX").pattern("ZYZ").pattern("XWX")
            .define('X', Ingredient.of(ingotSilver))
            .define('Y', Items.WATER_BUCKET)
            .define('Z', Blocks.GLASS)
            .define('W', Items.BLAZE_ROD)
            .unlockedBy("has_silver", has(ingotSilver))
            .save(consumer, new ResourceLocation("defeatedcrow", "tea_maker_next_silver"));

        // Processor tier-0 example (gravel -> sand) — from ProcessorRecipeRegister
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.SAND)
            .requires(Blocks.GRAVEL)
            .unlockedBy("has_gravel", has(Blocks.GRAVEL))
            .save(consumer, new ResourceLocation("defeatedcrow", "processor/gravel_to_sand"));

        // 11 custom RecipeType datapack examples (type: defeatedcrow:tea etc.)
        // Real custom recipes use ModRecipes TEA_SERIALIZER codec via MapCodec<TeaRecipe>.
        // JSON (data/defeatedcrow/recipes/tea/green_tea.json etc.) will be emitted by custom TeaRecipeBuilder.
        // For now vanilla placeholders prove PackOutput wiring. See plan-wt-d.md MapCodec plan.

        // Smelting — replaces RegistryHelper.addSmelting + OreCrushRecipe TODO datapack
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.APPLE), RecipeCategory.FOOD, Items.BAKED_POTATO, 0.3F, 200)
            .unlockedBy("has_apple", has(Items.APPLE))
            .save(consumer, new ResourceLocation("defeatedcrow", "smelting/baked_apple"));

        // Fondue/Chocolate — SoupType.CHOCO via TagKey ingredient
        TagKey<Item> cropCacao = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "crops/cocoa"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKIE)
            .requires(Ingredient.of(cropCacao))
            .requires(Items.SUGAR)
            .unlockedBy("has_cocoa", has(cropCacao))
            .save(consumer, new ResourceLocation("defeatedcrow", "grated_apple_to_chocolate"));
    }
}
