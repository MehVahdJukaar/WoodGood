package net.mehvahdjukaar.every_compat.modules.neoforge.abnormal;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.common_classes.*;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.LOG;

//SUPPORT: v4.0.2+
public class WoodworksModule extends EveryCompatModule {
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> chiseled_bookshelves;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> beehives;
    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;
    public final SimpleEntrySet<LeavesType, Block> leafPiles;

    public WoodworksModule(String modId) {
        super(modId, "abnww");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("acacia_bookshelf"),
                        () -> VanillaWoodTypes.ACACIA,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.log)
                                .sound(woodType.getSound())
                                .strength(1.5F)
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(bookshelves);

        chiseled_bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf", "chiseled",
                        getModBlock("chiseled_acacia_bookshelf"),
                        () -> VanillaWoodTypes.ACACIA,
                        woodType -> new BlueprintChiseledBookShelfBlock(Utils.copyPropertySafe(woodType.log)
                                .sound(woodType.getSound())
                                .strength(1.5F)
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTexture(modRes("block/chiseled_acacia_bookshelf_empty"))
                .addTextureM(modRes("block/chiseled_acacia_bookshelf_occupied"),
                        EveryCompat.res("block/acacia_chiseled_bookshelf_occupied_m"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_side"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_top"))
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(chiseled_bookshelves);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> VanillaWoodTypes.OAK,
                        woodType -> new RotatedPillarBlock(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F, 3.0F))
                )
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_boards"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(boards);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("spruce_ladder"),
                        () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new LadderBlock(Utils.copyPropertySafe(Blocks.LADDER)
                                .strength(0.4F)
                                .noOcclusion()
                                .pushReaction(PushReaction.DESTROY))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("quark:ladders"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("quark:ladders"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();
        this.addEntry(ladders);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"),
                        () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new BlueprintBeehiveBlock(Utils.copyPropertySafe(woodType.log)
                                .sound(woodType.getSound())
                                .strength(0.6F)
                        )
                )
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(beehives);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        getModBlock("oak_chest"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CompatChestBlock(this::getChestTile,
                                Utils.copyPropertySafe(woodType.planks).strength(2.5F)
                        )
                )
                .addTile(abwwChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("quark:revertable_chests"), Registries.ITEM)
                .addTag(ResourceLocation.parse("quark:boatable_chests"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "chest", "trapped",
                        getModBlock("trapped_oak_chest"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CompatTrappedChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(woodType.planks).strength(2.5F)
                        )
                )
                .addTile(abwwTrappedBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(trappedChests);


        leafPiles = SimpleEntrySet.builder(LeavesType.class, "leaf_pile",
                        WoodworksBlocks.OAK_LEAF_PILE, () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeafPileBlock(Utils.copyPropertySafe(leavesType.leaves)
                                .strength(0.2F)
                                .ignitedByLava()
                                .pushReaction(PushReaction.DESTROY)
                        )
                )
                .requiresChildren(LOG)
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("leaf_piles"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .copyParentTint()
                .copyParentDrop()
                .build();
        this.addEntry(leafPiles);
    }

    // GetTile -----------------------------------------------------------------------------------------------------------
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return trappedChests.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity -----------------------------------------------------------------------------------------------------------
    private class abwwChestBlockEntity extends CompatChestBlockEntity {
        public abwwChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
        }
    }

    private class abwwTrappedBlockEntity extends CompatChestBlockEntity {
        public abwwTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(trappedChests.getTile(), pos, state);
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        CompatChestBlockRenderer.register(event, trappedChests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) -> bookshelves.items.forEach((wood, item) -> {
            // The generation of ladders get skipped due to some mods already have ladders and will be used as an alt
            Item getLadder = ladders.items.get(wood);
            Item ladder = (getLadder != null) ? getLadder : BuiltInRegistries.ITEM.get(
                    ResourceLocation.fromNamespaceAndPath(wood.getNamespace(), wood.getTypeName() +"_ladder"));

            // sawmill recipes - from LOGS
            sawmillRecipe("oak_planks_from_oak_logs_sawing", wood.log.asItem(), wood.planks.asItem(),
                    sink, manager, wood);
            sawmillRecipe("oak_boards_from_oak_logs_sawing", wood.log.asItem(), boards.items.get(wood),
                    sink, manager, wood);
            sawmillRecipe("spruce_ladder_from_spruce_logs_sawing", wood.log.asItem(), ladder,
                    sink, manager, wood);
            createRecipeIfNotNull("oak_button_from_oak_logs_sawing", true, "button",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_door_from_oak_logs_sawing", true, "door",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_fence_from_oak_logs_sawing", true, "fence",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_fence_gate_from_oak_logs_sawing", true, "fence_gate",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_pressure_plate_from_oak_logs_sawing", true, "pressure_plate",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_sign_from_oak_logs_sawing", true, "sign",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_slab_from_oak_logs_sawing", true, "slab",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_stairs_from_oak_logs_sawing", true, "stairs",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_trapdoor_from_oak_logs_sawing", true, "trapdoor",
                    sink, manager, wood);

            // - from PLANKS
            sawmillRecipe("oak_boards_from_oak_planks_sawing", wood.planks.asItem(), boards.items.get(wood),
                    sink, manager, wood);
            sawmillRecipe("spruce_ladder_from_spruce_planks_sawing", wood.planks.asItem(), ladder,
                    sink, manager, wood);
            createRecipeIfNotNull("oak_button_from_oak_planks_sawing", false, "button",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_fence_from_oak_planks_sawing", false, "fence",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_slab_from_oak_planks_sawing", false, "slab",
                    sink, manager, wood);
            createRecipeIfNotNull("oak_stairs_from_oak_planks_sawing", false, "stairs",
                    sink, manager, wood);
        }));
    }

    @SuppressWarnings("DataFlowIssue")
    public void createRecipeIfNotNull(String recipeName, boolean usingLog, String output,
                                      ResourceSink sink, ResourceManager manager, WoodType wood) {
        Item input = (usingLog) ? wood.log.asItem() : wood.planks.asItem();

        if (Objects.nonNull(wood.getItemOfThis(output))) {
            sawmillRecipe(recipeName, input, wood.getItemOfThis(output), sink, manager, wood);
        } else if (Objects.nonNull(wood.getBlockOfThis(output))) {
            sawmillRecipe(recipeName, input, wood.getBlockOfThis(output).asItem(), sink, manager, wood);
        }
    }

    public void sawmillRecipe(String recipeName, Item input, Item output,
                              ResourceSink sink, ResourceManager manager, WoodType wood) {

        ResourceLocation recipeLocation = modRes("recipe/" + recipeName + ".json"); // get Recipe JSON

        try (InputStream recipeStream = manager.getResource(recipeLocation)
                .orElseThrow(() -> new FileNotFoundException("File Not Found: " + recipeLocation)).open()) {
            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject underIngredient = recipe.getAsJsonObject("ingredient");

            // Editing the JSON recipe
            if (underIngredient.has("tag")) {
                underIngredient.addProperty("tag",
                        getATagOrCreateANew("logs", "caps", wood, sink, manager).toString());
            } else { // underIngredient.has("item")
                underIngredient.addProperty("item", Utils.getID(input).toString());
            }
            recipe.getAsJsonObject("result").addProperty("id", Utils.getID(output).toString());

            // filenameBuilder: <woodType>_<blockType>_from_<woodType>_<logs|planks>_sawing
            String[] nameSplit = recipeName.split("_(?!gate|plate)");
            String filenameBuilder = "_" + nameSplit[1] + "_from_" + wood.getTypeName() + "_" + nameSplit[4] + "_sawing";

            sink.addJson(EveryCompat.res(this.shortenedId() + "/" + wood.getAppendableId() + filenameBuilder), recipe, ResType.RECIPES);

        } catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to open the recipe for {}: {}", recipeLocation,  e);
        }

    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, sink) ->
            trappedChests.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal"),
                        EveryCompat.res("block/abnww/chest/oak/normal_m"),
                        EveryCompat.res("block/abnww/chest/oak/normal_o"),
                        EveryCompat.res("block/abnww/chest/oak/trapped_o")
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal_left"),
                        EveryCompat.res("block/abnww/chest/oak/left_m"),
                        EveryCompat.res("block/abnww/chest/oak/left_o"),
                        EveryCompat.res("block/abnww/chest/oak/trapped_left_o")
                );
                // RIGHT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal_right"),
                        EveryCompat.res("block/abnww/chest/oak/right_m"),
                        EveryCompat.res("block/abnww/chest/oak/right_o"),
                        EveryCompat.res("block/abnww/chest/oak/trapped_right_o")
                );
        })
        );
    }

}
