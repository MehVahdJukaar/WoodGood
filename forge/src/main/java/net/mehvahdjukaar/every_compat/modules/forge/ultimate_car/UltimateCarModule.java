package net.mehvahdjukaar.every_compat.modules.forge.ultimate_car;

import de.maxhenkel.car.entity.car.parts.PartBodyBigWood;
import de.maxhenkel.car.entity.car.parts.PartBodyWood;
import de.maxhenkel.car.entity.car.parts.PartBumper;
import de.maxhenkel.car.entity.car.parts.PartLicensePlateHolder;
import de.maxhenkel.car.items.ItemCarPart;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;

import static java.util.Map.entry;

//SUPPORT: v1.0.34+
public class UltimateCarModule extends SimpleModule {

    public final ItemOnlyEntrySet<WoodType, Item> license_plate_holder;
    public final ItemOnlyEntrySet<WoodType, Item> bumper;
    public final ItemOnlyEntrySet<WoodType, Item> body;
    public final ItemOnlyEntrySet<WoodType, Item> big_body;

    public static Map<String, ResourceLocation> mapPlanks = Map.ofEntries(
            entry("born_in_chaos_v1:scorched", new ResourceLocation("born_in_chaos_v1:block/opdosk.png")),

            entry("luminous_nether:withered", new ResourceLocation("luminous_nether:textures/block/ashplanks.png") ),

            entry("mofus_better_end_:weepingstar", new ResourceLocation("mofus_better_end_:textures/block/weeoingstarplanks.png") ),

            entry("promenade:sakura", new ResourceLocation("promenade:textures/block/sakura/planks.png") ),
            entry("promenade:dark_amaranth", new ResourceLocation("promenade:textures/block/dark_amaranth/planks.png") ),
            entry("promenade:palm", new ResourceLocation("promenade:textures/block/palm/planks.png") ),
            entry("promenade:maple", new ResourceLocation("promenade:textures/block/maple/planks.png") ),

            entry("dawnoftimebuilder:waxed_oak", new ResourceLocation("dawnoftimebuilder:textures/block/waxed_oak_planks.png") ),
            entry("dawnoftimebuilder:charred_spruce", new ResourceLocation("dawnoftimebuilder:textures/block/charred_spruce_planks.png") ),

            entry("shadowlands:vellium", new ResourceLocation("shadowlands:textures/block/velliumplanks.png"))
    );

    public UltimateCarModule(String modId) {
        super(modId, "ucm");
        ResourceLocation tab = modRes("car_parts");

        license_plate_holder = ItemOnlyEntrySet.builder(WoodType.class, "license_plate_holder",
                        getModItem("oak_license_plate_holder"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            ResourceLocation planksResLoc = (mapPlanks.containsKey(w.getId().toString()))
                                    ? mapPlanks.get(w.getId().toString()).withPrefix("textures/")
                                    : new ResourceLocation(
                                            w.createFullIdWith(
                                                    w.getNamespace(),
                                                    "textures/block",
                                                    "",
                                                    "",
                                                    "planks.png"
                                            )
                            );
                            return new ItemCarPart(new PartLicensePlateHolder(planksResLoc));
                        }
                )
                .addTexture(modRes("item/oak_license_plate_holder"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(license_plate_holder);

        bumper = ItemOnlyEntrySet.builder(WoodType.class, "bumper",
                        getModItem("oak_bumper"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            ResourceLocation planksResLoc = (mapPlanks.containsKey(w.getId().toString()))
                                    ? mapPlanks.get(w.getId().toString()).withPrefix("textures/")
                                    : new ResourceLocation(
                                            w.createFullIdWith(
                                                    w.getNamespace(),
                                                    "textures/block",
                                                    "",
                                                    "",
                                                    "planks.png"
                                            )
                            );
                            return new ItemCarPart(new PartBumper(planksResLoc));
                        }
                )
                .addTexture(modRes("item/oak_bumper"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bumper);

        body = ItemOnlyEntrySet.builder(WoodType.class, "body",
                        getModItem("oak_body"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            ResourceLocation planksResLoc = new ResourceLocation(
                                            w.createFullIdWith(
                                                    EveryCompat.MOD_ID,
                                                    "textures/entity",
                                                    shortenedId(),
                                                    "car_wood",
                                                    ".png"
                                            )
                            );
                            return new ItemCarPart(new PartBodyWood(planksResLoc, w.getTypeName()));
                        }
                )
                .addTextureM(modRes("entity/car_wood_oak"), EveryCompat.res("entity/ucm/car_wood_oak_m"))
                .addTexture(modRes("item/oak_body"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(body);

        big_body = ItemOnlyEntrySet.builder(WoodType.class, "body", "big",
                        getModItem("big_oak_body"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            ResourceLocation planksResLoc =  new ResourceLocation(
                                            w.createFullIdWith(
                                                    EveryCompat.MOD_ID,
                                                    "textures/entity",
                                                    shortenedId(),
                                                    "car_big_wood",
                                                    ".png"
                                            )
                            );
                            return new ItemCarPart(new PartBodyBigWood(planksResLoc, w.getTypeName()));
                        }
                )
                .addTextureM(modRes("entity/car_big_wood_oak"), EveryCompat.res("entity/ucm/car_big_wood_oak_m"))
                .addTexture(modRes("item/big_oak_body"))
                .setTabKey(tab)
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