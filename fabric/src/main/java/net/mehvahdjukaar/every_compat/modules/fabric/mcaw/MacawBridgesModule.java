package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwbridges.MacawsBridges;
import net.kikoz.mcwbridges.init.BlockInit;
import net.kikoz.mcwbridges.objects.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.FENCE;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

// SUPPORT: v3.0.0+
public class MacawBridgesModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> bridgePiers,
            bridgeMiddles,
            ropeBridges,
            railBridges,
            bridgeStairs,
            ropeStairs;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");
        ResourceKey<CreativeModeTab> tab = MacawsBridges.BRIDGEGROUP;

        bridgePiers = SimpleEntrySet.builder(WoodType.class, "bridge_pier",
                        () -> BlockInit.OAK_BRIDGE_PIER, () -> VanillaWoodTypes.OAK,
                        w -> new Bridge_Support(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(FENCE) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_piers"), Registries.BLOCK)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(bridgePiers);

        ropeBridges = SimpleEntrySet.builder(WoodType.class, "bridge", "rope",
                        () -> BlockInit.ROPE_OAK_BRIDGE, () -> VanillaWoodTypes.OAK,
                        w -> new Bridge_Block_Rope(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_bridges"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(ropeBridges);

        bridgeMiddles = SimpleEntrySet.builder(WoodType.class, "log_bridge_middle",
                        () -> BlockInit.OAK_LOG_BRIDGE_MIDDLE, () -> VanillaWoodTypes.OAK,
                        w -> new Log_Bridge(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(SLAB, FENCE) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_bridges"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(bridgeMiddles);


        railBridges = SimpleEntrySet.builder(WoodType.class, "rail_bridge",
                        () -> BlockInit.OAK_RAIL_BRIDGE, () -> VanillaWoodTypes.OAK,
                        w -> new Rail_Bridge(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB, FENCE) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rail_bridges"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(railBridges);


        bridgeStairs = SimpleEntrySet.builder(WoodType.class, "log_bridge_stair",
                        () -> BlockInit.OAK_LOG_BRIDGE_STAIR, () -> VanillaWoodTypes.OAK,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(bridgeMiddles.blocks) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_stairs"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(bridgeStairs);

        ropeStairs = SimpleEntrySet.builder(WoodType.class, "rope_bridge_stair",
                        () -> BlockInit.OAK_ROPE_BRIDGE_STAIR, () -> VanillaWoodTypes.OAK,
                        w -> new Bridge_Stairs(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(ropeBridges.blocks) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_stairs"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(modRes("oak_rope_bridge_stair_recycle"))
                .build();
        this.addEntry(ropeStairs);


    }


}
