package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwbridges.kikoz.MacawsBridges;
import com.mcwbridges.kikoz.init.BlockInit;
import com.mcwbridges.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
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
        var tab = MacawsBridges.BridgeItemGroup;

        BRIDGE_PIERS = SimpleEntrySet.builder(WoodType.class,"bridge_pier",
                        BlockInit.OAK_BRIDGE_PIER, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Bridge_Support(WoodGood.copySafe(w.planks).strength(0.5F, 2.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_PIERS);

        ROPE_BRIDGES = SimpleEntrySet.builder(WoodType.class,"bridge", "rope",
                        BlockInit.ROPE_OAK_BRIDGE, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Bridge_Block_Rope(WoodGood.copySafe(w.planks).strength(0.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(ROPE_BRIDGES);

        BRIDGE_MIDDLES = SimpleEntrySet.builder(WoodType.class,"log_bridge_middle",
                        BlockInit.OAK_LOG_BRIDGE_MIDDLE, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Log_Bridge(WoodGood.copySafe(w.planks).strength(0.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_MIDDLES);


        RAIL_BRIDGES = SimpleEntrySet.builder(WoodType.class,"rail_bridge",
                        BlockInit.OAK_RAIL_BRIDGE, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Rail_Bridge(WoodGood.copySafe(w.planks).noOcclusion().strength(0.8F, 3)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(RAIL_BRIDGES);


        BRIDGE_STAIRS = SimpleEntrySet.builder(WoodType.class,"log_bridge_stair",
                        BlockInit.OAK_LOG_BRIDGE_STAIR, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Bridge_Stairs(WoodGood.copySafe(w.planks).strength(0.8F, 2.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BRIDGE_STAIRS);

        ROPE_STAIRS = SimpleEntrySet.builder(WoodType.class,"rope_bridge_stair",
                        BlockInit.OAK_ROPE_BRIDGE_STAIR, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new Bridge_Stairs(WoodGood.copySafe(w.planks).strength(0.8F, 2.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(ROPE_STAIRS);


    }


}
