package net.mehvahdjukaar.every_compat.modules.forge.dawn_of_time;

import java.util.function.ToIntFunction;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import org.dawnoftimebuilder.block.japanese.SpruceLeglessChairBlock;
import org.dawnoftimebuilder.block.japanese.SpruceLowTableBlock;
import org.dawnoftimebuilder.block.roman.BirchCouchBlock;
import org.dawnoftimebuilder.block.roman.BirchFootstoolBlock;
import org.dawnoftimebuilder.block.templates.BalusterBlock;
import org.dawnoftimebuilder.block.templates.BeamBlock;
import org.dawnoftimebuilder.block.templates.EdgeBlock;
import org.dawnoftimebuilder.block.templates.LatticeBlock;
import org.dawnoftimebuilder.block.templates.PergolaBlock;
import org.dawnoftimebuilder.block.templates.PlateBlock;
import org.dawnoftimebuilder.block.templates.SupportBeamBlock;
import org.dawnoftimebuilder.block.templates.SupportSlabBlock;
import org.dawnoftimebuilder.registry.DoTBBlockEntitiesRegistry;
import org.dawnoftimebuilder.registry.DoTBCreativeModeTabsRegistry;
import pokecube.legends.init.BlockInit;


//SUPPORT v1.5.3+
public class DawnOfTimeModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BEAM;
    public final SimpleEntrySet<WoodType, Block> COUCH;
    public final SimpleEntrySet<WoodType, Block> EDGE;
    public final SimpleEntrySet<WoodType, Block> FANCY_FENCE;
    public final SimpleEntrySet<WoodType, Block> FOOTSTOOL;
    public final SimpleEntrySet<WoodType, Block> LATTICE;
    public final SimpleEntrySet<WoodType, Block> LEGLESS_CHAIR;
    public final SimpleEntrySet<WoodType, Block> LOW_TABLE;
    public final SimpleEntrySet<WoodType, Block> PERGOLA;
    public final SimpleEntrySet<WoodType, Block> PLATE;
    public final SimpleEntrySet<WoodType, Block> SUPPORT_BEAM;
    public final SimpleEntrySet<WoodType, Block> SUPPORT_SLAB;
    public final SimpleEntrySet<WoodType, Block> WALL;

    public DawnOfTimeModule(String modId) {
        super(modId, "dot");
        var tab = DoTBCreativeModeTabsRegistry.DOT_TAB;

        PLATE = SimpleEntrySet.builder(WoodType.class, "planks_plate",
                        getModBlock("oak_planks_plate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlateBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(PLATE);

        EDGE = SimpleEntrySet.builder(WoodType.class, "planks_edge",
                        getModBlock("oak_planks_edge"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new EdgeBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(EDGE);

        PERGOLA = SimpleEntrySet.builder(WoodType.class, "pergola",
                        getModBlock("oak_pergola"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PergolaBlock(Utils.copyPropertySafe(w.log).ignitedByLava()))
                .addTextureM(modRes("block/oak_pergola"), EveryCompat.res("block/dot/oak_pergola_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .createPaletteFromOak(this::dullPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(PERGOLA);

        LATTICE = SimpleEntrySet.builder(WoodType.class, "lattice",
                        getModBlock("oak_lattice"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LatticeBlock(Utils.copyPropertySafe(w.planks).noOcclusion().ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_lattice"))
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(LATTICE);

        BEAM = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BeamBlock(Utils.copyPropertySafe(w.log).ignitedByLava()))
                .addTextureM(modRes("block/oak_beam"), EveryCompat.res("block/dot/oak_beam_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .createPaletteFromOak(this::dullPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(BEAM);

        WALL = SimpleEntrySet.builder(WoodType.class, "wall",
                        getModBlock("oak_wall"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(new ResourceLocation("minecraft:decoration_blocks/fences_and_walls"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .createPaletteFromOak(this::dullPalette)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(WALL);

        SUPPORT_BEAM = SimpleEntrySet.builder(WoodType.class, "support_beam",
                        getModBlock("oak_support_beam"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBeamBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .createPaletteFromOak(this::dullPalette)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(SUPPORT_BEAM);

        SUPPORT_SLAB = SimpleEntrySet.builder(WoodType.class, "support_slab",
                        getModBlock("oak_support_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportSlabBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(SUPPORT_SLAB);

        FANCY_FENCE = SimpleEntrySet.builder(WoodType.class, "fancy_fence",
                        getModBlock("birch_fancy_fence"), () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        w -> new BalusterBlock(Utils.copyPropertySafe(w.planks)
                                .ignitedByLava().noOcclusion().strength(3.0F, 5.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/birch_fancy_fence"))
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(FANCY_FENCE);

        FOOTSTOOL = SimpleEntrySet.builder(WoodType.class, "footstool",
                        getModBlock("birch_footstool"), () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        w -> new BirchFootstoolBlock(Utils.copyPropertySafe(w.planks), 9.0F))
                .addTextureM(modRes("block/birch_footstool"), EveryCompat.res("block/dot/birch_footstool_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(FOOTSTOOL);

        COUCH = SimpleEntrySet.builder(WoodType.class, "couch",
                        getModBlock("birch_couch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        w -> new BirchCouchBlock(Utils.copyPropertySafe(w.planks), 13.0F))
                .addTextureM(modRes("block/birch_couch"), EveryCompat.res("block/dot/birch_couch_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(COUCH);

        LOW_TABLE = SimpleEntrySet.builder(WoodType.class, "low_table",
                        getModBlock("spruce_low_table"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new SpruceLowTableBlock(Utils.copyPropertySafe(w.log).noOcclusion()
                                .strength(2.0F, 6.0F).lightLevel(litBlockEmission(14))))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/spruce_low_table"))
                .addTile(DoTBBlockEntitiesRegistry.DISPLAYER)
                .createPaletteFromOak(this::dullPalette)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(LOW_TABLE);

        LEGLESS_CHAIR = SimpleEntrySet.builder(WoodType.class, "legless_chair",
                        getModBlock("spruce_legless_chair"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new SpruceLeglessChairBlock(Utils.copyPropertySafe(w.log).noOcclusion()
                                .strength(2.0F, 6.0F), 3.0F))
                .addTextureM(modRes("block/spruce_legless_chair"), EveryCompat.res("block/dot/spruce_legless_chair_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .createPaletteFromOak(this::dullPalette)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(LEGLESS_CHAIR);
    }

    private void dullPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> {
            return (Boolean)state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
}
