package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.util.MathHelper;
import net.minecraft.core.Direction;
import mods.defeatedcrow.api.recipe.IPlateRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;

public class TileTeppanII extends BlockEntity implements ISidedInventory, IPipeConnection {
    public TileTeppanII(BlockPos pos, BlockState state) { super(null, pos, state); }


    private int cookTime = 0;
    private int cookFinishTime = 0;
    private int cookFailTime = 0;

    private boolean fisnished = false;
    private boolean failed = false;

    // private boolean isOvenMode = false;

    private int lastAmount = 0;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        // アイテムの読み込み
        ListTag nbttaglist = par1CompoundTag.getList("Items", 10);
        this.plateItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            CompoundTag nbttagcompound1 = nbttaglist.getCompound(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.plateItems.length) {
                this.plateItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.cookTime = par1CompoundTag.getShort("CookTime");
        this.cookFinishTime = par1CompoundTag.getShort("FinTime");
        this.cookFailTime = par1CompoundTag.getShort("FailTime");

        this.fisnished = par1CompoundTag.getBoolean("finish");
        this.failed = par1CompoundTag.getBoolean("fail");
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        // 燃焼時間や調理時間などの書き込み
        par1CompoundTag.putShort("CookTime", (short) this.cookTime);
        par1CompoundTag.putShort("FinTime", (short) this.cookFinishTime);
        par1CompoundTag.putShort("FailTime", (short) this.cookFailTime);

        par1CompoundTag.putBoolean("finish", this.fisnished);
        par1CompoundTag.putBoolean("fail", this.failed);

        // アイテムの書き込み
        ListTag nbttaglist = new ListTag();

        for (int i = 0; i < this.plateItems.length; ++i) {
            if (this.plateItems[i] != null) {
                CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.plateItems[i].saveAdditional(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1CompoundTag.put("Items", nbttaglist);

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

    /* ========== get, set ========== */

    public boolean isFinishCooking() {
        return this.fisnished && !this.failed && (this.plateItems[1] != null);
    }

    public boolean isFailed() {
        return this.failed;
    }

    public boolean isReadyToCook() {
        return !this.fisnished && !this.failed && this.plateNoHoldingItem();
    }

    // すべてリセットするメソッド
    public void refreshPlate() {
        this.fisnished = false;
        this.failed = false;
        this.setInventorySlotContents(0, (ItemStack) null);
        this.setInventorySlotContents(1, (ItemStack) null);
        this.setInventorySlotContents(2, (ItemStack) null);
        this.cookTime = 0;
        this.setCookFinishTime(0);
        this.setChanged();
    }

    // 描画系のアップデート
    public void updatePlate() {
        this.level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
        level.neighborChanged(level.getBlockState(pos), level, pos, level.getBlockState(pos).getBlock(), pos, false);
        level.neighborChanged(level.getBlockState(pos), level, pos, level.getBlockState(pos).getBlock(), pos, false);
        this.setChanged();
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public void setCookTime(int par1) {
        this.cookTime = par1;
    }

    public int getCookFinishTime() {
        return this.cookFinishTime;
    }

    // 失敗時間は必ず調理時間の2倍
    public void setCookFinishTime(int par1) {
        int i = DCsConfig.teppannRandomCookTime ? this.level.rand.nextInt(par1 + 1) : par1;
        int k = DCsConfig.teppannReadyTime > 0 ? par1 + DCsConfig.teppannReadyTime : par1 * 2;
        this.cookFinishTime = i;
        this.cookFailTime = k;
    }

    public int getCookFailTime() {
        return this.cookFailTime;
    }

    // 何かしらのアイテムを保持している
    public boolean plateNoHoldingItem() {
        return this.plateItems[0] == null && this.plateItems[1] == null && this.plateItems[2] == null;
    }

    /* ========== Plateのレシピ制御部分 ========== */

    // Blockクラスから使用。レシピがあるアイテムかどうか
    public boolean canSetRecipe(ItemStack item) {
        if (!this.plateNoHoldingItem()) {
            return false;
        }

        if (item == null) return false;

        IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(item);
        if (recipe == null) return false;

        // if (recipe.useOvenRecipe())
        // {
        // return this.isOvenMode();
        // }
        else {
            return true;
        }
    }

    // 投入メソッド
    public boolean setRecipe(ItemStack item) {
        if (!this.plateNoHoldingItem()) return false;
        if (item == null) return false;

        IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(item);
        if (recipe != null) {
            this.setInventorySlotContents(0, item);
            this.setCookFinishTime(recipe.cookingTime());
            return true;
        }
        return false;

    }

    // 熱源の上にいるか
    public boolean isOnHeatSource() {
        if (level.isEmptyBlock(pos.below())) return false;

        Block block = level.getBlockState(pos.below()).getBlock();
        int meta = 0;
        if (block != null) {
            AMTLogger.debugInfo("Current block : " + block.getUnlocalizedName() + ":" + meta);
            return RecipeRegisterManager.plateRecipe.isHeatSource(block, meta);
        } else {
            AMTLogger.debugInfo("Current block is null");
        }
        return false;
    }

    public boolean isOvenMode() {
        int count = 0;
        boolean b = false;

        if (level.canSeeSky(pos)) {
            for (int i = 0; i < 3; i++) {
                if (!level.isEmptyBlock(pos.above(i+1))
                    && level.getBlockState(pos.above(i+1)).getBlock()
                        .getMaterial() != Material.water) {
                    b = true;
                }
            }
            return false;
        } else {
            b = true;
        }

        for (Direction dir : Direction.values()) {
            if (dir == Direction.DOWN || dir == Direction.UP) continue;
            else {
                int x = getBlockPos().getX() + dir.getStepX();
                int y = getBlockPos().getY();
                int z = getBlockPos().getZ() + dir.getStepZ();
                Block block = level.getBlockState(getBlockPos()).getBlock();
                if (block == null || level.isAirBlock(x, y, z)) continue;

                if (block.getMaterial() != Material.water && block.getMaterial() != Material.air);

                {
                    count++;
                }
            }
        }
        return b && count >= 3;
    }

    // 実処理
    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileTeppanII be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    private void onServerUpdate() { level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3); }

    /* ========== 以下、ISidedInventoryのメソッド ========== */

    private static final int[] slots_top = new int[] { 0 };
    private static final int[] slots_bottom = new int[] { 1, 2 };
    private static final int[] slots_sides = new int[] { 0, 1, 2 };

    public ItemStack[] plateItems = new ItemStack[3];

    // スロット数
    @Override
    public int getSizeInventory() {
        return this.plateItems.length;
    }

    // インベントリ内の任意のスロットにあるアイテムを取得
    @Override
    public ItemStack getStackInSlot(int par1) {
        par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
        return this.plateItems[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
        if (this.plateItems[par1] != null) {
            ItemStack itemstack = null;

            if (this.plateItems[par1].stackSize <= par2) {
                itemstack = this.plateItems[par1];
                this.plateItems[par1] = null;
                return itemstack;
            } else {
                itemstack = this.plateItems[par1].splitStack(par2);

                if (this.plateItems[par1].stackSize == 0) {
                    this.plateItems[par1] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
        if (this.plateItems[par1] != null) {
            ItemStack itemstack = this.plateItems[par1];
            this.plateItems[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    // インベントリ内のスロットにアイテムを入れる
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

        if (par1 > 2) par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。

        this.plateItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    // インベントリの名前
    @Override
    public String getInventoryName() {
        return "Teppan";
    }

    // 多言語対応かどうか
    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    // インベントリ内のスタック限界値
    @Override
    public int getInventoryStackLimit() {
        return 1;// 1個ずつ
    }

    @Override
    public void setChanged() {
        setChanged();
    }

    // par1EntityPlayerがTileEntityを使えるかどうか
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.level.getBlockEntity(this.getBlockPos()) != this ? false
            : par1EntityPlayer.getDistanceSq(this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 0.5D, this.getBlockPos().getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 0 ? (!this.fisnished && this.canSetRecipe(par2ItemStack) ? true : false) : false;
    }

    // ホッパーにアイテムの受け渡しをする際の優先度
    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    // ホッパーからアイテムを入れられるかどうか
    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    // 隣接するホッパーにアイテムを送れるかどうか
    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return par1 != 0;
    }

    // BuildCraft対応
        @Override
    public ConnectOverride overridePipeConnection(PipeType type, Direction with) {
        return ConnectOverride.DISCONNECT;
    }

}
