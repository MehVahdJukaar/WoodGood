package net.mehvahdjukaar.every_compat.modules.variants;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestItem;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;
import java.util.function.Consumer;
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

    public VariantVanillaBlocksModule(String modId) {
        super(modId, "vvb");
        ResourceLocation tab = modRes(modId);

        //Barrel
        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        getModBlock("oak_barrel"),() -> VanillaWoodTypes.OAK,
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
                        getModBlock("spruce_beehive"), () -> VanillaWoodTypes.SPRUCE,
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
                        getModBlock("acacia_bookshelf"), () -> VanillaWoodTypes.ACACIA,
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
                        getModBlock("oak_cartography_table"),() -> VanillaWoodTypes.OAK,
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
                        getModBlock("acacia_chest"), () -> VanillaWoodTypes.ACACIA,
                        w -> new CompatChestBlock(this::getTile, Utils.copyPropertySafe(w.planks))
                )
                .addTile(VariantChestBlockEntity::new)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                                s.replace(
                                        "\"variantvanillablocks:chest/acacia_chest\"",
                                        "\""+woodType.createFullIdWith(EveryCompat.MOD_ID, "chest", shortenedId(), "", "chest") +"\""
                                )
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:chests_wooden"), Registries.BLOCK)
                .addTag(new ResourceLocation("c:chests"), Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .addTag(new ResourceLocation("c:chests_wooden"), Registries.ITEM)
                .addTag(new ResourceLocation("c:chests"), Registries.ITEM)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(chests);

        chiseledBookshelves = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                        getModBlock("acacia_chiseled_bookshelf"), () -> VanillaWoodTypes.ACACIA,
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
                        getModBlock("oak_composter"), () -> VanillaWoodTypes.OAK,
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
                        getModBlock("spruce_crafting_table"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new CraftingTableBlock(Utils.copyPropertySafe(w.planks)) {
                        }
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("crafting_tables"), Registries.BLOCK)
                .addTag(modRes("crafting_tables"), Registries.ITEM)
                //TEXTURE: texture is oak_craftng_table's texture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"),
                        EveryCompat.res("block/vct/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"),
                        EveryCompat.res("block/vct/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"),
                        EveryCompat.res("block/vct/spruce_crafting_table_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(craftingTable);

        fletchingTable = SimpleEntrySet.builder(WoodType.class, "fletching_table",
                        getModBlock("oak_fletching_table"), () -> VanillaWoodTypes.OAK,
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
                        getModBlock("oak_grindstone"), () -> VanillaWoodTypes.OAK,
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
                        getModBlock("acacia_lectern"), () -> VanillaWoodTypes.ACACIA,
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
                        getModBlock("oak_smithing_table"), () -> VanillaWoodTypes.OAK,
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
                        getModBlock("acacia_smoker"), () -> VanillaWoodTypes.ACACIA,
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

    @SuppressWarnings("CommentedOutCode")
    @Override
    public void onModSetup() {
        super.onModSetup();

        // POI & ACQUIREABLE_JOB //!! Dont use below until the problem is fixed
        /*
        RegHelper.addBlocksToPOI(PoiTypes.BEEHIVE, beehive.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.LIBRARIAN, lectern.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.FLETCHER, fletchingTable.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.BUTCHER, smoker.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.FISHERMAN, barrel.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.FARMER, composters.blocks.values());
        RegHelper.addBlocksToPOI(PoiTypes.WEAPONSMITH, grindstones.blocks.values());
        */
    }

    // REGISTRY --------------------------------------------------------------------------------------------------------

    @Override
    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        /*
        apparently due to class verifier issues this is needed since it needs to check if that lambda actually implements that interface and to do so it needs to load the class
        now I have no clue why this isn't needed on the other modules (this is only fabric one so maybe that?)
        could it be that environment here strips stuff less that on common? or that all classes that use this rendered also happen to be de facto fabric classes
        ClientProxy.shutUpClassVerifier(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        this is so dumb and IDK why it's needed. that class should never be loaded since it has environment annotation
        I tried everything, lambdas, double lambdas, anonymous classes...
        */
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
    }


    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, sink) -> {
            chests.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia_chest"),
                        EveryCompat.res("model/oak_chest_normal_m"),
                        EveryCompat.res("model/oak_chest_normal_o"),
                        null
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia_chest_left"),
                        EveryCompat.res("model/oak_chest_left_m"),
                        EveryCompat.res("model/oak_chest_left_o"),
                        null
                );
                // RIGHT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia_chest_right"),
                        EveryCompat.res("model/oak_chest_right_m"),
                        EveryCompat.res("model/oak_chest_right_o"),
                        null
                );

            });

        });
    }

}
