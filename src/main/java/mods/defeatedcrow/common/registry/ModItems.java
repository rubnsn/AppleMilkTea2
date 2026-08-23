package mods.defeatedcrow.common.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 Item registry — DeferredRegister + Item.Properties.
 * Bootstrap-owned skeleton. See doc/items/migration-guide.md:33
 * NBT維持: DataComponentsは1.20.5+なので1.20.1では導入しない (doc/items/migration-guide.md:220).
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "defeatedcrow");

    // --- WT-A: FOOD/INGREDIENT (bakedApple, appleTart, toffyApple, leafTea splits, gratedApple, mincedFoods, yeast, moromi, etc.) ---
    // Example:
    // public static final RegistryObject<Item> BAKED_APPLE = ITEMS.register("baked_apple",
    //     () -> new ItemBakedApple(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build())));

    // --- WT-A: MATERIALS (EXItems, inkStick, foodTea, DCgrater, icyCrystal, itemMintSeed, stickCarbon, oreDust, dustWood, essentialOil, strangeSlag, fossilScale, etc.) ---

    // --- WT-A: TOOLS (chalcedonyKnife, firestarter, chalcedonyHammer, monocle, onixSword, pruningShears, chopsticks, milkBottle, yuzuGatling, fossilCannon, eightEyesArm) ---

    // --- WT-A: BLOCK-ITEMS (teaMakerNext, woodBox, etc. — BlockItem wrappers) ---
    // public static final RegistryObject<Item> TEA_MAKER_NEXT = ITEMS.register("tea_maker_next",
    //     () -> new BlockItem(ModBlocks.TEA_MAKER_NEXT.get(), new Item.Properties()));

    // --- WT-A: BREWING ITEMS (itemLargeBottle, itemCordial, bucketYoungAlcohol moromi, etc.) ---

    // --- WT-C: INCENSE (incenseApple..incenseVanilla 11種) — may stay in ModItems or move to WT-C section ---

    private ModItems() {}
}
