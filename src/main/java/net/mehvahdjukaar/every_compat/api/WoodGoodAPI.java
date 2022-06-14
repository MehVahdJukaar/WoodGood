package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.modules.CompatModule;

public class WoodGoodAPI {

    /**
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     */
    public static void registerModule(CompatModule module) {
        WoodGood.ACTIVE_MODULES.put(module.getModId(), module);
    }

    //for each entry that you register you will need to add "block_type.everycomp.your_type" translation string to your lang file

    //example using simple module class
    /*

        SimpleModule mod = new SimpleModule("twigs", "tw");
        SimpleEntrySet<?, ?> e = SimpleEntrySet.builder(WoodType.class,"table", TwigsBlocks.OAK_TABLE, ()->WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks).instabreak()))
                .addTag(new ResourceLocation("twigs:tables"), Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setTab(Twigs.ITEM_GROUP)
                .addTexture(new ResourceLocation("twigs:block/oak_table"))
                .addTexture(new ResourceLocation("twigs:block/oak_table_top"))
                .addTexture(new ResourceLocation("twigs:block/oak_table_bottom"))
                .build();
        mod.addEntry(e);

        WoodGoodAPI.registerModule(mod)

     */

}
