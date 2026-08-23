package mods.defeatedcrow.network;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkDirection;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1: legacy simpleimpl wrapper removed; uses SimpleChannel (NetworkRegistry.newSimpleChannel).
 * See doc/network/migration-guide.md:58
 */
public class DCsNetworkHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(DCsAppleMilk.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals);

    private static int id = 0;

    public static void init() {
        INSTANCE.messageBuilder(MessageCharmWarp.class, id++, NetworkDirection.PLAY_TO_SERVER)
            .decoder(MessageCharmWarp::decode)
            .encoder(MessageCharmWarp::encode)
            .consumerMainThread(MessageCharmWarp::handle)
            .add();
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }
}
