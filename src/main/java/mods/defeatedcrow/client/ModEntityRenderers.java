package mods.defeatedcrow.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import mods.defeatedcrow.client.entity.RenderAlcoholCupEntity;
import mods.defeatedcrow.client.entity.RenderAnchorMissile;
import mods.defeatedcrow.client.entity.RenderBowlEntity;
import mods.defeatedcrow.client.entity.RenderBowlJPEntity;
import mods.defeatedcrow.client.entity.RenderCocktail2Entity;
import mods.defeatedcrow.client.entity.RenderCocktailEntity;
import mods.defeatedcrow.client.entity.RenderCocktailSPEntity;
import mods.defeatedcrow.client.entity.RenderCup2Entity;
import mods.defeatedcrow.client.entity.RenderCupEntity;
import mods.defeatedcrow.client.entity.RenderIceCreamEntity;
import mods.defeatedcrow.client.entity.RenderIllusionCreeper;
import mods.defeatedcrow.client.entity.RenderKinokoEntity;
import mods.defeatedcrow.client.entity.RenderMelonBomb;
import mods.defeatedcrow.client.entity.RenderSandwichEntity;
import mods.defeatedcrow.client.entity.RenderSilkyMelon;
import mods.defeatedcrow.client.entity.RenderSteakEntity;
import mods.defeatedcrow.client.entity.RenderStunEntity;
import mods.defeatedcrow.client.entity.RenderTartEntity;
import mods.defeatedcrow.client.entity.RenderYuzuBullet;
import mods.defeatedcrow.client.entity.base.RenderFoodEntityBase;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.registry.ModEntities;

/**
 * 1.20.1 client registration - replaces ClientProxy.registerEntityRenderingHandler.
 * WT-C owned: entity renderers + model layers for client/entity/** and client/model/model/**.
 *
 * Entity renderer registration mapping (former RenderingRegistry order):
 * <ul>
 * <li>MELON_BOMB     -&gt; RenderMelonBomb</li>
 * <li>SILKY_MELON    -&gt; RenderSilkyMelon</li>
 * <li>ICECREAM       -&gt; RenderIceCreamEntity</li>
 * <li>STEAK          -&gt; RenderSteakEntity</li>
 * <li>ALCOHOL_CUP    -&gt; RenderAlcoholCupEntity</li>
 * <li>COCKTAIL       -&gt; RenderCocktailEntity</li>
 * <li>COCKTAIL2      -&gt; RenderCocktail2Entity</li>
 * <li>BOWL           -&gt; RenderBowlEntity</li>
 * <li>BOWL_JP        -&gt; RenderBowlJPEntity</li>
 * <li>CUP1           -&gt; RenderCupEntity</li>
 * <li>CUP2           -&gt; RenderCup2Entity</li>
 * <li>TART           -&gt; RenderTartEntity</li>
 * <li>SANDWICH       -&gt; RenderSandwichEntity</li>
 * <li>KINOKO         -&gt; RenderKinokoEntity</li>
 * <li>STUN_EFFECT    -&gt; RenderStunEntity</li>
 * <li>ILLUSION_MOBS  -&gt; RenderIllusionCreeper</li>
 * <li>ANCHOR_MISSILE -&gt; RenderAnchorMissile</li>
 * <li>YUZU_BULLET    -&gt; RenderYuzuBullet</li>
 * <li>COCKTAIL_SP    -&gt; RenderCocktailSPEntity</li>
 * <li>BASE_SOUP (+ remaining FoodBaseEntity placeable variants) -&gt; RenderFoodEntityBase</li>
 * </ul>
 *
 * NOTE(WT-B): ModEntities field names above must match common/registry/ModEntities.java.
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    // --- model layer locations ---
    public static final ModelLayerLocation MODEL_MELON_BOMB = layer("model/melon_bomb");
    public static final ModelLayerLocation MODEL_SILKY_MELON = layer("model/silky_melon");
    public static final ModelLayerLocation MODEL_YUZU_BULLET = layer("model/yuzu_bullet");
    public static final ModelLayerLocation MODEL_ANCHOR_MISSILE = layer("model/anchor_missile");
    public static final ModelLayerLocation MODEL_KINOKO = layer("model/kinoko");
    public static final ModelLayerLocation MODEL_ICECREAM = layer("model/icecream");
    public static final ModelLayerLocation MODEL_STEAK = layer("model/steak");
    public static final ModelLayerLocation MODEL_TART = layer("model/tart");
    public static final ModelLayerLocation MODEL_SANDWICH = layer("model/sandwich");
    public static final ModelLayerLocation MODEL_BOWL_WOOD = layer("model/bowl_wood");
    public static final ModelLayerLocation MODEL_BOWL_JP = layer("model/bowl_jp");
    public static final ModelLayerLocation MODEL_TEA_CUP = layer("model/tea_cup");
    public static final ModelLayerLocation MODEL_ALCOHOL_CUP = layer("model/alcohol_cup");
    public static final ModelLayerLocation MODEL_COCKTAIL = layer("model/cocktail");
    public static final ModelLayerLocation MODEL_RICE_BOWL_B = layer("model/rice_bowl_b");
    public static final ModelLayerLocation MODEL_SOUP_BOWL_B = layer("model/soup_bowl_b");
    public static final ModelLayerLocation MODEL_GLASS_DISH_B = layer("model/glass_dish_b");
    public static final ModelLayerLocation MODEL_JP_DISH_B = layer("model/jp_dish_b");
    public static final ModelLayerLocation MODEL_WHITE_DISH_B = layer("model/white_dish_b");
    public static final ModelLayerLocation MODEL_INNER_KOBATI = layer("model/inner_kobati");
    public static final ModelLayerLocation MODEL_INNER_SOUP = layer("model/inner_soup");

    private static ModelLayerLocation layer(String path) {
        return new ModelLayerLocation(new ResourceLocation(DCsAppleMilk.MODID, path), "main");
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MODEL_MELON_BOMB, mods.defeatedcrow.client.model.model.ModelMelonBomb::createBodyLayer);
        // silky melon reuses the compressed melon geometry
        event.registerLayerDefinition(MODEL_SILKY_MELON, mods.defeatedcrow.client.model.model.ModelMelonBomb::createBodyLayer);
        event.registerLayerDefinition(MODEL_YUZU_BULLET, mods.defeatedcrow.client.model.model.ModelYuzuBullet::createBodyLayer);
        event.registerLayerDefinition(MODEL_ANCHOR_MISSILE, mods.defeatedcrow.client.model.model.ModelAnchorMissile::createBodyLayer);
        event.registerLayerDefinition(MODEL_KINOKO, mods.defeatedcrow.client.model.model.ModelKinoko::createBodyLayer);
        event.registerLayerDefinition(MODEL_ICECREAM, mods.defeatedcrow.client.model.model.ModelIceCream::createBodyLayer);
        event.registerLayerDefinition(MODEL_STEAK, mods.defeatedcrow.client.model.model.ModelSteak::createBodyLayer);
        event.registerLayerDefinition(MODEL_TART, mods.defeatedcrow.client.model.model.ModelTart::createBodyLayer);
        event.registerLayerDefinition(MODEL_SANDWICH, mods.defeatedcrow.client.model.model.ModelSandwich::createBodyLayer);
        event.registerLayerDefinition(MODEL_BOWL_JP, mods.defeatedcrow.client.model.model.ModelBowlJP::createBodyLayer);
        event.registerLayerDefinition(MODEL_TEA_CUP, mods.defeatedcrow.client.entity.base.ModelTeaCup::createBodyLayer);
        event.registerLayerDefinition(MODEL_ALCOHOL_CUP, mods.defeatedcrow.client.model.model.ModelAlcoholCup::createBodyLayer);
        event.registerLayerDefinition(MODEL_COCKTAIL, mods.defeatedcrow.client.model.model.ModelCocktail::createBodyLayer);
        event.registerLayerDefinition(MODEL_RICE_BOWL_B, mods.defeatedcrow.client.entity.base.ModelRiceBowlB::createBodyLayer);
        event.registerLayerDefinition(MODEL_SOUP_BOWL_B, mods.defeatedcrow.client.entity.base.ModelSoupBowlB::createBodyLayer);
        event.registerLayerDefinition(MODEL_GLASS_DISH_B, mods.defeatedcrow.client.entity.base.ModelGlassDishB::createBodyLayer);
        event.registerLayerDefinition(MODEL_JP_DISH_B, mods.defeatedcrow.client.entity.base.ModelJPDishB::createBodyLayer);
        event.registerLayerDefinition(MODEL_WHITE_DISH_B, mods.defeatedcrow.client.entity.base.ModelWhiteDishB::createBodyLayer);
        event.registerLayerDefinition(MODEL_INNER_KOBATI, mods.defeatedcrow.client.entity.base.ModelInnerKobati::createBodyLayer);
        event.registerLayerDefinition(MODEL_INNER_SOUP, mods.defeatedcrow.client.entity.base.ModelInnerSoup::createBodyLayer);
        event.registerLayerDefinition(MODEL_BOWL_WOOD, mods.defeatedcrow.client.entity.base.ModelWoodBowl::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // --- WT-C: Entity 23 (ClientProxy.registerEntityRenderingHandler) ---
        event.registerEntityRenderer(ModEntities.MELON_BOMB.get(), RenderMelonBomb::new);
        event.registerEntityRenderer(ModEntities.SILKY_MELON.get(), RenderSilkyMelon::new);
        event.registerEntityRenderer(ModEntities.ICECREAM.get(), RenderIceCreamEntity::new);
        event.registerEntityRenderer(ModEntities.STEAK.get(), RenderSteakEntity::new);
        event.registerEntityRenderer(ModEntities.ALCOHOL_CUP.get(), RenderAlcoholCupEntity::new);
        event.registerEntityRenderer(ModEntities.COCKTAIL.get(), RenderCocktailEntity::new);
        event.registerEntityRenderer(ModEntities.COCKTAIL2.get(), RenderCocktail2Entity::new);
        event.registerEntityRenderer(ModEntities.BOWL.get(), RenderBowlEntity::new);
        event.registerEntityRenderer(ModEntities.BOWL_JP.get(), RenderBowlJPEntity::new);
        event.registerEntityRenderer(ModEntities.CUP1.get(), RenderCupEntity::new);
        event.registerEntityRenderer(ModEntities.CUP2.get(), RenderCup2Entity::new);
        event.registerEntityRenderer(ModEntities.TART.get(), RenderTartEntity::new);
        event.registerEntityRenderer(ModEntities.SANDWICH.get(), RenderSandwichEntity::new);
        event.registerEntityRenderer(ModEntities.KINOKO.get(), RenderKinokoEntity::new);
        event.registerEntityRenderer(ModEntities.STUN_EFFECT.get(), RenderStunEntity::new);
        event.registerEntityRenderer(ModEntities.ILLUSION_MOBS.get(), RenderIllusionCreeper::new);
        event.registerEntityRenderer(ModEntities.ANCHOR_MISSILE.get(), RenderAnchorMissile::new);
        event.registerEntityRenderer(ModEntities.YUZU_BULLET.get(), RenderYuzuBullet::new);
        event.registerEntityRenderer(ModEntities.COCKTAIL_SP.get(), RenderCocktailSPEntity::new);
        event.registerEntityRenderer(ModEntities.BASE_SOUP.get(), RenderFoodEntityBase::new);
        // Remaining FoodBaseEntity placeable variants share RenderFoodEntityBase:
        // event.registerEntityRenderer(ModEntities.RICE_BOWL.get(), RenderFoodEntityBase::new); etc. (WT-B naming)
    }
}
