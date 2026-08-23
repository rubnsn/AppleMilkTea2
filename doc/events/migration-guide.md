# Event 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `event/*` 12 + `api/events/*` 6 + `PlantsClickEvent`
> 前提: Forge 1.7.10 `cpw.mods.fml.common.eventhandler` → Forge 1.12 `net.minecraftforge.fml.common.eventhandler` → Forge 1.16 `net.minecraftforge.eventbus.api` → **1.20.1 Forge 47 (eventbus 6.x, `net.minecraftforge.eventbus.api.Event` + `IEventBus` + `MinecraftForge.EVENT_BUS` + `Mod.EventBusSubscriber`)**
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)

## 概要
EventBus のパッケージ変更と `World,int x,y,z` → `BlockPos` 化が中心。1.16で `FMLCommonHandler` → `MinecraftForge.EVENT_BUS` に統合、**1.20.1でも `MinecraftForge.EVENT_BUS` は維持（NeoForgeでは `NeoForge.EVENT_BUS` にリネームだがForge 47では旧名）。`LivingSpawnEvent.CheckSpawn` → `MobSpawnEvent` (1.19) 等の一部リネーム、`ItemTooltipEvent` の `List<String>` → `List<Component>` + `TooltipFlag` 固定、`FillBucketEvent` の `MovingObjectPosition` → `BlockHitResult` が確定。**

> **ギャップ補足**（plan.md監査）: 旧DOCは 1.16止まり。1.20.1では `FMLCommonHandler` 完全削除（一部DOCで `FMLCommonHandler.instance().bus()` が残存）、`cpw.mods.fml` → `net.minecraftforge.eventbus.api` の完全置換、`World` → `Level` + `BlockPos` + `BlockState` の定型が漏れていた。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `MinecraftForge.EVENT_BUS.register(new BucketFillEvent())` (`cpw.mods.fml.common.eventhandler.SubscribeEvent`) | `MinecraftForge.EVENT_BUS.register` 維持 (`net.minecraftforge.fml.common.eventhandler.SubscribeEvent`) | `MinecraftForge.EVENT_BUS.register` 維持だが `@SubscribeEvent` は `net.minecraftforge.eventbus.api.SubscribeEvent` (1.13+) に変更。`IEventBus` (`ModContainer`) 管理も | **同左 + `@Mod.EventBusSubscriber(modid="defeatedcrow", bus=Mod.EventBusSubscriber.Bus.FORGE)` で登録するか、`MinecraftForge.EVENT_BUS.register(new BucketFillEvent())` を `FMLCommonSetupEvent` で** | `DCsAppleMilk.java:xxx` |
| `FMLCommonHandler.instance().bus().register(new CraftingEvent())` (`PlayerEvent.ItemCraftedEvent` は FML bus) | `MinecraftForge.EVENT_BUS.register` に統合（FML bus廃止、Forge busに統合） | 同左 | **同左。`FMLCommonHandler` は削除（`ServerLifecycleEvent` 等に置換）** | `event/CraftingEvent.java:1` |
| `MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...))` (Client/Server両busにpost) | 維持 (`EventBus.post`) | `MinecraftForge.EVENT_BUS.post` 維持だが `Result` → `Event.Result` | **同左 + `IEventBus` の `post` は `boolean` を返すが `Forge.EVENT_BUS.post` は `Event` を返す。`event.setResult(Event.Result.ALLOW)` 制御は `Result` が `ALLOW/DENY/DEFAULT` のまま** | `block/BlockTeaMakerNext.java:xx` |

## パッケージ変更

| 1.7.10 | 1.12.2 | 1.16+ | 1.20.1 |
|---|---|---|---|
| `cpw.mods.fml.common.eventhandler.Event` | `net.minecraftforge.fml.common.eventhandler.Event` | `net.minecraftforge.eventbus.api.Event` | **同左 (`net.minecraftforge.eventbus.api.Event`)** |
| `cpw.mods.fml.common.eventhandler.SubscribeEvent` | `net.minecraftforge.fml.common.eventhandler.SubscribeEvent` | `net.minecraftforge.eventbus.api.SubscribeEvent` | **同左** |
| `cpw.mods.fml.common.eventhandler.Event.Result` | `net.minecraftforge.fml.common.eventhandler.Event.Result` | `net.minecraftforge.eventbus.api.Event.Result` | **同左** |
| `cpw.mods.fml.common.FMLCommonHandler` | `FMLCommonHandler` 維持（一部） | `MinecraftForge` に統合 | **削除。`ServerLifecycleEvent`, `LevelEvent`, `TickEvent` 等に分離** |
| `cpw.mods.fml.common.gameevent.TickEvent` | `FMLCommonHandler` | `net.minecraftforge.event.TickEvent` | **同左 (`net.minecraftforge.event.TickEvent` → `TickEvent.LevelTickEvent` / `ServerTickEvent`)** |

## メソッド移行表

| Event | 1.7.10 シグネチャ | 1.12.2 | 1.16.5 | 1.20.1 |
|---|---|---|---|---|
| `FillBucketEvent` | `FillBucketEvent(World, MovingObjectPosition, ItemStack empty, ItemStack result)` → `event.world, event.target (MovingObjectPosition), event.current` | `FillBucketEvent(Player, ItemStack, Level, HitResult)` | `FillBucketEvent(Player, ItemStack, Level, BlockHitResult)` | **同左 + `event.getTarget()` は `HitResult` → `BlockHitResult` にキャスト。`getEmptyBucket()` / `getFilledBucket()` + `setFilledBucket(ItemStack)`** |
| `BonemealEvent` | `BonemealEvent(EntityPlayer, World, Block, int x,int y,int z)` → `event.world.getBlock(x,y,z)` | `BonemealEvent(Player, Level, BlockPos, BlockState, ItemStack)` | 同左 | **同左 + `event.getLevel()` + `event.getPos()` + `event.getState()` + `event.getStack()`** |
| `LivingUpdateEvent` | `LivingUpdateEvent(EntityLivingBase entityLiving)` → `event.entityLiving, worldObj` | `LivingEvent.LivingUpdateEvent(LivingEntity)` | 同左 | **同左 (`LivingEvent.LivingTickEvent`)** |
| `LivingHurtEvent` | `LivingHurtEvent(EntityLivingBase, DamageSource, float ammount)` → `event.entityLiving, event.source, event.ammount` | `LivingHurtEvent(LivingEntity, DamageSource, float)` | 同左 | **同左 + `event.getSource()` + `event.getAmount()` / `setAmount(float)`** |
| `LivingDropsEvent` | `LivingDropsEvent(EntityLivingBase, DamageSource, Collection<EntityItem>, int lootingLevel, boolean recentlyHit)` | `LivingDropsEvent(LivingEntity, DamageSource, Collection<EntityItem>, int, boolean)` | 同左 | **同左 + `Collection<ItemEntity>`（`EntityItem` → `ItemEntity`）** |
| `ItemTooltipEvent` | `ItemTooltipEvent(ItemStack, EntityPlayer, List<String> toolTip, boolean showAdvanced)` | `ItemTooltipEvent(ItemStack, Player, List<Component>, TooltipFlag)` | 同左 | **同左** |
| `LivingSpawnEvent.CheckSpawn` | `CheckSpawn(EntityLiving, World, float x, float y, float z, ...)` | `LivingSpawnEvent.CheckSpawn(Mob, LevelAccessor, MobSpawnType, BlockPos)` | `MobSpawnEvent` (1.19) | **同左 (`MobSpawnEvent.FinalizeSpawn` / `AllowDespawn` に細分化。`CheckSpawn` は 1.19で削除、 `MobSpawnEvent.SpawnPlacementCheck` や `PositionCheck` に置換される場合あり)** |
| `AMTBlockRightClickEvent` (自作) | `AMTBlockRightClickEvent(EntityPlayer, World, int x,int y,int z, Block, int meta, ItemStack)` | `AMTBlockRightClickEvent(Player, Level, BlockPos, BlockState, ItemStack)` | 同左 | **同左 + `Level` + `BlockPos` + `BlockState` + `InteractionResult` を返す形に整理** |
| `BucketFillEvent` / `CraftingEvent` | `FMLCommonHandler.instance().bus().register` | `MinecraftForge.EVENT_BUS.register` に統合 | 同左 | **同左** |

## World/BlockPos 移行例

```java
// 1.7.10 (DCsBonemealEvent.java:1)
Block id = event.world.getBlock(event.x, event.y, event.z);
if(id == DCsAppleMilk.cropMint){
  if(((BlockMintCrop)DCsAppleMilk.cropMint).fertilize(event.world, event.x, event.y, event.z)){
    event.setResult(Result.ALLOW);
  }
}

// 1.16.5
BlockState state = event.getLevel().getBlockState(event.getPos());
if(state.is(DCsAppleMilk.cropMint)){
  if(((BlockMintCrop)state.getBlock()).fertilize(event.getLevel(), event.getPos(), state)){
    event.setResult(Event.Result.ALLOW);
  }
}

// 1.20.1
BlockState state = event.getLevel().getBlockState(event.getPos());
if(state.is(ModBlocks.CROP_MINT.get())){
  if(((BlockMintCrop)state.getBlock()).grow(event.getLevel(), event.getPos(), state, (ServerLevel)event.getLevel())){
    event.setResult(Event.Result.ALLOW);
  }
}
// 注: 1.20.1では BonemealEvent の fertilize は `BonemealableBlock` の `isValidBonemealTarget` / `isBonemealSuccess` / `performBonemeal` に分離されている場合、event経由ではなくBlock側で実装
```

### 1.7.10 → 1.20.1 での自作イベントの刷新

```java
// 1.7.10 (api/events/AMTBlockRightClickEvent.java)
public class AMTBlockRightClickEvent extends Event {
  public final EntityPlayer player; public final World world; public final int x,y,z; public final Block block; public final int meta; public final ItemStack held;
  public AMTBlockRightClickEvent(EntityPlayer p, World w, int x,int y,int z, Block b, int meta, ItemStack s){ ... }
}

// 1.20.1
public class AMTBlockRightClickEvent extends Event {
  public final Player player; public final Level level; public final BlockPos pos; public final BlockState state; public final ItemStack held;
  public AMTBlockRightClickEvent(Player p, Level l, BlockPos pos, BlockState s, ItemStack held){ this.player=p; this.level=l; this.pos=pos; this.state=s; this.held=held; }
  @Override public boolean isCancelable(){ return true; }
  // postは MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...)) で、戻り値が trueなら cancel された
}
```

## 検証手順
1. `grep -r "cpw.mods.fml.common.eventhandler"` → `net.minecraftforge.eventbus.api` 置換確認。
2. `grep -r "FMLCommonHandler.instance().bus()"` → `MinecraftForge.EVENT_BUS` 統合確認。
3. `grep -r "getBlock(x,y,z)"` → `getBlockState(pos)` 置換確認。
4. `gradlew build` で `@SubscribeEvent` のimport未解決エラー解消確認（`net.minecraftforge.eventbus.api.SubscribeEvent`）。
5. 1.20.1追加: `grep -r "LivingSpawnEvent"` → `MobSpawnEvent` に置換されているか（残存があればリネーム）。

## 関連
- [Event 一覧](../events.md) / [個別ページ索引](./README.md)
- [Block 一覧](../blocks.md) - `AMTBlockRightClickEvent`
- [Item 一覧](../items.md) - `ShootingGunEvent`
- [ビルド移行ガイド](../build.md) - Mojmapで `LivingEntity` 等のMCP名が Mojmap名に
- `src/main/java/mods/defeatedcrow/event/*`
- `src/main/java/mods/defeatedcrow/api/events/*`
