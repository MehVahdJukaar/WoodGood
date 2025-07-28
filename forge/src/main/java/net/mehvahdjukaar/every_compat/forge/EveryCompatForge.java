package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.ECNetworking;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.network.forge.ChannelHandlerImpl;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerNegotiationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Optional;

import static net.mehvahdjukaar.every_compat.EveryCompat.forAllModules;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompatCommon {

    public EveryCompatForge() {
        this.initialize();

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    protected void addModules() {
        super.addModules();
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        // Remove the [Debug] strings from ItemToolTip
        event.getToolTip().removeIf(line ->
                line.getString().matches(".*\\[Debug\\] Item Tags:.*") || line.getString().matches(".*\\[Debug\\] Block Tags:.*")
        );

        EveryCompatClient.onItemTooltip(event.getItemStack(),event.getFlags(), event.getToolTip());
    }

    @SubscribeEvent
    public void onRemap(MissingMappingsEvent event) {
        for (var mapping : event.getMappings(Registries.BLOCK_ENTITY_TYPE, EveryCompat.MOD_ID)) {
            ResourceLocation key = mapping.getKey();
            String path = key.getPath();
            forAllModules(m -> {
                if (path.startsWith(m.shortenedId() + "_")) {
                    String newPath = path.substring((m.shortenedId() + "_").length());
                    ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(m.getModId(), newPath);
                    Optional<BlockEntityType<?>> optional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(newId);
                    optional.ifPresent(mapping::remap);
                }
            });
        }
    }


    @SubscribeEvent
    public void onPlayerNegotiation(PlayerNegotiationEvent playerNegotiationEvent) {
        if(ECConfigs.CHECK_PACKET.get()) {
            ((ChannelHandlerImpl) ECNetworking.CHANNEL).channel.sendTo(new ECNetworking.S2CModVersionCheckMessage(),
                    playerNegotiationEvent.getConnection(),
                    NetworkDirection.LOGIN_TO_CLIENT
            );
        }
    }
}
