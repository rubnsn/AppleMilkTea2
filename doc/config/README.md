# Config 個別ページ索引

> Source: `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:1` + `DCsConfigCocktail.java:1` + `asm/config/DCsConfiguration.java:1`
> File: `config/DCsAppleMilk.cfg` / `config/DCsAppleMilk-cocktail.cfg`
> 本ディレクトリは 1.7.10→1.12.2 移行向けの Config 詳細。移行後は TOML + `ModConfig` に変更。

## カテゴリ別一覧

### Potion ID (10) - `potionID` カテゴリ (0-127 EndlessIDs)
| キー | フィールド | デフォルト | 個別ページ | 説明 |
|---|---|---|---|---|
| `Immunization` | `potionIDImmunity` | 60 | [→](./potionIDImmunity.md) | 免疫 |
| `Protection:Projectile` | `potionIDPrvProjectile` | 62 | [→](./potionIDPrvProjectile.md) | 飛び道具耐性 |
| `Protection:Explode` | `potionIDPrvExplode` | 61 | [→](./potionIDPrvExplode.md) | 爆発耐性 |
| `Reflex` | `potionIDReflex` | 63 | [→](./potionIDReflex.md) | 反射 |
| `EXPAbsorption` | `potionIDAbsEXP` | 64 | [→](./potionIDAbsEXP.md) | 経験値吸収 |
| `DamageAbsorption` | `potionIDAbsHeal` | 65 | [→](./potionIDAbsHeal.md) | 回復吸収 |
| `Suffocation` | `potionIDSuffocation` | 66 | [→](./potionIDSuffocation.md) | 窒息 |
| `Protection:Suffocation` | `potionIDPrvSuffocation` | 67 | [→](./potionIDPrvSuffocation.md) | 窒息耐性 |
| `Protection:Hallucination` | `potionIDHallucinations` | 68 | [→](./potionIDHallucinations.md) | 幻覚 |
| `Protection:Confinement` | `potionIDConfinement` | 69 | [→](./potionIDConfinement.md) | 束縛 |

### Entity ID (20) - `entityid` カテゴリ (0=auto)
| キー | フィールド | 個別ページ | 説明 |
|---|---|---|---|
| `EntityIDCompressedMelon` | `entityIdMelon` | [→](./entityIdMelon.md) | スイカ爆弾 |
| `EntityIDSilkMelon` | `entityIdSilkMelon` | [→](./entityIdSilkMelon.md) | シルキーメロン |
| `EntityIDKinoko` | `entityIdKinoko` | [→](./entityIdKinoko.md) | キノコ箱 |
| `EntityIDStun` | `entityIdStun` | [→](./entityIdStun.md) | スタン |
| `EntityIDIllusion` | `entityIdIllusion` | [→](./entityIdIllusion.md) | 幻覚 |
| `EntityIDAnchorMissile` | `entityIdMissile` | [→](./entityIdMissile.md) | ミサイル |
| `EntityIDYuzuBullet` | `entityIdBullet` | [→](./entityIdBullet.md) | 柚子弾 |
| `EntityIDIceCream` | `entityIdIce` | [→](./entityIdIce.md) | アイス |
| `EntityIDCup` / `Cup2` / `Bowl` / `JPBowl` / `Steak` / `Cocktail` / `AlcoholCup` / `Sandwich` / `Tart` / `Cocktail2` / `CocktailSP` / `BaseSoup` | `entityId*` | [→](./entityIdPlaceable.md) | Placeable 13種 |

### WorldGen (4) - `world setting` カテゴリ
| キー | フィールド | デフォルト | 個別ページ | 説明 |
|---|---|---|---|---|
| `Tea Tree Gen Probability` | `teaTreeGenValue` | 5 (1-20) | [→](./teaTreeGenValue.md) | 茶の木頻度 |
| `Clam Gen Probability` | `clamChanceValue` | 5 (1-12) | [→](./clamChanceValue.md) | ハマグリ確率 |
| `No Gen TeaTree` | `notGenTeaTree` | false | [→](./notGenTeaTree.md) | 茶の木無効 |
| `Disable Clams` | `disableClam` | false | [→](./disableClam.md) | ハマグリ無効 |

### Villager (2) - `entity setting` カテゴリ
| キー | フィールド | 個別ページ | 説明 |
|---|---|---|---|
| `New Villager ID` | `villagerRecipeID` | [→](./villagerRecipeID.md) | Cafe村人ID |
| (自動+1) | `villagerRecipe2ID` | [→](./villagerRecipe2ID.md) | Yome村人ID |

### Game Difficulty (5) - `difficulty setting` カテゴリ
| キー | フィールド | デフォルト | 個別ページ | 説明 |
|---|---|---|---|---|
| `JawCrusher Dust Gen` | `dustDif` | 1 (0-3 sweet/normal/bitter/hard) | [→](./dustDif.md) | 粉砕結果難易度 |
| `Battery Charge Gen` | `chargeDif` | 1 (0-3) | [→](./chargeDif.md) | 電池生成難易度 |
| `Exchange Rate of Charge` | `exchangeDif` | 2 (0-4) | [→](./exchangeDif.md) | エネルギー交換レート |
| `Another Mod Recipe` | `altModRecipe` | true | [→](./altModRecipe.md) | 他MOD連携レシピ |
| `JawCrusher Recipe Difficulty` | `procDif` | 1 (0-2) | [→](./procDif.md) | レシピ階層難易度 |

### Game Setting (多数) - `setting` / `render setting` / `entity setting` / `debug setting`
| キー | フィールド | 個別ページ | 説明 |
|---|---|---|---|
| `Set JPbowl Texture` | `setCupTexture` | [→](./setCupTexture.md) | 碗テクスチャ 1-3 |
| `Set Texture Type Number` | `setAltTexturePass` | [→](./setAltTexturePass.md) | x16/x32 |
| `Enable Alt Teppan Texture` | `useAltTeppanTex` | [→](./useAltTeppanTex.md) | 網グリル |
| `Teppann Hard Mode` / `Ready Time` / `Randomly Cooking Time` | `teppann*` | [→](./teppan.md) | 鉄板難易度 |
| `Cups Stack Size` | `cupStackSize` | [→](./cupStackSize.md) | カップ1/3/8 |
| `Battery Update Cycle` | `batteryUpdate` | [→](./batteryUpdate.md) | 電池tick |
| `Charm Warp Key Number` | `charmWarpKey` | [→](./charmWarpKey.md) | チャームワープキー |
| `Set Drink Entity Scale` | `setCupScale` | [→](./setCupScale.md) | Entity縮尺 |
| `Debug Mode Pass` | `debugPass` | [→](./debugPass.md) | デバッグ合言葉 |
| `Use Extra Recipe` | `useEXRecipe` | [→](./useEXRecipe.md) | 茶苗追加レシピ |
| `Allow Slimeball OreDic` | `allowSlimeBallDic` | [→](./allowSlimeBallDic.md) | スライム辞書 |
| `Not Render Foods Steam` | `noRenderFoodsSteam` | [→](./noRenderFoodsSteam.md) | 湯気無効 |
| `Disable Fierstarter` | `disableFireSteater` | [→](./disableFireSteater.md) | 着火具無効 |
| `No Weathering Container` | `noWetGContainer` | [→](./noWetGContainer.md) | 風化無効 |
| `Use Summer Rendering` | `useSummerRender` | [→](./useSummerRender.md) | 夏レンダリング |
| `Enable Explode Melon` / `Melon not Break Block` / `Silky Melon of Fear` / `Complete Fear` | `canExplodeMelon` 等 | [→](./melonConfig.md) | 爆発制御 |
| `Safety Chocolate Gift` | `safetyChocolate` | [→](./safetyChocolate.md) | チョコ爆発無効 |
| `Allow Infinity Wipes` | `allowInfinityWipes` | [→](./allowInfinityWipes.md) | 無限紙箱 |
| `Allow Clam Fertilizer` | `bonemealClam` | [→](./bonemealClam.md) | 骨粉ハマグリ |
| `Enable Edible Entity` | `allowEdibleEntities` | [→](./allowEdibleEntities.md) | 食物Entity化 |
| `Hard Mode Wind Charm` | `charmRemain` | [→](./charmRemain.md) | チャーム回数制限 |
| `Enable Mob Drop Container` | `enableMobBlock` | [→](./enableMobBlock.md) | Mob圧縮 |
| `Hard Mode Leather Recipe` | `hardLeatherRecipe` | [→](./hardLeatherRecipe.md) | 皮レシピ |
| `Dsiable Missile Explosion` | `disableMissileExplosion` | [→](./disableMissileExplosion.md) | ミサイル爆発無効 |
| `PvP Prohibition Mode` | `PvPProhibitionMode` | [→](./PvPProhibitionMode.md) | PvP無効 |
| `Yuzu Crop Burn In Device` | `yuzuCropBurn` | [→](./yuzuCropBurn.md) | 柚子燃焼 |
| `Enable Mob Drop Container` 等他 | 各種 | [→](./otherSettings.md) | その他 |

### Cocktail Config
`DCsConfigCocktail.java:1` (`DCsAppleMilk-cocktail.cfg`) - カクテルポーション効果・材料設定

### ASM Config
`DCsConfiguration.java:1` (`asm/config`)

---

## 生成元
- テンプレ: Config 個別ページは `_template.md` (本READMEが索引)
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`config.md`](../config.md)
