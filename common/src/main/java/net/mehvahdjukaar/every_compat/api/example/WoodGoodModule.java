package net.mehvahdjukaar.every_compat.api.example;

import com.ninni.twigs.block.TableBlock;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
///      ┌──────────────────────────────────────────────────────────┐
///      │       WoodGoodModule using Twigs Mod as An Example       │
///      └──────────────────────────────────────────────────────────┘
public class WoodGoodModule extends SimpleModule {

        /// For Blocks
        public final SimpleEntrySet<WoodType, Block> sampleBlock, sampleBlock_2;

        /// For Items
        public final ItemOnlyEntrySet<WoodType, Item> sampleItem;

        public WoodGoodModule(String modId) {
            // an example of shortened ID for TerraFirmaCraft is "tfc", so one for Twigs is "tw"
            super(modId, "tw");

            //  your mod's tab or minecraft's tab can be used for setTabKey() - it can use either ResourceKey or ResourceLocation
            ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;
            ResourceLocation yourModTab = modRes("twig");

            sampleBlock_2 = SimpleEntrySet.builder(WoodType.class,"suffix", "prefix",
                    getModBlock("oak_table"), ()-> VanillaWoodTypes.OAK,
                    woodType -> new TableBlock(Utils.copyPropertySafe(woodType.planks).instabreak())
                    )
                    /// Similar setup as sampleBlock
                    .build();
            this.addEntry(sampleBlock_2);

            sampleBlock = SimpleEntrySet.builder(WoodType.class,"table",
                            getModBlock("oak_table"), ()-> VanillaWoodTypes.OAK,
                            woodType -> new TableBlock(Utils.copyPropertySafe(woodType.planks).instabreak())
                    )
                    ///OPTIONAL: Check if a WoodType or LeavesType has the children required, then block will be generated
                    .requiresChildren("slab", "other_childkey") //REASON: can be for recipes or textures
                    .requiresFromMap(sampleBlock_2.blocks) // If your block required another block for crafting or texturing
                    //NOTE: sampleBlock_2 has to be above of this EntrySet for .requiresFromMap to work properly

                    ///OPTIONAL: Add the block to EntityBlockType
                    .addTile(getModTile("id_of_EntityType"))

                    ///OPTIONAL: Adding block's textures to be generated
                    .addTexture(modRes("block/oak_table"), PaletteStrategies.LOG_SIDE_STANDARD)
                    .addTexture(new ResourceLocation("twigs:block/oak_table_bottom"), PaletteStrategies.LOG_SIDE_STANDARD)
                    .addTextureM(modRes("block/oak_table_top"), modRes("block/mask/oak_table_top_m")) // If the texture has parts that shouldn't be recolored, the mask (black color) can be used to exclude them

                    ///OPTIONAL: Adding tags to the block
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                    .addTag(new ResourceLocation("twigs:tables"), Registries.BLOCK)
                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)

                    ///OPTIONAL: If your block is glasses, then please take a look at below & Looking for // Common Tags which is at the bottom of the class
            // https://github.com/MehVahdJukaar/WoodGood/blob/1.20/common/src/main/java/net/mehvahdjukaar/every_compat/common_classes/TagUtility.java#L186

                    ///REQUIRED: Add block to your mod's tab or Minecraft's tab
                    .setTabKey(yourModTab)

                    ///OPTIONAL: Creating recipes for the block
                    .defaultRecipe() // default: new ResourceLocation("twigs:oak_table") via recipes folder
                    ///OPTIONAL: if the recipe has a different path unlike above
                    .addRecipe(modRes("path/to/recipeFile")) // Do not use "recipes/"

                    ///OPTIONAL: Special cases
                    .copyParentDrop() // copy the loot_table of the baseBlock (oak_table)
                    .copyParentTint() // Applying tinted color to Leaves - Good example is hedge from Quark OR Macaw's Fences & Walls
                    .setRenderType(RenderLayer.CUTOUT) //USAGE: CUTOUT, CUTOUT_MIPPED, SOLID, TRANSLUCENT

                    .build();
            this.addEntry(sampleBlock);

            sampleItem = ItemOnlyEntrySet.builder(WoodType.class,"table",
                            getModItem("oak_table"), ()-> VanillaWoodTypes.OAK,
                            w -> new Item(new Item.Properties())
                    )
                    .addTexture(modRes("item/itemTexture"))
                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)
                    .setTabKey(tab)
                    .defaultRecipe()
                    .addRecipe(modRes("path/to/recipeFile"))
                    .build();
            this.addEntry(sampleItem);

        }

///      ┌──────────────────────────────────────────────────────────┐
///      │     Manaully generating Textures or Modifying models     │
///      └──────────────────────────────────────────────────────────┘
        @Override
        public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
            super.addDynamicClientResources(executor);

            executor.accept((manager, sink) -> {
                /// a code to manually generate textures if `.addTexture()` doesn't generate a good looking texture
                /// Good examples is RegionsUnexploredModule's shrub OR ChippedModule's bundledLog OR ValhelsiaStructuresModule's bundledPosts
                /// You can look at above and other modules for references
            });
        }

///      ┌──────────────────────────────────────────────────────────┐
///      │  Manually generating Recipes or creating tags or adding  │
///      │  blocks to tags                                          │
///      └──────────────────────────────────────────────────────────┘
        @Override
        public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
            super.addDynamicServerResources(executor);

            executor.accept((manager, sink) -> {
                /// Code to manually add tags to blocks or creating a new tags for recipes to be generated
                /// `.addRecipe()` or `.defaultRecipe()` cannot generate recipes that use tags as ingredients
                /// A good example is QuarkModule's hedge where its recipe is using tag as ingredient
            });
        }

///      ┌──────────────────────────────────────────────────────────┐
///      │   If the mod has built-in codes that support Wood Mods   │
///      └──────────────────────────────────────────────────────────┘
    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty", "so_on...");
    }
}
