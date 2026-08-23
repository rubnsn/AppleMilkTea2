package mods.defeatedcrow.api.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/**
 * 植物系ブロック用のインターフェイス。
 * 収穫時の動作、収穫可能判定、収穫物を返すためのもの。
 * 苗との関連づけも追加。 <br>
 * 1.20.1: World,int x,int y,int z → Level, BlockPos。IInventory → Container。 <br>
 * 成長段階は実装側で BlockState にマップする。int meta はレガシー互換用として維持 (doc/api/migration-guide.md)。
 */
public interface IRightClickHarvestable {

    /**
     * 収穫時に呼ばれるメソッド。
     * このメソッドの中で引数のインベントリへ収穫物を突っ込む（失敗時は引数の座標にItemEntityをドロップ）、植物ブロックの状態更新を行う。 <br>
     * 成功時trueを返す。 <br>
     * 引数のlevel、posに収穫可能な植物ブロックが無ければ失敗する。
     */
    boolean onHarvest(Level level, BlockPos pos, Container inventory, ItemStack currentItem);

    /**
     * この座標に収穫可能状態の植物ブロックがあるかどうか。
     */
    boolean isHarvestable(Level level, BlockPos pos);

    /**
     * メタデータごとに収穫物を返す。
     * 未成熟のメタデータの場合はnullを返す。
     */
    ItemStack getCropItem(int blockMeta);

    /**
     * この座標の植物ブロックの収穫可能状態のメタデータ。
     * このメタデータに変更することで、成長段階を収穫可能段階に進められる。
     */
    int getGrownMetadata(Level level, BlockPos pos);

    /**
     * レガシー版: metaから収穫可能状態のメタデータを返す。
     */
    int getGrownMetadata(int meta);

    /**
     * この座標の植物ブロックの初期状態のメタデータ。
     * このメタデータに変更することで、成長段階を初期状態に戻すことも出来る。
     */
    int getInitialMetadata(Level level, BlockPos pos);

    /**
     * レガシー版: metaから初期状態のメタデータを返す。
     */
    int getInitialMetadata(int meta);

    /**
     * この座標の植物ブロックの苗にあたるBlockを返す。
     */
    Block getSaplingBlock(int meta);

    int getSaplingMeta(int meta);

}
