# CreativeTab 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.19+ / 1.20.1

> 対象: `AppleMilkTea2` 全5 CreativeTab
> 最終更新: 2026-08-24（1.20.1確認: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) / `DCsAppleMilk.java:144-149` / `CreativeTab*.java:1`

## 概要
CreativeTab の移行は比較的軽微だが、1.14で `ItemGroup` → 1.19で `CreativeModeTab` Registry化と段階的に変更。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.19.3+ | 参照 |
|---|---|---|---|---|
| `public static final CreativeTabs applemilk = new CreativeTabAMT("applemilk")` (`DCsAppleMilk.java:144`) | 同左 (`CreativeTabs` 維持) | `CreativeTabs` → `ItemGroup` にリネーム (1.14) だが `CreativeTabAMT` の継承先を `ItemGroup` に変更するだけで互換 | `CreativeModeTab` Registry化: `DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow")` + `TABS.register("applemilk", ()-> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.defeatedcrow.applemilk")).icon(()-> new ItemStack(DCsAppleMilk.teaMakerNext)).displayItems((params, out)->{ out.accept(DCsAppleMilk.leafTea); }).build())` | `DCsAppleMilk.java:144` |
| `extends CreativeTabs` + `getTabIconItem()` → `Item` | `getTabIconItem()` → `createIcon(): ItemStack` / `getIconItemStack()` | `ItemGroup` → `createIcon()` → `makeIcon()` | `CreativeModeTab.builder()` の `icon(Supplier<ItemStack>)` | `CreativeTabAMT.java:1` |
| `Block.setCreativeTab(DCsAppleMilk.applemilk)` / `Item.setCreativeTab` | 維持 | `new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD))` ではなく `Item.Properties` / `BlockItem` の `tab` 指定 | `Item.Properties` ではなく `CreativeModeTab` の `displayItems` で `output.accept` に登録。`setCreativeTab` 廃止 | `MaterialRegister.java:247` |

## コード移行例

```java
// 1.7.10 (CreativeTabAMT.java:1)
public class CreativeTabAMT extends CreativeTabs {
  public CreativeTabAMT(String label){ super(label); }
  public Item getTabIconItem(){ return Item.getItemFromBlock(DCsAppleMilk.teaMakerNext); }
}
// 登録
public static final CreativeTabs applemilk = new CreativeTabAMT("applemilk");
// 使用
new ItemLeafTea().setCreativeTab(DCsAppleMilk.applemilkMaterial);

// 1.12.2 (CreativeTabs 維持、iconを ItemStack 化)
public class CreativeTabAMT extends CreativeTabs {
  public CreativeTabAMT(String label){ super(label); }
  public ItemStack getTabIconItem(){ return new ItemStack(DCsAppleMilk.teaMakerNext); } // 1.12は ItemStack版
}

// 1.19.3+ (Registry化)
public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow");
public static final RegistryObject<CreativeModeTab> APPLEMILK = TABS.register("applemilk",
  ()-> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
    .title(Component.translatable("itemGroup.defeatedcrow.applemilk"))
    .icon(()-> new ItemStack(ModItems.TEA_MAKER_NEXT.get()))
    .displayItems((params, output)->{
      output.accept(ModItems.TEA_MAKER_NEXT.get());
      output.accept(ModItems.LEAF_TEA.get());
    }).build());
// Item側は setCreativeTab 不要、displayItems で登録
```

## 検証手順
1. `grep -r \"setCreativeTab\"` → `displayItems` 置換確認 (1.19+)。
2. `grep -r \"getTabIconItem\"` → `makeIcon` / `icon` 置換確認。
3. `gradlew build` で `CreativeModeTab` 未登録エラー解消確認。
4. `lang/*.json` の `itemGroup.defeatedcrow.applemilk` キー確認。

## 関連
- [CreativeTab 一覧](../creative-tabs.md) / [個別ページ索引](./README.md)
- [Item 一覧](../items.md) / [Block 一覧](../blocks.md) - `setCreativeTab` 移行
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`


---

## 1.20.1 補足

- **1.19.3で Registry化済、1.20.1でも同型**: `DeferredRegister<CreativeModeTab>` + `CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(...).icon(...).displayItems(...).build()` は1.20.1でも維持（Forge 47）。`Registries.CREATIVE_MODE_TAB` + `CreativeModeTab` の `displayItems` で `output.accept`。
- `setCreativeTab` は1.20.1では完全削除、代わりに `CreativeModeTab` の `displayItems` に登録するのみ。旧 `CreativeTabs` / `ItemGroup` は存在しない。
- 言語キー `itemGroup.defeatedcrow.applemilk` は `lang/ja_jp.json` で `"itemGroup.defeatedcrow.applemilk": "Apple Milk Tea!"` として維持。

### 検証 1.20.1

- `grep -r "setCreativeTab"` → 0件（`displayItems` 置換）確認
- `grep -r "CreativeModeTab"` → `DeferredRegister` で登録されているか確認
