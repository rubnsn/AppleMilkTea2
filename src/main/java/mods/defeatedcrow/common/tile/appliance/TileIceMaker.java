package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
// BiomeDictionary removed - use TagKey<Biome> + Holder<Biome>
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.recipe.*;

public class TileIceMaker extends BlockEntity implements WorldlyContainer {
    public TileIceMaker(BlockPos pos, BlockState state) { super(null, pos, state); }


    // 現在のチャージ量
    public int chargeAmount;
    // 燃料アイテムのチャージ量
    public int currentItemCharge;
    // 調理時間はかまどと同じ
    public int cookTime;
    // チャージアイテムを溶かす際の判定発生間隔
    // 温暖バイオームでのチャージ減少判定にも使用
    private int coolTime = 8;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        // アイテムの読み込み
        ListTag nbttaglist = par1CompoundTag.getList("Items", 10);
        this.iceItemStacks = new ItemStack[this.getContainerSize()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            CompoundTag nbttagcompound1 = (CompoundTag) nbttaglist.getCompound(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.iceItemStacks.length) {
                this.iceItemStacks[b0] = ItemStack.of(nbttagcompound1);
            }
        }

        this.chargeAmount = par1CompoundTag.getShort("ChargeAmount");
        this.cookTime = par1CompoundTag.getShort("CookTime");
        this.coolTime = par1CompoundTag.getByte("CoolTime");

    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        // 燃焼時間や調理時間などの書き込み
        par1CompoundTag.putShort("ChargeAmount", (short) this.chargeAmount);
        par1CompoundTag.putShort("CookTime", (short) this.cookTime);
        par1CompoundTag.putByte("CoolTime", (byte) this.coolTime);

        // アイテムの書き込み
        ListTag nbttaglist = new ListTag();

        for (int i = 0; i < this.iceItemStacks.length; ++i) {
            if (this.iceItemStacks[i] != null) {
                CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.iceItemStacks[i].saveAdditional(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
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

    // 調理中の矢印の描画
    
    public int getCookProgressScaled(int par1) {
        return this.cookTime * par1 / 150;
    }

    // チャージゲージの描画
    
    public int getBurnTimeRemainingScaled(int par1) {
        return this.chargeAmount * par1 / 127;
    }

    // 調理中
    public boolean isBurning() {
        return this.cookTime > 0;
    }

    // 調理中
    public boolean isCharged() {
        return this.chargeAmount > 0;
    }

    // 以下はパケット送受信用メソッド
    public void setChargeAmount(int par1) {
        this.chargeAmount = par1;
    }

    public int getChargeAmount() {
        return this.chargeAmount;
    }

    /**
     * Tick毎のアイスメーカーの処理。ほぼバニラかまどのパクリ。
     */
    public static void tick(Level level, BlockPos pos, BlockState state, TileIceMaker be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    /**
     * アイテムがIceMakerにレシピ登録された材料かどうか
     * レシピ登録はTeaMakerと類似の登録制を使用
     */
    private boolean canSmelt() {
        if (be.iceItemStacks[0] == null) {
            return false;
        } else {
            IIceRecipe recipe = RecipeRegisterManager.iceRecipe.getRecipe(be.iceItemStacks[0]);

            if (recipe != null) {

                // スタックサイズのチェック
                if (be.iceItemStacks[0].getCount() < recipe.getInput().getCount()) return false;

                if (recipe.getContainer() != null) {
                    ItemStack container = recipe.getContainer();
                    ItemStack output = recipe.getOutput();

                    if (output == null || container == null) return false;
                    boolean flag1 = false;
                    boolean flag2 = false;

                    if (be.iceItemStacks[2] == null) {
                        flag1 = true;
                    } else {
                        if (be.iceItemStacks[2].isItemEqual(output)) {
                            int result = be.iceItemStacks[2].getCount() + output.getCount();
                            flag1 = (result <= be.getMaxStackSize() && result <= output.getMaxStackSize());
                        }
                    }

                    if (be.iceItemStacks[3] == null) {
                        flag2 = true;
                    } else {
                        if (be.iceItemStacks[3].isItemEqual(container)) {
                            int leave = be.iceItemStacks[3].getCount() + container.getCount();
                            flag2 = (leave <= be.getMaxStackSize() && leave <= container.getMaxStackSize());
                        }
                    }

                    return (flag1 && flag2);
                } else {
                    ItemStack output = recipe.getOutput();

                    if (output == null) return false;

                    if (be.iceItemStacks[2] == null) return true;
                    if (!be.iceItemStacks[2].isItemEqual(output)) return false;

                    int result = be.iceItemStacks[2].getCount() + output.getCount();
                    return (result <= be.getMaxStackSize() && result <= output.getMaxStackSize());
                }
            }

            return false;
        }
    }

    /**
     * 実際に材料を消費して、完成スロットにアウトプットを返すためのメソッド
     */
    public void smeltItem() {
        if (this.canSmelt()) {
            IIceRecipe recipe = RecipeRegisterManager.iceRecipe.getRecipe(this.iceItemStacks[0]);
            ItemStack itemstack = recipe.getOutput();
            ItemStack container = recipe.getContainer();

            if (this.iceItemStacks[0].getCount() < recipe.getInput().getCount()) return;

            if (this.iceItemStacks[2] == null) {
                this.iceItemStacks[2] = itemstack.copy();
            } else if (this.iceItemStacks[2].isItemEqual(itemstack)) {
                this.iceItemStacks[2].getCount() += itemstack.getCount();
            }

            if (container != null)// 材料スロットに残すアイテム
            {
                if (this.iceItemStacks[3] == null) {
                    this.iceItemStacks[3] = container.copy();
                } else if (this.iceItemStacks[3].isItemEqual(container)) {
                    this.iceItemStacks[3].getCount() += container.getCount();
                }
            }

            this.iceItemStacks[0].getCount() -= recipe.getInput().getCount();

            if (this.iceItemStacks[0].getCount() <= 0) {
                this.iceItemStacks[0] = null;
            }

            // チャージを消費
            if (this.isHotBiome() == 0) {
                --this.chargeAmount;
            } else {
                this.chargeAmount -= this.isHotBiome() * 2;
            }
            if (this.chargeAmount < 0) this.chargeAmount = 0;
        }
    }

    /**
     * 現在のチャージ残量が、稼働に必要な分だけあるか。バイオームごとに異なるため専用メソッドで判定。
     */
    public boolean enoughCharge() {
        int biome = this.isHotBiome();
        if (biome == 0) {
            return this.isCharged();
        } else if (biome == 2) {
            return (this.chargeAmount > 3);
        } else {
            return (this.chargeAmount > 1);
        }
    }

    /**
     * このアイテムのチャージ量
     * 
     * @param par0ItemStack
     *                      チェック対象アイテム
     */
    public static int getItemBurnTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null) {
            return 0;
        } else {
            // Item item = par0ItemStack.getItem();
            //
            // if (par0ItemStack.getItem() instanceof ItemBlock && Block.getBlockFromItem(item) != null)
            // {
            // Block block = Block.getBlockFromItem(item);
            //
            // if (block.getMaterial() == /*Material*/ craftedSnow)
            // {
            // return 4;
            // }
            //
            // if (block.getMaterial() == /*Material*/ ice)
            // {
            // return 8;
            // }
            // }
            // if (item == Items.snowball) return 1;

            if (RecipeRegisterManager.iceRecipe.getChargeAmount(par0ItemStack) > 0) {
                return RecipeRegisterManager.iceRecipe.getChargeAmount(par0ItemStack);
            }
            return 0;
        }
    }

    /**
     * このアイテムがIceMakerにチャージできる冷媒であるかどうか
     * 
     * @param par0ItemStack
     *                      チェック対象アイテム
     */
    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * 暑いバイオームかどうか
     * 砂漠系・ジャングル系バイオームでは2、普通・温暖なバイオームでは1、寒冷バイオームでは0を返す。
     * （BiomeDictionaryを利用しているため他MODの追加バイオームでも正常に機能するはず。）
     * どれにも属さない（BiomeDictionaryに登録していない）場合は一律で1。
     */
    public int isHotBiome() {
        // 1.20.1: BiomeDictionary -> TagKey<Biome> + Holder<Biome>
        net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome> biomeHolder = level.getBiome(getBlockPos());
        int l = 1;
        if (biomeHolder.is(net.minecraft.tags.BiomeTags.IS_DESERT) || biomeHolder.is(net.minecraft.tags.BiomeTags.IS_JUNGLE) || biomeHolder.is(net.minecraft.tags.BiomeTags.IS_SAVANNA)) {
            l = 2;
        } else if (biomeHolder.is(net.minecraft.tags.BiomeTags.IS_SNOWY) || biomeHolder.is(net.minecraft.tags.BiomeTags.IS_TAIGA)) {
            l = 0;
        } else {
            l = 1;
        }
        return l;
    }

    /* ========== 以下、ISidedInventoryのメソッド ========== */

    private static final int[] slots_top = new int[] { 0 };
    private static final int[] slots_bottom = new int[] { 2, 3, 1 };
    private static final int[] slots_sides = new int[] { 1 };

    public ItemStack[] iceItemStacks = new ItemStack[4];

    // スロット数
    @Override
    public int getContainerSize() {
        return this.iceItemStacks.length;
    }

    // インベントリ内の任意のスロットにあるアイテムを取得
    @Override
    public ItemStack getItem(int par1) {
        return this.iceItemStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.iceItemStacks[par1] != null) {
            ItemStack itemstack;

            if (this.iceItemStacks[par1].getCount() <= par2) {
                itemstack = this.iceItemStacks[par1];
                this.iceItemStacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.iceItemStacks[par1].splitStack(par2);

                if (this.iceItemStacks[par1].getCount() == 0) {
                    this.iceItemStacks[par1] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeItemNoUpdate(int par1) {
        if (this.iceItemStacks[par1] != null) {
            ItemStack itemstack = this.iceItemStacks[par1];
            this.iceItemStacks[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    // インベントリ内のスロットにアイテムを入れる
    @Override
    public void setItem(int par1, ItemStack par2ItemStack) {

        if (par1 > 3) par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。

        this.iceItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.getCount() > this.getMaxStackSize()) {
            par2ItemStack.getCount() = this.getMaxStackSize();
        }
    }

    // インベントリの名前
    @Override
    public String getContainerName() {
        return "Ice Maker";
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
        return par1 > 1 ? false : (par1 == 1 ? this.isItemFuel(par2ItemStack) : true);
    }

    // ホッパーにアイテムの受け渡しをする際の優先度
    @Override
    public int[] getSlotsForFace(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    // ホッパーからアイテムを入れられるかどうか
    @Override
    public boolean canPlaceItemThroughFace(int par1, ItemStack par2ItemStack, int par3) {
        return this.canPlaceItem(par1, par2ItemStack);
    }

    // 隣接するホッパーにアイテムを送れるかどうか
    @Override
    public boolean canTakeItemThroughFace(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 0 || par1 != 1;
    }

}
