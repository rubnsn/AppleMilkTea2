package mods.defeatedcrow.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.registry.ModEntities;

@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {
    @SubscribeEvent
    public static void onRegister(EntityRenderersEvent.RegisterRenderers e){
        e.registerEntityRenderer(ModEntities.MELON_BOMB.get(), mods.defeatedcrow.client.entity.RenderMelonBomb::new);
        e.registerEntityRenderer(ModEntities.SILKY_MELON.get(), mods.defeatedcrow.client.entity.RenderSilkyMelon::new);
        e.registerEntityRenderer(ModEntities.KINOKO.get(), mods.defeatedcrow.client.entity.RenderKinokoEntity::new);
        e.registerEntityRenderer(ModEntities.STUN_EFFECT.get(), mods.defeatedcrow.client.entity.RenderStunEntity::new);
        e.registerEntityRenderer(ModEntities.ILLUSION_MOBS.get(), mods.defeatedcrow.client.entity.RenderIllusionCreeper::new);
        e.registerEntityRenderer(ModEntities.ANCHOR_MISSILE.get(), mods.defeatedcrow.client.entity.RenderAnchorMissile::new);
        e.registerEntityRenderer(ModEntities.YUZU_BULLET.get(), mods.defeatedcrow.client.entity.RenderYuzuBullet::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_ALCOHOL_CUP.get(), mods.defeatedcrow.client.entity.RenderAlcoholCupEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_BOWL.get(), mods.defeatedcrow.client.entity.RenderBowlEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_BOWL_JP.get(), mods.defeatedcrow.client.entity.RenderBowlJPEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_COCKTAIL.get(), mods.defeatedcrow.client.entity.RenderCocktailEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_COCKTAIL2.get(), mods.defeatedcrow.client.entity.RenderCocktail2Entity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_COCKTAIL_SP.get(), mods.defeatedcrow.client.entity.RenderCocktailSPEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_CUP1.get(), mods.defeatedcrow.client.entity.RenderCupEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_CUP2.get(), mods.defeatedcrow.client.entity.RenderCup2Entity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_ICECREAM.get(), mods.defeatedcrow.client.entity.RenderIceCreamEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_STEAK.get(), mods.defeatedcrow.client.entity.RenderSteakEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_TART.get(), mods.defeatedcrow.client.entity.RenderTartEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_SANDWICH.get(), mods.defeatedcrow.client.entity.RenderSandwichEntity::new);
        e.registerEntityRenderer(ModEntities.PLACEABLE_BASE_SOUP.get(), mods.defeatedcrow.client.entity.base.RenderFoodEntityBase::new);
    }
}
