# Entity 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2 (DCsAppleMilk 2.9m)` / Minecraft `1.7.10` (Forge 10.13.4.1614) → `1.12.2` (14.23.5.2860) → `1.16.5` / `1.18.2` → **1.20.1 (Forge 47.x, FG6, Mojmap, JDK17)**
> 登録元: `DCsAppleMilk.java:542-723` / `DCsConfig.java:20` / `ClientProxy.registerRenderers`
> 総数: **20 ModEntity + 2 Villager + 2 VillageComponent**
> 関連: [Entity 一覧](../entities.md) / [個別ページ索引](./README.md) / [ビルド移行ガイド](../build.md)

## 概要
AppleMilkTea2 の全Entityを 1.12.2 以降へ移行する際のメソッド参照・登録変更を整理。**1.20.1では `EntityRegistry.registerModEntity` は完全削除、`DeferredRegister<EntityType<?>>` + `EntityType.Builder` + `MobCategory` + `AttributeSupplier` + `EntityRendererProvider.Context` + `SynchedEntityData`（旧DataWatcher）の完全定型に統一。Placeable 13種は `Entity`（設置済み可食物の見た目用）と `Block` の二重管理を `BlockEntity` か `Block` 単体に整理する必要がある。**

> **ギャップ補足**（plan.md監査）: 旧DOCは 1.16止まりで `EntityRendererProvider.Context` / `AttributeSupplier.Builder` / `MobCategory` / `RemovalReason` 等の 1.20 APIが未記載。Rendererは `EntityRenderersEvent.RegisterRenderers` + `Context` 必須。

## 移行の大局（3段階）

### 1. 登録処理の分離
| 1.7.10 | 1.12.2+ | 参照先 |
|---|---|---|
| `EntityRegistry.registerModEntity(EntityMelonBomb.class, "compressedMelon", DCsConfig.entityIdMelon, mod, 250,5,true)` (`DCsAppleMilk.java:542`) | `DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, "defeatedcrow")` + `ENTITIES.register("compressed_melon", ()-> EntityType.Builder.<EntityMelonBomb>of(EntityMelonBomb::new, EntityClassification.MISC).sized(0.6F,0.6F).clientTrackingRange(250).updateInterval(5).build("compressed_melon"))` | `DCsAppleMilk.java:542` |
| `DCsConfig.entityIdMelon` (整数ID) | 削除。`ResourceLocation` (`defeatedcrow:compressed_melon`) で管理 | `DCsConfig.java:21` |
| `EntityRegistry.findGlobalUniqueEntityId()` | 削除。自動採番不要 | `DCsAppleMilk.java:542` |
| `VillagerRegistry.registerVillagerId(id)` / `registerVillageTradeHandler` | `DeferredRegister<VillagerProfession>` / `PoiType` (`VillagerProfession` + `PointOfInterest`) / `VillagerTrades` Event | `DCsAppleMilk.java:712` |
| `VillageCreateHandle` + `MapGenStructureIO.func_143031_a` | `StructureFeature` / `Jigsaw` / `StructureRegistry` (1.16+) → 1.20.1は `Structure` + `StructureSet` + `TemplatePool` | `DCsAppleMilk.java:719` |

### 1.20.1 登録定型

```java
// ModEntities.java (1.20.1)
public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "defeatedcrow");

public static final RegistryObject<EntityType<EntityMelonBomb>> MELON_BOMB = ENTITIES.register("melon_bomb",
  () -> EntityType.Builder.<EntityMelonBomb>of(EntityMelonBomb::new, MobCategory.MISC)
    .sized(0.6F, 0.6F).clientTrackingRange(8).updateInterval(10).build("melon_bomb"));

public static final RegistryObject<EntityType<EntitySilkyMelon>> SILKY_MELON = ENTITIES.register("silky_melon",
  () -> EntityType.Builder.<EntitySilkyMelon>of(EntitySilkyMelon::new, MobCategory.MISC)
    .sized(0.9F, 0.9F).clientTrackingRange(8).updateInterval(10).build("silky_melon"));

public static final RegistryObject<EntityType<PlaceableTart>> PLACEABLE_TART = ENTITIES.register("placeable_tart",
  () -> EntityType.Builder.<PlaceableTart>of(PlaceableTart::new, MobCategory.MISC)
    .sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_tart"));

// Attribute（Living系のみ）
@SubscribeEvent
public static void onAttributeCreate(EntityAttributeCreationEvent e){
  e.put(ModEntities.ENTITY_KINOKO.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).build());
  // 1.20.1では AttributeSupplier.Builder → AttributeSupplier。旧 `SharedMonsterAttributes` は `Attributes` に
}
```

- `MobCategory` は 1.19で `EntityClassification` からリネーム（`MISC`, `CREATURE`, `MONSTER` 等）。
- `sized(width,height)` + `clientTrackingRange` + `updateInterval` は維持だが、1.20.1では `build(String id)` の引数は `ResourceLocation` のpathのみ（`defeatedcrow:melon_bomb`）。

### 2. Entity本体の変更
| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 |
|---|---|---|---|
| `extends Entity` / `EntityLiving` | 維持だが `EntityType<?>` をコンストラクタに要求 (`Entity(EntityType<?>, World)`) | 同左 + `Level` | **同左 + `Entity(EntityType<?>, Level)`。`Level` は `net.minecraft.world.level.Level`** |
| `worldObj` | `world` | `level` | **同左 (`level`, `Level`)** |
| `dataWatcher.addObject(17, ...)` | `entityData.define(DATA_XXX, default)` (`EntityDataManager`) | `entityData.define` 維持 (`SynchedEntityData`) | **同左 + `EntityDataAccessor<T>` は `SynchedEntityData.defineId(Class, EntityDataSerializer)` で定義。`defineSynchedData()` で `entityData.define(DATA_ID, 0)`** |
| `writeEntityToNBT(NBTTagCompound)` / `readEntityFromNBT(NBTTagCompound)` | `writeAdditional(CompoundNBT)` / `readAdditional(CompoundNBT)` | `addAdditionalSaveData(CompoundTag)` / `readAdditionalSaveData(CompoundTag)` | **同左 (`addAdditionalSaveData` / `readAdditionalSaveData`) + `CompoundTag` の `putInt/getInt` etc** |
| `onUpdate()` | `tick()` (1.9+) | `tick()` / `baseTick()` | **同左 (`tick()` + `baseTick()` + `aiStep()` for Living)** |
| `AxisAlignedBB` (`net.minecraft.util.AxisAlignedBB`) | `AxisAlignedBB` (`net.minecraft.util.math.AxisAlignedBB`) | `AABB` (`net.minecraft.world.phys.AABB`) / `VoxelShape` | **同左 (`AABB` + `Vec3`)** |
| `MathHelper` (`net.minecraft.util.MathHelper`) | `MathHelper` (`net.minecraft.util.math.MathHelper`) | `Mth` (`net.minecraft.util.Mth`) | **同左 (`Mth`)** |
| `DamageSource` 直指定 | `DamageSource` 維持だが `Registry` 化 | `DamageSource` + `ResourceKey` | **同左 + `level.damageSources().mobAttack(this)` / `generic()` etc。`DamageSource` は `Holder` 管理** |
| `setDead()` | `remove()` (1.11+) | `remove(RemovalReason)` (1.16+) | **`discard()` (1.19+) / `remove(RemovalReason.DISCARDED)`** |

### 3. レンダーの分離
| 1.7.10 | 1.12.2+ | 1.20.1 | 参照先 |
|---|---|---|---|
| `RenderingRegistry.registerEntityRenderingHandler(EntityXxx.class, new RenderXxx())` (`ClientProxy.registerRenderers`) | 同左 (`RenderingRegistry` 維持) | `EntityRendererRegistry.register` / `EntityRenderersEvent.RegisterRenderers` (`EntityRendererProvider`) | **`EntityRenderersEvent.RegisterRenderers` で `event.registerEntityRenderer(ModEntities.MELON_BOMB.get(), RenderMelonBomb::new)`。`RenderMelonBomb` は `EntityRenderer<EntityMelonBomb>` + `EntityRendererProvider.Context` をコンストラクタに持つ** |
| `Render` の `doRender(Entity, double x,double y,double z, float yaw, float partial)` | `doRender(Entity, double,double,double, float,float)` | `render(T entity, float yaw, float partial, PoseStack, MultiBufferSource, int packedLight)` | **同左 + `PoseStack` + `MultiBufferSource` + `packedLight` + `OverlayTexture`** |

---

## メソッド別移行対応表

| メソッド | 1.7.10 シグネチャ | 移行後 (1.12.2) | 移行後 (1.16.5) | 1.20.1 | 対応例 |
|---|---|---|---|---|---|
| `entityInit` | `void entityInit()` | `protected void defineSynchedData()` (1.9+) | `defineSynchedData()` | **同左 (`protected void defineSynchedData() { entityData.define(DATA_ID, 0); }`)** | `dataWatcher.addObject(17, 0)` → `entityData.define(DATA_ID, 0)` |
| `onUpdate` | `void onUpdate()` | `void tick()` | `void tick()` | **同左 + Livingなら `aiStep()`** | `worldObj.isRemote` → `level.isClientSide` |
| `writeEntityToNBT` | `void writeEntityToNBT(NBTTagCompound)` | `void writeAdditional(CompoundNBT)` | `void addAdditionalSaveData(CompoundTag)` | **同左** | `compound.setInteger("Meta", meta)` → `compound.putInt("Meta", meta)` |
| `readEntityFromNBT` | `void readEntityFromNBT(NBTTagCompound)` | `void readAdditional(CompoundNBT)` | `void readAdditionalSaveData(CompoundTag)` | **同左** | `compound.getInteger` → `compound.getInt` |
| `getCollisionBox` | `AxisAlignedBB getCollisionBox(Entity)` | `AxisAlignedBB getCollisionBox()` | `VoxelShape getCollisionShape()` | **同左 + `AABB getBoundingBox()`** | |
| `onCollideWithPlayer` | `void onCollideWithPlayer(EntityPlayer)` | `void playerTouch(Player)` | `void playerTouch(Player)` | **同左** | |
| `attackEntityFrom` | `boolean attackEntityFrom(DamageSource, float)` | 維持 | 維持 | **同左 (`hurt(DamageSource, float)`)** | 1.12で `attackEntityFrom` → `attackEntityFrom` 維持だが 1.16で `hurt` にリネーム |
| `setDead` | `void setDead()` | `void remove()` (1.11+) | `void remove(RemovalReason)` (1.16+) | **`discard()` / `remove(RemovalReason.DISCARDED)`** | `setDead()` → `discard()` (1.19+) |

### Placeable 13種の1.20.1注意

- `PlaceableFoods` 基底は `onUpdate` で `ItemStack` メタ管理。1.20.1では `BlockState` Property ではなく `EntityDataAccessor<ItemStack>` で同期するか、`BlockEntity` に寄せる方が自然。
- `allowEdibleEntities` configで `Block`/`Entity` 切替。1.20.1も `Block` vs `Entity` の切替は維持だが、`Block` 側は `BlockBehaviour.Properties` に。
- `setCupScale` / `getCupSize` は `Util.getCupScale()` で config連動。`Util` の `cupScale` は `DCsConfig.setCupScale` 由来。1.20.1でも `ForgeConfigSpec.DoubleValue` で同じ。

### Villager 2種 1.20.1

- 1.7 `VillagerRegistry` → 1.20.1 `DeferredRegister<VillagerProfession>` + `DeferredRegister<PoiType>` + `VillagerTrades` (`VillagerTradesEvent`) + `Structure` (`JigsawPool`) へ全面刷新。詳細は [worldgen migration](../worldgen/migration-guide.md) も参照。
- `VillagerCafe` / `VillagerYome` の `EntityVillager` 継承は 1.20.1で `Villager` + `VillagerData` に。`VillagerProfession` は `Holder<PoiType>` + `Holder<SoundEvent>` を持つ。

### Renderer 1.20.1 定型

```java
// ClientSetup
@SubscribeEvent
public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers e){
  e.registerEntityRenderer(ModEntities.MELON_BOMB.get(), RenderMelonBomb::new);
  e.registerEntityRenderer(ModEntities.PLACEABLE_TART.get(), ctx -> new PlaceableRenderer(ctx, ModModelLayers.TART));
}
// Render
public class RenderMelonBomb extends EntityRenderer<EntityMelonBomb> {
  public RenderMelonBomb(EntityRendererProvider.Context ctx){ super(ctx); }
  public void render(EntityMelonBomb e, float yaw, float partial, PoseStack pose, MultiBufferSource buf, int light){
    pose.pushPose();
    // ... model render
    pose.popPose();
    super.render(e, yaw, partial, pose, buf, light);
  }
  public ResourceLocation getTextureLocation(EntityMelonBomb e){ return new ResourceLocation("defeatedcrow","textures/entity/melon_bomb.png"); }
}
```

---

## 検証手順
1. `grep -r "EntityRegistry.registerModEntity"` で残存検出 → 0件を確認。
2. `grep -r "dataWatcher"` → `entityData` / `SynchedEntityData` に置換確認。
3. `grep -r "EntityClassification"` → `MobCategory` に置換確認（1.19+）。
4. `gradlew build` で 1.20.1 環境で `EntityType.Builder` の `build` 引数エラーが無いか確認。
5. Placeable の `world.getTileEntity(x,y,z)` → `world.getBlockEntity(pos)` 置換確認（Block側）。

---

## 関連ドキュメント
- [Entity 一覧](../entities.md)
- [個別ページ索引](./README.md)
- [TileEntity 一覧](../tile-entities.md)
- [Block 一覧](../blocks.md)
- [Config](../config.md)
- [WorldGen 移行ガイド](../worldgen/migration-guide.md) - Villager/Structure
- `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:542-723`
- [ビルド移行ガイド](../build.md) - MojmapでEntityのMCP名が `m_...` でなく `tick`/`hurt` 等に
