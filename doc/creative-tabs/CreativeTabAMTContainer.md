# CreativeTabAMTContainer (applemilkContainer)

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTContainer.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilkContainer = new CreativeTabAMTContainer("applemilkcontainer")`)
> TabID: `applemilkcontainer`
> Icon: `DCsAppleMilk.woodBox` (`getTabIconItem()` / `getIconItemStack()`)
> Class: `CreativeTabAMTContainer extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTContainer.java:1`)

## 概要
コンテナタブ。収納・圧縮。

## 登録情報
- **クラス**: `CreativeTabAMTContainer extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTContainer.java:1`)
- **コンストラクタ**: `super("applemilkcontainer")`
- **インスタンス**: `DCsAppleMilk.applemilkContainer = new CreativeTabAMTContainer("applemilkcontainer")` (`DCsAppleMilk.java:144`)
- **Icon**: `getTabIconItem()` -> `DCsAppleMilk.woodBox`

## 割り当て
- **setCreativeTab**: 各Block/Itemで `setCreativeTab(DCsAppleMilk.applemilkContainer)` (`MaterialRegister.java:247-410`)

## 移行 (1.12.2+)
- [ ] `CreativeTabs` -> `ItemGroup` (1.14) -> `CreativeModeTab` (1.19 Registry)
- [ ] `getTabIconItem()` -> `makeIcon(): ItemStack` / `icon(Supplier)`
- [ ] `DeferredRegister<CreativeModeTab>` + `displayItems` に移行 (1.19.3+)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTContainer.java:1`
> 最終更新: 2026-08-24