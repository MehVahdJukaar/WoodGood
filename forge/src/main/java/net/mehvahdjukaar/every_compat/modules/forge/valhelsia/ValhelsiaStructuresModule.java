package net.mehvahdjukaar.every_compat.modules.forge.valhelsia;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.item.ModCreativeModeTabs;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

// SUPPORT: v1.0.4
public class ValhelsiaStructuresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> cutPosts;
    public final SimpleEntrySet<WoodType, Block> cutStrippedPosts;

    public ValhelsiaStructuresModule(String modId) {
        super(modId, "vs");

        posts = SimpleEntrySet.builder(WoodType.class, "post",
                        () -> getModBlock("oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PostBlock(Utils.getID(w.log), Utils.copyPropertySafe(w.log)))
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> ModCreativeModeTabs.MAIN)
                .defaultRecipe()
                //.addRecipe(modRes("bundled_oak_posts"))
                .build();

        this.addEntry(posts);

        strippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        () -> getModBlock("stripped_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            Block stripped = w.getBlockOfThis("stripped_log");
                            if (stripped == null) return null;
                            return new PostBlock(Utils.getID(w.log), Utils.copyPropertySafe(w.log));
                        })
                .addTag(modRes("stripped_posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("stripped_posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> ModCreativeModeTabs.MAIN)
                .defaultRecipe()
                //.addRecipe(modRes("bundled_stripped_oak_posts"))
                .build();

        this.addEntry(strippedPosts);

        cutPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut",
                        () -> getModBlock("cut_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CutPostBlock(cutPostProperties(w)))
                .addTag(modRes("cut_posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cut_posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS) //ModCreativeModeTabs.MAIN
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(cutPosts);

        cutStrippedPosts = SimpleEntrySet.builder(WoodType.class, "post", "cut_stripped",
                        () -> getModBlock("cut_stripped_oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            Block stripped = w.getBlockOfThis("stripped_log");
                            if (stripped == null) return null;
                            return new CutPostBlock(cutPostProperties(w));
                        })
                .addTag(modRes("cut_stripped_posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cut_stripped_posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS) //ModCreativeModeTabs.MAIN
                .defaultRecipe()
                .copyParentDrop()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(cutStrippedPosts);
    }

    public static BlockBehaviour.Properties cutPostProperties(WoodType woodType) {
        return BlockBehaviour.Properties.of(woodType.material,
                        (state) -> state.getValue(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y ?
                                woodType.planks.defaultBlockState().getMaterial().getColor() :
                                woodType.log.defaultBlockState().getMaterial().getColor())
                .strength(2.0F).sound(SoundType.WOOD).noOcclusion();
    }

    @Override
    public void onModSetup() {
        TemplateRecipeManager.registerTemplate(ModRecipes.AXE_CRAFTING_SERIALIZER.get(), AxeCraftingRecipeTemplate::new);
        super.onModSetup();
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        try {
            posts.blocks.forEach((w, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage topTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

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
                        RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteUtils.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage topTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, w.getBlockOfThis("stripped_log"), SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

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
    }


    private void createTopTexture(TextureImage original, TextureImage newImage) {
        original.forEachFrame((i, x, y) -> {
            int localX = x - original.getFrameX(i);
            int localY = y - original.getFrameY(i);
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
