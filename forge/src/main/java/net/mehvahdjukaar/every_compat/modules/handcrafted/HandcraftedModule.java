package net.mehvahdjukaar.every_compat.modules.handcrafted;

import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlock;
import earth.terrarium.handcrafted.common.item.BoardItem;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.every_compat.EveryCompat.res;


public class HandcraftedModule extends SimpleModule {

//    public final SimpleEntrySet<WoodType, Block> BOARDS;
    public final SimpleEntrySet<WoodType, Block> CHAIRS;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        CreativeModeTab tab = ModItems.ITEM_GROUP;


//        BOARDS = SimpleEntrySet.builder(WoodType.class, "oak_board",
//                        ModItems.OAK_BOARD, () -> WoodTypeRegistry.OAK_TYPE.initializeChildrenItems(),
//                        w -> new BoardItem(new Item.Properties().tab(tab)))
//                .addTexture(modRes("item/board/oak_chair"))
//                .setTab(() -> tab)
//                .defaultRecipe()
//                .build();
//
//        this.addEntry(BOARDS);

        CHAIRS = SimpleEntrySet.builder(WoodType.class, "oak_chair",
                        ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/oak_chair"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(CHAIRS);
    }

    public void init() {
        BlockSetAPI.addDynamicItemRegistration(this::registerBoardItems, WoodType.class);
    }

    public static final Map<WoodType, BoardItem> BOARD_ITEMS = new HashMap<>();

    public class CompatBoardItem extends BoardItem {

        public CompatBoardItem(WoodType type) {
            super(new Item.Properties());
        }
    }

    private void registerBoardItems(Registrator<Item> event, Collection<WoodType> woodTypes) {
        for (WoodType wood : woodTypes) {
            String name = wood.getVariantId("board");
            BoardItem item = new CompatBoardItem(wood);
            event.register(res(name), item);
            BOARD_ITEMS.put(wood, item);
            wood.addChild("board", (Object) item);
        }
    }
}
