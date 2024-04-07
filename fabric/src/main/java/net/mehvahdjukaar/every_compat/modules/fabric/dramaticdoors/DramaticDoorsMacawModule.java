package net.mehvahdjukaar.every_compat.modules.fabric.dramaticdoors;

import com.fizzware.dramaticdoors.fabric.DDRegistry;
import com.fizzware.dramaticdoors.fabric.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallSlidingDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallStableDoorBlock;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kikoz.mcwdoors.init.BlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/*
SUPPORT:
    - DramaticDoors - v3.1.2+
    - Macaw's Doors - v1.1.0+
*/
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
    public final SimpleEntrySet<WoodType, Block> tallShojiDoors; // japanese_door
    public final SimpleEntrySet<WoodType, Block> tallShojiWholeDoors; // japanese2_door
    public final SimpleEntrySet<WoodType, Block> tallStableDoors;
    public final SimpleEntrySet<WoodType, Block> tallStableHeadDoors;
    public final SimpleEntrySet<WoodType, Block> tallSwampDoors;
    public final SimpleEntrySet<WoodType, Block> tallTropicalDoors;
    public final SimpleEntrySet<WoodType, Block> tallMeshDoors; // bamboo_door
    public final SimpleEntrySet<WoodType, Block> tallWaffleDoors;

    public DramaticDoorsMacawModule(String modId) {
        super(modId, "ddm");

        tallBarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_barn_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARN_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_door"), EveryCompat.res("item/ddm/tall_oak_barn_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_barn_door"))
                .build();
        this.addEntry(tallBarnDoors);

        tallBarnGlassedDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_barn_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARN_GLASS_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_glass_door"), EveryCompat.res("item/ddm/tall_oak_barn_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_barn_glass_door"))
                .build();
        this.addEntry(tallBarnGlassedDoors);

        tallStableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_stable_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(BlockInit.OAK_STABLE_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_door"), EveryCompat.res("item/ddm/tall_oak_stable_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_stable_door"))
                .build();
        this.addEntry(tallStableDoors);

        tallStableHeadDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_stable_head_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(BlockInit.OAK_STABLE_HEAD_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_head_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_head_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_head_door"), EveryCompat.res("item/ddm/tall_oak_stable_head_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_stable_head_door"))
                .build();
        this.addEntry(tallStableHeadDoors);

        tallBarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_bark_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARK_GLASS_DOOR))
                .addTextureM(modRes("item/macaw/tall_oak_bark_glass_door"), EveryCompat.res("block/ddm/tall_oak_bark_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_bark_glass_door"))
                .build();
        this.addEntry(tallBarkGlassDoors);

        tallGlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_GLASS_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_lower"), EveryCompat.res("block/ddm/tall_oak_glass_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_glass_door"), EveryCompat.res("item/ddm/tall_oak_glass_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_glass_door"))
                .build();
        this.addEntry(tallGlassDoors);

        tallModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_modern_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_MODERN_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_lower"), EveryCompat.res("block/ddm/tall_oak_modern_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_middle"), EveryCompat.res("block/ddm/tall_oak_modern_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_upper"), EveryCompat.res("block/ddm/tall_oak_modern_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_modern_door"), EveryCompat.res("item/ddm/tall_oak_modern_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_modern_door"))
                .build();
        this.addEntry(tallModernDoors);

        tallShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_japanese_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(BlockInit.OAK_JAPANESE_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_lower"), EveryCompat.res("block/ddm/tall_oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_middle"), EveryCompat.res("block/ddm/tall_oak_japanese_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_upper"), EveryCompat.res("block/ddm/tall_oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese_door"), EveryCompat.res("item/ddm/tall_oak_japanese_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_japanese_door"))
                .build();
        this.addEntry(tallShojiDoors);

        tallShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_japanese2_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(BlockInit.OAK_JAPANESE2_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_lower"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_middle"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_upper"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese2_door"), EveryCompat.res("item/ddm/tall_oak_japanese2_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_japanese2_door"))
                .build();
        this.addEntry(tallShojiWholeDoors);

        tallClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_spruce_classic_door"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TallDoorBlock(BlockInit.SPRUCE_CLASSIC_DOOR))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_lower"), EveryCompat.res("block/ddm/tall_oak_classic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_middle"), EveryCompat.res("block/ddm/tall_oak_classic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_upper"), EveryCompat.res("block/ddm/tall_oak_classic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_spruce_classic_door"), EveryCompat.res("item/ddm/tall_oak_classic_door_m"))
                .addModelTransform(m -> m.replaceGenericType("spruce", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_classic_door"))
                .build();
        this.addEntry(tallClassicDoors);

        tallCottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_cottage_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_COTTAGE_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_lower"), EveryCompat.res("block/ddm/tall_oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_middle"), EveryCompat.res("block/ddm/tall_oak_cottage_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_upper"), EveryCompat.res("block/ddm/tall_oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_cottage_door"), EveryCompat.res("item/ddm/tall_oak_cottage_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_cottage_door"))
                .build();
        this.addEntry(tallCottageDoors);

        tallPaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_paper_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_PAPER_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_lower"), EveryCompat.res("block/ddm/tall_oak_paper_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_middle"), EveryCompat.res("block/ddm/tall_oak_paper_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_upper"), EveryCompat.res("block/ddm/tall_oak_paper_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_paper_door"), EveryCompat.res("item/ddm/tall_oak_paper_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_paper_door"))
                .build();
        this.addEntry(tallPaperDoors);

        tallBeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_beach_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BEACH_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_lower"), EveryCompat.res("block/ddm/tall_oak_beach_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_middle"), EveryCompat.res("block/ddm/tall_oak_beach_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_upper"), EveryCompat.res("block/ddm/tall_oak_beach_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_beach_door"), EveryCompat.res("item/ddm/tall_oak_beach_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_beach_door"))
                .build();
        this.addEntry(tallBeachDoors);

        tallTropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_tropical_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_TROPICAL_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_lower"), EveryCompat.res("block/ddm/tall_oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_middle"), EveryCompat.res("block/ddm/tall_oak_tropical_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_upper"), EveryCompat.res("block/ddm/tall_oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_tropical_door"), EveryCompat.res("item/ddm/tall_oak_tropical_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_tropical_door"))
                .build();
        this.addEntry(tallTropicalDoors);

        tallFourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_four_panel_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_FOUR_PANEL_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_lower"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_middle"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_upper"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_four_panel_door"), EveryCompat.res("item/ddm/tall_oak_four_panel_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_four_panel_door"))
                .build();
        this.addEntry(tallFourPanelDoors);

        tallSwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_swamp_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_SWAMP_DOOR))
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
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_swamp_door"))
                .build();
        this.addEntry(tallSwampDoors);

        tallNetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_nether_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_NETHER_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_lower"), EveryCompat.res("block/ddm/tall_oak_nether_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_middle"), EveryCompat.res("block/ddm/tall_oak_nether_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_upper"), EveryCompat.res("block/ddm/tall_oak_nether_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_nether_door"), EveryCompat.res("item/ddm/tall_oak_nether_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_nether_door"))
                .build();
        this.addEntry(tallNetherDoors);

        tallMysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_mystic_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_MYSTIC_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_lower"), EveryCompat.res("block/ddm/tall_oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_middle"), EveryCompat.res("block/ddm/tall_oak_mystic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_upper"), EveryCompat.res("block/ddm/tall_oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_mystic_door"), EveryCompat.res("item/ddm/tall_oak_mystic_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_mystic_door"))
                .build();
        this.addEntry(tallMysticDoors);

        tallMeshDoors = SimpleEntrySet.builder(WoodType.class, "bamboo_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_bamboo_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BAMBOO_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_bamboo_door_lower"), EveryCompat.res("block/ddm/tall_oak_bamboo_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_bamboo_door_middle"), EveryCompat.res("block/ddm/tall_oak_bamboo_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_bamboo_door_upper"), EveryCompat.res("block/ddm/tall_oak_bamboo_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_bamboo_door"), EveryCompat.res("item/ddm/tall_oak_bamboo_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_bamboo_door"))
                .build();
        this.addEntry(tallMeshDoors);

        tallWaffleDoors = SimpleEntrySet.builder(WoodType.class, "waffle_door", "tall_macaw",
                        () -> getModBlock("tall_macaw_oak_waffle_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_WAFFLE_DOOR))
                .addTextureM(modRes("block/macaw/tall_oak_waffle_door_lower"), EveryCompat.res("block/ddm/tall_oak_waffle_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_waffle_door_middle"), EveryCompat.res("block/ddm/tall_oak_waffle_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_waffle_door_upper"), EveryCompat.res("block/ddm/tall_oak_waffle_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_waffle_door"), EveryCompat.res("item/ddm/tall_oak_waffle_door_m"))
                .addModelTransform(m -> m.replaceGenericType("oak", "item/macaw"))
                .addTag(modRes("tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DDRegistry.MAIN_TAB)
                .copyParentDrop()
                .addRecipe(modRes("tall_macaw_oak_waffle_door"))
                .build();
        this.addEntry(tallWaffleDoors);
    }


    private void swampDoorPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);


        try (TextureImage mask = TextureImage.open(manager, EveryCompat.res("item/ddm/tall_bark_glass_door_mask"));
             TextureImage overlay = TextureImage.open(manager, EveryCompat.res("item/ddm/tall_bark_glass_door_overlay"))
        ) {
            tallBarkGlassDoors.blocks.forEach((wood, block) -> {
                var id = Utils.getID(block);

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))) {

                    var t = mask.makeCopy();
                    t.applyOverlayOnExisting(logTexture.makeCopy(), overlay.makeCopy());

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation(id.getNamespace(),
                            "item/macaw/" + id.getPath().replace("_macaw", "")), t);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to open Texture Image via: ", e);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Tall Bark Door item textures : ", ex);
        }
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        DynamicDataPack pack = handler.dynamicPack;

        tallBarnDoors.items.forEach((wood, item) -> {
            craftingShaped_Recipe("barn", item, wood, pack);

            craftingShaped_Recipe("barn_glass", tallBarkGlassDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("stable", tallStableDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("stable_head", tallStableHeadDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("bark_glass", tallBarkGlassDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("glass", tallGlassDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("modern", tallModernDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("japanese", tallShojiDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("japanese2", tallShojiWholeDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("classic", tallClassicDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("cottage", tallCottageDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("paper", tallPaperDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("beach", tallBeachDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("tropical", tallTropicalDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("four_panel", tallFourPanelDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("swamp", tallSwampDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("nether", tallNetherDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("mystic", tallMysticDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("bamboo", tallMeshDoors.items.get(wood), wood, pack);
            craftingShaped_Recipe("waffle", tallWaffleDoors.items.get(wood), wood, pack);

        });
    }

    public void craftingShaped_Recipe(String blockType, Item output,
                                     WoodType wood, DynamicDataPack pack) {
        // IDs from Macaw's Doors via EveryComp
        String itemName = wood.getTypeName() + "_" + blockType + "_door";
        // using the items from Macaw's Doors
        String input = EveryCompat.MOD_ID + ":" + "mcd/" + wood.getNamespace() + "/" + itemName;

        // Creating a JSON
        JsonObject result = new JsonObject();
        result.addProperty("item",Utils.getID(output).toString());
        result.addProperty("count", 2);

        JsonObject hash = new JsonObject();
        hash.addProperty("item", input);

        JsonObject key = new JsonObject();
        key.add("#", hash);

        JsonArray pattern = new JsonArray();
        pattern.add("#");
        pattern.add("#");
        pattern.add("#");

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shaped");
        recipe.addProperty("group", "tall_wooden_door");
        recipe.add("pattern", pattern);
        recipe.add("key", key);
        recipe.add("result", result);

        // Adding the JSON to ResourceLocation
        pack.addJson(EveryCompat.res(this.shortenedId() + "/" + wood.getNamespace() + "/tall_macaw_" + itemName), recipe, ResType.RECIPES);
    }
}
