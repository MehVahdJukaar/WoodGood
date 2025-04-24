package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwdoors.init.BlockInit;
import net.kikoz.mcwdoors.objects.JapaneseDoors;
import net.kikoz.mcwdoors.objects.StableDoor;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: v1.1.1+
public class MacawDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> WaffleDoors;
    public final SimpleEntrySet<WoodType, Block> BarkGlassDoors;
    public final SimpleEntrySet<WoodType, Block> BarnDoors;
    public final SimpleEntrySet<WoodType, Block> BarnGlassDoors;
    public final SimpleEntrySet<WoodType, Block> BeachDoors;
    public final SimpleEntrySet<WoodType, Block> ClassicDoors;
    public final SimpleEntrySet<WoodType, Block> CottageDoors;
    public final SimpleEntrySet<WoodType, Block> FourPanelDoors;
    public final SimpleEntrySet<WoodType, Block> GlassDoors;
    public final SimpleEntrySet<WoodType, Block> MeshDoors;
    public final SimpleEntrySet<WoodType, Block> ModernDoors;
    public final SimpleEntrySet<WoodType, Block> MysticDoors;
    public final SimpleEntrySet<WoodType, Block> NetherDoors;
    public final SimpleEntrySet<WoodType, Block> PaperDoors;
    public final SimpleEntrySet<WoodType, Block> ShojiDoors;
    public final SimpleEntrySet<WoodType, Block> ShojiWholeDoors;
    public final SimpleEntrySet<WoodType, Block> StableDoors;
    public final SimpleEntrySet<WoodType, Block> StableHeadDoors;
    public final SimpleEntrySet<WoodType, Block> SwampDoors;
    public final SimpleEntrySet<WoodType, Block> TropicalDoors;
    public final SimpleEntrySet<WoodType, Block> WesternDoors;

    public MacawDoorsModule(String modId) {
        super(modId, "mcd");
        var tab = modRes("inv");

        WaffleDoors = SimpleEntrySet.builder(WoodType.class, "waffle_door",
                        () -> BlockInit.OAK_WAFFLE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_waffle_door_lower"), EveryCompat.res("block/mcw/doors/oak_waffle_door_lower_m"))
                .addTextureM(modRes("block/oak_waffle_door_upper"), EveryCompat.res("block/mcw/doors/oak_waffle_door_upper_m"))
                .addTextureM(modRes("item/oak_waffle_door"), EveryCompat.res("item/mcw/doors/oak_waffle_door_m"))
                .build();
        this.addEntry(WaffleDoors);

        BarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door",
                        () -> BlockInit.OAK_BARK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("item/oak_bark_glass_door"), EveryCompat.res("item/mcw/doors/oak_bark_glass_door_m"))
                .build();
        this.addEntry(BarkGlassDoors);

        BarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door",
                        () -> BlockInit.OAK_BARN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_door_upper"), EveryCompat.res("block/mcw/doors/oak_barn_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_door"), EveryCompat.res("item/mcw/doors/oak_barn_door_m"))
                .build();
        this.addEntry(BarnDoors);

        BarnGlassDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door",
                        () -> BlockInit.OAK_BARN_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_glass_door_upper"), EveryCompat.res("block/mcw/doors/oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_glass_door"), EveryCompat.res("item/mcw/doors/oak_barn_glass_door_m"))
                .build();
        this.addEntry(BarnGlassDoors);

        BeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door",
                        () -> BlockInit.OAK_BEACH_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_beach_door_lower"), EveryCompat.res("block/mcw/doors/oak_beach_door_lower_m"))
                .addTextureM(modRes("block/oak_beach_door_upper"), EveryCompat.res("block/mcw/doors/oak_beach_door_upper_m"))
                .addTextureM(modRes("item/oak_beach_door"), EveryCompat.res("item/mcw/doors/oak_beach_door_m"))
                .build();
        this.addEntry(BeachDoors);

        ClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door",
                        () -> BlockInit.SPRUCE_CLASSIC_DOOR, () -> WoodTypeRegistry.getValue(ResourceLocation.parse("spruce")),
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/spruce_classic_door_lower"), EveryCompat.res("block/mcw/doors/spruce_classic_door_lower_m"))
                .addTextureM(modRes("block/spruce_classic_door_upper"), EveryCompat.res("block/mcw/doors/spruce_classic_door_upper_m"))
                .addTextureM(modRes("item/spruce_classic_door"), EveryCompat.res("item/mcw/doors/spruce_classic_door_m"))
                .build();
        this.addEntry(ClassicDoors);

        CottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door",
                        () -> BlockInit.OAK_COTTAGE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_cottage_door_lower"), EveryCompat.res("block/mcw/doors/oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/oak_cottage_door_upper"), EveryCompat.res("block/mcw/doors/oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/oak_cottage_door"), EveryCompat.res("item/mcw/doors/oak_cottage_door_m"))
                .build();
        this.addEntry(CottageDoors);

        FourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door",
                        () -> BlockInit.OAK_FOUR_PANEL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_four_panel_door_lower"), EveryCompat.res("block/mcw/doors/oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/oak_four_panel_door_upper"), EveryCompat.res("block/mcw/doors/oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/oak_four_panel_door"), EveryCompat.res("item/mcw/doors/oak_four_panel_door_m"))
                .build();
        this.addEntry(FourPanelDoors);

        GlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door",
                        () -> BlockInit.OAK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/glass/oak_glass_door_lower"), EveryCompat.res("block/mcw/doors/oak_glass_door_lower_m"))
                .addTextureM(modRes("block/glass/oak_glass_door_upper"), EveryCompat.res("block/mcw/doors/oak_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_glass_door"), EveryCompat.res("item/mcw/doors/oak_glass_door_m"))
                .build();
        this.addEntry(GlassDoors);

        MeshDoors = SimpleEntrySet.builder(WoodType.class, "bamboo_door",
                        () -> BlockInit.OAK_BAMBOO_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTabKey(tab)
                .addTextureM(modRes("block/oak_bamboo_door_lower"), EveryCompat.res("block/mcw/doors/oak_bamboo_door_lower_m"))
                .addTextureM(modRes("block/oak_bamboo_door_upper"), EveryCompat.res("block/mcw/doors/oak_bamboo_door_upper_m"))
                .addTextureM(modRes("item/oak_bamboo_door"), EveryCompat.res("item/mcw/doors/oak_bamboo_door_m"))
                .createPaletteFromPlanks(this::darkerPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(MeshDoors);

        ModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door",
                        () -> BlockInit.OAK_MODERN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_modern_door_lower"), EveryCompat.res("block/mcw/doors/oak_modern_door_lower_m"))
                .addTextureM(modRes("block/oak_modern_door_upper"), EveryCompat.res("block/mcw/doors/oak_modern_door_upper_m"))
                .addTextureM(modRes("item/oak_modern_door"), EveryCompat.res("item/mcw/doors/oak_modern_door_m"))
                .build();
        this.addEntry(ModernDoors);

        MysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door",
                        () -> BlockInit.OAK_MYSTIC_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_mystic_door_lower"), EveryCompat.res("block/mcw/doors/oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/oak_mystic_door_upper"), EveryCompat.res("block/mcw/doors/oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/oak_mystic_door"), EveryCompat.res("item/mcw/doors/oak_mystic_door_m"))
                .build();
        this.addEntry(MysticDoors);

        NetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door",
                        () -> BlockInit.OAK_NETHER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_nether_door_lower"), EveryCompat.res("block/mcw/doors/oak_nether_door_lower_m"))
                .addTextureM(modRes("block/oak_nether_door_upper"), EveryCompat.res("block/mcw/doors/oak_nether_door_upper_m"))
                .addTextureM(modRes("item/oak_nether_door"), EveryCompat.res("item/mcw/doors/oak_nether_door_m"))
                .build();
        this.addEntry(NetherDoors);

        PaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door",
                        () -> BlockInit.OAK_PAPER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/oak_paper_door_lower"), EveryCompat.res("block/mcw/doors/oak_paper_door_lower_m"))
                .addTextureM(modRes("block/oak_paper_door_upper"), EveryCompat.res("block/mcw/doors/oak_paper_door_upper_m"))
                .addTextureM(modRes("item/oak_paper_door"), EveryCompat.res("item/mcw/doors/oak_paper_door_m"))
                .build();
        this.addEntry(PaperDoors);

        ShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door",
                        () -> BlockInit.OAK_JAPANESE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new JapaneseDoors(Utils.copyPropertySafe(w.planks).noOcclusion().sound(SoundType.SCAFFOLDING),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/japanese_oak_lower"), EveryCompat.res("block/mcw/doors/japanese_oak_lower_m"))
                .addTextureM(modRes("block/japanese_oak_upper"), EveryCompat.res("block/mcw/doors/japanese_oak_upper_m"))
                .addTextureM(modRes("item/oak_japanese_door"), EveryCompat.res("item/mcw/doors/japanese_oak_door_m"))
                .build();
        this.addEntry(ShojiDoors);

        ShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door",
                        () -> BlockInit.OAK_JAPANESE2_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new JapaneseDoors(Utils.copyPropertySafe(w.planks).noOcclusion().sound(SoundType.SCAFFOLDING),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_japanese_door_lower"), EveryCompat.res("block/mcw/doors/oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/oak_japanese_door_upper"), EveryCompat.res("block/mcw/doors/oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/oak_japanese2_door"), EveryCompat.res("item/mcw/doors/oak_japanese_door_m"))
                .build();
        this.addEntry(ShojiWholeDoors);

        StableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door",
                        () -> BlockInit.OAK_STABLE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StableDoor(Utils.copyPropertySafe(w.planks).noOcclusion(),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), EveryCompat.res("block/mcw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_upper"), EveryCompat.res("block/mcw/doors/oak_stable_door_upper_m"))
                .addTextureM(modRes("item/oak_stable_door"), EveryCompat.res("item/mcw/doors/oak_stable_door_m"))
                .build();
        this.addEntry(StableDoors);

        StableHeadDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door",
                        () -> BlockInit.OAK_STABLE_HEAD_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StableDoor(Utils.copyPropertySafe(w.planks).noOcclusion(),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_stable_head_door"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable_head/oak_stable_head_door_lower"), EveryCompat.res("block/mcw/doors/oak_stable_head_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), EveryCompat.res("block/mcw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("item/oak_stable_head_door"), EveryCompat.res("item/mcw/doors/oak_stable_head_door_m"))
                .build();
        this.addEntry(StableHeadDoors);

        SwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door",
                        () -> BlockInit.OAK_SWAMP_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .createPaletteFromPlanks(this::darkerPalette)
                .addTextureM(EveryCompat.res("block/oak_swamp_door_lower"), EveryCompat.res("block/mcw/doors/oak_swamp_door_lower_m"))
                .addTextureM(EveryCompat.res("block/oak_swamp_door_upper"), EveryCompat.res("block/mcw/doors/oak_swamp_door_upper_m"))
                .addTextureM(modRes("item/oak_swamp_door"), EveryCompat.res("item/mcw/doors/oak_swamp_door_m"))
                .build();
        this.addEntry(SwampDoors);

        TropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door",
                        () -> BlockInit.OAK_TROPICAL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                        () -> BlockInit.OAK_WESTERN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(w.toVanillaOrOak().setType(), Utils.copyPropertySafe(w.log).noOcclusion()){}
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
                .addTextureM(modRes("block/western/oak_western_door_lower"), EveryCompat.res("block/mcw/doors/oak_western_door_lower_m"))
                .addTextureM(modRes("block/western/oak_western_door_upper"), EveryCompat.res("block/mcw/doors/oak_western_door_upper_m"))
                .addTextureM(modRes("item/oak_western_door"), EveryCompat.res("item/mcw/doors/oak_western_door_m"))
                .build();
        this.addEntry(WesternDoors);
    }

    private void darkerPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

}
