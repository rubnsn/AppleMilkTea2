package mods.defeatedcrow.recipe;

import net.minecraft.world.item.ItemStack;

/**
 * 1.20.1 migration stub: legacy maker recipes (tea/ice/pan etc.) moved to ModRecipes + datapack.
 * Original used ItemStack with damage (e.g. new ItemStack(Items.dye,1,3)) and Tags.
 * New system uses RecipeType/Serializer/MapCodec and data/recipes/*.json
 * This stub preserves the class for calls from CommonProxy but does nothing.
 */
public class RegisterMakerRecipe {

    public void registerTea() {}
    public void registerIce() {}
    public void registerChargeItem() {}
    public void registerPan() {}
    public void registerProcessor() {}
    public void registerEvaporator() {}
    public void registerBrewing() {}
    public void addKelpRecipe() {}
    public static void registerPlate() {}
    public static void registerChocolate() {}
    public static void registerSoupSource() {}
    public static void testRecipe() {}
}
