# EntityName

> Category: `projectile | dummy | placeable | villager | village`
> Source: `src/main/java/mods/defeatedcrow/common/entity/EntityName.java:1`
> Registry: `defeatedcrow.xxx` (`DCsAppleMilk.java:542` `EntityRegistry.registerModEntity`)
> Config: `DCsConfig.entityIdXxx` (default `xxx`, 0=auto) (`DCsConfig.java:21`)
> Render: `src/main/java/mods/defeatedcrow/client/entity/RenderXxx.java:1` (`ClientProxy.registerRenderers`)

## 概要
[1-2文でEntityの役割・特徴を記述。投擲/魔法/設置型食物/Villagerの分類]

## 登録情報
- **クラス**: `EntityXxx extends Entity / EntityLiving / FoodBaseEntity / ...` (`src/main/java/mods/defeatedcrow/common/entity/...:1`)
- **登録名**: `compressedMelon` 等（`EntityRegistry.registerModEntity(EntityXxx.class, "compressedMelon", id, mod, 250, 5, true)`）(`DCsAppleMilk.java:543`)
- **ID解決**: `DCsConfig.entityIdXxx !=0 ? config値 : EntityRegistry.findGlobalUniqueEntityId()` (`DCsAppleMilk.java:542-702`)
- **Tracking**: `range=250, frequency=5, velocityUpdates=true`（全Entity共通）
- **Villager**（該当時）: `VillagerRegistry.registerVillagerId(id)` + `registerVillageTradeHandler` (`DCsAppleMilk.java:712`)
- **Village**（該当時）: `MapGenStructureIO.func_143031_a(ComponentVillageXxx.class, "ViXxx")` (`DCsAppleMilk.java:719`)

## 継承・インターフェース
- 継承: `Entity` / `EntityLivingBase` / `PlaceableFoods` (`FoodBaseEntity`) / `Villager` 等
- 実装: `IProjectile`, `IRangedAttackMob`, `IMob` 等（該当なしなら `-`）

## プロパティ / 状態
- **Size**: `setSize(0.6F, 0.6F)` 等（コンストラクタ）
- **DataWatcher**: `dataWatcher.addObject(17, Integer)` 等 (`entityInit()`)
- **NBT**: `writeToNBT` / `readFromNBT` で `ItemStack`, `remain`, `direction` 等を保存
- **Physics**: `onUpdate()` / `moveEntity` / `onCollideWithPlayer` 等

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `entityInit` | `void entityInit()` | `Entity` 生成時 (`Entity` コンストラクタ) | DataWatcher登録 | 維持だが `DataWatcher` → `EntityDataManager` (`DataParameter`). `DataWatcher.addObject(17, ...)` → `entityData.register(DATA_ID, default)` |
| `onUpdate` | `void onUpdate()` | tick毎 (`World.updateEntity`) | 移動・衝突・tick処理 | `tick()` / `baseTick()` にリネーム(1.13+). `worldObj` → `world` / `level`, `posX/Y/Z` → `getX()/getY()/getZ()` |
| `writeToNBT` | `void writeEntityToNBT(NBTTagCompound)` | チャンク保存時 | NBT保存 | `writeAdditional(CompoundNBT)` / `saveAdditional(CompoundTag)` (1.16+). `NBTTagCompound` → `CompoundNBT` |
| `readFromNBT` | `void readEntityFromNBT(NBTTagCompound)` | チャンク読込時 | NBT復元 | `readAdditional(CompoundNBT)` / `load(CompoundTag)` |
| `onCollideWithPlayer` | `void onCollideWithPlayer(EntityPlayer)` | 衝突時 | アイテム付与等 | `playerTouch(Player)` に変更(1.16) |
| `attackEntityFrom` | `boolean attackEntityFrom(DamageSource, float)` | 被ダメージ時 | ダメージ処理 | 維持だが `DamageSource` は `Registry` 化 |
| `setDead` | `void setDead()` | 消滅時 | エンティティ削除 | 維持 |
| `getCollisionBox` | `AxisAlignedBB getCollisionBox(Entity)` | 衝突判定 | コリジョン | `getCollisionBox()` → `getBoundingBox()` + `VoxelShape` |
| `getPickedResult` | `ItemStack getPickedResult(MovingObjectPosition)` | 中クリック | ピック結果 | `getPickedResult(RayTraceResult)` → `getCloneItemStack()` |

## ネットワーク / スポーン
- **Spawn Packet**: `S0EPacketSpawnObject` / `S14PacketEntity` 等 via `EntityRegistry` 自動
- **Description**: 1.7.10は `Entity` が `S35PacketUpdateTileEntity` 的な個別パケットを持たない。1.12+は `SPacketSpawnObject` / `createSpawnPacket()` (Forge `IEntityAdditionalSpawnData` → `getAddEntityPacket()`)
- **DataSync**: `dataWatcher` (1.7) → `entityData` (1.9+) の同期

## レンダー / モデル
- Render ID: `RenderXxx` (`src/main/java/mods/defeatedcrow/client/entity/RenderXxx.java:1`)
- 登録: `ClientProxy.registerRenderers()` で `RenderingRegistry.registerEntityRenderingHandler(EntityXxx.class, new RenderXxx())` (1.7) → `RenderingRegistry.registerEntityRenderingHandler` → `EntityRendererRegistry` / `EntityRenderers.register` (1.16+)

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `EntityRegistry.registerModEntity` → `RegistryEvent.Register<EntityType<?>>` / `DeferredRegister<EntityType<?>>` + `EntityType.Builder.<EntityXxx>create(...)` + `.sized(w,h).tracker(250,5,true).build("xxx")`
- [ ] `DCsConfig.entityIdXxx` → 削除。1.12+は `ResourceLocation` ベースでID整数管理不要（`findGlobalUniqueEntityId` 廃止）
- [ ] `worldObj` → `world` / `level`
- [ ] `dataWatcher.addObject` → `entityData.define(DATA_XXX, default)`
- [ ] `NBTTagCompound` → `CompoundNBT` / `CompoundTag`, `writeEntityToNBT/readEntityFromNBT` → `addAdditionalSaveData/readAdditionalSaveData`
- [ ] `AxisAlignedBB` → `VoxelShape` / `AABB` (1.16 `net.minecraft.world.phys.AABB`)
- [ ] `onUpdate()` → `tick()` / `baseTick()`
- [ ] `S35PacketUpdateTileEntity` 的な同期 → `getAddEntityPacket()` / `IEntityAdditionalSpawnData` (`readSpawnData`/`writeSpawnData`) → `SpawnData` は `FriendlyByteBuf`
- [ ] `SideOnly(Side.CLIENT)` → `@OnlyIn(Dist.CLIENT)` (1.14+)
- [ ] `MathHelper` パッケージ変更 `net.minecraft.util.MathHelper` → `net.minecraft.util.math.MathHelper` (1.12) → `net.minecraft.util.Mth` (1.16)
- [ ] Villager: `VillagerRegistry.registerVillagerId/registerVillageTradeHandler/registerVillageCreationHandler` → `VillagerProfession` / `PointOfInterest` / `Structure` (`Jigsaw` / `StructureFeature`) へ全面刷新。`MapGenStructureIO` → `StructureRegistry`

## 関連ドキュメント
- [Entity 一覧](../entities.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md) - 食物ブロックとの対応（Placeable）
- [TileEntity 一覧](../tile-entities.md)
