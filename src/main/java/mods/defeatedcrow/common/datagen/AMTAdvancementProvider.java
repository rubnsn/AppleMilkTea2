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
 * See doc/achievements/migration-guide.md:43 and plan-wt-d.md:4-3.
 * Uses PackOutput + HolderLookup.Provider + ExistingFileHelper.
 * Parent is minecraft:story/root (old openInventory).
 * setSpecial() -> FrameType.CHALLENGE.
 */
public class AMTAdvancementProvider extends ForgeAdvancementProvider {

    public AMTAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper helper) {
        super(output, lookup, helper, List.of(new AMTAdvancements()));
    }

    public static class AMTAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper helper) {
            Advancement root = Advancement.Builder.advancement()
                .display(new ItemStack(Items.OAK_LEAVES),
                    Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"),
                    Component.translatable("advancement.defeatedcrow.getTeaLeaves.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.OAK_LEAVES).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_tea_leaves"), helper);

            Advancement makeTeaLeaves = Advancement.Builder.advancement()
                .parent(root)
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

            Advancement getAppleMilkTea = Advancement.Builder.advancement()
                .parent(craftTeaMaker)
                .display(new ItemStack(Items.MILK_BUCKET),
                    Component.translatable("advancement.defeatedcrow.getAppleMilkTea.title"),
                    Component.translatable("advancement.defeatedcrow.getAppleMilkTea.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_apple_milk_tea", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.MILK_BUCKET).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_apple_milk_tea"), helper);

            Advancement craftCharm = Advancement.Builder.advancement()
                .parent(root)
                .display(new ItemStack(Items.ENDER_PEARL),
                    Component.translatable("advancement.defeatedcrow.craftCharm.title"),
                    Component.translatable("advancement.defeatedcrow.craftCharm.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.CHALLENGE, true, true, false)
                .addCriterion("has_princess", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.PRISMARINE_SHARD).build()))
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

            Advancement getPrincess = Advancement.Builder.advancement()
                .parent(root)
                .display(new ItemStack(Items.HEART_OF_THE_SEA),
                    Component.translatable("advancement.defeatedcrow.getPrincess.title"),
                    Component.translatable("advancement.defeatedcrow.getPrincess.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_princess_clam", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.HEART_OF_THE_SEA).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_princess"), helper);

            Advancement getYuzu = Advancement.Builder.advancement()
                .parent(root)
                .display(new ItemStack(Items.GOLDEN_APPLE),
                    Component.translatable("advancement.defeatedcrow.getYuzu.title"),
                    Component.translatable("advancement.defeatedcrow.getYuzu.description"),
                    new ResourceLocation("defeatedcrow", "textures/gui/advancement_bg.png"),
                    FrameType.TASK, true, true, false)
                .addCriterion("has_yuzu", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(Items.GOLDEN_APPLE).build()))
                .save(saver, new ResourceLocation("defeatedcrow", "get_yuzu"), helper);
        }
    }
}
