package net.mehvahdjukaar.every_compat.modules.fabric.furnish;

import io.github.wouink.furnish.block.*;
import io.github.wouink.furnish.recipe.FurnitureRecipe;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.RecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.function.Consumer;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.TRAPDOOR;

///SUPPORT: v29+
public class FurnishModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> bedside_table;
    public final SimpleEntrySet<WoodType, Block> bench;
    public final SimpleEntrySet<WoodType, Block> cabinet;
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> crate;
    public final SimpleEntrySet<WoodType, Block> kitchen_cabinet;
    public final SimpleEntrySet<WoodType, Block> ladder;
    public final SimpleEntrySet<WoodType, Block> log_bench;
    public final SimpleEntrySet<WoodType, Block> pedestal_table;
    public final SimpleEntrySet<WoodType, Block> shelf;
    public final SimpleEntrySet<WoodType, Block> shutter;
    public final SimpleEntrySet<WoodType, Block> square_table;
    public final SimpleEntrySet<WoodType, Block> stool;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> wardrobe;

    public FurnishModule(String modId) {
        super(modId, "fur");
        ResourceLocation tab = modRes(modId);

        bedside_table = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        getModBlock("oak_bedside_table"), () -> VanillaWoodTypes.OAK,
                        w -> new Drawer(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile("furniture"))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("bedside_tables"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_bedside_table"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(bedside_table);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new Bench(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("benchs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_bench"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(bench);

        cabinet = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("birch_cabinet"), () -> VanillaWoodTypes.BIRCH,
                        w -> new Cabinet(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTexture(modRes("block/birch_cabinet_door_right"))
                .addTexture(modRes("block/birch_cabinet_door_left"))
                .addTile(getModTile("furniture"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("cabinets"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/birch_cabinet"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(cabinet);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new Chair(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("chairs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_chair"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(chair);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        getModBlock("oak_crate"), () -> VanillaWoodTypes.OAK,
                        w -> new Crate(copyStadnardProperties(w))
                )
                .addTile(getModTile("crate"))
                .addTexture(modRes("block/oak_crate_top"))
                .addTexture(modRes("block/oak_crate_side"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("crates"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_crate"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(crate);

        kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet",
                        getModBlock("oak_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new Drawer(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile("furniture"))
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("kitchen_cabinets"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_kitchen_cabinet"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(kitchen_cabinet);

        ladder = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("oak_ladder"), () -> VanillaWoodTypes.OAK,
                        w -> new Ladder(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("ladders"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_ladder"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(ladder);

        log_bench = SimpleEntrySet.builder(WoodType.class, "log_bench",
                        getModBlock("oak_log_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new LogBench(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log
                .addTexture(modRes("block/oak_log_bench_top"), PaletteStrategies.LOG_TOP_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("log_benchs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_log_bench"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(log_bench);

        pedestal_table = SimpleEntrySet.builder(WoodType.class, "pedestal_table",
                        getModBlock("oak_pedestal_table"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("pedestal_tables"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_pedestal_table"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(pedestal_table);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new Shelf(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile("shelf"))
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("shelfs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_shelf"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(shelf);

        shutter = SimpleEntrySet.builder(WoodType.class, "shutter",
                        getModBlock("oak_shutter"), () -> VanillaWoodTypes.OAK,
                        w -> new Shutter(copyStadnardProperties(w))
                )
                .requiresChildren(TRAPDOOR) //REASON: recipes
                .addTexture(modRes("block/oak_shutter"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("shutters"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_shutter"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(shutter);

        square_table = SimpleEntrySet.builder(WoodType.class, "square_table",
                        getModBlock("oak_square_table"), () -> VanillaWoodTypes.OAK,
                        w -> new Table(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("square_tables"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_square_table"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(square_table);

        stool = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> VanillaWoodTypes.OAK,
                        w -> new Chair(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("stools"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_stool"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(stool);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new Table(copyStadnardProperties(w).forceSolidOn())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tables"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/oak_table"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(table);

        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        getModBlock("birch_wardrobe"), () -> VanillaWoodTypes.BIRCH,
                        w -> new Wardrobe(copyStadnardProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile("large_furniture"))
                //TEXTURES: stripped_log
                .addTexture(modRes("block/birch_wardrobe_door_top_left")) //TODO: MASK
                .addTexture(modRes("block/birch_wardrobe_door_top_right")) //TODO: MASK
                .addTexture(modRes("block/birch_wardrobe_door_bottom_left")) //TODO: MASK
                .addTexture(modRes("block/birch_wardrobe_door_bottom_right")) //TODO: MASK
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_furniture"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("wardrobes"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("furniture_making/birch_wardrobe"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(wardrobe);

    }

    private BlockBehaviour.Properties copyStadnardProperties(WoodType woodType) {
        return Utils.copyPropertySafe(woodType.planks).noOcclusion();
    }

    @Override
    public void onModSetup() {
        super.onModSetup();
        RecipeTemplate.register(FurnitureRecipe.class, (originalRecipe, oldWoodType, newWoodType) -> {
            List<Ingredient> modifiedIngredient = RecipeTemplate.convertIngredients(originalRecipe.getIngredients(), oldWoodType, newWoodType);
            Ingredient newInput = Ingredient.of(modifiedIngredient.getFirst().getItems()[0]);
            ItemStack originalResult = originalRecipe.getResultItem(RegistryAccess.EMPTY);
            ItemStack newResult = RecipeTemplate.convertItemStack(originalResult, oldWoodType, newWoodType);

            if (newResult == null) {
                throw new UnsupportedOperationException("[Furnish Module @ everycomp] Failed to convert recipe result for FurnitureRecipe");
            }
            else
                return new FurnitureRecipe(originalRecipe.getGroup(), newInput, newResult);
        });
    }

    @Override
    // TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, handler) -> {

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

                boolean isTagCreated = false;
                SimpleTagBuilder itemTag = SimpleTagBuilder.of(modRes(woodType.getTypeName() + "_" + "furniture"));

                for (var entry : this.getEntries()) {
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(woodType);
                    if (item != null) {
                        isTagCreated = true;
                        itemTag.addEntry(item);
                    }
                }
                if (isTagCreated) {
                    handler.addTag(itemTag, Registries.ITEM);
                    handler.addTag(itemTag, Registries.BLOCK);
                }
            }
        });
    }

}
