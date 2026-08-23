package mods.defeatedcrow.api.charge;

/**
 * 1.7.10〜1.12 の独自チャージ登録ハブ。 <br>
 * 1.20.1 では ForgeEnergy ({@code ForgeCapabilities.ENERGY} / {@code IEnergyStorage}) への置換が推奨される
 * (doc/api/migration-guide.md「Capability 移行例」)。 <br>
 * 本体側の移行が完了するまでの互換のため {@link #chargeItem} を凍結維持する。新規実装は IEnergyStorage を使用すること。
 */
@Deprecated
public final class ChargeItemManager {

    public static IChargeItemRegister chargeItem;

    private ChargeItemManager() {}

}
