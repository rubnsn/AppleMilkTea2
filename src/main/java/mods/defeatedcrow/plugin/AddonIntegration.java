package mods.defeatedcrow.plugin;

import net.minecraftforge.fml.ModList;

// defeatedcrow製add-onとの調整が必要な部分をここに置く
public class AddonIntegration {

    private AddonIntegration() {}

    public static void load() {
        // 1.20.1: ModList.get().isLoaded はいつでも参照可能なため、
        // 事前キャッシュは不要。互換のためメソッド自体は維持する。
    }

    public static boolean loadedJP() {
        return ModList.get().isLoaded("AMTAddonJP");
    }

    public static boolean loadedMagic() {
        return ModList.get().isLoaded("AMTAddonMagic");
    }

    public static boolean loadedSweet() {
        return ModList.get().isLoaded("AMTAddonSweet");
    }

}
