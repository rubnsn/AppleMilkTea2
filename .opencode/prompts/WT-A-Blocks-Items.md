# WT-A Blocks+Items — 人間用opencodeプロンプト（コピペで起動）

> worktree: `../AMT2-WT-A` (branch `feature/blocks-items`, base `dev`)  
> 所有: `common/block/**` 128 + `common/item/**` 59 + `CreativeTab*.java`×5  
> 禁止: `DCsAppleMilk.java` / `MaterialRegister.java` / `CommonProxy` / `ClientProxy` / `common/registry/Mod*.java` の雛形外は触らない

## 起動コマンド

```powershell
git worktree add ../AMT2-WT-A -b feature/blocks-items dev
cd ../AMT2-WT-A
opencode --agent wt-a
# もしくは TUI内で /agent wt-a
```

## コピペプロンプト（opencodeに貼る）

```
あなたは WT-A (Blocks+Items) 担当。所有: common/block/**, common/item/**, common/CreativeTab*.java のみ。
禁止: common/DCsAppleMilk.java, common/MaterialRegister.java, common/CommonProxy.java, client/ClientProxy.java は読取専用。common/registry/Mod*.java は自分のセクション // --- WT-A: ... --- 内のみ追記。

DOCを厳守:
- doc/blocks/migration-guide.md の1.20.1追記（BlockBehaviour.Properties, VoxelShape, BlockState Property, DeferredRegister, BlockEntityType.Builder.of）
- doc/items/migration-guide.md の1.20.1追記（NBT維持、DataComponentsは1.20.5+なので導入しない）

タスク:
1. common/block/appliance/BlockProcessor.java から開始。IIcon/registerBlockIcons/getIcon/setBlockName/getRenderType を削除し、BlockBehaviour.Properties.of().mapColor(...).strength(...).noOcclusion() へ。World,int x,y,z → Level,BlockPos,BlockState へ。
2. GameRegistry.registerBlock は削除。代わりに common/registry/ModBlocks.java の // --- WT-A: APPLIANCE --- に RegistryObject を追記し、ItemBlockは ModItems.java の // --- WT-A: BLOCK-ITEMS --- に追記。
3. 同様に container/edible/brewing/plants/decorative/energy の全74 Blockを回す。
4. common/item/** の全59 Itemを Item.Properties + FoodProperties + Tier へ。leafTea等の亜種は個別Item化、largeBottle等の多亜種はNBT維持。
5. 終了条件: grepで自分の所有内に IIcon/registerBlockIcons/getIcon/setBlockName/GameRegistry.registerBlock が0件。

ビルドは最後に一括なので今は lint のみ。pwsh -File scripts/lint-migration.ps1 -Check wta で確認。
```

## 検証（人手で回す）

```powershell
pwsh -File scripts/lint-migration.ps1 -Check wta
# 期待: wta: PASS (IIcon:0, setBlockName:0, GameRegistry:0)
```
