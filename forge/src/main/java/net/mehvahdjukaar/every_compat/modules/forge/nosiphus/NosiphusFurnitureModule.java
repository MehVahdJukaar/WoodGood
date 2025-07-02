package net.mehvahdjukaar.every_compat.modules.forge.nosiphus;

import com.nosiphus.furniture.block.ChoppingBoardBlock;
import com.nosiphus.furniture.block.DoorBellBlock;
import com.nosiphus.furniture.block.WaterTankBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Objects;

//SUPPORT: v2025.02.07+
public class NosiphusFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chopping_board;
    public final SimpleEntrySet<WoodType, Block> door_bell;
    public final SimpleEntrySet<WoodType, Block> stripped_door_bell;
    public final SimpleEntrySet<WoodType, Block> water_tank;

    public NosiphusFurnitureModule(String modId) {
        super(modId, "nfm");
        ResourceLocation tab = modRes("creative_tab");

        chopping_board = SimpleEntrySet.builder(WoodType.class, "chopping_board",
                        getModBlock("oak_chopping_board"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChoppingBoardBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTile(getModTile("chopping_board"))
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chopping_board);

        door_bell = SimpleEntrySet.builder(WoodType.class, "door_bell",
                        getModBlock("oak_door_bell"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBellBlock(Utils.copyPropertySafe(w.log).noOcclusion())
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(door_bell);

        stripped_door_bell = SimpleEntrySet.builder(WoodType.class, "door_bell", "stripped",
                        getModBlock("stripped_oak_door_bell"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBellBlock(Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis("stripped_log"))).noOcclusion())
                )
                .requiresChildren("stripped_log") //REASON: textures, recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_door_bell);

        water_tank = SimpleEntrySet.builder(WoodType.class, "water_tank",
                        getModBlock("oak_water_tank"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WaterTankBlock(Utils.copyPropertySafe(Blocks.CAULDRON).noOcclusion())
                )
                .addTile(getModTile("water_tank"))
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(water_tank);


    }
}