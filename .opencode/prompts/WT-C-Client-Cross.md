# WT-C Client+Cross — 人間用opencodeプロンプト（コピペで起動）

> worktree: `../AMT2-WT-C` (branch `feature/client-cross`, base `dev`)  
> 所有: `client/**` 180 + `potion/**` 7 + `recipe/**` 15 + `network/**` 3 + `plugin/**` 66  
> 禁止: HotSpot、common/block/**, common/tile/**, common/fluid/**

## 起動コマンド

```powershell
git worktree add ../AMT2-WT-C -b feature/client-cross dev
cd ../AMT2-WT-C
opencode --agent wt-c --port 4098
```

## コピペプロンプト

```
あなたは WT-C (Client+Cross) 担当。所有: client/**, potion/**, recipe/**, network/**, plugin/** のみ。
禁止: common/DCsAppleMilk.java, common/MaterialRegister.java, common/CommonProxy.java, client/ClientProxy.java の直編集は不可（ClientProxyは BootstrapがModClientEventsへ移行済みを想定、あなたは client/model/**, client/renderblocks/**, client/gui/** を BER/BakedModelへ）。common/registry/Mod*.java は // --- WT-C: ... --- 内のみ追記。

DOC:
- doc/tile-entities/migration-guide.md:106 (BER: BlockEntityRenderer + Context, RenderType.cutout)
- doc/potions/migration-guide.md (Potion→MobEffect)
- doc/recipes/migration-guide.md (RecipeType/Serializer)
- doc/network/migration-guide.md:58 (SimpleNetworkWrapper→SimpleChannel, FriendlyByteBuf, NetworkEvent.Context)
- doc/plugins/migration-guide.md:78 (Omit/Keep表 — 大半削除、Bamboo保留)

タスク:
1. client/renderblocks/RenderProcessor.java 等44 ISBRHを削除。TileEntitySpecialRenderer→BlockEntityRenderer<T> (BlockEntityRendererProvider.Context ctor) へ。RenderingRegistry.registerBlockHandler→BlockEntityRenderers.register + EntityRenderersEvent.RegisterRenderers。
2. client/model/** 86件の Modelは bakeLayer へ。
3. potion/** 7件を MobEffectへ（ModMobEffects.java の // --- WT-C: MOB EFFECTS --- に登録）。
4. network/DCsNetworkHandler.java を SimpleChannel (NetworkRegistry.newSimpleChannel + messageBuilder + FriendlyByteBuf) へ。MessageCharmWarpは IMessage削除、encode/decode/handle 3メソッドへ。
5. plugin/** 66件は表通り大半削除。LoadThaumcraft/NEI/CraftGuide/MCE等は削除、Bambooは保留。

pwsh -File scripts/lint-migration.ps1 -Check wtc
```

## 検証

```powershell
pwsh -File scripts/lint-migration.ps1 -Check wtc
# 期待: wtc: PASS (RenderingRegistry.registerBlockHandler:0, SimpleNetworkWrapper:0, IMessage:0)
```
