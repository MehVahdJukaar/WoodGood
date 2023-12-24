package net.mehvahdjukaar.every_compat.modules.quark;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;
import org.violetmoon.quark.content.building.module.VariantChestsModule;

import java.util.HashMap;
import java.util.Map;

public class CompatChestRenderer extends VariantChestRenderer {
    private static final Map<BlockEntityType<?>, Material> MATERIALS = new HashMap<>();

    public CompatChestRenderer(BlockEntityRendererProvider.Context context, boolean isTrap) {
        super(context, isTrap);
    }


    @Override
    public Material getMaterial(ChestBlockEntity tile, ChestType type) {
MATERIALS.computeIfAbsent(tile.getType(), t->{
    WoodType wood;
    Block block = tile.getBlockState().getBlock();
    if(block instanceof CompatChestBlock b){
        wood =b.woodType;
    }
})
        Block var4 = tile.m_58900_().m_60734_();
        if (var4 instanceof VariantChestsModule.IVariantChest v) {
            StringBuilder tex = (new StringBuilder("quark_variant_chests/")).append(v.getChestType()).append('/');
            if (this.isTrap) {
                tex.append((String)this.choose(type, "trap", "trap_left", "trap_right"));
            } else {
                tex.append((String)this.choose(type, "normal", "left", "right"));
            }

            return new Material(InventoryMenu.f_39692_, new ResourceLocation("quark", tex.toString()));
        } else {
            return null;
        }
            return "model/chest/everycompat/" + ((CompatChestBlockTile)tile).woodType.getAppendableId() + "_";

        return super.getMaterial(tile, type);
    }
}
