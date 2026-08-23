# Build / Gradle 移行ガイド - 1.7.10 GTNH → 1.20.1 Forge 47.x (ForgeGradle 6)

> 最終更新: 2026-08-24  
> 対象: AppleMilkTea2 1.7.10 (GTNH convention) → 1.20.1 (Forge 47.x + ForgeGradle 6 + Mojang Official Mappings + JDK 17)  
> 正本: [移行プラン](../plan.md)（本docはプランのフェーズA-Cを再構成・文書化）  
> 関連: [概要](./overview.md) / [plugins/migration-guide](./plugins/migration-guide.md#1201対応分類) / 各 `migration-guide.md` の 1.20.1追記

## TL;DR

GTNH convention（`com.gtnewhorizons.gtnhconvention` + `gtnhsettingsconvention`）を完全撤去し、**Forge 47.x + ForgeGradle 6 + Mojang official mappings + JDK 17 + Gradle 8.x + `mods.toml`** の 1.20.1 標準ビルドへ刷新する。`rfg.deobf` 依存を通常依存へ置換し、ローカルjar/Bamboo・NEI/Thaum等の1.20.1非対応依存は削除（Bambooのみ保留）する。ソースの `@Mod` 変更は各カテゴリの移行ガイドが担当、本書はビルド枠組みのみ。

決議（plan.md「前提・決定事項」準拠）:
- ローダー: **Forge 47.x + ForgeGradle 6**（旧Forgeを維持、NeoForge/ModDevGradleは不採用）
- マッピング: **Mojang official (mojmap)**（FG6デフォルト、ユーザー選択）
- Java: **JDK 17**（既存JDK25/`.jdk` 依存撤去、ユーザー選択）
- 依存: **Gradle側は1.20.1対応版への依存リスト刷新まで**実施（ユーザー選択）
- Bamboo: 1.20.1版あり → `plugin/LoadBambooPlugin` は破棄せず保留し後追い検証
- FG7/Gradle 9は 1.20.1 (47.x) 非対応のため不採用、Gradleは8.x（公式MDKは8.1.1系）にダウングレード

---

## 1. 変更ファイル一覧

| ファイル | 現行 (1.7.10 GTNH) | 改修後 (1.20.1) | 備考 |
|---|---|---|---|
| `settings.gradle.kts` (削除) | `gtnhsettingsconvention 2.0.29` + GTNH Maven | `settings.gradle` (Groovy) + `pluginManagement` で `gradlePluginPortal`/`mavenCentral`/`ForgeGradle maven` のみ | GTNH設定を全除去 |
| `build.gradle.kts` (削除) | `plugins { id("com.gtnewhorizons.gtnhconvention") }` のみ | `build.gradle` (Groovy) + `net.minecraftforge.gradle` plugin + `java` + `toolchain 17` + `minecraft { mappings channel:'official', version:'1.20.1-47.3.x' }` + `repositories(forge, curse, modrinth)` + `base.archivesName` | FG6はKotlin DSL不可、Groovy推奨 |
| `gradle.properties` | `minecraftVersion=1.7.10` / `forgeVersion=10.13.4.1614` / MCP `stable 12` / `forceEnableMixins`/`coreModClass=asm.AppleMilkCorePlugin` 等GTNH 15+項目 | `minecraftVersion=1.20.1` / `forgeVersion=47.3.x` (例 `47.3.0`) + FG6用 `official` + 開発者名のみ、旧GTNH/MCP項目全削除 | `channel/mappingsVersion/remoteMappings` 撤去 |
| `gradle/wrapper/gradle-wrapper.properties` | Gradle 9.4 | **Gradle 8.x** (`distributionUrl=.../gradle-8.8-bin.zip` 等 8.1.1以上) | FG6はGradle 9非対応 |
| `gradle/gradle-daemon-jvm.properties` + `.jdk/` | JDK 25 指定 | **撤去**、ローカル JDK17 の `JAVA_HOME` でビルド | マシンにはJDK17のみが存在 |
| `dependencies.gradle` | `rfg.deobf(...)` + `curse.maven` (NEI/AppleCore/Forestry/BC/Railcraft/IC2) + ローカルjar (Bamboo, wa) | 通常 `minecraft 'net.minecraftforge:forge:1.20.1-47.3.x'` + `compileOnly fg.deobf(...)` 撤去、1.20.1対応座標へ置換、非対応は `if(file.exists())` ラップではなく**削除**。Bamboo保留 | 詳細は [plugins/migration-guide](./plugins/migration-guide.md#1201対応分類) |
| `addon.gradle` | `modVersion` (1.7.10文字列) | `modVersion = "1.20.1-xxx"` へ更新 or `build.gradle` に統合 | 任意だが統合推奨 |
| `src/main/resources/META-INF/mods.toml` (新規) | `mcmod.info` | `mods.toml` 新設 (`modId="DCsAppleMilk"`, `loaderVersion="[47,)"`, `[[mods]]` + `[[dependencies.DCsAppleMilk]]`) + `pack.mcmeta` (pack_format 15) | 1.20.1は `mcmod.info` 廃止 |
| `src/main/resources/pack.mcmeta` (新規) | 旧 `mcmod.info` 時代は不要 | `{"pack":{"pack_format":15,"description":"Apple&Milk&Tea! resources"}}` | 1.20.1は必須 |

---

## 2. フェーズA: ビルド枠組み刷新（詳細）

### settings.gradle <a id="settingsgradle"></a>

```groovy
// settings.gradle (Groovy) - 1.20.1 新設。settings.gradle.kts は削除
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = "https://maven.minecraftforge.net/" }
        // ForgeGradle 6 の maven
        maven { url = "https://maven.minecraftforge.net/" }
    }
}
plugins {
    id 'org.gradle.toolchains.foojay-resolver-convention' version '0.7.0'
}
rootProject.name = 'AppleMilkTea2'
```

- `gtnhsettingsconvention 2.0.29` を含む `pluginManagement` のGTNH Mavenブロックは全削除。
- `repositories` は `gradlePluginPortal` + `mavenCentral` + `forge maven` の最小構成。GTNH Nexusは不要（直接の `curse.maven`/`modrinth` は `build.gradle` の `repositories` で定義）。

### build.gradle <a id="buildgradle"></a>

```groovy
plugins {
    id 'eclipse'
    id 'idea'
    id 'maven-publish'
    id 'net.minecraftforge.gradle' version '6.0.+'
}

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

println "Java: ${System.getProperty('java.version')}, JVM: ${System.getProperty('java.vm.version')}, Arch: ${System.getProperty('os.arch')}"

minecraft {
    mappings channel: 'official', version: '1.20.1'
    // SRG → Mojang official。FG6デフォルト。MCP/parchmentは不使用
}

repositories {
    mavenCentral()
    maven { url = "https://maven.minecraftforge.net/" }
    maven { // Curse Maven
        url = "https://www.cursemaven.com"
        content { includeGroup "curse.maven" }
    }
    maven { // Modrinth
        url = "https://api.modrinth.com/maven"
    }
    gradlePluginPortal()
}

dependencies {
    minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'
    // 例: JEIのみ残す場合（1.20.1対応版が存在）
    // compileOnly fg.deobf("mezz.jei:jei-${mc_version}-forge-api:15.3.0.4")
    // runtimeOnly fg.deobf("mezz.jei:jei-${mc_version}-forge:15.3.0.4")
    // 非対応（Thaumcraft/NEI/CraftGuide/MCE2/Sector2/Forestry1.7系 等）は削除。Bambooは保留で将来追加
}

base {
    archivesName = 'AppleMilkTea2'
}

tasks.named('processResources', ProcessResources).configure {
    var replaceProperties = [
        minecraft_version: '1.20.1', forge_version: '47.3.0',
        mod_version: project.version,
        mod_id: 'DCsAppleMilk', mod_name: 'Apple&Milk&Tea!'
    ]
    inputs.properties replaceProperties
    filesMatching(['META-INF/mods.toml', 'pack.mcmeta']) {
        expand replaceProperties
    }
}

// run configs
tasks.named('jar', Jar).configure {
    manifest {
        attributes([
            "Specification-Title": "AppleMilkTea2",
            "Specification-Vendor": "defeatedcrow",
            "Specification-Version": "1",
            "Implementation-Title": project.name,
            "Implementation-Version": project.version,
            "Implementation-Vendor": "defeatedcrow",
            "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
        ])
    }
}
```

ポイント:
- `plugins { id("com.gtnewhorizons.gtnhconvention") }` は撤去。代わりに `net.minecraftforge.gradle` 6.x を `plugins` ブロックで直接適用。**Kotlin DSL (`build.gradle.kts`) はFG6公式MDKがGroovy前提のためGroovyへ切替**（`buildscript` 併用が煩雑になるため）。
- `java` プラグイン + `toolchain(J17)`。`sourceCompatibility`/`targetCompatibility` は `VERSION_17`。
- `minecraft { mappings channel:'official', version:'1.20.1' }`。旧 `channel = stable` / `mappingsVersion = 12` は削除。
- `repositories`: `forge`/`mavenCentral`/`gradlePluginPortal`/`curse`/`modrinth`。旧 `includeWellKnownRepositories` は不要。
- `base.archivesName = 'AppleMilkTea2'`（旧 `customArchiveBaseName` に相当）。

### gradle.properties <a id="gradleproperties"></a>

```properties
org.gradle.daemon=false
org.gradle.parallel=true
org.gradle.configuration-cache=false

# Minecraft
minecraftVersion=1.20.1
forgeVersion=47.3.0

# Mod
modId=DCsAppleMilk
modName=Apple&Milk&Tea!
modGroup=mods.defeatedcrow
modVersion=2.9m-1.20.1

# Mappings
# FG6は Mojang official 固定。旧MCPは撤去
# mappingsChannel=official
# mappingsVersion=1.20.1

# ForgeGradle
# 開発者名
author=defeatedcrow

# 旧GTNH専用プロパティは全削除:
# gtnh.settings.blowdryerTag
# gtnh.modules.gitVersion
# channel, mappingsVersion(旧MCP), remoteMappings, developmentEnvironmentUserName
# enableModernJavaSyntax, enableGenericInjection, generateGradleTokenClass, gradleTokenVersion
# accessTransformersFile, usesMixins, forceEnableMixins, coreModClass (=asm.AppleMilkCorePluginは撤去判断はソース側)
# usesShadowedDependencies, includeWellKnownRepositories, usesMavenPublishing, customArchiveBaseName 等
```

- 旧15+のGTNH/MCP固有キーを撤去。`coreModClass` はASM撤去判断（`asm/` はPotion拡張用のTransformerだが1.20.1ではEndlessIDs不要＆ASM→Mixin or 削除）。
- `modId=DCsAppleMilk` は維持（`mods.toml` の `modId` と一致必須）。
- `forgeVersion` は `47.3.x` の最新安定（例 `47.3.0` / `47.1.0` でも可。plan.mdは `47.3.x` 推奨）。

### wrapper <a id="wrapper"></a>

`gradle/wrapper/gradle-wrapper.properties`:
```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.8-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

- 現行 `Gradle 9.4` → **8.8（or 8.7/8.1.1）にダウングレード**。Forge公式MDK 1.20.1は `8.1.1`、FG6は `8.x` のみ対応、**9系非対応**。

### JDK <a id="jdk"></a>

- 現行: `.jdk/` ディレクトリ + `gradle-daemon-jvm.properties` で JDK25 指定。
- 改修: 両者を**削除または無効化**し、ローカル **JDK 17**（`JAVA_HOME`）でビルド。plan.md調査でマシンにはJDK17のみが存在し FG6/J17 に合致。
- 検証: `$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.x.y-hotspot"`（Windows例）→ `.\gradlew --version` で `JVM: 17.x.y` を確認。

### mods.toml / pack.mcmeta <a id="mods-toml"></a>

`src/main/resources/META-INF/mods.toml`（1.20.1新設、旧 `mcmod.info` は削除）:
```toml
modLoader="javafml"
loaderVersion="[47,)"
license="MMPL-1.0"
issueTrackerURL="https://github.com/defeatedcrow/AppleMilkTea2/issues"

[[mods]]
modId="DCsAppleMilk"
version="${file.jarVersion}"
displayName="Apple&Milk&Tea!"
updateJSONURL="https://raw.githubusercontent.com/defeatedcrow/AppleMilkTea2/master/update.json"
displayURL="https://github.com/defeatedcrow/AppleMilkTea2"
logoFile="defeatedcrow/logo.png"
credits="defeatedcrow, AppleMilkTea2 contributors"
authors="defeatedcrow"
description="Apple&Milk&Tea! - Large content mod for tea, cooking, brewing, chalcedony, incense, energy."

[[dependencies.DCsAppleMilk]]
modId="forge"
mandatory=true
versionRange="[47,)"
ordering="NONE"
side="BOTH"

[[dependencies.DCsAppleMilk]]
modId="minecraft"
mandatory=true
versionRange="[1.20.1,1.21)"
ordering="NONE"
side="BOTH"

# 任意連携は mods.toml で optional にはしない（ソース側 ModList.isLoaded で分岐）
```

`src/main/resources/pack.mcmeta`:
```json
{
  "pack": {
    "pack_format": 15,
    "description": "Apple&Milk&Tea! resources (1.20.1, Forge 47.x, mojmap, JDK17)"
  }
}
```

- 1.20.1の `pack_format` は 15（1.20→15, 1.20.2→18）。旧1.7.10の `mcmod.info` は JSON配列形式だったが 1.20.1ではTOMLが必須。
- `modId` は `DCsAppleMilk`（大文字含む）を維持。Forge 47は大文字modIdも許容だが小文字 `dcsapplemilk` へのリネームはソース影響が大きいため据置。

---

## 3. フェーズB: 依存の刷新 <a id="dependencies"></a>

### 現行 `dependencies.gradle` の問題

```groovy
// 1.7.10 (現行)
rfg.deobf("curse.maven:nei-432559:2946452") // NEI 1.7.10
rfg.deobf("curse.maven:codechicken-core-...") // CCC
rfg.deobf("curse.maven:applecore-...") 
rfg.deobf("curse.maven:forestry-...") // 1.7系
rfg.deobf("thaumcraft:Thaumcraft:1.7.10-4.2.3.5")
if(file('../Bamboo/target/classes').exists()) compileOnly files('../Bamboo/...')
```

- `rfg` (RetroFuturaGradle, GTNHのdeobfラッパー) は FG6非対応。`fg.deobf`（FG6）か `minecraft` 依存とは別機構。
- 近年のForge 1.20.1では `fg.deobf` は `compileOnly` / `runtimeOnly` の修飾子として使う（依存自体の難読化解決はFG6が自動）。

### 1.20.1 置換方針

| 現行依存 | 1.20.1対応有無 | 改修後 |
|---|---|---|
| `minecraft` + `forge` | あり | `minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'` |
| NEI / CodeChickenCore / CodeChickenLib | **なし** (1.7専用) | **削除** → JEI (`mezz.jei:jei-1.20.1-forge:15.x`) に置換 or 削除（表示のみなら省略可） |
| Thaumcraft (4→6 1.12止まり) | **なし** | **削除** (`plugin/LoadThaumcraftPlugin` 削除) |
| AppleCore | **なし** (挙動はForge内包) | **削除** (`LoadAppleCorePlugin` の `func_151686_a` 削除) |
| MCEconomy2 / SextiarySector2 | **なし** | **削除** (`plugin/mce`, `SSector` 削除) |
| CraftGuide | **なし** | **削除** (`craftguide` 削除) |
| PPC/Tofu/Gummi/Growthcraft/MapleTree/SugiForest/DartCraft/ExtraTrees/EnchantChanger/ExBucket/Wa | **なし/入手困難** | **大半削除**。`LoadModHandler` の文字列lookupはフェッチ不可になるが `ModList.isLoaded` で安全スキップ |
| **Bamboo** | **あり** (1.20.1版あり) | **保留** (`LoadBambooPlugin` 破棄せず、API差分 `CookingRegistory`/`GrindRegistory` 等を後追い検証) |
| IC2 (industrialcraft-2) | **あり** | 残す候補: `compileOnly fg.deobf("net.industrial-craft:industrialcraft-2:2.8.xx")`（最新版要確認、EU API `BasicSink` 等は継続） |
| Forestry | 半休眠だが **あり** | 要検証、候補: `curse.maven:forestry-...` の1.20 forge版 |
| BuildCraft | **あり** (コミュニティ維持) | 置換候補（燃料登録のみで影響小） |
| Railcraft | 1.7終了後 **Reborn系後継** | 要検証/オミット寄り |
| Biomes O' Plenty | **あり** | 置換候補: `curse.maven:biomes-o-plenty-220318:xxxx` |
| Thermal Series (TE4後継) | **あり** | 要検証、IMCのみでクラス参照なしのため影響小 |
| CoFH RF (`cofh.api.energy.IEnergyHandler`) | **なし** (ForgeEnergyへ移行) | **削除**、RFは `CapabilityEnergy(IEnergyStorage)` へ全面書換。中枢3ファイル `MachineBase`/`TileChargerDevice`/`TileHandleEngine` |
| JEI | **あり** (現役) | 置換先: `mezz.jei:jei-1.20.1-common-api:15.3.x` + `jei-1.20.1-forge:15.3.x`（必須ではないがレシピ表示連携なら推奨） |

対応の詳細な分類は [plugins/migration-guide.md#1201対応分類](./plugins/migration-guide.md#1201対応分類) を正本とする。**ソース側は全連携が `postInit` の `Loader.isModLoaded` で条件分岐しておりハード依存ではないため、Gradleからは非対応を単に削除すればビルドが通る**（実行時に `ClassNotFound` を `try/catch` で抑止している）。

置換例:
```groovy
dependencies {
    minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'
    // JEI (任意, 1.20.1)
    compileOnly fg.deobf("mezz.jei:jei-1.20.1-common-api:15.3.0.4")
    runtimeOnly fg.deobf("mezz.jei:jei-1.20.1-forge:15.3.0.4")
    // IC2 (任意, 要検証)
    // compileOnly fg.deobf("net.industrial-craft:industrialcraft-2:2.8.222-ex112")
    // Biomes O' Plenty (任意)
    // compileOnly fg.deobf("curse.maven:biomes-o-plenty-220318:5806419")
    // Bamboo (保留: ローカルjarは除去、将来的に1.20.1座標が判明したら追加)
}
```

ローカルjar（`Bamboo.jar`/`wa.jar`）は `dependencies.gradle` の `file.exists()` ラップではなく**除去**。存在しないjarを参照し続けるとCIで失敗する。

### addon.gradle

`modVersion` を `1.20.1-xxx` に更新するか `build.gradle` に統合。`addon.gradle` 自体を残す場合:
```groovy
ext.modVersion = "1.20.1-2.9m"
version = modVersion
```

---

## 4. フェーズC: 検証 <a id="verification"></a>

### 検証手順（plan.md「Verification」準拠）

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot"
.\gradlew build          # BUILD SUCCESSFUL が正
.\gradlew genIntellijRuns  # または genEclipseRuns / genVSCodeRuns
.\gradlew runClient       # 1.20.1 クライアントがタイトル画面まで到達
```

| 検証項目 | 期待結果 | 失敗時の診断 |
|---|---|---|
| `gradlew build` | `BUILD SUCCESSFUL` | `settings.gradle.kts` 残存→削除、`Gradle 9` エラー→ `8.x` にする、`mappings channel` エラー→ `official/1.20.1` に |
| `genIntellijRuns` 等 | `runClient`/`runServer`/`runData` タスク生成成功 | `mods.toml` 不備→ `loaderVersion`/`modId` 確認、`pack.mcmeta` 不備→ `pack_format:15` 確認 |
| `runClient` | 1.20.1 Forgeタイトル画面 도달、クラッシュせず | `mods.toml` の `modId` 大小不一致、`coreModClass` 残存（ASMは1.20.1非対応） |
| 生成jar | `build/libs/AppleMilkTea2-*.jar` が Forge47環境でロード可能 | `META-INF/mods.toml` がjar内に含まれるか `jar tf` で確認 |

### 生成物の検査

```powershell
jar tf build/libs/AppleMilkTea2-1.20.1-2.9m.jar | Select-String "mods.toml|pack.mcmeta|defeatedcrow"
```

- `META-INF/mods.toml` が含まれること（`mcmod.info` は含まれないこと）。
- `pack.mcmeta` がルートに含まれること。
- `mods.toml` の `modId=DCsAppleMilk` が `mods.defeatedcrow.common.DCsAppleMilk` の `@Mod` と一致すること。

---

## 5. 補足: DSL・Gradleバージョン・後続作業

### ビルドDSL

- FG6公式MDKは `build.gradle` (Groovy) 前提。Kotlin DSL (`build.gradle.kts`) + `buildscript` 併用は煩雑で本計画は **Groovy切替を推奨**。
- `settings.gradle.kts` → `settings.gradle` のリネームを忘れると `gtnhsettingsconvention` の解決で失敗する。

### ForgeGradle

- **FG6は非推奨寄りだが1.20.1の豊富なMod資産互換で選択**。FG7/Gradle9は 1.20.1(47.x)非対応。
- `minecraft { mappings channel:'official', version:'1.20.1' }` はFG6デフォルトで mojmap。MCP/`parchment` は任意だが本移行は `official` に統一。

### 難読化解除済み公式ソースの場所 (mojmap) <a id="deobf-sources"></a>

> `mappings channel:'official'` は Mojang が配布する ProGuard マッピングを FG6 が自動適用した **難読化解除済み (deobfuscated) 公式ソース**。旧MCP `stable 12` の `func_149663_c` ではなく `BlockBehaviour.Properties` / `VoxelShape` 等の正式名で読める。

**1. 本命 — Gradle キャッシュの `-sources.jar` (IDEで直接読むファイル)**

`minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'` を初回 `build`/`genIntellijRuns` すると FG6 が Mojang 公式マッピングを適用した sources を生成・キャッシュする:

```text
%USERPROFILE%\.gradle\caches\forge_gradle\minecraft_user_repo\net\minecraftforge\forge\1.20.1-47.3.0_mapped_official_1.20.1\
  forge-1.20.1-47.3.0_mapped_official_1.20.1-sources.jar  (7.2 MB, 本命)
  forge-1.20.1-47.3.0_mapped_official_1.20.1.jar          (18.4 MB, バイナリ)
  forge-1.20.1-47.3.0_mapped_official_1.20.1-recomp.jar   (18.3 MB, 再コンパイル用)
```

*中身の検証*:
```powershell
jar tf $env:USERPROFILE\.gradle\caches\forge_gradle\minecraft_user_repo\net\minecraftforge\forge\1.20.1-47.3.0_mapped_official_1.20.1\forge-1.20.1-47.3.0_mapped_official_1.20.1-sources.jar | Select-String "net/minecraft/world/level/block/Block\.java"
# → net/minecraft/world/level/block/Block.java
# → net/minecraft/world/level/block/Blocks.java  (MCPの func_149663_c ではなく公式名)
```

Windows 例: `C:\Users\white\.gradle\caches\...` / macOS/Linux 例: `~/.gradle/caches/...`。Forge バージョン (47.3.0) やマッピング (`_mapped_official_1.20.1`) が変わるとディレクトリ名も変わる。

**2. 補助 — Mojang 配布の生マッピング (FGが内部で使用)**

```text
%USERPROFILE%\.gradle\caches\forge_gradle\minecraft_repo\versions\1.20.1\
  client_mappings.txt  (8.0 MB, `com.mojang.blaze3d.Blaze3D -> ega:` 形式)
  server_mappings.txt  (6.1 MB)
  mcp_mappings.tsrg    (5.6 MB, TSRG 形式)
```

これらは FG が `client.jar` ↔ 公式名 の変換に使う中間ファイル。**通常は直接開かず、上記 `-sources.jar` を見る**。`client_mappings.txt` の先頭は Mojang EULA コメント + `com.mojang.blaze3d -> ega` のような ProGuard 形式。

**3. IDE での開き方**

*IntelliJ*: `.\gradlew genIntellijRuns` 実行後、Project Tool Window → External Libraries → `Gradle: net.minecraftforge:forge:1.20.1-47.3.0_mapped_official_1.20.1` → `net/minecraft/...` を展開。`-sources.jar` が自動アタッチされ `Block.java:1` 等が公式名で表示される。Run Configuration は `.idea/runConfigurations/runClient.xml` に生成済み。

*Eclipse/VSCode*: `.\gradlew genEclipseRuns` / `genVSCodeRuns` 同様に `-sources.jar` が参照ライブラリに追加される。

**4. 旧MCPとの違い**

| 旧 (1.7.10) | 新 (1.20.1 mojmap) |
|---|---|
| `~/.gradle/caches/minecraft/net/minecraft/minecraftSrc/1.7.10/srgs/stable_12` + `joined.srg` | 上記 `..._mapped_official_1.20.1-sources.jar` のみに統一 |
| `func_149663_c` (SRG) / `field_149764_J` | `BlockBehaviour.Properties` / `VoxelShape` 等の可読名 |
| `gradle.properties` で `mappingsChannel=stable` + `mappingsVersion=12` が必要 | `build.gradle:12` の `mappings channel:'official', version:'1.20.1'` のみに簡略化 |

> **Tips**: `build/` 配下の `build/createMcpToSrg/output.tsrg` / `build/reobfJar/output.jar` は **当MOD自身の reobf** 用で Minecraft 本体の公式ソースではない。Minecraft 公式ソースは常に上記 **Gradle User Cache の `-sources.jar`** を参照すること。

### ソース移行との分担

- 本書は**ビルド枠組み**のみ。以下はソース移行（別エージェント/DOC各論）担当でGradleでは依存削除のみ:
  - `asm/` (CoreMod) / `endlessids` / `PotionID` の撤去判断
  - ブロックモデルJSON化（手書きRender/Model → バニラJSON）はGradle範囲外
  - `TileEntity`→`BlockEntity`、`IWorldGenerator`→`BiomeModifier` 等本体は各 `migration-guide.md` の1.20.1追記を参照

### Further Considerations からの引継

- 連携Modの「大半は無くなってる＝ほぼオミット」はユーザー予想通り。詳細分類は [plugins/migration-guide.md#1201対応分類](./plugins/migration-guide.md#1201対応分類) を正本とし、Gradle側は対応する場合のみ `curse.maven`/`modrinth`/`forge.maven` の1.20.1座標へ置換、ローカルjarは除去。
- エネルギー系は `RF/IEnergyHandler/@Optional.Interface`→`Forge Energy capability(IEnergyStorage)` へ書換必須。GradleではRF系依存を削除するだけでビルドは通るが、ソースの `MachineBase.java:1` / `TileChargerDevice.java:1` / `TileHandleEngine.java:1` の全面書換は各DOC側で対応。

---

## 関連

- [移行プラン](../plan.md) - 本書の元となるフェーズA-C定義、検証、決定事項、連携分類
- [概要](./overview.md) - MOD全体像 + 本書サマリ + 統計
- [README](./README.md) - 索引 + 整備状況（本書新設を反映）
- 各 `migration-guide.md` の1.20.1追記: [blocks](./blocks/migration-guide.md) / [tile-entities](./tile-entities/migration-guide.md) / [fluids](./fluids/migration-guide.md) / [worldgen](./worldgen/migration-guide.md) / [network](./network/migration-guide.md) / [entities](./entities/migration-guide.md) / [achievements](./achievements/migration-guide.md) / [events](./events/migration-guide.md) / [items](./items/migration-guide.md) / [plugins](./plugins/migration-guide.md) / [recipes](./recipes/migration-guide.md) / [handler](./handler/migration-guide.md)
- `gradle.properties:1` / `settings.gradle.kts:1` / `build.gradle.kts:1` / `dependencies.gradle:1` / `gradle/wrapper/gradle-wrapper.properties:1`
- `src/main/resources/META-INF/mods.toml:1` (新設)
