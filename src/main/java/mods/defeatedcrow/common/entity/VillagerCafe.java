package mods.defeatedcrow.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 1.20.1 VillagerCafe - custom profession for cafe (was VillagerRegistry).
 * Original 1.7.10 used VillagerRegistry.registerVillageTradeHandler, now VillagerProfession + PoiType + VillagerTradesEvent.
 */
public class VillagerCafe {

    @SubscribeEvent
    public static void onTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) { // placeholder - should be custom CAFE profession
            // TODO: add cafe trades (tea, apple sandwich, etc.) when ModVillagers.CAFE is registered
            // event.getTrades().get(1).add((trader, rand) -> new MerchantOffer(...));
        }
    }

    // TODO: register custom VillagerProfession CAFE with PoiType (barrel/cordial) via DeferredRegister<VillagerProfession>
    // public static final RegistryObject<VillagerProfession> CAFE = VILLAGERS.register("cafe", () -> new VillagerProfession(...));
}
