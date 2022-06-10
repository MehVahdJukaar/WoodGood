package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwdoors.kikoz.MacawsDoors;
import com.mcwdoors.kikoz.init.BlockInit;
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
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;


public class MacawDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BARK_DOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_DOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_DOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_DOORS;
    public final SimpleEntrySet<WoodType, Block> NETHER_DOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_DOORS;
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

  //      this.addEntry(BARK_DOORS);

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

   //     this.addEntry(CLASSIC_DOORS);

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

     //   this.addEntry(FOUR_PANEL_DOORS);

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
                .addTexture(modRes("block/oak_barn_door_lower"))
                .addTextureM(modRes("block/glass/oak_glass_door_lower"), WoodGood.res("block/mcaw/doors/oak_glass_door_lower_m"))
                .addTextureM(modRes("block/glass/oak_glass_door_upper"), WoodGood.res("block/mcaw/doors/oak_glass_door_upper_m"))
                .addTextureM(modRes("item/oak_glass_door"), WoodGood.res("block/mcaw/doors/oak_glass_door_m"))
                .build();

     //   this.addEntry(GLASS_DOORS);

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

    //    this.addEntry(MYSTIC_DOORS);

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

     //   this.addEntry(NETHER_DOORS);

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

    //    this.addEntry(PAPER_DOORS);

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

   //     this.addEntry(STABLE_DOORS);

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
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/oak_barn_door_lower"))
                .addTextureM(modRes("block/stable_head/oak_stable_head_door_lower"), WoodGood.res("block/mcaw/doors/oak_stable_head_door_lower_m"))
                .addTextureM(modRes("block/stable/oak_stable_door_lower"), WoodGood.res("block/mcaw/doors/oak_stable_door_lower_m"))
                .addTextureM(modRes("item/oak_stable_head_door"), WoodGood.res("block/mcaw/doors/oak_stable_head_door_m"))
                .build();

  //      this.addEntry(STABLE_HEAD_DOORS);

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
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
//                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
//                .addTextureM(modRes("block/oak_tropical_door_lower"), WoodGood.res("block/mcaw/doors/oak_tropical_door_lower_m"))
//                .addTextureM(modRes("block/oak_tropical_door_upper"), WoodGood.res("block/mcaw/doors/oak_tropical_door_upper_m"))
                .addTextureM(modRes("block/oak_tropical_door_lower"),WoodGood.res("block/mcaw/doors/oak_tropical_door_lower_m"))
                .addTextureM(modRes("block/oak_tropical_door_upper"),WoodGood.res("block/mcaw/doors/oak_tropical_door_upper_m"))
                .addTextureM(modRes("item/oak_tropical_door"), WoodGood.res("block/mcaw/doors/oak_tropical_door_m"))
//                .setPalette(this::doorPalette)
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
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/western/oak_western_door_lower"), WoodGood.res("block/mcaw/doors/oak_western_door_lower_m"))
                .addTextureM(modRes("block/western/oak_western_door_upper"), WoodGood.res("block/mcaw/doors/oak_western_door_upper_m"))
                .addTextureM(modRes("item/oak_western_door"), WoodGood.res("block/mcaw/doors/oak_western_door_m"))
                .build();

  //      this.addEntry(WESTERN_DOORS);
    }

    public Pair<List<Palette>, AnimationMetadataSection> doorPalette(BlockType w, ResourceManager m) {
        try (TextureImage plankTexture = TextureImage.open(m,
                RPUtils.findFirstBlockTextureLocation(m, ((WoodType) w).planks))) {

            List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
            targetPalette.forEach(p -> {
                var l0 = p.getDarkest();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.remove(l0);
            });
            return Pair.of(targetPalette, plankTexture.getMetadata());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
        }
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
        Utils.addBlockResources(modId, manager, handler.dynamicPack, TROPICAL_DOORS.blocks,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceItemType("oak")
                        .IDReplaceBlock("oak_tropical_door"),
                ResType.ITEM_MODELS.getPath(modRes("oak_tropical_door"))
        );
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

    }
}
