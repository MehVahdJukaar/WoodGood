package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwbridges.init.BlockInit;
import net.kikoz.mcwbridges.objects.Iron_Stair;
import net.kikoz.mcwbridges.objects.Log_Bridge;
import net.kikoz.mcwbridges.objects.Rail_Bridge;
import net.kikoz.mcwbridges.objects.Support_Pillar;
import net.kikoz.mcwbridges.util.BridgesGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class MacawBridgesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BRIDGE_PIERS;
    public final SimpleEntrySet<WoodType, Block> BRIDGE_MIDDLES;
    public final SimpleEntrySet<WoodType, Block> ROPE_BRIDGES;
    public final SimpleEntrySet<WoodType, Block> RAIL_BRIDGES;
    public final SimpleEntrySet<WoodType, Block> BRIDGE_STAIRS;
    public final SimpleEntrySet<WoodType, Block> ROPE_STAIRS;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");

        BRIDGE_PIERS = SimpleEntrySet.builder(WoodType.class, "bridge_pier",
                        () -> BlockInit.OAK_BRIDGE_PIER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Support_Pillar(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_piers"), Registries.BLOCK)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_PIERS);

        ROPE_BRIDGES = SimpleEntrySet.builder(WoodType.class, "bridge", "rope",
                        () -> BlockInit.ROPE_OAK_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Log_Bridge(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_bridges"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(ROPE_BRIDGES);

        BRIDGE_MIDDLES = SimpleEntrySet.builder(WoodType.class, "log_bridge_middle",
                        () -> BlockInit.OAK_LOG_BRIDGE_MIDDLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Log_Bridge(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_bridges"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_MIDDLES);


        RAIL_BRIDGES = SimpleEntrySet.builder(WoodType.class, "rail_bridge",
                        () -> BlockInit.OAK_RAIL_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Rail_Bridge(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rail_bridges"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(RAIL_BRIDGES);


        BRIDGE_STAIRS = SimpleEntrySet.builder(WoodType.class, "log_bridge_stair",
                        () -> BlockInit.OAK_LOG_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Iron_Stair(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_stairs"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_STAIRS);

        ROPE_STAIRS = SimpleEntrySet.builder(WoodType.class, "rope_bridge_stair",
                        () -> BlockInit.OAK_ROPE_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Iron_Stair(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_stairs"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> BridgesGroup.BRIDGEGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(ROPE_STAIRS);


    }


}
