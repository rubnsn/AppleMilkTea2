# ワークツリープロンプト索引

> 3ワークツリー並列用。必ず `feature/1.20.1-bootstrap` が `dev` にマージされた後に使うこと。

| Worktree | Branch | 所有 | プロンプト |
|---|---|---|---|
| WT0 Bootstrap | `feature/1.20.1-bootstrap` | HotSpot + `common/registry/Mod*.java` 雛形 | [WT0-Bootstrap.md](./WT0-Bootstrap.md) |
| WT-A | `feature/blocks-items` | `common/block/**`, `common/item/**`, `CreativeTab*.java` | [WT-A-Blocks-Items.md](./WT-A-Blocks-Items.md) |
| WT-B | `feature/tiles-fluids-world` | `common/tile/**`, `common/fluid/**`, `common/entity/**`, `common/world/**`, `event/**`, `handler/**` | [WT-B-Tiles-Fluids-World.md](./WT-B-Tiles-Fluids-World.md) |
| WT-C | `feature/client-cross` | `client/**`, `potion/**`, `recipe/**`, `network/**`, `plugin/**` | [WT-C-Client-Cross.md](./WT-C-Client-Cross.md) |

## 使い方（人間が手動で）

```powershell
# 1. dev派生済みを確認
git branch --show-current  # dev

# 2. Bootstrapを先に作ってdevへマージ
git checkout -b feature/1.20.1-bootstrap dev
# opencode --agent bootstrap で WT0-Bootstrap.md のプロンプトを貼る
# commit & merge
git checkout dev; git merge --no-ff feature/1.20.1-bootstrap

# 3. 3ワークツリー作成（並列3枚まで）
git worktree add ../AMT2-WT-A -b feature/blocks-items dev
git worktree add ../AMT2-WT-B -b feature/tiles-fluids-world dev
git worktree add ../AMT2-WT-C -b feature/client-cross dev

# 4. 各worktreeで opencode 起動（別ポートで）
cd ../AMT2-WT-A; opencode --agent wt-a
cd ../AMT2-WT-B; opencode --agent wt-b --port 4097
cd ../AMT2-WT-C; opencode --agent wt-c --port 4098
# 各TUIに上のプロンプトを貼る

# 5. 検証はビルド前は lint のみ（修正優先）
pwsh -File scripts/lint-migration.ps1 -Check wta
pwsh -File scripts/lint-migration.ps1 -Check wtb
pwsh -File scripts/lint-migration.ps1 -Check wtc

# 6. 全修正後、devで一括ビルド
git checkout dev; git merge --no-ff feature/blocks-items feature/tiles-fluids-world feature/client-cross
./gradlew build
```
