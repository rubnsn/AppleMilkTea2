param(
  [ValidateSet("all","bootstrap","wta","wtb","wtc")]
  [string]$Check = "all"
)

$ErrorActionPreference = "Continue"
$failed = $false

function Lint-Grep {
  param([string]$Pattern, [string[]]$Paths, [string]$Label, [bool]$ExpectZero = $true)
  $hits = @()
  foreach ($p in $Paths) {
    $files = @()
    if ($p.Contains("**")) {
      $base = ($p -split "\*\*")[0].TrimEnd('/','\')
      if ([string]::IsNullOrWhiteSpace($base)) { $base = "src" }
      $ext = [System.IO.Path]::GetExtension($p)
      if ([string]::IsNullOrWhiteSpace($ext)) { $ext = ".java" }
      $files = @(Get-ChildItem -LiteralPath $base -Recurse -Filter "*$ext" -ErrorAction SilentlyContinue | Select-Object -ExpandProperty FullName)
    } else {
      $files = @(Get-ChildItem -Path $p -ErrorAction SilentlyContinue | Select-Object -ExpandProperty FullName)
      if ($files.Count -eq 0 -and (Test-Path -LiteralPath $p)) { $files = @($p) }
    }
    foreach ($f in $files) {
      $hits += @(Select-String -Pattern $Pattern -LiteralPath $f -ErrorAction SilentlyContinue)
    }
  }
  $count = $hits.Count
  if ($ExpectZero -and $count -gt 0) {
    Write-Host "FAIL: $Label — $Pattern : $count hits" -ForegroundColor Red
    $hits | Select-Object -First 5 | ForEach-Object { Write-Host "  $($_.Path):$($_.LineNumber): $($_.Line.Trim())" }
    $script:failed = $true
  } elseif (-not $ExpectZero -and $count -eq 0) {
    Write-Host "FAIL: $Label — $Pattern : expected >=1 but 0" -ForegroundColor Red
    $script:failed = $true
  } else {
    Write-Host "PASS: $Label — $Pattern : $count"
  }
}

# Define owned globs per check (bootstrap checks HotSpot, wta checks block/item, etc.)
$owned = switch ($Check) {
  "bootstrap" { @("src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java","src/main/java/mods/defeatedcrow/common/MaterialRegister.java","src/main/java/mods/defeatedcrow/common/CommonProxy.java","src/main/java/mods/defeatedcrow/client/ClientProxy.java","src/main/java/mods/defeatedcrow/common/registry/*.java","src/main/java/mods/defeatedcrow/common/config/*.java") }
  "wta"       { @("src/main/java/mods/defeatedcrow/common/block/**/*.java","src/main/java/mods/defeatedcrow/common/item/**/*.java","src/main/java/mods/defeatedcrow/common/CreativeTab*.java") }
  "wtb"       { @("src/main/java/mods/defeatedcrow/common/tile/**/*.java","src/main/java/mods/defeatedcrow/common/fluid/**/*.java","src/main/java/mods/defeatedcrow/common/entity/**/*.java","src/main/java/mods/defeatedcrow/common/world/**/*.java","src/main/java/mods/defeatedcrow/event/**/*.java","src/main/java/mods/defeatedcrow/handler/**/*.java") }
  "wtc"       { @("src/main/java/mods/defeatedcrow/client/**/*.java","src/main/java/mods/defeatedcrow/potion/**/*.java","src/main/java/mods/defeatedcrow/recipe/**/*.java","src/main/java/mods/defeatedcrow/network/**/*.java","src/main/java/mods/defeatedcrow/plugin/**/*.java") }
  default     { @("src/main/java/**/*.java") }
}

Write-Host "=== lint-migration Check=$Check Owned=$($owned -join ', ') ==="

# Common 1.7.10 remnants that must be 0 after migration
Lint-Grep -Pattern "cpw\.mods\.fml" -Paths $owned -Label "cpw.mods.fml (must be 0)"
Lint-Grep -Pattern "GameRegistry\.register" -Paths $owned -Label "GameRegistry.register"
Lint-Grep -Pattern "IIcon" -Paths $owned -Label "IIcon"
Lint-Grep -Pattern "registerBlockIcons" -Paths $owned -Label "registerBlockIcons"
Lint-Grep -Pattern "getIcon\(int" -Paths $owned -Label "getIcon(int"
Lint-Grep -Pattern "setBlockName" -Paths $owned -Label "setBlockName"
Lint-Grep -Pattern "S35PacketUpdateTileEntity" -Paths $owned -Label "S35PacketUpdateTileEntity"
Lint-Grep -Pattern "net\.minecraft\.block\.BlockContainer\b|extends BlockContainer\b|implements BlockContainer\b" -Paths $owned -Label "vanilla BlockContainer (exclude BlockContainerBase)"
Lint-Grep -Pattern "BlockFluidClassic" -Paths $owned -Label "BlockFluidClassic"
Lint-Grep -Pattern "FluidContainerRegistry" -Paths $owned -Label "FluidContainerRegistry"
Lint-Grep -Pattern "SimpleNetworkWrapper" -Paths $owned -Label "SimpleNetworkWrapper"
Lint-Grep -Pattern "implements IMessage" -Paths $owned -Label "IMessage"
Lint-Grep -Pattern "RenderingRegistry\.registerBlockHandler" -Paths $owned -Label "ISBRH registerBlockHandler"
Lint-Grep -Pattern "TileEntitySpecialRenderer" -Paths $owned -Label "TileEntitySpecialRenderer"
Lint-Grep -Pattern "BlockEntityType\.Builder\.create" -Paths $owned -Label "Builder.create (must be Builder.of)"
# Positive checks (should exist after bootstrap)
if ($Check -eq "bootstrap" -or $Check -eq "all") {
  Lint-Grep -Pattern "DeferredRegister" -Paths @("src/main/java/mods/defeatedcrow/common/registry/*.java") -Label "DeferredRegister exists" -ExpectZero:$false
  Lint-Grep -Pattern "BlockBehaviour\.Properties" -Paths @("src/main/java/mods/defeatedcrow/common/registry/*.java") -Label "BlockBehaviour.Properties hint" -ExpectZero:$false
}

if ($failed) { Write-Host "`nLINT FAILED — fix above hits before merge" -ForegroundColor Red; exit 1 }
else { Write-Host "`nLINT PASS" -ForegroundColor Green; exit 0 }
