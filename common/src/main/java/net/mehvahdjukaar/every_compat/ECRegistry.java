package net.mehvahdjukaar.every_compat;

import com.google.common.collect.ImmutableSet;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ECRegistry {

    public static void init(){

    }

    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(EveryCompat.res("all_woods"), AllWoodItem::new);

    @Nullable
    public static final RegSupplier<CreativeModeTab> MOD_TAB = ECConfigs.TAB_ENABLED.get() ?
                    RegHelper.registerCreativeModeTab(EveryCompat.res(EveryCompat.MOD_ID),
                            true,
                            builder -> builder.icon(() -> ALL_WOODS.get().getDefaultInstance())
                                    .backgroundSuffix("item_search.png")
                                    .title(Component.translatable("itemGroup.everycomp.everycomp"))
                                    .build())
                    : null;

    //TODO: use ML one when it updates
    @Deprecated(forRemoval = true)
    //call in setup when you have blocks
    public static void addBlocksToPOI(ResourceKey<PoiType> poi, Iterable<? extends Block> blocks) {
        var beehivePOI = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(poi);
        ImmutableSet.Builder<BlockState> builder = ImmutableSet.builder();
        builder.addAll(beehivePOI.value().matchingStates());
        for (var block : blocks) {
            builder.addAll(block.getStateDefinition().getPossibleStates());
        }
        PoiTypes.registerBlockStates(beehivePOI, builder.build());
    }
}
