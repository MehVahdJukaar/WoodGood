package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

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

        flowerBoxes = SimpleEntrySet.builder(WoodType.class, "flower_box",
                        AFBlocks.OAK_FLOWER_BOX, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FlowerBoxBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("flower_boxes"), Registries.BLOCK)
                .addTag(modRes("flower_boxes"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .addTile(AFBlockEntityTypes.FLOWER_BOX)
                .setTab(getModTab("tab"))
                .addTexture(modRes("block/flower_box/oak_bottom"))
                .addTexture(modRes("block/flower_box/oak_supports"))
                .addTextureM(modRes("block/flower_box/oak_top_sides"), EveryCompat.res("block/af/planter_box_top_sides_mask"))
                .build();

        this.addEntry(flowerBoxes);

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        AFBlocks.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShutterBlock(1, Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(modRes("shutters"), Registries.BLOCK)
                .addTag(modRes("shutters"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab("tab"))
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
//                .createPaletteFromOak(this::shuttersPalette)
                .build();

        this.addEntry(shutters);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        AFBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .copyParentDrop()
                .defaultRecipe()
                .setTab(getModTab("tab"))
                .setRenderType(() -> RenderType::cutout)
                .useMergedPalette()
                .createPaletteFromOak(palette -> palette.remove(palette.getDarkest()))
                .addTexture(modRes("block/table/oak_bottom"))
                .addTexture(modRes("block/table/oak_sides"))
                .addTexture(modRes("block/table/oak_supports"))
                .addTexture(modRes("block/table/oak_top"))
                .build();

        this.addEntry(tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        AFBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(1, Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("chairs"), Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.ITEM)
                .defaultRecipe()
                .setTab(getModTab("tab"))
                .setRenderType(() -> RenderType::cutout)
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
                        AFBlocks.OAK_SHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shelves"), Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.ITEM)
                .addTile(AFBlockEntityTypes.SHELF)
                .defaultRecipe()
                .setTab(getModTab("tab"))
                .useMergedPalette()
                .addTexture(modRes("block/shelf/oak_sides"))
                .addTexture(modRes("block/shelf/oak_top"))
                .addTexture(modRes("block/shelf/oak_bottom"))
                .addTexture(modRes("block/shelf/oak_supports"))
                .build();

        this.addEntry(shelves);

        drawers = SimpleEntrySet.builder(WoodType.class, "drawer",
                        AFBlocks.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTile(AFBlockEntityTypes.DRAWER)
                .defaultRecipe()
                .setTab(getModTab("tab"))
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/drawer/oak_front"), EveryCompat.res("block/af/oak_front_m"))
                .addTextureM(modRes("block/drawer/oak_front_open"), EveryCompat.res("block/af/oak_front_open_m"))
                .addTexture(modRes("block/drawer/oak_side"))
                .addTexture(modRes("block/drawer/oak_top"))
                .build();

        this.addEntry(drawers);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        AFBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("benches"), Registries.BLOCK)
                .addTag(modRes("benches"), Registries.ITEM)
                .defaultRecipe()
                .setTab(getModTab("tab"))
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/bench/oak"))
                .build();

        this.addEntry(benches);

    }

    private void shuttersPalette(Palette p) {
        float dl = p.getAverageLuminanceStep();
        {
            var c0 = p.get(0);
            var nc0 = new PaletteColor(c0.hcl().withLuminance(c0.hcl().luminance() - (dl * 0.35f)));
            nc0.setOccurrence(c0.getOccurrence());
            p.set(0, nc0);
        }
        {
            var c1 = p.get(1);
            var nc1 = new PaletteColor(c1.hcl().withLuminance(c1.hcl().luminance() - (dl * 0.18f)));
            nc1.setOccurrence(c1.getOccurrence());
            p.set(1, nc1);
        }
        {
            var c2 = p.get(2);
            var nc2 = new PaletteColor(c2.hcl().withLuminance(c2.hcl().luminance() - (dl * 0.05f)));
            nc2.setOccurrence(c2.getOccurrence());
            p.set(+2, nc2);
        }
    }

}
