package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
// BiomeDictionary removed - use TagKey<Biome> + Holder<Biome>
/*
 * 熟成時間の処理と、完了したかどうかの判定を持つ。
 * 直射日光は厳禁。日光に当てると熟成時間がリセットされてしまう。
 */
public class TileCordial extends BlockEntity {
    public TileCordial(BlockPos pos, BlockState state) { super(null, pos, state); }


    private int aging = 0;
    private boolean isAged = false;

    // NBT
    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.aging = par1CompoundTag.getInt("Remaining");
        this.isAged = par1CompoundTag.getBoolean("IsAged");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putInt("Remaining", this.aging);
        par1CompoundTag.putBoolean("IsAged", this.isAged);
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

    public int getAgingTime() {
        return this.aging;
    }

    public void setAgingTime(int par1) {
        this.aging = par1;
    }

    public boolean getAged() {
        return this.isAged;
    }

    public void setAged(boolean par1) {
        this.isAged = par1;
    }

    // レンダー用の熟成段階取得メソッド。一日ごとに色が濃くなっていく。
    public int getAgingStage() {
        int i = this.aging / 6000;
        return i;
    }

    public void setAgingStage(int par1) {
        int i = par1 * 6000;
        this.aging = i;
    }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileCordial be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    public int getMetadata() { return 0; }

        return flag;
    }

    public boolean isDryBiome() { return level != null && level.getBiome(getBlockPos()).is(net.minecraft.tags.BiomeTags.IS_DESERT); }

}
