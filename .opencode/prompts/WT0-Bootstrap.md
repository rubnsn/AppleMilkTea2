# WT0 Bootstrap — 人間用opencodeプロンプト（最初に1回だけ）

> branch: `feature/1.20.1-bootstrap` (base `dev`), 所有: HotSpot + registry雛形  
> このWTは他WTが始まる前に必ず devへマージする

## 起動コマンド

```powershell
git checkout dev
git checkout -b feature/1.20.1-bootstrap
opencode --agent bootstrap
```

## コピペプロンプト

```
あなたは Bootstrap 担当。所有: common/DCsAppleMilk.java, common/MaterialRegister.java, common/CommonProxy.java, client/ClientProxy.java, common/config/*, asm/*, common/registry/Mod*.java, mods.toml, build.gradle 等。
禁止: common/block/**, common/tile/**, common/fluid/**, common/item/**, client/** の葉ファイルは触らない。

DOC: doc/build.md, doc/tile-entities/migration-guide.md:12, doc/fluids/migration-guide.md:12, doc/blocks/migration-guide.md:174

タスク:
1. DCsAppleMilk.java を @Mod(MODID) + MODID="defeatedcrow" + DeferredRegister束ねに縮退。旧 static Block/Item/Fluid/modelXXX は RegistryObject 委譲か削除。SidedProxyは維持か削除か判断（1.20.1では不要）。
2. MaterialRegister.java の GameRegistry.register* を全削除し、common/registry/Mod*.java 8件が正であることを保証。addFluid/addPotionは ModFluids/ModMobEffectsへ移管。
3. CommonProxy.registerTileEntity の47件は ModBlockEntities へ、ClientProxy の bindTileEntitySpecialRenderer 38件と registerBlockHandler 44件は ModClientEvents への移行メモを残す。
4. asm/* の CoreMod (AppleMilkCorePlugin) は削除（EndlessIDsは1.20.1不要）。
5. コード断片はコメントアウトで残さず完全に DeferredRegister 化。

完了後: git add .opencode/ common/registry/ CODEOWNERS plan.md; git commit -m "chore: 1.20.1 bootstrap — DeferredRegister skeletons + ownership"
→ devへ merge --no-ff してから他WTを作成。
```
