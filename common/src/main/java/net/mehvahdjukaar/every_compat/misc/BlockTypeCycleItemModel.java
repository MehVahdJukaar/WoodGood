package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.candlelight.api.ClientOnly;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.util.Util;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getBlockTypeConfigs;
import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getChildConfigs;

@ClientOnly
public abstract class BlockTypeCycleItemModel<T extends BlockType> implements ItemModel {

    private final List<String> childKeys = new ArrayList<>();
    private final List<T> moddedBlockTypes = new ArrayList<>();
    private final Class<T> typeClass;
    private ItemStack currentStack = ItemStack.EMPTY;
    private int lastIndex = 0;
    private int lastTime = 0;
    private int typeIndex = 0;
    private boolean initialized;

    public BlockTypeCycleItemModel(Class<T> tClass) {
        this.typeClass = tClass;
    }

    private void initialize() {
        BlockTypeRegistry<T> reg = BlockSetAPI.getTypeRegistry(typeClass);
        if (reg == null) return;
        for (var currentChild : reg.getDefaultType().getChildren()) {
            if (currentChild.getKey().contains(":")
                    && !childKeys.contains(currentChild.getKey())
                    && currentChild.getValue() instanceof ItemLike
                    && isChildTypeEnabled(currentChild.getKey())) {

                childKeys.add(currentChild.getKey());
            }
        }
        for (T blockType : reg.getValues()) { // BlockType's children
            if (!HardcodedBlockType.isKnownVanillaType(blockType) && isBlockTypeEnabled(blockType)) moddedBlockTypes.add(blockType);
        }
        if (moddedBlockTypes.isEmpty()) childKeys.clear();
        Collections.shuffle(moddedBlockTypes);
    }

    @Override
    public void update(ItemStackRenderState output, ItemStack stack, ItemModelResolver resolver, ItemDisplayContext displayContext,
                       @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        if (!this.initialized) {
            this.initialize();
            this.initialized = true;
        }
        output.appendModelIdentityElement(this);
        resolver.appendItemLayers(output, getAnyItem(), displayContext, level, owner, seed);
    }

    public ItemStack getAnyItem() {
        int size = childKeys.size();
        if (size == 0) return Items.BARRIER.getDefaultInstance();
        int time = (int) (Util.getMillis() / 350L);
        int tm = time % (size+1);
        if (tm != lastTime) {

            ItemLike itemLike = null;
            do {
                var l = (this.lastIndex + 1) % size;
                // this.woodIndex = (this.woodIndex + 1);
                if (l < lastIndex || size == 1) this.typeIndex = (this.typeIndex + 1) % moddedBlockTypes.size();
                this.lastIndex = l;
                String key = childKeys.get(lastIndex);
                var vv = moddedBlockTypes.get(typeIndex % moddedBlockTypes.size()).getChild(key);
                if (vv instanceof ItemLike il) {
                    itemLike = il;
                }
            } while (itemLike == null);

            this.currentStack = itemLike.asItem().getDefaultInstance();
        }
        this.lastTime = tm;
        return currentStack;
    }

    // Below is a null check & ensure that the code is executed properly.

    public boolean isBlockTypeEnabled(BlockType blockType) {
        var blockTypeConfig = getBlockTypeConfigs(typeClass).get(blockType.getId().toString());

        if (blockTypeConfig != null) return blockTypeConfig.get();
        else return true;
    }

    public boolean isChildTypeEnabled(String currentChildKey) {
        Supplier<Boolean> childConfig = getChildConfigs(typeClass).get(currentChildKey);

        if (childConfig != null) return childConfig.get();
        else return true;
    }
}
