package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;

/**
 * Use this to register new wood type blocks and module
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 */
public class EveryCompatAPI {

    /**
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     */
    public static void registerModule(CompatModule module) {
        EveryCompat.ACTIVE_MODULES.put(module.getModId(), module);
        EveryCompat.SERVER_RESOURCES.getPack().addNamespaces(module.getModId());
    }

    //for each entry that you register you will need to add "block_type.everycomp.your_type" translation string to your lang file

    //example using simple module class
    /*

        SimpleModule mod = new SimpleModule("twigs", "tw");
        SimpleEntrySet<?, ?> e = SimpleEntrySet.builder(WoodType.class,"table", TwigsBlocks.OAK_TABLE, ()->WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).instabreak()))
                .addTag(new ResourceLocation("twigs:tables"), Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setTab(()->Twigs.ITEM_GROUP)
                .addTexture(new ResourceLocation("twigs:block/oak_table"))
                .addTexture(new ResourceLocation("twigs:block/oak_table_top"))
                .addTexture(new ResourceLocation("twigs:block/oak_table_bottom"))
                .build();
        mod.addEntry(e);

        WoodGoodAPI.registerModule(mod)

     */


    //custom register a wood type

    /*
    public static void init() {
        BlockSetAPI.addBlockTypeFinder(WoodType.class, WoodType.Finder
            .simple("my_mod", "cherry", "cherry_plank", "cherry_stem"));
    }
    */


}
