package mods.defeatedcrow.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageCharmWarp implements IMessage {

    public int x;
    public int y;
    public int z;

    public MessageCharmWarp() {}

    public MessageCharmWarp(int p2, int p3, int p4) {
        this.x = p2;
        this.y = p3;
        this.z = p4;
    }

    // read
    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    // write
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }
}
