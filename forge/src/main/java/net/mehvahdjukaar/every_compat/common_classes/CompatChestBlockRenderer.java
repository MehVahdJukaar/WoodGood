package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
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
    private final Map<WoodType, Material> trapped = new HashMap<>();
    private final Map<WoodType, Material> trapped_left = new HashMap<>();
    private final Map<WoodType, Material> trapped_right = new HashMap<>();

    //assumes standard naming here. Generalize if needed
    public CompatChestBlockRenderer(BlockEntityRendererProvider.Context context, String shortenedId) {
        super(context);
        for (WoodType w : WoodTypeRegistry.getTypes()) {
            if (w.isVanilla()) continue;
            String path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_chest";
            String trapped_path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_trapped_chest";
            if (!w.isVanilla()) {
                single.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path)));
                left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path + "_left")));
                right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path + "_right")));
                trapped.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path)));
                trapped_left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path + "_left")));
                trapped_right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path + "_right")));
            }
        }
    }

    @Override
    protected @NotNull Material getMaterial(CompatChestBlockEntity blockEntity, ChestType chestType) {
        WoodType w = blockEntity.getWoodType();
        if (blockEntity.isTrapped()) {
            return switch (chestType) {
                case LEFT -> trapped_left.get(w);
                case RIGHT -> trapped_right.get(w);
                default -> trapped.get(w);
            };
        } else {
            return switch (chestType) {
                case LEFT -> left.get(w);
                case RIGHT -> right.get(w);
                default -> single.get(w);
            };
        }
    }
}
