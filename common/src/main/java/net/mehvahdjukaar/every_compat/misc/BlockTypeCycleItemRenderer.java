package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

@Environment(EnvType.CLIENT)
public abstract class BlockTypeCycleItemRenderer<T extends BlockType> extends ItemStackRenderer {

    private final List<String> childKeys = new ArrayList<>();
    private final List<T> moddedTypes = new ArrayList<>();
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
        for (var c : reg.getDefaultType().getChildren()) {
            if (c.getKey().contains(":") && !childKeys.contains(c.getKey()) && c.getValue() instanceof ItemLike) {
                childKeys.add(c.getKey());
            }
        }
        for (var w : reg.getValues()) {
            if (!w.isVanilla()) moddedTypes.add(w);
        }
        if (moddedTypes.isEmpty()) childKeys.clear();
        Collections.shuffle(moddedTypes);
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

            ItemLike v = null;
            do {
                var l = (this.lastIndex + 1) % size;
                // this.woodIndex = (this.woodIndex + 1);
                if (l < lastIndex || size == 1) this.typeIndex = (this.typeIndex + 1) % moddedTypes.size();
                this.lastIndex = l;
                String key = childKeys.get(lastIndex);
                var vv = moddedTypes.get(typeIndex % moddedTypes.size()).getChild(key);
                if (vv instanceof ItemLike il) {
                    v = il;
                }
            } while (v == null);

            this.currentStack = v.asItem().getDefaultInstance();
        }
        this.lastTime = tm;
        return currentStack;
    }
}
