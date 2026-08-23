# Achievement 一覧

> 定義元: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> 登録元: `new AchievementRegister().register()` at `DCsAppleMilk.java:xxx` (init)
> 総数: **37 Achievement** (AchievementPage `Apple&Milk&Tea!`)
> 言語: `assets/defeatedcrow/lang/*.lang` (`achievement.dc.*`)

## 概要
1.7.10 `net.minecraft.stats.Achievement` + `AchievementPage` で実装。独立/依存の2層ツリーを `AchievementList.openInventory` / `buildWorkBench` / `buildFurnace` / `acquireIron` から分岐。特殊 (`setSpecial()`) は光沢枠。

全Achievementは `AchievementRegister.DCachievementsList` に集約し `AchievementPage.registerAchievementPage(DCachievementPage)` で登録。

## 一覧

### 独立 (InitiIndependentStat) - 8
親なしで最初から表示される起点。

| 名前 | ID (stat/name) | 表示位置 | アイコン | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `getTeaLeaves` | `defeatedcrow.getTeaLeaves` / `dc.getTeaLeaves` | 0,1 | `leafTea:0` | [→](./achievements/getTeaLeaves.md) | 茶葉取得 |
| `makeTeaLeaves` | `defeatedcrow.makeTealeaves` / `dc.makeTeaLeaves` | -2,0 | `foodTea:0` | [→](./achievements/makeTeaLeaves.md) | 製茶 |
| `getPrincess` | `defeatedcrow.getPrincess` / `dc.getPrincess` | 5,4 | `princessClam:0` | [→](./achievements/getPrincess.md) | 姫ハマグリ |
| `getYuzu` | `defeatedcrow.getYuzu` / `dc.getYuzuCrop` | 0,-1 | `leafTea:3` | [→](./achievements/getYuzu.md) | 柚子取得 |
| `craftBarrel` | `defeatedcrow.craftBarrel` / `dc.craftBarrel` | 2,0 | `barrel` | [→](./achievements/craftBarrel.md) | 樽 |
| `craftTart` | `defeatedcrow.craftTart` / `dc.craftTart` | -5,-3 | `appleTart` | [→](./achievements/craftTart.md) | タルト |

> 実際は `getTeaLeaves`, `getPrincess`, `getYuzu`, `craftBarrel`, `craftTart` の5が完全独立。`makeTeaLeaves`, `craftTeaMaker` 等も独立だがコード上は `initIndependentStat` 付与後に `registerStat()` なので表示は初期から。

### クラフト系 (12)

| 名前 | ID | 親 | 個別ページ | 説明 |
|---|---|---|---|---|
| `craftTeaMaker` | `defeatedcrow.teamaker` | `makeTeaLeaves` | [→](./achievements/craftTeaMaker.md) | ティーメーカー |
| `craftPan` | `defeatedcrow.pan` | `buildFurnace` | [→](./achievements/craftPan.md) | 鍋 |
| `craftTeppan` | `defeatedcrow.teppan` | `acquireIron` | [→](./achievements/craftTeppan.md) | 鉄板 |
| `craftIceMaker` | `defeatedcrow.icemaker` | `acquireIron` | [→](./achievements/craftIceMaker.md) | アイスメーカー |
| `craftChalcedony` | `defeatedcrow.chalcedony` | `buildFurnace` | [→](./achievements/craftChalcedony.md) | 玉髄 |
| `craftLogBox` | `defeatedcrow.logbox` | `buildWorkBench` | [→](./achievements/craftLogBox.md) | 木箱 |
| `craftGrater` | `defeatedcrow.grater` | `craftPan` | [→](./achievements/craftGrater.md) | おろし金 |
| `craftChalGear` | `defeatedcrow.chalGear` | `craftChalcedony` | [→](./achievements/craftChalGear.md) | ギア |
| `craftGlassLamp` | `defeatedcrow.glasslamp` | `craftChalcedony` | [→](./achievements/craftGlassLamp.md) | ランプ (特殊) |
| `craftAutoMaker` | `defeatedcrow.craftProsessor` | `craftGrater` | [→](./achievements/craftAutoMaker.md) | プロセッサー |
| `craftYuzuBattery` | `defeatedcrow.yuzuBattery` | `getYuzu` | [→](./achievements/craftYuzuBattery.md) | 柚子電池 |
| `craftChargeableBat` | `defeatedcrow.chargeableBattery` | `craftYuzuBattery` | [→](./achievements/craftChargeableBat.md) | 充電電池 |
| `craftEvp` | `defeatedcrow.evaporator` | `getAlcohol` | [→](./achievements/craftEvp.md) | エバポレーター |
| `craftJaw` | `defeatedcrow.craftJaw` | `craftChalGear` | [→](./achievements/craftJaw.md) | Jaw |
| `craftVegiBag` | `defeatedcrow.vegibag` | `craftLogBox` | [→](./achievements/craftVegiBag.md) | 野菜袋 |
| `craftCharcoalContainer` | `defeatedcrow.charcoalcontainer` | `craftLogBox` | [→](./achievements/craftCharcoalContainer.md) | 木炭箱 |

### 行為・取得系 (15)

| 名前 | ID | 親 | 個別ページ | 特殊 | 説明 |
|---|---|---|---|---|---|
| `getTea` | `defeatedcrow.getTea` | `craftTeaMaker` | [→](./achievements/getTea.md) |  | 緑茶 |
| `getAppleMilkTea` | `defeatedcrow.applemilktea` | `getTea` | [→](./achievements/getAppleMilkTea.md) | ● | アップルミルクティー |
| `getSoup` | `defeatedcrow.getSoup` | `makeRice` | [→](./achievements/getSoup.md) |  | スープ |
| `getHamaguri` | `defeatedcrow.hamaguri` | `craftTeppan` | [→](./achievements/getHamaguri.md) |  | ハマグリ焼き |
| `eatChocoGift` | `defeatedcrow.chocolateGift` | `makeRice` | [→](./achievements/eatChocoGift.md) | ● | チョコギフト |
| `eatIcecream` | `defeatedcrow.icecream` | `craftIceMaker` | [→](./achievements/eatIcecream.md) | ● | アイス |
| `crashMelon` | `defeatedcrow.crashMelon` | `craftVegiBag` | [→](./achievements/crashMelon.md) | ● | スイカ爆発 |
| `useSilkMelon` | `defeatedcrow.useSilkMelon` | `crashMelon` | [→](./achievements/useSilkMelon.md) | ● | シルキー |
| `drinkCocktail` | `defeatedcrow.drinkCocktail` | `getAlcohol` | [→](./achievements/drinkCocktail.md) | ● | カクテル |
| `getAlcohol` | `defeatedcrow.getAlcohol` | `craftBarrel` | [→](./achievements/getAlcohol.md) |  | 酒 |
| `burnOnTeppan` | `defeatedcrow.teppanFire` | `craftTeppan` | [→](./achievements/burnOnTeppan.md) |  | 鉄板炎上 |
| `makeRice` | `defeatedcrow.rice` | `craftGrater` | [→](./achievements/makeRice.md) |  | 米 |
| `craftCharm` | `defeatedcrow.craftCharm` | `getPrincess` | [→](./achievements/craftCharm.md) | ● | チャーム |
| `useIncense` | `defeatedcrow.useIncense` | `craftCharm` | [→](./achievements/useIncense.md) | ● | お香 |
| `getAlcohol` | (再掲) |  |  |  |  |

> 計 37 (リスト長 `DCachievementsList` は 35+2 独立で 37)。

## 付与タイミング

| Achievement | 付与箇所 | コード |
|---|---|---|
| `craft*` 系 | `CraftingEvent.onCraftingEvent` | `player.triggerAchievement(AchievementRegister.craftTeaMaker)` (`src/main/java/mods/defeatedcrow/event/CraftingEvent.java:1`) |
| `get*` / `eat*` 系 | 各 Tile/Item の `onBlockActivated` / `onFoodEaten` / `onUpdate` | `player.triggerAchievement(...)` を各所で呼出 |
| `crashMelon` | `EntityMelonBomb.onUpdate` 爆発時 | `AchievementRegister.crashMelon` |
| `useIncense` | `TileIncenseBase.updateEntity` | `useIncense` |
| `craftTart` etc | `ItemAppleTart.onItemUse` 等 | |

詳細は `grep -r "triggerAchievement" src/` で全16箇所を列挙。

## 表示位置マップ (grid)
```
y-6:           getAppleMilkTea(-4,-6)
y-5:                 craftChargeableBat(0,-5)
y-4:     getTea(-3,-4) craftTart(-5,-3) eatIcecream(3,-4) drinkCocktail(7,-3)
y-3:            craftYuzuBattery(0,-3) useSilkMelon(-1,7?) 実際は y7
y-2: craftTeaMaker(-2,-2) craftIceMaker(2,-2)
y-1: eatChocoGift(-9,-1) getYuzu(0,-1) craftEvp(6,-1)
 0: makeTeaLeaves(-2,0) craftBarrel(2,0) craftAutoMaker(-4,0) getAlcohol(4,0)
 1: getTeaLeaves(0,1) makeRice(-6,1) getSoup(-8,1) craftJaw(6,1)
 2: craftPan(-2,2) craftGrater(-4,2) craftChalcedony(2,2) craftChalGear(4,2)
 3: craftLogBox(0,3) 
 4: craftTeppan(-5,4) getHamaguri(-7,4) craftGlassLamp(3,4) getPrincess(5,4) useIncense(9,4) craftVegiBag(1,5?) 実際 y5
```

## 移行ドキュメント
- [個別ページ索引](./achievements/README.md) - 37個別ページへのリンク
- [移行ガイド](./achievements/migration-guide.md) - Advancement 移行

## 関連
- [概要](./overview.md)
- [Event 一覧](./events.md) - `CraftingEvent`
