package mods.defeatedcrow.api.charge;

import net.minecraft.core.Direction;

/**
 * chargeを発生させるBlockEntityに実装するメソッド。 <br>
 * なお、このMODのチャージエネルギーは基本的に様々なMODのエネルギーを仲介するためのAMT2専用のエネルギーなので、
 * 他MODがチャージを受け取ることは想定していない。 <br>
 * （連携目的であれば、RFやEUなどの他の工業エネルギー、1.20.1では {@code ForgeCapabilities.ENERGY} を利用することをお勧めする。） <br>
 * <br>
 * AMT2の装置にチャージを送りたい場合は、このインターフェイスを実装して装置に隣接させれば、 <br>
 * AMT2装置の側のupdate処理でチャージを取得する。 <br>
 * 1.20.1: ForgeDirection → Direction。
 */
public interface IChargeGenerator {

    boolean canGenerate();

    int generateCharge(Direction dir, boolean simulate);

}
