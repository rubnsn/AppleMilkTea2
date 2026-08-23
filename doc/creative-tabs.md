# CreativeTab 一覧

> 定義元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:144-149`  
> クラス: `src/main/java/mods/defeatedcrow/common/CreativeTab*.java:1`  
> 総数: **5 Tab**  
> 個別ページ: [`doc/creative-tabs/`](./creative-tabs/README.md) に 5件の個別ページを生成  
> 移行ガイド: [`doc/creative-tabs/migration-guide.md`](./creative-tabs/migration-guide.md)

| フィールド名 | タブID | クラス | アイコン想定 | 説明 |
|---|---|---|---|---|
| `applemilk` | `applemilk` | `CreativeTabAMT:1` | TeaMaker等 | メインタブ（機器・汎用） |
| `applemilkMaterial` | `applemilkmaterial` | `CreativeTabAMTMaterial:1` | leafTea等 | 素材タブ（茶葉・粉・素材） |
| `applemilkFood` | `applemilkfood` | `CreativeTabAMTFood:1` | bakedApple等 | 食物タブ（完成品・飲料） |
| `applemilkContainer` | `applemilkcontainer` | `CreativeTabAMTContainer:1` | woodBox等 | コンテナタブ（収納・圧縮） |
| `applemilkMagic` | `applemilkmagic` | `CreativeTabAMTMagic:1` | princessClam等 | 魔法タブ（お香・チャーム） |

## 割り当て例
- `emptyCup`, `teaMakerNext` → applemilk
- `leafTea`, `foodTea`, `oreDust` → applemilkMaterial
- `bakedApple`, `bowlBlock` → applemilkFood
- `woodBox`, `vegiBag` → applemilkContainer
- `princessClam`, `incense*` → applemilkMagic

## 移行ドキュメント
- [個別ページ索引](./creative-tabs/README.md) - 5個別ページの一覧
- [移行ガイド](./creative-tabs/migration-guide.md) - CreativeTabs→CreativeModeTab移行
- [テンプレ](./creative-tabs/_template.md)

ソース:
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMT.java:1`
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMaterial.java:1`
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMTFood.java:1`
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMTContainer.java:1`
- `src/main/java/mods/defeatedcrow/common/CreativeTabAMTMagic.java:1`
