package mods.defeatedcrow.api.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import mods.defeatedcrow.api.appliance.SoupType;

public interface IFondueRecipe {

    Object getInput();

    ArrayList<ItemStack> getProcessedInput();

    ItemStack getOutput();

    SoupType getType();

    public boolean matches(ItemStack items);

}
