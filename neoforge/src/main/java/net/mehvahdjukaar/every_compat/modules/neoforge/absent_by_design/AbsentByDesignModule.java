package net.mehvahdjukaar.every_compat.modules.neoforge.absent_by_design;

import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_WOOD;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.WOOD;

//SUPPORT: v1.8.0+
public class AbsentByDesignModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> fence_log;
    public final SimpleEntrySet<WoodType, Block> wall_log;
    public final SimpleEntrySet<WoodType, Block> wall_stripped_log;
    public final SimpleEntrySet<WoodType, Block> wall_planks;

    public AbsentByDesignModule(String modId) {
        super(modId, "abd");
        ResourceLocation tab = modRes("tab");

        fence_log = SimpleEntrySet.builder(WoodType.class, "", "fence_log",
                        getModBlock("fence_log_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new BlockAbsentFence(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                //TEXTURES: oak_log
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(fence_log);

        wall_log = SimpleEntrySet.builder(WoodType.class, "log", "wall",
                        getModBlock("wall_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                .requiresChildren(WOOD) //REASON: recipes
                //TEXTURES: oak_log
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("wall_oak_log_sc"))
                .build();
        this.addEntry(wall_log);

        wall_stripped_log = SimpleEntrySet.builder(WoodType.class, "log", "wall_stripped",
                        getModBlock("wall_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.log).ignitedByLava())
                )
                .requiresChildren(STRIPPED_WOOD) //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("wall_stripped_oak_log_sc"))
                .build();
        this.addEntry(wall_stripped_log);

        wall_planks = SimpleEntrySet.builder(WoodType.class, "planks", "wall",
                        getModBlock("wall_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new BlockAbsentWall(Utils.copyPropertySafe(w.planks).ignitedByLava())
                )
                //TEXTURES: planks
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("wall_oak_planks_sc"))
                .build();
        this.addEntry(wall_planks);

    }
}