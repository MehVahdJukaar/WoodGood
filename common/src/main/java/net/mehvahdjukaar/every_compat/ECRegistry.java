package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.configs.EarlyConfigs.TAB_ITEM_SEARCH_ENABLED;

public class ECRegistry {

    public static void init() {}

    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(EveryCompat.res("all_woods"), AllWoodItem::new);


    @Nullable
    public static final Supplier<CreativeModeTab> MOD_TAB = EarlyConfigs.TAB_ENABLED.get() ? null :
            RegHelper.registerCreativeModeTab(EveryCompat.res(EveryCompat.MOD_ID),
                    builder -> builder
                            .title(Component.translatable("itemGroup.everycomp.everycomp"))
                            .backgroundSuffix((TAB_ITEM_SEARCH_ENABLED.get()) ? "item_search.png" : "items.png")
                            .icon(() -> ALL_WOODS.get().getDefaultInstance())
            );

}
