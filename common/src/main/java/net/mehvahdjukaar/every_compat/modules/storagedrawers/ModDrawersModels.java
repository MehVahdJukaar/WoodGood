package net.mehvahdjukaar.every_compat.modules.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.modelprops.DrawerModelProperties;
import com.jaquadro.minecraft.storagedrawers.client.model.DrawerModelStore;
import com.jaquadro.minecraft.storagedrawers.client.model.PlatformDecoratedModel;
import com.jaquadro.minecraft.storagedrawers.client.model.decorator.DrawerModelDecorator;
import net.mehvahdjukaar.candlelight.api.PlatformImpl;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

public class ModDrawersModels {

    @PlatformImpl
    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        throw new AssertionError();
    }

    public static Set<ModelResourceLocation> getDrawerModelLocations(StorageDrawersModule module) {
        Set<ModelResourceLocation> locations = new HashSet<>();
        module.getDrawersOfType(BlockDrawers.class).forEach(b -> {
            for (BlockState state : b.getStateDefinition().getPossibleStates()) {
                locations.add(BlockModelShaper.stateToModelLocation(state));
            }
        });

        return locations;
    }

    public static BakedModel makeStandardDrawerModel(BakedModel parentModel) {
        DrawerModelDecorator decorator = new DrawerModelDecorator(DrawerModelStore.INSTANCE);
        return new PlatformDecoratedModel<>(parentModel, decorator, DrawerModelProperties.INSTANCE);
    }
}
