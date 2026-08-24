package mods.defeatedcrow.handler;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * 1.20.1 NetworkUtil - server mode detection.
 * Original 1.7.10 used MinecraftServer.getServer()+FMLServerHandler, now ServerLifecycleHooks.
 */
public class NetworkUtil {

    private NetworkUtil() {}

    public static void initClientMP() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            if (server.isSingleplayer()) {
                NetworkUtilServer.INSTANCE.setIngratedServerMode();
            } else {
                NetworkUtilServer.INSTANCE.setServerMode("dedicated", true, true, true);
            }
        } else {
            NetworkUtilServer.INSTANCE.setServerMode("unknown", false, true, true);
        }
    }

    public static void initServer() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            NetworkUtilServer.INSTANCE.setServerMode("dedicated", true, true, true);
        } else {
            NetworkUtilServer.INSTANCE.setServerMode("unknown", false, false, false);
        }
    }
}
