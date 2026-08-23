package mods.defeatedcrow.client.item;

import net.minecraft.resources.ResourceLocation;

/**
 * 1.20.1 stub for the 1.7.10 {@code IItemRenderer} of the Fossil Cannon item.
 *
 * <p>
 * The legacy renderer drew {@code ModelFossilCannon} with an additive second pass
 * ({@code defeatedcrow:textures/entity/fossilcannon.png}), with distinct first-person/equipped/entity
 * transforms. ModelBase does not exist on 1.20.1, so the render path is intentionally reduced to a
 * JSON-model placeholder:
 *
 * <ul>
 * <li>Short term: WT-A resources provides {@code assets/dcsapplemilk/models/item/fossil_cannon.json};
 * display transforms (firstperson_righthand etc.) go into the JSON {@code display} tag.</li>
 * <li>Long term: implement Forge's {@code IClientItemExtensions} returning a
 * {@code BlockEntityWithoutLevelRenderer} delegating to the ported model, hooked via
 * {@code Item#initializeClient(...)} in the WT-A item class.</li>
 * </ul>
 */
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public final class RenderItemFossilCannon {

    /** Kept for the future BEWLR port; same texture as 1.7.10. */
    public static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/fossilcannon.png");

    private RenderItemFossilCannon() {}
}
