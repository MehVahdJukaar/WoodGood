package net.mehvahdjukaar.every_compat.modules.regions_unexplored;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.misc.UtilityTag.createAndAddCustomTags;

///SUPPORT: v0.5.7+
public abstract class RegionsUnexploredModuleAbstract extends EveryCompatModule {
    public final BlockBehaviour.Properties BRANCH_PROPERTIES = BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.MANGROVE_ROOTS).strength(1.0F, 1.5F).dynamicShape();
    public final BlockBehaviour.Properties SHRUB_PROPERTIES = BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.AZALEA).offsetType(BlockBehaviour.OffsetType.XZ);

    public final SimpleEntrySet<WoodType, Block> branches;
    public final SimpleEntrySet<LeavesType, Block> shrubs;

    public RegionsUnexploredModuleAbstract(String modId) {
        super(modId, "ru");
        Supplier<CreativeModeTab> tab = getModTab("main");

        branches = SimpleEntrySet.builder(WoodType.class, "branch",
                        getModBlock("oak_branch"), () -> VanillaWoodTypes.OAK,
                        this::newBranchBlock
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("branches_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("branches"), Registries.BLOCK)
                .addTag(modRes("branches"), Registries.ITEM)
                .setTab(tab)
                .addRecipe(modRes("oak_branch_from_oak_log"))
                //RECIPE-GENERATED: stick_from_oak_branch
                .build();
        this.addEntry(branches);

        shrubs = SimpleEntrySet.builder(LeavesType.class, "shrub",
                        getModBlock("dark_oak_shrub"),
                        () -> VanillaLeavesTypes.DARK_OAK,
                        this::newShrubBlock
                )
                .addCondition(l -> {
                    boolean log = l.getAssociatedWoodType() != null; //REASON: textures
                    boolean sapling = l.getItemOfThis("sapling") != null; //REASON: recipes
                    return log && sapling;
                })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.BLOCK)
                .addTag(modRes("shrub_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.ITEM)
                .setTab(tab)
                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .addRecipe(modRes("dark_oak_shrub"))
                .copyParentDrop()
                .copyParentTint()
                .build();
        this.addEntry(shrubs);
    }

    public abstract Block newShrubBlock(LeavesType leavesType);

    public abstract Block newBranchBlock(WoodType w);

    @Override
    public void onModSetup() {
        branches.blocks.forEach((woodType, block) -> ComposterBlock.COMPOSTABLES.put(block, 0.3F));
    }

    @Override
    //TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        String recipe = """
                {
                    "type": "minecraft:crafting_shapeless",
                    "category": "misc",
                    "group": "stick",
                    "ingredients": [
                        {
                            "item": "[BRANCH]"
                        }
                    ],
                    "result": {
                    "count": 4,
                    "id": "minecraft:stick"
                    }
                }
                """;

        executor.accept((manager, sink) -> {
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (woodType.isVanilla() || woodType.getNamespace().equals("regions_unexplored")) continue;

                //Tagging the planks as ingredient to get painted_planks
                createAndAddCustomTags(ResourceLocation.withDefaultNamespace("planks"), sink, woodType.planks);
                createAndAddCustomTags(ResourceLocation.parse("forge:planks"), sink, woodType.planks);

                branches.blocks.forEach((wt, block) -> {
                    String newRecipe = recipe.replace("[BRANCH]", Utils.getID(block).toString());

                    sink.addBytes(EveryCompat.res(wt.createPathWith(shortenedId(), "stick_from", "branch")),
                            newRecipe.getBytes(), ResType.RECIPES);

                });
            }

        });

    }

    @Override
    //TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

// Generating branch textures ==========================================================================================
        executor.accept((manager, sink) -> {
            try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/oak_branch_side"));
                 TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/oak_branch_top"));
                 TextureImage branch_block = TextureImage.open(manager, modRes("block/oak_branch"))
            ) {
                Respriter respriterSIDE = Respriter.of(branch_side); // ITEM
                Respriter respriterTOP = Respriter.of(branch_top); // ITEM
                Respriter respriterBlock = Respriter.of(branch_block); // BLOCK

                branches.blocks.forEach((wood, block) -> {
                    try (TextureImage logSide_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage logTop_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                        String resLocITEM = wood.createFullIdWith("", "item", shortenedId(), "", "branch");
                        String resLocBLOCK = wood.createFullIdWith("", "block", shortenedId(), "", "branch");

                        List<Palette> list_logSide = Palette.fromAnimatedImage(logSide_texture);
                        List<Palette> list_logTop = Palette.fromAnimatedImage(logTop_texture);

                        // Block Texture
                        sink.addTextureIfNotPresent(manager, EveryCompat.res(resLocBLOCK), () -> respriterBlock.recolor(list_logSide));
                        // Item Texture
                        sink.addTextureIfNotPresent(manager, EveryCompat.res(resLocITEM), () -> {
                            TextureImage recoloredITEM = respriterSIDE.recolor(list_logSide);
                            try (TextureImage recoloredTOP = respriterTOP.recolor(list_logTop)) {
                                TextureOps.applyOverlay(recoloredITEM, recoloredTOP);
                            }
                            return recoloredITEM;
                        });

                    } catch (IOException e) {
                        EveryCompat.LOGGER.error("Failed to get Log Texture for {} : {}", block, e);
                    }
                });
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to get Branch Item Texture for ", e);
            }

// Generating shrub textures ===========================================================================================
            try (
                    // middle is the bark part of shrub_top's
                    TextureImage shrubTop = TextureImage.open(manager, modRes("block/dark_oak_shrub_top"));
                    TextureImage shrubBottom = TextureImage.open(manager, modRes("block/dark_oak_shrub_bottom"));
                    TextureImage shrubTopMask = TextureImage.open(manager, EveryCompat.res("block/ru/mask_shrub_top"));
                    TextureImage shrubMiddleMask = TextureImage.open(manager, EveryCompat.res("block/ru/mask_shrub_middle"))
            ) {


                shrubs.blocks.forEach((leavesType, block) -> {
                    String shrubPath = leavesType.createPathWith(shortenedId(), "shrub");

                    // Generating textures for shrubs
                    try (TextureImage logTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, Objects.requireNonNull(leavesType.getAssociatedWoodType()).log,
                                    CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage leavesTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, leavesType.leaves,
                                         CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
                    ) {
                        Respriter respriterTop = Respriter.masked(shrubTop, shrubMiddleMask);
                        Respriter respriterBottom = Respriter.of(shrubBottom);

                        List<Palette> logSidePalette = Palette.fromAnimatedImage(logTexture);
                        List<Palette> leavesPalette = Palette.fromAnimatedImage(leavesTexture);

                        // Adding to the resource
                        String resLoc = "block/" + shrubPath;
                        sink.addTextureIfNotPresent(manager, EveryCompat.res(resLoc + "_bottom"), () -> {
                            // Recoloring the shrub's Bottom
                            return respriterBottom.recolor(logSidePalette);
                        });

                        sink.addTextureIfNotPresent(manager, EveryCompat.res(resLoc + "_top"), () -> {
                            // Recoloring the shrub's Top (the leaves part)
                            try (TextureImage recoloredShrubTop = respriterTop.recolor(leavesPalette)) {
                                // Recoloring the shrub's Middle (the bark part)
                                Respriter respriterMiddle = Respriter.masked(recoloredShrubTop, shrubTopMask);
                                return respriterMiddle.recolor(logSidePalette);
                            }
                        });
                    } catch (IOException e) {
                        EveryCompat.LOGGER.error("Failed to get texture for {} : {}", block.toString(), e.getMessage());
                    }
                });
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to open textures for: {}", e.getMessage());
            }

        });
    }
}
