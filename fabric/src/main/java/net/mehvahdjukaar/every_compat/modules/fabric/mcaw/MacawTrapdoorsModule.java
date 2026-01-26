package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwtrpdoors.MacawsTrapdoors;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v1.1.4+
public class MacawTrapdoorsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> BARK_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARRED_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BEACH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> COTTAGE_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> MESH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> RANCH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> SWAMP_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> TROPICAL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> WAFFLE_TRAPDOORS; // BLOSSOM
    public final SimpleEntrySet<WoodType, Block> BARREL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> WHISPERING_TRAPDOORS;

    public MacawTrapdoorsModule(String modId) {
        super(modId, "mct");
        ResourceKey<CreativeModeTab> tab = MacawsTrapdoors.TRAPDOORSGROUP;

        BARK_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "bark_trapdoor",
                        getModBlock("oak_bark_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("bark_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(BARK_TRAPDOORS);

        BARN_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barn_trapdoor",
                        getModBlock("oak_barn_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks)){}
                )
                .addTexture(modRes("block/barn/oak_barn_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("barn_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(BARN_TRAPDOORS);

        BARRED_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barred_trapdoor",
                        getModBlock("oak_barred_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTexture(modRes("block/barred/oak_barred_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("barred_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(BARRED_TRAPDOORS);

        BEACH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "beach_trapdoor",
                        getModBlock("oak_beach_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTextureM(modRes("block/beach/oak_beach_trapdoor"), EveryCompat.res("block/mcw/trapdoors/oak_beach_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("beach_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(BEACH_TRAPDOORS);

        CLASSIC_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "classic_trapdoor",
                        getModBlock("spruce_classic_trapdoor"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTexture(modRes("block/classic/spruce_classic_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("classic_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(CLASSIC_TRAPDOORS);

        COTTAGE_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "cottage_trapdoor",
                        getModBlock("oak_cottage_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks)) {}
                )
                .addTextureM(modRes("block/cottage/oak_cottage_trapdoor"),
                        EveryCompat.res("block/mcw/trapdoors/oak_cottage_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("cottage_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(COTTAGE_TRAPDOORS);

        FOUR_PANEL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "four_panel_trapdoor",
                        getModBlock("oak_four_panel_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks)) {}
                )
                .addTexture(modRes("block/four_panel/oak_four_panel_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("four_panel_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(FOUR_PANEL_TRAPDOORS);

        GLASS_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "glass_trapdoor",
                        getModBlock("oak_glass_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTextureM(modRes("block/glass/oak_glass_trapdoor"),
                        EveryCompat.res("block/mcw/trapdoors/oak_glass_trapdoor_m"),
                        PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("glass_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(GLASS_TRAPDOORS);

        MESH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "bamboo_trapdoor",
                        getModBlock("oak_bamboo_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTexture(modRes("block/bamboo/oak_bamboo_trapdoor"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("bamboo_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(MESH_TRAPDOORS);

        MYSTIC_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "mystic_trapdoor",
                        getModBlock("oak_mystic_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("mystic_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/mystic/oak_mystic_trapdoor"))
                .build();
        this.addEntry(MYSTIC_TRAPDOORS);

        PAPER_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "paper_trapdoor",
                        getModBlock("oak_paper_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks)) {}
                )
                .addTextureM(modRes("block/paper/oak_paper_trapdoor"),
                        EveryCompat.res("block/mcw/trapdoors/oak_paper_trapdoor_m"),
                        PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("paper_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(PAPER_TRAPDOORS);

        RANCH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "ranch_trapdoor",
                        getModBlock("oak_ranch_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("ranch_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(RANCH_TRAPDOORS);

        SWAMP_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "swamp_trapdoor",
                        getModBlock("oak_swamp_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTexture(modRes("block/swamp/oak_swamp_trapdoor"), PaletteStrategies.PLANKS_REMOVE_2_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("swamp_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(SWAMP_TRAPDOORS);

        TROPICAL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "tropical_trapdoor",
                        getModBlock("oak_tropical_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTextureM(modRes("block/tropical/oak_tropical_trapdoor"),
                        EveryCompat.res("block/mcw/trapdoors/oak_tropical_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("tropical_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(TROPICAL_TRAPDOORS);

        WAFFLE_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "blossom_trapdoor",
                        getModBlock("oak_blossom_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTexture(modRes("block/blossom/oak_blossom_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("blossom_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(WAFFLE_TRAPDOORS);

        BARREL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barrel_trapdoor",
                        getModBlock("oak_barrel_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("barrel_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/barrel/oak_barrel_trapdoor"), EveryCompat.res("block/mcw/trapdoors/oak_barrel_trapdoor_m"))
                .build();
        this.addEntry(BARREL_TRAPDOORS);

        WHISPERING_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "whispering_trapdoor",
                        getModBlock("oak_whispering_trapdoor"), () -> VanillaWoodTypes.OAK,
                        w -> new TrapDoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.planks).noOcclusion()){}
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(modRes("whispering_trapdoors"), Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/whispering/oak_whispering_trapdoor"), EveryCompat.res("block/mcw/trapdoors/oak_whispering_trapdoor_m"))
                .build();
        this.addEntry(WHISPERING_TRAPDOORS);

    }
}
