package mods.defeatedcrow.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.registries.RegistryObject;
import mods.defeatedcrow.common.registry.ModCreativeTabs;

/**
 * Legacy CreativeTab stub - 1.20.1 migration.
 * Original 1.7.10 CreativeTabs is replaced by DeferredRegister<CreativeModeTab> in ModCreativeTabs.
 * This stub is retained for save-compat reference; actual registration is in ModCreativeTabs.
 * See doc/creative-tabs/migration-guide.md
 */
public class CreativeTabAMTFood {
    // 1.20.1: use ModCreativeTabs.TABS instead of static CreativeTabs field
    // This class is deprecated - do not instantiate directly
    @Deprecated
    public CreativeTabAMTFood(String label) {}

    public Component getDisplayName() {
        return Component.translatable("itemGroup.defeatedcrow.applemilkFood");
    }

    public ItemStack makeIcon() {
        return ItemStack.EMPTY;
    }
}
