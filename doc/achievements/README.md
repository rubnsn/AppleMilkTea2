# Achievement 個別ページ索引

> 総数: **37 Achievement** (`AchievementRegister.java:52` `DCachievementsList`) + `AchievementPage` `Apple&Milk&Tea!`
> 登録元: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:56` `register()`
> 本ディレクトリは 1.7.10→1.12.2 移行向けの個別ページ集。

## カテゴリ別一覧

### 製作系 (14)
| 名前 | ID | 親 | 個別ページ |
|---|---|---|---|
| `craftTeaMaker` | `defeatedcrow.teamaker` | `makeTeaLeaves` | [→](./craftTeaMaker.md) |
| `craftPan` | `defeatedcrow.pan` | `buildFurnace` | [→](./craftPan.md) |
| `craftIceMaker` | `defeatedcrow.icemaker` | `acquireIron` | [→](./craftIceMaker.md) |
| `craftTeppan` | `defeatedcrow.teppan` | `acquireIron` | [→](./craftTeppan.md) |
| `craftAutoMaker` | `defeatedcrow.craftProsessor` | `craftGrater` | [→](./craftAutoMaker.md) |
| `craftLogBox` | `defeatedcrow.logbox` | `buildWorkBench` | [→](./craftLogBox.md) |
| `craftGrater` | `defeatedcrow.grater` | `craftPan` | [→](./craftGrater.md) |
| `craftChalcedony` | `defeatedcrow.chalcedony` | `buildFurnace` | [→](./craftChalcedony.md) |
| `craftChalGear` | `defeatedcrow.chalGear` | `craftChalcedony` | [→](./craftChalGear.md) |
| `craftGlassLamp` | `defeatedcrow.glasslamp` | `craftChalcedony` | [→](./craftGlassLamp.md) |
| `craftCharcoalContainer` | `defeatedcrow.charcoalcontainer` | `craftLogBox` | [→](./craftCharcoalContainer.md) |
| `craftVegiBag` | `defeatedcrow.vegibag` | `craftLogBox` | [→](./craftVegiBag.md) |
| `craftYuzuBattery` | `defeatedcrow.yuzuBattery` | `getYuzu` | [→](./craftYuzuBattery.md) |
| `craftChargeableBat` | `defeatedcrow.chargeableBattery` | `craftYuzuBattery` | [→](./craftChargeableBat.md) |
| `craftBarrel` | `defeatedcrow.craftBarrel` | `buildWorkBench` | [→](./craftBarrel.md) |
| `craftEvp` | `defeatedcrow.evaporator` | `getAlcohol` | [→](./craftEvp.md) |
| `craftJaw` | `defeatedcrow.craftJaw` | `craftChalGear` | [→](./craftJaw.md) |
| `craftTart` | `defeatedcrow.craftTart` | `openInventory` | [→](./craftTart.md) |

### 取得・行為系 (15)
| 名前 | ID | 親 | 特殊 | 個別ページ |
|---|---|---|---|---|
| `getTeaLeaves` | `defeatedcrow.getTeaLeaves` | `openInventory` |  | [→](./getTeaLeaves.md) |
| `makeTeaLeaves` | `defeatedcrow.makeTealeaves` | `getTeaLeaves` |  | [→](./makeTeaLeaves.md) |
| `getTea` | `defeatedcrow.getTea` | `craftTeaMaker` |  | [→](./getTea.md) |
| `getAppleMilkTea` | `defeatedcrow.applemilktea` | `getTea` | ● | [→](./getAppleMilkTea.md) |
| `getSoup` | `defeatedcrow.getSoup` | `makeRice` |  | [→](./getSoup.md) |
| `getHamaguri` | `defeatedcrow.hamaguri` | `craftTeppan` |  | [→](./getHamaguri.md) |
| `eatChocoGift` | `defeatedcrow.chocolateGift` | `makeRice` | ● | [→](./eatChocoGift.md) |
| `eatIcecream` | `defeatedcrow.icecream` | `craftIceMaker` | ● | [→](./eatIcecream.md) |
| `crashMelon` | `defeatedcrow.crashMelon` | `craftVegiBag` | ● | [→](./crashMelon.md) |
| `drinkCocktail` | `defeatedcrow.drinkCocktail` | `getAlcohol` | ● | [→](./drinkCocktail.md) |
| `burnOnTeppan` | `defeatedcrow.teppanFire` | `craftTeppan` |  | [→](./burnOnTeppan.md) |
| `makeRice` | `defeatedcrow.rice` | `craftGrater` |  | [→](./makeRice.md) |
| `getPrincess` | `defeatedcrow.getPrincess` | `openInventory` |  | [→](./getPrincess.md) |
| `craftCharm` | `defeatedcrow.craftCharm` | `getPrincess` | ● | [→](./craftCharm.md) |
| `useIncense` | `defeatedcrow.useIncense` | `craftCharm` | ● | [→](./useIncense.md) |
| `useSilkMelon` | `defeatedcrow.useSilkMelon` | `crashMelon` | ● | [→](./useSilkMelon.md) |
| `getAlcohol` | `defeatedcrow.getAlcohol` | `craftBarrel` |  | [→](./getAlcohol.md) |
| `getYuzu` | `defeatedcrow.getYuzu` | `openInventory` |  | [→](./getYuzu.md) |

---

## 生成元
- テンプレ: [`_template.md`](./_template.md)
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`achievements.md`](../achievements.md)
