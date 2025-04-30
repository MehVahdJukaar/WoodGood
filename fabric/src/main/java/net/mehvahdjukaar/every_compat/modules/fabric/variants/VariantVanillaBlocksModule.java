package net.mehvahdjukaar.every_compat.modules.fabric.variants;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.ECRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestItem;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xanthian.variantvanillablocks.Initialise;
import net.xanthian.variantvanillablocks.block.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;


//SUPPORT: v1.3.6+
//why isn't this in common??
public class VariantVanillaBlocksModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BarrelBlock> barrel;
    public final SimpleEntrySet<WoodType, BeehiveBlock> beehive;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, CartographyTableBlock> cartography;

    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, ChiseledBookShelfBlock> chiseledBookshelves;
    public final SimpleEntrySet<WoodType, ComposterBlock> composters;
    public final SimpleEntrySet<WoodType, CraftingTableBlock> craftingTable;
    public final SimpleEntrySet<WoodType, FletchingTableBlock> fletchingTable;
    public final SimpleEntrySet<WoodType, GrindstoneBlock> grindstones;
    public final SimpleEntrySet<WoodType, LecternBlock> lectern;
    public final SimpleEntrySet<WoodType, SmithingTableBlock> smithingTable;
    public final SimpleEntrySet<WoodType, SmokerBlock> smoker;
    //LOOM?

    // Point-Of-Interest for Beehives -  //!! - remove when the addBlocksToPOI() is fixed & enabled below
    protected final ResourceLocation poiId = EveryCompat.res("vvb_beehive");
    public final Supplier<PoiType> compatBeeHivePOI = RegHelper.registerPOI(poiId,
            () -> new PoiType(getBeehives(), 1, 1));
    private Set<BlockState> getBeehives() {
        var set = new ImmutableSet.Builder<BlockState>();
        beehive.blocks.values().forEach(b -> set.addAll(b.getStateDefinition().getPossibleStates()));
        return set.build();
    }

    public VariantVanillaBlocksModule(String modID) {
        super(modID, "vvb");
        ResourceLocation tab = modRes(Initialise.MOD_ID);

        //Barrel
        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        () -> Barrels.OAK_BARREL, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("barrels"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:barrels"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:barrels_wooden"), Registries.BLOCK)
                .addTag(modRes("barrels"), Registries.ITEM)
                .addTag(new ResourceLocation("c:barrels"), Registries.ITEM)
                .addTag(new ResourceLocation("c:barrels_wooden"), Registries.ITEM)
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
                        () -> Beehives.SPRUCE_BEEHIVE,
                        () -> WoodTypeRegistry.getValue("spruce"),
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
                        () -> Bookshelves.ACACIA_BOOKSHELF,
                        () -> WoodTypeRegistry.getValue("acacia"),
                        w -> new Block(Utils.copyPropertySafe(Blocks.BOOKSHELF))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:bookshelves"), Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.ITEM)
                .addTag(new ResourceLocation("c:bookshelves"), Registries.ITEM)
                .addTextureM(modRes("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(bookshelves);

        cartography = SimpleEntrySet.builder(WoodType.class, "cartography_table",
                        () -> CartographyTables.OAK_CARTOGRAPHY_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CartographyTableBlock(Utils.copyPropertySafe(Blocks.CARTOGRAPHY_TABLE)) {
                        }
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
                        () -> Chests.ACACIA_CHEST,
                        () -> WoodTypeRegistry.getValue("acacia"),
                        w -> new CompatChestBlock(this::getTile, Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:chests_wooden"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:chests"), Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .addTag(new ResourceLocation("c:chests_wooden"), Registries.ITEM)
                .addTag(new ResourceLocation("c:chests"), Registries.ITEM)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .addTile(VariantChestBlockEntity::new)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(chests);

        chiseledBookshelves = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                        () -> ChiseledBookshelves.ACACIA_CHISELED_BOOKSHELF,
                        () -> WoodTypeRegistry.getValue("acacia"),
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
                        () -> Composters.OAK_COMPOSTER,
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ComposterBlock(Utils.copyPropertySafe(Blocks.COMPOSTER))
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
                        () -> CraftingTables.SPRUCE_CRAFTING_TABLE,
                        () -> WoodTypeRegistry.getValue("spruce"),
                        w -> new CraftingTableBlock(Utils.copyPropertySafe(w.planks)) {
                        }
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("crafting_tables"), Registries.BLOCK)
                .addTag(modRes("crafting_tables"), Registries.ITEM)
                //TEXTURE: texture is oak_craftng_table's texture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/vct/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/vct/spruce_crafting_table_side_m"))
                .addTexture(EveryCompat.res("block/spruce_crafting_table_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(craftingTable);

        fletchingTable = SimpleEntrySet.builder(WoodType.class, "fletching_table",
                        () -> FletchingTables.OAK_FLETCHING_TABLE,
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FletchingTableBlock(Utils.copyPropertySafe(Blocks.FLETCHING_TABLE)) {
                        }
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
                        () -> Grindstones.OAK_GRINDSTONE,
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GrindstoneBlock(Utils.copyPropertySafe(Blocks.GRINDSTONE)) {
                        }
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
                        () -> Lecterns.ACACIA_LECTERN,
                        () -> WoodTypeRegistry.getValue("acacia"),
                        w -> new LecternBlock(Utils.copyPropertySafe(Blocks.LECTERN)) {
                        }
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
                        () -> SmithingTables.OAK_SMITHING_TABLE,
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmithingTableBlock(Utils.copyPropertySafe(Blocks.SMITHING_TABLE)) {
                        }
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
                        () -> Smokers.ACACIA_SMOKER,
                        () -> WoodTypeRegistry.getValue("acacia"),
                        w -> new SmokerBlock(Utils.copyPropertySafe(Blocks.SMOKER)) {
                        }
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

    @Override
    public void onModSetup() {
        super.onModSetup();

        //POI & ACQUIREABLE_JOB //!! Dont use below until the problem is fixed
//        RegHelper.addBlocksToPOI(PoiTypes.BEEHIVE, beehive.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.LIBRARIAN, lectern.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.FLETCHER, fletchingTable.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.BUTCHER, smoker.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.FISHERMAN, barrel.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.FARMER, composters.blocks.values());
//        RegHelper.addBlocksToPOI(PoiTypes.WEAPONSMITH, grindstones.blocks.values());
    }

    // Registry --------------------------------------------------------------------------------------------------------

    @Override
    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        //apparently due to class verifier issues this is needed since it needs to check if that lambda actually implements that interface and to do so it needs to load the class
        //now I have no clue why this isn't needed on the other modules (this is only fabric one so maybe that?)
        //could it be that environment here strips stuff less that on common? or that all classes that use this rendered also happen to be de facto fabric classes
        //ClientProxy.shutUpClassVerifier(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        //this is so dumb and IDK why it's needed. that class should never be loaded since it has environment annotation
        //I tried everything, lambdas, double lambdas, anonymous classes...
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    // Textures --------------------------------------------------------------------------------------------------------

    @Override
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
                    handler.dynamicPack.addJson(EveryCompat.res(path), modelFile, ResType.ITEM_MODELS);
                } catch (IOException e) {
                    handler.getLogger().error("VariantVanillaBlocks: failed to open the model file: {} - {}", modelRLoc, e);
                }
            }
        });
    }

}
