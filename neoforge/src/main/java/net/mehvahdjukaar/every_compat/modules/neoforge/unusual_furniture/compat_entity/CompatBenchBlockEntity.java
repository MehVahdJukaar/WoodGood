package net.mehvahdjukaar.every_compat.modules.neoforge.unusual_furniture.compat_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

import static net.mehvahdjukaar.every_compat.modules.neoforge.unusual_furniture.UnusualFurnitureModule.CompatBenchBlock.woodType;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class CompatBenchBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> stacks;

    public CompatBenchBlockEntity(BlockPos position, BlockState state, BlockEntityType entityType) {
        super(entityType, position, state);
        this.stacks = NonNullList.withSize(9, ItemStack.EMPTY);
    }

    public void loadAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider lookupProvider) {
        super.loadAdditional(compound, lookupProvider);
        if (!this.tryLoadLootTable(compound)) {
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        }

        ContainerHelper.loadAllItems(compound, this.stacks, lookupProvider);
    }

    public void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider lookupProvider) {
        super.saveAdditional(compound, lookupProvider);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks, lookupProvider);
        }

    }

    public ClientboundBlockEntityDataPacket getClientUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider lookupProvider) {
        return this.saveWithFullMetadata(lookupProvider);
    }

    public int getContainerSize() {
        return this.stacks.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.stacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public @NotNull Component getDefaultName() {
        return Component.literal(woodType.getTypeName() + "_bench");
    }

    public int getMaxStackSize() {
        return 64;
    }

    public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory) {
        return ChestMenu.threeRows(id, inventory);
    }

    public @NotNull Component getDisplayName() {
        return Component.literal(capitalize(woodType.getTypeName()) + " Bench");
    }

    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    protected void setItems(@NotNull NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public boolean canPlaceItem(int index, @NotNull ItemStack stack) {
        return true;
    }

    public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack itemstack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemstack);
    }

    public boolean canTakeItemThroughFace(int index, @NotNull ItemStack itemstack, @NotNull Direction direction) {
        return true;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Packet getUpdatePacket() {
        return this.getClientUpdatePacket();
    }
}
