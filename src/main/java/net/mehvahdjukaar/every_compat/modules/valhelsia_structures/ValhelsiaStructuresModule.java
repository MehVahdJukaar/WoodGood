package net.mehvahdjukaar.every_compat.modules.valhelsia_structures;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.item.ModCreativeModeTabs;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.recipe.TemplateRecipeManager;
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

public class ValhelsiaStructuresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> POSTS;
    public final SimpleEntrySet<WoodType, Block> CUT_POSTS;

    public ValhelsiaStructuresModule(String modId) {
        super(modId, "vs");

        POSTS = SimpleEntrySet.builder(WoodType.class, "post",
                        () -> getModBlock("oak_post"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new PostBlock(() -> w.log))
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> ModCreativeModeTabs.MAIN)
                .defaultRecipe()
                .build();

        this.addEntry(POSTS);

        CUT_POSTS = SimpleEntrySet.builder(WoodType.class, "post", "cut",
                        () -> getModBlock("cut_oak_post"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CutPostBlock(cutPostProperties(w)))
                .addTag(modRes("cut_posts"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cut_posts"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS) //ModCreativeModeTabs.MAIN
                .defaultRecipe()
                .useLootFromBase()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CUT_POSTS);
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
            POSTS.blocks.forEach((w, block) -> {
                ResourceLocation id = block.getRegistryName();

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils::looksLikeSideLogTexture));
                     TextureImage topTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils::looksLikeTopLogTexture))) {

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
