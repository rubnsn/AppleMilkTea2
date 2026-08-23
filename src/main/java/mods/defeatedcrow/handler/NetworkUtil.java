package mods.defeatedcrow.handler;

import net.minecraft.server.MinecraftServer;

import mods.defeatedcrow.common.AMTLogger;

public class NetworkUtil {

    private NetworkUtil() {}

    // プレイヤーがクライアント側で呼ぶ処理
    public static void initClientMP() {
        if (MinecraftServer.getServer() != null) {
            if (MinecraftServer.getServer()
                .isSinglePlayer()) {
                AMTLogger.debugInfo("Recognized to Single Mode.");
                NetworkUtilServer.INSTANCE.setIngratedServerMode();
            } else {
                AMTLogger.debugInfo("Recognized to Server Mode.");
                String name = MinecraftServer.getServer()
                    .getServerOwner();
                if (name == null) {
                    name = "unknown";
                }
                boolean online = MinecraftServer.getServer()
                    .isServerInOnlineMode();
                boolean nether = MinecraftServer.getServer()
                    .getAllowNether();
                boolean pvp = MinecraftServer.getServer()
                    .isPVPEnabled();
                NetworkUtilServer.INSTANCE.setServerMode(name, online, nether, pvp);
            }
        } else// 恐らくマルチではこれになる。getServer()でnullしかかえってこない
        {
            AMTLogger.warn("Failed to recognize Minecraft Server. It will not work correctly.");
            NetworkUtilServer.INSTANCE.setServerMode("unknown", false, true, true);
        }
    }

    
    public static void initServer() {
        if (net.minecraftforge.fml.ModList.get()
            .getServer() != null) {
            AMTLogger.debugInfo("Recognized to Server Mode.");
            String name = net.minecraftforge.fml.ModList.get()
                .getServer()
                .getServerOwner();
            if (name == null) {
                name = "unknown";
            }
            boolean online = net.minecraftforge.fml.ModList.get()
                .getServer()
                .isServerInOnlineMode();
            boolean nether = net.minecraftforge.fml.ModList.get()
                .getServer()
                .getAllowNether();
            boolean pvp = net.minecraftforge.fml.ModList.get()
                .getServer()
                .isPVPEnabled();
            NetworkUtilServer.INSTANCE.setServerMode(name, online, nether, pvp);
        } else {
            AMTLogger.warn("Failed to recognize Minecraft Server. It will not work correctly.");
            NetworkUtilServer.INSTANCE.setServerMode("unknown", false, false, false);
        }
    }
}
