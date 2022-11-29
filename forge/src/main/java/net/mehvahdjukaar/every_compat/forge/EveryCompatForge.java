package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompat {
    public static final String MOD_ID = EveryCompat.MOD_ID;

    public EveryCompatForge() {
        this.commonInit();

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);
        addModule("mcwbridges", () -> MacawBridgesModule::new);


        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);

        addModule("create", () -> CreateModule::new);

        //addModule("graveyard", () -> GraveyardModule::new);


        addModule("cfm", () -> MrCrayfishFurnitureModule::new);
        addModule("backpacked", () -> BackpackedModule::new);

        addModule("campchair", () -> CampChairModule::new);

        addModule("quark", () -> QuarkModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("storagedrawers", () -> StorageDrawersModule::new);

        addModule("woodworks", () -> WoodworksModule::new);

        if(PlatformHelper.getEnv().isClient()){
            EveryCompatClient.commonInit();
        }

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onRegister(RegisterEvent event) {
        var key = event.getRegistryKey();
        if (key == Registry.ITEM_REGISTRY) {
            this.registerItems((ResourceLocation a, Item b) -> event.getForgeRegistry().register(a, b));
        } else if (key == Registry.BLOCK_ENTITY_TYPE_REGISTRY) {
            this.registerTiles((ResourceLocation a, BlockEntityType<?> b) -> event.getForgeRegistry().register(a, b));
        } else if (key == Registry.ENTITY_TYPE_REGISTRY) {
            this.registerEntities((ResourceLocation a, EntityType<?> b) -> event.getForgeRegistry().register(a, b));
        }
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        this.commonSetup();
    }
}
