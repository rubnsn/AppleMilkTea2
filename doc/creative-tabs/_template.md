# CreativeTabName

> Source: `src/main/java/mods/defeatedcrow/common/CreativeTabXxx.java:1`
> Registry: `CreativeTabs` (`DCsAppleMilk.java:144` `public static final CreativeTabs applemilk = new CreativeTabAMT("applemilk")`)
> Icon: `getIconItemStack()` / `getTabIconItem()` で `ItemStack` 返却

## 概要
[1-2文でタブの用途を記述。どのカテゴリのアイテムを集約するか]

## 登録情報
- **クラス**: `CreativeTabXxx extends CreativeTabs` (`src/main/java/mods/defeatedcrow/common/CreativeTabXxx.java:1`)
- **コンストラクタ**: `super("applemilk")` / `super("applemilkmaterial")` 等 (`CreativeTabs` の `label`)
- **インスタンス**: `DCsAppleMilk.applemilk = new CreativeTabAMT("applemilk")` (`DCsAppleMilk.java:144`)
- **Icon**: `@Override public Item getTabIconItem()` → `Item.getItemFromBlock(DCsAppleMilk.teaMakerNext)` / `DCsAppleMilk.leafTea` 等。1.12+は `ItemStack` 版も
- **Search**: デフォルトで `hasSearchBar=false` (AMTは全タブで検索バーなし)

## 継承・インターフェース
- 継承: `net.minecraft.creativetab.CreativeTabs` (1.7) → `CreativeTabs` (1.12) → `CreativeModeTab` (1.16+) → `CreativeModeTab` (1.19+ Registry)
- オーバーライド: `getTabIconItem()` / `getIconItemStack()` / `getTranslatedTabLabel()` / `displayAllRelevantItems` 等

## 割り当て
- **setCreativeTab**: 各 `Block` / `Item` のコンストラクタで `setCreativeTab(DCsAppleMilk.applemilk)` により所属先指定 (`MaterialRegister.java:247-410` / `MaterialRegister.java:500+` で呼出)
- **例**: `DCsAppleMilk.bakedApple.setCreativeTab(DCsAppleMilk.applemilkFood)` (`MaterialRegister.java:xxx`)

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `CreativeTabs` → `CreativeTabs` 維持 (1.12まで同じ) だが `ItemGroup` にリネームは 1.14から
- [ ] `getTabIconItem()` → `createIcon(): ItemStack` (1.12) → `makeIcon(): ItemStack` (1.16 `CreativeModeTab`)
- [ ] `CreativeTabs("label")` → `CreativeTabs.create("label", supplier)` ではなく `CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0).title(Component.translatable("itemGroup.defeatedcrow.xxx")).icon(()-> new ItemStack(DCsAppleMilk.XXX)).displayItems((params, output)->{ output.accept(DCsAppleMilk.XXX); }).build()` (1.19.3+)
- [ ] `DeferredRegister<CreativeModeTab>` で登録 (1.19.3+): `DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow")` + `CREATIVE_TABS.register("applemilk", ()-> CreativeModeTab.builder()...)`
- [ ] `setCreativeTab` は `Item.Properties.tab(tab)` に移行 (1.14 `Item.Properties` → 1.19 `Item.Properties` / `BlockItem` は `BlockItem` の `Properties`)
- [ ] 言語キー: `itemGroup.applemilk` → `itemGroup.defeatedcrow.applemilk` (`lang/*.json` の `itemGroup` エントリ)

## 関連ドキュメント
- [CreativeTab 一覧](../creative-tabs.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:144`
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`
