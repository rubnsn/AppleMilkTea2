package mods.defeatedcrow.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
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
    public static final RegistryObject<CreativeModeTab> APPLEMILK = TABS.register("applemilk",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk"))
            .icon(() -> new ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
                out.accept(ModBlocks.TEA_MAKER_NEXT.get());
                out.accept(ModBlocks.BASKET.get());
                out.accept(ModBlocks.BOWL_RACK.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_MATERIAL = TABS.register("applemilk_material",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkMaterial"))
            .icon(() -> new ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
                out.accept(ModItems.LEAF_TEA.get());
                out.accept(ModItems.LEAF_MINT.get());
                out.accept(ModItems.FOOD_TEA.get());
                out.accept(ModItems.ORE_DUST.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_FOOD = TABS.register("applemilk_food",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkFood"))
            .icon(() -> new ItemStack(ModItems.BAKED_APPLE.get())).displayItems((p, out) -> {
                out.accept(ModItems.BAKED_APPLE.get());
                out.accept(ModItems.APPLE_TART.get());
                out.accept(ModItems.CLAM.get());
                out.accept(ModBlocks.FILLED_CUP.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_CONTAINER = TABS.register("applemilk_container",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkContainer"))
            .icon(() -> new ItemStack(ModBlocks.WOOD_BOX.get())).displayItems((p, out) -> {
                out.accept(ModBlocks.WOOD_BOX.get());
                out.accept(ModBlocks.APPLE_BOX.get());
                out.accept(ModBlocks.VEGI_BAG.get());
                out.accept(ModBlocks.BASKET.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_MAGIC = TABS.register("applemilk_magic",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkMagic"))
            .icon(() -> new ItemStack(ModItems.PRINCESS_CLAM.get())).displayItems((p, out) -> {
                out.accept(ModItems.INCENSE_APPLE.get());
                out.accept(ModItems.PRINCESS_CLAM.get());
                out.accept(ModItems.YUZU_GATLING.get());
            }).build());

    private ModCreativeTabs() {}
}
