package net.mehvahdjukaar.every_compat.modules.neoforge.abnormal;

import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.common_classes.*;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
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

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;
import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.IsBambooLike;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

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
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTag(ResourceLocation.parse("blueprint:wooden_bookshelves"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .addTexture(modRes("block/chiseled_acacia_bookshelf_empty"))
                .addTextureM(modRes("block/chiseled_acacia_bookshelf_occupied"),
                        EveryCompat.res("block/acacia_chiseled_bookshelf_occupied_m"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_side"))
                .addTexture(modRes("block/chiseled_acacia_bookshelf_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Blocks.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTag(ResourceLocation.parse("blueprint:wooden_chiseled_bookshelves"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .addTexture(modRes("block/oak_boards"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_boards"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .addTag(ResourceLocation.parse("quark:ladders"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("blueprint:wooden_ladders"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .defaultRecipe()
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
                .addTag(ResourceLocation.parse("blueprint:wooden_beehives"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .addTag(ResourceLocation.parse("blueprint:wooden_chests"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("quark:revertable_chests"), Registries.ITEM)
                .addTag(ResourceLocation.parse("quark:boatable_chests"), Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .addTag(ResourceLocation.parse("blueprint:wooden_trapped_chests"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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
                .requiresChildren(LOG) //REASON:
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("leaf_piles"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
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

        ResourceLocation recipeLocation = (IsBambooLike(newWoodType))
                ? modRes("bamboo_" + typeOutput + "_from_bamboo_" + fromBambooType + "_sawing")
                : modRes("acacia_" + typeOutput + "_from_acacia_" + typeInput + "_sawing");

        if (manager.getResource(ResType.RECIPES.getPath(recipeLocation)).isEmpty()) return;

        Recipe<?> recipe = RPUtils.readRecipe(manager, recipeLocation);

        if (recipe instanceof SawmillRecipe sawmillRecipe) {
            boolean isIngredientModified = false;
            Ingredient newIngredient = null;

            var oldIngredients = sawmillRecipe.getIngredients().getFirst().getItems();
            for (ItemStack oldItemStack : oldIngredients) {

                Item oldItem = oldItemStack.getItem();

                // if the recipe has Items.BARRIER, then it's using TAG as ingredient
                if (oldItem != Items.BARRIER) {
                    WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                    Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);

                    if (newItem != null) {
                        newIngredient = Ingredient.of(new ItemStack(newItem));
                        isIngredientModified = true;
                    }
                }
                else {
                    isIngredientModified = true;
                    ResourceLocation newTag = getATagOrCreateANew("logs", "blocks", newWoodType, sink, manager);
                    newIngredient = Ingredient.of(TagKey.create(Registries.ITEM, newTag));

                }
            }

            if (isIngredientModified) {
                Item oldItem = sawmillRecipe.result.getItem();
                int count = sawmillRecipe.result.getCount();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);

                if (oldWoodType == VanillaWoodTypes.ACACIA || oldWoodType == VanillaWoodTypes.BAMBOO) {
                    Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);
                    if (newItem != null) {
                        SawmillRecipe newRecipe = new SawmillRecipe(sawmillRecipe.getGroup(), newIngredient, new ItemStack(newItem, count));

                        String appendedPath = recipeLocation.withPrefix(shortenedId() + "/" + newWoodType.getNamespace() + "/").getPath();
                        String newPath = (oldWoodType == VanillaWoodTypes.ACACIA)
                                ? appendedPath.replace("acacia", newWoodType.getTypeName())
                                : appendedPath.replace("bamboo", newWoodType.getTypeName());

                        sink.addRecipe(new RecipeHolder<>(
                                EveryCompat.res(newPath),
                                newRecipe)
                        );
                    }
                }
            }
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
