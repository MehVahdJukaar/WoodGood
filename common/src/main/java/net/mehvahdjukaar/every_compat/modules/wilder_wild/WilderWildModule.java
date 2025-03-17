package net.mehvahdjukaar.every_compat.modules.wilder_wild;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.frozenblock.wilderwild.block.HollowedLogBlock;
import net.frozenblock.wilderwild.entity.ai.TermiteManager;
import net.frozenblock.wilderwild.tag.WWBlockTags;
import net.frozenblock.wilderwild.tag.WWItemTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.createAndAddCustomTags;
import static net.mehvahdjukaar.every_compat.common_classes.Utilities.doChildrenExistFor;

//SUPPORT: v3.0.4+
//NOTE: Could be supported in NEOFORGE via Sinytra Connector
public class WilderWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, HollowedLogBlock> hollow_log;
    public final SimpleEntrySet<WoodType, HollowedLogBlock> stripped_hollow_log;

    public WilderWildModule(String modId) {
        super(modId, "ww");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        hollow_log = SimpleEntrySet.builder(WoodType.class, "log", "hollowed",
                        getModBlock("hollowed_oak_log", HollowedLogBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
                .addTexture(modRes("block/hollowed_oak_log"))
                //TEXTURES: stripped_hollowed_log, log_top
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setTabKey(tab)
                .addTag(modRes("hollowed_logs"), Registries.ITEM)
                .addTag(modRes("hollowed_logs_that_burn"), Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(modRes("hollowed_logs"), Registries.BLOCK)
                .addTag(modRes("splits_coconut"), Registries.BLOCK)
                .addRecipe(modRes("oak_wood_from_hollowed"))
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                .excludeBlockTypes("terrestria", "(yucca_palm|sakura)")
                .build();
        this.addEntry(hollow_log);

        stripped_hollow_log = SimpleEntrySet.builder(WoodType.class, "log", "stripped_hollowed",
                        getModBlock("stripped_hollowed_oak_log", HollowedLogBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe((w.getBlockOfThis("stripped_log") != null ) ? Objects.requireNonNull(w.getBlockOfThis("stripped_log")) : Blocks.STRIPPED_OAK_LOG))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .createPaletteFromChild("stripped_log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
                //TEXTURES: stripped_log, stripped_log_top
                .addTexture(modRes("block/stripped_hollowed_oak_log"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(WWBlockTags.HOLLOWED_LOGS, Registries.BLOCK)
                .addTag(WWBlockTags.STRIPPED_HOLLOWED_LOGS, Registries.BLOCK)
                .addTag(WWBlockTags.SPLITS_COCONUT, Registries.BLOCK)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(WWItemTags.HOLLOWED_LOGS, Registries.ITEM)
                .addTag(WWItemTags.HOLLOWED_LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                .addRecipe(modRes("stripped_oak_wood_from_hollowed"))
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                .excludeBlockTypes("terrestria", "(yucca_palm|sakura)")
                .build();
        this.addEntry(stripped_hollow_log);

    }

    @Override
    public void onModSetup() {
        super.onModSetup();

        hollow_log.blocks.forEach((wood, block) -> {
            if (doChildrenExistFor(wood, stripped_hollow_log)) // For stripping the logs
                StrippableBlockRegistry.register(block, stripped_hollow_log.blocks.get(wood));

            boolean isStem = Utils.getID(wood.log).toString().contains("stem");
            if (isStem) {
                HollowedLogBlock.registerAxeHollowBehaviorStem(wood.log, hollow_log.blocks.get(wood));
                if (doChildrenExistFor(wood, "stripped_log"))
                    HollowedLogBlock.registerAxeHollowBehaviorStem(wood.getBlockOfThis("stripped_log"), stripped_hollow_log.blocks.get(wood));
            }
            else {
                HollowedLogBlock.registerAxeHollowBehavior(wood.log, hollow_log.blocks.get(wood));
                if (doChildrenExistFor(wood, "stripped_log")) {
                    HollowedLogBlock.registerAxeHollowBehavior(wood.getBlockOfThis("stripped_log"), stripped_hollow_log.blocks.get(wood));
                    TermiteManager.Termite.addDegradable(block, stripped_hollow_log.blocks.get(wood));
                }
            }
            if (doChildrenExistFor(wood,  "wood", "stripped_wood"))
                TermiteManager.Termite.addDegradable(wood.getBlockOfThis("wood"), wood.getBlockOfThis("stripped_wood"));
        });
    }

    @Override
    // Recipes & Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        hollow_log.blocks.forEach((wood, block) -> {
                // Variables
            String pathRecipe = "oak_planks_from_hollowed";
            ResourceLocation recipeLoc = ResType.RECIPES.getPath( "wilderwild:" + pathRecipe);
            ResourceLocation newRecipeLoc = EveryCompat.res(pathRecipe.replace("oak", wood.getTypeName()));
            ResourceLocation tagRLoc = EveryCompat.res(shortenedId() + "/" + wood.getNamespace() + "/" + "hollowed_" +
                    wood.getTypeName() + "_logs");

// TAGS ================================================================================================
            boolean hasAddedNewTag = createAndAddCustomTags(tagRLoc, handler, block, stripped_hollow_log.blocks.get(wood));

// RECIPE ==============================================================================================
            try (InputStream recipeStream = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject()
                        .addProperty("tag", tagRLoc.toString());

                recipe.getAsJsonObject("result")
                        .addProperty("id", Utils.getID(wood.planks).toString());

                // Adding to the resources
                if (hasAddedNewTag) handler.dynamicPack.addJson(newRecipeLoc, recipe, ResType.RECIPES);


            } catch (IOException e) {
                handler.getLogger().error("Failed to generate the recipe file for {} : {} ", Utils.getID(block), e);
            }
        });

    }
}