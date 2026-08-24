package mods.defeatedcrow.client.model.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import java.util.function.Consumer;

/**
 * BlockItem that provides BEWLR for TESR blocks so inventory shows 3D model instead of white cube.
 * Uses TESRItemRenderer (BlockEntityWithoutLevelRenderer) for rendering.
 */
public class TESRBlockItem extends BlockItem {
    public TESRBlockItem(Block block, Properties props) {
        super(block, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private TESRItemRenderer renderer;

            @Override
            public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new TESRItemRenderer(Minecraft.getInstance(), Minecraft.getInstance().getEntityModels());
                }
                return renderer;
            }
        });
    }
}
