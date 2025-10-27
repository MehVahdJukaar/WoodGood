package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.starfish_studios.another_furniture.block.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//SUPPORT: v3.0.1+
public class AnotherFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> flowerBoxes;
    public final SimpleEntrySet<WoodType, Block> shutters;
    public final SimpleEntrySet<WoodType, Block> drawers;
    public final SimpleEntrySet<WoodType, Block> benches;

    public AnotherFurnitureModule(String modId) {
        super(modId, "af");

        ResourceLocation tabKey = PlatHelper.getPlatform().isForge() ?
                modRes(modId) : modRes("tab");

        flowerBoxes = SimpleEntrySet.builder(WoodType.class, "flower_box",
                       getModBlock("oak_flower_box"), () -> VanillaWoodTypes.OAK,
                        w -> new FlowerBoxBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("flower_boxes"), Registries.BLOCK)
                .addTag(modRes("flower_boxes"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .addTile(getModTile("planter_box"))
                .setTabKey(tabKey)
                .addTexture(modRes("block/flower_box/oak_bottom"))
                .addTexture(modRes("block/flower_box/oak_supports"))
                .addTextureM(modRes("block/flower_box/oak_top_sides"), EveryCompat.res("block/af/planter_box_top_sides_mask"))
                .build();
        this.addEntry(flowerBoxes);

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                       getModBlock("oak_shutter"), () -> VanillaWoodTypes.OAK,
                        w -> new ShutterBlock(1, Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(modRes("shutters"), Registries.BLOCK)
                .addTag(modRes("shutters"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTabKey(tabKey)
                .addTexture(modRes("block/shutter/variant_1/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_1/oak_middle"))
                .addTexture(modRes("block/shutter/variant_1/oak_single"))
                .addTexture(modRes("block/shutter/variant_1/oak_top"))
                .addTexture(modRes("block/shutter/variant_2/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_2/oak_middle"))
                .addTexture(modRes("block/shutter/variant_2/oak_single"))
                .addTexture(modRes("block/shutter/variant_2/oak_top"))
                .addTextureM(modRes("block/shutter/variant_3/oak_bottom"), EveryCompat.res("block/af/shutter/variant_3/oak_common_m"))
                .addTextureM(modRes("block/shutter/variant_3/oak_middle"), EveryCompat.res("block/af/shutter/variant_3/oak_common_m"))
                .addTextureM(modRes("block/shutter/variant_3/oak_single"), EveryCompat.res("block/af/shutter/variant_3/oak_common_m"))
                .addTextureM(modRes("block/shutter/variant_3/oak_top"), EveryCompat.res("block/af/shutter/variant_3/oak_common_m"))
                .addTexture(modRes("block/shutter/variant_4/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_4/oak_middle"))
                .addTexture(modRes("block/shutter/variant_4/oak_single"))
                .addTexture(modRes("block/shutter/variant_4/oak_top"))
                .addTexture(modRes("block/shutter/variant_5/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_5/oak_middle"))
                .addTexture(modRes("block/shutter/variant_5/oak_single"))
                .addTexture(modRes("block/shutter/variant_5/oak_top"))
                .addTexture(modRes("block/shutter/variant_6/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_6/oak_middle"))
                .addTexture(modRes("block/shutter/variant_6/oak_single"))
                .addTexture(modRes("block/shutter/variant_6/oak_top"))
                .addTexture(modRes("block/shutter/variant_7/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_7/oak_middle"))
                .addTexture(modRes("block/shutter/variant_7/oak_single"))
                .addTexture(modRes("block/shutter/variant_7/oak_top"))
                .addTexture(modRes("block/shutter/variant_8/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_8/oak_middle"))
                .addTexture(modRes("block/shutter/variant_8/oak_single"))
                .addTexture(modRes("block/shutter/variant_8/oak_top"))
                .addTexture(modRes("block/shutter/variant_9/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_9/oak_middle"))
                .addTexture(modRes("block/shutter/variant_9/oak_single"))
                .addTexture(modRes("block/shutter/variant_9/oak_top"))
                .addTexture(modRes("block/shutter/variant_10/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_10/oak_middle"))
                .addTexture(modRes("block/shutter/variant_10/oak_single"))
                .addTexture(modRes("block/shutter/variant_10/oak_top"))
                .addTexture(modRes("block/shutter/variant_11/oak_bottom"))
                .addTexture(modRes("block/shutter/variant_11/oak_middle"))
                .addTexture(modRes("block/shutter/variant_11/oak_single"))
                .addTexture(modRes("block/shutter/variant_11/oak_top"))
                .build();
        this.addEntry(shutters);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                       getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("table_connectable"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTabKey(tabKey)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .useMergedPalette()
                .addTexture(modRes("block/table/oak_bottom"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTexture(modRes("block/table/oak_sides"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTexture(modRes("block/table/oak_supports"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTexture(modRes("block/table/oak_top"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .build();
        this.addEntry(tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                       getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new ChairBlock(1, Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("chairs"), Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.ITEM)
                .defaultRecipe()
                .setTabKey(tabKey)
                .setRenderType(RenderLayer.CUTOUT)
                .useMergedPalette()
                .addTexture(modRes("block/chair/back_1/oak"))
                .addTexture(modRes("block/chair/back_2/oak"))
                .addTexture(modRes("block/chair/back_3/oak"))
                .addTexture(modRes("block/chair/back_4/oak"))
                .addTexture(modRes("block/chair/back_5/oak"))
                .addTexture(modRes("block/chair/back_6/oak"))
                .addTexture(modRes("block/chair/back_7/oak"))
                .addTexture(modRes("block/chair/back_8/oak"))
                .addTexture(modRes("block/chair/back_9/oak"))
                .addTexture(modRes("block/chair/back_10/oak"))
                .addTexture(modRes("block/chair/back_11/oak"))
                .addTexture(modRes("block/chair/bottom/oak"))
                .addTexture(modRes("block/chair/seat/oak"))
                .build();
        this.addEntry(chairs);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                       getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(modRes("shelves"), Registries.BLOCK, Registries.ITEM)
                .addTile(getModTile("shelf"))
                .defaultRecipe()
                .setTabKey(tabKey)
                .useMergedPalette()
                .addTexture(modRes("block/shelf/oak_sides"))
                .addTexture(modRes("block/shelf/oak_top"))
                .addTexture(modRes("block/shelf/oak_bottom"))
                .addTexture(modRes("block/shelf/oak_supports"))
                .build();
        this.addEntry(shelves);

        drawers = SimpleEntrySet.builder(WoodType.class, "drawer",
                       getModBlock("oak_drawer"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTile(getModTile("drawer"))
                .defaultRecipe()
                .setTabKey(tabKey)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/drawer/oak_front"), EveryCompat.res("block/af/oak_front_m"))
                .addTextureM(modRes("block/drawer/oak_front_open"), EveryCompat.res("block/af/oak_front_open_m"))
                .addTexture(modRes("block/drawer/oak_side"))
                .addTexture(modRes("block/drawer/oak_top"))
                .build();
        this.addEntry(drawers);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                       getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(modRes("benches"), Registries.BLOCK, Registries.ITEM)
                .defaultRecipe()
                .setTabKey(tabKey)
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/bench/oak"))
                .build();
        this.addEntry(benches);

    }
}
