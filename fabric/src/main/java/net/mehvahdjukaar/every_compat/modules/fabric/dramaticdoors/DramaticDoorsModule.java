package net.mehvahdjukaar.every_compat.modules.fabric.dramaticdoors;

import com.fizzware.dramaticdoors.fabric.DDRegistry;
import com.fizzware.dramaticdoors.fabric.blocks.ShortDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallDoorBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.List;

// SUPPORT: v3.1.2+
public class DramaticDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> shortDoors;
    public final SimpleEntrySet<WoodType, Block> tallDoors;

    public DramaticDoorsModule(String modId) {
        super(modId, "dd");

        tallDoors = SimpleEntrySet.builder(WoodType.class, "door", "tall",
                        () -> getModBlock("tall_oak_door"), () -> WoodTypeRegistry.OAK_TYPE, w -> new TallDoorBlock(Blocks.OAK_DOOR))
                .addTextureM(modRes("block/tall_oak_door_bottom"), EveryCompat.res("block/dd/tall_oak_door_bottom_m"))
                .addTextureM(modRes("block/tall_oak_door_middle"), EveryCompat.res("block/dd/tall_oak_door_middle_m"))
                .addTextureM(modRes("block/tall_oak_door_top"), EveryCompat.res("block/dd/tall_oak_door_top_m"))
                .addTextureM(modRes("item/tall_oak_door"), EveryCompat.res("item/dd/tall_oak_door_m"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .defaultRecipe()
                .build();

        this.addEntry(tallDoors);

        shortDoors = SimpleEntrySet.builder(WoodType.class, "door", "short",
                        () -> getModBlock("short_oak_door"), () -> WoodTypeRegistry.OAK_TYPE, w -> new ShortDoorBlock(Blocks.OAK_DOOR))
                .addTextureM(modRes("block/short_oak_door"), EveryCompat.res("block/dd/short_oak_door_m"))
                .addTextureM(modRes("item/short_oak_door"), EveryCompat.res("item/dd/short_oak_door_m"))
                .addTag(modRes("short_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("short_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .defaultRecipe()
                .build();

        this.addEntry(shortDoors);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
            "abundance", "abundant_atmosphere", "ad_astra",
            "aether", "alloyed", "architects_palette",
            "ars_nouveau", "atmospheric", "autumnity",
            "bamboo_blocks", "bayou_blues", "biomemakeover",
            "biomesoplenty", "blocksplus", "buzzier_bees",
            "byg", "caverns_and_chasms", "ceilands",
            "copperoverhaul", "createdeco", "darkerdepths",
            "dustrial_decor", "ecologics", "endergetic",
            "enhanced_mushrooms", "environmental", "habitat",
            "hexcasting", "nethers_exoticism", "nourished_end",
            "outer_end", "phantasm", "pokecube",
            "pokecube_legends", "prehistoricfauna", "premium_wood",
            "quark", "snowyspirit", "supplementaries",
            "tflostblocks", "twigs", "twilightforest",
            "undergarden", "upgrade_aquatic", "windswept",
            "vinery", "aurorasdeco", "create"
        );
    }
}
