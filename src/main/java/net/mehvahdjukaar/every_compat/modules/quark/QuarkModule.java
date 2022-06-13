package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.math.colors.HCLColor;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.content.building.block.VariantBookshelfBlock;
import vazkii.quark.content.building.block.VariantLadderBlock;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.client.render.be.VariantChestRenderer;
import vazkii.quark.content.building.module.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class QuarkModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final SimpleEntrySet<WoodType, Block> POSTS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_POSTS;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_PLANKS;
    public final SimpleEntrySet<WoodType, Block> LADDERS;
    public final SimpleEntrySet<WoodType, Block> CHESTS;
    public final SimpleEntrySet<LeavesType, Block> HEDGES;
    public static BlockEntityType<? extends ChestBlockEntity> CHEST_TILE;

    public QuarkModule(String modId) {
        super(modId, "q");

        BOOKSHELVES = QuarkSimpleEntrySet.builder("bookshelf",
                        VariantBookshelvesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("acacia_bookshelf")),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        (w, m) -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(), m, w.canBurn()))
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/acacia_bookshelf"))
                .addTextureM(WoodGood.res("block/acacia_bookshelf"), WoodGood.res("block/acacia_bookshelf_m"))
                .setPalette(this::bookshelfPalette)
                .build();

        this.addEntry(BOOKSHELVES);

        POSTS = QuarkSimpleEntrySet.builder("post",
                        VariantBookshelvesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_post")),
                        () -> WoodType.OAK_WOOD_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            return fence == null ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/", w.canBurn());
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(POSTS);

        STRIPPED_POSTS = QuarkSimpleEntrySet.builder("post", "stripped",
                        VariantBookshelvesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("stripped_oak_post")),
                        () -> WoodType.OAK_WOOD_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum")) return null;
                            if (w.getTypeName().contains("stripped")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            Block stripped = w.getBlockOfThis("stripped_log");
                            return (fence == null || stripped == null) ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/stripped_", w.canBurn());
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/stripped_oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_POSTS);

        VERTICAL_PLANKS = QuarkSimpleEntrySet.builder("planks", "vertical",
                        VerticalPlanksModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("vertical_oak_planks")),
                        () -> WoodType.OAK_WOOD_TYPE,
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
                            return new QuarkBlock(name, m, CreativeModeTab.TAB_BUILDING_BLOCKS, BlockBehaviour.Properties.copy(w.planks));
                        })
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addRecipe(modRes("building/crafting/vertplanks/vertical_oak_planks"))
                .build();

        this.addEntry(VERTICAL_PLANKS);

        LADDERS = QuarkSimpleEntrySet.builder("ladder",
                        VariantLaddersModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("spruce_ladder")),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new VariantLadderBlock(name, m, w.canBurn());
                        })
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("ladders"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("ladders"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/spruce_ladder"))
                .addTexture(WoodGood.res("block/spruce_ladder"))
                .build();

        this.addEntry(LADDERS);

        CHESTS = QuarkSimpleEntrySet.builder("chest",
                        VariantChestsModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_chest")),
                        () -> WoodType.OAK_WOOD_TYPE,
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatChestBlock(w, name, m, () -> CHEST_TILE, BlockBehaviour.Properties.copy(w.planks));
                        })
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/chests/oak_chest"))
                .addCustomItem((w, b, p) -> new CompatChestItem(b, p))
                .build();

        this.addEntry(CHESTS);


        HEDGES = QuarkSimpleEntrySet.builder("hedge",
                        HedgesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_hedge")),
                        () -> LeavesType.OAK_LEAVES_TYPE,
                        (l, m) -> {
                            if (l.woodType != null) {
                                Block fence = l.woodType.getBlockOfThis("fence");
                                if (fence != null) {
                                    String name = this.makeBlockId(l, "hedge");
                                    return new CompatHedgeBlock(m, name, fence, l.leaves);
                                }
                            }
                            return null;
                        })
                .addTag(modRes("hedges"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("hedges"), Registry.ITEM_REGISTRY)
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addRecipe(modRes("building/crafting/oak_hedge"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HEDGES);
    }


    public Pair<List<Palette>, AnimationMetadataSection> bookshelfPalette(BlockType w, ResourceManager m) {
        try (TextureImage plankTexture = TextureImage.open(m,
                RPUtils.findFirstBlockTextureLocation(m, ((WoodType) w).planks))) {

            List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
            targetPalette.forEach(p -> {
                var l0 = p.getDarkest();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.remove(l0);
            });
            return Pair.of(targetPalette, plankTexture.getMetadata());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
        }
    }

    @Override
    public void onModSetup() {
        POSTS.blocks.forEach((w, post) -> {
            Block stripped = STRIPPED_POSTS.blocks.get(w);
            if (stripped != null) ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        });
    }

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        //HAAAACK
        //removes stuff from autoreglib since it's too late to let it register these and we are registering them manually
        Map<String, ?> ARLModData;
        try {
            Field f = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f.setAccessible(true);
            ARLModData = (Map<String, ?>) f.get(null);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to setup Wood Good Quark Module");
            return;
        }

        super.registerWoodBlocks(registry, woodTypes);

        ARLModData.remove(WoodGood.MOD_ID);
    }

    @Override
    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {

        CHEST_TILE = BlockEntityType.Builder.of(CompatChestBlockTile::new,
                CHESTS.blocks.values().toArray(Block[]::new)).build(null);
        registry.register(CHEST_TILE.setRegistryName(WoodGood.res(this.shortenedId() + "_chest")));
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        HEDGES.blocks.forEach((l, h) -> {
            ItemColors colors = event.getItemColors();
            ItemStack leafStack = new ItemStack(l.leaves);
            colors.register((stack, tintIndex) -> colors.getColor(leafStack, tintIndex), h.asItem());
        });
    }

    @Override
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
            CHESTS.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
        }
    }


    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        super.registerEntityRenderers(event);
        event.registerBlockEntityRenderer(CHEST_TILE, VariantChestRenderer::new);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage normal = TextureImage.open(manager, modRes("model/chest/oak/normal"));
             TextureImage normal_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_normal_o"));
             TextureImage left = TextureImage.open(manager, modRes("model/chest/oak/left"));
             TextureImage left_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_left_o"));
             TextureImage right = TextureImage.open(manager, modRes("model/chest/oak/right"));
             TextureImage right_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_right_o"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            CHESTS.blocks.forEach((wood, block) -> {

                CompatChestBlock b = (CompatChestBlock) block;

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : targetPalette) {
                        var d1 = p.getDarkest();
                        p.remove(d1);
                        var d2 = p.getDarkest();
                        p.remove(d2);
                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    addQuarkTexture(handler, manager, b.getChestTexturePath() + "normal", () -> {
                        var img = respriterNormal.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                        img.applyOverlayOnExisting(respriterNormalO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));
                        return img;
                    });

                    addQuarkTexture(handler, manager, b.getChestTexturePath() + "left", () -> {
                        var img = respriterLeft.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                        img.applyOverlayOnExisting(respriterLeftO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));
                        return img;
                    });

                    addQuarkTexture(handler, manager, b.getChestTexturePath() + "right", () -> {
                        var img = respriterRight.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                        img.applyOverlayOnExisting(respriterRightO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));
                        return img;
                    });

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }

    private void addQuarkTexture(ClientDynamicResourcesHandler handler, ResourceManager manager,
                                 String relativePath, Supplier<TextureImage> textureSupplier) {

        ResourceLocation res = new ResourceLocation("quark", relativePath);
        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
            TextureImage textureImage = null;
            try {
                textureImage = textureSupplier.get();
            } catch (Exception e) {
                handler.getLogger().error("Failed to generate texture {}: {}", res, e);
            }
            if (textureImage == null) {
                handler.getLogger().warn("Could not generate texture {}", res);
            } else {
                handler.dynamicPack.addAndCloseTexture(res, textureImage);
            }
        }
    }

}
