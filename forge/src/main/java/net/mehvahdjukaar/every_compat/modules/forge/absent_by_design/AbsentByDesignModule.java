package net.mehvahdjukaar.every_compat.modules.forge.absent_by_design;

import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

//SUPPORT: v1.7.0-FINAL
public class AbsentByDesignModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> fence_log;
    public final SimpleEntrySet<WoodType, Block> wall_log;
    public final SimpleEntrySet<WoodType, Block> wall_stripped_log;
    public final SimpleEntrySet<WoodType, Block> wall_planks;

    public AbsentByDesignModule(String modId) {
        super(modId, "abd");

        fence_log = SimpleEntrySet.builder(WoodType.class, "", "fence_log",
                        () -> getModBlock("fence_log_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentFence(BlockBehaviour.Properties.of(Material.WOOD))
                )
                .addTag(BlockTags.FENCES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.FENCES, Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(fence_log);

        wall_log = SimpleEntrySet.builder(WoodType.class, "log", "wall",
                        () -> getModBlock("wall_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(BlockBehaviour.Properties.of(Material.WOOD))
                )
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WALLS, Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(wall_log);

        wall_stripped_log = SimpleEntrySet.builder(WoodType.class, "log", "wall_stripped",
                        () -> getModBlock("wall_stripped_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(BlockBehaviour.Properties.of(Material.WOOD))
                )
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WALLS, Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(wall_stripped_log);

        wall_planks = SimpleEntrySet.builder(WoodType.class, "planks", "wall",
                        () -> getModBlock("wall_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(BlockBehaviour.Properties.of(Material.WOOD))
                )
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WALLS, Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(wall_planks);

    }
}