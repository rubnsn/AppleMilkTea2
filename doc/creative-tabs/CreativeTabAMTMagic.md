# CreativeTabAMTMagic (applemilkMagic)

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMagic.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilkMagic = new CreativeTabAMTMagic("applemilkmagic")`)
> TabID: `applemilkmagic`
> Icon: `DCsAppleMilk.princessClam` (`getTabIconItem()` / `getIconItemStack()`)
> Class: `CreativeTabAMTMagic extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTMagic.java:1`)

## 概要
魔法タブ。お香・チャーム。

## 登録情報
- **クラス**: `CreativeTabAMTMagic extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTMagic.java:1`)
- **コンストラクタ**: `super("applemilkmagic")`
- **インスタンス**: `DCsAppleMilk.applemilkMagic = new CreativeTabAMTMagic("applemilkmagic")` (`DCsAppleMilk.java:144`)
- **Icon**: `getTabIconItem()` -> `DCsAppleMilk.princessClam`

## 割り当て
- **setCreativeTab**: 各Block/Itemで `setCreativeTab(DCsAppleMilk.applemilkMagic)` (`MaterialRegister.java:247-410`)

## 移行 (1.12.2+)
- [ ] `CreativeTabs` -> `ItemGroup` (1.14) -> `CreativeModeTab` (1.19 Registry)
- [ ] `getTabIconItem()` -> `makeIcon(): ItemStack` / `icon(Supplier)`
- [ ] `DeferredRegister<CreativeModeTab>` + `displayItems` に移行 (1.19.3+)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMagic.java:1`
> 最終更新: 2026-08-24