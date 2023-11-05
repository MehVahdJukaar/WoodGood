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
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pokecube.core.init.ItemGenerator;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.LegendsCreativeTabs;
import pokecube.legends.recipes.LegendsDistorticRecipeSerializer;


public class PokecubeLegendsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> distorticPlanks;
    public final SimpleEntrySet<WoodType, Block> distorticStairs;
    public final SimpleEntrySet<WoodType, Block> DISTORTICSLABS;

    public PokecubeLegendsModule(String modId) {
        super(modId, "pcl");
        var tab = LegendsCreativeTabs.BUILDING_BLOCKS_TAB;
        TemplateRecipeManager.registerTemplate(modRes("legends_recipe"), MirrorRecipeTemplate::new);

        distorticPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "distortic",
                        BlockInit.DISTORTIC_OAK_PLANKS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_planks"))
                .addTag(modRes("legends_planks"), Registries.BLOCK)
                .addTag(modRes("legends_planks"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(tab)
                .build();

        this.addEntry(distorticPlanks);

        distorticStairs = SimpleEntrySet.builder(WoodType.class, "stairs", "distortic",
                        BlockInit.DISTORTIC_OAK_STAIRS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ItemGenerator.GenericStairs(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_stairs"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(tab)
                .build();

        this.addEntry(distorticStairs);

        DISTORTICSLABS = SimpleEntrySet.builder(WoodType.class, "slab", "distortic",
                        BlockInit.DISTORTIC_OAK_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_slab"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(tab)
                .build();

        this.addEntry(DISTORTICSLABS);
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
            this.dimId = ResourceKey.create(Registries.DIMENSION, dimId);
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
        public @NotNull ResourceLocation getId() {
            return id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
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
            ItemStack newResult = new ItemStack(newRes);
            if (this.result.hasTag()) newResult.setTag(this.result.getOrCreateTag().copy());
            if (id == null) id = Utils.getID(newRes.asItem()).toString();

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
