package mods.defeatedcrow.common.registry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "defeatedcrow");
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerIceMaker>> ICE_MAKER = MENUS.register("ice_maker",
        () -> IForgeMenuType.create((id, inv, buf) -> { BlockPos p = buf.readBlockPos(); var be = inv.player.level().getBlockEntity(p); if(be instanceof mods.defeatedcrow.common.tile.appliance.TileIceMaker t) return new mods.defeatedcrow.common.tile.appliance.ContainerIceMaker(id, inv, t); return new mods.defeatedcrow.common.tile.appliance.ContainerIceMaker(id, inv); }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerProcessor>> PROCESSOR = MENUS.register("processor",
        () -> IForgeMenuType.create((id, inv, buf) -> { BlockPos p = buf.readBlockPos(); var be = inv.player.level().getBlockEntity(p); if(be instanceof mods.defeatedcrow.common.tile.appliance.TileProcessor t) return new mods.defeatedcrow.common.tile.appliance.ContainerProcessor(id, inv, t); return new mods.defeatedcrow.common.tile.appliance.ContainerProcessor(id, inv); }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor>> ADV_PROCESSOR = MENUS.register("adv_processor",
        () -> IForgeMenuType.create((id, inv, buf) -> { BlockPos p = buf.readBlockPos(); var be = inv.player.level().getBlockEntity(p); if(be instanceof mods.defeatedcrow.common.tile.appliance.TileAdvProcessor t) return new mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor(id, inv, t); return new mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor(id, inv); }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.appliance.ContainerEvaporator>> EVAPORATOR = MENUS.register("evaporator",
        () -> IForgeMenuType.create((id, inv, buf) -> { BlockPos p = buf.readBlockPos(); var be = inv.player.level().getBlockEntity(p); if(be instanceof mods.defeatedcrow.common.tile.appliance.TileEvaporator t) return new mods.defeatedcrow.common.tile.appliance.ContainerEvaporator(id, inv, t); return new mods.defeatedcrow.common.tile.appliance.ContainerEvaporator(id, inv); }));
    public static final RegistryObject<MenuType<mods.defeatedcrow.common.tile.energy.ContainerBatBox>> BAT_BOX = MENUS.register("bat_box",
        () -> IForgeMenuType.create((id, inv, buf) -> { BlockPos p = buf.readBlockPos(); var be = inv.player.level().getBlockEntity(p); if(be instanceof mods.defeatedcrow.common.tile.energy.TileChargerBase t) return new mods.defeatedcrow.common.tile.energy.ContainerBatBox(id, inv, t); return new mods.defeatedcrow.common.tile.energy.ContainerBatBox(id, inv); }));
}
