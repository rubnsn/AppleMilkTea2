package mods.defeatedcrow.client.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.ItemStack;

/**
 * EdibleEntityのレンダ―モデルやテクスチャパスを返すインターフェイス。
 * ボツにつき現在不使用。
 */
@Deprecated
public interface IEdibleRenderHandler {

    ItemStack getItem();

    Class<? extends ModelPart> getModel();

    String getEntityTex(int meta);

    String getEntityGlowTex(int meta);

    String getEntityClearTex(int meta);

}
