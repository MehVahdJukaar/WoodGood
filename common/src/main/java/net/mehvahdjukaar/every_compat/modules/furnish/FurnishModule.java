package net.mehvahdjukaar.every_compat.modules.furnish;

import com.google.gson.JsonObject;
import io.github.wouink.furnish.Furnish;
import io.github.wouink.furnish.block.*;
import io.github.wouink.furnish.block.util.VoxelShapeHelper;
import io.github.wouink.furnish.setup.FurnishBlocks;
import io.github.wouink.furnish.setup.FurnishRegistries;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class FurnishModule extends SimpleModule {

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

    public FurnishModule(String modId) {
        super(modId, "fur");
        CreativeModeTab tab = Furnish.CREATIVE_TAB;

        TemplateRecipeManager.registerTemplate(modRes("furniture_making"), FurnishRecipeTemplate::new);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        ()->getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Table(Utils.copyPropertySafe(w.log)), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(table);

        squareTable = SimpleEntrySet.builder(WoodType.class, "square_table",
                        ()->getModBlock("oak_square_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new SimpleFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_square_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(squareTable);

        pedestalTable = SimpleEntrySet.builder(WoodType.class, "pedestal_table",
                        ()->getModBlock("oak_pedestal_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new SimpleFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_pedestal_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(pedestalTable);

        bedsideTable = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        ()->getModBlock("oak_bedside_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new InventoryFurniture(Utils.copyPropertySafe(w.log), FurnishRegistries.Drawers_Open_Sound, FurnishRegistries.Drawers_Close_Sound), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_bedside_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(bedsideTable);

        kitchenCabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet",
                        FurnishBlocks.Oak_Kitchen_Cabinet, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new InventoryFurniture(Utils.copyPropertySafe(w.planks), FurnishRegistries.Drawers_Open_Sound, FurnishRegistries.Drawers_Close_Sound))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_kitchen_cabinet"))
                .setTab(() -> tab)
                .build();

        this.addEntry(kitchenCabinet);

        cabinet = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        FurnishBlocks.Birch_Cabinet, () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        ifHasChild(w -> new Cabinet(Utils.copyPropertySafe(w.log), FurnishRegistries.Cabinet_Open_Sound, FurnishRegistries.Cabinet_Close_Sound), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/birch_cabinet_door_right"))
                .addTexture(modRes("block/birch_cabinet_door_left"))
                .addRecipe(modRes("furniture_making/birch_cabinet"))
                .setTab(() -> tab)
                .build();

        this.addEntry(cabinet);

        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        FurnishBlocks.Birch_Wardrobe, () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        ifHasChild(w -> new Wardrobe(Utils.copyPropertySafe(w.log), FurnishRegistries.Cabinet_Open_Sound, FurnishRegistries.Cabinet_Close_Sound), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/birch_wardrobe_door_bottom_right"))
                .addTexture(modRes("block/birch_wardrobe_door_bottom_left"))
                .addTexture(modRes("block/birch_wardrobe_door_top_right"))
                .addTexture(modRes("block/birch_wardrobe_door_top_left"))
                .addRecipe(modRes("furniture_making/birch_wardrobe"))
                .setTab(() -> tab)
                .build();

        this.addEntry(wardrobe);

        stool = SimpleEntrySet.builder(WoodType.class, "stool",
                        FurnishBlocks.Oak_Stool, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Chair(Utils.copyPropertySafe(w.log), Chair.BASE_SHAPES), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_stool"))
                .setTab(() -> tab)
                .build();

        this.addEntry(stool);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        FurnishBlocks.Oak_Chair, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Chair(Utils.copyPropertySafe(w.log),
                                VoxelShapeHelper.getMergedShapes(Chair.BASE_SHAPES, Chair.CHAIR_SEAT)), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_chair"))
                .setTab(() -> tab)
                .build();

        this.addEntry(chair);

        shutter = SimpleEntrySet.builder(WoodType.class, "shutter",
                        FurnishBlocks.Oak_Shutter, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_shutter"))
                .addTexture(modRes("block/oak_shutter"))
                .setTab(() -> tab)
                .build();

        this.addEntry(shutter);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        FurnishBlocks.Oak_Crate, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Crate(Utils.copyPropertySafe(w.planks)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_crate"))
                .addTexture(modRes("block/oak_crate_side"))
                .addTexture(modRes("block/oak_crate_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(crate);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        FurnishBlocks.Oak_Shelf, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shelf(Utils.copyPropertySafe(w.planks)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_shelf"))
                .setTab(() -> tab)
                .build();

        this.addEntry(shelf);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        FurnishBlocks.Oak_Bench, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(Utils.copyPropertySafe(w.planks)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_bench"))
                .setTab(() -> tab)
                .build();

        this.addEntry(bench);

        logBenches = SimpleEntrySet.builder(WoodType.class, "log_bench",
                        FurnishBlocks.Oak_Log_Bench, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LogBench(Utils.copyPropertySafe(w.log)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_log_bench"))
                .addTexture(modRes("block/oak_log_bench_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(logBenches);

        ladder = SimpleEntrySet.builder(WoodType.class, "ladder",
                        FurnishBlocks.Oak_Ladder, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Ladder(Utils.copyPropertySafe(w.log)), "stripped_log"))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_ladder"))
                .setTab(() -> tab)
                .build();

        this.addEntry(ladder);

        coffin = SimpleEntrySet.builder(WoodType.class, "coffin",
                        FurnishBlocks.Jungle_Coffin, () -> WoodTypeRegistry.getValue(new ResourceLocation("jungle")),
                        w -> new Coffin(Utils.copyPropertySafe(w.planks)))
//                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/jungle_coffin"))
                .addTexture(modRes("block/jungle_coffin_sides"))
                .setTab(() -> tab)
                .build();

        this.addEntry(coffin);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        try {
            logBenches.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/oak_log_bench_top", w, id, "oak");

                    var newTexture = topTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Log Bench block texture for for {} : {}", block, e);

                }

            });
            coffin.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/jungle_coffin_sides", w, id, "jungle");

                    var newTexture = topTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Log Bench block texture for for {} : {}", block, e);

                }

            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Log Bench block textures : ", ex);
        }
    }

    private void createTopTexture(TextureImage original, TextureImage newImage) {
        original.forEachFrame((i, x, y) -> {
            int localX = x - original.getFrameX(i);
            int localY = y - original.getFrameY(i);
            if (localX >= 5 && localX <= 10 && localY >= 5 && localY <= 10) {
                newImage.getImage().setPixelRGBA(x - 3, y - 3, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 10 && localY > 0 && localY <= 7) {
                newImage.getImage().setPixelRGBA(x - 6, y, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localY >= 10 && localX > 0 && localX <= 7) {
                newImage.getImage().setPixelRGBA(x, y - 6, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localX >= 10 && localY >= 10) {
                newImage.getImage().setPixelRGBA(x - 6, y - 6, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 10 || localY >= 10) {
                newImage.getImage().setPixelRGBA(x, y, 0);
            }
        });
    }

    public static class FurnishFinishedRecipe implements FinishedRecipe {
        protected final Ingredient ingredient;
        protected final ItemStack result;
        protected final ResourceLocation id;
        protected final String group;
        private final Advancement.Builder advancement;
        protected final ResourceLocation advancementId;


        public FurnishFinishedRecipe(
                ResourceLocation resourceLocation,
                String group,
                Ingredient ingredient,
                ItemStack result, Advancement.Builder advancement, ResourceLocation advancementId
        ) {
            this.id = resourceLocation;
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }


        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }
            json.addProperty("id", this.id.toString());

            json.add("ingredient", ingredient.toJson());

            json.addProperty("result", Utils.getID(result.getItem()).toString());
            json.addProperty("count", result.getCount());
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return FurnishRegistries.Furniture_Recipe_Serializer.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }

    public class FurnishRecipeTemplate implements IRecipeTemplate<FurnishFinishedRecipe> {

        private final List<Object> conditions = new ArrayList<>();

        public final ItemStack result;
        public final String group;
        public final Ingredient ingredient;

        public FurnishRecipeTemplate(JsonObject json) {
            var g = json.get("group");
            this.group = g == null ? "" : g.getAsString();

            this.ingredient = Ingredient.fromJson(json.get("ingredient"));
            String s1 = GsonHelper.getAsString(json, "result");
            int i = GsonHelper.getAsInt(json, "count");
            this.result = new ItemStack( Registry.ITEM.get(new ResourceLocation(s1)), i);
        }

        @Override
        public <T extends BlockType> FurnishFinishedRecipe createSimilar(
                T originalMat, T destinationMat, Item unlockItem, String id) {
            ItemLike newRes = BlockType.changeItemType(this.result.getItem(), originalMat, destinationMat);
            if (newRes == null) {
                throw new UnsupportedOperationException(String.format("Could not convert output item %s from type %s to %s",
                        this.result, originalMat, destinationMat));
            }
            if (newRes.asItem().getItemCategory() == null) {
                Moonlight.LOGGER.error("Failed to generate recipe for {} in block type {}: Output item {} cannot have empty creative tab, skipping", this.result, destinationMat, newRes);
                return null;
            }
            ItemStack newResult = new ItemStack(newRes);
            if (this.result.hasTag()) newResult.setTag(this.result.getOrCreateTag().copy());
            if (id == null) id = Registry.ITEM.getKey(newRes.asItem()).toString();

            Ingredient newIng = ingredient;
            for (var in : ingredient.getItems()) {
                Item it = in.getItem();
                if (it != Items.BARRIER) {
                    ItemLike i = BlockType.changeItemType(it, originalMat, destinationMat);
                    if (i != null) {
                        //converts first ingredient it finds
                        newIng = Ingredient.of(i);
                        break;
                    }
                }
            }
            Advancement.Builder advancement = Advancement.Builder.advancement();

            advancement.addCriterion("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));
            var res = new ResourceLocation(id);
            return new FurnishFinishedRecipe(res, group, newIng, newResult, advancement,
                    modRes("recipes/" + "furnish" + "/" + res.getPath()));
        }

        @Override
        public void addCondition(Object condition) {
            this.conditions.add(condition);
        }

        @Override
        public List<Object> getConditions() {
            return conditions;
        }
    }
}
