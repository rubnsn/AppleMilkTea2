package mods.defeatedcrow.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class AddItemModifier extends LootModifier {
    public static final Codec<AddItemModifier> CODEC = RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst).and(
        inst.group(
            ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item),
            Codec.INT.fieldOf("minCount").orElse(1).forGetter(m -> m.minCount),
            Codec.INT.fieldOf("maxCount").orElse(1).forGetter(m -> m.maxCount),
            Codec.FLOAT.fieldOf("chance").orElse(1.0F).forGetter(m -> m.chance)
        )).apply(inst, AddItemModifier::new));

    private final Item item;
    private final int minCount;
    private final int maxCount;
    private final float chance;

    public AddItemModifier(LootItemCondition[] conditions, Item item, int minCount, int maxCount, float chance) {
        super(conditions);
        this.item = item;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        RandomSource rand = context.getRandom();
        if (rand.nextFloat() < chance) {
            int count = minCount;
            if (maxCount > minCount) count = minCount + rand.nextInt(maxCount - minCount + 1);
            generatedLoot.add(new ItemStack(item, count));
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() { return CODEC; }
}
