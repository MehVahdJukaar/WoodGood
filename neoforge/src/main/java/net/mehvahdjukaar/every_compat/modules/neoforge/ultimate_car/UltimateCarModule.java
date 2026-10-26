package net.mehvahdjukaar.every_compat.modules.neoforge.ultimate_car;

import de.maxhenkel.car.entity.car.parts.PartBodyBigWood;
import de.maxhenkel.car.entity.car.parts.PartBodyWood;
import de.maxhenkel.car.entity.car.parts.PartBumper;
import de.maxhenkel.car.entity.car.parts.PartLicensePlateHolder;
import de.maxhenkel.car.items.ItemCarPart;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.function.Supplier;

import static java.util.Map.entry;

///SUPPORT: v1.0.46+
public class UltimateCarModule extends EveryCompatModule {

    public final ItemOnlyEntrySet<WoodType, Item> license_plate_holder;
    public final ItemOnlyEntrySet<WoodType, Item> bumper;
    public final ItemOnlyEntrySet<WoodType, Item> body;
    public final ItemOnlyEntrySet<WoodType, Item> big_body;

    public static Map<String, ResourceLocation> mapPlanks = Map.ofEntries(
            entry("born_in_chaos_v1:scorched", ResourceLocation.parse("born_in_chaos_v1:block/opdosk.png")),

            entry("luminous_nether:withered", ResourceLocation.parse("luminous_nether:textures/block/ashplanks.png") ),

            entry("mofus_better_end_:weepingstar", ResourceLocation.parse("mofus_better_end_:textures/block/weeoingstarplanks.png") ),

            entry("promenade:sakura", ResourceLocation.parse("promenade:textures/block/sakura/planks.png") ),
            entry("promenade:dark_amaranth", ResourceLocation.parse("promenade:textures/block/dark_amaranth/planks.png") ),
            entry("promenade:palm", ResourceLocation.parse("promenade:textures/block/palm/planks.png") ),
            entry("promenade:maple", ResourceLocation.parse("promenade:textures/block/maple/planks.png") ),

            entry("dawnoftimebuilder:waxed_oak", ResourceLocation.parse("dawnoftimebuilder:textures/block/waxed_oak_planks.png") ),
            entry("dawnoftimebuilder:charred_spruce", ResourceLocation.parse("dawnoftimebuilder:textures/block/charred_spruce_planks.png") ),

            entry("shadowlands:vellium", ResourceLocation.parse("shadowlands:textures/block/velliumplanks.png"))
    );

    public UltimateCarModule(String modId) {
        super(modId, "ucm");
        Supplier<CreativeModeTab> tab = getModTab("car_parts");

        ResourceManager manager = Minecraft.getInstance().getResourceManager();

        license_plate_holder = ItemOnlyEntrySet.builder(WoodType.class, "license_plate_holder",
                        getModItem("oak_license_plate_holder"), () -> VanillaWoodTypes.OAK,
                        w -> {
                            ResourceLocation planksResLoc;
                            try {
                                planksResLoc = RPUtils.findFirstBlockTextureLocation(manager, w.planks);
                            } catch (Exception e) {
                                planksResLoc = ResourceLocation.withDefaultNamespace("block/oak_planks");
                                EveryCompat.LOGGER.error("Failed to get ResourceLocation for {} - {}", Utils.getID(w.planks), e);
                            }
                            /*= (mapPlanks.containsKey(w.getId().toString()))
                                    ? mapPlanks.get(w.getId().toString()).withPrefix("textures/")
                                    : ResourceLocation.parse(
                                    w.createFullIdWith(
                                            w.getNamespace(),
                                            "textures/block", "",
                                            "", "planks.png"
                                    )
                            );*/
                            return new ItemCarPart(new PartLicensePlateHolder(planksResLoc.withPrefix("textures/")));
                        }
                )
                .addTexture(modRes("item/oak_license_plate_holder"))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(license_plate_holder);

        bumper = ItemOnlyEntrySet.builder(WoodType.class, "bumper",
                        getModItem("oak_bumper"), () -> VanillaWoodTypes.OAK,
                        w -> {

                            ResourceLocation planksResLoc;
                            try {
                                planksResLoc = RPUtils.findFirstBlockTextureLocation(manager, w.planks);
                            } catch (Exception e) {
                                planksResLoc = ResourceLocation.withDefaultNamespace("block/oak_planks");
                                EveryCompat.LOGGER.error("Failed to get ResourceLocation for {} - {}", Utils.getID(w.planks), e);
                            }
                            return new ItemCarPart(new PartBumper(planksResLoc.withPrefix("textures/")));
                        }
                )
                .addTexture(modRes("item/oak_bumper"))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bumper);

        body = ItemOnlyEntrySet.builder(WoodType.class, "body",
                        getModItem("oak_body"), () -> VanillaWoodTypes.OAK,
                        w -> {
//                            ResourceLocation planksResLoc = ResourceLocation.parse(
//                                    w.createFullIdWith(
//                                            EveryCompat.MOD_ID,
//                                            "textures/entity",
//                                            shortenedId(),
//                                            "car_wood",
//                                            ".png"
//                                    )
//
//                            );
                            ResourceLocation planksResLoc;
                            try {
                                planksResLoc = RPUtils.findFirstBlockTextureLocation(manager, w.planks);
                            } catch (Exception e) {
                                planksResLoc = ResourceLocation.withDefaultNamespace("block/oak_planks");
                                EveryCompat.LOGGER.error("Failed to get ResourceLocation for {} - {}", Utils.getID(w.planks), e);
                            }
                            return new ItemCarPart(new PartBodyWood(planksResLoc.withPrefix("textures/"), w.getTypeName()));
                        }
                )
                .addTextureM(modRes("entity/car_wood_oak"), EveryCompat.res("entity/ucm/car_wood_oak_m"))
                .addTexture(modRes("item/oak_body"))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(body);

        big_body = ItemOnlyEntrySet.builder(WoodType.class, "body", "big",
                        getModItem("big_oak_body"), () -> VanillaWoodTypes.OAK,
                        w -> {
//                            ResourceLocation planksResLoc =  ResourceLocation.parse(
//                                    w.createFullIdWith(
//                                            EveryCompat.MOD_ID,
//                                            "textures/entity",
//                                            shortenedId(),
//                                            "car_big_wood",
//                                            ".png"
//                                    )
//                            );
                            ResourceLocation planksResLoc;
                            try {
                                planksResLoc = RPUtils.findFirstBlockTextureLocation(manager, w.planks);
                            } catch (Exception e) {
                                planksResLoc = ResourceLocation.withDefaultNamespace("block/oak_planks");
                                EveryCompat.LOGGER.error("Failed to get ResourceLocation for {} - {}", Utils.getID(w.planks), e);
                            }
                            return new ItemCarPart(new PartBodyBigWood(planksResLoc.withPrefix("textures/"), w.getTypeName()));
                        }
                )
                .addTextureM(modRes("entity/car_big_wood_oak"), EveryCompat.res("entity/ucm/car_big_wood_oak_m"))
                .addTexture(modRes("item/big_oak_body"))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(big_body);

    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        super.addTranslations(clientDynamicResourcesHandler, lang);

        body.items.forEach((woodType, item) -> addDynamicEntry(lang, "car_variant.%s", woodType));
    }

    /// Custom Lang for "car_variant.oak": "Oak"
    public void addDynamicEntry(AfterLanguageLoadEvent lang, String key, BlockType type) {
        String base = lang.getEntry(key);
        if (base != null) {
            String typeName = lang.getEntry(type.getTranslationKey());
            if (typeName != null) {
                String typeKey = String.format(key, typeName.toLowerCase());
                lang.addEntry(typeKey, String.format(base, typeName));
            }
        }
    }
}
