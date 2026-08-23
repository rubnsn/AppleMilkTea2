package mods.defeatedcrow.common.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 MenuType registry — replaces IGuiHandler.
 * See doc/tile-entities/migration-guide.md:40
 * WT-B owns (5 GUI: iceMaker, processor, advProcessor, evaporator, batBox).
 */
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "defeatedcrow");

    // --- WT-B: GUI MENUS ---
    // public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerIceMaker>> ICE_MAKER = MENUS.register("ice_maker",
    //     () -> IForgeMenuType.create((id, inv, buf) -> new mods.defeatedcrow.common.tile.appliance.ContainerIceMaker(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()))));

    private ModMenuTypes() {}
}
