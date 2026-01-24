package net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors;

import com.fizzware.dramaticdoors.fabric.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallSlidingDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallStableDoorBlock;
import net.kikoz.mcwdoors.MacawsDoors;
import net.kikoz.mcwdoors.init.BlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Consumer;

//SUPPORT: DramaticDoors v3.3.2+ | Macaw's Door v1.1.1+
//NOTE: The library of FABRIC and FORGE are not the same, must be in separated folders
public class DramaticDoorsMacawModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> tallBarkGlassDoors,
            tallBarnDoors,
            tallBarnGlassDoors,
            tallBeachDoors,
            tallCottageDoors,
            tallClassicDoors,
            tallGlassDoors,
            tallFourPanelDoors,
            tallModernDoors,
            tallMysticDoors,
            tallNetherDoors,
            tallPaperDoors,
            tallShojiDoors,
            tallShojiWholeDoors,
            tallStableDoors,
            tallStableHeadDoors,
            tallSwampDoors,
            tallTropicalDoors;

    public DramaticDoorsMacawModule(String modId) {
        super(modId, "ddm");
        ResourceLocation tab = modRes("macaw_tab");
        
        tallBarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_barn_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_BARN_DOOR)
                )
                .requiresChildren("mcwdoors:barn_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_barn_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_barn_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_door"),
                        EveryCompat.res("item/ddm/tall_oak_barn_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarnDoors);

        tallBarnGlassDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_barn_glass_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_BARN_GLASS_DOOR)
                )
                .requiresChildren("mcwdoors:barn_glass_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_barn_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_glass_door"),
                        EveryCompat.res("item/ddm/tall_oak_barn_glass_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarnGlassDoors);

        tallStableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_stable_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallStableDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_STABLE_DOOR)
                )
                .requiresChildren("mcwdoors:stable_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_stable_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_door"),
                        EveryCompat.res("item/ddm/tall_oak_stable_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallStableDoors);

        tallStableHeadDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_stable_head_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallStableDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_STABLE_HEAD_DOOR)
                )
                .requiresChildren("mcwdoors:stable_head_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_head_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_stable_head_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_head_door"),
                        EveryCompat.res("item/ddm/tall_oak_stable_head_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallStableHeadDoors);

        tallBarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_bark_glass_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_BARK_GLASS_DOOR)
                )
                .requiresChildren("mcwdoors:bark_glass_door") //REASON: Recipes
                .addTextureM(modRes("item/macaw/tall_oak_bark_glass_door"),
                        EveryCompat.res("item/ddm/tall_oak_bark_glass_door_m"),
                        PaletteStrategies.LOG_SIDE_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarkGlassDoors);

        tallGlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_glass_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_GLASS_DOOR)
                )
                .requiresChildren("mcwdoors:glass_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_glass_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_glass_door"),
                        EveryCompat.res("item/ddm/tall_oak_glass_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallGlassDoors);

        tallModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_modern_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_MODERN_DOOR)
                )
                .requiresChildren("mcwdoors:modern_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_modern_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_modern_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_modern_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_modern_door"),
                        EveryCompat.res("item/ddm/tall_oak_modern_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallModernDoors);

        tallShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_japanese_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallSlidingDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_JAPANESE_DOOR)
                )
                .requiresChildren("mcwdoors:japanese_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_japanese_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese_door"),
                        EveryCompat.res("item/ddm/tall_oak_japanese_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallShojiDoors);

        tallShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_japanese2_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallSlidingDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_JAPANESE2_DOOR)
                )
                .requiresChildren("mcwdoors:japanese2_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_japanese2_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_japanese2_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_japanese2_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese2_door"),
                        EveryCompat.res("item/ddm/tall_oak_japanese2_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallShojiWholeDoors);

        tallClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door", "tall_macaw",
                        getModBlock("tall_macaw_spruce_classic_door"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.SPRUCE_CLASSIC_DOOR)
                )
                .requiresChildren("mcwdoors:classic_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_classic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_classic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_classic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_spruce_classic_door"),
                        EveryCompat.res("item/ddm/tall_oak_classic_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallClassicDoors);

        tallCottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_cottage_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_COTTAGE_DOOR)
                )
                .requiresChildren("mcwdoors:cottage_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_cottage_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_cottage_door"),
                        EveryCompat.res("item/ddm/tall_oak_cottage_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallCottageDoors);

        tallPaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_paper_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_PAPER_DOOR)
                )
                .requiresChildren("mcwdoors:paper_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_paper_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_paper_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_paper_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_paper_door"),
                        EveryCompat.res("item/ddm/tall_oak_paper_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallPaperDoors);

        tallBeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_beach_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_BEACH_DOOR)
                )
                .requiresChildren("mcwdoors:beach_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_beach_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_beach_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_beach_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_beach_door"),
                        EveryCompat.res("item/ddm/tall_oak_beach_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBeachDoors);

        tallTropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_tropical_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_TROPICAL_DOOR)
                )
                .requiresChildren("mcwdoors:tropical_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_tropical_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_tropical_door"),
                        EveryCompat.res("item/ddm/tall_oak_tropical_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallTropicalDoors);

        tallFourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_four_panel_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_FOUR_PANEL_DOOR)
                )
                .requiresChildren("mcwdoors:four_panel_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_four_panel_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_four_panel_door"),
                        EveryCompat.res("item/ddm/tall_oak_four_panel_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallFourPanelDoors);

        tallSwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_swamp_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_SWAMP_DOOR)
                )
                .requiresChildren("mcwdoors:swamp_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_lower"),
                        EveryCompat.res("block/ddm/tall_oak_swamp_door_lower_m"),
                        PaletteStrategies.PLANKS_REMOVE_2_DARKEST)
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_middle"),
                        EveryCompat.res("block/ddm/tall_oak_swamp_door_middle_m"),
                        PaletteStrategies.PLANKS_REMOVE_2_DARKEST)
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_upper"),
                        EveryCompat.res("block/ddm/tall_oak_swamp_door_upper_m"),
                        PaletteStrategies.PLANKS_REMOVE_2_DARKEST)
                .addTextureM(modRes("item/macaw/tall_oak_swamp_door"),
                        EveryCompat.res("item/ddm/tall_oak_swamp_door_m"),
                        PaletteStrategies.PLANKS_REMOVE_2_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallSwampDoors);

        tallNetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_nether_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_NETHER_DOOR)
                )
                .requiresChildren("mcwdoors:nether_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_lower"), EveryCompat.res("block/ddm/tall_oak_nether_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_middle"), EveryCompat.res("block/ddm/tall_oak_nether_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_upper"), EveryCompat.res("block/ddm/tall_oak_nether_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_nether_door"), EveryCompat.res("item/ddm/tall_oak_nether_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallNetherDoors);

        tallMysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_mystic_door"), () -> VanillaWoodTypes.OAK,
                        w -> new TallDoorBlock(w.toVanillaOrOak().setType(), BlockInit.OAK_MYSTIC_DOOR)
                )
                .requiresChildren("mcwdoors:mystic_door") //REASON: Recipes
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_lower"), EveryCompat.res("block/ddm/tall_oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_middle"), EveryCompat.res("block/ddm/tall_oak_mystic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_upper"), EveryCompat.res("block/ddm/tall_oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_mystic_door"), EveryCompat.res("item/ddm/tall_oak_mystic_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("tall_doors"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("create:brittle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("locksmith:lockable"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallMysticDoors);
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        String recipe = """
        {
          "type": "minecraft:crafting_shaped",
          "group": "tall_wooden_door",
          "pattern": [
            "#",
            "#",
            "#"
          ],
          "key": {
            "#": {
              "item": "[mcwdoors]"
            }
          },
          "result": {
            "id": "[ddm_doors]",
            "count": 2
          }
        }
        """;

        executor.accept((manager, sink) -> {
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

                for (var entry : this.getEntries()) {
                    String newRecipe = recipe;

                    SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                    Block currentDDMdoor = currentEntry.blocks.get(woodType);

                    // Macaw's Doors' Entries
                    String childNameMCD = currentEntry.typeName.replace("tall_macaw_", "");
                    Block currentMCDoor = woodType.getBlockOfThis(MacawsDoors.MOD_ID + ":" + childNameMCD);

                    if (Objects.nonNull(currentDDMdoor) && Objects.nonNull(currentMCDoor)) {
                        newRecipe = newRecipe.replace("[ddm_doors]", Utils.getID(currentDDMdoor).toString())
                                .replace("[mcwdoors]", Utils.getID(currentMCDoor).toString());

                        ResourceLocation newResLoc = Utils.getID(currentDDMdoor);

                        sink.addBytes(newResLoc, newRecipe.getBytes(), ResType.RECIPES);
                    }
                }
            }

        });
    }
}
