package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.UtilityTexture;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//SUPPORT: v5.0.1+
public class BoatLoadModule extends SimpleModule {
    public final ItemOnlyEntrySet<WoodType, Item> largeBoats;
    public final ItemOnlyEntrySet<WoodType, Item> furnaceBoats;
    public final Map<WoodType, BoatloadBoatType> boatTypes = new HashMap<>();

    public BoatLoadModule(String modId) {
        super(modId, "abnbl");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.TOOLS_AND_UTILITIES;

        largeBoats = ItemOnlyEntrySet.builder(WoodType.class, "boat", "large",
                        getModItem("large_oak_boat"),
                        () -> VanillaWoodTypes.OAK,
                        w -> new LargeBoatItem(getBoatType(w))
                )
                .setTabKey(tab)
                .requiresChildren(VanillaWoodChildKeys.BOAT) //REASON: recipes
                .addTag(ItemTags.BOATS, Registries.ITEM)
                .addTag(modRes("large_boats"), Registries.ITEM)
                .addRecipe(modRes("large_oak_boat"))
//                .addTexture(modRes("entity/large_boat/oak"))
//                .addTextureM(modRes("entity/furnace_boat/oak_on"), EveryCompat.res("entity/furnace_boat_mask"))
//                .addTextureM(modRes("entity/furnace_boat/oak"), EveryCompat.res("entity/furnace_boat_mask"))
                .build();
        this.addEntry(largeBoats);

        furnaceBoats = ItemOnlyEntrySet.builder(WoodType.class, "furnace_boat",
                        getModItem("oak_furnace_boat"),
                        () -> VanillaWoodTypes.OAK,
                        w -> new FurnaceBoatItem(getBoatType(w))
                )
                .setTabKey(tab)
                .requiresChildren(VanillaWoodChildKeys.BOAT) //REASON: recipes
                .addTag(ItemTags.BOATS, Registries.ITEM)
                .addTag(modRes("furnace_boats"), Registries.ITEM)
                .addRecipe(modRes("oak_furnace_boat"))
//                .addTexture(modRes("item/large_oak_boat"))
//                .addTextureM(modRes("item/oak_furnace_boat"), EveryCompat.res("item/furnace_boat_mask"))
                .build();
        this.addEntry(furnaceBoats);
    }

    private BoatloadBoatType getBoatType(WoodType w) {
        return boatTypes.computeIfAbsent(w, k -> BoatloadBoatType.register(new BoatloadBoatType(
                EveryCompat.res(this.shortenedId() + "/" + w.getAppendableId()),
                () -> w.getItemOfThis("planks"),
                () -> w.getItemOfThis("boat"),
                () -> w.getItemOfThis("chest_boat"),
                () -> furnaceBoats.items.get(w),
                () -> largeBoats.items.get(w),
                w.canBurn(),
                false
        )));
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) -> {
            UtilityTexture.addBoatTextureEntity(furnaceBoats,
                    modRes("entity/furnace_boat/oak_on"), EveryCompat.res("entity/furnace_boat_mask"),
                    manager, sink);

            UtilityTexture.addBoatTextureEntity(furnaceBoats,
                    modRes("entity/furnace_boat/oak"), EveryCompat.res("entity/furnace_boat_mask"),
                    manager, sink);

            UtilityTexture.addBoatTextureItem(furnaceBoats,
                    modRes("item/oak_furnace_boat"), EveryCompat.res("item/furnace_boat_mask"),
                    manager, sink);

            UtilityTexture.addBoatTextureItem(largeBoats,
                    modRes("entity/large_boat/oak"), null,
                    manager, sink);

            UtilityTexture.addBoatTextureItem(largeBoats,
                    modRes("item/large_oak_boat"), null,
                    manager, sink);
        });
    }

}
