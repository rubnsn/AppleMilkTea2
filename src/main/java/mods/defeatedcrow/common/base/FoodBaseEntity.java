package mods.defeatedcrow.common.base;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import mods.defeatedcrow.common.entity.edible.PlaceableFoods;

/**
 * AMT2のソースを利用して食べ物の見た目を持つEntityを追加する場合、このクラスをextendsして下さい。<br>
 * Entityの仕様はAMT2のEntity類(PlaceableFoods)に準拠します。<br>
 * 異なる仕様にしたい場合は、PlaceableFoodsのメソッドをオーバーライドして上書きして下さい。
 * <p>1.20.1 migration: former atlas icon removed - soup texture is now a ResourceLocation.
 * Former atlas sprite (meta -&gt; icon) is replaced by entity renderer using
 * {@link mods.defeatedcrow.client.entity.base.RenderFoodEntityBase} with
 * {@code RenderType.entityCutout(ResourceLocation)}.</p>
 */
public abstract class FoodBaseEntity extends PlaceableFoods implements IFoodType {

    public FoodBaseEntity(Level world) {
        super(world);
    }

    public FoodBaseEntity(Level world, ItemStack item) {
        super(world, true, item);
    }

    public FoodBaseEntity(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    /**
     * 1.20.1: soup / inner contents texture for the given meta.
     * @param meta containerMeta (entityData 17)
     * @return texture location, e.g. {@code defeatedcrow:textures/items/contents/basesoup_WATER.png}
     *         or null to use default inner texture in renderer
     */
    public abstract ResourceLocation getSoupIcon(int meta);

}
