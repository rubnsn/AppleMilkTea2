package mods.defeatedcrow.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 CreativeModeTab registry — replaces CreativeTabs (1.19.3+ Registry化).
 * See doc/creative-tabs/migration-guide.md:1
 * WT-A owns displayItems. Bootstrap owns registration shell.
 */
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow");

    // --- WT-A: TABS (applemilk, applemilkMaterial, applemilkFood, applemilkContainer, applemilkMagic) ---
    // public static final RegistryObject<CreativeModeTab> APPLEMILK = TABS.register("applemilk",
    //     () -> CreativeModeTab.builder().title(net.minecraft.network.chat.Component.translatable("itemGroup.applemilk"))
    //         .icon(() -> new net.minecraft.world.item.ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
    //             out.accept(ModBlocks.TEA_MAKER_NEXT.get());
    //         }).build());

    private ModCreativeTabs() {}
}
