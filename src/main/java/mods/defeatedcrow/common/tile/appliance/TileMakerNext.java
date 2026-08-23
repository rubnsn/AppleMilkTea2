package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.api.appliance.ITeaMaker;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

public class TileMakerNext extends BlockEntity implements ITeaMaker {
    public TileMakerNext(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_MAKER_NEXT.get(), pos, state); }


    private byte remain = 1;
    private byte contentsID = 0;
    private boolean isMilk = false;

    private ItemStack input = null;
    private String tex = "defeatedcrow:textures/blocks/contents_water.png";
    private String tex_milk = "defeatedcrow:textures/blocks/contents_water.png";

    private byte coolTime = 0;

    // NBT
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        if (par1CompoundTag.contains("Input")) {
            this.setItemStack(ItemStack.of(par1CompoundTag.getCompound("Input")));
        }

        this.remain = par1CompoundTag.getByte("Remaining");
        this.isMilk = par1CompoundTag.getBoolean("Milk");
        this.tex = par1CompoundTag.getString("Tex");
        this.tex_milk = par1CompoundTag.getString("Tex_Milk");
        this.coolTime = par1CompoundTag.getByte("CoolTime");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        par1CompoundTag.putByte("Remaining", this.remain);
        par1CompoundTag.putBoolean("Milk", this.isMilk);
        par1CompoundTag.putString("Tex", tex);
        par1CompoundTag.putString("Tex_Milk", tex_milk);
        par1CompoundTag.putByte("CoolTime", this.coolTime);

        if (this.getItemStack() != null) {
            par1CompoundTag.put(
                "Input",
                this.getItemStack()
                    .saveAdditional(new CompoundTag()));
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    /* 以下はITeaRecipeのメソッド */

    @Override
    public ITeaRecipe getRecipe() {
        if (Util.notEmptyItem(input)) {
            return RecipeRegisterManager.teaRecipe.getRecipe(input);
        } else {
            return null;
        }

    }

    @Override
    public void setRecipe(ItemStack item) {
        this.input = item;
        this.setTexture(item);
        this.setRemain((byte) (3 + this.level.rand.nextInt(3)));
        this.updateTeaMaker();
    }

    @Override
    public byte getRemain() {
        return this.remain;
    }

    @Override
    public void setRemain(byte par1) {
        this.remain = par1;
    }

    @Override
    public boolean getMilked() {
        return this.isMilk;
    }

    @Override
    public void setMilk(boolean flag) {
        this.isMilk = flag;
        this.remain = 3;
        this.updateTeaMaker();
    }

    @Override
    public ItemStack getOutput() {
        if (this.input != null) {
            ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(input);
            if (recipe != null) {
                if (this.isMilk && recipe.getOutputMilk() != null) {
                    return recipe.getOutputMilk();
                } else {
                    return recipe.getOutput();
                }
            }
        }
        return null;
    }

    @Override
    public boolean canSetRecipe(ItemStack item) {
        if (this.input != null) return false;

        ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(item);

        return recipe != null;

    }

    public ItemStack getItemStack() {
        return this.input;
    }

    private void setItemStack(ItemStack item) {
        this.input = item;
    }

    public String getCurrentTexture() {
        return this.isMilk ? this.tex_milk : this.tex;
    }

    private void setTexture(ItemStack input) {
        if (input == null) {
            this.tex = "defeatedcrow:textures/blocks/contents_water.png";
            this.tex_milk = "defeatedcrow:textures/blocks/contents_water.png";
            return;
        } else {
            ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(input);
            if (recipe != null) {
                this.tex = recipe.getTex();
                if (recipe.getMilkTex() != null) {
                    this.tex_milk = recipe.getMilkTex();
                } else {
                    this.tex_milk = recipe.getTex();
                }
            } else {
                this.tex = "defeatedcrow:textures/blocks/contents_water.png";
                this.tex_milk = "defeatedcrow:textures/blocks/contents_water.png";
            }
        }
    }

    private byte getCoolTime() {
        return this.coolTime;
    }

    private void setCoolTime(byte t) {
        this.coolTime = t;
    }

    public int getMetadata() { return 0; }
}
