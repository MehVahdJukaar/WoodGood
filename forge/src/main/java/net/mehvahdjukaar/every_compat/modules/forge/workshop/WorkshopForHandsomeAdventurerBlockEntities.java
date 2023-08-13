package net.mehvahdjukaar.every_compat.modules.forge.workshop;

import moonfather.workshop_for_handsome_adventurer.block_entities.*;
import moonfather.workshop_for_handsome_adventurer.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.Nullable;

class WorkshopForHandsomeAdventurerBlockEntities
{
    static BlockEntityType<? extends SimpleTableBlockEntity> SIMPLE_TABLE_BE;
    static BlockEntityType<? extends BookShelfBlockEntity> BOOK_SHELF_BE;
    static BlockEntityType<? extends PotionShelfBlockEntity> POTION_SHELF_BE;
    static BlockEntityType<? extends DualTableBlockEntity> DUAL_TABLE_BE;
    static BlockEntityType<? extends ToolRackBlockEntity> TOOL_RACK_BE_1_AAARGH; // yes i get angry when i type stupid things
    static BlockEntityType<? extends ToolRackBlockEntity> TOOL_RACK_BE_2_AHRGHARGG;
    static BlockEntityType<? extends ToolRackBlockEntity> TOOL_RACK_BE_3_HAAARGH;
    static BlockEntityType<? extends ToolRackBlockEntity> TOOL_RACK_BE_4_GAAARGHUUHG;  // did not help

    static class CompatSimpleCraftingTableBE extends SimpleTableBlockEntity
    {
        public CompatSimpleCraftingTableBE(BlockPos pos, BlockState state)
        {
            super(SIMPLE_TABLE_BE, pos, state);
        }
    }

    static class CompatBookShelfBE extends BookShelfBlockEntity
    {
        public CompatBookShelfBE(BlockPos pos, BlockState state)
        {
            super(BOOK_SHELF_BE, pos, state, 20);
        }
    }

    static class CompatPotionShelfBE extends PotionShelfBlockEntity
    {
        public CompatPotionShelfBE(BlockPos pos, BlockState state) { super(POTION_SHELF_BE, pos, state); }
    }

    static class CompatDualCraftingTableBE extends DualTableBlockEntity
    {
        public CompatDualCraftingTableBE(BlockPos pos, BlockState state) { super(DUAL_TABLE_BE, pos, state); }
    }

    static class CompatToolRack1BE extends ToolRackBlockEntity
    {
        public CompatToolRack1BE(BlockPos pos, BlockState state) { super(TOOL_RACK_BE_1_AAARGH, pos, state, 6); } // yes they are the same
        @Override
        public BlockEntityType<?> getType() { return TOOL_RACK_BE_1_AAARGH; }
    }
    static class CompatToolRack2BE extends ToolRackBlockEntity
    {
        public CompatToolRack2BE(BlockPos pos, BlockState state) { super(TOOL_RACK_BE_2_AHRGHARGG, pos, state, 6); }
        @Override
        public BlockEntityType<?> getType() { return TOOL_RACK_BE_2_AHRGHARGG; }
    }
    static class CompatToolRack3BE extends ToolRackBlockEntity
    {
        public CompatToolRack3BE(BlockPos pos, BlockState state) { super(TOOL_RACK_BE_3_HAAARGH, pos, state, 6); }
        @Override
        public BlockEntityType<?> getType() { return TOOL_RACK_BE_3_HAAARGH; }
    }
    static class CompatToolRack4BE extends ToolRackBlockEntity
    {
        public CompatToolRack4BE(BlockPos pos, BlockState state) { super(TOOL_RACK_BE_4_GAAARGHUUHG, pos, state, 6); }
        @Override
        public BlockEntityType<?> getType() { return TOOL_RACK_BE_4_GAAARGHUUHG; }
    }


    //////////////////////////////////////////////////////////////////

    static class CompatSimpleCraftingTable extends SimpleTable
    {
        public CompatSimpleCraftingTable()
        {
            super();
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            return SIMPLE_TABLE_BE.create(pos, blockState);
        }
    }

    static class CompatBookShelfDual extends BookShelf.Dual
    {
        public CompatBookShelfDual(String type)
        {
            super(type);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            BookShelfBlockEntity BE = BOOK_SHELF_BE.create(pos, blockState);
            BE.setCapacity(Math.max(this.numberOfRows() * this.numberOfBooksInARow(), 8));
            return BE;
        }
    }

    static class CompatBookShelfTopSimple extends BookShelf.TopSimple
    {
        public CompatBookShelfTopSimple(String type)
        {
            super(type);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            BookShelfBlockEntity BE = BOOK_SHELF_BE.create(pos, blockState);
            BE.setCapacity(Math.max(this.numberOfRows() * this.numberOfBooksInARow(), 8));
            return BE;
        }
    }

    static class CompatBookShelfTopWithLanterns extends BookShelf.TopWithLanterns
    {
        public CompatBookShelfTopWithLanterns(String type)
        {
            super(type);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            BookShelfBlockEntity BE = BOOK_SHELF_BE.create(pos, blockState);
            BE.setCapacity(Math.max(this.numberOfRows() * this.numberOfBooksInARow(), 8));
            return BE;
        }
    }

    static class CompatPotionShelf extends PotionShelf
    {
        public CompatPotionShelf() { super(); }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            return POTION_SHELF_BE.create(pos, blockState);
        }
    }

    static class CompatDualCraftingTable extends AdvancedTableBottomPrimary
    {
        public CompatDualCraftingTable() { super(); }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            return DUAL_TABLE_BE.create(pos, blockState);
        }
    }

    static class CompatToolRack4 extends ToolRack
    {
        public CompatToolRack4(int itemCount, String subType)
        {
            super(itemCount, subType);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            ToolRackBlockEntity be = TOOL_RACK_BE_4_GAAARGHUUHG.create(pos, blockState);
            be.setCapacity(2);
            return be;
        }
    }

    static class CompatDualToolRack1 extends DualToolRack
    {
        public CompatDualToolRack1(int itemCount, String subType)
        {
            super(itemCount, subType);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            if (blockState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) && blockState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER)
            {
                return null;
            }
            ToolRackBlockEntity be = TOOL_RACK_BE_1_AAARGH.create(pos, blockState);
            be.setCapacity(6);
            return be;
        }
    }
    static class CompatDualToolRack2 extends DualToolRack
    {
        public CompatDualToolRack2(int itemCount, String subType)
        {
            super(itemCount, subType);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            if (blockState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) && blockState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER)
            {
                return null;
            }
            ToolRackBlockEntity be = TOOL_RACK_BE_2_AHRGHARGG.create(pos, blockState);
            be.setCapacity(6);
            return be;
        }
    }
    static class CompatDualToolRack3 extends DualToolRack
    {
        public CompatDualToolRack3(int itemCount, String subType)
        {
            super(itemCount, subType);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            if (blockState.hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF) && blockState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.UPPER)
            {
                return null;
            }
            ToolRackBlockEntity be = TOOL_RACK_BE_3_HAAARGH.create(pos, blockState);
            be.setCapacity(6);
            return be;
        }
    }
}
