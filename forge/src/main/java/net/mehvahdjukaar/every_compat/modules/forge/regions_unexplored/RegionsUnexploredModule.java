package net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.regions_unexplored.block.RuBlocks;
import net.regions_unexplored.world.level.block.plant.other.BranchBlock;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

import javax.json.Json;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

// SUPPORT: v0.5.3-FINAL
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> branchs;
    public final SimpleEntrySet<WoodType, Block> shrubs;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");

        branchs = SimpleEntrySet.builder(WoodType.class, "branch",
                        () -> getModBlock("oak_branch"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH.get()))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("branches_can_survive_on"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("branches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("branches"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("oak_branch_from_oak_log"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(branchs);

        shrubs = SimpleEntrySet.builder(WoodType.class, "shrub",
                        () -> getModBlock("dark_oak_shrub"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new ShrubBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_SHRUB.get()))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("shrubs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shrub_can_survive_on"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shrubs"), Registry.ITEM_REGISTRY)
//                .addRecipe(modRes("dark_oak_shrub"))
//                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .build();
        this.addEntry(shrubs);
    }

    @Override
    public void onModSetup() {
        branchs.blocks.forEach((woodType, block) -> ComposterBlock.COMPOSTABLES.put(block, 0.3F));
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        ResourceLocation shrubRecipe = modRes("dark_oak_shrub");
        ResourceLocation sapling_shrubRecipe = modRes("dark_oak_sapling_from_dark_oak_shrub");

        try (InputStream shrubStream = manager.getResource(ResType.RECIPES.getPath(shrubRecipe))
                .orElseThrow(() -> new FileNotFoundException("File not found: " + shrubRecipe)).open();
             InputStream sapling_shrubStream = manager.getResource(ResType.RECIPES.getPath(sapling_shrubRecipe))
                 .orElseThrow(() -> new FileNotFoundException("File not found: " + sapling_shrubRecipe)).open()) {

            JsonObject recipeShrub = RPUtils.deserializeJson(shrubStream);
                JsonObject recipeSapling_shrub = RPUtils.deserializeJson(sapling_shrubStream);

            shrubs.blocks.forEach((wood, block) -> {
                String saplingID = wood.getNamespace() + ":" + wood.getTypeName() + "_sapling";

                // Modifying shrub Recipe
                recipeShrub.getAsJsonObject("key").getAsJsonObject("#")
                        .addProperty("item", saplingID);

                recipeShrub.getAsJsonObject("result")
                        .addProperty("item", Utils.getID(block).toString());

                // Modifying sapling_from_shrub Recipe
                recipeSapling_shrub.getAsJsonArray("ingredients").get(0).getAsJsonObject()
                        .addProperty("item", Utils.getID(block).toString());

                recipeSapling_shrub.getAsJsonObject("result")
                        .addProperty("item", saplingID);

                // Adding to the resource
                String shrubName = wood.getTypeName() + "_shrub";

                handler.dynamicPack.addJson(
                        EveryCompat.res( shortenedId() + "/" + wood.getNamespace() + "/" + shrubName),
                        recipeShrub,
                        ResType.RECIPES);

                handler.dynamicPack.addJson(
                        EveryCompat.res(shortenedId() + "/" + wood.getAppendableId() + "_sapling_from_" + shrubName),
                        recipeSapling_shrub,
                        ResType.RECIPES
                );
            });
        } catch (IOException e) {
            handler.getLogger().error("Failed to open the recipe: " + e);
        }
    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/regions_unexplored/oak_branch_side"));
             TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/regions_unexplored/oak_branch_top"));
             TextureImage branch_block = TextureImage.open(manager, modRes("block/oak_branch"))
            ) {

            branchs.blocks.forEach((wood, block) -> {
                try (TextureImage logSide_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage logTop_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    ResourceLocation resLocITEM = EveryCompat.res("item/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");
                    ResourceLocation resLocBLOCK = EveryCompat.res("block/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");

                    Respriter respriterSIDE = Respriter.of(branch_side); // ITEM
                    Respriter respriterTOP = Respriter.of(branch_top); // ITEM
                    Respriter respriterBlock = Respriter.of(branch_block); // BLOCK

                    // Recoloring ITEM textures
                    TextureImage recoloredITEM = respriterSIDE.recolorWithAnimationOf(logSide_texture);
                    TextureImage recoloredTOP = respriterTOP.recolorWithAnimationOf(logTop_texture);
                    recoloredITEM.applyOverlay(recoloredTOP);

                    // Recoloring BLOCK texture
                    TextureImage recoloredBLOCK = respriterBlock.recolorWithAnimationOf(logSide_texture);

                    // Block Texture
                    handler.dynamicPack.addAndCloseTexture(resLocBLOCK, recoloredBLOCK);

                    // Item Texture
                    handler.dynamicPack.addAndCloseTexture(resLocITEM, recoloredITEM);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get Log Texture for {} : {}", block, e);
                }
            });
        }
        catch (IOException e) {
            handler.getLogger().error("Failed to get Branch Item Texture for " + e);
        }

        try (TextureImage shrubTop = TextureImage.open(manager, modRes("block/dark_oak_shrub_top"));
             TextureImage shrubBottom = TextureImage.open(manager, modRes("block/dark_oak_shrub_bottom"));
             TextureImage leaveMask = TextureImage.open(manager, EveryCompat.res("block/regions_unexplored/dark_oak_shrub_leaves_m"));
             TextureImage logMask = TextureImage.open(manager, EveryCompat.res("block/regions_unexplored/dark_oak_shrub_logs_m"));
            ) {

            shrubs.blocks.forEach((wood, block) -> {

                try (
                     TextureImage leaveTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("leaves"), SpriteHelper.LOOKS_LIKE_LEAF_TEXTURE));
                     TextureImage logTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    Respriter respriterTop = Respriter.masked(shrubTop, leaveMask);
                    Respriter respriterBottom = Respriter.of(shrubBottom);

                    // Recoloring the log top & bottom
                    TextureImage recoloredLog = respriterTop.recolorWithAnimationOf(logTexture);
                    TextureImage recoloredBottom = respriterBottom.recolorWithAnimationOf(logTexture);

                    // Recoloring the leave
                    Respriter respriterleave = Respriter.masked(recoloredLog, logMask);
                    TextureImage recoloredTop = respriterleave.recolorWithAnimationOf(leaveTexture);

                    // Adding to the resource
                    String resLoc = shortenedId() + "/" + wood.getAppendableId() + "_shrub";
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res("block/" + resLoc + "_top"), recoloredTop);
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res("block/" + resLoc + "_bottom"), recoloredBottom);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            handler.getLogger().error("Failed to get textures for " + e);
        }
    }
}
