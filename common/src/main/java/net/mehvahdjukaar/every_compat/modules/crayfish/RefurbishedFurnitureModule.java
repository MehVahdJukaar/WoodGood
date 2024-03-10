package net.mehvahdjukaar.every_compat.modules.crayfish;

import com.mrcrayfish.furniture.refurbished.block.CeilingFanBlock;
import com.mrcrayfish.furniture.refurbished.block.ChairBlock;
import com.mrcrayfish.furniture.refurbished.block.MetalType;
import com.mrcrayfish.furniture.refurbished.block.TableBlock;
import com.mrcrayfish.furniture.refurbished.client.renderer.blockentity.CeilingFanBlockEntityRenderer;
import com.mrcrayfish.furniture.refurbished.core.ModBlockEntities;
import com.mrcrayfish.furniture.refurbished.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RefurbishedFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> darkFans;
    public final SimpleEntrySet<WoodType, Block> lightFans;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");

        // somebody else pls finish this <3
        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of().strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_chair"))
                .build();

        this.addEntry(chairs);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of()
                                .strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_particle"))
                .build();

        this.addEntry(tables);

        darkFans = SimpleEntrySet.builder(WoodType.class, "dark_ceiling_fan",
                        () -> getModBlock("oak_dark_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .defaultRecipe()
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_dark_ceiling_fan"))
                .build();

        this.addEntry(darkFans);

        lightFans = SimpleEntrySet.builder(WoodType.class, "light_ceiling_fan",
                        () -> getModBlock("oak_light_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .defaultRecipe()
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_light_ceiling_fan"))
                .build();

        this.addEntry(lightFans);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        // code copied from ResourceUtils.addStandardResources
        {
            //remove the ones from mc namespace
            StaticResource darkBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                    modRes("extra/oak_dark_ceiling_fan_blade")
            ));
            addFanModels(handler, manager, darkBlade, darkFans);
        }
        {
            //remove the ones from mc namespace
            StaticResource lightBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                    modRes("extra/oak_light_ceiling_fan_blade")
            ));
            addFanModels(handler, manager, lightBlade, lightFans);
        }

    }

    private void addFanModels(ClientDynamicResourcesHandler handler, ResourceManager manager, StaticResource darkBlade, SimpleEntrySet<WoodType, Block> darkFans) {
        BlockTypeResTransformer<WoodType> modelModifier =
                ResourcesUtils.standardModelTransformer(modId, manager, darkFans.getBaseType(),
                        darkFans.typeName, null);

        darkFans.blocks.forEach((w, b) -> {
            ResourceLocation id = Utils.getID(b);

            //creates item model
            try {
                StaticResource newModel = modelModifier.transform(darkBlade, id, w);
                assert newModel.location != darkBlade.location : "ids cant be the same";
                handler.addResourceIfNotPresent(manager, newModel);
            } catch (Exception exception) {
                EveryCompat.LOGGER.error("Failed to add {} model json file:", b, exception);
            }
        });
    }

    @Override
    public void onClientSetup() {
        super.onClientSetup();
        darkFans.blocks.forEach((key, value) -> {
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> {
                ModelManager manager = Minecraft.getInstance().getModelManager();
                return ClientHelper.getModel(manager,
                        EveryCompat.res("extra/rfm/" + key.getAppendableId() + "_dark_ceiling_fan_blade"));
            });
        });
        lightFans.blocks.forEach((key, value) -> {
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> {
                ModelManager manager = Minecraft.getInstance().getModelManager();
                return ClientHelper.getModel(manager,
                        EveryCompat.res("extra/rfm/" + key.getAppendableId() + "_light_ceiling_fan_blade"));
            });
        });
    }

    @Override
    public void onClientInit() {
        super.onClientInit();
        ClientHelper.addSpecialModelRegistration(event -> {
            darkFans.blocks.keySet().forEach(w -> {
                event.register(EveryCompat.res("extra/rfm/" + w.getAppendableId() + "_dark_ceiling_fan_blade"));
                event.register(EveryCompat.res("extra/rfm/" + w.getAppendableId() + "_light_ceiling_fan_blade"));
            });
        });
    }
}
