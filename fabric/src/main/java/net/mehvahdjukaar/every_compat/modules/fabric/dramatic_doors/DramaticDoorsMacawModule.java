package net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors;

import com.fizzware.dramaticdoors.fabric.DDRegistry;
import com.fizzware.dramaticdoors.fabric.blocks.TallDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallSlidingDoorBlock;
import com.fizzware.dramaticdoors.fabric.blocks.TallStableDoorBlock;
import net.kikoz.mcwdoors.MacawsDoors;
import net.kikoz.mcwdoors.init.BlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

//SUPPORT: DramaticDoors v3.2.7+ | Macaw's Door v1.1.1+
//NOTE: The library of FABRIC and FORGE are not the same, must be in separated folders
public class DramaticDoorsMacawModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tallBarkGlassDoors;
    public final SimpleEntrySet<WoodType, Block> tallBarnDoors;
    public final SimpleEntrySet<WoodType, Block> tallBarnGlassDoors;
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
    public final SimpleEntrySet<WoodType, Block> tallStableHeadDoors;
    public final SimpleEntrySet<WoodType, Block> tallSwampDoors;
    public final SimpleEntrySet<WoodType, Block> tallTropicalDoors;

    public DramaticDoorsMacawModule(String modId) {
        super(modId, "ddm");
        ResourceKey<CreativeModeTab> tab = DDRegistry.MAIN_TAB;
        
        tallBarnDoors = SimpleEntrySet.builder(WoodType.class, "barn_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_barn_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARN_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_door"), EveryCompat.res("item/ddm/tall_oak_barn_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarnDoors);

        tallBarnGlassDoors = SimpleEntrySet.builder(WoodType.class, "barn_glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_barn_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARN_GLASS_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_barn_door_lower"), EveryCompat.res("block/ddm/tall_oak_barn_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_barn_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_barn_glass_door"), EveryCompat.res("item/ddm/tall_oak_barn_glass_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarnGlassDoors);

        tallStableDoors = SimpleEntrySet.builder(WoodType.class, "stable_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_stable_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(BlockInit.OAK_STABLE_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_door"), EveryCompat.res("item/ddm/tall_oak_stable_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallStableDoors);

        tallStableHeadDoors = SimpleEntrySet.builder(WoodType.class, "stable_head_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_stable_head_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallStableDoorBlock(BlockInit.OAK_STABLE_HEAD_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_lower"), EveryCompat.res("block/ddm/tall_oak_stable_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_head_door_middle"), EveryCompat.res("block/ddm/tall_oak_stable_head_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_stable_door_upper"), EveryCompat.res("block/ddm/tall_oak_stable_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_stable_head_door"), EveryCompat.res("item/ddm/tall_oak_stable_head_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallStableHeadDoors);

        tallBarkGlassDoors = SimpleEntrySet.builder(WoodType.class, "bark_glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_bark_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BARK_GLASS_DOOR, w.toVanillaOrOak().setType())
                )
                .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
                .addTextureM(modRes("item/macaw/tall_oak_bark_glass_door"), EveryCompat.res("item/ddm/tall_oak_bark_glass_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBarkGlassDoors);

        tallGlassDoors = SimpleEntrySet.builder(WoodType.class, "glass_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_glass_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_GLASS_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_lower"), EveryCompat.res("block/ddm/tall_oak_glass_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_middle"), EveryCompat.res("block/ddm/tall_oak_glass_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_glass_door_upper"), EveryCompat.res("block/ddm/tall_oak_glass_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_glass_door"), EveryCompat.res("item/ddm/tall_oak_glass_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallGlassDoors);

        tallModernDoors = SimpleEntrySet.builder(WoodType.class, "modern_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_modern_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_MODERN_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_lower"), EveryCompat.res("block/ddm/tall_oak_modern_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_middle"), EveryCompat.res("block/ddm/tall_oak_modern_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_modern_door_upper"), EveryCompat.res("block/ddm/tall_oak_modern_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_modern_door"), EveryCompat.res("item/ddm/tall_oak_modern_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallModernDoors);

        tallShojiDoors = SimpleEntrySet.builder(WoodType.class, "japanese_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_japanese_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(BlockInit.OAK_JAPANESE_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_lower"), EveryCompat.res("block/ddm/tall_oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_middle"), EveryCompat.res("block/ddm/tall_oak_japanese_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese_door_upper"), EveryCompat.res("block/ddm/tall_oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese_door"), EveryCompat.res("item/ddm/tall_oak_japanese_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallShojiDoors);

        tallShojiWholeDoors = SimpleEntrySet.builder(WoodType.class, "japanese2_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_japanese2_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallSlidingDoorBlock(BlockInit.OAK_JAPANESE2_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_lower"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_middle"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_japanese2_door_upper"), EveryCompat.res("block/ddm/tall_oak_japanese2_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_japanese2_door"), EveryCompat.res("item/ddm/tall_oak_japanese2_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallShojiWholeDoors);

        tallClassicDoors = SimpleEntrySet.builder(WoodType.class, "classic_door", "tall_macaw",
                        getModBlock("tall_macaw_spruce_classic_door"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TallDoorBlock(BlockInit.SPRUCE_CLASSIC_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_lower"), EveryCompat.res("block/ddm/tall_oak_classic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_middle"), EveryCompat.res("block/ddm/tall_oak_classic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_spruce_classic_door_upper"), EveryCompat.res("block/ddm/tall_oak_classic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_spruce_classic_door"), EveryCompat.res("item/ddm/tall_oak_classic_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallClassicDoors);

        tallCottageDoors = SimpleEntrySet.builder(WoodType.class, "cottage_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_cottage_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_COTTAGE_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_lower"), EveryCompat.res("block/ddm/tall_oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_middle"), EveryCompat.res("block/ddm/tall_oak_cottage_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_cottage_door_upper"), EveryCompat.res("block/ddm/tall_oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_cottage_door"), EveryCompat.res("item/ddm/tall_oak_cottage_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallCottageDoors);

        tallPaperDoors = SimpleEntrySet.builder(WoodType.class, "paper_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_paper_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_PAPER_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_lower"), EveryCompat.res("block/ddm/tall_oak_paper_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_middle"), EveryCompat.res("block/ddm/tall_oak_paper_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_paper_door_upper"), EveryCompat.res("block/ddm/tall_oak_paper_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_paper_door"), EveryCompat.res("item/ddm/tall_oak_paper_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallPaperDoors);

        tallBeachDoors = SimpleEntrySet.builder(WoodType.class, "beach_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_beach_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_BEACH_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_lower"), EveryCompat.res("block/ddm/tall_oak_beach_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_middle"), EveryCompat.res("block/ddm/tall_oak_beach_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_beach_door_upper"), EveryCompat.res("block/ddm/tall_oak_beach_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_beach_door"), EveryCompat.res("item/ddm/tall_oak_beach_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallBeachDoors);

        tallTropicalDoors = SimpleEntrySet.builder(WoodType.class, "tropical_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_tropical_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_TROPICAL_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_lower"), EveryCompat.res("block/ddm/tall_oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_middle"), EveryCompat.res("block/ddm/tall_oak_tropical_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_tropical_door_upper"), EveryCompat.res("block/ddm/tall_oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_tropical_door"), EveryCompat.res("item/ddm/tall_oak_tropical_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallTropicalDoors);

        tallFourPanelDoors = SimpleEntrySet.builder(WoodType.class, "four_panel_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_four_panel_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_FOUR_PANEL_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_lower"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_middle"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_four_panel_door_upper"), EveryCompat.res("block/ddm/tall_oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_four_panel_door"), EveryCompat.res("item/ddm/tall_oak_four_panel_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallFourPanelDoors);

        tallSwampDoors = SimpleEntrySet.builder(WoodType.class, "swamp_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_swamp_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_SWAMP_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_lower"), EveryCompat.res("block/ddm/tall_oak_swamp_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_middle"), EveryCompat.res("block/ddm/tall_oak_swamp_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_swamp_door_upper"), EveryCompat.res("block/ddm/tall_oak_swamp_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_swamp_door"), EveryCompat.res("item/ddm/tall_oak_swamp_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .createPaletteFromPlanks(this::swampDoorPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallSwampDoors);

        tallNetherDoors = SimpleEntrySet.builder(WoodType.class, "nether_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_nether_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_NETHER_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_lower"), EveryCompat.res("block/ddm/tall_oak_nether_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_middle"), EveryCompat.res("block/ddm/tall_oak_nether_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_nether_door_upper"), EveryCompat.res("block/ddm/tall_oak_nether_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_nether_door"), EveryCompat.res("item/ddm/tall_oak_nether_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallNetherDoors);

        tallMysticDoors = SimpleEntrySet.builder(WoodType.class, "mystic_door", "tall_macaw",
                        getModBlock("tall_macaw_oak_mystic_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallDoorBlock(BlockInit.OAK_MYSTIC_DOOR, w.toVanillaOrOak().setType())
                )
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_lower"), EveryCompat.res("block/ddm/tall_oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_middle"), EveryCompat.res("block/ddm/tall_oak_mystic_door_middle_m"))
                .addTextureM(modRes("block/macaw/tall_oak_mystic_door_upper"), EveryCompat.res("block/ddm/tall_oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/macaw/tall_oak_mystic_door"), EveryCompat.res("item/ddm/tall_oak_mystic_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tall_wooden_doors"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:brittle"), Registries.BLOCK)
                .addTag(new ResourceLocation("locksmith:lockable"), Registries.BLOCK)
                .addTag(new ResourceLocation("caupona:chimney_ignore"), Registries.BLOCK)
                .addTag(modRes("tall_doors"), Registries.ITEM)
                .addTag(modRes("tall_wooden_doors"), Registries.ITEM)
                .addTag(modRes("categories/tall_macaw_doors"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .copyParentDrop()
                .build();
        this.addEntry(tallMysticDoors);
    }

    private void swampDoorPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

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

        for (WoodType woodType : WoodTypeRegistry.getTypes()) {
            if (woodType.isVanilla()) continue;

            for (var entry : this.getEntries()) {
                String newRecipe = recipe;

                SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                Block currentDDMdoor = currentEntry.blocks.get(woodType);

                // Macaw's Doors' Entries
                String childNameMCD =  currentEntry.typeName.replace("tall_macaw_", "");
                Block currentMCDoor = woodType.getBlockOfThis(MacawsDoors.MOD_ID +":"+ childNameMCD);

                if (Objects.nonNull(currentDDMdoor) && Objects.nonNull(currentMCDoor)) {
                    newRecipe = newRecipe.replace("[ddm_doors]", Utils.getID(currentDDMdoor).toString())
                            .replace("[mcwdoors]", Utils.getID(currentMCDoor).toString());

                    ResourceLocation newResLoc =  Utils.getID(currentDDMdoor);

                    handler.dynamicPack.addBytes(newResLoc, newRecipe.getBytes(), ResType.RECIPES);
                }
            }
        }
    }
}
