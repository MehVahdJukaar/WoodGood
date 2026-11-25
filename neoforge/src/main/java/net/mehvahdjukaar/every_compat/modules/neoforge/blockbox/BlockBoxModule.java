package net.mehvahdjukaar.every_compat.modules.neoforge.blockbox;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;
import vectorwing.blockbox.common.block.PalisadeBlock;
import vectorwing.blockbox.common.block.SeatBlock;
import vectorwing.blockbox.common.block.SpikedPalisadeBlock;
import vectorwing.blockbox.common.registry.ModCreativeTabs;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//SUPPORT: v0.1.3+
//credits to partonetrain
public class BlockBoxModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> seat;

    public final SimpleEntrySet<WoodType, Block> palisade;
    public final SimpleEntrySet<WoodType, Block> spikedPalisade;
    public final SimpleEntrySet<WoodType, Block> strippedPalisade;
    public final SimpleEntrySet<WoodType, Block> strippedSpikedPalisade;

    //texture constants since they are used more than once
    public static final String BLOCK_OAK_PALISADE_CORE = "block/oak_palisade_core";
    public static final String BLOCK_OAK_PALISADE_TOP = "block/oak_palisade_top";
    public static final String BLOCK_STRIPPED_OAK_PALISADE_CORE = "block/stripped_oak_palisade_core";
    public static final String BLOCK_STRIPPED_OAK_PALISADE_TOP = "block/stripped_oak_palisade_top";


    public BlockBoxModule(String modId) {
        super(modId, "bb");
        ResourceLocation creativeTabRL = ModCreativeTabs.TAB_BLOCK_BOX.getId();

        this.seat = SimpleEntrySet.builder(WoodType.class, "seat",
                        getModBlock("oak_seat"), () -> VanillaWoodTypes.OAK,
                        w -> new SeatBlock(copyProperties(getModBlock("oak_seat").get(), w, null)))
                .requiresChildren(PLANKS) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_seats"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(creativeTabRL))
                .addRecipe(modRes("oak_seat"))
                .copyParentDrop()
                .addTexture(modRes("block/oak_seat_side"))
                .addTexture(modRes("block/oak_seat_top"))
                .build();
        this.addEntry(seat);

        this.palisade = SimpleEntrySet.builder(WoodType.class, "palisade",
                        getModBlock("oak_palisade"), () -> VanillaWoodTypes.OAK,
                        w -> new PalisadeBlock(getPalisadeForm(w, PalisadeForm.SPIKED), getPalisadeForm(w, PalisadeForm.STRIPPED),
                                palisadeProperties(w))
                        )
                .requiresChildren(LOG) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("palisades"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(creativeTabRL))
                .copyParentDrop()
                .addRecipe(modRes("oak_palisade"))
                .addRecipe(modRes("oak_palisade_from_spiked"))
                .addTexture(modRes(BLOCK_OAK_PALISADE_CORE), PaletteStrategies.PLANKS_STANDARD)
                .addTexture(modRes(BLOCK_OAK_PALISADE_TOP), PaletteStrategies.LOG_TOP_STANDARD) //<-- spike texture in here
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(palisade);
        this.spikedPalisade = SimpleEntrySet.builder(WoodType.class, "palisade", "spiked",
                        getModBlock("spiked_oak_palisade"), () -> VanillaWoodTypes.OAK,
                        w -> new SpikedPalisadeBlock(getPalisadeForm(w, PalisadeForm.STRIPPED_SPIKED), palisadeProperties(w)))
                .requiresChildren(LOG)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("spiked_palisades"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(creativeTabRL))
                .copyParentDrop()
                .addRecipe(modRes("spiked_oak_palisade"))
                .addTexture(modRes(BLOCK_OAK_PALISADE_CORE), PaletteStrategies.PLANKS_STANDARD)
                .addTexture(modRes(BLOCK_OAK_PALISADE_TOP), PaletteStrategies.LOG_TOP_STANDARD)
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(spikedPalisade);

        this.strippedPalisade = SimpleEntrySet.builder(WoodType.class, "palisade", "stripped",
                        getModBlock("stripped_oak_palisade"), () -> VanillaWoodTypes.OAK,
                        w -> new PalisadeBlock(getPalisadeForm(w, PalisadeForm.STRIPPED_SPIKED), null, palisadeProperties(w)))
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("palisades"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(creativeTabRL))
                .copyParentDrop()
                .addRecipe(modRes("stripped_oak_palisade"))
                .addRecipe(modRes("stripped_oak_palisade_from_spiked"))
                .addTexture(modRes(BLOCK_STRIPPED_OAK_PALISADE_CORE), PaletteStrategies.STRIPPED_LOG_SIDE_STANDARD)
                .addTexture(modRes(BLOCK_STRIPPED_OAK_PALISADE_TOP), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedPalisade);
        this.strippedSpikedPalisade = SimpleEntrySet.builder(WoodType.class, "palisade", "stripped_spiked",
                        getModBlock("stripped_spiked_oak_palisade"), () -> VanillaWoodTypes.OAK,
                        w -> new SpikedPalisadeBlock(null, palisadeProperties(w)))
                .requiresChildren(STRIPPED_LOG)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("spiked_palisades"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(creativeTabRL))
                .copyParentDrop()
                .addRecipe(modRes("stripped_spiked_oak_palisade"))
                .addTexture(modRes(BLOCK_STRIPPED_OAK_PALISADE_CORE), PaletteStrategies.STRIPPED_LOG_SIDE_STANDARD)
                .addTexture(modRes(BLOCK_STRIPPED_OAK_PALISADE_TOP), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedSpikedPalisade);

    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, sink) -> {
            for (var entry : List.of(palisade, spikedPalisade)) {
                entry.blocks.forEach((w, block) -> addPalisadeSide(manager, sink, w, w.log, "", false, block));
            }
            for (var e : List.of(strippedPalisade, strippedSpikedPalisade)) {
                e.blocks.forEach((w, block) -> addPalisadeSide(manager, sink, w, w.getBlockOfThis("stripped_log"), "stripped_", true, block));
            }
        });
    }

    private void addPalisadeSide(ResourceManager manager, ResourceSink sink, WoodType w, Block log, String prefix, boolean hasDarkSides, Block block) {
        try (TextureImage logTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))) {

            ResourceLocation newId = EveryCompat.res("block/" + shortenedId() + "/" + w.getNamespace() + "/" + prefix + w.getTypeName() + "_palisade_side");
            var newTexture = logTexture.makeCopy();
            if (hasDarkSides) moveDarkSideAway(newTexture);
            darkenEdges(newTexture);

            sink.addTextureIfNotPresent(manager, newId, () -> newTexture);

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate Palisade texture for for {} : {}", block, e);
        }
    }

    private static void moveDarkSideAway(TextureImage t) {
        //int w = t.imageWidth();
        int w = t.frameWidth();
        for (int f = 0; f < t.frameCount(); f++) {
            for (int y = 0; y < t.frameHeight(); y++) {
                t.setFramePixel(f, 0, y, t.getFramePixel(f, w / 2, y));
                t.setFramePixel(f, 1, y, t.getFramePixel(f, w / 2 + 1, y));
            }
        }
    }

    //nonsense edge darkening, mimics block box
    private static void darkenEdges(TextureImage t) {
        Palette p = Palette.fromImage(t, null, 0);

        int n = p.size();
        int w = t.frameWidth();
        for (int f = 0; f < t.frameCount(); f++) {
            for (int edge : new int[]{w / 2 - 1, w - 1}) {
                for (int y = 0; y < t.frameHeight(); y++) {
                    // if (t.getFramePixel(f, edge, y) == 0) continue;
                    int i = p.indexOf(p.getColorClosestTo(new PaletteColor(t.getFramePixel(f, edge, y))));
                    t.setFramePixel(f, edge, y, p.get(Math.min(i > n / 2 ? 1 : 0, n - 1)).value());

                    int j = p.indexOf(p.getColorClosestTo(new PaletteColor(t.getFramePixel(f, edge - 1, y))));
                    t.setFramePixel(f, edge - 1, y, p.get(j * (n / 2) / n).value());
                }
            }
        }
    }

    private BlockBehaviour.Properties palisadeProperties(WoodType w) {
        // blockbox gives nether palisades the stem sound
        return copyProperties(this.palisade.getBaseBlock(), w, w.canBurn() ? SoundType.WOOD : SoundType.STEM);
    }

    private static BlockBehaviour.Properties copyProperties(Block base, WoodType w, @Nullable SoundType sound) {
        var p = BlockBehaviour.Properties.ofFullCopy(base).mapColor(w.getColor());
        if (sound != null) p.sound(sound);
        return p;
    }

    public Supplier<Block> getPalisadeForm(WoodType woodType, PalisadeForm palisadeForm){
        return () -> switch (palisadeForm){
            case NORMAL -> palisade.blocks.get(woodType);
            case SPIKED -> spikedPalisade.blocks.get(woodType);
            case STRIPPED -> strippedPalisade.blocks.get(woodType);
            case STRIPPED_SPIKED -> strippedSpikedPalisade.blocks.get(woodType);
        };
    }

    public enum PalisadeForm {
        NORMAL,
        SPIKED,
        STRIPPED,
        STRIPPED_SPIKED
    }

}
