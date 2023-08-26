package net.mehvahdjukaar.every_compat.modules.dramaticdoors;

import com.fizzware.dramaticdoors.DramaticDoors;
import com.fizzware.dramaticdoors.blocks.ShortDoorBlock;
import com.fizzware.dramaticdoors.blocks.TallDoorBlock;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class DramaticDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> shortDoors;
    public final SimpleEntrySet<WoodType, Block> tallDoors;

    public DramaticDoorsModule(String modId) {
        super(modId, "dd");

        tallDoors = SimpleEntrySet.builder(WoodType.class, "door", "tall",
                        () -> getModBlock("tall_oak_door"), () -> WoodType.OAK_WOOD_TYPE, w -> new TallDoorBlock(Blocks.OAK_DOOR))
                .addTextureM(modRes("block/tall_oak_door_bottom"), WoodGood.res("block/dd/tall_oak_door_bottom_m"))
                .addTextureM(modRes("block/tall_oak_door_middle"), WoodGood.res("block/dd/tall_oak_door_middle_m"))
                .addTextureM(modRes("block/tall_oak_door_top"), WoodGood.res("block/dd/tall_oak_door_top_m"))
                .addTextureM(modRes("item/tall_oak_door"), WoodGood.res("item/dd/tall_oak_door_m"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallDoors);

        shortDoors = SimpleEntrySet.builder(WoodType.class, "door", "short",
                        () -> getModBlock("short_oak_door"), () -> WoodType.OAK_WOOD_TYPE, w -> new ShortDoorBlock(Blocks.OAK_DOOR))
                .addTextureM(modRes("block/short_oak_door"), WoodGood.res("block/dd/short_oak_door_m"))
                .addTextureM(modRes("item/short_oak_door"), WoodGood.res("item/dd/short_oak_door_m"))
                .addTag(modRes("short_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("short_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(shortDoors);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "biomesoplenty", "byg", "prehistoricfauna", "twilightforest", "tflostblocks",
                "bamboo_blocks", "caverns_and_chasms", "endergetic", "environmental", "upgrade_aquatic",
                "abundance", "atmospheric", "autumnity", "bayou_blues", "buzzier_bees",
                "enhanced_mushrooms", "architects_palette", "ars_nouveau", "biomemakeover", "blocksplus",
                "ceilands", "copperoverhaul", "alloyed", "createdeco", "darkerdepths",
                "dustrial_decor", "ecologics", "phantasm", "nourished_end", "habitat",
                "nethers_exoticism", "outer_end", "pokecube", "pokecube_legends", "premium_wood", "quark",
                "snowyspirit", "supplementaries", "twigs", "undergarden"
        );
    }
}
