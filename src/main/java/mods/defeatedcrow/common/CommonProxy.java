package mods.defeatedcrow.common;

import net.minecraft.world.level.Level;

/**
 * 1.20.1 stub — CommonProxy は IGuiHandler / GR registerTileEntity を廃止。
 * 全 47 TileEntity 登録は ModBlockEntities (DeferredRegister + BlockEntityType Builder of) に移管.
 * GUI は IGuiHandler → MenuType (ModMenuTypes: iceMaker, processor, advProcessor, evaporator, batBox)。
 *
 * 旧 CommonProxy registerTileEntity の 47件:
 * TileHasDirection, TileHasRemaining, TileHasRemain2,
 * TileCupHandle, TileBread, TileDummy, TileJPBowl, TileChopsticksBox, TileEggs, TileSteak,
 * TileMakerHandle, TilePanHandle, TileFilledSoupPan, TileMakerNext, TileWipeBox, TileIceMaker,
 * TileIceCream, TileWipeBox2, TileRotaryDial, TileCocktail, TileCocktail2, TileLargeBottle,
 * TileEmptyBottle, TileCLamp, TileCordial, TileAlcoholCup, TileEvaporator, TileProcessor,
 * TileAdvProcessor, TileVegiBag, TileCardBoard, TileCPanel, TileIncenseBase, TilePanG,
 * TileBrewingBarrel, TileChargerBase, TileChargerDevice, TileFlowerPot, TileGelBat,
 * TileTeppanII, TileCocktailSP, TileHandleEngine, TileBowlRack, TileContainerBase, TileCrowDoll
 * → ModBlockEntities BLOCK_ENTITIES register("...", () -> BlockEntityType Builder of(..., ModBlocks X get()).build(null))
 *
 * doc/tile-entities/migration-guide.md:12 準拠。Builder create → Builder of へ。
 */
public class CommonProxy {

    public void registerTileEntity() {}

    public int getRenderID() {
        return -1;
    }

    public void registerRenderers() {}

    public int addArmor(String armor) {
        return 0;
    }

    public Level getClientWorld() {
        return null;
    }

    public void serverStart() {}

    public void playerLogin(net.minecraft.world.entity.player.Player player) {}

    public void init() {}

    public void registerTex() {}

    public void registerFluidTex() {}

    public boolean isShiftKeyDown() { return false; }
    public boolean isJumpKeyDown() { return false; }
    public boolean isSneakKeyDown() { return false; }
    public boolean isFowardKeyDown() { return false; }
    public boolean isBackKeyDown() { return false; }
    public boolean isLeftKeyDown() { return false; }
    public boolean isRightKeyDown() { return false; }
    public boolean isWarpKeyDown() { return false; }
}
