package mods.defeatedcrow.client.item;

import net.minecraft.resources.ResourceLocation;

/**
 * 1.20.1 stub for the 1.7.10 {@code IItemRenderer} of the Eight Eyes Arm item (purely cosmetic).
 *
 * <p>
 * The legacy renderer drew {@code ModelEightEyesArm} twice with additive blending
 * ({@code defeatedcrow:textures/entity/8eyesarm.png}). ModelBase does not exist on 1.20.1, so the render
 * path is intentionally reduced to a JSON-model placeholder:
 *
 * <ul>
 * <li>Short term: WT-A resources provides {@code assets/dcsapplemilk/models/item/eight_eyes_arm.json}.</li>
 * <li>Long term: implement Forge's {@code IClientItemExtensions} returning a
 * {@code BlockEntityWithoutLevelRenderer} that re-creates the double-pass translucent draw using blaze3d
 * ({@code RenderSystem.enableBlend()} + {@code VertexConsumer} with alpha), hooked via
 * {@code Item#initializeClient(...)} in the WT-A item class.</li>
 * </ul>
 */
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public final class RenderEightEyesArm {

    /** Kept for the future BEWLR port; same texture as 1.7.10. */
    public static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/8eyesarm.png");

    private RenderEightEyesArm() {}
}
