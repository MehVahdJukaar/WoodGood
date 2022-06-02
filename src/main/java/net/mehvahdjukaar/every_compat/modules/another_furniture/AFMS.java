package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.TableBlock;
import com.crispytwig.another_furniture.init.ModBlocks;
import com.crispytwig.another_furniture.render.ShelfRenderer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AFMS extends SimpleModule {

    public AFMS(String modId) {
        super(modId, "af");

        SimpleEntrySet<?, ?> tables = SimpleEntrySet.builder("table",
                        ModBlocks.OAK_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
                .addTexture(modRes("block/table/oak_sides"))
                .addTexture(modRes("block/table/oak_top"))
                .build();

        this.addEntry(tables);

        SimpleEntrySet<?, ?> chairs = SimpleEntrySet.builder("chair",
                        ModBlocks.OAK_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ChairBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("chairs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/chair/oak_back"))
                .addTexture(modRes("block/chair/oak_bottom"))
                .addTexture(modRes("block/chair/oak_seat"))
                .build();

        this.addEntry(chairs);

        SimpleEntrySet<?, ?> shelves = SimpleEntrySet.builder("shelf",
                        ModBlocks.OAK_SHELF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ShelfBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("shelves"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shelves"), Registry.ITEM_REGISTRY)
                .addTile(CompatShelfBlockTile::new, () -> ShelfRenderer::new)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
                .addTexture(modRes("block/shelf/oak_sides"))
                .addTexture(modRes("block/shelf/oak_top"))
                .addTexture(modRes("block/shelf/oak_supports"))
                .build();

        this.addEntry(shelves);
    }


}
