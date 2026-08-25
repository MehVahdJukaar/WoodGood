package net.mehvahdjukaar.every_compat.modules.macaw;

import net.kikoz.mcwbridges.init.BlockInit;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.FENCE;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

/// SUPPORT: v3.1.2+
public abstract class MacawBridgesModuleAbstract extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> bridgePiers;
    public final SimpleEntrySet<WoodType, Block> bridgeMiddles;
    public final SimpleEntrySet<WoodType, Block> ropeBridges;
    public final SimpleEntrySet<WoodType, Block> railBridges;
    public final SimpleEntrySet<WoodType, Block> bridgeStairs;
    public final SimpleEntrySet<WoodType, Block> ropeStairs;

    public MacawBridgesModuleAbstract(String modId) {
        super(modId, "mcb");
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric())
                ? modRes("bridges")
                : modRes(modId);

        bridgePiers = SimpleEntrySet.builder(WoodType.class, "bridge_pier",
                        () -> BlockInit.OAK_BRIDGE_PIER, () -> VanillaWoodTypes.OAK,
                        this::newBridge_Support
    )
                .requiresChildren(FENCE) //REASON: recieps
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_piers"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bridgePiers);

        ropeBridges = SimpleEntrySet.builder(WoodType.class, "bridge", "rope",
                        () -> BlockInit.ROPE_OAK_BRIDGE, () -> VanillaWoodTypes.OAK,
                        this::newBridge_Block_Rope
                )
                .requiresChildren(SLAB) //REASON: recieps
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_bridges"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ropeBridges);

        bridgeMiddles = SimpleEntrySet.builder(WoodType.class, "log_bridge_middle",
                        () -> BlockInit.OAK_LOG_BRIDGE_MIDDLE, () -> VanillaWoodTypes.OAK,
                        this::newLog_Bridge
                )
                .requiresChildren(SLAB, FENCE) //REASON: recieps
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_bridges"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bridgeMiddles);


        railBridges = SimpleEntrySet.builder(WoodType.class, "rail_bridge",
                        () -> BlockInit.OAK_RAIL_BRIDGE, () -> VanillaWoodTypes.OAK,
                        this::newRail_Bridge
                )
                .requiresChildren(SLAB, FENCE) //REASON: recieps
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rail_bridges"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(railBridges);


        bridgeStairs = SimpleEntrySet.builder(WoodType.class, "log_bridge_stair",
                        () -> BlockInit.OAK_LOG_BRIDGE_STAIR, () -> VanillaWoodTypes.OAK,
                        this::newBridge_Stairs
                )
                .requiresFromMap(bridgeMiddles.blocks) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("log_stairs"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bridgeStairs);

        ropeStairs = SimpleEntrySet.builder(WoodType.class, "rope_bridge_stair",
                        () -> BlockInit.OAK_ROPE_BRIDGE_STAIR, () -> VanillaWoodTypes.OAK,
                        this::newBridge_Stairs
                )
                .requiresFromMap(ropeBridges.blocks) //REASON: recipes
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("rope_stairs"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ropeStairs);

    }

    public abstract Block newBridge_Support(WoodType woodType);
    public abstract Block newBridge_Block_Rope(WoodType woodType);
    public abstract Block newLog_Bridge(WoodType woodType);
    public abstract Block newRail_Bridge(WoodType woodType);
    public abstract Block newBridge_Stairs(WoodType woodType);

}
