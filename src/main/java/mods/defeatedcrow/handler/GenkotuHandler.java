package mods.defeatedcrow.handler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * 1.20.1 GenkotuHandler - mob drop helper (was reflection getDropItem).
 * Now uses loot tables; stub returns empty.
 */
public class GenkotuHandler {

    public static ItemStack getMobsDrop(LivingEntity entity) {
        // TODO: restore loot table based drop when needed (was ReflectionHelper.getDropItem)
        return ItemStack.EMPTY;
    }
}
