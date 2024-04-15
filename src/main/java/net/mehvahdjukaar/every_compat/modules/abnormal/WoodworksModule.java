package net.mehvahdjukaar.every_compat.modules.abnormal;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.client.ChestManager;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintLadderBlock;
import com.teamabnormals.blueprint.common.block.BookshelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintBeehiveBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.math.colors.HCLColor;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//SUPPORT: v1.2.1
public class WoodworksModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> beehives;
    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<LeavesType, Block> leafPiles;

    public WoodworksModule(String modId) {
        super(modId, "abnww");

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        WoodworksBlocks.ACACIA_BOOKSHELF, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(WoodworksBlocks.WoodworksProperties.ACACIA_WOOD.bookshelf())
                )
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addTextureM(WoodGood.res("block/acacia_bookshelf"), WoodGood.res("block/acacia_bookshelf_m"))
                .build();
        this.addEntry(bookshelves);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.planks)))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_boards"))
                .build();
        this.addEntry(boards);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        () -> getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new BlueprintBeehiveBlock(WoodworksBlocks.WoodworksProperties.SPRUCE_WOOD.beehive()))
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .addTile(BlueprintBeehiveBlockEntity::new)
                .addTextureM(WoodGood.res("block/spruce_beehive_front_honey"), WoodGood.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(WoodGood.res("block/spruce_beehive_front"), WoodGood.res("block/spruce_beehive_front_m"))
                .addTextureM(WoodGood.res("block/spruce_beehive_side"), WoodGood.res("block/spruce_beehive_side_m"))
                .addTexture(WoodGood.res("block/spruce_beehive_end"))
                .build();
        this.addEntry(beehives);


        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        () -> getModBlock("oak_chest"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlueprintChestBlock(WoodGood.MOD_ID + ":" + w.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest())
                )
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
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
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        () -> getModBlock("oak_trapped_chest"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlueprintTrappedChestBlock(WoodGood.MOD_ID + ":" + w.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest())
                )
                .setTab(() -> CreativeModeTab.TAB_REDSTONE)
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
                .build();
        this.addEntry(trappedChests);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new BlueprintLadderBlock(WoodworksBlocks.WoodworksProperties.SPRUCE_WOOD.ladder()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:blocks/ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:items/ladders"), Registry.ITEM_REGISTRY)
                .addTexture(WoodGood.res("block/spruce_ladder"))
                .build();
        this.addEntry(ladders);

        leafPiles = SimpleEntrySet.builder(LeavesType.class, "leaf_pile",
                        WoodworksBlocks.OAK_LEAF_PILE, () -> LeavesType.OAK_LEAVES_TYPE,
                        (w) -> {
                            if (w.woodType == null) return null;
                            return new LeafPileBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.leafPile());
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("leaf_piles"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .setRenderType(() -> RenderType::cutout)
                .useLootFromBase()
                .build();
        this.addEntry(leafPiles);
    }

    @Override
    public void onFirstClientTick() {
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
    private static BEWLRBlockItem.LazyBEWLR chestBEWLR(boolean trapped) {
        return trapped
        ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(
                dispatcher, entityModelSet,
                new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState())))
        : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> new ChestBlockEntityWithoutLevelRenderer<>(
                dispatcher, entityModelSet,
                new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState())));
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        leafPiles.blocks.forEach((t, b) -> {
            ItemColors colors = event.getItemColors();
            colors.register((stack, tintIndex) -> colors.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());

            BlockColors blockColor = event.getBlockColors();
            blockColor.register((s, l, p, i) -> blockColor.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

        bookshelves.items.forEach((wood, item) -> {
            // crafting_shaped recipes
            craftingShaped_Recipe("acacia_bookshelf", wood.planks.asItem(), item,
                    handler, manager, wood);
            craftingShaped_Recipe("oak_boards", wood.planks.asItem(), boards.items.get(wood),
                    handler, manager, wood);
            craftingShaped_Recipe("spruce_beehive", wood.planks.asItem(), beehives.items.get(wood),
                    handler, manager, wood);
            craftingShaped_Recipe("spruce_ladder", wood.planks.asItem(), ladders.items.get(wood),
                    handler, manager, wood);

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

        leafPiles.items.forEach((types, item) -> {
            leaves_Recipe("oak_leaf_pile", types.leaves.asItem(), leafPiles.items.get(types),
                    handler, manager, types);

            leaves_craftingShaped("oak_leaves_from_leaf_piles", leafPiles.items.get(types), types.leaves.asItem(),
                    handler, manager, types);
        });

    }

    public void createRecipe_ifNotNull(String recipeName, boolean usingLog, String output,
                                       ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {
        Item input = (usingLog) ? wood.log.asItem() : wood.planks.asItem();

        // statement for "tfc" is a workaround for wood.getItemOfThis("sign") being null
        if (Objects.nonNull(wood.getItemOfThis(output)) || Objects.equals(wood.getNamespace(), "tfc")) {
            sawmill_Recipe(recipeName, input, wood.getItemOfThis(output), handler, manager, wood);
        }
    }

    public void sawmill_Recipe(String recipeName, Item input, Item output,
                               ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {

        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).getInputStream()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject getRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            JsonObject getIngredient = getRecipe.getAsJsonObject("ingredient");

            // Editing the JSON recipe
            if (getIngredient.has("tag")) {
                getIngredient.addProperty("tag", wood.getNamespace() + ":" + wood.getTypeName() + "_logs");
            } else { // getIngredient.has("item")
                getIngredient.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
            }
            getRecipe.addProperty("result", // if statement, a workaround for tfc's wood.signItem being null
                    (Objects.equals(wood.getNamespace(), "tfc") && Objects.equals(output, wood.getItemOfThis("sign")))
                            ? wood.getNamespace() + ":wood/sign/" + wood.getTypeName()
                            : Objects.requireNonNull(output.getRegistryName()).toString()
            );

        } catch (IOException e) {
            WoodGood.LOGGER.error("{Woodworks Module} sawmill_recipe(): " + e);
        }

        // filenameBuilder: <woodType>_<blockType>_from_<woodType>_<logs|planks>_sawing
        String[] nameSplit = recipeName.split("_(?!gate|plate)");
        String filenameBuilder = wood.getAppendableId() + "_" + nameSplit[1] + "_from_" + wood.getTypeName() + "_" + nameSplit[4] + "_sawing";

        handler.dynamicPack.addJson(WoodGood.res(this.shortenedId() + "/" + filenameBuilder), recipe, ResType.RECIPES);
    }

    public void craftingShaped_Recipe(String recipeName, Item input, Item output,
                                      ServerDynamicResourcesHandler handler, ResourceManager manager, WoodType wood) {
        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).getInputStream()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject getRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            // Editing the JSON recipe
            getRecipe.getAsJsonObject("key").getAsJsonObject("#")
                    .addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
            getRecipe.getAsJsonObject("result")
                    .addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());
        } catch (IOException e) {
            WoodGood.LOGGER.error("{Woodworks Module} craftingShaped_Recipe(): " + e);
        }

        // for filenameBuilder
        String[] nameSplit = recipeName.split("_");
        String filenameBuilder = wood.getTypeName() + "_" + nameSplit[1];

        handler.dynamicPack.addJson(WoodGood.res(filenameBuilder), recipe, ResType.RECIPES);
    }

    public void leaves_Recipe(String recipeName, Item input, Item output,
                              ServerDynamicResourcesHandler handler, ResourceManager manager, LeavesType leaves) {

        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).getInputStream()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject getRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            JsonObject getIngredients = getRecipe.getAsJsonArray("ingredients").get(0).getAsJsonObject();

            // Editing the JSON recipe
            getIngredients.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
            getRecipe.getAsJsonObject("result")
                    .addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());

        } catch (IOException e) {
            WoodGood.LOGGER.error("{Woodworks Module} leaves_Recipe(): " + e);
        }

        handler.dynamicPack.addJson(WoodGood.res(leaves.getTypeName() + "_leaf_pile"), recipe, ResType.RECIPES);

    }

    public void leaves_craftingShaped(String recipeName, Item input, Item output,
                                      ServerDynamicResourcesHandler handler, ResourceManager manager, LeavesType leaves) {
        ResourceLocation recipeLocation = modRes("recipes/" + recipeName + ".json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).getInputStream()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            // VARIABLES
            JsonObject getRecipe = recipe.getAsJsonArray("recipes")
                    .get(0).getAsJsonObject().getAsJsonObject("recipe");

            // Editing the JSON recipe
            getRecipe.getAsJsonObject("key").getAsJsonObject("#")
                    .addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
            getRecipe.getAsJsonObject("result")
                    .addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());
        } catch (IOException e) {
            WoodGood.LOGGER.error("{Woodworks Module} craftingShaped_Recipe(): " + e);
        }

        // filenameBuilder: <leavesType>_leaves_from_leaf_piles
        String filenameBuilder = leaves.getTypeName() + "_leaves_from_leaf_piles";

        handler.dynamicPack.addJson(WoodGood.res(filenameBuilder), recipe, ResType.RECIPES);
    }

    @Override
    // Textures
    public void addDynamicClientResources (ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

         try (TextureImage normal = TextureImage.open(manager, modRes("entity/chest/oak/normal"));
             TextureImage normal_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_normal_o"));
             TextureImage left = TextureImage.open(manager, modRes("entity/chest/oak/normal_left"));
             TextureImage left_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_left_o"));
             TextureImage right = TextureImage.open(manager, modRes("entity/chest/oak/normal_right"));
             TextureImage right_m = TextureImage.open(manager, WoodGood.res("model/oak_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, WoodGood.res("model/oak_chest_right_o"));
             TextureImage normal_t = TextureImage.open(manager, WoodGood.res("model/trapped_chest_left"));
             TextureImage left_t = TextureImage.open(manager, WoodGood.res("model/trapped_chest_right"));
             TextureImage right_t = TextureImage.open(manager, WoodGood.res("model/trapped_chest_normal"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            chests.blocks.forEach((wood, block) -> {

                BlueprintChestBlock b = (BlueprintChestBlock) block;
                String folderPath = "entity/chest/";

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

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
                    // FRONT
                    {
                        ResourceLocation res =  WoodGood.res(folderPath + wood.getTypeName() + "/normal");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = WoodGood.res(folderPath + wood.getTypeName() + "/trapped");

                            var img = respriterNormal.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterNormalO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();

                            trapped.applyOverlayOnExisting(normal_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    // LEFT
                    {
                        ResourceLocation res = WoodGood.res(folderPath + wood.getTypeName() + "/normal_left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = WoodGood.res(folderPath + wood.getTypeName()+ "/trapped_left");

                            var img = respriterLeft.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterLeftO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(left_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    // RIGHT
                    {
                        ResourceLocation res = WoodGood.res(folderPath + wood.getTypeName() + "/normal_right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = WoodGood.res(folderPath + wood.getTypeName() + "/trapped_right");

                            var img = respriterRight.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterRightO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(right_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }

                ChestManager.putChestInfo(WoodGood.MOD_ID, wood.getTypeName(), false);
                ChestManager.putChestInfo(WoodGood.MOD_ID, wood.getTypeName(), true);

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }
}

