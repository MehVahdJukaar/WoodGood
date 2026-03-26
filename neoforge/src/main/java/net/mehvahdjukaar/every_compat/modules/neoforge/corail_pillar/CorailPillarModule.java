package net.mehvahdjukaar.every_compat.modules.neoforge.corail_pillar;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import ovh.corail.corail_pillar.block.BlockPillar;
import ovh.corail.corail_pillar.registry.ModTabs;

//SUPPORT: v5.9.1+
public class CorailPillarModule extends EveryCompatModule {
    public final SimpleEntrySet<WoodType, Block> LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> PLANK_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_PLANK_PILLAR;
    public CorailPillarModule(String modId) {
        super(modId, "cpr");
        ResourceLocation tab = ModTabs.TAB_ID;

        LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "pillar",
                getModBlock("pillar_oak_log"), () -> VanillaWoodTypes.OAK,
                w -> new BlockPillar(w.getTypeName() + "_log", w.log, false)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTab(getTab(tab))
                .addRecipe(modRes("stonecutting/pillar_oak_log"))
                .build();
        this.addEntry(LOG_PILLAR);

        SMALL_LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "small_pillar",
                getModBlock("small_pillar_oak_log"), () -> VanillaWoodTypes.OAK,
                w -> new BlockPillar(w.getTypeName() + "_log", w.log, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTab(getTab(tab))
                .addRecipe(modRes("stonecutting/small_pillar_oak_log"))
                .build();
        this.addEntry(SMALL_LOG_PILLAR);

        PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "pillar",
                getModBlock("pillar_oak_planks"), () -> VanillaWoodTypes.OAK,
                w -> new BlockPillar(w.getTypeName() + "_planks", w.planks, false)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTab(getTab(tab))
                .build();
        this.addEntry(PLANK_PILLAR);

        SMALL_PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "small_pillar",
                getModBlock("small_pillar_oak_planks"), () -> VanillaWoodTypes.OAK,
                w -> new BlockPillar(w.getTypeName() + "_planks", w.planks, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar"), Registries.BLOCK)
                .addTag(modRes("wooden_pillar"), Registries.BLOCK)
                .setTab(getTab(tab))
                .addRecipe(modRes("stonecutting/pillar_oak_planks"))
                .build();
        this.addEntry(SMALL_PLANK_PILLAR);

    }

}

