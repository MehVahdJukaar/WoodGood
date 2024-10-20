package net.mehvahdjukaar.every_compat.modules.forge.valhelsia;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.valhelsia.valhelsia_core.api.common.block.StrippableRotatedPillarBlock;

import java.io.IOException;
import java.util.List;

//SUPPORT: v1.1.0+
public class ValhelsiaStructuresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> cutPosts;
    public final SimpleEntrySet<WoodType, Block> cutStrippedPosts;
    public final SimpleEntrySet<WoodType, Block> bundledStrippedPosts;
    public final SimpleEntrySet<WoodType, Block> bundledPosts;

    public ValhelsiaStructuresModule(String modId) {
        super(modId, "vs");
        var tab = modRes("main");

        posts = SimpleEntrySet.builder(WoodType.class, "post",
                        getModBlock("oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PostBlock(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("posts"), Registries.BLOCK)
                .addTag(modRes("posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(posts);

        strippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        getModBlock("stripped_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            Block stripped = w.getBlockOfThis("stripped_log");
                            if (stripped == null) return null;
                            return new PostBlock(Utils.copyPropertySafe(w.log));
                        })
                .addTag(modRes("stripped_posts"), Registries.BLOCK)
                .addTag(modRes("stripped_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedPosts);

        cutPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut",
                        getModBlock("cut_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CutPostBlock(cutPostProperties(w)))
                .addTag(modRes("cut_posts"), Registries.BLOCK)
                .addTag(modRes("cut_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cutPosts);

        cutStrippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut_stripped",
                        getModBlock("cut_stripped_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            Block stripped = w.getBlockOfThis("stripped_log");
                            if (stripped == null) return null;
                            return new CutPostBlock(cutPostProperties(w));
                        })
                .addTag(modRes("cut_stripped_posts"), Registries.BLOCK)
                .addTag(modRes("cut_stripped_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cutStrippedPosts);

        bundledStrippedPosts = SimpleEntrySet.builder(WoodType.class, "posts", "bundled_stripped",
                        getModBlock("bundled_stripped_oak_posts"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            Block stripped = w.getBlockOfThis("stripped_log");
                            if (stripped == null) return null;
                            return new RotatedPillarBlock(bundledPostProperties(w));
                        })
                .addTag(modRes("cut_stripped_posts"), Registries.BLOCK)
                .addTag(modRes("cut_stripped_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bundledStrippedPosts);

        bundledPosts = SimpleEntrySet.builder(WoodType.class, "posts", "bundled",
                        getModBlock("bundled_oak_posts"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            if (bundledStrippedPosts.blocks.get(w) == null) return null;
                            return new StrippableRotatedPillarBlock(() -> bundledStrippedPosts.blocks.get(w), bundledPostProperties(w));
                        })
                .addTag(modRes("cut_stripped_posts"), Registries.BLOCK)
                .addTag(modRes("cut_stripped_posts"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bundledPosts);
    }

    public static BlockBehaviour.Properties cutPostProperties(WoodType woodType) {
        return woodType.copyProperties()
                .mapColor(
                        (state) -> state.getValue(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y
                                ? woodType.planks.defaultMapColor()
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
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        try {
            posts.blocks.forEach((w, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage topTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/post/oak_post", w, id, "oak");
                    var newTexture = logTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Post block texture for for {} : {}", block, e);

                }

            });

            posts.blocks.forEach((w, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage topTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/post/stripped_oak_post", w, id, "oak");

                    var newTexture = logTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Stripped Post block texture for for {} : {}", block, e);

                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Table block texture : ", ex);
        }

        // bundled_<type>_posts
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
                        handler, manager, block);
            });

            bundledStrippedPosts.blocks.forEach((w, block) -> {
                    String newPath = "block/" + shortenedId() + "/" + w.getNamespace() + "/bundled_posts/bundled_stripped_"
                            + w.getTypeName() + "_posts";

                    createTexture(newPath, w.getBlockOfThis("stripped_log"), logInnerMask, logOuterMask,
                            BPTopInnerMask, BPTopOuterMask,
                            modRes("block/bundled_posts/bundled_stripped_oak_posts"),
                            modRes("block/bundled_posts/bundled_stripped_oak_posts_top"),
                            handler, manager, block);
            });
        } catch (Exception e) {
            handler.getLogger().error("Failed to open bundled_posts texture: ", e);
        }
    }

    private void createTexture(String newPath, Block getLogBlock, TextureImage logInnerMask, TextureImage logOuterMask,
                               TextureImage BPTopInnerMask, TextureImage BPTopOuterMask,
                               ResourceLocation getLogSide, ResourceLocation getLogTop,
                               ClientDynamicResourcesHandler handler, ResourceManager manager, Block block
                               ) {
        try (TextureImage logSide_texture = TextureImage.open(manager,
                 RPUtils.findFirstBlockTextureLocation(manager, getLogBlock, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
             TextureImage logTop_texture = TextureImage.open(manager,
                 RPUtils.findFirstBlockTextureLocation(manager, getLogBlock, SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE));
             TextureImage TextureSide = TextureImage.open(manager, getLogSide);
             TextureImage TextureTop = TextureImage.open(manager, getLogTop);
        ) {

// Side texture ================================================================================================
            {
                AnimationMetadataSection metaSide = logSide_texture.getMetadata();
                List<Palette> targetSide = Palette.fromAnimatedImage(logSide_texture);

                Respriter respriterSide = Respriter.of(TextureSide);

                // Recoloring
                TextureImage recoloredSIDE = respriterSide.recolorWithAnimation(targetSide, metaSide);

                // Adding to the Resource
                handler.dynamicPack.addAndCloseTexture(EveryCompat.res(newPath), recoloredSIDE);
            }

// Top texture =================================================================================================
            {
                AnimationMetadataSection metaTop = logTop_texture.getMetadata();

                List<Palette> targetTopInner = Palette.fromAnimatedImage(logTop_texture, logOuterMask, 0);
                List<Palette> targetTopOuter = Palette.fromAnimatedImage(logTop_texture, logInnerMask, 0);

                // Inner
                Respriter innerTopResp = Respriter.masked(TextureTop, BPTopOuterMask);
                TextureImage recoloredwithInner = innerTopResp.recolorWithAnimation(targetTopInner, metaTop);

                // Outer
                Respriter outerTopResp = Respriter.masked(recoloredwithInner, BPTopInnerMask);
                TextureImage recoloredwithOuter;

                if (targetTopOuter.size() < 3)
                    recoloredwithOuter = outerTopResp.recolorWithAnimationOf(logTop_texture);
                else// stripped_log_top's outer|edge must have 3 color palettes
                    recoloredwithOuter = outerTopResp.recolorWithAnimation(targetTopOuter, metaTop);

                // Adding to the Resource
                handler.dynamicPack.addAndCloseTexture(EveryCompat.res(newPath + "_top"), recoloredwithOuter);
            }

        } catch (Exception e) {
            handler.getLogger().error("Failed to generate the texture for {} : {}", block, e);
        }
    }

    private void createTopTexture(TextureImage original, TextureImage newImage) {
        original.forEachFramePixel((i, x, y) -> {
            //TODO: use ImageTransformer here instead
            int localX = x - original.getFrameStartX(i);
            int localY = y - original.getFrameStartX(i);
            if (localX >= 5 && localX <= 10 && localY >= 5 && localY <= 10) {
                newImage.getImage().setPixelRGBA(x - 3, y - 3, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 14 && localY > 0 && localY <= 7) {
                newImage.getImage().setPixelRGBA(x - 6, y, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localY >= 14 && localX > 0 && localX <= 7) {
                newImage.getImage().setPixelRGBA(x, y - 6, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localX >= 14 && localY >= 14) {
                newImage.getImage().setPixelRGBA(x - 6, y - 6, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 10 || localY >= 10) {
                newImage.getImage().setPixelRGBA(x, y, 0);
            }
        });
    }

}
