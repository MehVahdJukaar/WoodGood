package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class BoatLoadModule extends SimpleModule {
    public final ItemOnlyEntrySet<WoodType, Item> largeBoats;
    public final ItemOnlyEntrySet<WoodType, Item> furnaceBoats;
    public final Map<WoodType, BoatloadBoatType> boatTypes = new HashMap<>();

    public BoatLoadModule(String modId) {
        super(modId, "abnbl");
        var tab = CreativeModeTabs.TOOLS_AND_UTILITIES;

        largeBoats = ItemOnlyEntrySet.builder(WoodType.class, "boat", "large",
                        getModItem("large_oak_boat"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LargeBoatItem(getBoatType(w)))
                .setTabKey(() -> tab)
                .requiresChildren("chest_boat")
                .requiresChildren("boat")
                .addTag(ItemTags.BOATS, Registries.ITEM)
                .addTag(modRes("large_boats"), Registries.ITEM)
                .addRecipe(modRes("large_oak_boat"))
                .addTexture(modRes("entity/large_boat/oak"))
                .addTextureM(modRes("entity/furnace_boat/oak_on"), EveryCompat.res("entity/furnace_boat_mask"))
                .addTextureM(modRes("entity/furnace_boat/oak"), EveryCompat.res("entity/furnace_boat_mask"))
                .build();
        this.addEntry(largeBoats);

        furnaceBoats = ItemOnlyEntrySet.builder(WoodType.class, "furnace_boat",
                        getModItem("oak_furnace_boat"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FurnaceBoatItem(getBoatType(w)))
                .setTabKey(() -> tab)
                .requiresChildren("chest_boat")
                .requiresChildren("boat")
                .addTag(ItemTags.BOATS, Registries.ITEM)
                .addTag(modRes("furnace_boats"), Registries.ITEM)
                .addRecipe(modRes("oak_furnace_boat"))
                .addTexture(modRes("item/large_oak_boat"))
                .addTextureM(modRes("item/oak_furnace_boat"), EveryCompat.res("item/furnace_boat_mask"))
                .createPaletteFromChild(p -> {
                }, "boat")
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
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
    }
}
