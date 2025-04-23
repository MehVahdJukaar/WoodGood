package net.mehvahdjukaar.every_compat;

import io.netty.buffer.Unpooled;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.network.Message;
import net.mehvahdjukaar.moonlight.api.platform.network.NetworkHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ECNetworking {

    public static void init() {
        NetworkHelper.addNetworkRegistration(ECNetworking::registerMessages, 3);
    }

    private static void registerMessages(NetworkHelper.RegisterMessagesEvent event) {
        event.registerBidirectional(S2CModVersionCheckMessage.CODEC);
        event.registerClientBound(S2CBlockStateCheckMessage.CODEC);
    }


    public static class S2CModVersionCheckMessage implements Message {

        public static final TypeAndCodec<RegistryFriendlyByteBuf, S2CModVersionCheckMessage> CODEC = Message.makeType(
                EveryCompat.res("mod_version_check"), S2CModVersionCheckMessage::new);

        private final Map<String, String> mods = new HashMap<>();

        public S2CModVersionCheckMessage() {
            for (var m : EveryCompat.getDependencies()) {
                var v = PlatHelper.getModVersion(m);
                if (v != null) {
                    mods.put(m, v);
                }
            }
        }

        public S2CModVersionCheckMessage(RegistryFriendlyByteBuf buf) {
            this.mods.putAll(buf.readMap(buf1 -> buf.readUtf(), buf1 -> buf.readUtf()));
        }

        @Override
        public void write(RegistryFriendlyByteBuf buf) {
            buf.writeMap(mods, (buf1, s) -> buf.writeUtf(s), (buf1, s) -> buf.writeUtf(s));
        }

        @Override
        public void handle(Context context) {
            for (var m : mods.entrySet()) {
                String clientVersion = PlatHelper.getModVersion(m.getKey());
                String serverVersion = m.getValue();
                if (!Objects.equals(serverVersion, clientVersion)) {
                    context.disconnect(Component.literal("EveryCompat has detected that server has mismatched mod versions for mod " +
                            m.getKey() + ": requested version " + serverVersion + ", actual version " + clientVersion));
                }
            }
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return CODEC.type();
        }
    }

    public static class S2CBlockStateCheckMessage implements Message {

        public static final TypeAndCodec<RegistryFriendlyByteBuf, S2CBlockStateCheckMessage> CODEC = Message.makeType(
                EveryCompat.res("blockstate_check"), S2CBlockStateCheckMessage::new);

        public S2CBlockStateCheckMessage(RegistryFriendlyByteBuf buf) {
            int start = buf.readVarInt();
            int size = buf.readVarInt();

            boolean mismatched = false;
            for (int i = start; i < (start + size); i++) {
                try {
                    var nbt = buf.readNbt();
                    if (nbt == null) {
                        int aa = 1;
                    }
                    var b = Utils.readBlockState(nbt, null);
                    BlockState exp = Block.BLOCK_STATE_REGISTRY.byId(i);
                    if (b != exp) {
                        if (!mismatched) {
                            EveryCompat.LOGGER.error("Found blockstate id mismatch from {}at id {}. Was expecting: {}", b, i, exp);
                        }
                        mismatched = true;
                    } else {
                        if (mismatched) {
                            EveryCompat.LOGGER.error("to{}at id {}", b, i);
                        }
                        mismatched = false;
                    }
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to read blockstate in id map: ", e);
                    break;
                }
            }
            buf.release();
        }

        public S2CBlockStateCheckMessage() {
        }

        @Override
        public void write(RegistryFriendlyByteBuf buf) {
            FriendlyByteBuf dummy = new FriendlyByteBuf(Unpooled.buffer());
            int start = lastInd;
            for (int i = lastInd; i < Block.BLOCK_STATE_REGISTRY.size(); i++) {
                lastInd++;
                CompoundTag nbt = NbtUtils.writeBlockState(Block.stateById(i));
                dummy.writeNbt(nbt);
                if (dummy.writerIndex() > 1048576 * 0.9) {
                    break;
                }
            }
            buf.writeVarInt(start);
            buf.writeVarInt(lastInd - start);
            buf.writeBytes(dummy);
            dummy.release();
        }

        @Override
        public void handle(Context context) {

        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return CODEC.type();
        }
    }


    private static int lastInd = 0;

    public static void sendPacket(ServerPlayer s) {
        if (ECConfigs.DEBUG_PACKET.get() || PlatHelper.isDev()) {
            lastInd = 0;
            EveryCompat.LOGGER.warn("Starting Blockstate Map validity check:");
            while (lastInd < Block.BLOCK_STATE_REGISTRY.size()) {
                NetworkHelper.sendToClientPlayer(s, new S2CBlockStateCheckMessage());
            }
        }
    }

}
