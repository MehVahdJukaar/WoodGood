package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;

import java.util.Collection;
import java.util.List;

/**
 * Use this to register new wood type blocks and module
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 */
public class EveryCompatAPI {

    /**
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     */
    public static synchronized void registerModule(CompatModule module) {
        EveryCompat.addModule(module);
    }

    public static Collection<CompatModule> getModule(String modId) {
        return EveryCompat.getModulesOfMod(modId);
    }

    /// If you mod has compat mods that support it with Biomes O' Plenty or other Wood Mods below can make an exception
    /// so EC won't generate blocks from your mod with Biomes O' Plenty
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId){
        EveryCompat.addOtherCompatMod(compatModId, fromModId, supportedModId);
    }

    ///      ┌──────────────────────────────────────────────────────────┐
    ///      │       WoodGoodModule using Twigs Mod as An Example       │
    ///      └──────────────────────────────────────────────────────────┘
//    public WoodGoodModule extends SimpleModule {

            /// For Blocks
//        public final SimpleEntrySet<WoodType, Block> sampleBlock;
            /// For Items
//        public final ItemOnlyEntrySet<WoodType, Item> sampleItem;

//        public WoodGoodModule(String modId) {
//            super(modId, "tw");

            //  your mod's tab or minecraft's tab can be used for setTabKey()
//            ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;
//            ResourceLocation yourModTab = modRes("twig");

//            sampleBlock = SimpleEntrySet.builder(WoodType.class,"table",
//                            getModBlock("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
//                            woodType -> new TableBlock(Utils.copyPropertySafe(woodType.planks).instabreak())
//                    )
                    /// Check if a WoodType or LeavesType has the children required, then block will be generated
//                    .requiresChildren("slab", "other_childkey") //REASON: can be for recipes or textures
//                    .requiresFromMap(sampleBlock_2.blocks) // If your block required another block for crafting or texturing

                    /// Add the block to EntityBlockType
//                    .addTile(getModTile("id_of_EntityType"))

                    /// Adding block's textures to be generated
//                    .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE) // Without this, the default texture is planks' texture. but this is using log's SIDE texture
//                    .addTexture(modRes("block/oak_table"))
//                    .addTexture(new ResourceLocation("twigs:block/oak_table_bottom"))
//                    .addTextureM(modRes("block/oak_table_top"), modRes("block/mask/oak_table_top_m")) // If the texture has parts that shouldn't be recolored, the mask (black color) can be used to exclude them

                    /// Adding tags to the block
//                    .addTag(new ResourceLocation("twigs:tables"), Registries.BLOCK)
//                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)

                    /// Add block to your mod's tab or Minecraft's tab
//                    .setTabKey(yourModTab)

                    /// Creating recipes for the block
//                    .defaultRecipe() // default: new ResourceLocation("twigs:oak_table") via recipes folder
//                    .addRecipe(modRes("path/to/recipeFile"))

                    /// Special cases
//                    .copyParentDrop() // copy the loot_table of the baseBlock (oak_table)
//                    .copyParentTint() // Applying tinted color to Leaves - Good example is hedge from Quark OR Macaw's Fences & Walls
//                    .setRenderType(RenderLayer.CUTOUT)

//                    .build();
//            this.addEntry(sampleBlock);

//            sampleItem = ItemOnlyEntrySet.builder(WoodType.class,"table",
//                            getModItem("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
//                            w -> new Item(new Item.Properties())
//                    )
//                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)
//                    .setTabKey(tab)
//                    .addTexture(modRes("item/itemTexture"))
//                    .build();
//            this.addEntry(sampleItem);

//        }
//    }

/*
    If you want one more example of how is this applied, then you can check below:
    https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L57
    https://github.com/macuguita/woodworks/blob/1.21.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L105
    https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/ModCompat.java
    WoodGoodModule: https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/WoodGood.java
*/

///      ┌──────────────────────────────────────────────────────────┐
///      │         register a custom non-detected wood type         │
///      └──────────────────────────────────────────────────────────┘
/*
    public static void init() {
        BlockSetAPI.addBlockTypeFinder(WoodType.class, WoodType.Finder
            .simple("my_mod", "cherry", "cherry_plank", "cherry_stem"));

    }
*/


}
