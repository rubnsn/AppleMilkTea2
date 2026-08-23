package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.potion.PotionGetter;

public class RegisterManager {

    public static void load() {
        RecipeRegisterManager.teaRecipe = new TeaRecipeRegister();
        RecipeRegisterManager.iceRecipe = new IceRecipeRegister();
        RecipeRegisterManager.processorRecipe = new ProcessorRecipeRegister();
        RecipeRegisterManager.evaporatorRecipe = new EvaporatorRecipeRegister();
        RecipeRegisterManager.panRecipe = new PanRecipeRegister();
        RecipeRegisterManager.chocoRecipe = new ChocolateRecipe();
        RecipeRegisterManager.fondueRecipe = new FondueRecipeRegister();
        RecipeRegisterManager.plateRecipe = new PlateRecipeRegister();

        ChargeItemManager.chargeItem = new ChargeItemRegister();
        RecipeRegisterManager.slagLoot = new SlagResultLoot();

        // 1.20.1: AMTPotionManager(api凍結) 廃止、PotionGetter への直接移行
        PotionGetter.initialize();
    }

    private RegisterManager() {}

}
