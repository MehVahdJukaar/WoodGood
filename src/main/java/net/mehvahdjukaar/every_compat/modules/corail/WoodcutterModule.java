package net.mehvahdjukaar.every_compat.modules.corail;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import ovh.corail.woodcutter.block.WoodcutterBlock;
import ovh.corail.woodcutter.item.WoodcutterItem;
import ovh.corail.woodcutter.registry.ModBlocks;
import ovh.corail.woodcutter.registry.ModTabs;

import java.util.List;
import java.util.Objects;

//Support v2.3.9
public class WoodcutterModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> WOODCUTTER;

    public WoodcutterModule(String modId) {
        super(modId, "cwr");

        WOODCUTTER = SimpleEntrySet.builder(WoodType.class, "woodcutter",
                () -> getModBlock("oak_woodcutter"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new WoodcutterBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("woodcutters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("vanilla_woodcutters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("woodcutters"), Registry.ITEM_REGISTRY)
                .addTag(modRes("vanilla_woodcutters"), Registry.ITEM_REGISTRY)
                .addCustomItem((w, b, p) -> new WoodcutterItem(b))
                .setTab(() -> ModTabs.mainTab)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(WOODCUTTER);
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        WOODCUTTER.items.forEach((wood, item) -> {
                                                // Crafting woodcutter Recipe
            JsonArray pattern = new JsonArray();
            pattern.add(" 1 ");
            pattern.add("000");

            JsonObject ingredient = new JsonObject();
            ingredient.addProperty("item", Objects.requireNonNull(wood.planks.getRegistryName()).toString());

            JsonObject tag = new JsonObject();
            if (wood.getNamespace().equals("tfc") || wood.getNamespace().equals("afc")) {
                tag.addProperty("tag", "forge:ingots/wrought_iron");
            }
            else { tag.addProperty("tag", "forge:ingots/iron"); }

            JsonObject key = new JsonObject();
            key.add("0", ingredient);
            key.add("1", tag);

            JsonObject result = new JsonObject();
            result.addProperty("item", Objects.requireNonNull(item.getRegistryName()).toString());

            JsonObject recipe = new JsonObject();
            recipe.addProperty("type", "minecraft:crafting_shaped");
            recipe.addProperty("group", "woodcutter");
            recipe.add("pattern", pattern);
            recipe.add("key", key);
            recipe.add("result", result);

            handler.dynamicPack.addJson(WoodGood.res(this.shortenedId() + "/" + wood.getAppendableId() + "_woodcutter"), recipe, ResType.RECIPES);

                                                //  Recipes for woodcutter
            createRecipeIfNotNull("button", wood, handler);
            createRecipeIfNotNull("door", wood, handler);
            createRecipeIfNotNull("fence", wood, handler);
            createRecipeIfNotNull("fence_gate", wood, handler, false);
            createRecipeIfNotNull("planks", wood, handler, false);
            createRecipeIfNotNull("boat", wood, handler, false);
            createRecipeIfNotNull("pressure_plate", wood, handler);
            createRecipeIfNotNull("sign", wood, handler);
            createRecipeIfNotNull("slab", wood, handler);
            createRecipeIfNotNull("stairs", wood, handler);
            createRecipeIfNotNull("trapdoor", wood, handler);
        });
    }

    public void woodcuttingRecipe(Item input, Item output, int count, WoodType wood, ServerDynamicResourcesHandler handler) {

        // filename: <type>_sign_from_<type>_log
        String filenameBuilder;

        // ONLY TFC & AFC: Change wood/planks/<type>_sign_from_wood/planks/<type> to filename format (above)
        if (Objects.equals(wood.getNamespace(), "tfc") || Objects.equals(wood.getNamespace(), "afc")) {
            String[] outputSplit = output.toString().split("/");
            String[] inputSplit = input.toString().split("/");
            filenameBuilder = outputSplit[2] + "_from_" + inputSplit[2] + "_" + inputSplit[1];
        }
        else {
            filenameBuilder = output + "_from_" + input;
        }

        // Creating a new JSON file
        JsonObject ingredient = new JsonObject();
        if (Objects.equals(input, wood.log.asItem())) {
            // alpha from regions_unexplored doesn't have a tag
            if (Objects.equals(wood.getNamespace(),"regions_unexplored") && Objects.equals(wood.getTypeName(),"alpha")) {
                ingredient.addProperty("item", "regions_unexplored:alpha_log");
            }
            else {
                ingredient.addProperty("tag", wood.getNamespace() + ":" + wood.getTypeName() + "_logs");
            }
        } else {
            ingredient.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
        }

        // boolean for "result"
        boolean isSignFromTFC = (Objects.equals(wood.getNamespace(), "tfc") && Objects.equals(output, wood.getItemOfThis("sign")));
        boolean isBoatFromTFC = (Objects.equals(wood.getNamespace(), "tfc") && Objects.equals(output, wood.getItemOfThis("boat")));
        String outputPath;
        if (isBoatFromTFC) {
            outputPath = ":wood/boat/";
        }
        else {
            outputPath = ":wood/sign/";
        }

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "corail_woodcutter:woodcutting");
        recipe.add("ingredient", ingredient);
        recipe.addProperty("result", // if statement, a workaround for tfc's wood.signItem being null
            (isBoatFromTFC || isSignFromTFC) ? wood.getNamespace() + ":wood/" + outputPath + "/" + wood.getTypeName() :
            Objects.requireNonNull(input.getRegistryName()).toString());
        recipe.addProperty("count", count);

        handler.dynamicPack.addJson(WoodGood.res(
        this.shortenedId() + "/" + wood.getNamespace() + "/woodcutting/" + filenameBuilder),
                recipe, ResType.RECIPES);
    }


    // if the block is null, then skip the creation of the recipe
    public void createRecipeIfNotNull(String output, WoodType wood, ServerDynamicResourcesHandler handler) {
        createRecipeIfNotNull(output, wood, handler, true);
    }

    public void createRecipeIfNotNull(String output, WoodType wood, ServerDynamicResourcesHandler handler, boolean planksIncluded) {
        if (Objects.equals(output, "slab")) { // UNIQUE COUNT
            woodcuttingRecipe(wood.log.asItem(), wood.getItemOfThis(output), 8, wood, handler);
            woodcuttingRecipe(wood.planks.asItem(), wood.getItemOfThis(output), 2, wood, handler);
        }
        else { // Objects.nonNull(wood.getItemOfThis(output))
            woodcuttingRecipe(wood.log.asItem(), wood.getItemOfThis(output), 4, wood, handler);
            if (planksIncluded) {
                woodcuttingRecipe(wood.planks.asItem(), wood.getItemOfThis(output), 1, wood, handler);
            }
        }
    }

    @Override
    public void onModSetup() {
        super.onModSetup();
        ModBlocks.WOODCUTTERS.addAll(WOODCUTTER.blocks.values());
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty", "twilightforest", "tropicraft",
                "corail_woodcutter_extension_byg", "quark");
    }

}
