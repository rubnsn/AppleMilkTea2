# TileEntity 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2` 全45 TileEntity / `CommonProxy.java:70-117`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) / [Block移行ガイド](../blocks/migration-guide.md)

## 概要
45 TileEntityの 1.12.2/1.16 → **1.20.1** 移行対応を整理。`_template.md` の詳細版。**1.20.1では `TileEntity` は `BlockEntity` に全面置換、`BlockEntityType.Builder.create` → `Builder.of`、`DeferredRegister<BlockEntityType<?>>` + Holder、さらに同期パケットとGUI（`MenuType`/`MenuProvider`）の分離、Capability→`IItemHandler`/`IEnergyStorage` のLazyOptional据置が確定。ネットワークは [network migration](../network/migration-guide.md) のSimpleChannelと共通。**

> **ギャップ補足**（plan.md監査）: 旧DOCは `BlockEntityType.create/BlockEntity` を 1.16止まりで記載。1.20.1では `BlockEntityType.Builder.of(Supplier, Block...)` → `.build(null)` → `DeferredRegister` の定型、加えてTicker/`getTicker`/`BlockEntityWithoutLevelRenderer` への置換が必要。本追記で補完。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `GameRegistry.registerTileEntity(TileMakerNext.class, "TileMakerNext")` (`CommonProxy.java:85`) | `@SubscribeEvent Register<TileEntityType<?>>` で `TileEntityType.Builder.create(TileMakerNext::new, validBlocks).build(null).setRegistryName("defeatedcrow","tile_maker_next")` | `DeferredRegister<BlockEntityType<?>>` + `BlockEntityType.Builder.of(TileMakerNext::new, validBlocks).build(null)` | **同左 + `Registries.BLOCK_ENTITY_TYPE` + `DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "defeatedcrow")`** → `TYPES.register("tile_maker_next", ()-> BlockEntityType.Builder.of(TileMakerNext::new, ModBlocks.TEA_MAKER_NEXT.get()).build(null))` | `CommonProxy.java:70` |
| `BlockContainer` + `createNewTileEntity(World,int)` | `Block` + `hasTileEntity(IBlockState)` + `createTileEntity(World,IBlockState)` → 1.14+ `createTileEntity(BlockState,IBlockReader)` | `BlockEntity` 化、`Block` は `EntityBlock` 実装 + `newBlockEntity(BlockPos,BlockState)` + `getTicker(Level,BlockState,BlockEntityType)` | **同左 + `BlockBehaviour.Properties` に変更なし。`BlockEntity` は `BlockEntityType<?>` をコンストラクタ引数に持つ (`BlockEntity(BlockEntityType<?>, BlockPos, BlockState)`)** | `block/appliance/BlockTeaMakerNext.java:332` |
| `TileEntity` 直 | 1.16+ `BlockEntity` にリネーム | 維持 (`BlockEntity`) | **維持。`TileEntity` 参照は全削除** | 全Tile |
| `xCoord/yCoord/zCoord` | `pos.getX()/getY()/getZ()` / `worldPosition` | `worldPosition` / `getBlockPos()` | **同左 (`getBlockPos()`, `level`)** | 全Tile |
| `worldObj` | `level` / `world` | `level` | **同左 (`level`, `Level`)** | 全Tile |
| `S35PacketUpdateTileEntity(x,y,z,1,nbt)` / `getDescriptionPacket()` | `SUpdateTileEntityPacket` / `getUpdatePacket()` / `ClientboundBlockEntityDataPacket` (1.16) | `ClientboundBlockEntityDataPacket.create(this)` | **同左 + `getUpdateTag()` は `saveAdditional(CompoundTag)` / `load(CompoundTag)` に分離** | `TileMakerNext.java:xx` |
| `GameRegistry.findBlock` / `ItemAPI` | `ForgeRegistries.BLOCKS` | `Registries.BLOCK` / `Holder` | **同左 + `Holder<Block>` / `RegistryObject<Block>`** | `api/ItemAPI.java:54` |

## メソッド別移行表

| メソッド | 1.7.10 | 1.12.2 | 1.16.5 | 1.20.1 | 対応例 |
|---|---|---|---|---|---|
| `writeToNBT` | `void writeToNBT(NBTTagCompound)` | `CompoundNBT write(CompoundNBT)` | `CompoundTag save(CompoundTag)` | `void saveAdditional(CompoundTag)` + `CompoundTag saveWithoutMetadata()` | `compound.setByte` → `compound.putByte` |
| `readFromNBT` | `void readFromNBT(NBTTagCompound)` | `void read(CompoundNBT)` | `void load(BlockState,CompoundTag)` → `void load(CompoundTag)` | `void load(CompoundTag)` (引数 `BlockState` なしに統合) | `compound.getByte` → `compound.getByte` |
| `getDescriptionPacket` | `Packet getDescriptionPacket()` → `S35PacketUpdateTileEntity` | `SPacketUpdateTileEntity getUpdatePacket()` | `ClientboundBlockEntityDataPacket` | **同左 (`ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag)` のオーバーロード有)** | `new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,nbt)` → `ClientboundBlockEntityDataPacket.create(this)` |
| `onDataPacket` | `void onDataPacket(NetworkManager,S35PacketUpdateTileEntity)` | `void onDataPacket(NetworkManager,SPacketUpdateTileEntity)` | 同左 | **同左 + `Connection` 型に変更 (`onDataPacket(Connection, ClientboundBlockEntityDataPacket)`)** | `pkt.func_148857_g()` → `pkt.getTag()` |
| `getUpdateTag` | `NBTTagCompound getUpdateTag()` なし | `NBTTagCompound getUpdateTag()` | `CompoundTag getUpdateTag()` | **同左 (`getUpdateTag()` は `saveAdditional` で生成したTagを包む)** | `return writeToNBT(nbt)` → `return saveWithoutMetadata()` |
| `handleUpdateTag` | なし | `handleUpdateTag(CompoundNBT)` | `handleUpdateTag` → `load` | **削除、 `onDataPacket` で `load` 直接** | |
| `updateEntity` | `void updateEntity()` | `void tick()` (`ITickable`) | `void tick()` (`TickableBlockEntity`) | **`static <T extends BlockEntity> void tick(Level,BlockPos,BlockState,T)` (Blockの `getTicker` で供給するstatic tick)** | `ITickable` 削除、Block側で `createTickerHelper` |
| `getInventoryName` | `String getInventoryName()` | `ITextComponent getDisplayName()` | `Component getDisplayName()` | **同左 (`MenuProvider#getDisplayName(): Component`)** | |
| `markDirty` | `void markDirty()` | 維持だが `setChanged()` も呼ぶ (1.16) | `setChanged()` | **同左 (`setChanged()` + `level.sendBlockUpdated(pos, oldState, newState, 3)`)** | |
| `getCapability` | なし (IInventory直) | `LazyOptional<IItemHandler>` | `LazyOptional` 維持 | **同左 + `IEnergyStorage` も `CapabilityEnergy.ENERGY` で同型。`invalidateCaps()` で `LazyOptional.invalidate()`** | |
| `createTicker` | なし | なし | `BlockEntityTicker<T>` | **同左。 `Block#getTicker(Level,State,Type)` で `createTickerHelper(type, ModBlockEntities.TEA_MAKER_NEXT.get(), TileMakerNext::tick)`** | |

## GUI移行

| 1.7.10 | 1.12.2 | 1.16+ | 1.20.1 |
|---|---|---|---|
| `player.openGui(mod, ID, world,x,y,z)` (`IGuiHandler`) | 維持 | `NetworkHooks.openGui((ServerPlayer)player, new SimpleMenuProvider(...), pos)` | **同左。`MenuProvider`はBlockEntityが実装、FriendlyByteBuf.writeBlockPosで送出** |
| `Container` (`ContainerIceMaker`) | 維持 | `AbstractContainerMenu` + `MenuType` (`DeferredRegister<MenuType<?>>`) | **同左。`IForgeMenuType.create((id,inv,buf)-> new ContainerIceMaker(id,inv, buf.readBlockPos()))`** |
| `GuiContainer` | 維持 | `Screen` (`AbstractContainerScreen`) | **同左。`MenuScreens.register`は`FMLClientSetupEvent`で** |

`CommonProxy.getServerGuiElement/getClientGuiElement` の `switch(ID)` は `MenuType` 登録に分離。1.20.1では `NetworkHooks.openGui` は `BlockPos` を `buf.writeBlockPos` する定型。

```java
// 1.20.1 GUI登録定型
public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "defeatedcrow");
public static final RegistryObject<MenuType<ContainerIceMaker>> ICE_MAKER = MENUS.register("ice_maker",
  () -> IForgeMenuType.create((id, inv, buf) -> new ContainerIceMaker(id, inv, inv.player.level.getBlockEntity(buf.readBlockPos()))));
```

## Inventory移行

| 1.7.10 | 1.12.2+ 推奨 | 1.20.1 |
|---|---|---|
| `implements IInventory` (`getSizeInventory`, `getStackInSlot`, `setInventorySlotContents`, `isUseableByPlayer`) | `CapabilityItemHandler` (`IItemHandler` / `ItemStackHandler`) + `getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)` + `LazyOptional` | **同左 + 1.20.1は`ForgeCapabilities.ITEM_HANDLER`にリネーム（旧`CapabilityItemHandler`はエイリアス）** |
| `ItemStack null` | `ItemStack.EMPTY` (1.11+) | **同左** |
| `decrStackSize` | 維持だが `ItemStack.EMPTY` 対応 | **同左** |

`IInventory` 自体は 1.12まで維持可能だが 1.14+は `Container` 経由の `IItemHandler` が推奨。**1.20.1でも `LazyOptional<IItemHandler>` は維持（NeoForgeでは別だがForge47では維持）。**

## NBT/Sync移行詳細

```java
// 1.7.10
public Packet getDescriptionPacket(){
  NBTTagCompound nbt = new NBTTagCompound();
  writeToNBT(nbt);
  return new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,nbt);
}
public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
  readFromNBT(pkt.func_148857_g());
}

// 1.12.2
public SPacketUpdateTileEntity getUpdatePacket(){
  return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
}
public NBTTagCompound getUpdateTag(){ return writeToNBT(new NBTTagCompound()); }
public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){ handleUpdateTag(pkt.getNbtCompound()); }

// 1.16.5
public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
public CompoundTag getUpdateTag(){ return save(new CompoundTag()); }
public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){ load(pkt.getTag()); }

// 1.20.1
public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
public CompoundTag getUpdateTag(){ return saveWithoutMetadata(); }
public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){ load(pkt.getTag()); }
@Override
public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); tag.putInt("Remain", remain); }
@Override
public void load(CompoundTag tag){ super.load(tag); remain = tag.getInt("Remain"); }
// Block側のticker
public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type){
  return createTickerHelper(type, ModBlockEntities.TEA_MAKER_NEXT.get(), TileMakerNext::tick);
}
public static void tick(Level level, BlockPos pos, BlockState state, TileMakerNext be){ /* ... 旧updateEntityの中身 ... */ }
```

## 検証手順
1. `grep -r "xCoord"` → `getBlockPos()` に置換確認。
2. `grep -r "S35PacketUpdateTileEntity"` → 削除確認。
3. `gradlew build` で `ITickable` 未実装エラー確認 → `tick()` 化確認。
4. `grep -r "null"` for `ItemStack` → `ItemStack.EMPTY` 確認。
5. 1.20.1追加: `grep -r "BlockEntityType.Builder.create"` → `Builder.of` に置換確認。`grep -r "TileEntity"` → 0件（`BlockEntity` のみ）を確認。

## 関連
- [TileEntity 一覧](../tile-entities.md) / [個別ページ索引](./README.md)
- [Block 移行ガイド](../blocks/migration-guide.md)
- [ビルド移行ガイド](../build.md)
- `src/main/java/mods/defeatedcrow/common/CommonProxy.java:70`
