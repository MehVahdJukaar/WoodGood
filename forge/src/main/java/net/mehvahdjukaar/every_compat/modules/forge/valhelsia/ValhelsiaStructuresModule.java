package net.mehvahdjukaar.every_compat.modules.forge.valhelsia;

import com.mojang.blaze3d.platform.NativeImage;
import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.valhelsia.valhelsia_core.api.common.block.StrippableRotatedPillarBlock;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

//SUPPORT: v1.1.2+
@SuppressWarnings("ALL")
public class ValhelsiaStructuresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> cutStrippedPosts;
    public final SimpleEntrySet<WoodType, Block> cutPosts;
    public final SimpleEntrySet<WoodType, Block> bundledStrippedPosts;
    public final SimpleEntrySet<WoodType, Block> bundledPosts;

    public ValhelsiaStructuresModule(String modId) {
        super(modId, "vs");
        ResourceLocation tab = modRes("main");

        strippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        getModBlock("stripped_oak_post"), () -> VanillaWoodTypes.OAK,
                        woodType -> new PostBlock(postProperties(woodType))
                )
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: manual generation (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK)
                .addTag(modRes("posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedPosts);

        posts = SimpleEntrySet.builder(WoodType.class, "post",
                        getModBlock("oak_post"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StrippablePostBlock(woodType, postProperties(woodType))
                )
                //TEXTURES: manual generation (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK)
                .addTag(modRes("posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(posts);

        cutStrippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut_stripped",
                        getModBlock("cut_stripped_oak_post"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CutPostBlock(cutPostProperties(woodType))
                )
                .requiresFromMap(strippedPosts.blocks) //REASON: recipes
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: manual generation (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cut_posts"), Registries.BLOCK)
                .addTag(modRes("cut_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cutStrippedPosts);

        cutPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut",
                        getModBlock("cut_oak_post"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StrippableCutPostBlock(woodType, cutPostProperties(woodType))
                )
                .requiresFromMap(posts.blocks) //REASON: recipes
                //TEXTURES: manual generation (BELOW)
                .addTag(modRes("cut_posts"), Registries.BLOCK)
                .addTag(modRes("cut_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cutPosts);

        bundledStrippedPosts = SimpleEntrySet.builder(WoodType.class, "posts", "bundled_stripped",
                        getModBlock("bundled_stripped_oak_posts"), () -> VanillaWoodTypes.OAK,
                        woodType -> new RotatedPillarBlock(bundledPostProperties(woodType))
                )
                .requiresFromMap(strippedPosts.blocks) //REASON: recipes
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: manual generation (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bundledStrippedPosts);

        bundledPosts = SimpleEntrySet.builder(WoodType.class, "posts", "bundled",
                        getModBlock("bundled_oak_posts"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StrippableRotatedPillarBlock(() -> bundledStrippedPosts.blocks.get(woodType), bundledPostProperties(woodType))
                )
                .requiresFromMap(posts.blocks) //REASON: recipes
                .requiresFromMap(bundledStrippedPosts.blocks) //REASON: strippable_block
                //TEXTURES: manual generation (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bundledPosts);
    }

    public static BlockBehaviour.Properties postProperties(WoodType woodType) {
        return woodType.copyProperties()
                .mapColor(
                        Objects.nonNull(woodType.getBlockOfThis("stripped_log"))
                                ? Objects.requireNonNull(woodType.getBlockOfThis("stripped_log")).defaultMapColor()
                                : woodType.log.defaultMapColor())
                .strength(2.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties cutPostProperties(WoodType woodType) {
        return woodType.copyProperties()
                .mapColor(
                        (state) -> state.getValue(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y && Objects.nonNull(woodType.getBlockOfThis("stripped_log"))
                                ? Objects.requireNonNull(woodType.getBlockOfThis("stripped_log")).defaultMapColor()
                                : woodType.log.defaultMapColor())
                .strength(2.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties bundledPostProperties(WoodType woodType) {
        return woodType.copyProperties()
                .mapColor(
                        (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                                ? MapColor.WOOD
                                : MapColor.PODZOL
                );
    }

    @Override
    public void onModSetup() {
        TemplateRecipeManager.registerTemplate(ModRecipes.AXE_CRAFTING_SERIALIZER.get(), AxeCraftingRecipeTemplate::new);
        super.onModSetup();
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, sink) -> {
            try {
                //!! oak_posts
                posts.blocks.forEach((w, block) -> {
                    ResourceLocation id = Utils.getID(block);

                    try (TextureImage logTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, w.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage topTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, w.log, CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                        String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/post/oak_post", w, id, "oak");
                        var newTexture = logTexture.makeCopy();

                        sink.addTextureIfNotPresent(manager, newId, () -> newTexture);

                        var newTop = topTexture.makeCopy();
                        CompatSpritesHelper.createSmallLogTopTexture(topTexture, newTop);

                        sink.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate Post texture for for {} : {}", block, e);
                    }
                });

                //!! stripped_oak_posts
                strippedPosts.blocks.forEach((w, block) -> {
                    ResourceLocation id = Utils.getID(block);

                    try (TextureImage logTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage topTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                        String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/post/stripped_oak_post", w, id, "oak");

                        try (TextureImage newTexture = logTexture.makeCopy();
                             TextureImage newTop = topTexture.makeCopy()) {
                            sink.addTextureIfNotPresent(manager, newId, () -> newTexture);

                            CompatSpritesHelper.createSmallLogTopTexture(topTexture, newTop);

                            sink.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);
                        }

                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate Stripped-Post texture for {} : {}", block, e);
                    }
                });
            } catch (Exception ex) {
                EveryCompat.LOGGER.error("Could not generate block texture: ", ex);
            }

            //!! bundled_<type>_posts
            try (TextureImage BPTopInnerMask = TextureImage.open(manager,
                    EveryCompat.res("block/vs/bundledposts_top_inner_m"));
                 TextureImage BPTopOuterMask = TextureImage.open(manager,
                         EveryCompat.res("block/vs/bundledposts_top_outer_m"));

                 TextureImage logInnerMask = TextureImage.open(manager,
                         EveryCompat.res("block/vs/log_top_inner_m"));
                 TextureImage logOuterMask = TextureImage.open(manager,
                         EveryCompat.res("block/vs/log_top_outer_m"))
            ) {

                bundledPosts.blocks.forEach((w, block) -> {
                    String newPath = "block/" + shortenedId() + "/" + w.getNamespace() + "/bundled_posts/bundled_"
                            + w.getTypeName() + "_posts";

                    createTexture(newPath, w.log, logInnerMask, logOuterMask, BPTopInnerMask, BPTopOuterMask,
                            modRes("block/bundled_posts/bundled_oak_posts"),
                            modRes("block/bundled_posts/bundled_oak_posts_top"),
                            sink, manager, block);
                });

                bundledStrippedPosts.blocks.forEach((w, block) -> {
                    String newPath = "block/" + shortenedId() + "/" + w.getNamespace() + "/bundled_posts/bundled_stripped_"
                            + w.getTypeName() + "_posts";

                    createTexture(newPath, w.getBlockOfThis("stripped_log"), logInnerMask, logOuterMask,
                            BPTopInnerMask, BPTopOuterMask,
                            modRes("block/bundled_posts/bundled_stripped_oak_posts"),
                            modRes("block/bundled_posts/bundled_stripped_oak_posts_top"),
                            sink, manager, block);
                });
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to open bundled_posts texture: ", e);
            }

        });
    }

    private void createTexture(String newPath, Block getLogBlock, TextureImage logInnerMask, TextureImage logOuterMask,
                               TextureImage BPTopInnerMask, TextureImage BPTopOuterMask,
                               ResourceLocation getLogSide, ResourceLocation getLogTop,
                               ResourceSink sink, ResourceManager manager, Block block
    ) {
        try (TextureImage logSide_texture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, getLogBlock, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
             TextureImage logTop_texture = TextureImage.open(manager,
                     RPUtils.findFirstBlockTextureLocation(manager, getLogBlock, CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE));
             TextureImage TextureSide = TextureImage.open(manager, getLogSide);
             TextureImage TextureTop = TextureImage.open(manager, getLogTop)
        ) {

// Side texture ================================================================================================
            {
                McMetaFile metaSide = logSide_texture.getMcMeta();

                TextureImage sideImage; // Creating a 16x16 texture from an animated log's texture
                if (Objects.nonNull(logSide_texture.getMcMeta())) {
                    NativeImage standardSize = new NativeImage(16, 16, false);
                    standardSize.copyFrom(logSide_texture.getImage());
                    sideImage = TextureImage.of(standardSize);
                } else {
                    sideImage = logSide_texture;
                }

                List<Palette> targetSide = Palette.fromAnimatedImage(sideImage);

                Respriter respriterSide = Respriter.of(TextureSide);

                // Recoloring

                // Adding to the Resource
                sink.addTextureIfNotPresent(manager, newPath, () ->
                        respriterSide.recolorWithAnimation(targetSide, metaSide)
                );
            }

// Top texture =================================================================================================
            {
                McMetaFile metaTop = logTop_texture.getMcMeta();

                TextureImage topImage; // Creating a 16x16 texture from an animated log's texture
                if (Objects.nonNull(logTop_texture.getMcMeta())) {
                    NativeImage standardSize = new NativeImage(16, 16, false);
                    standardSize.copyFrom(logTop_texture.getImage());
                    topImage = TextureImage.of(standardSize);
                } else {
                    topImage = logTop_texture;
                }

                List<Palette> targetTopInner = Palette.fromAnimatedImage(topImage, logOuterMask, 0);
                List<Palette> targetTopOuter = Palette.fromAnimatedImage(topImage, logInnerMask, 0);

                // Inner
                Respriter innerTopResp = Respriter.masked(TextureTop, BPTopOuterMask);
                TextureImage recoloredInner = innerTopResp.recolorWithAnimation(targetTopInner, metaTop);

                // Outer
                Respriter outerTopResp = Respriter.masked(recoloredInner, BPTopInnerMask);
                TextureImage recoloredOuter;

                // stripped_log_top's outer|edge must have 3 color palettes
                while (targetTopOuter.get(0).size() < 3) targetTopOuter.get(0).increaseInner();

                recoloredOuter = outerTopResp.recolorWithAnimation(targetTopOuter, metaTop);

                // Adding to the Resource
                sink.addTextureIfNotPresent(manager, newPath + "_top", () -> recoloredOuter);
                recoloredOuter.close();
            }

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate the texture for {} : {}", block, e);
        }
    }


    public class StrippablePostBlock extends PostBlock {
        public final WoodType woodType;

        public StrippablePostBlock(WoodType woodType, Properties properties) {
            super(properties);
            this.woodType = woodType;
        }

        @Override
        public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(this);

            if (!context.getItemInHand().canPerformAction(toolAction) || Objects.requireNonNull(location).getPath().contains("stripped")) {
                return null;
            }

            if (toolAction == ToolActions.AXE_STRIP && Objects.nonNull(strippedPosts.blocks.get(woodType))) {
                return BuiltInRegistries.BLOCK.get(Utils.getID(strippedPosts.blocks.get(woodType))).defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(ATTACHED, state.getValue(ATTACHED))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }

            return null;
        }
    }

    public class StrippableCutPostBlock extends CutPostBlock {
        public final WoodType woodType;

        public StrippableCutPostBlock(WoodType woodType, Properties properties) {
            super(properties);
            this.woodType = woodType;
        }

        @Override
        public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(this);

            if (!context.getItemInHand().canPerformAction(toolAction) || Objects.requireNonNull(location).getPath().contains("stripped")) {
                return null;
            }

            if (toolAction == ToolActions.AXE_STRIP && Objects.nonNull(cutStrippedPosts.blocks.get(woodType))) {
                return Objects.requireNonNull(BuiltInRegistries.BLOCK.get(Utils.getID(cutStrippedPosts.blocks.get(woodType)))).defaultBlockState()
                        .setValue(FACING, state.getValue(FACING))
                        .setValue(ATTACHED, state.getValue(ATTACHED))
                        .setValue(PARTS, state.getValue(PARTS))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }

            return null;
        }
    }

}
