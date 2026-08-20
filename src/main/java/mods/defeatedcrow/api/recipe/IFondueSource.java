package mods.defeatedcrow.api.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import mods.defeatedcrow.api.appliance.SoupType;

public interface IFondueSource {

    SoupType beforeType();

    ArrayList<ItemStack> getProcessedInput();

    Object getInput();

    SoupType afterType();

    public boolean matches(ItemStack items);

}
