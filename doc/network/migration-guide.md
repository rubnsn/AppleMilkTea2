# Network 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `DCsNetworkHandler` / `MessageCharmWarp` / `MessageHandlerCharmWarp` / `NetworkUtil` / `NetworkUtilServer`
> 登録元: `network/DCsNetworkHandler.java:1` (`SimpleNetworkWrapper "AMT2"`)
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [Network一覧](../network.md) / [ビルド移行ガイド](../build.md)

## 概要

`SimpleNetworkWrapper` (1.7.10, `cpw.mods.fml.common.network.simpleimpl`) → Forge `SimpleChannel` (1.13+ `net.minecraftforge.network.simple`) への刷新。**1.20.1では `SimpleChannel` は `NetworkRegistry.newSimpleChannel` + `NetworkRegistry.ChannelBuilder` + `FriendlyByteBuf` + `NetworkEvent.Context` の定型に統一。旧 `IMessage`/`IMessageHandler` は削除、`ByteBuf` → `FriendlyByteBuf`、`Side.SERVER/CLIENT` → `NetworkDirection.PLAY_TO_SERVER` / `PLAY_TO_CLIENT` に置換。**

> **ギャップ補足**（plan.md監査）: 旧DOC（本ファイルの1.16索引）は2行の表のみで `SimpleChannel`/`UUID`/バージョン述語未記載。かつ [network.md](../network.md) 自体が1.7.10のままだったため、本改訂で完全版を追記。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("AMT2")` (`FMLNetworkEvent`) | 維持だが `NetworkRegistry.newSimpleChannel` の所在が `net.minecraftforge.fml.common.network.NetworkRegistry` | `SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("defeatedcrow","amt2"), ()-> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals)` (`net.minecraftforge.network.NetworkRegistry`) | **同左 + Protocolは `String PROTOCOL = "1"`（全クライアント共通ならハードコード）。`ChannelBuilder` ではなく `NetworkRegistry.newSimpleChannel` が正（Forge 47）。`SimpleChannel` は `int nextId` でメッセージIDを採番** | `network/DCsNetworkHandler.java:1` |
| `IMessage` + `IMessageHandler<MessageCharmWarp, IMessage>` + `toBytes(ByteBuf)`/`fromBytes(ByteBuf)` | 維持 (1.12) | `IMessage` 削除 → `encoder(BiConsumer<MSG,FriendlyByteBuf>)` / `decoder(Function<FriendlyByteBuf,MSG>)` / `consumer(BiConsumer<MSG,Supplier<NetworkEvent.Context>>)` を `SimpleChannel.messageBuilder(MSG, id).encoder(...).decoder(...).consumerMainThread(...).add()` で登録 | **同左 + `FriendlyByteBuf` (`net.minecraft.network.FriendlyByteBuf`) を使用。`NetworkEvent.Context` の `enqueueWork` + `setPacketHandled(true)` が必須** | `network/MessageCharmWarp.java:1` |
| `Side.SERVER` / `Side.CLIENT` (`IMessageHandler` 登録時) | 維持 (`Side` は `cpw.mods.fml.relauncher.Side`) | `NetworkDirection.PLAY_TO_SERVER` / `PLAY_TO_CLIENT` (`net.minecraftforge.network.NetworkDirection`) | **同左 + `SimpleChannel.messageBuilder` で `NetworkDirection` を暗黙に決定（`consumerMainThread` のContextが方向を持つ）** | 同上 |
| `ByteBuf` (`io.netty.buffer.ByteBuf`) | 維持 (`ByteBuf`) | `FriendlyByteBuf` (`net.minecraft.network.FriendlyByteBuf`) | **同左 (`FriendlyByteBuf` の `writeInt/readInt`, `writeBlockPos/readBlockPos`, `writeUtf/readUtf`)** | `MessageCharmWarp.java:1` |
| `MessageContext#getServerHandler().playerEntity` (`EntityPlayerMP`) | `ctx.getServerHandler().player` | `ctx.get().getSender()` (`ServerPlayer`) | **同左 + `ctx.get().enqueueWork(() -> { ServerPlayer p = ctx.get().getSender(); ... })` でメインスレッドに寄せる** | `network/MessageHandlerCharmWarp.java:1` |
| `FMLCommonHandler.instance().bus().register` | `MinecraftForge.EVENT_BUS.register` に統合 | 同左 | **同左** | `DCsAppleMilk.java:xxx` |

## コード比較

### 1.7.10（現行）

```java
// DCsNetworkHandler.java:1
public class DCsNetworkHandler {
  public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("AMT2");
  public static void init(){ INSTANCE.registerMessage(MessageHandlerCharmWarp.class, MessageCharmWarp.class, 0, Side.SERVER); }
}
// MessageCharmWarp.java:1
public class MessageCharmWarp implements IMessage {
  int x,y,z, dim;
  public MessageCharmWarp(){}
  public MessageCharmWarp(int x,int y,int z,int dim){ this.x=x; this.y=y; this.z=z; this.dim=dim; }
  public void fromBytes(ByteBuf buf){ x=buf.readInt(); y=buf.readInt(); z=buf.readInt(); dim=buf.readInt(); }
  public void toBytes(ByteBuf buf){ buf.writeInt(x); buf.writeInt(y); buf.writeInt(z); buf.writeInt(dim); }
}
// MessageHandlerCharmWarp.java:1
public class MessageHandlerCharmWarp implements IMessageHandler<MessageCharmWarp, IMessage> {
  public IMessage onMessage(MessageCharmWarp m, MessageContext ctx){
    EntityPlayerMP p = ctx.getServerHandler().playerEntity;
    p.setPositionAndUpdate(m.x+0.5, m.y+1, m.z+0.5);
    // dim移動は省略
    return null;
  }
}
```

### 1.12.2（中間、IMessage維持）

ほぼ1.7.10と同じだが `ByteBuf` 周りは `FriendlyByteBuf` ではなく `ByteBuf` のまま。

### 1.20.1（Forge 47, SimpleChannel）

```java
// DCsNetworkHandler.java (1.20.1)
public class DCsNetworkHandler {
  private static final String PROTOCOL_VERSION = "1";
  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
    new ResourceLocation("defeatedcrow","amt2"),
    () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
  );
  private static int id = 0;
  public static void init(){
    INSTANCE.messageBuilder(MessageCharmWarp.class, id++, NetworkDirection.PLAY_TO_SERVER)
      .encoder(MessageCharmWarp::encode)
      .decoder(MessageCharmWarp::decode)
      .consumerMainThread(MessageCharmWarp::handle)
      .add();
    // 将来クライアント→クライアントやサーバー→クライアントも同様に id++ で追加。UUIDは不要（SimpleChannelが自動でバージョン述語を管理）
  }
}

// MessageCharmWarp.java (1.20.1, IMessage削除)
public class MessageCharmWarp {
  final int x,y,z, dim;
  public MessageCharmWarp(int x,int y,int z,int dim){ this.x=x; this.y=y; this.z=z; this.dim=dim; }
  public static void encode(MessageCharmWarp msg, FriendlyByteBuf buf){
    buf.writeInt(msg.x); buf.writeInt(msg.y); buf.writeInt(msg.z); buf.writeInt(msg.dim);
    // 1.20.1では BlockPos を使う方が自然: buf.writeBlockPos(new BlockPos(msg.x,msg.y,msg.z))
  }
  public static MessageCharmWarp decode(FriendlyByteBuf buf){
    return new MessageCharmWarp(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
  }
  public static void handle(MessageCharmWarp msg, Supplier<NetworkEvent.Context> ctx){
    ctx.get().enqueueWork(() -> {
      ServerPlayer player = ctx.get().getSender();
      if(player != null){
        // チャームワープ本来は dim移動も含むが 1.20.1では ServerPlayer.changeDimension や teleportTo を使用
        player.teleportTo(player.server.getLevel(Level.OVERWORLD), msg.x+0.5, msg.y+1, msg.z+0.5, player.getYRot(), player.getXRot());
        // もしくは player.teleportTo(msg.x+0.5, msg.y+1, msg.z+0.5) （dim固定なら）
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
```

### 送信側（NetworkUtil）

```java
// 1.7.10: NetworkUtil.java
DCsNetworkHandler.INSTANCE.sendToServer(new MessageCharmWarp(x,y,z,dim));

// 1.20.1: 同じだが handle 側が consumerMainThread に統合されたため送信は同一
DCsNetworkHandler.INSTANCE.sendToServer(new MessageCharmWarp(x,y,z,player.level.dimension().location().toString()... ));
// またはクライアント→サーバー以外: INSTANCE.send(PacketDistributor.PLAYER.with(()-> player), msg)
```

## 1.20.1 追加注意

- **UUID**: `SimpleChannel` の `newSimpleChannel` 第2-4引数は `Supplier<String> version` + `Predicate<String> clientAccepted` + `Predicate<String> serverAccepted`。旧来の `NetworkModHolder` のようなUUIDは不要。`PROTOCOL_VERSION` を `"1"` に固定し `::equals` で検証する定型で十分。
- **スレッド**: `consumerMainThread` は自動で `enqueueWork` を要求しないが、旧 `onMessage` がメインスレッド外で呼ばれていたため、1.20.1では `handle` 内で `ctx.get().enqueueWork` することが必須（さもないと `ConcurrentModification`）。
- **Side**: 1.20.1では `Side.SERVER/CLIENT` は削除。代わりに `NetworkDirection` で方向を表すが、`messageBuilder` の登録時に暗黙に決まる（`PLAY_TO_SERVER` ならクライアント→サーバー）。
- **複数メッセージ**: `id` は `0,1,2...` と連番。CharM Warpが唯一なら `0`、今後 `MessageTeaMakerSync` 等を追加するなら `1` 以降を採番。

## [network.md](../network.md) 本体の追記

`network.md` は 1.20.1で上記 SimpleChannel 定型に更新済（詳細は本migration-guideを正とする）。旧 `IMessage` の記述は `network/` 個別ページ (`DCsNetworkHandler.md`, `MessageCharmWarp.md`) にも反映し、`ByteBuf` → `FriendlyByteBuf`、`IMessageHandler` → `encode/decode/handle` の3メソッド分割に更新すること。

## 検証手順

1. `grep -r "SimpleNetworkWrapper"` → 0件（`SimpleChannel` のみに置換）を確認。
2. `grep -r "IMessage"` → 0件（1.20.1は `IMessage` 削除）を確認。
3. `grep -r "FriendlyByteBuf"` → 1件以上（encode/decodeで使用）を確認。
4. `gradlew build` で `NetworkRegistry.newSimpleChannel` の引数3つの型エラーが無いか確認（`Supplier<String>` + `Predicate<String>` ×2）。
5. ゲーム内でチャームワープ（`X` キー）→ サーバー側 `handle` が `setPacketHandled(true)` されワープが成功するか `runClient` で確認。

## 関連

- [Network一覧](../network.md) / [個別ページ索引](../network/README.md) - 1.20.1 SimpleChannel定型
- [Handler一覧](../handler.md) - `NetworkUtil` / `NetworkUtilServer` はクラ/サバ側のラッパー
- `src/main/java/mods/defeatedcrow/network/*`
- `src/main/java/mods/defeatedcrow/handler/NetworkUtil.java:1`
- [ビルド移行ガイド](../build.md) - Mojang mappingsで `FriendlyByteBuf` 周りのMCP名が `m_...` でなく `writeInt` 等のMojmap名に
