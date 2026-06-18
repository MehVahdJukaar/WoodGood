package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.candlelight.api.ClientOnly;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getBlockTypeConfigs;
import static net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs.getChildConfigs;

@ClientOnly
public abstract class BlockTypeCycleItemRenderer<T extends BlockType> extends ItemStackRenderer {

    private final List<String> childKeys = new ArrayList<>();
    private final List<T> moddedBlockTypes = new ArrayList<>();
    private final Class<T> typeClass;
    private ItemStack currentStack = Items.BARRIER.getDefaultInstance();
    private int lastIndex = 0;
    private int lastTime = 0;
    private int typeIndex = 0;
    private boolean initialized;

    public BlockTypeCycleItemRenderer(Class<T> tClass) {
        super();
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
            if (!blockType.isVanilla() && isBlockTypeEnabled(blockType)) moddedBlockTypes.add(blockType);
        }
        if (moddedBlockTypes.isEmpty()) childKeys.clear();
        Collections.shuffle(moddedBlockTypes);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType,
                             PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        if (!this.initialized) {
            this.initialize();
            this.initialized = true;
        }
        ItemStack item = getAnyItem();

        var itemRenderer = Minecraft.getInstance().getItemRenderer();

        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        BakedModel bakedmodel = itemRenderer.getModel(item, null, null, 0);
        itemRenderer.render(item, transformType, false, matrixStack, buffer, combinedLight, combinedOverlay, bakedmodel);
        if (!bakedmodel.isGui3d()) Lighting.setupForFlatItems();
        //forces rendering now with flat lighting
        if (buffer instanceof MultiBufferSource.BufferSource bu) {
            bu.endBatch();
        }
        Lighting.setupFor3DItems();
        matrixStack.popPose();

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
