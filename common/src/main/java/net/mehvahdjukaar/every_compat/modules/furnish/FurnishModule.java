package net.mehvahdjukaar.every_compat.modules.furnish;

import io.github.wouink.furnish.Furnish;
import io.github.wouink.furnish.block.*;
import io.github.wouink.furnish.block.util.VoxelShapeHelper;
import io.github.wouink.furnish.setup.FurnishBlocks;
import io.github.wouink.furnish.setup.FurnishRegistries;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Consumer;

// SUPPORT: v24+
public class FurnishModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> bedsideTable;
    public final SimpleEntrySet<WoodType, Block> bench;
    public final SimpleEntrySet<WoodType, Block> cabinet;
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> coffin;
    public final SimpleEntrySet<WoodType, Block> crate;
    public final SimpleEntrySet<WoodType, Block> kitchenCabinet;
    public final SimpleEntrySet<WoodType, Block> ladder;
    public final SimpleEntrySet<WoodType, Block> logBenches;
    public final SimpleEntrySet<WoodType, Block> pedestalTable;
    public final SimpleEntrySet<WoodType, Block> shelf;
    public final SimpleEntrySet<WoodType, Block> shutter;
    public final SimpleEntrySet<WoodType, Block> squareTable;
    public final SimpleEntrySet<WoodType, Block> stool;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> wardrobe;
    public final SimpleEntrySet<WoodType, Block> bookshelfChest;

    public FurnishModule(String modId) {
        super(modId, "fur");
        ResourceLocation tab = modRes(Furnish.MODID);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new Table(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_table"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(table);

        squareTable = SimpleEntrySet.builder(WoodType.class, "square_table",
                        getModBlock("oak_square_table"), () -> VanillaWoodTypes.OAK,
                        w -> new SimpleFurniture(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_square_table"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(squareTable);

        pedestalTable = SimpleEntrySet.builder(WoodType.class, "pedestal_table",
                        getModBlock("oak_pedestal_table"), () -> VanillaWoodTypes.OAK,
                        w -> new SimpleFurniture(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_pedestal_table"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(pedestalTable);

        bedsideTable = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        getModBlock("oak_bedside_table"), () -> VanillaWoodTypes.OAK,
                        w -> new InventoryFurniture(Utils.copyPropertySafe(w.log), FurnishRegistries.Drawers_Open_Sound, FurnishRegistries.Drawers_Close_Sound)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(FurnishRegistries.Furniture_BlockEntity)
                .addRecipe(modRes("furniture_making/oak_bedside_table"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(bedsideTable);

        kitchenCabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet",
                        FurnishBlocks.Oak_Kitchen_Cabinet, () -> VanillaWoodTypes.OAK,
                        w -> new InventoryFurniture(Utils.copyPropertySafe(w.planks), FurnishRegistries.Drawers_Open_Sound, FurnishRegistries.Drawers_Close_Sound)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(FurnishRegistries.Furniture_BlockEntity)
                .addRecipe(modRes("furniture_making/oak_kitchen_cabinet"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(kitchenCabinet);

        cabinet = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        FurnishBlocks.Birch_Cabinet, () -> VanillaWoodTypes.BIRCH,
                        w -> new Cabinet(Utils.copyPropertySafe(w.log), FurnishRegistries.Cabinet_Open_Sound, FurnishRegistries.Cabinet_Close_Sound)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(FurnishRegistries.Furniture_BlockEntity)
                .addTexture(modRes("block/birch_cabinet_door_right"))
                .addTexture(modRes("block/birch_cabinet_door_left"))
                .addRecipe(modRes("furniture_making/birch_cabinet"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(cabinet);

        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        FurnishBlocks.Birch_Wardrobe, () -> VanillaWoodTypes.BIRCH,
                        w -> new Wardrobe(Utils.copyPropertySafe(w.log), FurnishRegistries.Cabinet_Open_Sound, FurnishRegistries.Cabinet_Close_Sound)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(FurnishRegistries.Large_Furniture_BlockEntity)
                .addTexture(modRes("block/birch_wardrobe_door_bottom_right"))
                .addTexture(modRes("block/birch_wardrobe_door_bottom_left"))
                .addTexture(modRes("block/birch_wardrobe_door_top_right"))
                .addTexture(modRes("block/birch_wardrobe_door_top_left"))
                .addRecipe(modRes("furniture_making/birch_wardrobe"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(wardrobe);

        stool = SimpleEntrySet.builder(WoodType.class, "stool",
                        FurnishBlocks.Oak_Stool, () -> VanillaWoodTypes.OAK,
                        w -> new Chair(Utils.copyPropertySafe(w.log), Chair.BASE_SHAPES)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_stool"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(stool);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        FurnishBlocks.Oak_Chair, () -> VanillaWoodTypes.OAK,
                        w -> new Chair(Utils.copyPropertySafe(w.log),
                                VoxelShapeHelper.getMergedShapes(Chair.BASE_SHAPES, Chair.CHAIR_SEAT))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_chair"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(chair);

        shutter = SimpleEntrySet.builder(WoodType.class, "shutter",
                        FurnishBlocks.Oak_Shutter, () -> VanillaWoodTypes.OAK,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("trapdoor") //REASON: recipes
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_shutter"))
                .addTexture(modRes("block/oak_shutter"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(shutter);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        FurnishBlocks.Oak_Crate, () -> VanillaWoodTypes.OAK,
                        w -> new Crate(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(modRes("crates"), Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.ITEM)
                .addTag(modRes("mail"), Registries.ITEM)
                .addTag(modRes("crates"), Registries.ITEM)
                .addTag(modRes("crate_blacklist"), Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_crate"))
                .addTexture(modRes("block/oak_crate_side"))
                .addTexture(modRes("block/oak_crate_top"))
                .setTab(getModTab(tab))
                .addCustomItem((woodType, block, properties) -> new BlockItem(block, properties.stacksTo(1))
                )
                .copyParentDrop()
                .build();
        this.addEntry(crate);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        FurnishBlocks.Oak_Shelf, () -> VanillaWoodTypes.OAK,
                        w -> new Shelf(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(FurnishRegistries.Shelf_BlockEntity)
                .addRecipe(modRes("furniture_making/oak_shelf"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(shelf);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        FurnishBlocks.Oak_Bench, () -> VanillaWoodTypes.OAK,
                        w -> new Bench(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_bench"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(bench);

        logBenches = SimpleEntrySet.builder(WoodType.class, "log_bench",
                        FurnishBlocks.Oak_Log_Bench, () -> VanillaWoodTypes.OAK,
                        w -> new LogBench(Utils.copyPropertySafe(w.log))
                )
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_log_bench"))
                .addTexture(modRes("block/oak_log_bench_top"))
                .setRenderType(RenderLayer.CUTOUT)
                .setTab(getModTab(tab))
                .build();
        this.addEntry(logBenches);

        ladder = SimpleEntrySet.builder(WoodType.class, "ladder",
                        FurnishBlocks.Oak_Ladder, () -> VanillaWoodTypes.OAK,
                        w -> new Ladder(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/oak_ladder"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(ladder);

        coffin = SimpleEntrySet.builder(WoodType.class, "coffin",
                        FurnishBlocks.Jungle_Coffin, () -> VanillaWoodTypes.JUNGLE,
                        w -> new Coffin(Utils.copyPropertySafe(w.planks))
                )
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("furniture_making/jungle_coffin"))
                .addTexture(modRes("block/jungle_coffin_sides"))
                .setTab(getModTab(tab))
                .build();
        this.addEntry(coffin);

        bookshelfChest = SimpleEntrySet.builder(WoodType.class, "bookshelf_chest",
                        FurnishBlocks.Dark_Oak_Bookshelf_Chest, () -> VanillaWoodTypes.DARK_OAK,
                        w -> new BookshelfChest(Utils.copyPropertySafe(w.planks).pushReaction(PushReaction.BLOCK))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf_chest"), Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.ITEM)
                .addTag(modRes("bookshelf_chests"), Registries.ITEM)
                .addRecipe(modRes("furniture_making/dark_oak_bookshelf_chest"))
                .addTextureM(modRes("block/bookshelf/dark_oak_bookshelf"),
                        EveryCompat.res("block/fur/dark_oak_bookshelf_chest_m"))
                .addTextureM(modRes("block/bookshelf/dark_oak_bookshelf_chest_empty"),
                        EveryCompat.res("block/fur/dark_oak_bookshelf_chest_m"))
                .addTextureM(modRes("block/bookshelf/dark_oak_bookshelf_chest_plenty"),
                        EveryCompat.res("block/fur/dark_oak_bookshelf_chest_m"))
                .addTextureM(modRes("block/bookshelf/dark_oak_bookshelf_chest_sparse"),
                        EveryCompat.res("block/fur/dark_oak_bookshelf_chest_m"))
                .addTile(FurnishRegistries.BookshelfChest_BlockEntity)
                .setTab(getModTab(tab))
                .build();
        this.addEntry(bookshelfChest);
    }

    @Override
    // TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, handler) -> {

            for (var w : WoodTypeRegistry.INSTANCE) {
                boolean hasSomething = false;
                SimpleTagBuilder itemTag = SimpleTagBuilder.of(modRes(w.getTypeName() + "_" + "furniture"));

                for (var entry : this.getEntries()) {
                    Item b = ((SimpleEntrySet<?, ?>) entry).items.get(w);
                    if (b != null) {
                        hasSomething = true;
                        itemTag.addEntry(b);
                    }
                }
                if (hasSomething) {
                    handler.addTag(itemTag, Registries.ITEM);
                    handler.addTag(itemTag, Registries.BLOCK);
                }
            }
        });
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) -> {
            logBenches.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE));
                     TextureImage newTexture = topTexture.makeCopy()) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/oak_log_bench_top", w, id, "oak");

                    sink.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    sink.addTextureIfNotPresent(manager, newId + "_top", () -> {
                        TextureImage newTop = topTexture.makeCopy();
                        CompatSpritesHelper.createSmallLogTopTexture(topTexture, newTop);
                        return newTop;
                    });

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to generate Log Bench block texture for for {} : {}", block, e);

                }

            });
            coffin.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/jungle_coffin_sides", w, id, "jungle");

                    sink.addTextureIfNotPresent(manager, newId + "_top", () -> {
                        TextureImage newTop = topTexture.makeCopy();
                        CompatSpritesHelper.createSmallLogTopTexture(topTexture, newTop);
                        return newTop;
                    });

                    //odd. reusing an existing texture
                    sink.addTextureIfNotPresent(manager, newId, () -> topTexture);

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to generate coffin block texture for for {} : {}", block, e);

                }

            });
        });
    }
}
