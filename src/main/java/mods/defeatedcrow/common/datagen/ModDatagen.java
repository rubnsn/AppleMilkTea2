package mods.defeatedcrow.common.datagen;

import net.minecraftforge.data.event.GatherDataEvent;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1 DataGenerator entry - WT-D owns recipe+advancement.
 * Registered via DCsAppleMilk#DCsAppleMilk() modBus.addListener(ModDatagen::gatherData)
 * so modId is DCsAppleMilk.MODID = "dcsapplemilk" (lowercase, 1.20.1 spec).
 * Previous @Mod.EventBusSubscriber("DCsAppleMilk") uppercase was invalid for
 * modId pattern ^[a-z][a-z0-9_]{1,63}$ - fixed in 6443a24.
 * Providers: AMTRecipeProvider + AMTAdvancementProvider (WT-D).
 * BlockState/ItemModel/Loot providers are WT-A owned and not registered here
 * (they run via their own GatherDataEvent if needed, see plan.md 9.2).
 */
public class ModDatagen {

    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        gen.addProvider(event.includeServer(), new AMTRecipeProvider(output));
        gen.addProvider(event.includeServer(), new AMTAdvancementProvider(output, lookup, helper));
    }
}
