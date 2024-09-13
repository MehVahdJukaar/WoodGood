package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.client.ChestManager;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintLadderBlock;
import com.teamabnormals.blueprint.common.block.BookshelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

//SUPPORT v2.2.2+
public class WoodworksModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> beehives;
    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;
    public final SimpleEntrySet<LeavesType, Block> leafPiles;

    public WoodworksModule(String modId) {
        super(modId, "abnww");

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(WoodworksBlocks.WoodworksProperties.ACACIA_WOOD.bookshelf()))
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .defaultRecipe()
                .build();
        this.addEntry(bookshelves);


        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_boards"))
                .defaultRecipe()
                .build();
        this.addEntry(boards);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintLadderBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.ladder()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:ladders"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();
        this.addEntry(ladders);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        () -> getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintBeehiveBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.beehive()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTile(BlueprintBlockEntityTypes.BEEHIVE)
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .build();
        this.addEntry(beehives);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        () -> getModBlock("oak_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintChestBlock(EveryCompat.MOD_ID + ":" + w.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.CHESTS, Registry.ITEM_REGISTRY)
                .addTag(Tags.Items.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTag(BlueprintItemTags.BOATABLE_CHESTS, Registry.ITEM_REGISTRY)
                .addTag(BlueprintItemTags.REVERTABLE_CHESTS, Registry.ITEM_REGISTRY)
                .addTile(BlueprintChestBlockEntity::new)
                .addCustomItem((w, b, p) -> new BEWLRFuelBlockItem(b, p, () -> () -> chestBEWLR(false), 300))
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        () -> getModBlock("oak_trapped_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintTrappedChestBlock(EveryCompat.MOD_ID + ":" + w.getTypeName() + "_trapped", WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.CHESTS, Registry.ITEM_REGISTRY)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registry.ITEM_REGISTRY)
                .addTag(Tags.Items.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTile(BlueprintTrappedChestBlockEntity::new)
                .addCustomItem((w, b, p) -> new BEWLRFuelBlockItem(b, p, () -> () -> chestBEWLR(true), 300))
                .defaultRecipe()
                .build();
        this.addEntry(trappedChests);


        leafPiles = SimpleEntrySet.builder(LeavesType.class, "leaf_pile",
                        WoodworksBlocks.OAK_LEAF_PILE, () -> LeavesTypeRegistry.OAK_TYPE,
                        (w) -> {
                            if (w.getWoodType() == null) return null;
                            return new LeafPileBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.leafPile());
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("leaf_piles"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .setRenderType(() -> RenderType::cutout)
                .copyParentDrop()
                .defaultRecipe()
                .build();
        this.addEntry(leafPiles);
    }


    @OnlyIn(Dist.CLIENT)
    private static BEWLRBlockItem.LazyBEWLR chestBEWLR(boolean trapped) {
        return trapped
                ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) ->
                    new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet,
                    new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState())))
                : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) ->
                    new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet,
                    new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState())));
    }

    public void onFirstClientTick1 () {
        var ic = Minecraft.getInstance().getItemColors();
        var bc = Minecraft.getInstance().getBlockColors();
        leafPiles.blocks.forEach((t, b) -> {
            var leaf = t.getChild("leaves");
            if (leaf instanceof Block block) {
                bc.register((s, l, p, i) -> bc.getColor(block.defaultBlockState(), l, p, i), b);
                ic.register((stack, tintIndex) -> ic.getColor(new ItemStack(block.asItem()), tintIndex), b.asItem());
            }
        });
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event){
        super.registerBlockColors(event);
        leafPiles.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors (ClientPlatformHelper.ItemColorEvent event){
        leafPiles.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
            //blockColor.register((s, l, p, i) -> blockColor.getColor(bl.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        bookshelves.items.forEach((wood, item) -> {
            // sawmill recipes - from LOGS
            sawmill_Recipe("oak_planks_from_oak_logs_sawing", wood.log.asItem(), wood.planks.asItem(),
                    handler, manager, wood);
            sawmill_Recipe("oak_boards_from_oak_logs_sawing", wood.log.asItem(), boards.items.get(wood),
                    handler, manager, wood);
            sawmill_Recipe("spruce_ladder_from_spruce_logs_sawing", wood.log.asItem(), ladders.items.get(wood),
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_button_from_oak_logs_sawing", true, "button",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_door_from_oak_logs_sawing", true, "door",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_fence_from_oak_logs_sawing", true, "fence",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_fence_gate_from_oak_logs_sawing", true, "fence_gate",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_pressure_plate_from_oak_logs_sawing", true, "pressure_plate",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_sign_from_oak_logs_sawing", true, "sign",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_slab_from_oak_logs_sawing", true, "slab",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_stairs_from_oak_logs_sawing", true, "stairs",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_trapdoor_from_oak_logs_sawing", true, "trapdoor",
                    handler, manager, wood);

            // - from PLANKS
            sawmill_Recipe("oak_boards_from_oak_planks_sawing", wood.planks.asItem(), boards.items.get(wood),
                    handler, manager, wood);
            sawmill_Recipe("spruce_ladder_from_spruce_planks_sawing", wood.planks.asItem(), ladders.items.get(wood),
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_button_from_oak_planks_sawing", false, "button",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_fence_from_oak_planks_sawing", false, "fence",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_slab_from_oak_planks_sawing", false, "slab",
                    handler, manager, wood);
            createRecipe_ifNotNull("oak_stairs_from_oak_planks_sawing", false, "stairs",
                    handler, manager, wood);

        });

    }

    @SuppressWarnings("DataFlowIssue")
    public void createRecipe_ifNotNull(String recipeName, boolean usingLog, String output,
                                       ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {
        Item input = (usingLog) ? wood.log.asItem() : wood.planks.asItem();
        if (Objects.nonNull(wood.getItemOfThis(output))) {
            sawmill_Recipe(recipeName, input, wood.getItemOfThis(output), handler, manager, wood);
        }
        else if (Objects.nonNull(wood.getBlockOfThis(output))) {
            sawmill_Recipe(recipeName, input, wood.getBlockOfThis(output).asItem(), handler, manager, wood);
        }
    }

    public void sawmill_Recipe(String recipeName, Item input, Item output,
                               ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {

        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).orElseThrow().open()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject getRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            JsonObject getIngredient = getRecipe.getAsJsonObject("ingredient");

            // Editing the JSON recipe
            if (getIngredient.has("tag")) {
                getIngredient.addProperty("tag", wood.getNamespace() + ":" + wood.getTypeName() + "_logs");
            } else { // getIngredient.has("item")
                getIngredient.addProperty("item", Utils.getID(input).toString());
            }
            getRecipe.addProperty("result", Utils.getID(output).toString()
            );

        } catch (IOException e) {
            EveryCompat.LOGGER.error("{Woodworks Module} sawmill_recipe(): {0}", e);
        }

        // filenameBuilder: <woodType>_<blockType>_from_<woodType>_<logs|planks>_sawing
        String[] nameSplit = recipeName.split("_(?!gate|plate)");
        String filenameBuilder = "_" + nameSplit[1] + "_from_" + wood.getTypeName() + "_" + nameSplit[4] + "_sawing";

        handler.dynamicPack.addJson(EveryCompat.res(this.shortenedId() + "/" + wood.getAppendableId() + filenameBuilder), recipe, ResType.RECIPES);
    }


    @Override
    // Textures
    public void addDynamicClientResources (ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

            trappedChests.blocks.forEach((wood, block) -> {

                BlueprintTrappedChestBlock b = (BlueprintTrappedChestBlock) block;
                String folderPath = "entity/chest/";

                { // SINGLE
                    ResourceLocation res = EveryCompat.res(folderPath + wood.getTypeName() + "/normal");
                    ResourceLocation trappedRes = EveryCompat.res(folderPath + wood.getTypeName() + "/trapped");

                    CompatChestTexture.generateChestTexture(handler, manager, wood, block, res, trappedRes,
                            modRes("entity/chest/oak/normal"),
                            EveryCompat.res("model/oak_chest_normal_m"),
                            EveryCompat.res("model/oak_chest_normal_o"),
                            EveryCompat.res("model/trapped_chest_normal")
                    );
                }
                { // LEFT
                    ResourceLocation res = EveryCompat.res(folderPath + wood.getTypeName() + "/normal_left");
                    ResourceLocation trappedRes = EveryCompat.res(folderPath + wood.getTypeName() + "/trapped_left");

                    CompatChestTexture.generateChestTexture(handler, manager, wood, block, res, trappedRes,
                            modRes("entity/chest/oak/normal_left"),
                            EveryCompat.res("model/oak_chest_left_m"),
                            EveryCompat.res("model/oak_chest_left_o"),
                            EveryCompat.res("model/trapped_chest_left")
                    );
                }
                { // RIGHT
                    ResourceLocation res = EveryCompat.res(folderPath + wood.getTypeName() + "/normal_right");
                    ResourceLocation trappedRes = EveryCompat.res(folderPath + wood.getTypeName() + "/trapped_right");

                    CompatChestTexture.generateChestTexture(handler, manager, wood, block, res, trappedRes,
                            modRes("entity/chest/oak/normal_right"),
                            EveryCompat.res("model/oak_chest_right_m"),
                            EveryCompat.res("model/oak_chest_right_o"),
                            EveryCompat.res("model/trapped_chest_right")
                    );
                }

                ChestManager.putChestInfo(EveryCompat.MOD_ID, wood.getTypeName(), false);
                ChestManager.putChestInfo(EveryCompat.MOD_ID, wood.getTypeName(), true);

            });

    }

}
