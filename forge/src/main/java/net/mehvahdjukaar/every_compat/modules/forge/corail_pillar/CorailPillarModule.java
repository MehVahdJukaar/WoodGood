package net.mehvahdjukaar.every_compat.modules.forge.corail_pillar;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import ovh.corail.corail_pillar.block.BlockPillar;
import ovh.corail.corail_pillar.registry.ModTabs;

//SUPPORT: v5.9.1+
public class CorailPillarModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> PLANK_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_PLANK_PILLAR;
    public CorailPillarModule(String modId) {
        super(modId, "cpr");
        ResourceLocation tab = ModTabs.TAB_ID;

        LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "pillar",
                getModBlock("pillar_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                w -> new BlockPillar(w.getTypeName() + "_log", w.log, false)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stonecutting/pillar_oak_log"))
                .build();
        this.addEntry(LOG_PILLAR);

        SMALL_LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "small_pillar",
                getModBlock("small_pillar_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                w -> new BlockPillar(w.getTypeName() + "_log", w.log, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stonecutting/pillar_oak_planks"))
                .build();
        this.addEntry(SMALL_LOG_PILLAR);

        PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "pillar",
                getModBlock("pillar_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                w -> new BlockPillar(w.getTypeName() + "_planks", w.planks, false)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(PLANK_PILLAR);

        SMALL_PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "small_pillar",
                getModBlock("small_pillar_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                w -> new BlockPillar(w.getTypeName() + "_planks", w.planks, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTabKey(tab)
//                .addRecipe(modRes("stonecutting/"))
                .build();
        this.addEntry(SMALL_PLANK_PILLAR);

    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        LOG_PILLAR.blocks.forEach((wood, item) -> {
            // LOG
            stonecuttingRecipe(handler, wood.log, item, 2);
            stonecuttingRecipe(handler, wood.log, SMALL_LOG_PILLAR.blocks.get(wood), 4);

            // PLANKS
            stonecuttingRecipe(handler, wood.planks, PLANK_PILLAR.blocks.get(wood), 2);
            stonecuttingRecipe(handler, wood.planks, SMALL_PLANK_PILLAR.blocks.get(wood), 4);

        });
    }

    public void stonecuttingRecipe(ServerDynamicResourcesHandler handler, Block input, Block output, int count) {
        String recipeJSON = """
            {   
                "type":"minecraft:stonecutting",
                "ingredient":{
                    "item":"[INPUT]"
                },
                "result":"[OUTPUT]",
                "count": amount
            }
        """;

        String newJson = recipeJSON
                .replace("[INPUT]", Utils.getID(input).toString())
                .replace("[OUTPUT]", Utils.getID(output).toString())
                .replace("amount", String.valueOf(count));

        ResourceLocation resLoc = Utils.getID(output); // provide everycomp:cpr/namespace/BlockID

        handler.getPack().addBytes(resLoc, newJson.getBytes(), ResType.RECIPES);
    }

}

