package net.mehvahdjukaar.every_compat.modules.beautiful_campfires;

import com.arcanc.bc.content.BlockDatabase;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
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
import java.util.function.ToIntFunction;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.getATagOrCreateANew;

//SUPPORT: v1.0.2+
public class BeautifulCampfiresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, CampfireBlock> campfires;
    public final SimpleEntrySet<WoodType, CampfireBlock> soul_campfires;

    public BeautifulCampfiresModule(String modId) {
        super(modId, "bc");
        var tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        campfires = SimpleEntrySet.builder(WoodType.class, "campfire",
                        () -> BlockDatabase.CAMPFIRE_ACACIA, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CampfireBlock(true, 1, copyProperties(15))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                .addTextureM(modRes("item/acacia_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTextureM(modRes("item/acacia_soul_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(campfires);

        soul_campfires = SimpleEntrySet.builder(WoodType.class, "soul_campfire",
                        () -> BlockDatabase.SOUL_CAMPFIRE_ACACIA, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CampfireBlock(true, 2, copyProperties(10))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                //TEXTURE: using acacia_soul_campfire above
                .createPaletteFromChild("log")
                .addTextureM(modRes("block/acacia_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"))
                .addTextureM(modRes("block/acacia_soul_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .addTag(BlockTags.PIGLIN_REPELLENTS, Registries.BLOCK)
                .setTabKey(tab)
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
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);
        ResourceLocation campfireLoc = modRes("acacia_campfire");
        ResourceLocation soulCampfireLoc = modRes("acacia_soul_campfire");

        campfires.blocks.forEach((wood, block) ->{
            createRecipe("campfire", wood, block, campfireLoc, handler, manager);
            createRecipe("soul_campfire", wood, soul_campfires.blocks.get(wood), soulCampfireLoc,
                    handler, manager);
        });

    }

    public void createRecipe(String recipeName, WoodType woodType, Block output, ResourceLocation recipeLoc,
                             ServerDynamicResourcesHandler handler, ResourceManager manager) {

        try (InputStream recipeStream = manager.getResource(ResType.RECIPES.getPath(recipeLoc))
                .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {

            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // Editing the recipe
            recipe.getAsJsonObject("key").getAsJsonObject("L")
                    .addProperty("tag", getATagOrCreateANew("logs", "caps", woodType, handler, manager).toString());

            recipe.getAsJsonObject("result").addProperty("item", Utils.getID(output).toString());

            // Adding to resources
            handler.dynamicPack.addJson(
                    EveryCompat.res(shortenedId() +"/"+ woodType.getAppendableId() +"_"+ recipeName),
                    recipe,
                    ResType.RECIPES
            );

        }
        catch (IOException e) {
            handler.getLogger().error("Failed to generate the recipe @ {} : {}", recipeLoc, e);
        }

    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        String campfirePath = "block/acacia_campfire_log";
        ResourceLocation campfireImage = modRes(campfirePath);
        ResourceLocation targetLogMask = EveryCompat.res("block/bc/campfire_log_m"); // Focus on the log part
        ResourceLocation targetPlankMask = EveryCompat.res("block/bc/campfire_plank_m"); // Focus on the plank part

        try (TextureImage textureImage = TextureImage.open(manager, campfireImage);
             TextureImage targetLogImage = TextureImage.open(manager, targetLogMask);
             TextureImage targetPlankImage = TextureImage.open(manager, targetPlankMask)
        ) {
            campfires.blocks.forEach((wood, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (
                        TextureImage plankTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.planks));
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.log))
                ) {
                    ResourceLocation newResLoc = EveryCompat.res(BlockTypeResTransformer.replaceTypeNoNamespace(campfirePath, wood, id, "acacia"));

                    // Recoloring the log part
                    Respriter respriterLog = Respriter.masked(textureImage, targetLogImage);

                    TextureImage recoloredLog = respriterLog.recolorWithAnimationOf(logTexture);

                    // Recoloring the plank part
                    Respriter respriterPlank = Respriter.masked(recoloredLog, targetPlankImage);

                    TextureImage finishedImage = respriterPlank.recolorWithAnimationOf(plankTexture);

                    // Adding to the resource
                    handler.dynamicPack.addAndCloseTexture(newResLoc, finishedImage);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to open log/plank texture file: ", e);
                }
            });
        } catch (IOException e) {
            handler.getLogger().error("Failed to open texture file: ", e);
        }

    }
}