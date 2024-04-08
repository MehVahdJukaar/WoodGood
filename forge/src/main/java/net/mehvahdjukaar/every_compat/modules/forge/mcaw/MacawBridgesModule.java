package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwbridges.kikoz.init.BlockInit;
import com.mcwbridges.kikoz.init.TabInit;
import com.mcwbridges.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

// SUPPORT: v3.0.0+
public class MacawBridgesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> bridgePiers;
    public final SimpleEntrySet<WoodType, Block> bridgeMiddles;
    public final SimpleEntrySet<WoodType, Block> ropeBridges;
    public final SimpleEntrySet<WoodType, Block> railBridges;
    public final SimpleEntrySet<WoodType, Block> bridgeStairs;
    public final SimpleEntrySet<WoodType, Block> ropeStairs;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");

        bridgePiers = SimpleEntrySet.builder(WoodType.class, "bridge_pier",
                        BlockInit.OAK_BRIDGE_PIER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Support(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_piers"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(bridgePiers);

        ropeBridges = SimpleEntrySet.builder(WoodType.class, "bridge", "rope",
                        BlockInit.ROPE_OAK_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Block_Rope(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_bridges"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(ropeBridges);

        bridgeMiddles = SimpleEntrySet.builder(WoodType.class, "log_bridge_middle",
                        BlockInit.OAK_LOG_BRIDGE_MIDDLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Log_Bridge(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_bridges"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(bridgeMiddles);


        railBridges = SimpleEntrySet.builder(WoodType.class, "rail_bridge",
                        BlockInit.OAK_RAIL_BRIDGE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Rail_Bridge(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rail_bridges"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(railBridges);


        bridgeStairs = SimpleEntrySet.builder(WoodType.class, "log_bridge_stair",
                        BlockInit.OAK_LOG_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_stairs"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(bridgeStairs);

        ropeStairs = SimpleEntrySet.builder(WoodType.class, "rope_bridge_stair",
                        BlockInit.OAK_ROPE_BRIDGE_STAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_stairs"), Registries.BLOCK)
                .setTab(TabInit.BRIDGEITEMGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(ropeStairs);


    }


}
