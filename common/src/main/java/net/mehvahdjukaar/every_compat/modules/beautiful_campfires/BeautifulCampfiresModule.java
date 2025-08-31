package net.mehvahdjukaar.every_compat.modules.beautiful_campfires;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.getATagOrCreateANew;

//SUPPORT: v1.0.0+
//NOTE: The Project ID is 1085950
public class BeautifulCampfiresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, CampfireBlock> campfires;
    public final SimpleEntrySet<WoodType, CampfireBlock> soul_campfires;

    public BeautifulCampfiresModule(String modId) {
        super(modId, "bc");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        campfires = SimpleEntrySet.builder(WoodType.class, "campfire",
                        getModBlock("acacia_campfire", CampfireBlock.class), () -> VanillaWoodTypes.ACACIA,
                        w -> new CampfireBlock(true, 1, copyProperties(15))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                .addTextureM(modRes("item/acacia_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTextureM(modRes("item/acacia_soul_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: Manully created below
                .build();
        this.addEntry(campfires);

        soul_campfires = SimpleEntrySet.builder(WoodType.class, "soul_campfire",
                        getModBlock("acacia_soul_campfire", CampfireBlock.class), () -> VanillaWoodTypes.ACACIA,
                        w -> new CampfireBlock(true, 2, copyProperties(10))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                //TEXTURES: acacia_soul_campfire above
                .addTextureM(modRes("block/acacia_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTextureM(modRes("block/acacia_soul_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .addTag(BlockTags.PIGLIN_REPELLENTS, Registries.BLOCK)
                .setTabKey(tab)
                //RECIPES: Manully created below
                .build();
        this.addEntry(soul_campfires);

    }

    public BlockBehaviour.Properties copyProperties(int pLightValue) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.PODZOL)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .lightLevel(litBlockEmission(pLightValue))
                .noOcclusion()
                .ignitedByLava();
    }

    private static @NotNull ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) -> {
            ResourceLocation campfireLoc = modRes("acacia_campfire");
            ResourceLocation soulCampfireLoc = modRes("acacia_soul_campfire");

            campfires.blocks.forEach((wood, block) ->{
                createRecipe("campfire", wood, block, campfireLoc, sink, manager);
                createRecipe("soul_campfire", wood, soul_campfires.blocks.get(wood), soulCampfireLoc,
                        sink, manager);
            });

        });
    }


    public void createRecipe(String recipeName, WoodType woodType, Block output, ResourceLocation recipeLoc,
                             ResourceSink sink, ResourceManager manager) {

        try (InputStream recipeStream = manager.getResource(ResType.RECIPES.getPath(recipeLoc))
                .orElseThrow(() -> new FileNotFoundException("File not found @ " + recipeLoc)).open()) {

            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // Editing the recipe
            recipe.getAsJsonObject("key").getAsJsonObject("L")
                    .addProperty("tag", getATagOrCreateANew("logs", "caps", woodType, sink, manager).toString());

            recipe.getAsJsonObject("result").addProperty("item", Utils.getID(output).toString());

            // Adding to resources
            sink.addJson(
                    EveryCompat.res(shortenedId() +"/"+ woodType.getAppendableId() +"_"+ recipeName),
                    recipe,
                    ResType.RECIPES
            );

        }
        catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to generate the {} recipe for {} : {}", recipeName, woodType.getId(), e);
        }

    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept(this::makeCampfireTextures);
    }

    private void makeCampfireTextures(ResourceManager manager, ResourceSink sink) {
        String campfirePath = "block/acacia_campfire_log";
        ResourceLocation campfireImage = modRes(campfirePath);
        ResourceLocation targetLogMask = EveryCompat.res("block/bc/campfire_log_m"); // Focus on the log part
        ResourceLocation targetPlankMask = EveryCompat.res("block/bc/campfire_plank_m"); // Focus on the plank part

        try (TextureImage textureImage = TextureImage.open(manager, campfireImage);
             TextureImage targetLogImage = TextureImage.open(manager, targetLogMask);
             TextureImage targetPlankImage = TextureImage.open(manager, targetPlankMask)
        ) {
            Respriter respriterLog = Respriter.masked(textureImage, targetLogImage);

            campfires.blocks.forEach((wood, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (
                        TextureImage plankTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.planks));
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    String newPath = BlockTypeResTransformer.replaceTypeNoNamespace(campfirePath, wood, id, "acacia");

                    // Adding to the resource
                    sink.addTextureIfNotPresent(manager, newPath, () -> {
                        // Recoloring the plank part
                        try (TextureImage recoloredLog = respriterLog.recolorWithAnimationOf(logTexture)) {
                            Respriter respriterPlank = Respriter.masked(recoloredLog, targetPlankImage);
                            return respriterPlank.recolorWithAnimationOf(plankTexture);
                        }
                    });

                } catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to open log/plank texture file: ", e);
                }
            });
        } catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to open texture file: ", e);
        }
    }
}