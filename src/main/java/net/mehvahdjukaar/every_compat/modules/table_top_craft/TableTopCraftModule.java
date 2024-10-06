package net.mehvahdjukaar.every_compat.modules.table_top_craft;

import andrews.table_top_craft.TableTopCraft;
import andrews.table_top_craft.tile_entities.ChessTileEntity;
import andrews.table_top_craft.tile_entities.render.ChessTileEntityRenderer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.IForgeRegistry;

//SUPPORT: FORGE-v2.2.0+
public class TableTopCraftModule extends SimpleModule {
    // Blocks
    public final SimpleEntrySet<WoodType, Block> CHESS_BOARDS;
    // Block Entity Types
    public static BlockEntityType<? extends ChessTileEntity> CHESS_TILE;

    public TableTopCraftModule(String modId) {
        super(modId, "ttc");

        CHESS_BOARDS = SimpleEntrySet.builder(WoodType.class, "chess",
                        () -> getModBlock("oak_chess"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatChessBlock(w.getMaterial(), getSound(w)))
                .addTile(CompatChessBlockTile::new)
                .addTag(modRes("chess_boards"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TableTopCraft.TABLE_TOP_CRAFT_GROUP)
                .defaultRecipe()
                .build();
        this.addEntry(CHESS_BOARDS);
    }

    /*
     * We need the sound type for the constructors, this code
     * retrieves the proper SoundType from the given BlockType.
     * (This method already exists in BlockType, in 1.20.1+)
     */
    @SuppressWarnings("deprecation")
    private SoundType getSound(BlockType type) {
        ItemLike itemLike = type.mainChild();
        if (itemLike instanceof Block block)
            return block.getSoundType(block.defaultBlockState());
        return SoundType.STONE;
    }

    @Override
    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        CHESS_TILE = (BlockEntityType<? extends ChessTileEntity>) CHESS_BOARDS.getTileHolder().tile;
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        super.registerEntityRenderers(event);
        event.registerBlockEntityRenderer(CHESS_TILE, ChessTileEntityRenderer::new);
    }
}