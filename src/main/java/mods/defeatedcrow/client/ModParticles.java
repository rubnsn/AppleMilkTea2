package mods.defeatedcrow.client;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.registry.ModParticleTypes;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 1.20.1 client particle registration — replaces the 1.7.10 {@code ParticleTex}
 * (TextureStitchEvent.Pre icon stitching, now deleted; sprites come from the particle atlas via
 * {@code SpriteSet}) and the old {@code MinecraftForgeEventBus} spawn helpers.
 *
 * <p>
 * SYNC CONTRACT with WT-B (owner of {@code common/registry/ModParticleTypes.java}, not yet created):
 * the following {@code RegistryObject<SimpleParticleType>} fields are assumed, registered under the
 * namespace {@code "defeatedcrow"} to match the other Mod* registries:
 * <ul>
 * <li>{@code BLINK = register("blink", ...)} — sprite textures/particle/blink.png</li>
 * <li>{@code ORB = register("orb", ...)} — sprite textures/particle/orb.png</li>
 * <li>{@code DC_CLOUD = register("cloud", ...)} — sprite textures/particle/cloud.png</li>
 * <li>{@code FLOWER = register("flower", ...)} — sprite textures/particle/flower.png</li>
 * <li>{@code FEATHER = register("feather", ...)} — sprite textures/particle/feather.png</li>
 * </ul>
 *
 * <p>
 * Bootstrap note (NOT done here): {@code DCsAppleMilk}'s constructor must call
 * {@code ModParticleTypes.PARTICLE_TYPES.register(modBus)} alongside the other DeferredRegisters.
 * Sprite files live at {@code assets/defeatedcrow/textures/particle/<name>.png} (WT-A resources).
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModParticles {

    private ModParticles() {}

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.BLINK.get(), mods.defeatedcrow.client.particle.EntityBlinkFX.Provider::new);
        event.registerSpriteSet(ModParticleTypes.ORB.get(), mods.defeatedcrow.client.particle.EntityOrbFX.Provider::new);
        event.registerSpriteSet(ModParticleTypes.DC_CLOUD.get(), mods.defeatedcrow.client.particle.EntityDCCloudFX.Provider::new);
        event.registerSpriteSet(ModParticleTypes.FLOWER.get(), mods.defeatedcrow.client.particle.EntityDCCloudFX.Provider::new);
        event.registerSpriteSet(ModParticleTypes.FEATHER.get(), mods.defeatedcrow.client.particle.EntityFeatherFX.Provider::new);
    }

    /**
     * Convenience for WT-B spawner code: resolves a type without hard-importing every particle class.
     */
    public static ParticleType<?> byName(String legacyIconName) {
        switch (legacyIconName.toLowerCase(java.util.Locale.ROOT)) {
            case "blink":
                return ModParticleTypes.BLINK.get();
            case "orb":
                return ModParticleTypes.ORB.get();
            case "cloud":
                return ModParticleTypes.DC_CLOUD.get();
            case "flower":
                return ModParticleTypes.FLOWER.get();
            case "feather":
                return ModParticleTypes.FEATHER.get();
            default:
                return ModParticleTypes.DC_CLOUD.get();
        }
    }
}
