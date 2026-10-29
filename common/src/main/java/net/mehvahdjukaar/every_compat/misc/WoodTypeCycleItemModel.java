package net.mehvahdjukaar.every_compat.misc;

import com.mojang.serialization.MapCodec;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ResolvableModel;
import org.joml.Matrix4fc;

public class WoodTypeCycleItemModel extends BlockTypeCycleItemModel<WoodType> {

    private static final WoodTypeCycleItemModel INSTANCE = new WoodTypeCycleItemModel();

    public WoodTypeCycleItemModel() {
        super(WoodType.class);
    }

    public record Unbaked() implements ItemModel.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation) {
            return INSTANCE;
        }

        @Override
        public void resolveDependencies(ResolvableModel.Resolver resolver) {
        }
    }
}
