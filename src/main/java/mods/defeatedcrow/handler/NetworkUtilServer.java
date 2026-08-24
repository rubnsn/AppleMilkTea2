package mods.defeatedcrow.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;
import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1 NetworkUtilServer - server property holder.
 * Original used FMLClientHandler+IntegratedServer, now ServerLifecycleHooks+Minecraft.
 */
public class NetworkUtilServer {

    public static final NetworkUtilServer INSTANCE = new NetworkUtilServer();
    private NetworkUtilServer() {}

    private static boolean isOnlineMode;
    private static boolean allowedNether;
    private static boolean allowedPvP;
    private static String ownerName;
    private static boolean isIntegratedServer = false;

    public void setServerMode(String owner, boolean online, boolean nether, boolean pvp) {
        ownerName = owner;
        isOnlineMode = online;
        allowedNether = nether;
        allowedPvP = pvp;
    }

    public void setIngratedServerMode() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null && Minecraft.getInstance() != null) {
            ownerName = "Integrated";
        } else if (server != null) {
            ownerName = server.isSingleplayer() ? "Integrated" : "dedicated";
        } else {
            ownerName = "unknown";
        }
        if ("ForgeDevName".equalsIgnoreCase(ownerName)) {
            isIntegratedServer = true;
            isOnlineMode = true;
            allowedNether = true;
            allowedPvP = true;
            DCsAppleMilk.debugMode = true;
        } else {
            isIntegratedServer = server != null && server.isSingleplayer();
            isOnlineMode = true;
            allowedNether = true;
            allowedPvP = true;
        }
    }

    public String getOwnerName() { return ownerName; }
    public boolean isOnlineMode() { return isOnlineMode; }
    public boolean enableNether() { return allowedNether; }
    public boolean enablePvP() { return allowedPvP; }
    public boolean isIntegratedServer() { return isIntegratedServer; }
}
