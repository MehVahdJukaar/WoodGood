package net.mehvahdjukaar.every_compat.modules.table_top_craft;

import andrews.table_top_craft.TableTopCraft;
import andrews.table_top_craft.objects.blocks.ChessBlock;
import andrews.table_top_craft.objects.blocks.ChessTimerBlock;
import andrews.table_top_craft.objects.blocks.ConnectFourBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: FORGE-v3.1.0+ | FABRIC-v2.1.0+
public class TableTopCraftModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> chessBoards;
    public final SimpleEntrySet<WoodType, Block> chessTimers;
    public final SimpleEntrySet<WoodType, Block> connectFours;

    public TableTopCraftModule(String modId) {
        super(modId, "ttc");
        // This is fine up to 1.19.2, but after that tab registration changed so the field no longer exists!
        var tab = TableTopCraft.TABLE_TOP_CRAFT_GROUP;

        chessBoards = SimpleEntrySet.builder(WoodType.class, "chess",
                        () -> getModBlock("oak_chess"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChessBlock(w.material, getSound(w)))
                .addTile(() -> getModTile("chess"))
                .addTag(modRes("chess_boards"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(chessBoards);

        chessTimers = SimpleEntrySet.builder(WoodType.class, "chess_timer",
                        () -> getModBlock("oak_chess_timer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChessTimerBlock(w.material, getSound(w)))
                .addTile(() -> getModTile("chess_timer"))
                .addTag(modRes("chess_timers"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(chessTimers);

        connectFours = SimpleEntrySet.builder(WoodType.class, "connect_four",
                        () -> getModBlock("oak_connect_four"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ConnectFourBlock(w.material, getSound(w)))
                .addTile(() -> getModTile("connect_four"))
                .addTag(modRes("connect_four"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(connectFours);
    }

    /*
     * We need the sound type for the constructors, this code
     * retrieves the proper SoundType from the given BlockType.
     * (This method already exists in BlockType, in 1.20.1+)
     */
    private SoundType getSound(BlockType type) {
        ItemLike itemLike = type.mainChild();
        if (itemLike instanceof Block block)
            return block.getSoundType(block.defaultBlockState());
        return SoundType.STONE;
    }
}