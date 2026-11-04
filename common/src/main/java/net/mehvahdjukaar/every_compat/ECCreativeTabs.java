package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ECCreativeTabs {

    public static void init() {}

    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(EveryCompat.res("all_woods"), AllWoodItem::new);

    public static final ResourceLocation TAB_ID = EveryCompat.res(EveryCompat.MOD_ID);

//    ResourceKey<CreativeModeTab> resourceKey = BuiltInRegistries
    @Nullable
    public static final Supplier<CreativeModeTab> MOD_TAB = !ECConfigs.TAB_ENABLED.get() ? null :
            RegHelper.registerCreativeModeTab(TAB_ID, builder -> builder
                            .icon(() -> ALL_WOODS.get().getDefaultInstance())
                            .title(Component.translatable("itemGroup.everycomp.everycomp"))
                            .backgroundSuffix("item_search.png")
                            .build()
            );

}
