package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwbridges.init.BlockInit;
import net.kikoz.mcwbridges.init.BridgesGroup;
import net.kikoz.mcwbridges.objects.*;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

// SUPPORT: v3.0.0+
public class MacawBridgesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BRIDGE_PIERS;
    public final SimpleEntrySet<WoodType, Block> BRIDGE_MIDDLES;
    public final SimpleEntrySet<WoodType, Block> ROPE_BRIDGES;
    public final SimpleEntrySet<WoodType, Block> RAIL_BRIDGES;
    public final SimpleEntrySet<WoodType, Block> BRIDGE_STAIRS;
    public final SimpleEntrySet<WoodType, Block> ROPE_STAIRS;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");
        var tab = BridgesGroup.BRIDGEGROUP;

        BRIDGE_PIERS = SimpleEntrySet.builder(WoodType.class, "bridge_pier",
                        () -> BlockInit.OAK_BRIDGE_PIER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Support(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_piers"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_PIERS);

        ROPE_BRIDGES = SimpleEntrySet.builder(WoodType.class, "bridge", "rope",
                        () -> BlockInit.ROPE_OAK_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Block_Rope(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("rope_bridges"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ROPE_BRIDGES);

        BRIDGE_MIDDLES = SimpleEntrySet.builder(WoodType.class, "log_bridge_middle",
                        () -> BlockInit.OAK_LOG_BRIDGE_MIDDLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Log_Bridge(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("log_bridges"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_MIDDLES);


        RAIL_BRIDGES = SimpleEntrySet.builder(WoodType.class, "rail_bridge",
                        () -> BlockInit.OAK_RAIL_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Rail_Bridge(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("rail_bridges"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(RAIL_BRIDGES);


        BRIDGE_STAIRS = SimpleEntrySet.builder(WoodType.class, "log_bridge_stair",
                        () -> BlockInit.OAK_LOG_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("log_stairs"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_STAIRS);

        ROPE_STAIRS = SimpleEntrySet.builder(WoodType.class, "rope_bridge_stair",
                        () -> BlockInit.OAK_ROPE_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("rope_stairs"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ROPE_STAIRS);


    }


}
