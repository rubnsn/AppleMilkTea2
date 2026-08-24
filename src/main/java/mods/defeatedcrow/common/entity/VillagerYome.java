package mods.defeatedcrow.common.entity;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 1.20.1 VillagerYome - second custom villager (was bride).
 * Original 1.7.10 used custom Villager class, now VillagerProfession.
 */
public class VillagerYome {

    @SubscribeEvent
    public static void onTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) { // placeholder
            // TODO: add yome trades (princessClam, etc.)
        }
    }

    // TODO: register YOME profession with PoiType (bed/crow doll) via DeferredRegister
}
