package net.mehvahdjukaar.every_compat.modules.table_top_craft;

import andrews.table_top_craft.objects.blocks.ChessBlock;
import andrews.table_top_craft.objects.blocks.ChessTimerBlock;
import andrews.table_top_craft.objects.blocks.ConnectFourBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: FORGE-v6.1.0+ | FABRIC-v5.0.0+
public class TableTopCraftModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chessBoards;
    public final SimpleEntrySet<WoodType, Block> chessTimers;
    public final SimpleEntrySet<WoodType, Block> connectFours;

    public TableTopCraftModule(String modId) {
        super(modId, "ttc");
        var tab = modRes("tab");

        chessBoards = SimpleEntrySet.builder(WoodType.class, "chess",
                        getModBlock("oak_chess"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChessBlock(w.getColor(), w.getSound()))
                .addTile(getModTile("chess"))
                .addTag(modRes("chess_boards"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chessBoards);

        chessTimers = SimpleEntrySet.builder(WoodType.class, "chess_timer",
                        getModBlock("oak_chess_timer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChessTimerBlock(w.getColor(), w.getSound()))
                .addTile(getModTile("chess_timer"))
                .addTag(modRes("chess_timers"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chessTimers);

        connectFours = SimpleEntrySet.builder(WoodType.class, "connect_four",
                        getModBlock("oak_connect_four"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ConnectFourBlock(w.getColor(), w.getSound()))
                .addTile(getModTile("connect_four"))
                .addTag(modRes("connect_four"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(connectFours);
    }
}