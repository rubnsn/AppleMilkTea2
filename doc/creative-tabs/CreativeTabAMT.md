# CreativeTabAMT (applemilk)

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilk = new CreativeTabAMT("applemilk")`)
> TabID: `applemilk`
> Icon: `DCsAppleMilk.teaMakerNext` (`getTabIconItem()` / `getIconItemStack()`)
> Class: `CreativeTabAMT extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`)

## 概要
メインタブ。機器・汎用。TeaMaker等。

## 登録情報
- **クラス**: `CreativeTabAMT extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`)
- **コンストラクタ**: `super("applemilk")`
- **インスタンス**: `DCsAppleMilk.applemilk = new CreativeTabAMT("applemilk")` (`DCsAppleMilk.java:144`)
- **Icon**: `getTabIconItem()` -> `DCsAppleMilk.teaMakerNext`

## 割り当て
- **setCreativeTab**: 各Block/Itemで `setCreativeTab(DCsAppleMilk.applemilk)` (`MaterialRegister.java:247-410`)

## 移行 (1.12.2+)
- [ ] `CreativeTabs` -> `ItemGroup` (1.14) -> `CreativeModeTab` (1.19 Registry)
- [ ] `getTabIconItem()` -> `makeIcon(): ItemStack` / `icon(Supplier)`
- [ ] `DeferredRegister<CreativeModeTab>` + `displayItems` に移行 (1.19.3+)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`
> 最終更新: 2026-08-24