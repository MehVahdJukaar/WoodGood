package net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors;

import com.fizzware.dramaticdoors.fabric.blocks.ShortDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.fabric.registry.DDCreativeTabs;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Objects;

//SUPPORT: v3.3.2+
//NOTE: The library of FABRIC and FORGE are not the same, must be in separated folders
public class DramaticDoorsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> shortDoors;
    public final SimpleEntrySet<WoodType, Block> tallDoors;

    public DramaticDoorsModule(String modId) {
        super(modId, "dd");
        ResourceKey<CreativeModeTab> tab = DDCreativeTabs.MAIN_TAB;

        tallDoors = SimpleEntrySet.builder(WoodType.class, "door", "tall",
                        getModBlock("tall_oak_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), getDoorSafe(w))
                )
                .addTextureM(modRes("block/tall_oak_door_bottom"), EveryCompat.res("block/dd/tall_oak_door_bottom_m"))
                .addTextureM(modRes("block/tall_oak_door_middle"), EveryCompat.res("block/dd/tall_oak_door_middle_m"))
                .addTextureM(modRes("block/tall_oak_door_top"), EveryCompat.res("block/dd/tall_oak_door_top_m"))
                .addTextureM(modRes("item/tall_oak_door"), EveryCompat.res("item/dd/tall_oak_door_m"))
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTab(getTab(tab))
                .copyParentDrop()
                .defaultRecipe()
                .build();
        this.addEntry(tallDoors);

        shortDoors = SimpleEntrySet.builder(WoodType.class, "door", "short",
                        getModBlock("short_oak_door"), () -> VanillaWoodTypes.OAK,
                        w -> new ShortDoorBlock(w.toVanillaOrOak().setType(), getDoorSafe(w))
                )
                .addTextureM(modRes("block/short_oak_door"), EveryCompat.res("block/dd/short_oak_door_m"))
                .addTextureM(modRes("item/short_oak_door"), EveryCompat.res("item/dd/short_oak_door_m"))
                .addTag(modRes("short_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTab(getTab(tab))
                .copyParentDrop()
                .defaultRecipe()
                .build();
        this.addEntry(shortDoors);
    }

    public Block getDoorSafe(WoodType woodType) {
        Block door = woodType.getBlockOfThis("door");
        return (door != null) ? Objects.requireNonNull(door) : Blocks.OAK_DOOR;
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "abundant_atmosphere", "ad_astra", "aether", "aether_redux",
                "alexscaves", "alloyed", "architects_palette", "atum",
                "aurorasdeco", "automaticdoors", "bambooeverything", "betterarcheology",
                "betterend", "betternether", "bewitchment", "biomancy",
                "biomemakeover", "biomesoplenty", "blocksplus", "blockus",
                "biomeswevegone", "caupona", "ceilands", "charm",
                "chipped", "cinderscapes", "cobblemon", "colorfulazaleas",
                "copperoverhaul", "couplings", "create_things_and_misc", "createdeco",
                "darkerdepths", "deep_aether", "deeperdarker", "desolation",
                "doubledoors", "dustrial_decor", "ecologics", "enderscape",
                "endlessbiomes", "enhanced_mushrooms", "enlightened_end", "everythingcopper",
                "extendedmushrooms", "forbidden_arcanus", "fruittrees", "gardens_of_the_dead",
                "goodending", "graveyard", "hexcasting", "hexerei",
                "horizons", "integrateddynamics", "malum", "manyideas_doors",
                "mcwdoors", "modern_glass_doors", "morecraft", "ms",
                "mysticsbiomes", "nethers_exoticism", "newworld", "phantasm",
                "pokecube", "prehistoricfauna", "premium_wood", "promenade",
                "pyromancer", "quark", "regions_unexplored", "silentgear",
                "snowyspirit", "statement", "supplementaries", "tconstruct",
                "techreborn", "terraqueous", "terrestria", "traverse",
                "twilightforest", "undergarden", "vinery", "wilderwild",
                "windswept", "woodworks", "xps_additions", "yippee"
        );
    }
}
