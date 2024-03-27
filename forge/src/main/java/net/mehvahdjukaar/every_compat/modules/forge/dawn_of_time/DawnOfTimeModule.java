package net.mehvahdjukaar.every_compat.modules.forge.dawn_of_time;

import com.mcwpaths.kikoz.init.BlockInit;
import com.mcwpaths.kikoz.init.TabInit;
import com.mcwpaths.kikoz.objects.FacingPathBlock;
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
import org.dawnoftimebuilder.block.templates.BeamBlock;
import org.dawnoftimebuilder.block.templates.EdgeBlock;
import org.dawnoftimebuilder.block.templates.LatticeBlock;
import org.dawnoftimebuilder.block.templates.PergolaBlock;
import org.dawnoftimebuilder.block.templates.PlateBlock;
import org.dawnoftimebuilder.registry.DoTBCreativeModeTabsRegistry;


//SUPPORT v1.0.4+
public class DawnOfTimeModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BEAM;
    public final SimpleEntrySet<WoodType, Block> EDGE;
    public final SimpleEntrySet<WoodType, Block> LATTICE;
    public final SimpleEntrySet<WoodType, Block> PERGOLA;
    public final SimpleEntrySet<WoodType, Block> PLATE;
    public final SimpleEntrySet<WoodType, Block> WALL;

    public DawnOfTimeModule(String modId) {
        super(modId, "dot");
        var tab = DoTBCreativeModeTabsRegistry.DOT_TAB;


        PLATE = SimpleEntrySet.builder(WoodType.class, "planks_plate",
                        () -> getModBlock("oak_planks_plate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlateBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(PLATE);


        EDGE = SimpleEntrySet.builder(WoodType.class, "planks_edge",
                        () -> getModBlock("oak_planks_edge"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new EdgeBlock(Utils.copyPropertySafe(w.planks).ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(EDGE);


        BEAM = SimpleEntrySet.builder(WoodType.class, "beam",
                        () -> getModBlock("oak_beam"), () -> WoodTypeRegistry.OAK_TYPE,
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
                        () -> getModBlock("oak_wall"), () -> WoodTypeRegistry.OAK_TYPE,
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


        PERGOLA = SimpleEntrySet.builder(WoodType.class, "pergola",
                        () -> getModBlock("oak_pergola"), () -> WoodTypeRegistry.OAK_TYPE,
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
                        () -> getModBlock("oak_lattice"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LatticeBlock(Utils.copyPropertySafe(w.planks).noOcclusion().ignitedByLava()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_lattice"))
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(LATTICE);
    }

    private void dullPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
