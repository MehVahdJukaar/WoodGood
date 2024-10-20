package net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ColoringUtils;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.regions_unexplored.block.RuBlocks;
import net.regions_unexplored.item.tab.RuTabs;
import net.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

// SUPPORT: v0.5.5+
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
            .addRecipe(modRes("oak_branch_from_oak_log"))
            .setTabKey(tab)
            .build();
        this.addEntry(branchs);

        shrubs = SimpleEntrySet.builder(LeavesType.class, "shrub",
                        getModBlock("dark_oak_shrub"),
                        () -> LeavesTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        l -> new ShrubBlock(Utils.copyPropertySafe(l.leaves).pushReaction(PushReaction.DESTROY)
                                .ignitedByLava().noCollission().instabreak().sound(SoundType.AZALEA)
                                .offsetType(BlockBehaviour.OffsetType.XZ))
                )
                .addCondition(l -> {
                    boolean log = l.getWoodType().log != null; // for textures
                    boolean leave = l.leaves != null; // for textures
                    boolean sapling = l.getItemOfThis("sapling") != null; // for recipes
                    return log && leave && sapling;
                })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.BLOCK)
                .addTag(modRes("shrub_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.ITEM)
                .addRecipe(modRes("dark_oak_shrub"))
                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .addTexture(EveryCompat.res("block/dark_oak_shrub_top"))
                .copyParentDrop()
                .setTabKey(tab)
                .build();
        this.addEntry(shrubs);
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        for (Map.Entry<LeavesType, Block> entry : shrubs.blocks.entrySet()) {
            LeavesType type = entry.getKey();
            Block block = entry.getValue();
            event.register((stack, tintIndex) -> {
                if (tintIndex > 0) return 0xFFFFFFFF;
                return event.getColor(new ItemStack(type.leaves), tintIndex);
            }, block);
        }
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : shrubs.blocks.entrySet()) {
            LeavesType type = entry.getKey();
            Block b = entry.getValue();
            event.register((blockState, tintGetter, pos, index) ->
                    event.getColor(type.leaves.defaultBlockState(), tintGetter, pos, index), b);
        }
    }

    @Override
    public void onModSetup() {
        branchs.blocks.forEach((woodType, block) -> ComposterBlock.COMPOSTABLES.put(block, 0.3F));
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

                    // Recoloring the log middle & bottom
                    TextureImage recoloredMiddle = respriterMiddle.recolorWithAnimationOf(logTexture);
                    TextureImage recoloredBottom = respriterBottom.recolorWithAnimationOf(logTexture);

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
