package net.mehvahdjukaar.every_compat.modules.forge.xerca;

import com.google.gson.JsonObject;
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
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.Nullable;
import xerca.xercamod.common.DecoCreativeTab;
import xerca.xercamod.common.block.BlockCarvedLog;
import xerca.xercamod.common.block.Blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class XercaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> carved1;
    public final SimpleEntrySet<WoodType, Block> carved2;
    public final SimpleEntrySet<WoodType, Block> carved3;
    public final SimpleEntrySet<WoodType, Block> carved4;
    public final SimpleEntrySet<WoodType, Block> carved5;
    public final SimpleEntrySet<WoodType, Block> carved6;
    public final SimpleEntrySet<WoodType, Block> carved7;
    public final SimpleEntrySet<WoodType, Block> carved8;

    public XercaModule(String modId) {
        super(modId, "x");
        CreativeModeTab tab = DecoCreativeTab.TAB_BUILDING_BLOCKS;

        TemplateRecipeManager.registerTemplate(modRes("carving"), CarvingRecipeTemplate::new);

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        Blocks.CARVED_DARK_OAK_1, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_1_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_1_side_abcd"))
                .addRecipe(modRes("carving/carved_dark_oak_1_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_1_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        Blocks.CARVED_DARK_OAK_2, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_2_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_2_side_abcd"))
                .addRecipe(modRes("carving/carved_dark_oak_2_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_2_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        Blocks.CARVED_DARK_OAK_3, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_3_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_3_side_abcd"))
                .addRecipe(modRes("carving/carved_dark_oak_3_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_3_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        Blocks.CARVED_DARK_OAK_4, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_4_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_4_side_abcd"))
                .addRecipe(modRes("carving/carved_dark_oak_4_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_4_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        Blocks.CARVED_DARK_OAK_5, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_5_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_5_side_ab"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_5_side_cd"))
                .addRecipe(modRes("carving/carved_dark_oak_5_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_5_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        Blocks.CARVED_DARK_OAK_6, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_bottom"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_side_a"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_side_b"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_side_c"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_6_side_d"))
                .addRecipe(modRes("carving/carved_dark_oak_6_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_6_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        Blocks.CARVED_DARK_OAK_7, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_7_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_7_side_a"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_7_side_bcd"))
                .addRecipe(modRes("carving/carved_dark_oak_7_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_7_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        Blocks.CARVED_DARK_OAK_8, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/carved_wood/carved_dark_oak_8_top"))
                .addTexture(modRes("block/carved_wood/carved_dark_oak_8_side_abcd"))
                .addRecipe(modRes("carving/carved_dark_oak_8_from_dark_oak_log_carving"))
                .addRecipe(modRes("carving/carved_dark_oak_8_from_stripped_dark_oak_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved8);


    }

    public static class CarvingFinishedRecipe implements FinishedRecipe {
        protected final Ingredient ingredient;
        protected final ItemStack result;
        protected final ResourceLocation id;
        protected final String group;
        private final Advancement.Builder advancement;
        protected final ResourceLocation advancementId;


        public CarvingFinishedRecipe(
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
            return (RecipeSerializer) xerca.xercamod.common.item.Items.CARVING.get();
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

    public class CarvingRecipeTemplate implements IRecipeTemplate<CarvingFinishedRecipe> {

        private final List<Object> conditions = new ArrayList<>();

        public final ItemStack result;
        public final String group;
        public final Ingredient ingredient;

        public CarvingRecipeTemplate(JsonObject json) {
            var g = json.get("group");
            this.group = g == null ? "" : g.getAsString();

            this.ingredient = Ingredient.fromJson(json.get("ingredient"));
            String s1 = GsonHelper.getAsString(json, "result");
            int i = GsonHelper.getAsInt(json, "count");
            this.result = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
        }

        @Override
        public <T extends BlockType> CarvingFinishedRecipe createSimilar(
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

            advancement.addCriterion("has_logs", InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));
            var res = (new ResourceLocation(id + "_from_log_carving"));
            var res2 = new ResourceLocation(id);
            return new CarvingFinishedRecipe(res, group, newIng, newResult, advancement,
                    modRes("recipes/" + "xercamod/" + "carving/" + res.getPath()));
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
