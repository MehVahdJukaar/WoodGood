package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;

//SUPPORT: v1.1.2+
public abstract class MacawDoorsModuleAbstract extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> WaffleDoors,
            BarkGlassDoors,
            BarnDoors,
            BarnGlassDoors,
            BeachDoors,
            ClassicDoors,
            CottageDoors,
            FourPanelDoors,
            GlassDoors,
            MeshDoors,
            ModernDoors,
            MysticDoors,
            NetherDoors,
            PaperDoors,
            ShojiDoors,
            ShojiWholeDoors,
            StableDoors,
            StableHeadDoors,
            SwampDoors,
            TropicalDoors,
            WesternDoors,
            WhisperingDoors;

    public MacawDoorsModuleAbstract(String modId) {
        super(modId, "mcd");
        ResourceLocation tab = modRes(modId);

        WaffleDoors = SimpleEntrySet.builder(WoodType.class, "waffle_door",
                        getModBlock("oak_waffle_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_waffle_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_waffle_door_lower_m"))
                .addTextureM(modRes("block/oak_waffle_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_waffle_door_upper_m"))
                .addTextureM(modRes("item/oak_waffle_door"),
                        EveryCompat.res("item/mcw/doors/oak_waffle_door_m"))
                .build();
        this.addEntry(WaffleDoors);

        BarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door",
                        getModBlock("oak_bark_glass_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("item/oak_bark_glass_door"),
                        EveryCompat.res("item/mcw/doors/oak_bark_glass_door_m"))
                .build();
        this.addEntry(BarkGlassDoors);

        BarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door",
                        getModBlock("oak_barn_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_barn_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_door"),
                        EveryCompat.res("item/mcw/doors/oak_barn_door_m"))
                .build();
        this.addEntry(BarnDoors);

        BarnGlassDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door",
                        getModBlock("oak_barn_glass_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_glass_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_glass_door"),
                        EveryCompat.res("item/mcw/doors/oak_barn_glass_door_m"))
                .build();
        this.addEntry(BarnGlassDoors);

        BeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door",
                        getModBlock("oak_beach_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_beach_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_beach_door_lower_m"))
                .addTextureM(modRes("block/oak_beach_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_beach_door_upper_m"))
                .addTextureM(modRes("item/oak_beach_door"),
                        EveryCompat.res("item/mcw/doors/oak_beach_door_m"))
                .build();
        this.addEntry(BeachDoors);

        ClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door",
                        getModBlock("spruce_classic_door"), () -> VanillaWoodTypes.SPRUCE,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/spruce_classic_door_lower"),
                        EveryCompat.res("block/mcw/doors/spruce_classic_door_lower_m"))
                .addTextureM(modRes("block/spruce_classic_door_upper"),
                        EveryCompat.res("block/mcw/doors/spruce_classic_door_upper_m"))
                .addTextureM(modRes("item/spruce_classic_door"),
                        EveryCompat.res("item/mcw/doors/spruce_classic_door_m"))
                .build();
        this.addEntry(ClassicDoors);

        CottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door",
                        getModBlock("oak_cottage_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_cottage_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/oak_cottage_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/oak_cottage_door"),
                        EveryCompat.res("item/mcw/doors/oak_cottage_door_m"))
                .build();
        this.addEntry(CottageDoors);

        FourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door",
                        getModBlock("oak_four_panel_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_four_panel_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/oak_four_panel_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/oak_four_panel_door"),
                        EveryCompat.res("item/mcw/doors/oak_four_panel_door_m"))
                .build();
        this.addEntry(FourPanelDoors);

        GlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door",
                        getModBlock("oak_glass_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/glass/oak_glass_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_glass_door_lower_m"))
                .addTextureM(modRes("block/glass/oak_glass_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_glass_door"),
                        EveryCompat.res("item/mcw/doors/oak_glass_door_m"))
                .build();
        this.addEntry(GlassDoors);

        MeshDoors = SimpleEntrySet.builder(WoodType.class, "bamboo_door",
                        getModBlock("oak_bamboo_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTabKey(tab)
                .addTextureM(modRes("block/oak_bamboo_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_bamboo_door_lower_m"),
                        PLANKS_DARKER_PALETTE)
                .addTextureM(modRes("block/oak_bamboo_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_bamboo_door_upper_m"),
                        PLANKS_DARKER_PALETTE)
                .addTextureM(modRes("item/oak_bamboo_door"),
                        EveryCompat.res("item/mcw/doors/oak_bamboo_door_m"),
                        PLANKS_DARKER_PALETTE)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(MeshDoors);

        ModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door",
                        getModBlock("oak_modern_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_modern_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_modern_door_lower_m"))
                .addTextureM(modRes("block/oak_modern_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_modern_door_upper_m"))
                .addTextureM(modRes("item/oak_modern_door"),
                        EveryCompat.res("item/mcw/doors/oak_modern_door_m"))
                .build();
        this.addEntry(ModernDoors);

        MysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door",
                        getModBlock("oak_mystic_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_mystic_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/oak_mystic_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/oak_mystic_door"),
                        EveryCompat.res("item/mcw/doors/oak_mystic_door_m"))
                .build();
        this.addEntry(MysticDoors);

        NetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door",
                        getModBlock("oak_nether_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_nether_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_nether_door_lower_m"))
                .addTextureM(modRes("block/oak_nether_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_nether_door_upper_m"))
                .addTextureM(modRes("item/oak_nether_door"),
                        EveryCompat.res("item/mcw/doors/oak_nether_door_m"))
                .build();
        this.addEntry(NetherDoors);

        PaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door",
                        getModBlock("oak_paper_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_paper_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_paper_door_lower_m"))
                .addTextureM(modRes("block/oak_paper_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_paper_door_upper_m"))
                .addTextureM(modRes("item/oak_paper_door"),
                        EveryCompat.res("item/mcw/doors/oak_paper_door_m"))
                .build();
        this.addEntry(PaperDoors);

        ShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door",
                        getModBlock("oak_japanese_door"), () -> VanillaWoodTypes.OAK,
                        this::newJapaneseDoors
                        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/japanese_oak_lower"),
                        EveryCompat.res("block/mcw/doors/japanese_oak_lower_m"))
                .addTextureM(modRes("block/japanese_oak_upper"),
                        EveryCompat.res("block/mcw/doors/japanese_oak_upper_m"))
                .addTextureM(modRes("item/oak_japanese_door"),
                        EveryCompat.res("item/mcw/doors/japanese_oak_door_m"))
                .build();
        this.addEntry(ShojiDoors);

        ShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door",
                        getModBlock("oak_japanese2_door"), () -> VanillaWoodTypes.OAK,
                        this::newJapaneseDoors
                        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_japanese_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/oak_japanese_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/oak_japanese2_door"),
                        EveryCompat.res("item/mcw/doors/oak_japanese_door_m"))
                .build();
        this.addEntry(ShojiWholeDoors);

        StableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door",
                        getModBlock("oak_stable_door"), () -> VanillaWoodTypes.OAK,
                        this::newStableDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_stable_door_upper_m"))
                .addTextureM(modRes("item/oak_stable_door"),
                        EveryCompat.res("item/mcw/doors/oak_stable_door_m"))
                .build();
        this.addEntry(StableDoors);

        StableHeadDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door",
                        getModBlock("oak_stable_head_door"), () -> VanillaWoodTypes.OAK,
                        this::newStableDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_stable_head_door"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable_head/oak_stable_head_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_stable_head_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("item/oak_stable_head_door"),
                        EveryCompat.res("item/mcw/doors/oak_stable_head_door_m"))
                .build();
        this.addEntry(StableHeadDoors);

        SwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door",
                        getModBlock("oak_swamp_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_swamp_door"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_swamp_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_swamp_door_lower_m"),
                        PLANKS_DARKER_PALETTE)
                .addTextureM(modRes("block/oak_swamp_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_swamp_door_upper_m"),
                        PLANKS_DARKER_PALETTE)
                .addTextureM(modRes("item/oak_swamp_door"),
                        EveryCompat.res("item/mcw/doors/oak_swamp_door_m"),
                        PLANKS_DARKER_PALETTE)
                .build();
        this.addEntry(SwampDoors);

        TropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door",
                        getModBlock("oak_tropical_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_tropical_door"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_tropical_door_lower"), EveryCompat.res("block/mcw/doors/oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/oak_tropical_door_upper"), EveryCompat.res("block/mcw/doors/oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/oak_tropical_door"), EveryCompat.res("item/mcw/doors/oak_tropical_door_m"))
                .build();
        this.addEntry(TropicalDoors);

        WesternDoors = SimpleEntrySet.builder(WoodType.class, "western_door",
                        getModBlock("oak_western_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_western_door"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/western/oak_western_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_western_door_lower_m"))
                .addTextureM(modRes("block/western/oak_western_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_western_door_upper_m"))
                .addTextureM(modRes("item/oak_western_door"),
                        EveryCompat.res("item/mcw/doors/oak_western_door_m"))
                .build();
        this.addEntry(WesternDoors);

        WhisperingDoors = SimpleEntrySet.builder(WoodType.class, "whispering_door",
                        getModBlock("oak_whispering_door"), () -> VanillaWoodTypes.OAK,
                        this::newDoor
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_whispering_door_lower"),
                        EveryCompat.res("block/mcw/doors/oak_whispering_door_lower_m"))
                .addTextureM(modRes("block/oak_whispering_door_upper"),
                        EveryCompat.res("block/mcw/doors/oak_whispering_door_upper_m"))
                .addTextureM(modRes("item/oak_whispering_door"),
                        EveryCompat.res("item/mcw/doors/oak_whispering_door_m"))
                .build();
        this.addEntry(WhisperingDoors);
    }

    public static final PaletteStrategy PLANKS_DARKER_PALETTE = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, VanillaWoodChildKeys.PLANKS, null,
                    p -> {
                        p.add(p.increaseInner());
                        p.remove(p.getDarkest());
                        p.remove(p.getLightest());
                    }));

    protected abstract Block newDoor(WoodType woodType);
    protected abstract Block newJapaneseDoors(WoodType woodType);
    protected abstract Block newStableDoor(WoodType woodType);

}
