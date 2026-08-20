package mods.defeatedcrow.plugin.nei;

import cpw.mods.fml.common.Loader;
import mods.defeatedcrow.common.AMTLogger;

public class ClientProxyNPA extends CommonProxyNPA {

    @Override
    public void loadNEI() {
        if (Loader.isModLoaded("NotEnoughItems")) {
            AMTLogger.loadingModInfo("NotEnoughItems");
            try {
                LoadNEIPlugin.load();
            } catch (Exception e) {
                AMTLogger.failLoadingModInfo("NotEnoughItems");
                e.printStackTrace(System.err);
            }
        }
    }

}
