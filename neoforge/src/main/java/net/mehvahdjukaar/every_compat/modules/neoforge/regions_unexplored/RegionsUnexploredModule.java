package net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.regions_unexplored.block.RuBlocks;
import net.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.createAndAddCustomTags;

// SUPPORT: v0.5.6+
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> branchs;
    public final SimpleEntrySet<LeavesType, Block> shrubs;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");
        var tab = modRes("ru_main");

        branchs = SimpleEntrySet.builder(WoodType.class, "branch",
            getModBlock("oak_branch"), () -> WoodTypeRegistry.OAK_TYPE,
            w -> new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH.get()), "branch")
        )
            .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
            .addTag(modRes("branches_can_survive_on"), Registries.BLOCK)
            .addTag(modRes("branches"), Registries.BLOCK)
            .addTag(modRes("branches"), Registries.ITEM)
            .setTabKey(tab)
            .addRecipe(modRes("oak_branch_from_oak_log"))
            .build();
        this.addEntry(branchs);

        shrubs = SimpleEntrySet.builder(LeavesType.class, "shrub",
                        getModBlock("dark_oak_shrub"),
                        () -> LeavesTypeRegistry.getValue(VanillaWoods.DARK_OAK),
                        l -> new ShrubBlock(Utils.copyPropertySafe(l.leaves).pushReaction(PushReaction.DESTROY)
                                .ignitedByLava().noCollission().instabreak().sound(SoundType.AZALEA)
                                .offsetType(BlockBehaviour.OffsetType.XZ))
                )
                .addCondition(l -> {
                    boolean log = l.getWoodType() != null; //REASON: textures
                    boolean sapling = l.getItemOfThis("sapling") != null; //REASON: recipes
                    return log && sapling;
                })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.BLOCK)
                .addTag(modRes("shrub_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.ITEM)
                .addTexture(EveryCompat.res("block/dark_oak_shrub_top"))
                .setTabKey(tab)
                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .addRecipe(modRes("dark_oak_shrub"))
                .copyParentDrop()
                .copyParentTint()
                .build();
        this.addEntry(shrubs);
    }

    @Override
    public void onModSetup() {
        branchs.blocks.forEach((woodType, block) -> ComposterBlock.COMPOSTABLES.put(block, 0.3F));
    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        for (WoodType woodType : WoodTypeRegistry.getTypes()) {
            if (woodType.isVanilla() || woodType.getNamespace().equals("regions_unexplored")) continue;

            //Tagging the planks as ingredient to get painted_planks
            createAndAddCustomTags(new ResourceLocation("planks"), handler, woodType.planks);
            createAndAddCustomTags(new ResourceLocation("forge:planks"), handler, woodType.planks);
        }

    }

    @Override
    // Textures & Models
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

// Generating branch textures ==========================================================================================
        try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/oak_branch_side"));
             TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/oak_branch_top"));
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

                    List<Palette> list_logSide = Palette.fromAnimatedImage(logSide_texture);
                    List<Palette> list_logTop = Palette.fromAnimatedImage(logTop_texture);

                    // Recoloring ITEM textures
                    TextureImage recoloredITEM = respriterSIDE.recolor(list_logSide);
                    TextureImage recoloredTOP = respriterTOP.recolor(list_logTop);
                    recoloredITEM.applyOverlay(recoloredTOP);

                    // Recoloring BLOCK texture
                    TextureImage recoloredBLOCK = respriterBlock.recolor(list_logSide);

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
            handler.getLogger().error("Failed to get Branch Item Texture for ", e);
        }

// Generating shrub textures ===========================================================================================
        try (
            // middle is where the log color is located below the leave via dark_oak_shrub_top
             TextureImage shrubMiddle = TextureImage.open(manager,
                     EveryCompat.res("block/regions_unexplored/dark_oak_shrub_middle"));
             TextureImage shrubBottom = TextureImage.open(manager, modRes("block/dark_oak_shrub_bottom"))
            ) {

            shrubs.blocks.forEach((leavesType, block) -> {
                // Modifying BLOCK MODEL
                String shrubPath = shortenedId() + "/" + leavesType.getAppendableId() + "_shrub";

                String crossModel = """
                    {
                        "parent": "minecraft:block/cross",
                        "render_type": "cutout",
                        "textures": {
                            "particle": "[shrub_top]",
                            "cross_tint": "[shrub_top]",
                            "cross": "[shrub_middle]"
                        },
                        "elements": [
                            {   "from": [ 0.8, 0, 8 ],
                                "to": [ 15.2, 16, 8 ],
                                "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
                                "shade": false,
                                "faces": {
                                    "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" },
                                    "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" }
                                }
                            },
                            {   "from": [ 8, 0, 0.8 ],
                                "to": [ 8, 16, 15.2 ],
                                "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
                                "shade": false,
                                "faces": {
                                    "west": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" },
                                    "east": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross" }
                                }
                            },
                            {   "from": [ 0.8, 0, 8 ],
                                "to": [ 15.2, 16, 8 ],
                                "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
                                "shade": false,
                                "faces": {
                                    "north": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross_tint", "tintindex": 0 },
                                    "south": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross_tint", "tintindex": 0 }
                                }
                            },
                            {   "from": [ 8, 0, 0.8 ],
                                "to": [ 8, 16, 15.2 ],
                                "rotation": { "origin": [ 8, 8, 8 ], "axis": "y", "angle": 45, "rescale": true },
                                "shade": false,
                                "faces": {
                                    "west": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross_tint", "tintindex": 0 },
                                    "east": { "uv": [ 0, 0, 16, 16 ], "texture": "#cross_tint", "tintindex": 0 }
                                }
                            }
                        ]
                    }
                    """;


                String blockID = "everycomp:block/" + shrubPath;

                String newModel = crossModel.replace("[shrub_middle]", blockID + "_middle")
                        .replace("[shrub_top]", blockID + "_top");

                handler.dynamicPack.addBytes(EveryCompat.res(shrubPath), newModel.getBytes(), ResType.BLOCK_MODELS);

                // Modifying ITEM MODEL ================================================================================
                var itemPath = ResType.ITEM_MODELS.getPath(EveryCompat.res(shrubPath));

                try (InputStream modelItemStream = manager.getResource(itemPath)
                        .orElseThrow(FileNotFoundException::new).open()) {

                    JsonObject modelItem = RPUtils.deserializeJson(modelItemStream);

                    modelItem.getAsJsonObject("textures")
                            .addProperty("layer0", blockID + "_top");
                    modelItem.getAsJsonObject("textures")
                            .addProperty("layer1", blockID + "_middle");

                    handler.dynamicPack.addJson(EveryCompat.res(shrubPath), modelItem, ResType.ITEM_MODELS);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to open the item model file via {} : {}", itemPath, e);
                }

                // Generating textures for shrubs ======================================================================
                try (TextureImage logTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, leavesType.getWoodType().log,
                                     SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    Respriter respriterBottom = Respriter.of(shrubBottom);

                    Respriter respriterMiddle= Respriter.of(shrubMiddle);

                    List<Palette> list_logSide = Palette.fromAnimatedImage(logTexture);

                    // Recoloring the log middle & bottom
                    TextureImage recoloredMiddle = respriterMiddle.recolor(list_logSide);
                    TextureImage recoloredBottom = respriterBottom.recolor(list_logSide);

                    // Adding to the resource
                    String resLoc = "block/" + shrubPath;
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res(resLoc + "_middle"), recoloredMiddle);
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res(resLoc + "_bottom"), recoloredBottom);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get texture for {} : {}", block.toString(), e);
                }
            });
        } catch (IOException e) {
            handler.getLogger().error("Failed to open textures for: ", e);
        }
    }
}
