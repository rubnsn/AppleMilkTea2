import pathlib, re
root = pathlib.Path(r"E:\AMT2-WT-B")

# Fix DCsBonemealEvent
p = root / "src/main/java/mods/defeatedcrow/event/DCsBonemealEvent.java"
t = p.read_text(encoding='utf-8')
new = """package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.plants.BlockCassisTree;
import mods.defeatedcrow.common.block.plants.BlockMintCrop;
import mods.defeatedcrow.common.block.plants.BlockSaplingTea;
import mods.defeatedcrow.common.block.plants.BlockTeaTree;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;

/**
 * 1.20.1: BonemealEvent now uses Level + BlockPos + BlockState (no int x,y,z)
 * See doc/events/migration-guide.md
 */
public class DCsBonemealEvent {

    @SubscribeEvent
    public void useBoneMeal(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Block block = level.getBlockState(pos).getBlock();
        if (block == DCsAppleMilk.cropMint) {
            if (((BlockMintCrop) DCsAppleMilk.cropMint).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.cassisTree) {
            if (((BlockCassisTree) DCsAppleMilk.cassisTree).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.teaTree) {
            if (((BlockTeaTree) DCsAppleMilk.teaTree).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.saplingTea) {
            if (((BlockSaplingTea) DCsAppleMilk.saplingTea).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.saplingYuzu) {
            if (((BlockYuzuSapling) DCsAppleMilk.saplingYuzu).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        }
    }

}
"""
p.write_text(new, encoding='utf-8')
print("fixed DCsBonemealEvent")

# Fix DispenserEvent
p = root / "src/main/java/mods/defeatedcrow/event/DispenserEvent.java"
t = p.read_text(encoding='utf-8')
new = """package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerNext;
import mods.defeatedcrow.common.tile.TileIncenseBase;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;

/**
 * 1.20.1: BlockDispenser -> DispenserBlock, IBlockSource -> BlockSource, EnumFacing -> Direction, BlockPos
 * See doc/events/migration-guide.md
 */
public class DispenserEvent {

    public static DispenserEvent instance = new DispenserEvent();

    private DispenserEvent() {}

    public void init() {
        // firestarter
        DispenserBlock.registerBehavior(DCsAppleMilk.firestarter, new DefaultDispenseItemBehavior() {
            private boolean flag = true;
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                Level level = source.getLevel();
                BlockPos pos = source.getPos().relative(dir);
                if (level.isEmptyBlock(pos)) {
                    level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
                    if (stack.hurt(1, level.getRandom(), null)) stack.setCount(0);
                } else if (level.getBlockState(pos).is(Blocks.TNT)) {
                    Blocks.TNT.onCaughtFire(level.getBlockState(pos), level, pos, dir, null);
                    level.removeBlock(pos, false);
                } else if (level.getBlockState(pos).getBlock() == DCsAppleMilk.incenseBase) {
                    if (level.getBlockEntity(pos) instanceof TileIncenseBase tile && tile.hasItem() && !tile.getActive()) {
                        if (stack.hurt(1, level.getRandom(), null)) stack.setCount(0);
                        tile.setActive();
                        tile.setChanged();
                        level.setBlock(pos, level.getBlockState(pos).setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT, true), 3);
                        level.scheduleTick(pos, DCsAppleMilk.incenseBase, 20);
                    }
                } else {
                    this.flag = false;
                }
                return stack;
            }
            @Override
            protected void playSound(BlockSource source) {
                if (this.flag) source.getLevel().levelEvent(1000, source.getPos(), 0);
                else source.getLevel().levelEvent(1001, source.getPos(), 0);
            }
        });
    }

    public void registerTeaMakerEvent(ItemStack item) {
        if (item.getItem() == Items.ORANGE_DYE) return;
        DispenserBlock.registerBehavior(item.getItem(), new DefaultDispenseItemBehavior() {
            private boolean flag = true;
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                Level level = source.getLevel();
                BlockPos pos = source.getPos().relative(dir);
                if (!level.isClientSide && level.getBlockState(pos).getBlock() instanceof BlockTeaMakerNext) {
                    if (level.getBlockEntity(pos) instanceof TileMakerNext tile && tile.getItemStack() == null) {
                        ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(stack);
                        if (recipe != null) {
                            tile.setRecipe(new ItemStack(stack.getItem(), 1));
                            tile.setRemain((byte) 3);
                            tile.setChanged();
                            level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
                            stack.shrink(1);
                            flag = true;
                        }
                    }
                } else {
                    this.flag = false;
                }
                return flag ? stack : super.execute(source, stack);
            }
            @Override
            protected void playSound(BlockSource source) {
                if (this.flag) source.getLevel().levelEvent(1009, source.getPos(), 0);
                else source.getLevel().levelEvent(1001, source.getPos(), 0);
            }
        });
    }

    public void registerFluidDispence() {
        DispenserBlock.registerBehavior(DCsAppleMilk.bucketCamOil, new DefaultDispenseItemBehavior() {
            private boolean flag = true;
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                Level level = source.getLevel();
                BlockPos pos = source.getPos().relative(dir);
                if (!level.isClientSide && level.isEmptyBlock(pos)) {
                    level.setBlock(pos, DCsAppleMilk.blockCamelliaOil.defaultBlockState(), 3);
                    flag = true;
                    return new ItemStack(Items.BUCKET);
                }
                flag = false;
                return stack;
            }
            @Override
            protected void playSound(BlockSource source) {
                if (this.flag) source.getLevel().levelEvent(1009, source.getPos(), 0);
                else source.getLevel().levelEvent(1001, source.getPos(), 0);
            }
        });
        DispenserBlock.registerBehavior(DCsAppleMilk.bucketVegiOil, new DefaultDispenseItemBehavior() {
            private boolean flag = true;
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                Level level = source.getLevel();
                BlockPos pos = source.getPos().relative(dir);
                if (!level.isClientSide && level.isEmptyBlock(pos)) {
                    level.setBlock(pos, DCsAppleMilk.blockVegitableOil.defaultBlockState(), 3);
                    flag = true;
                    return new ItemStack(Items.BUCKET);
                }
                flag = false;
                return stack;
            }
            @Override
            protected void playSound(BlockSource source) {
                if (this.flag) source.getLevel().levelEvent(1009, source.getPos(), 0);
                else source.getLevel().levelEvent(1001, source.getPos(), 0);
            }
        });
    }
}
"""
p.write_text(new, encoding='utf-8')
print("fixed DispenserEvent")

# Fix FluidDispenser
p = root / "src/main/java/mods/defeatedcrow/event/FluidDispenser.java"
new = """package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * 1.20.1: BlockDispenser.dispenseBehaviorRegistry -> DispenserBlock.registerBehavior
 * IBlockSource -> BlockSource, func_149937_b -> getValue(FACING), BlockPos
 */
public class FluidDispenser {

    private FluidDispenser() {}

    public static void load() {
        // Bucket pickup / place is now handled via DispenserBlock.registerBehavior for oils
        // Combined handler for bucket -> check for oil blocks at dispense pos
        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Level level = source.getLevel();
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos pos = source.getPos().relative(dir);
                if (!level.isClientSide) {
                    if (level.getBlockState(pos).is(mods.defeatedcrow.common.registry.ModBlocks.BLOCK_CAMELLIA_OIL.get())) {
                        level.removeBlock(pos, false);
                        level.levelEvent(1009, pos, 0);
                        return new ItemStack(mods.defeatedcrow.common.DCsAppleMilk.bucketCamOil);
                    }
                    if (level.getBlockState(pos).is(mods.defeatedcrow.common.registry.ModBlocks.BLOCK_VEGI_OIL.get())) {
                        level.removeBlock(pos, false);
                        level.levelEvent(1009, pos, 0);
                        return new ItemStack(mods.defeatedcrow.common.DCsAppleMilk.bucketVegiOil);
                    }
                }
                return super.execute(source, stack);
            }
        });
    }
}
"""
p.write_text(new, encoding='utf-8')
print("fixed FluidDispenser")
