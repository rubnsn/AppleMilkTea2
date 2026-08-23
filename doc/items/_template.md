# ItemName（フィールド名）

> Category: `food | material | brewing | tool | appliance | magic | container-item | fluid-container | dummy`
> Source: `src/main/java/mods/defeatedcrow/common/item/ItemXxx.java:1`
> Registry: `defeatedcrow.xxx` (`DCsAppleMilk.fieldName`)
> ItemClass: `ItemXxx extends Item / ItemFood / ItemTool / ItemArmor / ItemBucket / ItemSeeds / ItemBlock ...`
> Implements: `IIncenseEffect / IBattery / IJawPlate / IProcessorPanel / ICompressedItem / IPlantable ...`（該当なしなら `-`）
> CreativeTab: `DCsAppleMilk.applemilk / applemilkMaterial / applemilkFood / applemilkContainer / applemilkMagic`
> StackSize: `64` / `1` （hasSubtypes, maxDamage）

## 概要
[1-2文でItemの用途・特徴を記述。例: 加工用素材 / 完成食品 / ツール / お香 / 充電池]

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:xxx` `public static Item fieldName;`
- **インスタンス生成**: `MaterialRegister.java:xxx` `new ItemXxx()` / `setUnlocalizedName("defeatedcrow.xxx")` (+ `setMaxDamage(0)` `setHasSubtypes(true)` `setMaxStackSize(...)`)
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.fieldName, "defeatedcrow.xxx")` (`MaterialRegister.java:xxx` / `DCsAppleMilk.java:xxx` for dummy)
- **Fluid連携**（該当時のみ）: `FluidContainerRegistry.registerFluidContainer(FluidStack, ItemStack(container), ItemStack(empty))` (`MaterialRegister.addFluid():xxx`)
- **OreDictionary**（該当時のみ）: `OreDictionary.registerOre("xxx", new ItemStack(DCsAppleMilk.fieldName, 1, meta))` (`RegisterOreHandler.java:xxx`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → `par1`（damageをそのままメタとして利用）。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返し、言語ファイルで `item.xxx_0`, `item.xxx_1` を分岐。

## メタデータ / 亜種一覧
| meta | unlocalizedName サフィックス | 用途 / 表示名 | icon |
|---|---|---|---|
| 0 | `_<0>` | 例: `leafTea_0 = 生茶葉` | `defeatedcrow:leaf_raw` |
| 1 | `_<1>` | ... | ... |

> 詳細マッピングは `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメントおよび `ItemXxx.java:xx` の `registerIcons` / `getSubItems` を参照。

## 継承・インターフェース
- 継承: `Item` / `ItemFood` / `ItemTool` / `ItemArmor` / `ItemBucket` / `ItemSeeds` / `ItemBow` / `EdibleEntityItem2` 等
- 実装: `IIncenseEffect`（`effectAreaRange()`, `getEffectType()`, `formEffect()`, `particle*()`）、`IBattery`（`getChargeAmount`, `charge/discharge`）、`IProcessorPanel` / `IJawPlate` / `ICompressedItem` / `IPlantable` / `IShearable` 等

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20+ での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getIconFromDamage` | `IIcon getIconFromDamage(int par1)` | `Item.getIcon(ItemStack, int)` 経由、描画時 | damage → IIcon | 削除。`ModelLoader` + `assets/defeatedcrow/models/item/*.json` + `getTranslationKey` でモデル分岐。`IIconRegister` 廃止 |
| `registerIcons` | `void registerIcons(IIconRegister)` | クライアント初期化 `TextureStitchEvent` 前 | テクスチャ登録 | 削除。`ModelRegistryEvent` / `assets/.../models/item/` JSONへ。`@SideOnly(Side.CLIENT)` 不要 |
| `getMetadata` | `int getMetadata(int par1)` | `ItemStack.getItemDamage` → `getMetadata` 経由 | damage → metadata 変換 | 維持（`getMetadata(int)` は残る）だが 1.13+ でメタ廃止→個別Item or NBTへ分割。`hasSubtypes` は `setHasSubtypes` 維持だが非推奨 |
| `getUnlocalizedName(ItemStack)` | `String getUnlocalizedName(ItemStack)` | ツールチップ / 言語キー取得時 | メタごとに `xxx_0`, `xxx_1` を返す | `getUnlocalizedName` → `getTranslationKey()` (1.11) → `getDescriptionId()` (1.16) にリネーム。`ItemStack` 版は `getTranslationKey(ItemStack)` へ |
| `getSubItems` | `void getSubItems(Item, CreativeTabs, List)` | CreativeTab 表示時 | タブに全メタを列挙 | 維持だが `CreativeTabs` → `CreativeModeTab` / `ItemGroup` (1.14+)。`NonNullList<ItemStack>`, `CreativeModeTab.TabVisibility` に変更。`fillItemCategory` (1.16) にリネーム |
| `onItemUse` | `boolean onItemUse(ItemStack, EntityPlayer, World, int x, int y, int z, int side, float hitX, float hitY, float hitZ)` | ブロック右クリック時 (`PlayerInteractEvent`) | 骨粉効果(ClamDust), 設置等 | `BlockPos`, `World`, `EntityPlayer`, `EnumHand`, `EnumFacing`, `float` に変更。`ItemUseContext` (1.14+) + `useOn(UseOnContext)` へ |
| `onItemRightClick` | `ItemStack onItemRightClick(ItemStack, World, EntityPlayer)` | 空中右クリック時 | 飲食開始(`setItemInUse`)、バケツ等 | `World, Player, InteractionHand` → `InteractionResultHolder<ItemStack>` に変更。`use(World, Player, InteractionHand)` |
| `onEaten` / `onFoodEaten` | `ItemStack onEaten(ItemStack, World, EntityPlayer)` | 飲食完了時 | ポーション付与、空容器返却 | `finishUsingItem(ItemStack, World, LivingEntity)` に統合。`onFoodEaten` は削除 |
| `addInformation` | `void addInformation(ItemStack, EntityPlayer, List, boolean)` | ツールチップ描画時 | Shift時拡張表示等 | 維持だが `List<String>` → `List<Component>`、引数 `Level, List<Component>, TooltipFlag` に変更。`appendHoverText` (1.16+) |
| `hasEffect` / `getRarity` | `boolean hasEffect(ItemStack)` / `EnumRarity getRarity(ItemStack)` | 描画時 | エンチャント光・レア色 | 維持だが `getRarity` → `getRarity(ItemStack)` は `Rarity` enum に変更 |
| `onBlockStartBreak` / `onBlockDestroyed` | `boolean onBlockStartBreak(ItemStack, int x, int y, int z, EntityPlayer)` | ブロック破壊時 | はさみ分岐等 | `BlockPos`, `Player` に変更。`mineBlock` / `onBlockStartBreak` シグネチャ変更 |
| `itemInteractionForEntity` | `boolean itemInteractionForEntity(ItemStack, EntityPlayer, EntityLivingBase)` | エンティティ右クリック時 | 羊の毛刈り等 | `interactLivingEntity(ItemStack, Player, LivingEntity, InteractionHand)` に変更 |

## レシピ / イベント / その他連携
- レシピ: `DCsRecipeRegister` / `RegisterMakerRecipe.registerXxx()` で素材として使用。例: `leafTea:0` は `TeaMaker` レシピ、`mincedFoods` は `Pan` / `Plate` レシピ。
- イベント: `CraftingEvent` で `DCgrater` の耐久減耗、`ShowOreNameEvent` で `monocle` の鉱石辞書表示、`BucketFillEvent` / `FluidDispenser` で流体バケツの設置。
- チャージ: `ChargeItemManager` + `IBattery` 実装（`ItemBattery`, `ItemYuzuGatling`, `ItemFossilCannon`, `ItemDebugArm`）。`PropertyHandler.rateRF/EU/GF()` で換算。
- お香: `IIncenseEffect` 実装（`ItemIncense*` 11種）。`TileIncenseBase` が `formEffect()` をtickごとに呼び出し、パーティクル `particleIcon` / `particleColor*()` .
- プラント: `IPlantable` 実装（`ItemMintSeed`）。`getPlantType` / `getPlant` / `getPlantMetadata`。

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.xxx")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "xxx")` (1.11+ `setTranslationKey`, 1.12 `setRegistryName` 必須)
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.xxx")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` で `item.setRegistryName("defeatedcrow", "xxx")`
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/xxx.json` + `textures/...` + `ModelLoader` へ。メタ分岐がある場合は `item/xxx_0.json`, `xxx_1.json` を `overrides` で `predicate:{damage:0}` 分岐、または個別Item化
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`, `damage` 0-15) → 1.13+ でメタ廃止。`NonNullList` / 個別Item / `Capability` / `NBT` で亜種管理へ分割。`@Deprecated getMetadata` は削除
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` (1.16) / `appendStacks` 等。`CreativeTabs` → `CreativeModeTab` / `ItemGroup`
- [ ] `onItemUse` → `useOn(UseOnContext)` / `onItemUse(BlockPos, World, Player, Hand, Facing, hitVec)`。`World` → `Level`, `EntityPlayer` → `Player`, `ItemStack` → `ItemStack` は維持
- [ ] `onItemRightClick` → `use(Level, Player, InteractionHand)` → `InteractionResultHolder<ItemStack>`
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` 。`EnumChatFormatting` → `ChatFormatting` / `Component.literal`
- [ ] `maxStackSize = 64` 直代入 → `properties.stacksTo(64)` に変更（`Item.Properties`）。`setMaxDamage(0)` → `durability(0)` / `defaultDurability`。`setHasSubtypes(true)` は `Item.Properties` では不要、メタ廃止で代替
- [ ] `ItemFood` / `ItemTool` / `ItemArmor` / `ItemBucket` 継承は維持だがコンストラクタ変更（`Item.Properties` 必須、`ToolMaterial` → `Tier`、`ArmorMaterial` → `ArmorMaterial` / `ArmorItem`）
- [ ] `ItemSeeds` / `IPlantable` → `BlockItem` + `IPlantable` インターフェースは `net.minecraftforge.common.IPlantable` 維持。`getPlantType`/`getPlant` は `BlockState` 版に変更
- [ ] `IBattery` / `IIncenseEffect` / `ICompressedItem` 等の自作APIは `Capability` 移行を検討。`ChargeItemManager` → `CapabilityEnergy` / `IEnergyStorage` に置換可能
- [ ] `OreDictionary` → `Tags` / `TagKey<Item>` に移行（1.14+）。`OreDictionary.registerOre` → `TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "xxx"))`
- [ ] 言語ファイル: `assets/defeatedcrow/lang/*.lang` → `assets/defeatedcrow/lang/*.json` (`ja_jp`, `en_us`) に移行。`item.defeatedcrow.xxx_0.name` → `item.defeatedcrow.xxx` + メタ対応は個別キー化

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ
