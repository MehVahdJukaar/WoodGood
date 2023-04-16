package net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors;

import com.fizzware.dramaticdoors.DramaticDoors;
import com.fizzware.dramaticdoors.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.blocks.TallSlidingDoorBlock;
import com.fizzware.dramaticdoors.blocks.TallStableDoorBlock;
import com.fizzware.dramaticdoors.init.DDBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class DramaticDoorsMacawModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tallBarkGlassDoors;
    public final SimpleEntrySet<WoodType, Block> tallBarnDoors;
    public final SimpleEntrySet<WoodType, Block> tallBarnGlassedDoors;
    public final SimpleEntrySet<WoodType, Block> tallBeachDoors;
    public final SimpleEntrySet<WoodType, Block> tallCottageDoors;
    public final SimpleEntrySet<WoodType, Block> tallClassicDoors;
    public final SimpleEntrySet<WoodType, Block> tallGlassDoors;
    public final SimpleEntrySet<WoodType, Block> tallFourPanelDoors;
    public final SimpleEntrySet<WoodType, Block> tallModernDoors;
    public final SimpleEntrySet<WoodType, Block> tallMysticDoors;
    public final SimpleEntrySet<WoodType, Block> tallNetherDoors;
    public final SimpleEntrySet<WoodType, Block> tallPaperDoors;
    public final SimpleEntrySet<WoodType, Block> tallShojiDoors;
    public final SimpleEntrySet<WoodType, Block> tallShojiWholeDoors;
    public final SimpleEntrySet<WoodType, Block> tallStableDoors;
    public final SimpleEntrySet<WoodType, Block> tallStableHorseDoors;
    public final SimpleEntrySet<WoodType, Block> tallSwampDoors;
    public final SimpleEntrySet<WoodType, Block> tallTropicalDoors;

    public DramaticDoorsMacawModule(String modId) {
        super(modId, "ddm");

        tallBarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_barn_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_barn_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_door"), EveryCompat.res("item/ddm/tall_oak_barn_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallBarnDoors);

        tallBarnGlassedDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_barn_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_barn_glass_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_glass_door"), EveryCompat.res("item/ddm/tall_oak_barn_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallBarnGlassedDoors);

        tallStableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_stable_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_stable_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_door"), EveryCompat.res("item/ddm/tall_oak_stable_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallStableDoors);

        tallStableHorseDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_stable_head_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_stable_head_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_head_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_head_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_head_door"), EveryCompat.res("item/ddm/tall_oak_stable_head_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallStableHorseDoors);

        tallBarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_bark_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_bark_glass_door"))))
                .addTextureM(modRes("item/macaw/tall_oak_bark_glass_door"), EveryCompat.res("block/ddm/tall_oak_bark_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallBarkGlassDoors);

        tallGlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_glass_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_lower"), EveryCompat.res("block/ddm/tall_oak_glass_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_glass_door"), EveryCompat.res("item/ddm/tall_oak_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallGlassDoors);

        tallModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_modern_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_modern_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_lower"), EveryCompat.res("block/ddm/tall_oak_modern_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_middle"), EveryCompat.res("block/ddm/tall_oak_modern_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_upper"), EveryCompat.res("block/ddm/tall_oak_modern_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_modern_door"), EveryCompat.res("item/ddm/tall_oak_modern_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallModernDoors);

        tallShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_japanese_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_japanese_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_lower"), EveryCompat.res("block/ddm/tall_oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_middle"), EveryCompat.res("block/ddm/tall_oak_japanese_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_upper"), EveryCompat.res("block/ddm/tall_oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese_door"), EveryCompat.res("item/ddm/tall_oak_japanese_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallShojiDoors);

        tallShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_japanese2_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_japanese2_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_lower"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_middle"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_upper"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese2_door"), EveryCompat.res("item/ddm/tall_oak_japanese2_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallShojiWholeDoors);

        tallClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door", "tall_macaw",
                        () -> getModBlock("tall_spruce_classic_door"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "spruce_classic_door"))))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_lower"), EveryCompat.res("block/ddm/tall_oak_classic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_middle"), EveryCompat.res("block/ddm/tall_oak_classic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_upper"), EveryCompat.res("block/ddm/tall_oak_classic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_spruce_classic_door"), EveryCompat.res("item/ddm/tall_oak_classic_door_m"))
                .addModelTransform(m -> m.replaceGenericType("spruce", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallClassicDoors);

        tallCottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_cottage_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_cottage_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_lower"), EveryCompat.res("block/ddm/tall_oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_middle"), EveryCompat.res("block/ddm/tall_oak_cottage_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_upper"), EveryCompat.res("block/ddm/tall_oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_cottage_door"), EveryCompat.res("item/ddm/tall_oak_cottage_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallCottageDoors);

        tallPaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_paper_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_paper_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_lower"), EveryCompat.res("block/ddm/tall_oak_paper_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_middle"), EveryCompat.res("block/ddm/tall_oak_paper_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_upper"), EveryCompat.res("block/ddm/tall_oak_paper_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_paper_door"), EveryCompat.res("item/ddm/tall_oak_paper_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallPaperDoors);

        tallBeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_beach_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_beach_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_lower"), EveryCompat.res("block/ddm/tall_oak_beach_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_middle"), EveryCompat.res("block/ddm/tall_oak_beach_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_upper"), EveryCompat.res("block/ddm/tall_oak_beach_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_beach_door"), EveryCompat.res("item/ddm/tall_oak_beach_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallBeachDoors);

        tallTropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_tropical_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_tropical_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_lower"), EveryCompat.res("block/ddm/tall_oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_middle"), EveryCompat.res("block/ddm/tall_oak_tropical_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_upper"), EveryCompat.res("block/ddm/tall_oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_tropical_door"), EveryCompat.res("item/ddm/tall_oak_tropical_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallTropicalDoors);

        tallFourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_four_panel_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_four_panel_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_lower"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_middle"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_upper"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_four_panel_door"), EveryCompat.res("item/ddm/tall_oak_four_panel_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallFourPanelDoors);

        tallSwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_swamp_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_swamp_door"))))
                .addTextureM(EveryCompat.res("block/macaw/tall_oak_swamp_door_lower"), EveryCompat.res("block/ddm/tall_oak_swamp_door_lower_m"))
                .addTextureM(EveryCompat.res("block/macaw/tall_oak_swamp_door_middle"), EveryCompat.res("block/ddm/tall_oak_swamp_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_upper"), EveryCompat.res("block/ddm/tall_oak_swamp_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_swamp_door"), EveryCompat.res("item/ddm/tall_oak_swamp_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .createPaletteFromOak(this::swampDoorPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallSwampDoors);

        tallNetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_nether_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_nether_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_lower"), EveryCompat.res("block/ddm/tall_oak_nether_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_middle"), EveryCompat.res("block/ddm/tall_oak_nether_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_upper"), EveryCompat.res("block/ddm/tall_oak_nether_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_nether_door"), EveryCompat.res("item/ddm/tall_oak_nether_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallNetherDoors);

        tallMysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_mystic_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(DDBlocks.getBlockByKey(new ResourceLocation("mcwdoors", "oak_mystic_door"))))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_lower"), EveryCompat.res("block/ddm/tall_oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_middle"), EveryCompat.res("block/ddm/tall_oak_mystic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_upper"), EveryCompat.res("block/ddm/tall_oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_mystic_door"), EveryCompat.res("item/ddm/tall_oak_mystic_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .useLootFromBase()
                .defaultRecipe()
                .build();

        this.addEntry(tallMysticDoors);
    }

    private void swampDoorPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);


        try (TextureImage mask = TextureImage.open(manager, EveryCompat.res("item/ddm/tall_bark_glass_door_mask"));
             TextureImage overlay = TextureImage.open(manager, EveryCompat.res("item/ddm/tall_bark_glass_door_overlay"))
        ) {
            tallBarkGlassDoors.blocks.forEach((wood, block) -> {
                var id = Utils.getID(block);

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteUtils.LOOKS_LIKE_SIDE_LOG_TEXTURE))) {

                    var t = mask.makeCopy();
                    t.applyOverlayOnExisting(logTexture.makeCopy(), overlay.makeCopy());

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation(id.getNamespace(),
                            "item/macaw/" + id.getPath().replace("_macaw", "")), t);

                } catch (Exception ignored) {
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Tall Bark Door item textures : ", ex);
        }
    }
}
