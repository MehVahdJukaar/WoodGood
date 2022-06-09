package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwtrpdoors.kikoz.MacawsTrapdoors;
import com.mcwtrpdoors.kikoz.init.BlockInit;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class MacawTrapdoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BARK_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARRED_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BEACH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> COTTAGE_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> TROPICAL_TRAPDOORS;

    public MacawTrapdoorsModule(String modId) {
        super(modId, "mct");

        BARK_TRAPDOORS = SimpleEntrySet.builder("bark_trapdoor",
                        BlockInit.OAK_BARK_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(BARK_TRAPDOORS);

        BARN_TRAPDOORS = SimpleEntrySet.builder("barn_trapdoor",
                        BlockInit.OAK_BARN_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/barn/oak_barn_trapdoor"))
                .build();

        this.addEntry(BARN_TRAPDOORS);

        BARRED_TRAPDOORS = SimpleEntrySet.builder("barred_trapdoor",
                        BlockInit.OAK_BARRED_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/barred/oak_barred_trapdoor"))
                .build();

        this.addEntry(BARRED_TRAPDOORS);

        BEACH_TRAPDOORS = SimpleEntrySet.builder("beach_trapdoor",
                        BlockInit.OAK_BEACH_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/beach/oak_beach_trapdoor"), WoodGood.res("block/mcaw/trapdoors/oak_beach_trapdoor_m"))
                .build();

        this.addEntry(BEACH_TRAPDOORS);

        CLASSIC_TRAPDOORS = SimpleEntrySet.builder("classic_trapdoor",
                        BlockInit.SPRUCE_CLASSIC_TRAPDOOR, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/classic/spruce_classic_trapdoor"))
                .build();

        this.addEntry(CLASSIC_TRAPDOORS);

        COTTAGE_TRAPDOORS = SimpleEntrySet.builder("cottage_trapdoor",
                        BlockInit.OAK_COTTAGE_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/cottage/oak_cottage_trapdoor"), WoodGood.res("block/mcaw/trapdoors/oak_cottage_trapdoor_m"))
                .build();

        this.addEntry(COTTAGE_TRAPDOORS);

        FOUR_PANEL_TRAPDOORS = SimpleEntrySet.builder("four_panel_trapdoor",
                        BlockInit.OAK_FOUR_PANEL_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/four_panel/oak_four_panel_trapdoor"))
                .build();

        this.addEntry(FOUR_PANEL_TRAPDOORS);

        GLASS_TRAPDOORS = SimpleEntrySet.builder("glass_trapdoor",
                        BlockInit.OAK_GLASS_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/glass/oak_glass_trapdoor"), WoodGood.res("block/mcaw/trapdoors/oak_glass_trapdoor_m"))
                .build();

        this.addEntry(GLASS_TRAPDOORS);

        MYSTIC_TRAPDOORS = SimpleEntrySet.builder("mystic_trapdoor",
                        BlockInit.OAK_MYSTIC_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/mystic/oak_mystic_trapdoor"))
                .build();

        this.addEntry(MYSTIC_TRAPDOORS);

        PAPER_TRAPDOORS = SimpleEntrySet.builder("paper_trapdoor",
                        BlockInit.OAK_PAPER_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/paper/oak_paper_trapdoor"), WoodGood.res("block/mcaw/trapdoors/oak_paper_trapdoor_m"))
                .build();

        this.addEntry(PAPER_TRAPDOORS);

        TROPICAL_TRAPDOORS = SimpleEntrySet.builder("tropical_trapdoor",
                        BlockInit.OAK_TROPICAL_TRAPDOOR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5F, 2.5F).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.TRAPDOORS, Registry.ITEM_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setTab(MacawsTrapdoors.TrapDoorItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTextureM(modRes("block/tropical/oak_tropical_trapdoor"), WoodGood.res("block/mcaw/trapdoors/oak_tropical_trapdoor_m"))
                .build();

        this.addEntry(TROPICAL_TRAPDOORS);
    }
}
