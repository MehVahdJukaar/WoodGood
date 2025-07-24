package net.mehvahdjukaar.every_compat.api.example;

import com.ninni.twigs.block.TableBlock;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

///      ┌──────────────────────────────────────────────────────────┐
///      │       WoodGoodModule using Twigs Mod as An Example       │
///      └──────────────────────────────────────────────────────────┘
public class WoodGoodModule extends SimpleModule {

        /// For Blocks
        public final SimpleEntrySet<WoodType, Block> sampleBlock, sampleBlock_2;

        /// For Items
        public final ItemOnlyEntrySet<WoodType, Item> sampleItem;

        public WoodGoodModule(String modId) {
            super(modId, "tw");

            //  your mod's tab or minecraft's tab can be used for setTabKey() - it can use either ResourceKey or ResourceLocation
            ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;
            ResourceLocation yourModTab = modRes("twig");

            sampleBlock_2 = SimpleEntrySet.builder(WoodType.class,"suffix", "prefix",
                    getModBlock("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
                    woodType -> new TableBlock(Utils.copyPropertySafe(woodType.planks).instabreak())
                    )
                    .build();
            this.addEntry(sampleBlock_2);

            sampleBlock = SimpleEntrySet.builder(WoodType.class,"table",
                            getModBlock("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
                            woodType -> new TableBlock(Utils.copyPropertySafe(woodType.planks).instabreak())
                    )
                    /// Check if a WoodType or LeavesType has the children required, then block will be generated
                    .requiresChildren("slab", "other_childkey") //REASON: can be for recipes or textures
                    .requiresFromMap(sampleBlock_2.blocks) // If your block required another block for crafting or texturing
                    //NOTE: sampleBlock_2 has to be above of this EntrySet for .requiresFromMap to work properly

                    /// Add the block to EntityBlockType
                    .addTile(getModTile("id_of_EntityType"))

                    /// Adding block's textures to be generated
                    .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE) // Without this, the default texture is planks' texture. but this is using log's SIDE texture
                    .addTexture(modRes("block/oak_table"))
                    .addTexture(new ResourceLocation("twigs:block/oak_table_bottom"))
                    .addTextureM(modRes("block/oak_table_top"), modRes("block/mask/oak_table_top_m")) // If the texture has parts that shouldn't be recolored, the mask (black color) can be used to exclude them

                    /// Adding tags to the block
                    .addTag(new ResourceLocation("twigs:tables"), Registries.BLOCK)
                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)

                    /// Add block to your mod's tab or Minecraft's tab
                    .setTabKey(yourModTab)

                    /// Creating recipes for the block
                    .defaultRecipe() // default: new ResourceLocation("twigs:oak_table") via recipes folder
                    .addRecipe(modRes("path/to/recipeFile"))

                    /// Special cases
                    .copyParentDrop() // copy the loot_table of the baseBlock (oak_table)
                    .copyParentTint() // Applying tinted color to Leaves - Good example is hedge from Quark OR Macaw's Fences & Walls
                    .setRenderType(RenderLayer.CUTOUT)

                    .build();
            this.addEntry(sampleBlock);

            sampleItem = ItemOnlyEntrySet.builder(WoodType.class,"table",
                            getModItem("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
                            w -> new Item(new Item.Properties())
                    )
                    .addTexture(modRes("item/itemTexture"))
                    .addTag(new ResourceLocation("twigs:tables"), Registries.ITEM)
                    .setTabKey(tab)
                    .build();
            this.addEntry(sampleItem);

        }
    }
