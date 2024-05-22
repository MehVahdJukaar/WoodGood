package net.mehvahdjukaar.every_compat.modules.forge.variants;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.xanthian.variantvanillablocks.block.*;
import net.xanthian.variantvanillablocks.utils.ModCreativeModTabs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

//SUPPORT: v1.3.6
public class VariantVanillaBlocks extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> Barrel;
    public final SimpleEntrySet<WoodType, Block> Beehive;
    public final SimpleEntrySet<WoodType, Block> Bookshelves;
    public final SimpleEntrySet<WoodType, Block> Cartography;
    public final SimpleEntrySet<WoodType, Block> Chests;
    public final SimpleEntrySet<WoodType, Block> ChiseledBookshelves;
    public final SimpleEntrySet<WoodType, Block> Composters;
    public final SimpleEntrySet<WoodType, Block> CraftingTable;
    public final SimpleEntrySet<WoodType, Block> FletchingTable;
    public final SimpleEntrySet<WoodType, Block> Grindstones;
    public final SimpleEntrySet<WoodType, Block> Lectern;
    public final SimpleEntrySet<WoodType, Block> SmithingTable;
    public final SimpleEntrySet<WoodType, Block> Smoker;

    public static BlockEntityType<ChestBlockEntity> Chest_tile;

    public VariantVanillaBlocks(String modID) {
        super(modID, "vvb");
        var tab = ModCreativeModTabs.VVB_TAB;

        Barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        Barrels.OAK_BARREL, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("barrels"), Registries.BLOCK)
                .addTag(Tags.Blocks.BARRELS, Registries.BLOCK)
                .addTag(Tags.Blocks.BARRELS_WOODEN, Registries.BLOCK)
                .addTag(modRes("barrels"), Registries.ITEM)
                .addTag(Tags.Items.BARRELS, Registries.ITEM)
                .addTag(Tags.Items.BARRELS_WOODEN, Registries.ITEM)
                .addTexture(modRes("block/oak_barrel_bottom"))
                .addTextureM(modRes("block/oak_barrel_side"),
                        EveryCompat.res("block/vanilla_barrel_side_m"))
                .addTextureM(modRes("block/oak_barrel_top"),
                        EveryCompat.res("block/vanilla_barrel_top_m"))
                .addTexture(modRes("block/oak_barrel_top_open"))
                .addTile(() -> BlockEntityType.BARREL)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(Barrel);

        Beehive = SimpleEntrySet.builder(WoodType.class, "beehive",
                        Beehives.SPRUCE_BEEHIVE,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BeehiveBlock(Utils.copyPropertySafe(Blocks.BEEHIVE))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .addTag(modRes("beehives"), Registries.BLOCK)
                .addTag(modRes("beehives"), Registries.ITEM)
                .addTexture(modRes("block/spruce_beehive_end"))
                .addTexture(modRes("block/spruce_beehive_front"))
                .addTextureM(modRes("block/spruce_beehive_front_honey"),
                        EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(modRes("block/spruce_beehive_side"),
                        EveryCompat.res("block/spruce_beehive_side_m"))
                .addTile(() -> BlockEntityType.BEEHIVE)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(Beehive);

        Bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        net.xanthian.variantvanillablocks.block.Bookshelves.ACACIA_BOOKSHELF,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.ITEM)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTextureM(modRes("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(Bookshelves);

        Cartography = SimpleEntrySet.builder(WoodType.class, "cartography_table",
                        CartographyTables.OAK_CARTOGRAPHY_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CartographyTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cartography_tables"), Registries.BLOCK)
                .addTag(modRes("cartography_tables"), Registries.ITEM)
                .addTexture(modRes("block/oak_cartography_table_side1"))
                .addTexture(modRes("block/oak_cartography_table_side2"))
                .addTexture(modRes("block/oak_cartography_table_side3"))
                .addTexture(modRes("block/oak_cartography_table_top"))
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(Cartography);
/*
*/
        Chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        net.xanthian.variantvanillablocks.block.Chests.ACACIA_CHEST,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CompatChestBlock(Utils.copyPropertySafe(w.planks))
                )
                .addCustomItem((w, block, properties) -> new ChestItem(block, new Item.Properties()))
                .addTile(() -> BlockEntityType.CHEST)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(Chests);

        {
            ChiseledBookshelves = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                            net.xanthian.variantvanillablocks.block.ChiseledBookshelves.ACACIA_CHISELED_BOOKSHELF,
                            () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                            w -> new ChiseledBookShelfBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(modRes("chiseled_bookshelves"), Registries.BLOCK)
                    .addTag(modRes("chiseled_bookshelves"), Registries.ITEM)
                    .addTexture(modRes("block/acacia_chiseled_bookshelf_empty"))
                    .addTextureM(modRes("block/acacia_chiseled_bookshelf_occupied"),
                            EveryCompat.res("block/acacia_chiseled_bookshelf_occupied_m"))
                    .addTexture(modRes("block/acacia_chiseled_bookshelf_side"))
                    .addTexture(modRes("block/acacia_chiseled_bookshelf_top"))
                    .addTile(() -> BlockEntityType.CHISELED_BOOKSHELF)
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(ChiseledBookshelves);

            Composters = SimpleEntrySet.builder(WoodType.class, "composter",
                            net.xanthian.variantvanillablocks.block.Composters.OAK_COMPOSTER,
                            () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new ComposterBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(modRes("composters"), Registries.BLOCK)
                    .addTag(modRes("composters"), Registries.ITEM)
                    .addTexture(modRes("block/oak_composter_bottom"))
                    .addTexture(modRes("block/oak_composter_side"))
                    .addTexture(modRes("block/oak_composter_top"))
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(Composters);

            CraftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                            CraftingTables.SPRUCE_CRAFTING_TABLE,
                            () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                            w -> new CraftingTableBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(modRes("crafting_tables"), Registries.BLOCK)
                    .addTag(modRes("crafting_tables"), Registries.ITEM)
                    .addTexture(EveryCompat.res("block/spruce_crafting_table_top"))
                    .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"),
                            EveryCompat.res("block/vct/spruce_crafting_table_front_m"))
                    .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"),
                            EveryCompat.res("block/vct/spruce_crafting_table_side_m"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(CraftingTable);

            FletchingTable = SimpleEntrySet.builder(WoodType.class, "fletching_table",
                            FletchingTables.OAK_FLETCHING_TABLE,
                            () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new FletchingTableBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                    .addTag(modRes("fletching_tables"), Registries.BLOCK)
                    .addTag(modRes("fletching_tables"), Registries.ITEM)
                    .addTextureM(modRes("block/oak_fletching_table_front"),
                            EveryCompat.res("block/vanilla_fletching_table_front_m"))
                    .addTextureM(modRes("block/oak_fletching_table_side"),
                            EveryCompat.res("block/vanilla_fletching_table_side_m"))
                    .addTextureM(modRes("block/oak_fletching_table_top"),
                            EveryCompat.res("block/vanilla_fletching_table_top_m"))
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(FletchingTable);

            Grindstones = SimpleEntrySet.builder(WoodType.class, "grindstone",
                            net.xanthian.variantvanillablocks.block.Grindstones.OAK_GRINDSTONE,
                            () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new GrindstoneBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                    .addTag(modRes("grindstones"), Registries.BLOCK)
                    .addTag(modRes("grindstones"), Registries.ITEM)
                    .addTexture(modRes("block/oak_grindstone_pivot"))
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(Grindstones);

            Lectern = SimpleEntrySet.builder(WoodType.class, "lectern",
                            Lecterns.ACACIA_LECTERN,
                            () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                            w -> new LecternBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(modRes("lecterns"), Registries.BLOCK)
                    .addTag(modRes("lecterns"), Registries.ITEM)
                    .addTextureM(modRes("block/acacia_lectern_base"),
                            EveryCompat.res("block/vanilla_lectern_base_m"))
                    .addTextureM(modRes("block/acacia_lectern_front"),
                            EveryCompat.res("block/vanilla_lectern_front_m"))
                    .addTexture(modRes("block/acacia_lectern_sides"))
                    .addTexture(modRes("block/acacia_lectern_top"))
                    .addTile(() -> BlockEntityType.LECTERN)
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(Lectern);

            SmithingTable = SimpleEntrySet.builder(WoodType.class, "smithing_table",
                            SmithingTables.OAK_SMITHING_TABLE,
                            () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new SmithingTableBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(modRes("smithing_tables"), Registries.BLOCK)
                    .addTag(modRes("smithing_tables"), Registries.ITEM)
                    .addTextureM(modRes("block/oak_smithing_table_bottom"),
                            EveryCompat.res("block/vanilla_smithing_table_bottom_m"))
                    .addTextureM(modRes("block/oak_smithing_table_front"),
                            EveryCompat.res("block/vanilla_smithing_table_front_m"))
                    .addTextureM(modRes("block/oak_smithing_table_side"),
                            EveryCompat.res("block/vanilla_smithing_table_side_m"))
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(SmithingTable);

            Smoker = SimpleEntrySet.builder(WoodType.class, "smoker",
                            Smokers.ACACIA_SMOKER,
                            () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                            w -> new SmokerBlock(Utils.copyPropertySafe(w.planks))
                    )
                    .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                    .addTag(modRes("smokers"), Registries.BLOCK)
                    .addTag(modRes("smokers"), Registries.ITEM)
                    .addTextureM(modRes("block/acacia_smoker_front"),
                            EveryCompat.res("block/vanilla_smoker_front_m"))
                    .addTextureM(modRes("block/acacia_smoker_front_on"),
                            EveryCompat.res("block/vanilla_smoker_front_on_m"))
                    .addTextureM(modRes("block/acacia_smoker_side"),
                            EveryCompat.res("block/vanilla_smoker_side_m"))
                    .addTextureM(modRes("block/acacia_smoker_top"),
                            EveryCompat.res("block/vanilla_smoker_x_m"))
                    .addTextureM(modRes("block/acacia_smoker_bottom"),
                            EveryCompat.res("block/vanilla_smoker_x_m"))
                    .addTile(() -> BlockEntityType.SMOKER)
                    .defaultRecipe()
                    .setTab(tab)
                    .build();
            this.addEntry(Smoker);

        }
    }

    protected final ResourceLocation POI_ID = EveryCompat.res("vvb_beehive");

    private final Supplier<PoiType> compatBeeHivePOI = RegHelper.registerPOI(POI_ID,
            () -> new PoiType(getBeehives(), 1, 1));

    private Set<BlockState> getBeehives() {
        var set = new ImmutableSet.Builder<BlockState>();
        Beehive.blocks.values().forEach(b -> set.addAll(b.getStateDefinition().getPossibleStates()));
        return set.build();
    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(PoiTypeTags.BEE_HOME);
        tagBuilder.add(POI_ID);

        handler.dynamicPack.addTag(tagBuilder, Registries.POINT_OF_INTEREST_TYPE);
    }

    // Block -----------------------------------------------------------------------------------------------------------
/*
*/
    public class CompatChestBlock extends ChestBlock {
        public CompatChestBlock(BlockBehaviour.Properties properties) {
            super(properties, () -> Chest_tile);
        }

        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            return new CompatChestEntityBlock(pos, state);
        }
    }

    // EntityBlock -----------------------------------------------------------------------------------------------------
    public class CompatChestEntityBlock extends ChestBlockEntity {
        public CompatChestEntityBlock(BlockPos pos, BlockState state) {
            super(Chest_tile, pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return Chests.getTile();
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        Chest_tile = Chests.getTile(ChestBlockEntity.class);
    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(Chest_tile, context -> new ChestRenderer(context));
    }

    // Item ------------------------------------------------------------------------------------------------------------
    private static class ChestItem extends BlockItem implements ICustomItemRendererProvider {

        public ChestItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new ItemStackRenderer() {
                final BlockEntityRenderDispatcher renderer = Minecraft.getInstance().getBlockEntityRenderDispatcher();
                final ChestBlockEntity dummy = new ChestBlockEntity(BlockPos.ZERO, ChestItem.this.getBlock().defaultBlockState());

                @Override
                public void renderByItem(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
                    renderer.renderItem(dummy, poseStack, multiBufferSource, i, i1);
                }
            };
        }
    }

/*
*/
    @Override
    // Textures --------------------------------------------------------------------------------------------------------
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        // single
        try (TextureImage normal = TextureImage.open(manager, modRes("entity/chest/acacia_chest"));
             TextureImage normal_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_o"));
             // left
             TextureImage left = TextureImage.open(manager, modRes("entity/chest/acacia_chest_left"));
             TextureImage left_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_o"));
             // right
             TextureImage right = TextureImage.open(manager, modRes("entity/chest/acacia_chest_right"));
             TextureImage right_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_o"));
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            Chests.blocks.forEach((wood, block) -> {

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    AnimationMetadataSection meta = plankTexture.getMetadata();

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

                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + wood.getTypeName() + "_chest");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterNormal, respriterNormalO, meta, targetPalette, overlayPalette, res);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + wood.getTypeName() + "_chest_left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterLeft, respriterLeftO, meta, targetPalette, overlayPalette, res);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + wood.getTypeName() + "_chest_right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterRight, respriterRightO, meta, targetPalette, overlayPalette, res);
                        }
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", block, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }


    private void createChestTextures(ClientDynamicResourcesHandler handler,
                                     Respriter respriter, Respriter respriter_O,
                                     AnimationMetadataSection baseMeta, List<Palette> basePalette,
                                     List<Palette> overlayPalette, ResourceLocation res) {

        TextureImage recoloredBase = respriter.recolorWithAnimation(basePalette, baseMeta);
        TextureImage recoloredOverlay = respriter_O.recolorWithAnimation(overlayPalette, baseMeta);
        recoloredBase.applyOverlay(recoloredOverlay);

        handler.dynamicPack.addAndCloseTexture(res, recoloredBase);

    }

}
