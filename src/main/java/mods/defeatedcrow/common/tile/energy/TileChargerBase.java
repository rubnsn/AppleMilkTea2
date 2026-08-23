package mods.defeatedcrow.common.tile.energy;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevellyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeItem;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.Util;

/* AMT単体で動作させる場合は、このクラスだけで事足りる */
public class TileChargerBase extends BlockEntity implements WorldlyContainer, IChargeableMachine {
    public TileChargerBase(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CHARGER_BASE.get(), pos, state); }


    // 現在のチャージ量
    protected int chargeAmount = 0;
    // チャージアイテムを溶かす際の判定発生間隔
    private int coolTime = 4;
    public final int MAX_CHARGE = 128000;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        ListTag nbttaglist = par1CompoundTag.getList("Items", 10);
        this.itemstacks = new ItemStack[this.getContainerSize()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            CompoundTag CompoundTag1 = (CompoundTag) nbttaglist.getCompound(i);
            byte b0 = CompoundTag1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemstacks.length) {
                this.itemstacks[b0] = ItemStack.of(CompoundTag1);
            }
        }

        this.chargeAmount = par1CompoundTag.getInt("ChargeAmount");
        this.coolTime = par1CompoundTag.getByte("CoolTime");
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        ListTag nbttaglist = new ListTag();

        for (int i = 0; i < this.itemstacks.length; ++i) {
            if (this.itemstacks[i] != null) {
                CompoundTag CompoundTag1 = new CompoundTag();
                CompoundTag1.putByte("Slot", (byte) i);
                this.itemstacks[i].saveAdditional(CompoundTag1);
                nbttaglist.add(CompoundTag1);
            }
        }

        par1CompoundTag.put("Items", nbttaglist);

        // 燃焼時間や調理時間などの書き込み
        par1CompoundTag.putInt("ChargeAmount", this.chargeAmount);
        par1CompoundTag.putByte("CoolTime", (byte) this.coolTime);
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

    // チャージゲージの描画
    
    public int getBurnTimeRemainingScaled(int par1) {
        return this.chargeAmount * par1 / this.getMaxChargeAmount();
    }

    // チャージが満タンである
    public boolean isFullCharged() {
        return this.chargeAmount == this.getMaxChargeAmount();
    }

    // チャージゲージ上限も変更可能に。
    @Override
    public int getMaxChargeAmount() {
        return MAX_CHARGE;
    }

    /* ゲッターとセッター */

    public void setChargeAmount(int par1) {
        int ret = Math.min(par1, this.getMaxChargeAmount());
        this.chargeAmount = ret;
    }

    @Override
    public int getChargeAmount() {
        return this.chargeAmount;
    }

    /* Container と GUI のためのメソッド */

    public int getUpper() {
        int i = this.chargeAmount;
        int get = i >>> 4;
        return get;
    }

    public int getUnder() {
        int i = this.chargeAmount;
        int get = i & 15;
        return get;
    }

    public void setUpper(int i) {
        int current = this.chargeAmount & 15;
        int get = i << 4;
        get += current;
        get = Math.min(get, MAX_CHARGE);
        this.chargeAmount = get;
    }

    public void setUnder(int i) {
        int currentUpper = this.chargeAmount >>> 4;
        int cur = currentUpper << 4;
        int get = i & 15;
        get += cur;
        get = Math.min(get, MAX_CHARGE);
        this.chargeAmount = get;
    }

    // アプデ処理
    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerBase be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    /* IChargeableMachineのメソッド */

    @Override
    public boolean isActive() {
        return this.coolTime > 0;
    }

    @Override
    public boolean canReceiveChargeItem(ItemStack item) {
        boolean flag = false;
        boolean flag2 = false;
        if (item != null) {
            int i = this.getItemBurnTime(item);
            flag = i > 0 && (this.getChargeAmount() + i <= this.getMaxChargeAmount());
        }

        if (this.getItem(0) == null) {
            flag2 = true;
        } else {
            ItemStack current = this.getItem(0);
            flag2 = net.minecraft.world.item.ItemStack.isSameItemSameTags(item, current) && (current.getCount() + item.getCount() < current.getMaxStackSize());
        }

        return flag && flag2;
    }

    @Override
    public int addCharge(int amount, boolean isSimulate) {
        int eng = this.getChargeAmount();
        int get = amount;
        if (this.isFullCharged()) return 0;

        int ret = Math.min(this.getMaxChargeAmount() - eng, get);

        if (!isSimulate) {
            this.setChargeAmount(eng + ret);
        }

        return ret;
    }

    @Override
    public int extractCharge(int amount, boolean isSimulate) {
        int eng = this.getChargeAmount();
        int get = amount;

        int ret = Math.min(eng, get);

        if (!isSimulate) {
            this.setChargeAmount(eng - ret);
        }

        return ret;
    }

    /**
     * このアイテムのチャージ量
     * 
     * @param par0ItemStack
     *                      チェック対象アイテム
     */
    public int getItemBurnTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null) {
            return 0;
        } else {
            if (ChargeItemManager.chargeItem.getChargeAmount(par0ItemStack) > 0) {
                return ChargeItemManager.chargeItem.getChargeAmount(par0ItemStack);
            } else if (par0ItemStack.getItem() instanceof IBattery) {
                // 充電池の場合、16/4tickずつ減少する。
                IBattery bat = (IBattery) par0ItemStack.getItem();
                int ret = bat.discharge(par0ItemStack, 16, false);
                return ret;
            }

            return 0;
        }
    }

    /**
     * このアイテムがチャージできる燃料であるかどうか
     * 
     * @param par0ItemStack
     *                      チェック対象アイテム
     */
    public boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * 燃料スロットの電池アイテムを処理するメソッド
     */
    public int discharge(ItemStack item, int amount, int slot) {
        this.decrStackSize(slot, 1);
        return amount;
    }

    /**
     * 隣接ブロックからエネルギーを受け入れるメソッド。継承先で中身を入れる。
     * チャージ発生装置からチャージを受け取る
     */
    public int acceptChargeFromDir(Direction dir) {
        // IChargeGeneratorからチャージを受け取れるように
        Direction opposite = dir.getOpposite();
        BlockEntity tile = level.getBlockEntity(pos.relative(dir));
        if (tile instanceof IChargeGenerator) {
            IChargeGenerator device = (IChargeGenerator) tile;
            int get = device.generateCharge(opposite, true);

            if (get > 0) {
                device.generateCharge(opposite, false);
                return get;
            }
        }
        return 0;
    }

    /**
     * 他MODの電池アイテムを対応させるためのメソッド。
     * 残量確認なども含めて、実際に充電できる時のみTrueを返すこと。
     */
    public boolean isChargeableBattery(ItemStack item) {
        return false;
    }

    /**
     * 他MODの電池アイテムを対応させるためのメソッド。
     * ここで充電を増やす。
     * シミュレート可能だが当MOD内では使っていない。
     */
    public int chargeAnotherBattery(ItemStack item, int inc, boolean isSimulate) {
        return 0;
    }

    /**
     * 空容器返却
     */
    public ItemStack batteryContainerItem(ItemStack item) {
        if (item != null && !item.isEmpty()) {
            if (item.getItem() instanceof IChargeItem charge) {
                return charge.returnItem();
            } else {
                ItemStack ret = item.getItem().getCraftingRemainingItem(item);
                return ret == null ? ItemStack.EMPTY : ret;
            }
        }
        return ItemStack.EMPTY;
    }

    /* ========== 以下、ISidedInventoryのメソッド ========== */

    /*
     * 0 : 燃料搬入
     * 1 : 燃料の空容器搬出
     * 2~ : 各Tileで実装される。
     */
    protected int[] slotsTop() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }

    protected int[] slotsBottom() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }

    protected int[] slotsSides() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }

    public ItemStack[] itemstacks = new ItemStack[getContainerSize()];

    // スロット数は各Tileでオーバーライドして増やすこと。2は最低限の値。
    @Override
    public int getContainerSize() {
        return 10;
    }

    // インベントリ内の任意のスロットにあるアイテムを取得
    @Override
    public ItemStack getItem(int par1) {
        return par1 < this.getContainerSize() ? this.itemstacks[par1] : null;
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.itemstacks[par1] != null) {
            ItemStack itemstack;

            if (this.itemstacks[par1].getCount() <= par2) {
                itemstack = this.itemstacks[par1];
                this.itemstacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.itemstacks[par1].split(par2);

                if (this.itemstacks[par1].getCount() == 0) {
                    this.itemstacks[par1] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeItemNoUpdate(int par1) {
        if (this.itemstacks[par1] != null) {
            ItemStack itemstack = this.itemstacks[par1];
            this.itemstacks[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    // インベントリ内のスロットにアイテムを入れる
    @Override
    public void setItem(int par1, ItemStack par2ItemStack) {

        if (par1 > this.getContainerSize()) par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。

        this.itemstacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.getCount() > this.getMaxStackSize()) {
            par2ItemStack.setCount(this.getMaxStackSize());
        }
    }

    // インベントリの名前
    @Override
    public String getContainerName() {
        return "Battery Charger";
    }

    // 多言語対応かどうか
    @Override
    public boolean hasCustomName() {
        return true;
    }

    // インベントリ内のスタック限界値
    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    // par1EntityPlayerがTileEntityを使えるかどうか
    @Override
    public boolean stillValid(Player par1EntityPlayer) {
        return this.level.getBlockEntity(this.getBlockPos()) != this ? false
            : par1EntityPlayer
                .distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D, (double) this.getBlockPos().getZ() + 0.5D)
                <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean canPlaceItem(int par1, ItemStack par2ItemStack) {
        if (par1 == 1) {
            return false;
        } else if (par1 == 0) {
            return this.isItemFuel(par2ItemStack);
        } else {
            return par2ItemStack != null && par2ItemStack.getItem() instanceof IBattery;
        }
    }

    // ホッパーにアイテムの受け渡しをする際の優先度
    @Override
    public int[] getSlotsForFace(int par1) {
        return par1 == 0 ? slotsBottom() : (par1 == 1 ? slotsTop() : slotsSides());
    }

    // ホッパーからアイテムを入れられるかどうか
    @Override
    public boolean canPlaceItemThroughFace(int par1, ItemStack par2ItemStack, int par3) {
        return this.canPlaceItem(par1, par2ItemStack);
    }

    // 隣接するホッパーにアイテムを送れるかどうか
    @Override
    public boolean canTakeItemThroughFace(int par1, ItemStack par2ItemStack, int par3) {
        if (par1 == 1) {
            return true;
        } else if (par1 > 1) {
            if (par2ItemStack != null && par2ItemStack.getItem() instanceof IBattery) {
                IBattery bat = (IBattery) par2ItemStack.getItem();
                return bat.isFullCharged(par2ItemStack);
            } else if (this.isChargeableBattery(par2ItemStack)) {
                return this.chargeAnotherBattery(par2ItemStack, 1, true) == 0;// フルチャージかどうか
            } else {
                return true;// 無関係なアイテムは排出する
            }
        }

        return false;
    }

}
