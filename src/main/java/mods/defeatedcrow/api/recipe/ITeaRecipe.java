package mods.defeatedcrow.api.recipe;

import net.minecraft.world.item.ItemStack;

public interface ITeaRecipe {

    ItemStack getInput();

    ItemStack getOutput();

    ItemStack getOutputMilk();

    String getTex();

    String getMilkTex();

}
