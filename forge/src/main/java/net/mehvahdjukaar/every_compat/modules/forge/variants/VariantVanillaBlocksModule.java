package net.mehvahdjukaar.every_compat.modules.forge.variants;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
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
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.xanthian.variantvanillablocks.block.*;
import net.xanthian.variantvanillablocks.utils.ModCreativeModTabs;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Supplier;

//SUPPORT: v1.3.6
public class VariantVanillaBlocksModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> barrel;
    public final SimpleEntrySet<WoodType, Block> beehive;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> cartography;
    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> chiseledBookshelves;
    public final SimpleEntrySet<WoodType, Block> composters;
    public final SimpleEntrySet<WoodType, Block> craftingTable;
    public final SimpleEntrySet<WoodType, Block> fletchingTable;
    public final SimpleEntrySet<WoodType, Block> grindstones;
    public final SimpleEntrySet<WoodType, Block> lectern;
    public final SimpleEntrySet<WoodType, Block> smithingTable;
    public final SimpleEntrySet<WoodType, Block> smoker;

    protected final ResourceLocation poiId = EveryCompat.res("vvb_beehive");
    @SuppressWarnings("unused")
    public final Supplier<PoiType> compatBeeHivePOI = RegHelper.registerPOI(poiId,
            () -> new PoiType(getBeehives(), 1, 1));

    public VariantVanillaBlocksModule(String modID) {
        super(modID, "vvb");
        var tab = ModCreativeModTabs.VVB_TAB;

        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
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
        this.addEntry(barrel);

        beehive = SimpleEntrySet.builder(WoodType.class, "beehive",
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
        this.addEntry(beehive);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
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
        this.addEntry(bookshelves);

        cartography = SimpleEntrySet.builder(WoodType.class, "cartography_table",
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
        this.addEntry(cartography);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        net.xanthian.variantvanillablocks.block.Chests.ACACIA_CHEST,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CompatChestBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addCustomItem((w, block, properties) -> new ChestItem(block, properties))
                .addTile(CompatChestBlockEntity::new)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(chests);

        {
            chiseledBookshelves = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
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
            this.addEntry(chiseledBookshelves);

            composters = SimpleEntrySet.builder(WoodType.class, "composter",
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
            this.addEntry(composters);

            craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
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
            this.addEntry(craftingTable);

            fletchingTable = SimpleEntrySet.builder(WoodType.class, "fletching_table",
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
            this.addEntry(fletchingTable);

            grindstones = SimpleEntrySet.builder(WoodType.class, "grindstone",
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
            this.addEntry(grindstones);

            lectern = SimpleEntrySet.builder(WoodType.class, "lectern",
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
            this.addEntry(lectern);

            smithingTable = SimpleEntrySet.builder(WoodType.class, "smithing_table",
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
            this.addEntry(smithingTable);

            smoker = SimpleEntrySet.builder(WoodType.class, "smoker",
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
            this.addEntry(smoker);

        }
    }


    private Set<BlockState> getBeehives() {
        var set = new ImmutableSet.Builder<BlockState>();
        beehive.blocks.values().forEach(b -> set.addAll(b.getStateDefinition().getPossibleStates()));
        return set.build();
    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(PoiTypeTags.BEE_HOME);
        tagBuilder.add(poiId);

        handler.dynamicPack.addTag(tagBuilder, Registries.POINT_OF_INTEREST_TYPE);
    }

    // Block -----------------------------------------------------------------------------------------------------------
    public class CompatChestBlock extends ChestBlock {

        public CompatChestBlock(BlockBehaviour.Properties properties) {
            super(properties, () -> chests.getTile(CompatChestBlockEntity.class));
        }

        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            return new CompatChestBlockEntity(pos, state);
        }
    }

    // EntityBlock -----------------------------------------------------------------------------------------------------
    public class CompatChestBlockEntity extends ChestBlockEntity {
        private final WoodType woodType;

        public CompatChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
            var w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
            this.woodType = w == null ? WoodTypeRegistry.OAK_TYPE : w;
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(chests.getTile(CompatChestBlockEntity.class), CompatChestRenderer::new);
    }

    private class ChestItem extends BlockItem implements ICustomItemRendererProvider {

        public ChestItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new ItemStackRenderer() {
                final BlockEntityRenderDispatcher renderer = Minecraft.getInstance().getBlockEntityRenderDispatcher();
                final CompatChestBlockEntity dummy = new CompatChestBlockEntity(BlockPos.ZERO, ChestItem.this.getBlock().defaultBlockState());

                @Override
                public void renderByItem(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
                    renderer.renderItem(dummy, poseStack, multiBufferSource, i, i1);
                }
            };
        }
    }

    // Renderer --------------------------------------------------------------------------------------------------------
    @OnlyIn(Dist.CLIENT)
    private class CompatChestRenderer extends ChestRenderer<CompatChestBlockEntity> {
        private final Map<WoodType, Material> single = new HashMap<>();
        private final Map<WoodType, Material> left = new HashMap<>();
        private final Map<WoodType, Material> right = new HashMap<>();

        public CompatChestRenderer(BlockEntityRendererProvider.Context context) {
            super(context);

            for (WoodType w : WoodTypeRegistry.getTypes()) {
                if (!w.isVanilla()) {
                    String path = VariantVanillaBlocksModule.this.shortenedId() + "/" + w.getAppendableId() + "_chest";
                    single.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path)));
                    left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path + "_left")));
                    right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path + "_right")));
                }
            }
        }

        @Override
        protected @NotNull Material getMaterial(CompatChestBlockEntity blockEntity, ChestType chestType) {
            WoodType w = blockEntity.woodType;
            return switch (chestType) {
                case LEFT -> left.get(w);
                case RIGHT -> right.get(w);
                default -> single.get(w);
            };
        }
    }

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
             TextureImage right_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_o"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            chests.blocks.forEach((wood, block) -> {
                // path to (json||texture) for chest
                String path = this.shortenedId() + "/" + wood.getAppendableId() + "_chest";

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
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path);
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterNormal, respriterNormalO, meta, targetPalette, overlayPalette, res);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path + "_left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterLeft, respriterLeftO, meta, targetPalette, overlayPalette, res);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path + "_right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            createChestTextures(handler, respriterRight, respriterRightO, meta, targetPalette, overlayPalette, res);
                        }
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", block, ex);
                }

                // MODEL ITEM ------------------------------------------------------------------------------------------
                JsonObject modelItem;

                try (InputStream modelStream = manager.getResource(EveryCompat.res("models/item/" + path + ".json"))
                        .orElseThrow(() -> new TypeNotPresentException("Model-Item file not found: ", null)).open()) {
                    modelItem = RPUtils.deserializeJson(modelStream);
                    String textureID = EveryCompat.MOD_ID + ":chest/" + path;
                    // Editing
                    modelItem.getAsJsonObject("textures").addProperty("chest", textureID);

                    handler.dynamicPack.addJson(EveryCompat.res(path), modelItem, ResType.ITEM_MODELS );
                } catch (IOException e) {
                    handler.getLogger().error("VariantVanillaBlocksModule - failed to open the model-item file: {0}", e);
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
