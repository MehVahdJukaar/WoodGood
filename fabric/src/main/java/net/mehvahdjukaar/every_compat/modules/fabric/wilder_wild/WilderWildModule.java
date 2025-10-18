package net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.frozenblock.wilderwild.block.HollowedLogBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.doChildrenExistFor;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.createAndAddCustomTags;

//SUPPORT: v4.0+
public class WilderWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, HollowedLogBlock> hollow_logs;
    public final SimpleEntrySet<WoodType, HollowedLogBlock> stripped_hollow_logs;

    public WilderWildModule(String modId) {
        super(modId, "ww");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        hollow_logs = SimpleEntrySet.builder(WoodType.class, "log", "hollowed",
                        getModBlock("hollowed_oak_log", HollowedLogBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe(getModBlock("hollowed_oak_log").get()))
                )
                .requiresChildren("stripped_log", "wood") //REASON: textures, recipes
                //TEXTURES: stripped_oak_log, log_top
                //REASON: using the vanilla textures instead of generated textures
                .addModelTransform(m -> m.replaceWithTextureFromChild(
                        "wilderwild:block/hollowed_oak_log", "log",
                        CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE
                ))
//-                .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
//-                .addTexture(modRes("block/hollowed_oak_log"))
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(modRes("hollowed_logs"), Registries.BLOCK)
                .addTag(modRes("splits_coconut"), Registries.BLOCK)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(modRes("hollowed_logs"), Registries.ITEM)
                .addTag(modRes("hollowed_logs_that_burn"), Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                .addRecipe(modRes("oak_wood_from_hollowed"))
                //REASON: The top texture is not a standard 16x16. Take a look, you'll see why
                .excludeBlockTypes("terrestria", "(yucca_palm|sakura)")
                .build();
        this.addEntry(hollow_logs);

        stripped_hollow_logs = SimpleEntrySet.builder(WoodType.class, "log", "stripped_hollowed",
                        getModBlock("stripped_hollowed_oak_log", HollowedLogBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe(getModBlock("stripped_hollowed_oak_log").get()))
                )
                .requiresChildren("stripped_log", "stripped_wood") //REASON: textures, recipes
                //TEXTURES: stripped_log, stripped_log_top
                //REASON: using the vanilla textures instead of generated textures
                .addModelTransform(m -> m.replaceWithTextureFromChild(
                        "wilderwild:block/stripped_hollowed_oak_log", "stripped_log",
                        CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE
                ))
//-                .createPaletteFromChild("stripped_log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
//-                .addTexture(modRes("block/stripped_hollowed_oak_log"))
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(modRes("hollowed_logs"), Registries.BLOCK)
                .addTag(modRes("stripped_hollowed_logs"), Registries.BLOCK)
                .addTag(modRes("splits_coconut"), Registries.BLOCK)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(modRes("hollowed_logs"), Registries.ITEM)
                .addTag(modRes("hollowed_logs_that_burn"), Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                .addRecipe(modRes("stripped_oak_wood_from_hollowed"))
                //REASON: The top texture is not a standard 16x16. Take a look, you'll see why
                .excludeBlockTypes("terrestria", "(yucca_palm|sakura)")
                .build();
        this.addEntry(stripped_hollow_logs);

    }

    @Override
    public void onModSetup() {
        super.onModSetup();

        hollow_logs.blocks.forEach((wood, block) -> {
            boolean isStem = Utils.getID(wood.log).toString().contains("stem");
            if (isStem) {
                HollowedLogBlock.registerAxeHollowBehaviorStem(wood.log, hollow_logs.blocks.get(wood));
                if (doChildrenExistFor(wood,"stripped_log"))
                    HollowedLogBlock.registerAxeHollowBehaviorStem(wood.getBlockOfThis("stripped_log"), stripped_hollow_logs.blocks.get(wood));
            }
            else {
                HollowedLogBlock.registerAxeHollowBehavior(wood.log, hollow_logs.blocks.get(wood));
                if (doChildrenExistFor(wood,"stripped_log")) {
                    HollowedLogBlock.registerAxeHollowBehavior(wood.getBlockOfThis("stripped_log"), stripped_hollow_logs.blocks.get(wood));
                }
            }

            if (doChildrenExistFor(wood, stripped_hollow_logs)) // For stripping the logs
                StrippableBlockRegistry.register(block, stripped_hollow_logs.blocks.get(wood));
        });
    }

    @Override
    // RECIPES, TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            hollow_logs.blocks.forEach((wood, block) -> {
                // Variables
                ResourceLocation recipeLoc = ResType.RECIPES.getPath("wilderwild:oak_planks_from_hollowed");
                ResourceLocation newRecipeLoc = EveryCompat.res(wood.getTypeName() + "_planks_from_hollowed");
                ResourceLocation tagRLoc = EveryCompat.res(shortenedId() + "/" + wood.getNamespace() + "/" + "hollowed_" +
                        wood.getTypeName() + "_logs");

// TAGS ================================================================================================
                boolean hasAddedNewTag = createAndAddCustomTags(tagRLoc, sink, block, stripped_hollow_logs.blocks.get(wood));

// RECIPE ==============================================================================================
                try (InputStream recipeStream = manager.getResource(recipeLoc)
                        .orElseThrow(() -> new FileNotFoundException("Failed to get the recipe file @: " + recipeLoc)).open()) {
                    JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                    // Editing the recipe
                    recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject()
                            .addProperty("tag", tagRLoc.toString());

                    recipe.getAsJsonObject("result")
                            .addProperty("item", Utils.getID(wood.planks).toString());

                    // Adding to the resources
                    if (hasAddedNewTag) sink.addJson(newRecipeLoc, recipe, ResType.RECIPES);


                } catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to open the recipe file: ", e);
                }
            });

        });
    }
}