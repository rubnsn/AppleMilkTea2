package mods.defeatedcrow.common.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import mods.defeatedcrow.common.loot.AddItemModifier;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "defeatedcrow");

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
        LOOT_MODIFIERS.register("add_item", () -> AddItemModifier.CODEC);
}
