# Network 一覧

> Source: `src/main/java/mods/defeatedcrow/network/*` (3クラス) + `handler/NetworkUtil.java:1` + `handler/NetworkUtilServer.java:1`
> Channel: `src/main/java/mods/defeatedcrow/network/DCsNetworkHandler.java:1` (`SimpleNetworkWrapper` "AMT2")
> 対象: チャームワープ同期

## 概要
`SimpleNetworkWrapper` で `MessageCharmWarp` (クライアント→サーバー) を同期。`DCsNetworkHandler` が `SimpleNetworkWrapper` のチャンネル `AMT2` を生成し `registerMessage(MessageHandlerCharmWarp.class, MessageCharmWarp.class, 0, Side.SERVER)`。`ItemPrincessClam` のチャームワープキー (default `X` `0x2D`) で発動。

## 一覧

| クラス | ソース | 役割 | 個別ページ |
|---|---|---|---|
| `DCsNetworkHandler` | `network/DCsNetworkHandler.java:1` | チャンネル生成・登録 | [→](./network/DCsNetworkHandler.md) |
| `MessageCharmWarp` | `network/MessageCharmWarp.java:1` | パケット (`IMessage`, `toBytes`/`fromBytes` で int 座標) | [→](./network/MessageCharmWarp.md) |
| `MessageHandlerCharmWarp` | `network/MessageHandlerCharmWarp.java:1` | ハンドラ (`IMessageHandler<MessageCharmWarp, IMessage>`, `onMessage` でサーバー側テレポート) | [→](./network/MessageHandlerCharmWarp.md) |
| `NetworkUtil` | `handler/NetworkUtil.java:1` | クライアント側ユーティリティ (`ClientProxy` から呼出) | [→](./network/NetworkUtil.md) |
| `NetworkUtilServer` | `handler/NetworkUtilServer.java:1` | サーバー側ユーティリティ (`CommonProxy.serverStart`) | [→](./network/NetworkUtilServer.md) |

## 登録情報

```java
// DCsNetworkHandler.java:1
public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("AMT2");
public static void init(){
  INSTANCE.registerMessage(MessageHandlerCharmWarp.class, MessageCharmWarp.class, 0, Side.SERVER);
  // 1.7.10は Side 指定、1.8+は MessageHandler 側で Side
}
// MessageCharmWarp.java:1
public class MessageCharmWarp implements IMessage {
  int x,y,z, dim; // ワープ先座標
  public void fromBytes(ByteBuf buf){ x=buf.readInt(); ... }
  public void toBytes(ByteBuf buf){ buf.writeInt(x); ... }
}
// MessageHandlerCharmWarp.java:1
public class MessageHandlerCharmWarp implements IMessageHandler<MessageCharmWarp, IMessage> {
  public IMessage onMessage(MessageCharmWarp message, MessageContext ctx){
    EntityPlayerMP player = ctx.getServerHandler().playerEntity;
    player.setPositionAndUpdate(message.x+0.5, message.y+1, message.z+0.5);
    return null;
  }
}
```

## 移行 (1.12.2+)

| 1.7.10 | 1.12.2+ | 1.16.5+ | 参照 |
|---|---|---|---|
| `SimpleNetworkWrapper` (`cpw.mods.fml.common.network.simpleimpl.*`) | `SimpleNetworkWrapper` 維持だが `NetworkRegistry.newSimpleChannel` → `NetworkRegistry.newSimpleChannel` (Forge `SimpleChannel`) | `SimpleChannel` (`net.minecraftforge.network.simple.SimpleChannel`) + `NetworkRegistry.ChannelBuilder` | `network/DCsNetworkHandler.java:1` |
| `IMessage` + `IMessageHandler` + `toBytes`/`fromBytes` | 維持 (1.12) | `Packet` (`FriendlyByteBuf`) + `SimpleChannel.messageBuilder` + `encoder`/`decoder`/`consumer` | 同上 |
| `NetworkRegistry.INSTANCE.newSimpleChannel("AMT2")` (`FMLNetworkEvent`) | `NetworkRegistry.ChannelBuilder.named(new ResourceLocation("defeatedcrow","amt2")).networkProtocolVersion(() -> "1.0")` | 同左 + `ChannelBuilder` | 同上 |
| `Side.SERVER` / `Side.CLIENT` | `NetworkDirection` (`NetworkDirection.PLAY_TO_SERVER`) | 同左 | 同上 |
| `ByteBuf` (`io.netty.buffer.ByteBuf`) | 維持 (1.12 `ByteBuf`) | `FriendlyByteBuf` (`net.minecraft.network.FriendlyByteBuf`) | `MessageCharmWarp.java:1` |

### 1.16+ 移行例

```java
// DCsNetworkHandler.java (1.16+)
private static final String PROTOCOL = "1";
public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
  new ResourceLocation("defeatedcrow","amt2"), ()-> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals);
public static void init(){
  INSTANCE.registerMessage(0, MessageCharmWarp.class,
    (msg, buf)->{ buf.writeInt(msg.x); buf.writeInt(msg.y); buf.writeInt(msg.z); buf.writeInt(msg.dim); },
    buf-> new MessageCharmWarp(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()),
    (msg, ctx)->{
      ctx.get().enqueueWork(()->{
        ServerPlayer player = ctx.get().getSender();
        player.teleportTo(msg.x+0.5, msg.y+1, msg.z+0.5);
      });
      ctx.get().setPacketHandled(true);
    });
}
```

## 関連
- [Handler 一覧](./handler.md)
- [Item 一覧](./items.md) - `ItemPrincessClam` (チャームワープ)
- `src/main/java/mods/defeatedcrow/network/*`
