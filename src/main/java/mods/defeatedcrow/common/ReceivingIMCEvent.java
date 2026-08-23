package mods.defeatedcrow.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;

/**
 * 1.20.1 IMC event handler — replaces legacy FML IMCEvent with InterModProcessEvent + modern tag.
 * See doc/recipes/migration-guide.md and doc/build.md.
 * NBT is kept (DataComponents are 1.20.5+), but old tag class -> CompoundTag.
 */
public class ReceivingIMCEvent {

    private ReceivingIMCEvent() {}

    public static void receiveIMC(InterModProcessEvent event) {
        event.getIMCStream().forEach(message -> {
            String key = message.method();
            Object raw = message.messageSupplier().get();
            if (!(raw instanceof CompoundTag tag)) {
                AMTLogger.warn("Received IMC message with non-CompoundTag payload for key: " + key + " from " + message.senderModId());
                return;
            }
            switch (key) {
                case "TeaMakerRecipe" -> receiveAddTeaRecipe(tag, message.senderModId());
                case "IceMakerRecipe" -> receiveAddIceRecipe(tag, message.senderModId());
                case "IceChargeItem" -> receiveAddIceCharge(tag, message.senderModId());
                default -> AMTLogger.warn("Received IMC message with unknown key : " + key);
            }
        });
    }

    private static void receiveAddTeaRecipe(CompoundTag tag, String sender) {
        boolean flag = false;
        if (tag.contains("input", 10) && tag.contains("output", 10) && tag.contains("texture", 8)) {
            ItemStack input = ItemStack.of(tag.getCompound("input"));
            ItemStack output = ItemStack.of(tag.getCompound("output"));
            ItemStack outputMilk = ItemStack.EMPTY;
            if (tag.contains("outputMilk", 10)) {
                outputMilk = ItemStack.of(tag.getCompound("outputMilk"));
            }
            String texture = tag.getString("texture");
            String textureMilk = "defeatedcrow:textures/blocks/contents_water.png";
            if (tag.contains("textureMilk", 8)) {
                textureMilk = tag.getString("textureMilk");
            }
            if (!input.isEmpty() && !output.isEmpty() && !texture.isEmpty()) {
                RecipeRegisterManager.teaRecipe.registerCanMilk(input, output, outputMilk.isEmpty() ? null : outputMilk, texture, textureMilk);
                flag = true;
            }
        }
        if (!flag) {
            AMTLogger.warn("Failed to register new TeaMaker recipe with IMC message from " + sender);
        }
    }

    private static void receiveAddIceRecipe(CompoundTag tag, String sender) {
        boolean flag = false;
        if (tag.contains("input", 10) && tag.contains("output", 10)) {
            ItemStack input = ItemStack.of(tag.getCompound("input"));
            ItemStack output = ItemStack.of(tag.getCompound("output"));
            ItemStack container = ItemStack.EMPTY;
            if (tag.contains("container", 10)) {
                container = ItemStack.of(tag.getCompound("container"));
            }
            if (!input.isEmpty() && !output.isEmpty()) {
                RecipeRegisterManager.iceRecipe.registerCanLeave(input, output, container.isEmpty() ? null : container);
                flag = true;
            }
        }
        if (!flag) {
            AMTLogger.warn("Failed to register new IceMaker recipe with IMC message from " + sender);
        }
    }

    private static void receiveAddIceCharge(CompoundTag tag, String sender) {
        boolean flag = false;
        if (tag.contains("input", 10) && tag.contains("amount", 3)) {
            ItemStack input = ItemStack.of(tag.getCompound("input"));
            int amount = tag.getInt("amount");
            if (!input.isEmpty() && amount > 0) {
                RecipeRegisterManager.iceRecipe.registerCharger(input, amount);
                flag = true;
            }
        }
        if (!flag) {
            AMTLogger.warn("Failed to register new IceMaker chargeable item with IMC message from " + sender);
        }
    }

    /**
     * Legacy enqueue helper for other mods to send IMC.
     * Uses modern InterModComms.sendTo with CompoundTag supplier.
     */
    public static void enqueueTeaRecipe(CompoundTag tag) {
        InterModComms.sendTo("defeatedcrow", "TeaMakerRecipe", () -> tag);
    }
}
