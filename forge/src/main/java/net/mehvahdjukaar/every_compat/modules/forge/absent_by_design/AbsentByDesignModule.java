package net.mehvahdjukaar.every_compat.modules.forge.absent_by_design;

import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: v1.8.0+
public class AbsentByDesignModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> fence_log;
    public final SimpleEntrySet<WoodType, Block> wall_log;
    public final SimpleEntrySet<WoodType, Block> wall_stripped_log;
    public final SimpleEntrySet<WoodType, Block> wall_planks;

    public AbsentByDesignModule(String modId) {
        super(modId, "abd");
        var tab = modRes("tab");

        fence_log = SimpleEntrySet.builder(WoodType.class, "", "fence_log",
                        getModBlock("fence_log_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentFence(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                //TEXTURE: using the oak_log
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(fence_log);

        wall_log = SimpleEntrySet.builder(WoodType.class, "log", "wall",
                        getModBlock("wall_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                //TEXTURE: using the oak_log
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_log);

        wall_stripped_log = SimpleEntrySet.builder(WoodType.class, "log", "wall_stripped",
                        getModBlock("wall_stripped_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                //TEXTURE: using the stripped_oak_log
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_stripped_log);

        wall_planks = SimpleEntrySet.builder(WoodType.class, "planks", "wall",
                        getModBlock("wall_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.planks).ignitedByLava())
                )
                //TEXTURE: using the oak_planks
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_planks);

    }
}