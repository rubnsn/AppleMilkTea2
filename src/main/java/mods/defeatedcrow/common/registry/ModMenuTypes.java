package mods.defeatedcrow.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 MenuType registry - replaces IGuiHandler.
 * See doc/tile-entities/migration-guide.md:40
 * WT-B owns (5 GUI: iceMaker, processor, advProcessor, evaporator, batBox).
 */
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "defeatedcrow");

    // --- WT-B: GUI MENUS ---
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerIceMaker>> ICE_MAKER = MENUS.register("ice_maker",
        () -> IForgeMenuType.create((id, inv, buf) -> {
            BlockPos pos = buf.readBlockPos();
            if (inv.player.level().getBlockEntity(pos) instanceof mods.defeatedcrow.common.tile.appliance.TileIceMaker be) {
                return new mods.defeatedcrow.common.tile.appliance.ContainerIceMaker(id, inv, be);
            }
            return new mods.defeatedcrow.common.tile.appliance.ContainerIceMaker(id, inv, pos);
        }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerProcessor>> PROCESSOR = MENUS.register("processor",
        () -> IForgeMenuType.create((id, inv, buf) -> {
            BlockPos pos = buf.readBlockPos();
            if (inv.player.level().getBlockEntity(pos) instanceof mods.defeatedcrow.common.tile.appliance.TileProcessor be) {
                return new mods.defeatedcrow.common.tile.appliance.ContainerProcessor(id, inv, be);
            }
            return new mods.defeatedcrow.common.tile.appliance.ContainerProcessor(id, inv, pos);
        }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor>> ADV_PROCESSOR = MENUS.register("adv_processor",
        () -> IForgeMenuType.create((id, inv, buf) -> {
            BlockPos pos = buf.readBlockPos();
            if (inv.player.level().getBlockEntity(pos) instanceof mods.defeatedcrow.common.tile.appliance.TileAdvProcessor be) {
                return new mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor(id, inv, be);
            }
            return new mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor(id, inv, pos);
        }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerEvaporator>> EVAPORATOR = MENUS.register("evaporator",
        () -> IForgeMenuType.create((id, inv, buf) -> {
            BlockPos pos = buf.readBlockPos();
            if (inv.player.level().getBlockEntity(pos) instanceof mods.defeatedcrow.common.tile.appliance.TileEvaporator be) {
                return new mods.defeatedcrow.common.tile.appliance.ContainerEvaporator(id, inv, be);
            }
            return new mods.defeatedcrow.common.tile.appliance.ContainerEvaporator(id, inv, pos);
        }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.energy.ContainerBatBox>> BAT_BOX = MENUS.register("bat_box",
        () -> IForgeMenuType.create((id, inv, buf) -> {
            BlockPos pos = buf.readBlockPos();
            if (inv.player.level().getBlockEntity(pos) instanceof mods.defeatedcrow.common.tile.energy.TileChargerDevice be) {
                return new mods.defeatedcrow.common.tile.energy.ContainerBatBox(id, inv, be);
            }
            return new mods.defeatedcrow.common.tile.energy.ContainerBatBox(id, inv, pos);
        }));

    private ModMenuTypes() {}
}
