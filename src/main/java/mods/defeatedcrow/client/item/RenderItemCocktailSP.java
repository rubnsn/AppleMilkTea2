package mods.defeatedcrow.client.item;

import net.minecraft.resources.ResourceLocation;

/**
 * 1.20.1 stub for the 1.7.10 {@code IItemRenderer} of the Special Cocktail item.
 *
 * <p>
 * The legacy renderer drew {@code ModelCocktail} (inner liquid tinted per metadata via
 * {@code BlockCocktailSP#getColorPropertySP}, deco + translucent glass). ModelBase does not exist on
 * 1.20.1, and the damage/metadata variants become separate blockstates, so:
 *
 * <ul>
 * <li>Short term: WT-A resources provides per-state JSON models
 * ({@code assets/dcsapplemilk/blockstates/cocktail_sp.json} + tinted layers); the in-world rendering
 * counterpart is the TileCocktailSP BER registered by ModBlockEntityRenderers (other agent).</li>
 * <li>Long term: if a dynamic Java model is still wanted, implement Forge's
 * {@code IClientItemExtensions} returning a {@code BlockEntityWithoutLevelRenderer} that reads the
 * drink state from the stack NBT and draws inner/deco/glass passes with blaze3d.</li>
 * </ul>
 */
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public final class RenderItemCocktailSP {

    /** Kept for the future BEWLR port; same textures as 1.7.10. */
    public static final ResourceLocation TEXTURE_GLASS = new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png");
    public static final ResourceLocation TEXTURE_INNER = new ResourceLocation("defeatedcrow", "textures/blocks/contents_cocktailbase.png");

    private RenderItemCocktailSP() {}
}
