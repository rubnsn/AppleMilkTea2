package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.api.recipe.IPanRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;

public class TilePanG extends BlockEntity {
    public TilePanG(BlockPos pos, BlockState state) { super(null, pos, state); }


    private byte remain = 1;
    private boolean direction = false;

    private ItemStack input = null;
    private String tex = "defeatedcrow:textures/blocks/contents_rice.png";

    private byte coolTime = 0;

    // NBT
    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        if (par1CompoundTag.contains("Input")) {
            this.setItemStack(ItemStack.loadItemStackFromNBT(par1CompoundTag.getCompound("Input")));
        }

        this.remain = par1CompoundTag.getByte("Remaining");
        this.direction = par1CompoundTag.getBoolean("Direction");
        this.tex = par1CompoundTag.getString("Tex");
        this.coolTime = par1CompoundTag.getByte("CoolTime");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        par1CompoundTag.putByte("Remaining", this.remain);
        par1CompoundTag.putBoolean("Direction", this.direction);
        par1CompoundTag.setString("Tex", tex);
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
        CompoundTag nbtTagCompound = new CompoundTag();
        this.saveAdditional(nbtTagCompound);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public byte getRemainByte() {
        return this.remain;
    }

    public void setRemainByte(byte par1) {
        this.remain = par1;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void setDirection(boolean par1) {
        this.direction = par1;
    }

    public ItemStack getItemStack() {
        return this.input;
    }

    public void setItemStack(ItemStack item) {
        this.input = item;
        this.setTexture(item);
        this.setChanged();
    }

    public String getCurrentTexture() {
        return this.tex;
    }

    public String getDisplayName() {
        String s = "Empty";
        if (this.getRecipe() != null) {
            s = this.getRecipe()
                .getDisplayName();
        }
        return s;
    }

    public void setTexture(ItemStack input) {
        if (input == null) {
            this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
            return;
        } else {
            IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
            if (recipe != null) {
                String s = recipe.getTex();
                if (s.contains(":")) {
                    this.tex = s;
                } else {
                    this.tex = "defeatedcrow:textures/blocks/contents_" + s + ".png";
                }
            } else {
                this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
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

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TilePanG be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    public IPanRecipe getRecipe() {
        if (this.input == null) return null;
        if (this.input != null) {
            IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(this.input);
            return recipe;
        }
        return null;
    }

    public ItemStack getOutput() {
        if (this.input != null) {
            IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
            if (recipe != null) {
                return recipe.getOutput();
            }
        }
        return null;
    }

    public ItemStack getOutputJP() {
        if (this.input != null) {
            IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
            if (recipe != null) {
                return recipe.getOutputJP();
            }
        }
        return null;
    }

    public void clearTile() {
        this.input = null;
        this.remain = 0;
        this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
    }
}
