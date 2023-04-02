package net.mehvahdjukaar.every_compat.modules.forge.pokecube;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;

import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import pokecube.core.init.ItemGenerator;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.BlockInit;
import pokecube.legends.recipes.LegendsDistorticRecipeSerializer;


public class PokecubeLegendsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> DISTORTIC_PLANKS;
    public final SimpleEntrySet<WoodType, Block> DISTORTIC_STAIRS;
    public final SimpleEntrySet<WoodType, Block> DISTORTIC_SLABS;

    public PokecubeLegendsModule(String modId) {
        super(modId, "pcl");
        CreativeModeTab tab = PokecubeLegends.TAB_DECORATIONS;

        TemplateRecipeManager.registerTemplate(modRes("legends_recipe"), MirrorRecipeTemplate::new);

        DISTORTIC_PLANKS = SimpleEntrySet.builder(WoodType.class, "planks", "distortic",
                        BlockInit.DISTORTIC_OAK_PLANKS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_planks"))
                .addTag(modRes("legends_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("legends_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_PLANKS);

        DISTORTIC_STAIRS = SimpleEntrySet.builder(WoodType.class, "stairs", "distortic",
                        BlockInit.DISTORTIC_OAK_STAIRS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ItemGenerator.GenericStairs(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_stairs"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_STAIRS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_STAIRS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_STAIRS);

        DISTORTIC_SLABS = SimpleEntrySet.builder(WoodType.class, "slab", "distortic",
                        BlockInit.DISTORTIC_OAK_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_slab"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_SLABS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_SLABS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_SLABS);
    }

    public static class MirrorFinishedRecipe implements FinishedRecipe {

        private final Ingredient ingredient;
        private final ItemStack result;
        private final Block block;
        private final ResourceLocation id;
        public final ResourceKey<Level> dimId;
        private final Advancement.Builder advancement;
        protected final ResourceLocation advancementId;
//        public final String group;


        public MirrorFinishedRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, Block block, Advancement.Builder advancement, ResourceLocation advancementId, ResourceLocation dimId) {
            this.id = id;
            this.ingredient = ingredient;
            this.result = result;
            this.block = block;
            this.dimId = ResourceKey.create(Registry.DIMENSION_REGISTRY, dimId);
            this.advancement = advancement;
            this.advancementId = advancementId;
//            this.group = group;
        }


        public void serializeRecipeData(JsonObject json) {
//            if (!this.group.isEmpty()) {
//                json.addProperty("group", this.group);
//            }
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
            return LegendsDistorticRecipeSerializer.SERIALIZER_DISTORTIC;
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

    public class MirrorRecipeTemplate implements IRecipeTemplate<MirrorFinishedRecipe> {

        private final List<Object> conditions = new ArrayList<>();

        public final Block block;
        public final Ingredient input;
        public final ItemStack result;
        public final JsonElement inputElement;
        public final ResourceLocation blockId;
        public final ResourceLocation dimID;
        public final String group;

        public MirrorRecipeTemplate(JsonObject json) {
            var g = json.get("group");
            this.group = g == null ? "" : g.getAsString();

            this.inputElement = GsonHelper.isArrayNode(json, "input") ? GsonHelper.getAsJsonArray(json, "input") : GsonHelper.getAsJsonObject(json, "input");
            this.result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            this.blockId = new ResourceLocation(GsonHelper.getAsString(json, "blockId"));
            this.dimID = new ResourceLocation(GsonHelper.getAsString(json, "dimId"));
            this.block = ForgeRegistries.BLOCKS.getValue(blockId);
            this.input = Ingredient.fromJson((JsonElement)inputElement);
        }

        @Override
        public <T extends BlockType> MirrorFinishedRecipe createSimilar(
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

            Ingredient newIng = null;
            for (var in : input.getItems()) {
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
            return new MirrorFinishedRecipe(res, newIng, newResult, block, advancement,
                    modRes("recipes/" + "pokecube_legends/" + "distortic_planks" + "/" + res.getPath()), dimID);
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
