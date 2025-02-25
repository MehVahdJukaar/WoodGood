package net.mehvahdjukaar.every_compat.modules.forge.variants;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestItem;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import net.xanthian.variantvanillablocks.block.*;
import net.xanthian.variantvanillablocks.utils.ModCreativeModTabs;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;

//SUPPORT: v1.3.6+
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

    public VariantVanillaBlocksModule(String modId) {
        super(modId, "vvb");
        ResourceLocation tab = modRes(modId);

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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
                .build();
        this.addEntry(bookshelves);

        cartography = SimpleEntrySet.builder(WoodType.class, "cartography_table",
                        CartographyTables.OAK_CARTOGRAPHY_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CartographyTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cartography_tables"), Registries.BLOCK)
                .addTag(modRes("cartography_tables"), Registries.ITEM)
                .addTextureM(modRes("block/oak_cartography_table_side1"), EveryCompat.res("block/vanilla_cartography_table_side1_m"))
                .addTextureM(modRes("block/oak_cartography_table_side2"), EveryCompat.res("block/vanilla_cartography_table_side2_m"))
                .addTexture(modRes("block/oak_cartography_table_side3"))
                .addTextureM(modRes("block/oak_cartography_table_top"), EveryCompat.res("block/vanilla_cartography_table_top_m"))
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(cartography);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        net.xanthian.variantvanillablocks.block.Chests.ACACIA_CHEST,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CompatChestBlock(this::getTile, Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .addTile(VariantChestBlockEntity::new)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(chests);

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
                        EveryCompat.res("block/vanilla_chiseled_bookshelf_occupied_m"))
                .addTexture(modRes("block/acacia_chiseled_bookshelf_side"))
                .addTexture(modRes("block/acacia_chiseled_bookshelf_top"))
                .addTile(() -> BlockEntityType.CHISELED_BOOKSHELF)
                .defaultRecipe()
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
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
                .setTabKey(tab)
                .build();
        this.addEntry(smoker);

    }

    //kind of hacy.dont like but we cant reference chests itself while constructing its own object
    // GetTiles
    private BlockEntityType<? extends ChestBlockEntity> getTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity
    private class VariantChestBlockEntity extends CompatChestBlockEntity {
        public VariantChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
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


    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    @Override
    // Textures --------------------------------------------------------------------------------------------------------
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        chests.blocks.forEach((wood, block) -> {
            // SINGLE
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                modRes("entity/chest/acacia_chest"),
                EveryCompat.res("model/oak_chest_normal_m"),
                EveryCompat.res("model/oak_chest_normal_o"),
                null
                );
            // LEFT
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                modRes("entity/chest/acacia_chest_left"),
                EveryCompat.res("model/oak_chest_left_m"),
                EveryCompat.res("model/oak_chest_left_o"),
                null
                );
            // RIGHT
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                modRes("entity/chest/acacia_chest_right"),
                EveryCompat.res("model/oak_chest_right_m"),
                EveryCompat.res("model/oak_chest_right_o"),
                null
                );

            // MODEL ITEM
            String path = shortenedId() + "/" + wood.getAppendableId() + "_chest"; // path to json for chest
            JsonObject modelFile;
            ResourceLocation modelRLoc = EveryCompat.res("models/item/" + path + ".json");

            if (manager.getResource(modelRLoc).isPresent()) {
                try (InputStream modelStream = manager.getResource(modelRLoc).get().open()) {
                    modelFile = RPUtils.deserializeJson(modelStream);
                    String textureID = EveryCompat.MOD_ID + ":chest/" + path;
                    // Editing
                    modelFile.getAsJsonObject("textures").addProperty("chest", textureID);

                    // Add to Resource
                    handler.dynamicPack.addJson(EveryCompat.res(path), modelFile, ResType.ITEM_MODELS );
                } catch (IOException e) {
                    handler.getLogger().error("VariantVanillaBlocks: failed to open the model file: {} - {}", modelRLoc, e);
                }
            }
        });
    }

}
