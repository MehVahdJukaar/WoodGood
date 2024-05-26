package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.forge.variants.VariantVanillaBlocksModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CompatChestBlockRenderer extends ChestRenderer<CompatChestBlockEntity> {
    private final Map<WoodType, Material> single = new HashMap<>();
    private final Map<WoodType, Material> left = new HashMap<>();
    private final Map<WoodType, Material> right = new HashMap<>();

    //assumes standard naming here. Generalize if needed
    public CompatChestBlockRenderer(BlockEntityRendererProvider.Context context, CompatModule module) {
        super(context);

        for (WoodType w : WoodTypeRegistry.getTypes()) {
            if (!w.isVanilla()) {
                String path = module.shortenedId() + "/" + w.getAppendableId() + "_chest";
                single.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path)));
                left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path + "_left")));
                right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res("entity/chest/" + path + "_right")));
            }
        }
    }

    @Override
    protected @NotNull Material getMaterial(CompatChestBlockEntity blockEntity, ChestType chestType) {
        WoodType w = blockEntity.getWoodType();
        return switch (chestType) {
            case LEFT -> left.get(w);
            case RIGHT -> right.get(w);
            default -> single.get(w);
        };
    }
}
