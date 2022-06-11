package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwdoors.kikoz.MacawsDoors;
import com.mcwdoors.kikoz.init.BlockInit;
import com.mcwdoors.kikoz.objects.JapaneseDoors;
import com.mcwtrpdoors.kikoz.MacawsTrapdoors;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;


public class MacawDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BARK_DOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_DOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> BEACH_DOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> COTTAGE_DOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_DOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> MODERN_DOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> NETHER_DOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_DOORS;
    public final SimpleEntrySet<WoodType, Block> SHOJI_DOORS;
    public final SimpleEntrySet<WoodType, Block> SHOJI_WHOLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> STABLE_DOORS;
    public final SimpleEntrySet<WoodType, Block> STABLE_HEAD_DOORS;
    public final SimpleEntrySet<WoodType, Block> TROPICAL_DOORS;
    public final SimpleEntrySet<WoodType, Block> WESTERN_DOORS;

    public MacawDoorsModule(String modId) {
        super(modId, "mcd");

        BARK_DOORS = SimpleEntrySet.builder("bark_glass_door",
                        BlockInit.OAK_BARK_GLASS_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("item/oak_bark_glass_door"), WoodGood.res("block/mcaw/doors/oak_bark_glass_door_m"))
                .build();

        this.addEntry(BARK_DOORS);

        BARN_DOORS = SimpleEntrySet.builder("barn_door",
                        BlockInit.OAK_BARN_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), WoodGood.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_door_upper"), WoodGood.res("block/mcaw/doors/oak_barn_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_door"), WoodGood.res("block/mcaw/doors/oak_barn_door_m"))
                .build();

        this.addEntry(BARN_DOORS);

        BARN_GLASS_DOORS = SimpleEntrySet.builder("barn_glass_door",
                        BlockInit.OAK_BARN_GLASS_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_barn_door_lower"), WoodGood.res("block/mcaw/doors/oak_barn_door_lower_m"))
                .addTextureM(modRes("block/oak_barn_glass_door_upper"), WoodGood.res("block/mcaw/doors/oak_barn_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_barn_glass_door"), WoodGood.res("block/mcaw/doors/oak_barn_glass_door_m"))
                .build();

        this.addEntry(BARN_GLASS_DOORS);

        BEACH_DOORS = SimpleEntrySet.builder("beach_door",
                        BlockInit.OAK_BEACH_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_beach_door_lower"), WoodGood.res("block/mcaw/doors/oak_beach_door_lower_m"))
                .addTextureM(modRes("block/oak_beach_door_upper"), WoodGood.res("block/mcaw/doors/oak_beach_door_upper_m"))
                .addTextureM(modRes("item/oak_beach_door"), WoodGood.res("block/mcaw/doors/oak_beach_door_m"))
                .build();

        this.addEntry(BEACH_DOORS);

        CLASSIC_DOORS = SimpleEntrySet.builder("classic_door",
                        BlockInit.SPRUCE_CLASSIC_DOOR, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/spruce_classic_door_lower"), WoodGood.res("block/mcaw/doors/spruce_classic_door_lower_m"))
                .addTextureM(modRes("block/spruce_classic_door_upper"), WoodGood.res("block/mcaw/doors/spruce_classic_door_upper_m"))
                .addTextureM(ResourceLocation.tryParse("item/oak_door"), WoodGood.res("block/mcaw/doors/spruce_classic_door_m"))
                .build();

        this.addEntry(CLASSIC_DOORS);

        COTTAGE_DOORS = SimpleEntrySet.builder("cottage_door",
                        BlockInit.OAK_COTTAGE_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_cottage_door_lower"), WoodGood.res("block/mcaw/doors/oak_cottage_door_lower_m"))
                .addTextureM(modRes("block/oak_cottage_door_upper"), WoodGood.res("block/mcaw/doors/oak_cottage_door_upper_m"))
                .addTextureM(modRes("item/oak_cottage_door"), WoodGood.res("block/mcaw/doors/oak_cottage_door_m"))
                .build();

        this.addEntry(COTTAGE_DOORS);

        FOUR_PANEL_DOORS = SimpleEntrySet.builder("four_panel_door",
                        BlockInit.OAK_FOUR_PANEL_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_four_panel_door_lower"), WoodGood.res("block/mcaw/doors/oak_four_panel_door_lower_m"))
                .addTextureM(modRes("block/oak_four_panel_door_upper"), WoodGood.res("block/mcaw/doors/oak_four_panel_door_upper_m"))
                .addTextureM(modRes("item/oak_four_panel_door"), WoodGood.res("block/mcaw/doors/oak_four_panel_door_m"))
                .build();

        this.addEntry(FOUR_PANEL_DOORS);

        GLASS_DOORS = SimpleEntrySet.builder("glass_door",
                        BlockInit.OAK_GLASS_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/glass/oak_glass_door_lower"), WoodGood.res("block/mcaw/doors/oak_glass_door_lower_m"))
                .addTextureM(modRes("block/glass/oak_glass_door_upper"), WoodGood.res("block/mcaw/doors/oak_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_glass_door"), WoodGood.res("block/mcaw/doors/oak_glass_door_m"))
                .build();

        this.addEntry(GLASS_DOORS);

        MODERN_DOORS = SimpleEntrySet.builder("modern_door",
                        BlockInit.OAK_MODERN_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_modern_door_lower"), WoodGood.res("block/mcaw/doors/oak_modern_door_lower_m"))
                .addTextureM(modRes("block/oak_modern_door_upper"), WoodGood.res("block/mcaw/doors/oak_modern_door_upper_m"))
                .addTextureM(modRes("item/oak_modern_door"), WoodGood.res("block/mcaw/doors/oak_modern_door_m"))
                .build();

        this.addEntry(MODERN_DOORS);

        MYSTIC_DOORS = SimpleEntrySet.builder("mystic_door",
                        BlockInit.OAK_MYSTIC_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_mystic_door_lower"), WoodGood.res("block/mcaw/doors/oak_mystic_door_lower_m"))
                .addTextureM(modRes("block/oak_mystic_door_upper"), WoodGood.res("block/mcaw/doors/oak_mystic_door_upper_m"))
                .addTextureM(modRes("item/oak_mystic_door"), WoodGood.res("block/mcaw/doors/oak_mystic_door_m"))
                .build();

        this.addEntry(MYSTIC_DOORS);

        NETHER_DOORS = SimpleEntrySet.builder("nether_door",
                        BlockInit.OAK_NETHER_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_nether_door_lower"), WoodGood.res("block/mcaw/doors/oak_nether_door_lower_m"))
                .addTextureM(modRes("block/oak_nether_door_upper"), WoodGood.res("block/mcaw/doors/oak_nether_door_upper_m"))
                .addTextureM(modRes("item/oak_nether_door"), WoodGood.res("block/mcaw/doors/oak_nether_door_m"))
                .build();

        this.addEntry(NETHER_DOORS);

        PAPER_DOORS = SimpleEntrySet.builder("paper_door",
                        BlockInit.OAK_PAPER_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_paper_door_lower"), WoodGood.res("block/mcaw/doors/oak_paper_door_lower_m"))
                .addTextureM(modRes("block/oak_paper_door_upper"), WoodGood.res("block/mcaw/doors/oak_paper_door_upper_m"))
                .addTextureM(modRes("item/oak_paper_door"), WoodGood.res("block/mcaw/doors/oak_paper_door_m"))
                .build();

        this.addEntry(PAPER_DOORS);

        SHOJI_DOORS = SimpleEntrySet.builder("japanese_door",
                        BlockInit.OAK_JAPANESE_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new JapaneseDoors(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.SCAFFOLDING)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/japanese_oak_lower"), WoodGood.res("block/mcaw/doors/japanese_oak_lower_m"))
                .addTextureM(modRes("block/japanese_oak_upper"), WoodGood.res("block/mcaw/doors/japanese_oak_upper_m"))
                .addTextureM(modRes("item/oak_japanese_door"), WoodGood.res("block/mcaw/doors/oak_japanese_door_m"))
                .build();

        this.addEntry(SHOJI_DOORS);

        SHOJI_WHOLE_DOORS = SimpleEntrySet.builder("japanese2_door",
                        BlockInit.OAK_JAPANESE2_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new JapaneseDoors(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.SCAFFOLDING)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_japanese2_door_lower"), WoodGood.res("block/mcaw/doors/oak_japanese2_door_lower_m"))
                .addTextureM(modRes("block/oak_japanese2_door_upper"), WoodGood.res("block/mcaw/doors/oak_japanese2_door_upper_m"))
                .addTextureM(modRes("item/oak_japanese2_door"), WoodGood.res("block/mcaw/doors/oak_japanese2_door_m"))
                .build();

        this.addEntry(SHOJI_WHOLE_DOORS);

        STABLE_DOORS = SimpleEntrySet.builder("stable_door",
                        BlockInit.OAK_STABLE_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(3.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/oak_barn_door_lower"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), WoodGood.res("block/mcaw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_upper"), WoodGood.res("block/mcaw/doors/oak_stable_door_upper_m"))
                .addTextureM(modRes("item/oak_stable_door"), WoodGood.res("block/mcaw/doors/oak_stable_door_m"))
                .build();

        this.addEntry(STABLE_DOORS);

        STABLE_HEAD_DOORS = SimpleEntrySet.builder("stable_head_door",
                        BlockInit.OAK_STABLE_HEAD_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(3.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .addRecipe(modRes("oak_stable_head_door"))
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/oak_barn_door_lower"))
                .addTextureM(modRes("block/stable_head/oak_stable_head_door_lower"), WoodGood.res("block/mcaw/doors/oak_stable_head_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), WoodGood.res("block/mcaw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("item/oak_stable_head_door"), WoodGood.res("block/mcaw/doors/oak_stable_head_door_m"))
                .build();

        this.addEntry(STABLE_HEAD_DOORS);

        TROPICAL_DOORS = SimpleEntrySet.builder("tropical_door",
                        BlockInit.OAK_TROPICAL_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .addRecipe(modRes("oak_tropical_door"))
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/oak_tropical_door_lower"),WoodGood.res("block/mcaw/doors/oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/oak_tropical_door_upper"),WoodGood.res("block/mcaw/doors/oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/oak_tropical_door"), WoodGood.res("block/mcaw/doors/oak_tropical_door_m"))
                .build();

        this.addEntry(TROPICAL_DOORS);

        WESTERN_DOORS = SimpleEntrySet.builder("western_door",
                        BlockInit.OAK_WESTERN_DOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(3.0F, 3.0F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.DOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsDoors.DoorItemGroup)
                .useLootFromBase()
                .addRecipe(modRes("oak_western_door"))
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/western/oak_western_door_lower"), WoodGood.res("block/mcaw/doors/oak_western_door_lower_m"))
                .addTextureM(modRes("block/western/oak_western_door_upper"), WoodGood.res("block/mcaw/doors/oak_western_door_upper_m"))
                .addTextureM(modRes("item/oak_western_door"), WoodGood.res("block/mcaw/doors/oak_western_door_m"))
                .build();

        this.addEntry(WESTERN_DOORS);
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
        Utils.addBlockResources(modId, manager, handler.dynamicPack, TROPICAL_DOORS.blocks,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceItemType("oak")
                        .IDReplaceType("oak"),
                ResType.ITEM_MODELS.getPath(modRes("oak_bark_glass_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_barn_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_barn_glass_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_beach_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_cottage_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_four_panel_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_glass_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_japanese_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_japanese2_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_modern_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_mystic_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_nether_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_paper_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_stable_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_stable_head_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_tropical_door")),
                ResType.ITEM_MODELS.getPath(modRes("oak_western_door"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, CLASSIC_DOORS.blocks,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceItemType("spruce")
                        .IDReplaceType("spruce"),
                ResType.ITEM_MODELS.getPath(modRes("spruce_classic_door"))
        );
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
    }
}
