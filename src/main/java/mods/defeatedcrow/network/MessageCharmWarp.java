package mods.defeatedcrow.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1: SimpleChannel payload. Legacy fromBytes/toBytes replaced by encode/decode/handle.
 * See doc/network/migration-guide.md:58
 */
public class MessageCharmWarp {

    public int x;
    public int y;
    public int z;

    public MessageCharmWarp() {}

    public MessageCharmWarp(int p2, int p3, int p4) {
        this.x = p2;
        this.y = p3;
        this.z = p4;
    }

    public static void encode(MessageCharmWarp msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);
        buf.writeInt(msg.z);
    }

    public static MessageCharmWarp decode(FriendlyByteBuf buf) {
        return new MessageCharmWarp(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public static void handle(MessageCharmWarp msg, Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                player.teleportTo(msg.x + 0.5D, msg.y + 1, msg.z + 0.5D);
                player.fallDistance = 0.0F;
                SoundEvent suzu = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(DCsAppleMilk.MODID, "suzu"));
                if (suzu != null) {
                    player.level().playSound(null, player.blockPosition(), suzu, SoundSource.PLAYERS, 1.0F, 1.2F);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
