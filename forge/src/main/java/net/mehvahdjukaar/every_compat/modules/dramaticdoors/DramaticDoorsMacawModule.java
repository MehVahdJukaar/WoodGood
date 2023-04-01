package net.mehvahdjukaar.every_compat.modules.dramaticdoors;

import com.fizzware.dramaticdoors.DramaticDoors;
import com.fizzware.dramaticdoors.blocks.DDBlocks;
import com.fizzware.dramaticdoors.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.blocks.TallSlidingDoorBlock;
import com.fizzware.dramaticdoors.blocks.TallStableDoorBlock;
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

    public final SimpleEntrySet<WoodType, Block> TALL_BARK_GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_BARN_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_BARN_GLASSED_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_BEACH_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_COTTAGE_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_CLASSIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_FOUR_PANEL_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_MODERN_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_MYSTIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_NETHER_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_PAPER_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_SHOJI_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_SHOJI_WHOLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_STABLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_STABLE_HORSE_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_SWAMP_DOORS;
    public final SimpleEntrySet<WoodType, Block> TALL_TROPICAL_DOORS;

    public DramaticDoorsMacawModule(String modId) {
        super(modId, "ddm");

        TALL_BARN_DOORS = SimpleEntrySet.builder(WoodType.class, "barn_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_BARN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_BARN_DOORS);

        TALL_BARN_GLASSED_DOORS = SimpleEntrySet.builder(WoodType.class, "barn_glass_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_BARN_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_BARN_GLASSED_DOORS);

        TALL_STABLE_DOORS = SimpleEntrySet.builder(WoodType.class, "stable_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_STABLE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_STABLE_DOORS);

        TALL_STABLE_HORSE_DOORS = SimpleEntrySet.builder(WoodType.class, "stable_head_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_STABLE_HEAD_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_STABLE_HORSE_DOORS);

        TALL_BARK_GLASS_DOORS = SimpleEntrySet.builder(WoodType.class, "bark_glass_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_BARK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_BARK_GLASS_DOORS);

        TALL_GLASS_DOORS = SimpleEntrySet.builder(WoodType.class, "glass_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_GLASS_DOORS);

        TALL_MODERN_DOORS = SimpleEntrySet.builder(WoodType.class, "modern_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_MODERN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_MODERN_DOORS);

        TALL_SHOJI_DOORS = SimpleEntrySet.builder(WoodType.class, "japanese_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_JAPANESE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_SHOJI_DOORS);

        TALL_SHOJI_WHOLE_DOORS = SimpleEntrySet.builder(WoodType.class, "japanese2_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_JAPANESE2_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_SHOJI_WHOLE_DOORS);

        TALL_CLASSIC_DOORS = SimpleEntrySet.builder(WoodType.class, "classic_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_SPRUCE_CLASSIC_DOOR, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
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

        this.addEntry(TALL_CLASSIC_DOORS);

        TALL_COTTAGE_DOORS = SimpleEntrySet.builder(WoodType.class, "cottage_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_COTTAGE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_COTTAGE_DOORS);

        TALL_PAPER_DOORS = SimpleEntrySet.builder(WoodType.class, "paper_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_PAPER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_PAPER_DOORS);

        TALL_BEACH_DOORS = SimpleEntrySet.builder(WoodType.class, "beach_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_BEACH_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_BEACH_DOORS);

        TALL_TROPICAL_DOORS = SimpleEntrySet.builder(WoodType.class, "tropical_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_TROPICAL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_TROPICAL_DOORS);

        TALL_FOUR_PANEL_DOORS = SimpleEntrySet.builder(WoodType.class, "four_panel_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_FOUR_PANEL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_FOUR_PANEL_DOORS);

        TALL_SWAMP_DOORS = SimpleEntrySet.builder(WoodType.class, "swamp_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_SWAMP_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_SWAMP_DOORS);

        TALL_NETHER_DOORS = SimpleEntrySet.builder(WoodType.class, "nether_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_NETHER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_NETHER_DOORS);

        TALL_MYSTIC_DOORS = SimpleEntrySet.builder(WoodType.class, "mystic_door", "tall_macaw",
                        DDBlocks.TALL_MACAW_OAK_MYSTIC_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(TALL_MYSTIC_DOORS);
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
            TALL_BARK_GLASS_DOORS.blocks.forEach((wood, block) -> {
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
