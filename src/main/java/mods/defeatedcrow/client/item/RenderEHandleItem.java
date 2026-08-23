package mods.defeatedcrow.client.item;

import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1 stub for the 1.7.10 {@code IItemRenderer} of the Handle Engine block-item.
 *
 * <p>
 * The legacy renderer drew {@code ModelHandleEngine} (ModelBase, client/model/model/, owned by the
 * model-migration agent) in inventory/equipped/entity views with
 * {@code defeatedcrow:textures/entity/handle_engine.png}. ModelBase does not exist on 1.20.1, so the
 * render path is intentionally reduced to a JSON-model placeholder:
 *
 * <ul>
 * <li>Short term: WT-A resources provides {@code assets/dcsapplemilk/models/block/handle_engine.json}
 * and the BlockItem inherits it — no code needed for this class.</li>
 * <li>Long term (if a dynamic Java model is still wanted): implement Forge's
 * {@code IClientItemExtensions} returning a {@code BlockEntityWithoutLevelRenderer}
 * ({@code net.minecraftforge.client.rendering.BlockEntityWithoutLevelRenderer}) that delegates to the
 * ported model, hooked via {@code Item#initializeClient(...)} in the WT-A item class. The TESR-side
 * counterpart is registered by ModBlockEntityRenderers (other agent).</li>
 * </ul>
 */
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public final class RenderEHandleItem {

    /** Kept for the future BEWLR port; same texture as 1.7.10. */
    public static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/handle_engine.png");

    private RenderEHandleItem() {}
}
