package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final WoodType woodType;
    private final boolean trapped;
    private final boolean useWoodTypeName;

    public CompatChestBlockEntity(boolean useWoodTypeName, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
        var w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.woodType = w == null ? VanillaWoodTypes.OAK : w;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
        this.useWoodTypeName = useWoodTypeName;
    }

    public CompatChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
        var w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.woodType = w == null ? VanillaWoodTypes.OAK : w;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
        this.useWoodTypeName = true;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (trapped && eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }

    public boolean isTrapped() {
        return trapped;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        if (this.useWoodTypeName)
            return Component.translatable("container.everycomp.chest.name", Component.translatable(woodType.getTranslationKey()).getString());
        return Component.translatable("container.chest");
    }
}
