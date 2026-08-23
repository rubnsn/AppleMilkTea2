package mods.defeatedcrow.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.registry.ModBlockEntities;
import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModCreativeTabs;
import mods.defeatedcrow.common.registry.ModEntities;
import mods.defeatedcrow.common.registry.ModFluidTypes;
import mods.defeatedcrow.common.registry.ModFluids;
import mods.defeatedcrow.common.registry.ModItems;
import mods.defeatedcrow.common.registry.ModMenuTypes;
import mods.defeatedcrow.common.registry.ModMobEffects;

/**
 * 1.20.1 entry point — DeferredRegister aggregation.
 * 1.7.10 static Block/Item/Fluid/modelXXX fields, SidedProxy, GameRegistry, Tags.VERSION は削除。
 * 各登録は common/registry/Mod*.java の DeferredRegister に委譲 (doc/build.md:22, doc/blocks/migration-guide.md:174)。
 */
@Mod(DCsAppleMilk.MODID)
public class DCsAppleMilk {

    public static final String MODID = "DCsAppleMilk";
    public static final String MOD_NAME = "Apple&Milk&Tea!";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public DCsAppleMilk() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFluids.FLUIDS.register(modBus);
        ModFluidTypes.FLUID_TYPES.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModMenuTypes.MENUS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        ModMobEffects.MOB_EFFECTS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DCsConfig.COMMON_SPEC, "defeatedcrow-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DCsConfig.CLIENT_SPEC, "defeatedcrow-client.toml");

        modBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Apple&Milk&Tea! 1.20.1 bootstrap — DeferredRegister wired (Forge 47.3 / FG6 / mojmap / JDK17)");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DCsConfig.sync();
            LOGGER.debug("Common setup — config synced");
        });
    }

    public int getMajorVersion() {
        return 2;
    }

    public int getMinorVersion() {
        return 9;
    }

    public String getRivision() {
        return "m";
    }

    public String getModName() {
        return MOD_NAME;
    }

    public String getModID() {
        return MODID;
    }
}
