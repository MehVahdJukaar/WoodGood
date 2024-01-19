package net.mehvahdjukaar.every_compat.modules.iron_age_furniture;

import com.mcmoddev.ironagefurniture.BlockObjectHolder;
import com.mcmoddev.ironagefurniture.api.Blocks.Chair;
import com.mcmoddev.ironagefurniture.api.Blocks.Stool;
import com.mcmoddev.ironagefurniture.api.Blocks.TallStool;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.List;

import static com.mcmoddev.ironagefurniture.Ironagefurniture.IAF_GROUP;

public class IronAgeFurnitureModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> CHAIR_CLASSIC;
    public final SimpleEntrySet<WoodType, Block> CHAIR_SHIELD;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> STOOL_TALL;
    public final SimpleEntrySet<WoodType, Block> BENCH;
    public final SimpleEntrySet<WoodType, Block> BENCH_BACK;
    public final SimpleEntrySet<WoodType, Block> BENCH_LOG;

    public IronAgeFurnitureModule(String modId) {
        super(modId, "iaf");

        CreativeModeTab tab = IAF_GROUP;

        CHAIR_CLASSIC = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_classic",
                () -> BlockObjectHolder.chair_wood_ironage_classic_oak, () -> WoodType.OAK_WOOD_TYPE,
                w -> new Chair(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_classic_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(CHAIR_CLASSIC);

        CHAIR_SHIELD = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_shield",
                () -> getModBlock("chair_wood_ironage_shield_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new Chair(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_shield_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(CHAIR_SHIELD);

        STOOL = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_stool_short",
                () -> getModBlock("chair_wood_ironage_stool_short_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new Stool(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_stool_short_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(STOOL);

        STOOL_TALL = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_stool_tall",
                () -> getModBlock("chair_wood_ironage_stool_tall_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new TallStool(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_stool_tall_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(STOOL_TALL);

        BENCH = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_bench_single",
                () -> getModBlock("chair_wood_ironage_bench_single_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new CompatBench(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_bench_single_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(BENCH);

        BENCH_BACK = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_bench_back_single_",
                () -> getModBlock("chair_wood_ironage_bench_back_single_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new CompatBackBench(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_bench_back_single_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(BENCH_BACK);

        BENCH_LOG = SimpleEntrySet.builder(WoodType.class, "", "chair_wood_ironage_bench_log_single",
                () -> getModBlock("chair_wood_ironage_bench_log_single_oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new CompatLogBench(1, 10, SoundType.WOOD,
                        shortenedId() + "/" + w.getNamespace() + "/chair_wood_ironage_bench_log_single_" + w.getTypeName())
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(BENCH_LOG);

    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "biomesoplenty", "byg", "immersiveengineering"
        );
    }
}
