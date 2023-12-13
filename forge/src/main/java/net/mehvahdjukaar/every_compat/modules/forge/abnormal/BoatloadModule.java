package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.registry.BoatTypeRegistry;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadItemTags;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.HashMap;

import static com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper.createSimpleItemProperty;

public class BoatloadModule extends SimpleModule {

    public final ItemOnlyEntrySet<WoodType, Item> BOATS;
    public final ItemOnlyEntrySet<WoodType, Item> CHEST_BOATS;
    public final ItemOnlyEntrySet<WoodType, Item> FURNACE_BOATS;
    public final ItemOnlyEntrySet<WoodType, Item> LARGE_BOATS;

    private static final HashMap<WoodType, BoatloadBoatType> BOATLOAD_BOAT_TYPES = new HashMap<>();
    private static final HashMap<WoodType, String> BLUEPRINT_BOAT_TYPES = new HashMap<>();

    public BoatloadModule(String modId) {
        super(modId, "abnbl");

        BOATS = ItemOnlyEntrySet.builder(WoodType.class, "boat",
                        BoatloadItems.CRIMSON_BOAT, BoatloadModule::crimsonTypeSupplier,
                        woodType -> new BlueprintBoatItem(false, BLUEPRINT_BOAT_TYPES
                                .computeIfAbsent(woodType, this::generateBlueprintBoatType),
                                createSimpleItemProperty(1, CreativeModeTab.TAB_TRANSPORTATION)))
                .addTag(ItemTags.BOATS, Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_TRANSPORTATION)
                .addRecipe(modRes("crimson_boat"))
                .addTexture(modRes("item/crimson_boat"))
                .addTexture(modRes("entity/boat/crimson"))
                .build();
        this.addEntry(BOATS);

        CHEST_BOATS = ItemOnlyEntrySet.builder(WoodType.class, "chest_boat",
                        BoatloadItems.CRIMSON_CHEST_BOAT, BoatloadModule::crimsonTypeSupplier,
                        woodType -> new BlueprintBoatItem(true, BLUEPRINT_BOAT_TYPES
                                .computeIfAbsent(woodType, this::generateBlueprintBoatType),
                                createSimpleItemProperty(1, CreativeModeTab.TAB_TRANSPORTATION)))
                .addTag(ItemTags.CHEST_BOATS, Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_TRANSPORTATION)
                .addRecipe(modRes("crimson_chest_boat"))
                .addTextureM(modRes("item/crimson_chest_boat"),
                        EveryCompat.res("item/" + shortenedId() + "/chest_boat_mask"))
                .addTextureM(modRes("entity/chest_boat/crimson"),
                        EveryCompat.res("entity/chest_boat/" + shortenedId() + "/mask"))
                .build();
        this.addEntry(CHEST_BOATS);

        FURNACE_BOATS = ItemOnlyEntrySet.builder(WoodType.class, "furnace_boat",
                        BoatloadItems.OAK_FURNACE_BOAT, () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new FurnaceBoatItem(BOATLOAD_BOAT_TYPES
                                .computeIfAbsent(woodType, this::generateBoatloadBoatType)))
                .addTag(BoatloadItemTags.FURNACE_BOATS, Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_TRANSPORTATION)
                .addRecipe(modRes("oak_furnace_boat"))
                .addTextureM(modRes("item/oak_furnace_boat"),
                        EveryCompat.res("item/" + shortenedId() + "/furnace_boat_mask"))
                .addTextureM(modRes("entity/furnace_boat/oak"),
                        EveryCompat.res("entity/furnace_boat/" + shortenedId() + "/mask"))
                .addTextureM(modRes("entity/furnace_boat/oak_on"),
                        EveryCompat.res("entity/furnace_boat/" + shortenedId() + "/mask"))
                .build();
        this.addEntry(FURNACE_BOATS);

        LARGE_BOATS = ItemOnlyEntrySet.builder(WoodType.class, "boat", "large",
                        BoatloadItems.LARGE_OAK_BOAT, () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new LargeBoatItem(BOATLOAD_BOAT_TYPES
                                .computeIfAbsent(woodType, this::generateBoatloadBoatType)))
                .addTag(BoatloadItemTags.LARGE_BOATS, Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_TRANSPORTATION)
                .addRecipe(modRes("large_oak_boat"))
                .addTexture(modRes("item/large_oak_boat"))
                .addTexture(modRes("entity/large_boat/oak"))
                .build();
        this.addEntry(LARGE_BOATS);
    }


    public String generateBlueprintBoatType(WoodType woodType) {
        String key = new ResourceLocation(EveryCompat.MOD_ID,
                shortenedId() + "/" + woodType.getTexturePath()).toString();
        BoatTypeRegistry.registerBoat(
                key,
                () -> BOATS.items.get(woodType),
                () -> CHEST_BOATS.items.get(woodType),
                () -> woodType.planks);
        return key;
    }

    public BoatloadBoatType generateBoatloadBoatType(WoodType woodType) {
        return BoatloadBoatType.register(BoatloadBoatType.create(
                new ResourceLocation(EveryCompat.MOD_ID, shortenedId() + "/" + woodType.getTexturePath()),
                woodType.planks::asItem,
                () -> BOATS.items.getOrDefault(woodType, woodType.getItemOfThis("boat")),
                () -> CHEST_BOATS.items.getOrDefault(woodType, woodType.getItemOfThis("chest_boat")),
                () -> FURNACE_BOATS.items.get(woodType),
                () -> LARGE_BOATS.items.get(woodType)
        ));
    }

    private static final ResourceLocation CRIMSON = new ResourceLocation("minecraft", "crimson");
    private static WoodType crimsonTypeSupplier() {
        return WoodTypeRegistry.getValue(CRIMSON);
    }
}
