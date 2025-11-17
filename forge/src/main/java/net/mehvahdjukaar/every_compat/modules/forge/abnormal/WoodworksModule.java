package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.*;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;

//SUPPORT: v3.0.0+
@SuppressWarnings({"removal", "DataFlowIssue", "deprecation"})
public class WoodworksModule extends SimpleModule {
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
                                .strength(1.5F)
                        )
                )
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_bookshelves"), Registries.BLOCK, Registries.ITEM)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(bookshelves);

        chiseled_bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf", "chiseled",
                        getModBlock("chiseled_acacia_bookshelf"),
                        () -> VanillaWoodTypes.ACACIA,
                        woodType -> new ChiseledBookShelfBlock(Utils.copyPropertySafe(woodType.log)
                                .strength(1.5F)
                        )
                )
                .addTexture(modRes("block/chiseled_acacia_bookshelf_empty"))
                .addTextureM(modRes("block/chiseled_acacia_bookshelf_occupied"),
                        EveryCompat.res("block/acacia_chiseled_bookshelf_occupied_m"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_side"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_chiseled_bookshelves"), Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_chiseled_bookshelves"), Registries.ITEM)
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
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_boards"), Registries.BLOCK)
                .addTag(modRes("wooden_boards"), Registries.ITEM)
                .addTexture(modRes("block/oak_boards"))
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
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
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(new ResourceLocation("forge:ladders"), Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_ladders"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:ladders"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:ladders"), Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_ladders"), Registries.ITEM)
                .addTag(new ResourceLocation("quark:ladders"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ladders);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"),
                        () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new BlueprintBeehiveBlock(Utils.copyPropertySafe(woodType.log)
                                .strength(0.6F)
                        )
                )
                .addTile(BlueprintBlockEntityTypes.BEEHIVE)
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_beehives"), Registries.BLOCK, Registries.ITEM)
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
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_chests"), Registries.ITEM, Registries.BLOCK)
                .addTag(new ResourceLocation("quark","revertable_chests"), Registries.ITEM)
                .addTag(new ResourceLocation("quark","boatable_chests"), Registries.ITEM)
                .addTile(abwwChestBlockEntity::new)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "chest", "trapped",
                        getModBlock("trapped_oak_chest"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CompatTrappedChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(woodType.planks).strength(2.5F)
                        )
                )
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_trapped_chests"), Registries.BLOCK)

                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_trapped_chests"), Registries.ITEM)
                .addTile(abwwTrappedBlockEntity::new)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .defaultRecipe()
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
                .addCondition(l-> l.getAssociatedWoodType() != null)
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("leaf_piles"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .copyParentDrop()
                .copyParentTint()
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
    // Recipes
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
            bookshelves.items.forEach((wood, item) -> {
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
            })
        );
    }

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

        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON

        try (InputStream recipeStream = manager.getResource(recipeLocation)
                .orElseThrow(() -> new FileNotFoundException("File Not Found: " + recipeLocation)).open()) {
            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject foundRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            JsonObject getIngredient = foundRecipe.getAsJsonObject("ingredient");

            // Editing the JSON recipe
            if (getIngredient.has("tag")) {
                getIngredient.addProperty("tag",
                        getATagOrCreateANew("logs", "caps", wood, sink, manager).toString());
            } else { // getIngredient.has("item")
                getIngredient.addProperty("item", Utils.getID(input).toString());
            }
            foundRecipe.addProperty("result", Utils.getID(output).toString());

            // filenameBuilder: <woodType>_<blockType>_from_<woodType>_<logs|planks>_sawing
            String[] nameSplit = recipeName.split("_(?!gate|plate)");
            String filenameBuilder = "_" + nameSplit[1] + "_from_" + wood.getTypeName() + "_" + nameSplit[4] + "_sawing";

            sink.addJson(EveryCompat.res(this.shortenedId() + "/" + wood.getAppendableId() + filenameBuilder), recipe, ResType.RECIPES);

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Error while creating recipes for woodwork sawmill: ", e);
        }

    }



    // Textures
    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) ->
            trappedChests.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal"),
                        EveryCompat.res("model/oak_chest_normal_m"),
                        EveryCompat.res("model/oak_chest_normal_o"),
                        EveryCompat.res("model/trapped_chest_normal")
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal_left"),
                        EveryCompat.res("model/oak_chest_left_m"),
                        EveryCompat.res("model/oak_chest_left_o"),
                        EveryCompat.res("model/trapped_chest_left")
                );
                // RIGHT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak/normal_right"),
                        EveryCompat.res("model/oak_chest_right_m"),
                        EveryCompat.res("model/oak_chest_right_o"),
                        EveryCompat.res("model/trapped_chest_right")
                );
            })
        );
    }

}
