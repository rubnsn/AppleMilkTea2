# ConfigKey

> Category: `potionID | entityid | world setting | setting | render setting | entity setting | difficulty setting | debug setting`
> Source: `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:xx`
> File: `config/DCsAppleMilk.cfg` (`Configuration.get("category", "key", default, "comment")`)
> Field: `DCsConfig.fieldName` (type `int` / `boolean` / `double` / `String` / `boolean[]`)
> Default: `xx` (range や意味)

## 概要
[1-2文でConfigの役割・効果を記述。ゲーム難易度/生成/描画/Entity制御の分類]

## 登録情報
- **キー**: `category` / `key` (`cfg.get("category", "key", default, "comment")` at `DCsConfig.java:125`)
- **フィールド**: `public static int/boolean fieldName = default;` (`DCsConfig.java:xx`)
- **読み込み**: `fieldName = property.getInt()/getBoolean()/getString()/getBooleanList()` (`DCsConfig.java:360`)
- **参照箇所**: `[検索]` (`grep -r "DCsConfig.fieldName" src/`)
- **反映タイミング**: `DCsAppleMilk.preInit` (`DCsAppleMilk.java:476`で `new DCsConfig().config(cfg)` 呼出)

## プロパティ / 値
- **型**: `int` / `boolean` / `double` / `String`
- **範囲**: 例: `1-20` (`teaTreeGenValue` は `DCsAppleMilk.java:740`でクランプ) / `0=auto` (`entityId` は 0で `findGlobalUniqueEntityId`)
- **再起動要否**: 要（Configは起動時読込）
- **例**: `fieldName=5` → 効果 `xxx`

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `net.minecraftforge.common.config.Configuration` (1.7) → `net.minecraftforge.common.ForgeConfigSpec` (1.14+ TOML) + `ModConfig` (`ModConfig.Type.COMMON` / `CLIENT` / `SERVER`)
- [ ] `cfg.get("category","key",default,"comment")` → `BUILDER.comment("comment").define("key", default)` / `defineInRange("key", default, min, max)` / `define("key", default)` / `defineList`
- [ ] `config/DCsAppleMilk.cfg` (INICFG) → `config/defeatedcrow-common.toml` / `config/defeatedcrow-client.toml` (TOML)
- [ ] `DCsConfig.fieldName` static 直参照 → `ConfigHolder.COMMON.fieldName.get()` / `ConfigValue<Integer>` 経由に置換（`Supplier` キャッシュ推奨）
- [ ] 範囲チェック: `teaTreeGenValue` の手動クランプ (`DCsAppleMilk.java:740`) → `defineInRange(...,1,20)` で自動
- [ ] 配列 `boolean[] enableMobBlock` は `defineList` + `ConfigValue<List<? extends Boolean>>` へ
- [ ] `@Config` アノテーション方式 (1.12 `net.minecraftforge.common.config.Config`) は 1.13で廃止 → `ForgeConfigSpec` に移行
- [ ] `DCsConfigCocktail` の別ファイル (`DCsAppleMilk-cocktail.cfg`) も同様に `common.toml` 内サブカテゴリまたは別TOMLへ
- [ ] `debugPass` の平文保持は `Config` の取り扱い上維持だが `String` 型で `define` → `get()` へ
- [ ] クライアント同期が必要な値 (`setCupScale`, `setCupTexture` 等) は `CLIENT` config に分離し、`ModConfigEvent` で再読込

## 関連ドキュメント
- [Config 一覧](../config.md)
- [カテゴリ別一覧](./README.md) (本索引)
- [移行ガイド](./migration-guide.md)
- `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:1`
