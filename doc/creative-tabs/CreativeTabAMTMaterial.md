# CreativeTabAMTMaterial (applemilkMaterial)

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMaterial.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilkMaterial = new CreativeTabAMTMaterial("applemilkmaterial")`)
> TabID: `applemilkmaterial`
> Icon: `DCsAppleMilk.leafTea` (`getTabIconItem()` / `getIconItemStack()`)
> Class: `CreativeTabAMTMaterial extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTMaterial.java:1`)

## 概要
素材タブ。茶葉・粉・素材。

## 登録情報
- **クラス**: `CreativeTabAMTMaterial extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTMaterial.java:1`)
- **コンストラクタ**: `super("applemilkmaterial")`
- **インスタンス**: `DCsAppleMilk.applemilkMaterial = new CreativeTabAMTMaterial("applemilkmaterial")` (`DCsAppleMilk.java:144`)
- **Icon**: `getTabIconItem()` -> `DCsAppleMilk.leafTea`

## 割り当て
- **setCreativeTab**: 各Block/Itemで `setCreativeTab(DCsAppleMilk.applemilkMaterial)` (`MaterialRegister.java:247-410`)

## 移行 (1.12.2+)
- [ ] `CreativeTabs` -> `ItemGroup` (1.14) -> `CreativeModeTab` (1.19 Registry)
- [ ] `getTabIconItem()` -> `makeIcon(): ItemStack` / `icon(Supplier)`
- [ ] `DeferredRegister<CreativeModeTab>` + `displayItems` に移行 (1.19.3+)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMaterial.java:1`
> 最終更新: 2026-08-24