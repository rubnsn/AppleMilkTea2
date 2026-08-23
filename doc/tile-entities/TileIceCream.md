# TileIceCream

> Category: `food / food`
> Source: `src/main/java/mods/defeatedcrow/common/tile/TileIceCream.java:1`
> Registry: `TileIcecream` (`CommonProxy.java:70` `GameRegistry.registerTileEntity(TileIceCream.class, "TileIcecream")`)
> Block: `-` (`DCsAppleMilk.-`)
> GUI: なし (ID `-`)

## 概要
アイスクリーム。 `CommonProxy.registerTileEntity()` で `GameRegistry.registerTileEntity` 登録。

## 登録情報
- **クラス**: `TileIceCream extends TileHasRemaining` (`src/main/java/mods/defeatedcrow/common/tile/TileIceCream.java:1`)
- **登録名**: `TileIcecream` (`GameRegistry.registerTileEntity(TileIceCream.class, "TileIcecream")` at `CommonProxy.java:70-117`)
- **対応Block**: `-` の `createNewTileEntity` で生成
- **NBTキー**: `Remaining`, `Direction`, `Input` 等 (`writeToNBT`/`readFromNBT`)

## 継承・インターフェース
- 継承: `TileHasRemaining`
- 実装: `IInventory` / `ISidedInventory` / `IChargeableMachine` 等（該当Tileで差異）

## プロパティ / 状態
- **Sync**: `getDescriptionPacket() -> S35PacketUpdateTileEntity(x,y,z,1,nbt)` / `onDataPacket` でクライアント同期
- **Inventory**: 該当Tileは `getSizeInventory` / `getStackInSlot` 実装
- **Tick**: `updateEntity()` で毎tick処理（MachineBase系は冷却/レシピ進行）

## オーバーライドメソッド一覧
| メソッド | シグネチャ (1.7.10) | 説明 | 移行 (1.12.2+) |
|---|---|---|---|
| `writeToNBT` | `void writeToNBT(NBTTagCompound)` | NBT保存 | `CompoundNBT` へ |
| `readFromNBT` | `void readFromNBT(NBTTagCompound)` | NBT復元 | `load(CompoundTag)` へ, `xCoord`->`worldPosition` |
| `getDescriptionPacket` | `Packet getDescriptionPacket()` | 同期パケット | `getUpdatePacket() -> SUpdateTileEntityPacket` |
| `onDataPacket` | `void onDataPacket(NetworkManager, S35PacketUpdateTileEntity)` | 受信 | `onDataPacket(Connection, SUpdateTileEntityPacket)` |
| `updateEntity` | `void updateEntity()` | tick | `tick()` + `ITickable` |

## Container / GUI 連携
- **Container**: なし
- **Gui**: なし
- **ID**: `-` (`CommonProxy.getServerGuiElement` / `getClientGuiElement`)
- **Open**: `player.openGui(mod, ID, world, x,y,z)` -> 1.14+ `NetworkHooks.openGui`

## レンダー
- Renderer: `TileEntityIceCreamRenderer` (ISBRH/TESR) (`ClientProxy.registerRenderers()`)
- 1.16+: `BlockEntityRenderer` に移行

## 1.7.10 -> 1.12.2 移行チェックリスト
- [ ] `GameRegistry.registerTileEntity` -> `TileEntityType.Builder` / `DeferredRegister<TileEntityType<?>>`
- [ ] `xCoord/yCoord/zCoord` -> `worldPosition` / `getBlockPos()`
- [ ] `worldObj` -> `level`
- [ ] `getDescriptionPacket` -> `getUpdatePacket` + `getUpdateTag`
- [ ] `updateEntity()` -> `tick()` (`ITickable`)
- [ ] `ItemStack null` -> `ItemStack.EMPTY`
- [ ] `IInventory` -> `CapabilityItemHandler` 検討

## 関連ドキュメント
- [TileEntity 一覧](../tile-entities.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/tile/TileIceCream.java:1` / `CommonProxy.java:70`
> 最終更新: 2026-08-24