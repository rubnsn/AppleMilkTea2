package mods.defeatedcrow.api.potion;

/**
 * AMT2 のポーション取得ハブ。 <br>
 * 本体側では {@code DeferredRegister<MobEffect>} ({@code common/registry/ModMobEffects}) のラッパーである
 * {@code PotionGetter} がこの manager を実装する (doc/api/migration-guide.md「1.20.1 追補」)。
 */
public class AMTPotionManager {

    public static IPotionGetter manager;

    private AMTPotionManager() {}

}
