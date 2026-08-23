package mods.defeatedcrow.common;

import net.minecraft.world.item.ItemStack;

/**
 * 1.20.1 migration stub: legacy 1.7 crafting recipes moved to datapack.
 * Original 2000+ lines of RegistryHelper/ShapedRecipe code removed.
 * New recipes are in src/main/resources/data/defeatedcrow/recipes/** via ModRecipes + datagen
 * (AMTRecipeProvider generates src/generated/resources/data/defeatedcrow/recipes/** then
 * merges to src/main/resources - see build.gradle:50 --output/--existing).
 * This stub keeps the class for backward compat (called from CommonProxy) but does nothing.
 * No call sites remain (grep -r "DCsRecipeRegister" -> 0 in src/main/java after 6443a24);
 * will be deleted when 0 callers confirmed - see plan.md 10.4 P2.
 */
public class DCsRecipeRegister {

    public void addRecipe() {
        // No-op: recipes now datapack
    }

    static void addContainerRecipe() {}
    static void addTablewareRecipe() {}
    static void addGraterRecipe() {}
    static void addCocktailRecipe() {}
    static void addFoodRecipe() {}
    static void addMaterials() {}
    static void addChalcedony() {}
    static void addPrincess() {}
    static void addBottle() {}
    static void addCordial() {}
    static void addCharms() {}
    static void addMachines() {}
    static void addSmelting() {}

    // Keep for compat: some old code may call these directly
    public static void register() {}
}
