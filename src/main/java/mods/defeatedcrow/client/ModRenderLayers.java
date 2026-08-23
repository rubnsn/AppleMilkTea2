package mods.defeatedcrow.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.registry.ModBlocks;

/**
 * 1.20.1 RenderType registration — replaces ISBRH cutout layer setup (ClientProxy, 44 ISBRH blocks).
 *
 * NOTE for WT-A: the RegistryObject field names below MUST match the fields declared in
 * {@link ModBlocks} (comment-section convention from ModClientEvents.java:52-59).
 * If WT-A names any field differently (e.g. TEPPAN vs TEPPAN_II), adjust here accordingly.
 * Every entry is rendered with {@link RenderType#cutout()} because the old ISBRH models used
 * alpha-tested textures (plants, liquid surfaces, lattice fences).
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderLayers {

    private static void cutout(RegistryObject<?> block) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // --- WT-C: RenderType cutout for 44 ISBRH blocks ---
            cutout(ModBlocks.TEA_MAKER_NEXT);
            cutout(ModBlocks.EMPTY_CUP);
            cutout(ModBlocks.TEA_TREE);
            cutout(ModBlocks.FILLED_CUP);
            cutout(ModBlocks.BOWL);
            cutout(ModBlocks.BOWL_RACK);
            cutout(ModBlocks.C_LAMP);
            cutout(ModBlocks.BASKET);
            cutout(ModBlocks.FOOD_PLATE);
            cutout(ModBlocks.TEPPAN_II);
            cutout(ModBlocks.BOWL_JP);
            cutout(ModBlocks.CUP_SUMMER);
            cutout(ModBlocks.CHOPSTICKS_BOX);
            cutout(ModBlocks.EGG_BASKET);
            cutout(ModBlocks.KINOKO);
            cutout(ModBlocks.CHOCO_PAN);
            cutout(ModBlocks.TEA_MAKER_BLACK);
            cutout(ModBlocks.PROCESSOR);
            cutout(ModBlocks.WIPE_BOX);
            cutout(ModBlocks.ICE_MAKER);
            cutout(ModBlocks.ICE_CREAM);
            cutout(ModBlocks.DIAL);
            cutout(ModBlocks.COCKTAIL);
            cutout(ModBlocks.LARGE_BOTTLE);
            cutout(ModBlocks.CASSIS_TREE);
            cutout(ModBlocks.CORDIAL);
            cutout(ModBlocks.ALCOHOL_CUP);
            cutout(ModBlocks.EVAPORATOR);
            cutout(ModBlocks.JAW_CRUSHER);
            cutout(ModBlocks.C_PANEL);
            cutout(ModBlocks.INCENSE_BASE);
            cutout(ModBlocks.YUZU_BAT);
            cutout(ModBlocks.GEL_BAT);
            cutout(ModBlocks.CHARGER_DEVICE);
            cutout(ModBlocks.FLOWER_POT);
            cutout(ModBlocks.YUZU_FENCE);
            cutout(ModBlocks.E_HANDLE);
            cutout(ModBlocks.WOOD_PANEL);
            cutout(ModBlocks.C_LAMP_OP);
            cutout(ModBlocks.SOUP_PAN_FILLED);
            cutout(ModBlocks.CONTAINER_W_BOTTLE);
            cutout(ModBlocks.FLOWER_VASE);
            cutout(ModBlocks.HEDGE);
        });
    }
}
