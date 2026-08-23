package mods.defeatedcrow.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "defeatedcrow");
    // Stub entities - original had ~21 entities; minimal placeholder to allow compile
    public static final RegistryObject<EntityType<?>> PLACEABLE_ALCOHOL_CUP = ENTITIES.register("placeable_alcohol_cup",
        () -> EntityType.Builder.<net.minecraft.world.entity.Entity>of((et, lvl) -> null, MobCategory.MISC).sized(0.5F,0.5F).build("placeable_alcohol_cup"));
}
