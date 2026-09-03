package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityRenderer;
import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityWithoutLevelRenderer;
import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.common.block.TrappedClosetBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.common.block.entity.TrappedClosetBlockEntity;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.*;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.*;
import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.isBambooLike;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

///SUPPORT: v3.0.2+
@SuppressWarnings({"removal", "deprecation"})
public class WoodworksModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> chiseled_bookshelves;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> beehives;
    public final SimpleEntrySet<WoodType, Block> chests, trappedChests; // WoodType
    public final SimpleEntrySet<WoodType, Block> closet, trappedCloset; // BambooType
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
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_chiseled_bookshelves"), Registries.BLOCK, Registries.ITEM)
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
                .addTexture(modRes("block/oak_boards"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_boards"), Registries.BLOCK, Registries.ITEM)
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
                .addTag(new ResourceLocation("forge:ladders"), Registries.BLOCK, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_ladders"), Registries.BLOCK, Registries.ITEM)
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
                                Utils.copyPropertySafe(woodType.planks).strength(2.5F).sound(woodType.getSound())
                        )
                )
                .addCondition(woodType -> !woodType.isBambooLike())
                .addTile(abwwChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_chests"), Registries.ITEM, Registries.BLOCK)
                .addTag(new ResourceLocation("quark","revertable_chests"), Registries.ITEM)
                .addTag(new ResourceLocation("quark","boatable_chests"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "chest", "trapped",
                        getModBlock("trapped_oak_chest"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CompatTrappedChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(woodType.planks).strength(2.5F).sound(woodType.getSound())
                        )
                )
                .addCondition(woodType -> !woodType.isBambooLike())
                .addTile(abwwTrappedBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_trapped_chests"), Registries.BLOCK, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(trappedChests);

        closet = SimpleEntrySet.builder(WoodType.class, "closet",
                        getModBlock("bamboo_closet"), () -> VanillaWoodTypes.BAMBOO,
                        woodType -> {
                            String name = BlueprintChestMaterials.registerMaterials(EveryCompat.MOD_ID,
                                    woodType.createPathWith(shortenedId(), ""), false
                            );

                            return new ClosetBlock(name,
                                    Utils.copyPropertySafe(woodType.planks).strength(2.5F).sound(woodType.getSound())
                            );
                        }
                )
                .addCondition(WoodType::isBambooLike)
                .addTile(getModTile("closet"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(new ResourceLocation("blueprint:wooden_chests"), Registries.ITEM, Registries.BLOCK)
                .addTag(new ResourceLocation("quark","revertable_chests"), Registries.ITEM)
                .addTag(new ResourceLocation("quark","boatable_chests"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new BEWLRFuelBlockItem(block, new Item.Properties(), () -> () -> closetBEWLR(false), 300))
                .build();
        this.addEntry(closet);

        trappedCloset = SimpleEntrySet.builder(WoodType.class, "closet", "trapped",
                        getModBlock("trapped_bamboo_closet"), () -> VanillaWoodTypes.BAMBOO,
                        woodType -> {
                            String name = BlueprintChestMaterials.registerMaterials(EveryCompat.MOD_ID,
                                    woodType.createPathWith(shortenedId(), ""), true
                            );

                            return new TrappedClosetBlock(name,
                                    Utils.copyPropertySafe(woodType.planks).strength(2.5F).sound(woodType.getSound())
                            );
                        }
                )
                .addCondition(WoodType::isBambooLike)
                .addTile(getModTile("trapped_closet"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registries.BLOCK)
                .addTag(new ResourceLocation("blueprint:wooden_trapped_chests"), Registries.BLOCK, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new BEWLRFuelBlockItem(block, new Item.Properties(), () -> () -> closetBEWLR(true), 300))
                .build();
        this.addEntry(trappedCloset);

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

    @OnlyIn(Dist.CLIENT)
    private static BEWLRBlockItem.LazyBEWLR closetBEWLR(boolean trapped) {
        return trapped
                ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) ->
                new DrawerBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet,
                        new TrappedClosetBlockEntity(BlockPos.ZERO, WoodworksBlocks.TRAPPED_BAMBOO_CLOSET.get().defaultBlockState())))
                : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) ->
                new DrawerBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet,
                        new ClosetBlockEntity(BlockPos.ZERO, WoodworksBlocks.BAMBOO_CLOSET.get().defaultBlockState())));
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
            noWoodTypeName();
        }
    }

    private class abwwTrappedBlockEntity extends CompatChestBlockEntity {
        public abwwTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(trappedChests.getTile(), pos, state);
            noWoodTypeName();
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        CompatChestBlockRenderer.register(event, trappedChests.getTile(CompatChestBlockEntity.class), shortenedId());

        event.register(closet.getTile(ClosetBlockEntity.class), DrawerBlockEntityRenderer::new);

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
            createSawmillRecipe(PLANKS, "logs", wood.getItemOfThis(PLANKS), sink, manager, wood);
            createSawmillRecipe("boards", "logs", boards.items.get(wood), sink, manager, wood);
            createSawmillRecipe("ladder", "logs", ladder, sink, manager, wood);
            createSawmillRecipe(BUTTON, "logs", wood.getItemOfThis(BUTTON), sink, manager, wood);
            createSawmillRecipe(DOOR, "logs", wood.getItemOfThis(DOOR), sink, manager, wood);
            createSawmillRecipe(FENCE, "logs", wood.getItemOfThis(FENCE), sink, manager, wood);
            createSawmillRecipe(FENCE_GATE, "logs", wood.getItemOfThis(FENCE_GATE), sink, manager, wood);
            createSawmillRecipe(SIGN, "logs", wood.getItemOfThis(SIGN), sink, manager, wood);
            createSawmillRecipe(SLAB, "logs", wood.getItemOfThis(SLAB), sink, manager, wood);
            createSawmillRecipe(STAIRS, "logs", wood.getItemOfThis(STAIRS), sink, manager, wood);
            createSawmillRecipe(TRAPDOOR, "logs", wood.getItemOfThis(TRAPDOOR), sink, manager, wood);

            // - from PLANKS
            createSawmillRecipe("boards", PLANKS, boards.items.get(wood), sink, manager, wood);
            createSawmillRecipe("ladder", PLANKS, ladder, sink, manager, wood);
            createSawmillRecipe(BUTTON, PLANKS, wood.getItemOfThis(BUTTON), sink, manager, wood);
            createSawmillRecipe(FENCE, PLANKS, wood.getItemOfThis(FENCE), sink, manager, wood);
            createSawmillRecipe(SLAB, PLANKS, wood.getItemOfThis(SLAB), sink, manager, wood);
            createSawmillRecipe(STAIRS, PLANKS, wood.getItemOfThis(STAIRS), sink, manager, wood);
        }));
    }

    public void createSawmillRecipe(String typeOutput, String typeInput, Item itemOutput, ResourceSink sink, ResourceManager manager, WoodType newWoodType) {
        if (itemOutput == null) return;

        String fromBambooType = typeInput.matches("logs") ? "blocks" : typeInput;

        ResourceLocation recipeLocation = (isBambooLike(newWoodType))
                ? modRes("bamboo_" + typeOutput + "_from_bamboo_" + fromBambooType + "_sawing")
                : modRes("acacia_" + typeOutput + "_from_acacia_" + typeInput + "_sawing");

        if (manager.getResource(ResType.RECIPES.getPath(recipeLocation)).isEmpty()) return;

        Recipe<?> recipe = RPUtils.readRecipe(manager, ResType.RECIPES.getPath(recipeLocation));

        if (recipe instanceof SawmillRecipe sawmillRecipe) {
            boolean isIngredientModified = false;
            Ingredient newIngredient = null;

            var oldIngredients = sawmillRecipe.getIngredients();

            if (oldIngredients.get(0).toJson().getAsJsonObject().has("tag")) {
                isIngredientModified = true;
                ResourceLocation newTag = getATagOrCreateANew("logs", "blocks", newWoodType, sink, manager);
                newIngredient = Ingredient.of(TagKey.create(Registries.ITEM, newTag));

            }
            else {
                Item oldItem = oldIngredients.get(0).getItems()[0].getItem();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);

                if (newItem != null) {
                    newIngredient = Ingredient.of(new ItemStack(newItem));
                    isIngredientModified = true;
                }
            }

            if (isIngredientModified) {
                Item oldItem = sawmillRecipe.result.getItem();
                int count = sawmillRecipe.result.getCount();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);

                if (oldWoodType == VanillaWoodTypes.ACACIA || oldWoodType == VanillaWoodTypes.BAMBOO) {
                    Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);
                    if (newItem != null) {
                        ItemStack newResult = new ItemStack(BuiltInRegistries.BLOCK.get(Utils.getID(newItem)), count);

                        String appendedPath = recipeLocation.withPrefix(shortenedId() + "/" + newWoodType.getNamespace() + "/").getPath();
                        String newPath = (oldWoodType == VanillaWoodTypes.ACACIA)
                                ? appendedPath.replace("acacia", newWoodType.getTypeName())
                                : appendedPath.replace("bamboo", newWoodType.getTypeName());

                        CompatSawmillRecipe newRecipe = new CompatSawmillRecipe(EveryCompat.res(newPath), sawmillRecipe.getGroup(), newIngredient, newResult);

                        sink.addJson(EveryCompat.res(newPath), newRecipe.toJson(), ResType.RECIPES);
                    }
                }
            }

        }
    }


    public static class CompatSawmillRecipe extends SawmillRecipe {

        public CompatSawmillRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
            super(id, group, ingredient, result);
        }

        public JsonObject toJson() {
            JsonObject json = new JsonObject();

            json.addProperty("type", "forge:conditional");
            json.add("recipes", new JsonArray());

            JsonArray recipes = json.getAsJsonArray("recipes");
            recipes.add(new JsonObject());

            JsonObject recipesJson = recipes.get(0).getAsJsonObject();
            recipesJson.add("conditions", new JsonArray());

            JsonArray conditions = recipesJson.getAsJsonArray("conditions");
            conditions.add(new JsonObject());
            JsonObject conditionsJson = conditions.get(0).getAsJsonObject();
            conditionsJson.addProperty("type", "woodworks:config");
            conditionsJson.addProperty("value", "sawmill");

            recipesJson.add("recipe", new JsonObject());

            JsonObject recipe = recipesJson.getAsJsonObject("recipe");
            recipe.addProperty("type", "woodworks:sawmill");
            recipe.add("ingredient", ingredient.toJson());
            recipe.addProperty("count", result.getCount());
            recipe.addProperty("result", Utils.getID(result.getItem()).toString());

            return json;
        }
    }

    // Textures
    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) ->
            trappedChests.blocks.forEach((wood, block) -> {
                // mods like environmental already ship chest textures for their own wood. reuse those over a recolor
                if (copyModProvidedChestTextures(sink, manager, shortenedId(), wood)) return;

                if (PlatHelper.isModLoaded("quark")
                        && copyHandmadeChestTextures(sink, manager, "q", shortenedId(), wood)) return;

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

        executor.accept((manager, sink) ->
            trappedCloset.blocks.forEach((wood, block) -> {
                setSuffix("/normal", "/trapped");

                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/bamboo/normal"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_m"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_o"),
                        EveryCompat.res("block/abnww/chest/bamboo/trapped_o"),
                        true
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/bamboo/normal_left"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_left_m"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_left_o"),
                        EveryCompat.res("block/abnww/chest/bamboo/trapped_left_o"),
                        true
                );
                // RIGHTdamn
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/bamboo/normal_right"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_right_m"),
                        EveryCompat.res("block/abnww/chest/bamboo/normal_right_o"),
                        EveryCompat.res("block/abnww/chest/bamboo/trapped_right_o"),
                        true
                );
            })
        );
    }


}
