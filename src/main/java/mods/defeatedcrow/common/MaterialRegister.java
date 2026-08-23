package mods.defeatedcrow.common;

/**
 * 1.20.1: MaterialRegister は廃止。1.7.10 の GR registerBlock / registerItem / FluidRegistry / Potion 拡張は
 * 全て DeferredRegister に移管された。参照先:
 * - Block:   common/registry/ModBlocks.java      (WT-A)
 * - Item:    common/registry/ModItems.java       (WT-A)
 * - Fluid:   common/registry/ModFluids.java + ModFluidTypes.java (WT-B) doc/fluids/migration-guide.md:12
 * - BE/ME:   common/registry/ModBlockEntities.java + ModMenuTypes.java (WT-B) doc/tile-entities/migration-guide.md:12
 * - Entity:  common/registry/ModEntities.java    (WT-B)
 * - Effect:  common/registry/ModMobEffects.java  (WT-C)
 * - Tab:     common/registry/ModCreativeTabs.java(WT-A)
 *
 * 本クラスは歴史的参照のためだけに残し、将来的に削除する。DCsAppleMilk.preInit からの呼び出しは DCsAppleMilk.java 1.20.1 で削除済。
 */
public class MaterialRegister {

    public static final MaterialRegister instance = new MaterialRegister();

    private MaterialRegister() {}

    @Deprecated
    public void load() {}

    @Deprecated
    public void addFluid() {}

    @Deprecated
    public boolean addPotion() {
        return true;
    }
}
