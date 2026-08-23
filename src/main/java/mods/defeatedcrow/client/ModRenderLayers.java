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
 * 1.20.1 RenderType registration - replaces ISBRH cutout layer setup (ClientProxy, 44 ISBRH blocks).
 * See doc/blocks/migration-guide.md
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderLayers {

    private static void cutout(RegistryObject<? extends net.minecraft.world.level.block.Block> block) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            cutout(ModBlocks.TEA_MAKER_NEXT);
            cutout(ModBlocks.EMPTY_CUP);
            cutout(ModBlocks.TEA_TREE);
            cutout(ModBlocks.FILLED_CUP);
            cutout(ModBlocks.BOWL_BLOCK);
            cutout(ModBlocks.BOWL_RACK);
            cutout(ModBlocks.CHALCEDONY_LAMP);
            cutout(ModBlocks.BASKET);
            cutout(ModBlocks.FOOD_PLATE);
            cutout(ModBlocks.TEPPAN_II);
            cutout(ModBlocks.BOWL_JP);
            cutout(ModBlocks.FILLED_CUP2);
            cutout(ModBlocks.CHOPSTICKS_BOX);
            cutout(ModBlocks.EGG_BASKET);
            cutout(ModBlocks.MUSH_BOX);
            cutout(ModBlocks.CHOCO_BLOCK);
            cutout(ModBlocks.TEA_MAKER_BLACK);
            cutout(ModBlocks.PROCESSOR);
            cutout(ModBlocks.WIPE_BOX);
            cutout(ModBlocks.ICE_MAKER);
            cutout(ModBlocks.ICE_CREAM);
            cutout(ModBlocks.ROTARY_DIAL);
            cutout(ModBlocks.COCKTAIL);
            cutout(ModBlocks.LARGE_BOTTLE);
            cutout(ModBlocks.CASSIS_TREE);
            cutout(ModBlocks.CORDIAL);
            cutout(ModBlocks.ALCOHOL_CUP);
            cutout(ModBlocks.EVAPORATOR);
            cutout(ModBlocks.ADV_PROCESSOR);
            cutout(ModBlocks.CHALCEDONY_PANEL);
            cutout(ModBlocks.INCENSE_BASE);
            cutout(ModBlocks.YUZU_BAT);
            cutout(ModBlocks.GEL_BAT);
            cutout(ModBlocks.BAT_BOX);
            cutout(ModBlocks.FLOWER_POT);
            cutout(ModBlocks.YUZU_FENCE);
            cutout(ModBlocks.HANDLE_ENGINE);
            cutout(ModBlocks.WOOD_PANEL);
            cutout(ModBlocks.CHALCEDONY_LAMP_OP);
            cutout(ModBlocks.FILLED_SOUP_PAN);
            cutout(ModBlocks.CONTAINER_WATER_BOTTLE);
            cutout(ModBlocks.FLOWER_VASE);
            cutout(ModBlocks.HEDGE);
        });
    }
}
