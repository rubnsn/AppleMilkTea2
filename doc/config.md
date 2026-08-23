# Config 一覧

> クラス: `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:1`  
> カクテル設定: `src/main/java/mods/defeatedcrow/common/config/DCsConfigCocktail.java:1`  
> ASM設定: `src/main/java/mods/defeatedcrow/asm/config/DCsConfiguration.java:1`  
> ファイル: `config/DCsAppleMilk.cfg` / `config/DCsAppleMilk-cocktail.cfg`  
> 個別ページ: [`doc/config/`](./config/README.md) に 27件の個別ページを生成  
> 移行ガイド: [`doc/config/migration-guide.md`](./config/migration-guide.md) - Configuration→ForgeConfigSpec移行

## 主なカテゴリ

### Potion ID（0-127）
| キー | 説明 |
|---|---|
| `potionIDImmunity` | Immunization |
| `potionIDPrvExplode` | 爆発耐性 |
| `potionIDPrvProjectile` | 飛び道具耐性 |
| `potionIDReflex` | 反射 |
| `potionIDAbsEXP` | 経験値吸収 |
| `potionIDAbsHeal` | 回復吸収 |
| `potionIDSuffocation` | 窒息 |
| `potionIDPrvSuffocation` | 窒息耐性 |
| `potionIDHallucinations` | 幻覚 |
| `potionIDConfinement` | 束縛 |

### Entity ID（0=自動）
| キー | Entity |
|---|---|
| `entityIdMelon`, `entityIdSilkMelon`, `entityIdKinoko`, `entityIdStun`, `entityIdIllusion`, `entityIdMissile`, `entityIdBullet` | 投擲/魔法 |
| `entityIdIce`, `entityIdSteak`, `entityIdAlcohol`, `entityIdCocktail`, `entityIdCocktail2`, `entityIdBowl`, `entityIdBowlJP`, `entityIdCup`, `entityIdCup2`, `entityIdTart`, `entityIdSandwich`, `entityIdCocktailSP`, `entityIdBaseSoup` | Placeable |

### WorldGen
| キー | デフォルト | 説明 |
|---|---|---|
| `notGenTeaTree` | false | trueで茶の木生成無効 |
| `teaTreeGenValue` | 1-20 | 生成頻度（クランプ済み） |
| `disableClam` | false | trueでハマグリ生成無効 |

### Villager
| キー | 説明 |
|---|---|
| `villagerRecipeID` | Cafe村人ID |
| `villagerRecipe2ID` | Yome村人ID |

### その他
| キー | 説明 |
|---|---|
| `debugPass` | デバッグモード合言葉（Util.checkDebugModePass） |
| `altModRecipe` | 他MOD連携レシピの代替有無 |

## Cocktail Config
`DCsConfigCocktail` でカクテルのポーション効果や材料設定を管理。`DCsAppleMilk-cocktail.cfg` に保存。

## 移行ドキュメント
- [個別ページ索引](./config/README.md) - 27個別ページの一覧
- [移行ガイド](./config/migration-guide.md) - Configuration→ForgeConfigSpec
- [テンプレ](./config/_template.md)

## 関連
- `DCsAppleMilk.preInit` : `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:476-489` で config 読み込み
- `Potion` 登録は IDが空きかつ <128 のときのみ成功
- [WorldGen](./worldgen.md) / [Entity](./entities.md) / [Potion](./potions.md) - Config参照
