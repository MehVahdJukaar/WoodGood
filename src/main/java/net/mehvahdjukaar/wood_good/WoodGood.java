package net.mehvahdjukaar.wood_good;


import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.mehvahdjukaar.wood_good.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.wood_good.modules.deco_block.DecoBlocksModule;
import net.mehvahdjukaar.wood_good.modules.quark.QuarkModule;
import net.mehvahdjukaar.wood_good.modules.twigs.TwigsModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Author: MehVahdJukaar
 */
@Mod(WoodGood.MOD_ID)
public class WoodGood {
    public static final String MOD_ID = "wood_good";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static final List<CompatModule> ACTIVE_MODULES = new ArrayList<>();

    public static final List<String> COMPETITOR_MODS = new ArrayList<>();

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.forEach(action);
    }

    public WoodGood() {

        addCompetitorMods("much_more_mod_compat", "compatoplenty");

        addModule("decorative_blocks", DecoBlocksModule::new);
        addModule("twigs", TwigsModule::new);
        addModule("quark", QuarkModule::new);
        addModule("another_furniture", AnotherFurnitureModule::new);

        BlockSetManager.addBlockSetRegistrationCallback(this::registerWoodStuff, Block.class, WoodType.class);
        BlockSetManager.addBlockSetRegistrationCallback(this::registerLeavesStuff, Block.class, LeavesType.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //bus.addListener(WoodGood::init);
        bus.register(this);

        // MinecraftForge.EVENT_BUS.register(this);

        var serverRes = new ServerDynamicResourcesHandler();
        serverRes.register(bus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            var clientRes = new ClientDynamicResourcesHandler();
            clientRes.register(bus);
        }

    }


    private void addCompetitorMods(String... mods) {
        Arrays.stream(mods).forEach(m -> {
            if (ModList.get().isLoaded(m)) COMPETITOR_MODS.add(m);
        });
    }

    private void addModule(String modId, Function<String, CompatModule> moduleFactory) {
        if (ModList.get().isLoaded(modId)) ACTIVE_MODULES.add(moduleFactory.apply(modId));
    }

    @SubscribeEvent
    public void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ACTIVE_MODULES.forEach(m -> m.onModSetup(event));
        });
    }

    public void registerWoodStuff(RegistryEvent.Register<Block> event, Collection<WoodType> woods) {
        var reg = event.getRegistry();
        ACTIVE_MODULES.forEach(m -> m.registerWoodBlocks(reg, woods));
    }

    public void registerLeavesStuff(RegistryEvent.Register<Block> event, Collection<LeavesType> leaves) {
        var reg = event.getRegistry();
        ACTIVE_MODULES.forEach(m -> m.registerLeavesBlocks(reg, leaves));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        ACTIVE_MODULES.forEach(m -> m.registerItems(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerTiles(RegistryEvent.Register<BlockEntityType<?>> event) {
        ACTIVE_MODULES.forEach(m -> m.registerTiles(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        ACTIVE_MODULES.forEach(m -> m.registerEntities(event.getRegistry()));
    }

}
