package net.mehvahdjukaar.every_compat.modules.forge.xerca;

import com.google.gson.JsonObject;
import io.github.wouink.furnish.setup.FurnishRegistries;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
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
import xerca.xercamod.common.DecoCreativeTab;
import xerca.xercamod.common.block.BlockCarvedLog;
import xerca.xercamod.common.block.Blocks;

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

    //TODO: Remove model transforms once built-in models work
    // Fix recipes

    public XercaModule(String modId) {
        super(modId, "x");
        CreativeModeTab tab = DecoCreativeTab.TAB_BUILDING_BLOCKS;

//        TemplateRecipeManager.registerTemplate(modRes("recipes/carving"), CarvingRecipeTemplate::new);

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        Blocks.CARVED_WARPED_1, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_1_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_1_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_1_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_1_from_stripped_warped_log_carving"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        Blocks.CARVED_WARPED_2, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("xercamod:block/carved_wood/carved_warped", "xercamod:block/carved_wood/carved_oak"))
                .addTexture(modRes("block/carved_wood/carved_warped_2_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_2_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_2_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_2_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        Blocks.CARVED_WARPED_3, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_3_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_3_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_3_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_3_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        Blocks.CARVED_WARPED_4, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_4_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_4_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_4_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_4_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        Blocks.CARVED_WARPED_5, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_5_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_ab"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_cd"))
                .addRecipe(modRes("carving/carved_warped_5_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_5_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        Blocks.CARVED_WARPED_6, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_6_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_bottom"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_b"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_c"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_d"))
                .addRecipe(modRes("carving/carved_warped_6_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_6_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        Blocks.CARVED_WARPED_7, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_7_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_bcd"))
                .addRecipe(modRes("carving/carved_warped_7_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_7_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        Blocks.CARVED_WARPED_8, () -> WoodTypeRegistry.getValue(new ResourceLocation("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_8_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_8_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_8_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_8_from_stripped_warped_log_carving"))
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(carved8);
    }

    private void darkerPalette(Palette p) {
        p.add(p.increaseInner());
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        JsonObject json = new JsonObject();
        json.addProperty("type","xercamod:carving");
        json.add("ingredient", Ingredient.fromJson(json.get("ingredient")).toJson());
        String s1 = GsonHelper.getAsString(json, "result");
        int i = GsonHelper.getAsInt(json, "count");
        json.addProperty("result", Utils.getID((new ItemStack( Registry.ITEM.get(new ResourceLocation(s1)), i)).getItem()).toString());
        json.addProperty("count", (new ItemStack( Registry.ITEM.get(new ResourceLocation(s1)), i)).getCount());

        handler.dynamicPack.addJson(EveryCompat.res("carving"), json, ResType.RECIPES);
    }
}
