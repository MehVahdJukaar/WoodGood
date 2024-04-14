package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwdoors.kikoz.init.BlockInit;
import com.mcwdoors.kikoz.init.TabInit;
import com.mcwdoors.kikoz.objects.JapaneseDoors;
import com.mcwdoors.kikoz.objects.StableDoor;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;


public class MacawDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> WAFFLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> BARK_GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_DOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> BEACH_DOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> COTTAGE_DOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_DOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> MESH_DOORS;
    public final SimpleEntrySet<WoodType, Block> MODERN_DOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> NETHER_DOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_DOORS;
    public final SimpleEntrySet<WoodType, Block> SHOJI_DOORS;
    public final SimpleEntrySet<WoodType, Block> SHOJI_WHOLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> STABLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> STABLE_HEAD_DOORS;
    public final SimpleEntrySet<WoodType, Block> SWAMP_DOORS;
    public final SimpleEntrySet<WoodType, Block> TROPICAL_DOORS;
    public final SimpleEntrySet<WoodType, Block> WESTERN_DOORS;

    public MacawDoorsModule(String modId) {
        super(modId, "mcd");
        var tab = TabInit.DOORITEMGROUP;

        WAFFLE_DOORS = SimpleEntrySet.builder(WoodType.class, "waffle_door",
                        BlockInit.OAK_WAFFLE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_waffle_door_lower"), EveryCompat.res("block/mcaw/doors/oak_waffle_door_lower_m"))
                .addTextureM(modRes("block/oak_waffle_door_upper"), EveryCompat.res("block/mcaw/doors/oak_waffle_door_upper_m"))
                .addTextureM(modRes("item/oak_waffle_door"), EveryCompat.res("item/mcaw/doors/oak_waffle_door_m"))
                .build();
        this.addEntry(WAFFLE_DOORS);

        BARK_GLASS_DOORS = SimpleEntrySet.builder(WoodType.class, "bark_glass_door",
                        BlockInit.OAK_BARK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("item/oak_bark_glass_door"), EveryCompat.res("item/mcaw/doors/oak_bark_glass_door_m"))
                .build();

        this.addEntry(BARK_GLASS_DOORS);

        BARN_DOORS = SimpleEntrySet.builder(WoodType.class, "barn_door",
                        BlockInit.OAK_BARN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_door_upper"), EveryCompat.res("block/mcaw/doors/oak_barn_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_door"), EveryCompat.res("item/mcaw/doors/oak_barn_door_m"))
                .build();

        this.addEntry(BARN_DOORS);

        BARN_GLASS_DOORS = SimpleEntrySet.builder(WoodType.class, "barn_glass_door",
                        BlockInit.OAK_BARN_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_glass_door_upper"), EveryCompat.res("block/mcaw/doors/oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_glass_door"), EveryCompat.res("item/mcaw/doors/oak_barn_glass_door_m"))
                .build();

        this.addEntry(BARN_GLASS_DOORS);

        BEACH_DOORS = SimpleEntrySet.builder(WoodType.class, "beach_door",
                        BlockInit.OAK_BEACH_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_beach_door_lower"), EveryCompat.res("block/mcaw/doors/oak_beach_door_lower_m"))
                .addTextureM(modRes("block/oak_beach_door_upper"), EveryCompat.res("block/mcaw/doors/oak_beach_door_upper_m"))
                .addTextureM(modRes("item/oak_beach_door"), EveryCompat.res("item/mcaw/doors/oak_beach_door_m"))
                .build();

        this.addEntry(BEACH_DOORS);

        CLASSIC_DOORS = SimpleEntrySet.builder(WoodType.class, "classic_door",
                        BlockInit.SPRUCE_CLASSIC_DOOR, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/spruce_classic_door_lower"), EveryCompat.res("block/mcaw/doors/spruce_classic_door_lower_m"))
                .addTextureM(modRes("block/spruce_classic_door_upper"), EveryCompat.res("block/mcaw/doors/spruce_classic_door_upper_m"))
                .addTextureM(modRes("item/spruce_classic_door"), EveryCompat.res("item/mcaw/doors/spruce_classic_door_m"))
                .build();

        this.addEntry(CLASSIC_DOORS);

        COTTAGE_DOORS = SimpleEntrySet.builder(WoodType.class, "cottage_door",
                        BlockInit.OAK_COTTAGE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_cottage_door_lower"), EveryCompat.res("block/mcaw/doors/oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/oak_cottage_door_upper"), EveryCompat.res("block/mcaw/doors/oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/oak_cottage_door"), EveryCompat.res("item/mcaw/doors/oak_cottage_door_m"))
                .build();

        this.addEntry(COTTAGE_DOORS);

        FOUR_PANEL_DOORS = SimpleEntrySet.builder(WoodType.class, "four_panel_door",
                        BlockInit.OAK_FOUR_PANEL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_four_panel_door_lower"), EveryCompat.res("block/mcaw/doors/oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/oak_four_panel_door_upper"), EveryCompat.res("block/mcaw/doors/oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/oak_four_panel_door"), EveryCompat.res("item/mcaw/doors/oak_four_panel_door_m"))
                .build();

        this.addEntry(FOUR_PANEL_DOORS);

        GLASS_DOORS = SimpleEntrySet.builder(WoodType.class, "glass_door",
                        BlockInit.OAK_GLASS_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/glass/oak_glass_door_lower"), EveryCompat.res("block/mcaw/doors/oak_glass_door_lower_m"))
                .addTextureM(modRes("block/glass/oak_glass_door_upper"), EveryCompat.res("block/mcaw/doors/oak_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_glass_door"), EveryCompat.res("item/mcaw/doors/oak_glass_door_m"))
                .build();

        this.addEntry(GLASS_DOORS);

        MESH_DOORS = SimpleEntrySet.builder(WoodType.class, "bamboo_door",
                        BlockInit.OAK_BAMBOO_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .addTextureM(modRes("block/oak_bamboo_door_lower"), EveryCompat.res("block/mcaw/doors/oak_bamboo_door_lower_m"))
                .addTextureM(modRes("block/oak_bamboo_door_upper"), EveryCompat.res("block/mcaw/doors/oak_bamboo_door_upper_m"))
                .addTextureM(modRes("item/oak_bamboo_door"), EveryCompat.res("item/mcaw/doors/oak_bamboo_door_m"))
                .createPaletteFromOak(this::darkerPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(MESH_DOORS);

        MODERN_DOORS = SimpleEntrySet.builder(WoodType.class, "modern_door",
                        BlockInit.OAK_MODERN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_modern_door_lower"), EveryCompat.res("block/mcaw/doors/oak_modern_door_lower_m"))
                .addTextureM(modRes("block/oak_modern_door_upper"), EveryCompat.res("block/mcaw/doors/oak_modern_door_upper_m"))
                .addTextureM(modRes("item/oak_modern_door"), EveryCompat.res("item/mcaw/doors/oak_modern_door_m"))
                .build();

        this.addEntry(MODERN_DOORS);

        MYSTIC_DOORS = SimpleEntrySet.builder(WoodType.class, "mystic_door",
                        BlockInit.OAK_MYSTIC_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_mystic_door_lower"), EveryCompat.res("block/mcaw/doors/oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/oak_mystic_door_upper"), EveryCompat.res("block/mcaw/doors/oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/oak_mystic_door"), EveryCompat.res("item/mcaw/doors/oak_mystic_door_m"))
                .build();

        this.addEntry(MYSTIC_DOORS);

        NETHER_DOORS = SimpleEntrySet.builder(WoodType.class, "nether_door",
                        BlockInit.OAK_NETHER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_nether_door_lower"), EveryCompat.res("block/mcaw/doors/oak_nether_door_lower_m"))
                .addTextureM(modRes("block/oak_nether_door_upper"), EveryCompat.res("block/mcaw/doors/oak_nether_door_upper_m"))
                .addTextureM(modRes("item/oak_nether_door"), EveryCompat.res("item/mcaw/doors/oak_nether_door_m"))
                .build();

        this.addEntry(NETHER_DOORS);

        PAPER_DOORS = SimpleEntrySet.builder(WoodType.class, "paper_door",
                        BlockInit.OAK_PAPER_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_paper_door_lower"), EveryCompat.res("block/mcaw/doors/oak_paper_door_lower_m"))
                .addTextureM(modRes("block/oak_paper_door_upper"), EveryCompat.res("block/mcaw/doors/oak_paper_door_upper_m"))
                .addTextureM(modRes("item/oak_paper_door"), EveryCompat.res("item/mcaw/doors/oak_paper_door_m"))
                .build();

        this.addEntry(PAPER_DOORS);

        SHOJI_DOORS = SimpleEntrySet.builder(WoodType.class, "japanese_door",
                        BlockInit.OAK_JAPANESE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new JapaneseDoors(Utils.copyPropertySafe(w.planks).noOcclusion().sound(SoundType.SCAFFOLDING),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/japanese_oak_lower"), EveryCompat.res("block/mcaw/doors/japanese_oak_lower_m"))
                .addTextureM(modRes("block/japanese_oak_upper"), EveryCompat.res("block/mcaw/doors/japanese_oak_upper_m"))
                .addTextureM(modRes("item/oak_japanese_door"), EveryCompat.res("item/mcaw/doors/japanese_oak_door_m"))
                .build();

        this.addEntry(SHOJI_DOORS);

        SHOJI_WHOLE_DOORS = SimpleEntrySet.builder(WoodType.class, "japanese2_door",
                        BlockInit.OAK_JAPANESE2_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new JapaneseDoors(Utils.copyPropertySafe(w.planks).noOcclusion().sound(SoundType.SCAFFOLDING),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_japanese_door_lower"), EveryCompat.res("block/mcaw/doors/oak_japanese_door_lower_m"))
                .addTextureM(modRes("block/oak_japanese_door_upper"), EveryCompat.res("block/mcaw/doors/oak_japanese_door_upper_m"))
                .addTextureM(modRes("item/oak_japanese2_door"), EveryCompat.res("item/mcaw/doors/oak_japanese_door_m"))
                .build();

        this.addEntry(SHOJI_WHOLE_DOORS);

        STABLE_DOORS = SimpleEntrySet.builder(WoodType.class, "stable_door",
                        BlockInit.OAK_STABLE_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StableDoor(Utils.copyPropertySafe(w.planks).noOcclusion(),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), EveryCompat.res("block/mcaw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_upper"), EveryCompat.res("block/mcaw/doors/oak_stable_door_upper_m"))
                .addTextureM(modRes("item/oak_stable_door"), EveryCompat.res("item/mcaw/doors/oak_stable_door_m"))
                .build();

        this.addEntry(STABLE_DOORS);

        STABLE_HEAD_DOORS = SimpleEntrySet.builder(WoodType.class, "stable_head_door",
                        BlockInit.OAK_STABLE_HEAD_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StableDoor(Utils.copyPropertySafe(w.planks).noOcclusion(),
                                w.toVanillaOrOak().setType()))
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_stable_head_door"))
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), EveryCompat.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/stable_head/oak_stable_head_door_lower"), EveryCompat.res("block/mcaw/doors/oak_stable_head_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), EveryCompat.res("block/mcaw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("item/oak_stable_head_door"), EveryCompat.res("item/mcaw/doors/oak_stable_head_door_m"))
                .build();

        this.addEntry(STABLE_HEAD_DOORS);

        SWAMP_DOORS = SimpleEntrySet.builder(WoodType.class, "swamp_door",
                        BlockInit.OAK_SWAMP_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_swamp_door"))
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(this::darkerPalette)
                .addTextureM(EveryCompat.res("block/oak_swamp_door_lower"), EveryCompat.res("block/mcaw/doors/oak_swamp_door_lower_m"))
                .addTextureM(EveryCompat.res("block/oak_swamp_door_upper"), EveryCompat.res("block/mcaw/doors/oak_swamp_door_upper_m"))
                .addTextureM(modRes("item/oak_swamp_door"), EveryCompat.res("item/mcaw/doors/oak_swamp_door_m"))
                .build();

        this.addEntry(SWAMP_DOORS);

        TROPICAL_DOORS = SimpleEntrySet.builder(WoodType.class, "tropical_door",
                        BlockInit.OAK_TROPICAL_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_tropical_door"))
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/oak_tropical_door_lower"), EveryCompat.res("block/mcaw/doors/oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/oak_tropical_door_upper"), EveryCompat.res("block/mcaw/doors/oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/oak_tropical_door"), EveryCompat.res("item/mcaw/doors/oak_tropical_door_m"))
                .build();

        this.addEntry(TROPICAL_DOORS);

        WESTERN_DOORS = SimpleEntrySet.builder(WoodType.class, "western_door",
                        BlockInit.OAK_WESTERN_DOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()){})
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setTab(tab)
                .copyParentDrop()
                .addRecipe(modRes("oak_western_door"))
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/western/oak_western_door_lower"), EveryCompat.res("block/mcaw/doors/oak_western_door_lower_m"))
                .addTextureM(modRes("block/western/oak_western_door_upper"), EveryCompat.res("block/mcaw/doors/oak_western_door_upper_m"))
                .addTextureM(modRes("item/oak_western_door"), EveryCompat.res("item/mcaw/doors/oak_western_door_m"))
                .build();

        this.addEntry(WESTERN_DOORS);
    }

    private void darkerPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

}
