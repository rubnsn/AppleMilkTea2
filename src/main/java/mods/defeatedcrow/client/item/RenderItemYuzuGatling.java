package mods.defeatedcrow.client.item;

import net.minecraft.resources.ResourceLocation;

/**
 * 1.20.1 stub for the 1.7.10 {@code IItemRenderer} of the Yuzu Gatling item.
 *
 * <p>
 * The legacy renderer drew {@code ModelYuzuGatling} with an additive second pass
 * ({@code defeatedcrow:textures/entity/yuzugatling.png}). ModelBase does not exist on 1.20.1, so the
 * render path is intentionally reduced to a JSON-model placeholder:
 *
 * <ul>
 * <li>Short term: WT-A resources provides {@code assets/dcsapplemilk/models/item/yuzu_gatling.json};
 * the held-weapon pose goes into the JSON {@code display} tag.</li>
 * <li>Long term: implement Forge's {@code IClientItemExtensions} returning a
 * {@code BlockEntityWithoutLevelRenderer} delegating to the ported model, hooked via
 * {@code Item#initializeClient(...)} in the WT-A item class.</li>
 * </ul>
 */
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public final class RenderItemYuzuGatling {

    /** Kept for the future BEWLR port; same texture as 1.7.10. */
    public static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/yuzugatling.png");

    private RenderItemYuzuGatling() {}
}
