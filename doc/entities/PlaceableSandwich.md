# PlaceableSandwich

> Category: `placeable`
> Source: `src/main/java/mods/defeatedcrow/common/entity/edible/PlaceableSandwich.java:1`
> Registry: `defeatedcrow.PlaceableSandwich` (`DCsAppleMilk.java:542` `EntityRegistry.registerModEntity`)
> Config: `DCsConfig.entityIdSandwich` (default `158`, 0=auto) (`DCsConfig.java:21`)
> Render: `RenderSandwichEntity` (`src/main/java/mods/defeatedcrow/client/entity/RenderSandwichEntity.java:1` if exists)
> Tracking: `range=250, frequency=5, velocityUpdates=true` (全Entity共通)

## 概要
設置サンドイッチ。ItemAppleSandwichのEntity形態。 `DCsConfig.entityIdSandwich` でID可変、0時は `findGlobalUniqueEntityId()` で自動採番。

## 登録情報
- **クラス**: `PlaceableSandwich extends PlaceableFoods` (`src/main/java/mods/defeatedcrow/common/entity/edible/PlaceableSandwich.java:1`)
- **登録名**: `PlaceableSandwich` (`EntityRegistry.registerModEntity(PlaceableSandwich.class, "PlaceableSandwich", DCsConfig.entityIdSandwich, mod, 250, 5, true)` at `DCsAppleMilk.java:542-702`)
- **ID解決**: `DCsConfig.entityIdSandwich !=0 ? config値 : EntityRegistry.findGlobalUniqueEntityId()` のパターン全Entity共通
- **関連**: Placeable系は Block ↔ Entity 対応表参照

## 継承・インターフェース
- 継承: `Entity` / `PlaceableFoods` (Placeable系は `FoodBaseEntity` 継承) / `EntityLiving` (Illusion)
- 実装: `IProjectile` (YuzuBullet) / `IVillageTradeHandler` (Villager)

## プロパティ / 状態
- **Size**: `setSize(0.6F, 0.6F)` 付近（投擲系）/ `0.5F, 0.5F` (Bullet) / `1.0F, 0.7F` (Placeable)
- **DataWatcher**: `dataWatcher.addObject(17, Integer)` 等でメタ保持 (`entityInit()`)
- **NBT**: `writeEntityToNBT` / `readEntityFromNBT` で `ItemStack`, `direction`, `remain` 等を保存（Placeable）
- **Physics**: `onUpdate()` で移動・重力・衝突・`setDead` 判定

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。

| メソッド | シグネチャ (1.7.10) | 参照元 | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `entityInit` | `void entityInit()` | `Entity` 生成時 | DataWatcher登録 | `DataWatcher` → `EntityDataManager` (`DataParameter`) |
| `onUpdate` | `void onUpdate()` | tick毎 | 移動・衝突 | `tick()` / `baseTick()` にリネーム, `worldObj` → `level` |
| `writeEntityToNBT` | `void writeEntityToNBT(NBTTagCompound)` | チャンク保存 | NBT保存 | `addAdditionalSaveData(CompoundNBT)` |
| `readEntityFromNBT` | `void readEntityFromNBT(NBTTagCompound)` | チャンク読込 | NBT復元 | `readAdditionalSaveData(CompoundNBT)` |
| `onCollideWithPlayer` | `void onCollideWithPlayer(EntityPlayer)` | 衝突時 | アイテム付与 | `playerTouch(Player)` |
| `attackEntityFrom` | `boolean attackEntityFrom(DamageSource, float)` | 被ダメージ | ダメージ処理 | 維持だが `DamageSource` Registry化 |

## ネットワーク / スポーン
- **Spawn**: `EntityRegistry` が `S0EPacketSpawnObject` を自動送信
- **Sync**: `dataWatcher` 同期 (1.7) → `entityData` (1.9+) 
- **Placeable**: `ItemBlock` の `onItemUse` で `world.spawnEntityInWorld(new PlaceableSandwich(world, stack, x,y,z))` 生成

## レンダー
- Renderer: `RenderSandwichEntity` (`ClientProxy.registerRenderers()` で `RenderingRegistry.registerEntityRenderingHandler(PlaceableSandwich.class, new RenderSandwichEntity())`)
- 1.12+: `RenderingRegistry.registerEntityRenderingHandler` 維持、1.16+ `EntityRendererRegistry` / `EntityRenderers.register`

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `EntityRegistry.registerModEntity` → `DeferredRegister<EntityType<?>>` + `EntityType.Builder.<PlaceableSandwich>of(...).sized(w,h).clientTrackingRange(250).updateInterval(5).build("PlaceableSandwich")`
- [ ] `DCsConfig.entityIdSandwich` 整数ID → 削除（ResourceLocationベース）
- [ ] `worldObj` → `level` / `world`, `posX/Y/Z` → `getX()/getY()/getZ()`
- [ ] `dataWatcher.addObject` → `entityData.define(DATA_XXX, default)`
- [ ] `NBTTagCompound` → `CompoundNBT`, `writeEntityToNBT` → `addAdditionalSaveData`
- [ ] `onUpdate()` → `tick()`
- [ ] `AxisAlignedBB` → `AABB` (`net.minecraft.world.phys.AABB`)
- [ ] `SideOnly(Side.CLIENT)` → `@OnlyIn(Dist.CLIENT)`
- [ ] Villager系は全面刷新: `VillagerProfession` / `PoiType` / `StructureFeature` へ

## 関連ドキュメント
- [Entity 一覧](../entities.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md) - Placeable対応Block
- [Config](../config.md) - `DCsConfig.entityIdSandwich`

> 自動生成: `src/main/java/mods/defeatedcrow/common/entity/edible/PlaceableSandwich.java:1` / `DCsAppleMilk.java:542`
> 最終更新: 2026-08-24
