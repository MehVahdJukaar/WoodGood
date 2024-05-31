package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;

//SUPPORT: v3.0.0+
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
        var tab = CreativeModeTabs.BUILDING_BLOCKS;

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new Block(WoodworksBlocks.WoodworksProperties.ACACIA_WOOD.bookshelf()))
                .setTabKey(() -> tab)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .defaultRecipe()
                .build();
        this.addEntry(bookshelves);

        chiseled_bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf", "chiseled",
                        getModBlock("chiseled_acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new ChiseledBookShelfBlock(WoodworksBlocks.WoodworksProperties.ACACIA_WOOD.chiseledBookshelf()))
                .setTabKey(() -> tab)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTexture(modRes("block/chiseled_acacia_bookshelf_empty"))
                .addTextureM(modRes("block/chiseled_acacia_bookshelf_occupied"),
                        EveryCompat.res("block/acacia_chiseled_bookshelf_occupied_m"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_side"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_top"))
                .defaultRecipe()
                .build();
        this.addEntry(chiseled_bookshelves);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks)))
                .setTabKey(() -> tab)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_boards"))
                .defaultRecipe()
                .build();
        this.addEntry(boards);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new LadderBlock(WoodworksBlocks.WoodworksProperties.SPRUCE_WOOD.ladder()))
                .setTabKey(() -> tab)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:ladders"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:ladders"), Registries.ITEM)
                .defaultRecipe()
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();
        this.addEntry(ladders);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintBeehiveBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.beehive()))
                .setTabKey(() -> tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .defaultRecipe()
                .addTile(BlueprintBlockEntityTypes.BEEHIVE)
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .build();
        this.addEntry(beehives);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        getModBlock("oak_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            // EveryComp.MOD_ID:<type>_normal
                            String registryName = BlueprintChestMaterials.registerMaterials(EveryCompat.MOD_ID,
                                   shortenedId() + w.getAppendableId(), false);
                            return new BlueprintChestBlock(registryName,
                                    WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest());
                        })
                .setTabKey(() -> tab)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(new ResourceLocation("quark:revertable_chests"), Registries.ITEM)
                .addTag(new ResourceLocation("quark:boatable_chests"), Registries.ITEM)
                .addTile(BlueprintChestBlockEntity::new)
                .addCustomItem((w, b, p) -> new BEWLRFuelBlockItem(b, p, () -> () -> chestBEWLR(false), 300))
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "chest", "trapped",
                        getModBlock("trapped_oak_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            // EveryComp.MOD_ID:<type>_trapped
                            String registryName = BlueprintChestMaterials.registerMaterials(EveryCompat.MOD_ID,
                                    shortenedId() + w.getAppendableId(), true);
                            return new BlueprintTrappedChestBlock(registryName,
                                    WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest());
                        })
                .setTabKey(() -> tab)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registries.ITEM)
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
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("leaf_piles"), Registries.BLOCK)
                .setTabKey(() -> tab)
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


    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : leafPiles.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("twilightforest") && t.getTypeName().equals("beanstalk")) continue;
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        for (Map.Entry<LeavesType, Block> entry : leafPiles.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("twilightforest") && t.getTypeName().equals("beanstalk")) continue;
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }
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
        } else if (Objects.nonNull(wood.getBlockOfThis(output))) {
            sawmill_Recipe(recipeName, input, wood.getBlockOfThis(output).asItem(), handler, manager, wood);
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void sawmill_Recipe(String recipeName, Item input, Item output,
                               ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {

        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).get().open()) {
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
            getRecipe.addProperty("result", Utils.getID(output).toString());

        } catch (IOException e) {
            EveryCompat.LOGGER.error("Woodworks Module/sawmill_recipe() - failed to open the recipe: {0}", e);
        }

        // filenameBuilder: <woodType>_<blockType>_from_<woodType>_<logs|planks>_sawing
        String[] nameSplit = recipeName.split("_(?!gate|plate)");
        String filenameBuilder = "_" + nameSplit[1] + "_from_" + wood.getTypeName() + "_" + nameSplit[4] + "_sawing";

        handler.dynamicPack.addJson(EveryCompat.res(this.shortenedId() + "/" + wood.getAppendableId() + filenameBuilder), recipe, ResType.RECIPES);
    }

    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        trappedChests.blocks.forEach((wood, block) -> {
            // SINGLE
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                    modRes("entity/chest/oak/normal"),
                    EveryCompat.res("model/oak_chest_normal_m"),
                    EveryCompat.res("model/oak_chest_normal_o"),
                    EveryCompat.res("model/trapped_chest_normal")
            );
            // LEFT
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                    modRes("entity/chest/oak/normal_left"),
                    EveryCompat.res("model/oak_chest_left_m"),
                    EveryCompat.res("model/oak_chest_left_o"),
                    EveryCompat.res("model/trapped_chest_left")
            );
            // RIGHT
            generateChestTexture(handler, manager, shortenedId(), wood, block,
                    modRes("entity/chest/oak/normal_right"),
                    EveryCompat.res("model/oak_chest_right_m"),
                    EveryCompat.res("model/oak_chest_right_o"),
                    EveryCompat.res("model/trapped_chest_right")
            );
        });
    }

}
