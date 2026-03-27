package net.mehvahdjukaar.every_compat.modules.neoforge.curiosities;

import com.syndicatemc.curiosities.common.block.VerticalConnectingPillarBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.UtilityRecipe;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Consumer;

//SUPPORT: v0.1.0+
public class CuriositiesModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> fancied_planks;

    public CuriositiesModule(String modId) {
        super(modId, "cur");
        ResourceKey<CreativeModeTab> tabPath = CreativeModeTabs.BUILDING_BLOCKS;

        fancied_planks = SimpleEntrySet.builder(WoodType.class, "planks", "fancied",
                        getModBlock("fancied_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new VerticalConnectingPillarBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/fancied_oak_planks_normal"))
                .addTexture(modRes("block/fancied_oak_planks_top"))
                .addTexture(modRes("block/fancied_oak_planks_top_connected"))
                .addTexture(modRes("block/fancied_oak_planks_both_connected"))
                .addTexture(modRes("block/fancied_oak_planks_bottom_connected"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tabPath))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .defaultRecipe()
                .addRecipe(modRes("fancied_oak_planks_from_oak_planks_sawing"))
                .build();
        this.addEntry(fancied_planks);

    }

    @Override
    // Recipes
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        if (PlatHelper.isModLoaded("woodworks")) {
            executor.accept((manager, sink) -> {
                for (Map.Entry<WoodType, Block> entry : fancied_planks.blocks.entrySet()) {
                    WoodType woodType = entry.getKey();
                    Block block = entry.getValue();

                    ResourceLocation sawingLoc = modRes("fancied_oak_planks_from_oak_logs_sawing");

                    var newSawingLoc = sawingLoc.withPrefix(shortenedId() + "/" + woodType.getNamespace() + "/" )
                            .getPath().replace("oak", woodType.getTypeName());

                    String newTagIng = UtilityTag.getATagOrCreateANew("logs", "stems", woodType, sink, manager).toString();

                    UtilityRecipe.createRecipeWithTag(sawingLoc, EveryCompat.res(newSawingLoc),
                            "minecraft:oak_logs", newTagIng, block, sink, manager);
                }
            });
        }
    }
}