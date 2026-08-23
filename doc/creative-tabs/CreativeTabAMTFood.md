# CreativeTabAMTFood (applemilkFood)

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTFood.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilkFood = new CreativeTabAMTFood("applemilkfood")`)
> TabID: `applemilkfood`
> Icon: `DCsAppleMilk.bakedApple` (`getTabIconItem()` / `getIconItemStack()`)
> Class: `CreativeTabAMTFood extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTFood.java:1`)

## 概要
食物タブ。完成品・飲料。

## 登録情報
- **クラス**: `CreativeTabAMTFood extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabAMTFood.java:1`)
- **コンストラクタ**: `super("applemilkfood")`
- **インスタンス**: `DCsAppleMilk.applemilkFood = new CreativeTabAMTFood("applemilkfood")` (`DCsAppleMilk.java:144`)
- **Icon**: `getTabIconItem()` -> `DCsAppleMilk.bakedApple`

## 割り当て
- **setCreativeTab**: 各Block/Itemで `setCreativeTab(DCsAppleMilk.applemilkFood)` (`MaterialRegister.java:247-410`)

## 移行 (1.12.2+)
- [ ] `CreativeTabs` -> `ItemGroup` (1.14) -> `CreativeModeTab` (1.19 Registry)
- [ ] `getTabIconItem()` -> `makeIcon(): ItemStack` / `icon(Supplier)`
- [ ] `DeferredRegister<CreativeModeTab>` + `displayItems` に移行 (1.19.3+)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)

> 自動生成: `src/main/java/mods/defeatedcrow/common/CreativeTabAMTFood.java:1`
> 最終更新: 2026-08-24