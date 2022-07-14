package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllWoodItemRenderer extends ItemStackRenderer {

    private final List<String> CHILD_KEYS = new ArrayList<>();
    private final List<WoodType> MODDED_WOOD_TYPES = new ArrayList<>();
    private ItemStack currentStack = Items.OAK_WOOD.getDefaultInstance();
    private int lastIndex = 0;
    private int lastTime = 0;
    private int woodIndex = 0;
    private boolean initialized;

    public AllWoodItemRenderer() {
        super();
    }

    private void initialize() {
        label:
        for (var c : WoodTypeRegistry.OAK_TYPE.getChildren()) {
            if (c.getKey().contains("/")) {
                for (var k : CHILD_KEYS) {
                    if (WoodTypeRegistry.OAK_TYPE.getChild(k).asItem() == c.getValue().asItem()) {
                        continue label;
                    }
                }
                CHILD_KEYS.add(c.getKey());
            }
        }
        for (var w : WoodTypeRegistry.getTypes()) {
            if (!w.isVanilla()) MODDED_WOOD_TYPES.add(w);
        }
        Collections.shuffle(MODDED_WOOD_TYPES);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType,
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
        matrixStack.popPose();
        if (!bakedmodel.isGui3d()) Lighting.setupForFlatItems();
        //forces rendering now with flat lighting
        if (buffer instanceof MultiBufferSource.BufferSource bu) {
            bu.endBatch();
        }
        Lighting.setupFor3DItems();
    }


    //TODO: fix this
    public ItemStack getAnyItem() {
        int size = CHILD_KEYS.size();
        if (size == 0) return Items.OAK_PLANKS.getDefaultInstance();
        int time = (int) (Util.getMillis() / 350L);
        int tm = time % size;
        if (tm != lastTime) {

            ItemLike v;
            do {
                var l = (this.lastIndex + 1) % size;
                // this.woodIndex = (this.woodIndex + 1);
                if (l < lastIndex) this.woodIndex = (this.woodIndex + 1) % MODDED_WOOD_TYPES.size();
                this.lastIndex = l;
                String key = CHILD_KEYS.get(lastIndex);
                v = MODDED_WOOD_TYPES.get(woodIndex % MODDED_WOOD_TYPES.size()).getChild(key);
                if (v != null && v.asItem().getItemCategory() == null) v = null;
            } while (v == null);

            this.currentStack = v.asItem().getDefaultInstance();
        }
        this.lastTime = tm;
        return currentStack;
    }
}
