package mods.defeatedcrow.common.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

/**
 * 1.20.1 Advancement provider - replaces 1.7.10 AchievementRegister (37 entries).
 * See doc/achievements/migration-guide.md:43 and plan.md 10.4
 * Uses PackOutput + HolderLookup.Provider + ExistingFileHelper + Advancement + FrameType (1.20.1).
 * AdvancementHolder is 1.20.5+ so not used here (Forge 47.3 still uses Advancement/DisplayInfo).
 * Parent is minecraft:story/root for independent stats (openInventory, buildWorkBench).
 * setSpecial() -> FrameType.CHALLENGE.
 * All 37 are generated via runData to src/generated/resources/data/defeatedcrow/advancements/*.json
 * then manually merged to src/main/resources/data/defeatedcrow/advancements/*.json
 * Language keys: "advancement.defeatedcrow.<camel>.title" (assets/defeatedcrow/lang/*.json)
 */
public class AMTAdvancementProvider extends ForgeAdvancementProvider {

    public AMTAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper helper) {
        super(output, lookup, helper, List.of(new AMTAdvancements()));
    }

    public static class AMTAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper helper) {
            // Root - getTeaLeaves (old initIndependentStat -> parent null, background)
            Advancement getTeaLeaves = Advancement.Builder.advancement()
                .display(new ItemStack(Items.OAK_LEAVES),
                    Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"),
                    Component.translatable("advancement.defeatedcrow.getTeaLeaves.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.OAK_LEAVES).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_tea_leaves"), helper);

            Advancement makeTeaLeaves = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Items.SUGAR),
                    Component.translatable("advancement.defeatedcrow.makeTeaLeaves.title"),
                    Component.translatable("advancement.defeatedcrow.makeTeaLeaves.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_food_tea", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.SUGAR).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "make_tea_leaves"), helper);

            Advancement craftTeaMaker = Advancement.Builder.advancement()
                .parent(makeTeaLeaves)
                .display(new ItemStack(Blocks.FURNACE),
                    Component.translatable("advancement.defeatedcrow.craftTeaMaker.title"),
                    Component.translatable("advancement.defeatedcrow.craftTeaMaker.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_tea_maker", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.FURNACE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_tea_maker"), helper);

            Advancement craftPan = Advancement.Builder.advancement()
                .parent(makeTeaLeaves)
                .display(new ItemStack(Items.CAULDRON),
                    Component.translatable("advancement.defeatedcrow.craftPan.title"),
                    Component.translatable("advancement.defeatedcrow.craftPan.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_pan", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_pan"), helper);

            Advancement craftTeppan = Advancement.Builder.advancement()
                .parent(craftPan)
                .display(new ItemStack(Items.IRON_INGOT),
                    Component.translatable("advancement.defeatedcrow.craftTeppan.title"),
                    Component.translatable("advancement.defeatedcrow.craftTeppan.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_teppan", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_teppan"), helper);

            Advancement craftIceMaker = Advancement.Builder.advancement()
                .parent(makeTeaLeaves)
                .display(new ItemStack(Blocks.ICE),
                    Component.translatable("advancement.defeatedcrow.craftIceMaker.title"),
                    Component.translatable("advancement.defeatedcrow.craftIceMaker.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_ice_maker", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.ICE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_ice_maker"), helper);

            Advancement craftChalcedony = Advancement.Builder.advancement()
                .parent(craftPan)
                .display(new ItemStack(Items.DIAMOND),
                    Component.translatable("advancement.defeatedcrow.craftChalcedony.title"),
                    Component.translatable("advancement.defeatedcrow.craftChalcedony.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_chalcedony", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.DIAMOND).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_chalcedony"), helper);

            Advancement craftLogBox = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Blocks.CHEST),
                    Component.translatable("advancement.defeatedcrow.craftLogbox.title"),
                    Component.translatable("advancement.defeatedcrow.craftLogbox.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_logbox", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.CHEST).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_logbox"), helper);

            Advancement craftGrater = Advancement.Builder.advancement()
                .parent(craftPan)
                .display(new ItemStack(Items.SHEARS),
                    Component.translatable("advancement.defeatedcrow.craftGrater.title"),
                    Component.translatable("advancement.defeatedcrow.craftGrater.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_grater", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.SHEARS).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_grater"), helper);

            Advancement makeRice = Advancement.Builder.advancement()
                .parent(craftGrater)
                .display(new ItemStack(Items.WHEAT),
                    Component.translatable("advancement.defeatedcrow.makeRice.title"),
                    Component.translatable("advancement.defeatedcrow.makeRice.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_rice", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.WHEAT).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "make_rice"), helper);

            Advancement getSoup = Advancement.Builder.advancement()
                .parent(makeRice)
                .display(new ItemStack(Items.MUSHROOM_STEW),
                    Component.translatable("advancement.defeatedcrow.getSoup.title"),
                    Component.translatable("advancement.defeatedcrow.getSoup.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_soup", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.MUSHROOM_STEW).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_soup"), helper);

            Advancement eatChocoGift = Advancement.Builder.advancement()
                .parent(makeRice)
                .display(new ItemStack(Items.COOKIE),
                    Component.translatable("advancement.defeatedcrow.eatChocoGift.title"),
                    Component.translatable("advancement.defeatedcrow.eatChocoGift.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_choco", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.COOKIE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "eat_chocolate_gift"), helper);

            Advancement getTea = Advancement.Builder.advancement()
                .parent(craftTeaMaker)
                .display(new ItemStack(Items.POTION),
                    Component.translatable("advancement.defeatedcrow.getTea.title"),
                    Component.translatable("advancement.defeatedcrow.getTea.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_tea", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.POTION).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_tea"), helper);

            Advancement getAppleMilkTea = Advancement.Builder.advancement()
                .parent(getTea)
                .display(new ItemStack(Items.MILK_BUCKET),
                    Component.translatable("advancement.defeatedcrow.getAppleMilkTea.title"),
                    Component.translatable("advancement.defeatedcrow.getAppleMilkTea.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_apple_milk_tea", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.MILK_BUCKET).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_apple_milk_tea"), helper);

            Advancement getHamaguri = Advancement.Builder.advancement()
                .parent(craftTeppan)
                .display(new ItemStack(Items.PRISMARINE_SHARD),
                    Component.translatable("advancement.defeatedcrow.getHamaguri.title"),
                    Component.translatable("advancement.defeatedcrow.getHamaguri.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_hamaguri", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.PRISMARINE_SHARD).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_hamaguri"), helper);

            Advancement craftCharcoalContainer = Advancement.Builder.advancement()
                .parent(craftLogBox)
                .display(new ItemStack(Items.CHARCOAL),
                    Component.translatable("advancement.defeatedcrow.craftCharcoalContainer.title"),
                    Component.translatable("advancement.defeatedcrow.craftCharcoalContainer.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_charcoal_box", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.CHARCOAL).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_charcoal_container"), helper);

            Advancement craftVegiBag = Advancement.Builder.advancement()
                .parent(craftLogBox)
                .display(new ItemStack(Blocks.HAY_BLOCK),
                    Component.translatable("advancement.defeatedcrow.craftVegiBag.title"),
                    Component.translatable("advancement.defeatedcrow.craftVegiBag.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_vegibag", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.HAY_BLOCK).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_vegi_bag"), helper);

            Advancement crashMelon = Advancement.Builder.advancement()
                .parent(craftVegiBag)
                .display(new ItemStack(Blocks.MELON),
                    Component.translatable("advancement.defeatedcrow.crashMelon.title"),
                    Component.translatable("advancement.defeatedcrow.crashMelon.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_melon", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.MELON).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "crash_melon"), helper);

            Advancement eatIcecream = Advancement.Builder.advancement()
                .parent(craftIceMaker)
                .display(new ItemStack(Items.SNOWBALL),
                    Component.translatable("advancement.defeatedcrow.eatIcecream.title"),
                    Component.translatable("advancement.defeatedcrow.eatIcecream.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_icecream", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.SNOWBALL).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "eat_icecream"), helper);

            Advancement craftChalGear = Advancement.Builder.advancement()
                .parent(craftChalcedony)
                .display(new ItemStack(Items.IRON_INGOT),
                    Component.translatable("advancement.defeatedcrow.craftChalGear.title"),
                    Component.translatable("advancement.defeatedcrow.craftChalGear.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_chal_gear", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_chal_gear"), helper);

            Advancement craftGlassLamp = Advancement.Builder.advancement()
                .parent(craftChalcedony)
                .display(new ItemStack(Blocks.SEA_LANTERN),
                    Component.translatable("advancement.defeatedcrow.craftGlassLamp.title"),
                    Component.translatable("advancement.defeatedcrow.craftGlassLamp.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_glass_lamp", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.SEA_LANTERN).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_glass_lamp"), helper);

            Advancement craftProcessor = Advancement.Builder.advancement()
                .parent(craftGrater)
                .display(new ItemStack(Blocks.CRAFTING_TABLE),
                    Component.translatable("advancement.defeatedcrow.craftProcessor.title"),
                    Component.translatable("advancement.defeatedcrow.craftProcessor.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_processor", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.CRAFTING_TABLE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_processor"), helper);

            Advancement burnOnTeppan = Advancement.Builder.advancement()
                .parent(craftTeppan)
                .display(new ItemStack(Items.FLINT_AND_STEEL),
                    Component.translatable("advancement.defeatedcrow.burnOnTeppan.title"),
                    Component.translatable("advancement.defeatedcrow.burnOnTeppan.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_fire", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.FLINT_AND_STEEL).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "burn_on_teppan"), helper);

            Advancement getPrincess = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Items.HEART_OF_THE_SEA),
                    Component.translatable("advancement.defeatedcrow.getPrincess.title"),
                    Component.translatable("advancement.defeatedcrow.getPrincess.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_princess_clam", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.HEART_OF_THE_SEA).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_princess"), helper);

            Advancement getYuzu = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Items.GOLDEN_APPLE),
                    Component.translatable("advancement.defeatedcrow.getYuzu.title"),
                    Component.translatable("advancement.defeatedcrow.getYuzu.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_yuzu", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.GOLDEN_APPLE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_yuzu"), helper);

            Advancement craftYuzuBattery = Advancement.Builder.advancement()
                .parent(getYuzu)
                .display(new ItemStack(Items.REDSTONE),
                    Component.translatable("advancement.defeatedcrow.craftYuzuBattery.title"),
                    Component.translatable("advancement.defeatedcrow.craftYuzuBattery.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_yuzu_battery", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.REDSTONE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_yuzu_battery"), helper);

            Advancement craftChargeableBattery = Advancement.Builder.advancement()
                .parent(craftYuzuBattery)
                .display(new ItemStack(Items.REPEATER),
                    Component.translatable("advancement.defeatedcrow.craftChargeableBattery.title"),
                    Component.translatable("advancement.defeatedcrow.craftChargeableBattery.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_charge_battery", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.REPEATER).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_chargeable_battery"), helper);

            Advancement craftBarrel = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Blocks.BARREL),
                    Component.translatable("advancement.defeatedcrow.craftBarrel.title"),
                    Component.translatable("advancement.defeatedcrow.craftBarrel.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_barrel", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.BARREL).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_barrel"), helper);

            Advancement getAlcohol = Advancement.Builder.advancement()
                .parent(craftBarrel)
                .display(new ItemStack(Items.POTION),
                    Component.translatable("advancement.defeatedcrow.getAlcohol.title"),
                    Component.translatable("advancement.defeatedcrow.getAlcohol.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_alcohol", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.POTION).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_alcohol"), helper);

            Advancement craftEvaporator = Advancement.Builder.advancement()
                .parent(getAlcohol)
                .display(new ItemStack(Blocks.BREWING_STAND),
                    Component.translatable("advancement.defeatedcrow.craftEvaporator.title"),
                    Component.translatable("advancement.defeatedcrow.craftEvaporator.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_evaporator", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.BREWING_STAND).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_evaporator"), helper);

            Advancement drinkCocktail = Advancement.Builder.advancement()
                .parent(getAlcohol)
                .display(new ItemStack(Items.GLASS_BOTTLE),
                    Component.translatable("advancement.defeatedcrow.drinkCocktail.title"),
                    Component.translatable("advancement.defeatedcrow.drinkCocktail.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_cocktail", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.GLASS_BOTTLE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "drink_cocktail"), helper);

            Advancement craftJaw = Advancement.Builder.advancement()
                .parent(craftChalGear)
                .display(new ItemStack(Blocks.PISTON),
                    Component.translatable("advancement.defeatedcrow.craftJaw.title"),
                    Component.translatable("advancement.defeatedcrow.craftJaw.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_jaw", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Blocks.PISTON).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_jaw"), helper);

            Advancement craftTart = Advancement.Builder.advancement()
                .parent(getTeaLeaves)
                .display(new ItemStack(Items.CAKE),
                    Component.translatable("advancement.defeatedcrow.craftTart.title"),
                    Component.translatable("advancement.defeatedcrow.craftTart.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_tart", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.CAKE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_tart"), helper);

            Advancement useSilkMelon = Advancement.Builder.advancement()
                .parent(crashMelon)
                .display(new ItemStack(Items.MELON_SLICE),
                    Component.translatable("advancement.defeatedcrow.useSilkMelon.title"),
                    Component.translatable("advancement.defeatedcrow.useSilkMelon.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_silk_melon", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.MELON_SLICE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "use_silk_melon"), helper);

            Advancement craftCharm = Advancement.Builder.advancement()
                .parent(getPrincess)
                .display(new ItemStack(Items.ENDER_PEARL),
                    Component.translatable("advancement.defeatedcrow.craftCharm.title"),
                    Component.translatable("advancement.defeatedcrow.craftCharm.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_charm", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.ENDER_PEARL).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "craft_charm"), helper);

            Advancement useIncense = Advancement.Builder.advancement()
                .parent(craftCharm)
                .display(new ItemStack(Items.STICK),
                    Component.translatable("advancement.defeatedcrow.useIncense.title"),
                    Component.translatable("advancement.defeatedcrow.useIncense.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_incense", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.STICK).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "use_incense"), helper);

            // 37th - extra for 1.7.10 commented getCordial (plan 10.4 P7 no-op, keep as advancement)
            Advancement getCordial = Advancement.Builder.advancement()
                .parent(craftEvaporator)
                .display(new ItemStack(Items.HONEY_BOTTLE),
                    Component.translatable("advancement.defeatedcrow.getCordial.title"),
                    Component.translatable("advancement.defeatedcrow.getCordial.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_cordial", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.HONEY_BOTTLE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_cordial"), helper);
        }
    }
}
