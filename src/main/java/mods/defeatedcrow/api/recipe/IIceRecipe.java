package mods.defeatedcrow.api.recipe;

import net.minecraft.world.item.ItemStack;

public abstract interface IIceRecipe {

    public abstract ItemStack getInput();

    public abstract ItemStack getOutput();

    public abstract ItemStack getContainer();

}
