package mods.defeatedcrow.api.potion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * AMT2 の MobEffect ベースクラス。 <br>
 * 1.20.1: Potion(int id) 廃止 → {@link MobEffect#MobEffect(MobEffectCategory, int)}。
 * setIconIndex/getStatusIconIndex は 1.20.1 で削除されたため、
 * アイコン座標 (indexX/indexY) のみを保持し、描画は Forge クライアント拡張
 * ({@code IClientMobEffectExtensions}) を実装するクライアント側 (WT-C) に委ねる。 <br>
 * テクスチャ: {@code defeatedcrow:textures/gui/icons_potion.png}
 */
public class PotionBaseAMT extends MobEffect {

    protected static final ResourceLocation texture = new ResourceLocation(
        "defeatedcrow:textures/gui/icons_potion.png");

    private final int indexX;
    private final int indexY;

    public PotionBaseAMT(MobEffectCategory category, int color, int x, int y) {
        super(category, color);
        indexX = x;
        indexY = y;
    }

    /** legacy icon atlas position (x). kept for client rendering. */
    public int getStatusIconIndexX() {
        return indexX;
    }

    /** legacy icon atlas position (y). kept for client rendering. */
    public int getStatusIconIndexY() {
        return indexY;
    }

    public static ResourceLocation getIconsTexture() {
        return texture;
    }

}
