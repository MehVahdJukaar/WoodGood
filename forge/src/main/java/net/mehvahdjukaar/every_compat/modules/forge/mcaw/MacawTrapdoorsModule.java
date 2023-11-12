package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwtrpdoors.kikoz.MacawsTrapdoors;
import com.mcwtrpdoors.kikoz.init.BlockInit;
import com.mcwtrpdoors.kikoz.init.TabInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;


public class MacawTrapdoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BARK_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARN_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARRED_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BEACH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> CLASSIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> COTTAGE_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> FOUR_PANEL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> GLASS_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> MESH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> MYSTIC_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> PAPER_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> RANCH_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> SWAMP_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> TROPICAL_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> WAFFLE_TRAPDOORS;
    public final SimpleEntrySet<WoodType, Block> BARREL_TRAPDOORS;

    //SUPPORT: v1.1.2
    public MacawTrapdoorsModule(String modId) {
        super(modId, "mct");

        var tab = TabInit.TRAPDOORITEMGROUP;

        BARK_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "bark_trapdoor",
                        BlockInit.OAK_BARK_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(BARK_TRAPDOORS);

        BARN_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barn_trapdoor",
                        BlockInit.OAK_BARN_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/barn/oak_barn_trapdoor"))
                .build();

        this.addEntry(BARN_TRAPDOORS);

        BARRED_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barred_trapdoor",
                        BlockInit.OAK_BARRED_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/barred/oak_barred_trapdoor"))
                .build();

        this.addEntry(BARRED_TRAPDOORS);

        BEACH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "beach_trapdoor",
                        BlockInit.OAK_BEACH_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/beach/oak_beach_trapdoor"), EveryCompat.res("block/mcaw/trapdoors/oak_beach_trapdoor_m"))
                .build();

        this.addEntry(BEACH_TRAPDOORS);

        CLASSIC_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "classic_trapdoor",
                        BlockInit.SPRUCE_CLASSIC_TRAPDOOR, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/classic/spruce_classic_trapdoor"))
                .build();

        this.addEntry(CLASSIC_TRAPDOORS);

        COTTAGE_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "cottage_trapdoor",
                        BlockInit.OAK_COTTAGE_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/cottage/oak_cottage_trapdoor"), EveryCompat.res("block/mcaw/trapdoors/oak_cottage_trapdoor_m"))
                .build();

        this.addEntry(COTTAGE_TRAPDOORS);

        FOUR_PANEL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "four_panel_trapdoor",
                        BlockInit.OAK_FOUR_PANEL_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/four_panel/oak_four_panel_trapdoor"))
                .build();

        this.addEntry(FOUR_PANEL_TRAPDOORS);

        GLASS_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "glass_trapdoor",
                        BlockInit.OAK_GLASS_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/glass/oak_glass_trapdoor"), EveryCompat.res("block/mcaw/trapdoors/oak_glass_trapdoor_m"))
                .build();

        this.addEntry(GLASS_TRAPDOORS);

        MESH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "bamboo_trapdoor",
                        BlockInit.OAK_BAMBOO_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .addTexture(modRes("block/bamboo/oak_bamboo_trapdoor"))
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(MESH_TRAPDOORS);

        MYSTIC_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "mystic_trapdoor",
                        BlockInit.OAK_MYSTIC_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/mystic/oak_mystic_trapdoor"))
                .build();

        this.addEntry(MYSTIC_TRAPDOORS);

        PAPER_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "paper_trapdoor",
                        BlockInit.OAK_PAPER_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/paper/oak_paper_trapdoor"), EveryCompat.res("block/mcaw/trapdoors/oak_paper_trapdoor_m"))
                .build();

        this.addEntry(PAPER_TRAPDOORS);

        RANCH_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "ranch_trapdoor",
                        BlockInit.OAK_RANCH_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(RANCH_TRAPDOORS);

        SWAMP_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "swamp_trapdoor",
                        BlockInit.OAK_SWAMP_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(this::swampTrapdoorPalette)
                .addTexture(modRes("block/swamp/oak_swamp_trapdoor"))
                .build();

        this.addEntry(SWAMP_TRAPDOORS);

        TROPICAL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "tropical_trapdoor",
                        BlockInit.OAK_TROPICAL_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/tropical/oak_tropical_trapdoor"), EveryCompat.res("block/mcaw/trapdoors/oak_tropical_trapdoor_m"))
                .build();

        this.addEntry(TROPICAL_TRAPDOORS);

        WAFFLE_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "blossom_trapdoor",
                        BlockInit.OAK_BLOSSOM_TRAPDOOR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/blossom/oak_blossom_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(WAFFLE_TRAPDOORS);

        BARREL_TRAPDOORS = SimpleEntrySet.builder(WoodType.class, "barrel_trapdoor",
                        BlockInit.SPRUCE_BARREL_TRAPDOOR, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/barrel/spruce_barrel_trapdoor"))
                .build();

        this.addEntry(BARREL_TRAPDOORS);

    }

    private void swampTrapdoorPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
