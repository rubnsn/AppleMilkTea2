# WT0 Bootstrap — 人間用opencodeプロンプト（最初に1回だけ）

> branch: `feature/1.20.1-bootstrap` (base `dev`), 所有: HotSpot + registry雛形（初期）→ 最終的には全ファイル対象  
> このWTは他WTが始まる前に必ず devへマージする（bootstrap後は全域修正可）

## 起動コマンド

```powershell
git checkout dev
git checkout -b feature/1.20.1-bootstrap
opencode --agent bootstrap
```

## コピペプロンプト

```
あなたは Bootstrap 担当。所有: common/DCsAppleMilk.java, common/MaterialRegister.java, common/CommonProxy.java, client/ClientProxy.java, common/config/*, asm/*, common/registry/Mod*.java 9件, mods.toml, build.gradle, gradle.properties 等。※初期所有は上記だが最終的には全ファイル触る想定（他WTと競合時はWT0優先で調整）。
禁止: 初期は common/block/**, common/tile/**, common/fluid/**, common/item/**, client/** 葉は他WT優先のため最小限に。最終的には全域修正可（bootstrapなので実質禁止なし）。

DOC: doc/build.md, doc/build.md:22, doc/tile-entities/migration-guide.md:12, doc/fluids/migration-guide.md:12, doc/blocks/migration-guide.md:174, doc/config/migration-guide.md

タスク:
1. DCsAppleMilk.java を @Mod("DCsAppleMilk") + public static final String MODID="DCsAppleMilk" + IEventBus/DeferredRegister束ねに縮退。旧 static Block/Item/Fluid/modelXXX は RegistryObject 委譲か削除。SidedProxyは DistExecutor/IEventBus へ置換または削除（1.20.1では @SidedProxy 非推奨）。
2. MaterialRegister.java の GameRegistry.register* を全削除し、common/registry/Mod*.java 9件（ModBlocks/ModItems/ModFluids/ModFluidTypes/ModBlockEntities/ModMenuTypes/ModEntities/ModMobEffects/ModCreativeTabs）が正であることを保証。addFluid/addPotionは ModFluids/ModFluidTypes/ModMobEffectsへ移管。
3. CommonProxy.registerTileEntity の47件は ModBlockEntities へ、ClientProxy の bindTileEntitySpecialRenderer 38件と registerBlockHandler 44件 + registerEntityRenderingHandler 23件は ModBlockEntities/ModClientEvents への移行メモ（BlockEntityRenderers.register / EntityRenderers.register）を残す。
4. asm/* の CoreMod (AppleMilkCorePlugin) は削除（EndlessIDsは1.20.1不要。mods.tomlのcoremod指定も削除）。
5. コード断片はコメントアウトで残さず完全に DeferredRegister 化。api/* は凍結読取専用（変更必要ならWT0で集約）。

完了後: git add .opencode/ common/registry/ CODEOWNERS plan.md; git commit -m "chore: 1.20.1 bootstrap — DeferredRegister skeletons + ownership"
→ devへ merge --no-ff してから他WTを作成。
```
