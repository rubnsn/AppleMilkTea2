# TileEntityName

> Category: `appliance | container | decorative | energy | food | base`
> Source: `src/main/java/mods/defeatedcrow/common/tile/TileEntityName.java:1` (or `tile/appliance/*`, `tile/energy/*`)
> Registry: `TileEntityName` (`CommonProxy.java:70` `GameRegistry.registerTileEntity(TileXxx.class, "TileXxx")`)
> Block: `DCsAppleMilk.xxx` (`src/main/java/mods/defeatedcrow/common/block/**:1`)
> GUI: `ContainerXxx` / `GuiXxx` (`CommonProxy.getServerGuiElement/getClientGuiElement` ID `x`) / なし

## 概要
[1-2文でTileEntityの役割・特徴を記述。インベントリ/液体/電荷/演出/レシピ処理の分類]

## 登録情報
- **クラス**: `TileXxx extends TileEntity implements IInventory / ISidedInventory / IFluidHandler / IChargeableMachine ...` (`src/main/java/mods/defeatedcrow/common/tile/TileXxx.java:1`)
- **登録名**: `TileXxx` (`GameRegistry.registerTileEntity(TileXxx.class, "TileXxx")` at `CommonProxy.java:70-117`)
- **対応Block**: `DCsAppleMilk.xxx` (`BlockXxx.java:1`) - `createNewTileEntity` で生成
- **基底**: `TileHasDirection` / `TileHasRemaining` / `TileHasRemain2` / `TileEntity` 直
- **NBTキー**: `Remaining`, `Direction`, `Input`, `HoldItem`, `Charge` 等 (`writeToNBT`/`readFromNBT`)

## 継承・インターフェース
- 継承: `TileEntity` / `TileHasDirection` / `TileHasRemaining` / `MachineBase` (`appliance`) / `TileChargerBase` (`energy`)
- 実装: `IInventory`, `ISidedInventory`, `ITickable`, `IChargeableMachine`, `IChargeGenerator`, `IFluidHandler` 等

## プロパティ / 状態
- **Inventory**: `ItemStack[]` スロット管理（`getSizeInventory` / `getStackInSlot` / `setInventorySlotContents`）
- **Ticks**: `updateEntity()` / `update()` で毎tick処理（冷却/調理/チャージ）
- **Sync**: `getDescriptionPacket()` → `S35PacketUpdateTileEntity(x,y,z,1,nbt)` / `onDataPacket(NetworkManager, S35PacketUpdateTileEntity)` でクライアント同期
- **AABB**: `getRenderBoundingBox()` オーバーライド時は描画範囲拡張

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `writeToNBT` | `void writeToNBT(NBTTagCompound)` | チャンク保存時 (`AnvilChunkLoader`) | NBT保存 | 維持だが `NBTTagCompound` → `CompoundNBT`. 1.12+は `writeToNBT` → `write(CompoundNBT)` / `save(CompoundTag)` + `super.write` 呼び必須 |
| `readFromNBT` | `void readFromNBT(NBTTagCompound)` | チャンク読込時 | NBT復元 | 維持。1.16+ `load(BlockState, CompoundNBT)` / `load(CompoundTag)` にシグネチャ変更. `xCoord/yCoord/zCoord` → `worldPosition` / `getBlockPos()` |
| `getDescriptionPacket` | `Packet getDescriptionPacket()` → `S35PacketUpdateTileEntity(x,y,z,1,nbt)` | クライアント同期 (`World.markBlockForUpdate`) | 初期同期 | **変更**: `getUpdatePacket()` → `SUpdateTileEntityPacket` (1.9) → `ClientboundBlockEntityDataPacket` (1.16). `xCoord` → `worldPosition` / `pos` |
| `onDataPacket` | `void onDataPacket(NetworkManager, S35PacketUpdateTileEntity)` | パケット受信時 | クライアント側反映 | `onDataPacket(Connection, SUpdateTileEntityPacket)` → `onDataPacket(Connection, ClientboundBlockEntityDataPacket)` |
| `updateEntity` | `void updateEntity()` | tick毎 (`World.updateEntities`) | サーバーtick | `tick()` / `update()` (1.9+ `ITickable` インターフェース). `worldObj` → `level` / `world` |
| `getInventoryName` | `String getInventoryName()` | GUI表示 | 名前 | `getDisplayName(): ITextComponent` / `Component` (1.12+ `hasCustomName` / `getName`) |
| `isUseableByPlayer` | `boolean isUseableByPlayer(EntityPlayer)` | GUI開制御 | 距離判定 | 維持。`EntityPlayer` → `Player` |
| `getSizeInventory` | `int getSizeInventory()` | `IInventory` | スロット数 | 維持だが 1.14+は `Container` / `CapabilityItemHandler` (`IItemHandler`) へ分割 |
| `getStackInSlot` | `ItemStack getStackInSlot(int)` | インベントリ |取得 | 維持。`ItemStack` は空を `null` → `ItemStack.EMPTY` (1.11+) |
| `markDirty` | `void markDirty()` | 変更時 | ダーティマーク | 維持 |

## Container / GUI 連携
- **Container**: `ContainerXxx extends Container` (`src/main/java/mods/defeatedcrow/common/tile/appliance/ContainerXxx.java:1`) - スロット配置、`transferStackInSlot` 等
- **Gui**: `GuiXxx extends GuiContainer` (`src/main/java/mods/defeatedcrow/client/gui/GuiXxx.java:1`) - 背景テクスチャ `textures/gui/*.png`
- **ID**: `CommonProxy.getServerGuiElement/getClientGuiElement` の `switch(ID)` で分岐（IceMaker=2, Processor=3, Evaporator=4, AdvProcessor=5, BatBox=6）
- **Open**: `player.openGui(DCsAppleMilk.instance, ID, world, x,y,z)` (`Block.onBlockActivated`) → 1.14+ `NetworkHooks.openGui(player, MenuProvider, pos)`

## レンダー / モデル
- TileEntityRenderer: `src/main/java/mods/defeatedcrow/client/model/tileentity/TileEntityXxxRenderer.java:1` (ISBRH / TESR)
- 登録: `ClientProxy.registerRenderers()` で `ClientRegistry.bindTileEntitySpecialRenderer(TileXxx.class, new RendererXxx())` (1.7) → 1.12+ `ClientRegistry.bindTileEntitySpecialRenderer` 維持、1.16+ `BlockEntityRenderer` + `EntityRendererRegistry`

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `GameRegistry.registerTileEntity(TileXxx.class, "TileXxx")` → `RegistryEvent.Register<TileEntityType<?>>` / `DeferredRegister<TileEntityType<?>>` + `TileEntityType.Builder.create(TileXxx::new, validBlocks).build(null).setRegistryName("defeatedcrow","tile_xxx")`
- [ ] `TileEntity` → `BlockEntity` (1.16+ リネーム、1.12までは `TileEntity` 維持)
- [ ] `xCoord/yCoord/zCoord` → `pos.getX()/getY()/getZ()` / `worldPosition` / `getBlockPos()`
- [ ] `worldObj` → `level` / `world` (`getLevel()`)
- [ ] `getDescriptionPacket() → S35PacketUpdateTileEntity` → `getUpdatePacket() → SUpdateTileEntityPacket` / `ClientboundBlockEntityDataPacket` + `getUpdateTag()` / `handleUpdateTag()`
- [ ] `onDataPacket` シグネチャ変更対応
- [ ] `updateEntity()` → `tick()` implements `ITickable` / `TickableBlockEntity`
- [ ] `IInventory` 直実装 → `CapabilityItemHandler` (`getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)`) / `IItemHandler` + `LazyOptional`
- [ ] `ItemStack null` → `ItemStack.EMPTY` (1.11+). `if(stack != null)` → `if(!stack.isEmpty())`
- [ ] `NBTTagCompound` → `CompoundNBT` / `CompoundTag`, `func_148857_g()` → `getTag()`
- [ ] Container: `IInventory` → `Container` / `AbstractContainerMenu` (1.14+) + `MenuType` 登録
- [ ] `setCreativeTab` は維持だが `CreativeTabs` → `ItemGroup`

## 関連ドキュメント
- [TileEntity 一覧](../tile-entities.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md) - 対応Block
