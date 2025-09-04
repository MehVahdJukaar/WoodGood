package net.mehvahdjukaar.every_compat.modules.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v
public class ChippedLogModule extends ChippedMainModule {

    public final SimpleEntrySet<WoodType, Block> BundledLog,
            CenterCutLog,
            DamagedLog,
            EdgeCutLog,
            FirewoodLog,
            FloweringLog,
            MixedLog,
            NailedLog,
            OvergrownLog,
            PlankedLog,
            ReinforcedLog;

    public final SimpleEntrySet<WoodType, Block> carvedStrippedLog,
            ChippedStrippedLog,
            DSignStrippedLog,
            edgedStrippedLog,
            FSignStrippedLog,
            ISignStrippedLog,
            KSignStrippedLog,
            knottedStrippedLog,
            LSignStrippedLog,
            LayeredStrippedLog,
            LumpyStrippedLog,
            PatientStrippedLog,
            ReinforcedStrippedLog,
            signStrippedLog,
            sternStrippedLog,
            wiseStrippedLog;

    public ChippedLogModule(String modId) {
        super(modId);
        ResourceLocation tab = modRes(tabPath);

        BundledLog = SimpleEntrySet.builder(WoodType.class, "log", "bundled",
                        getModBlock("bundled_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/bundled_oak_log"))
                .addTexture(modRes("block/oak_log/bundled_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(BundledLog);

        CenterCutLog = SimpleEntrySet.builder(WoodType.class, "log", "center_cut",
                        getModBlock("center_cut_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/center_cut_oak_log"))
                .addTexture(modRes("block/oak_log/center_cut_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(CenterCutLog);

        DamagedLog = SimpleEntrySet.builder(WoodType.class, "log", "damaged",
                        getModBlock("damaged_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/damaged_oak_log"))
                .addTexture(modRes("block/oak_log/damaged_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(DamagedLog);

        EdgeCutLog = SimpleEntrySet.builder(WoodType.class, "log", "edge_cut",
                        getModBlock("edge_cut_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/edge_cut_oak_log"))
                .addTexture(modRes("block/oak_log/edge_cut_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(EdgeCutLog);

        FirewoodLog = SimpleEntrySet.builder(WoodType.class, "log", "firewood",
                        getModBlock("firewood_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/firewood_oak_log"))
                .addTexture(modRes("block/oak_log/firewood_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FirewoodLog);

        FloweringLog = SimpleEntrySet.builder(WoodType.class, "log", "flowering",
                        getModBlock("flowering_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/flowering_oak_log"),
                        EveryCompat.res("block/ch/logs/flowering_oak_log_m"))
                .addTextureM(modRes("block/oak_log/flowering_oak_log_top"),
                        EveryCompat.res("block/ch/logs/flowering_oak_log_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FloweringLog);

        MixedLog = SimpleEntrySet.builder(WoodType.class, "log", "mixed",
                        getModBlock("mixed_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/mixed_oak_log"))
                .addTexture(modRes("block/oak_log/mixed_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(MixedLog);

        NailedLog = SimpleEntrySet.builder(WoodType.class, "log", "nailed",
                        getModBlock("nailed_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/nailed_oak_log"),
                        EveryCompat.res("block/ch/logs/nailed_oak_log_m"))
                .addTexture(modRes("block/oak_log/nailed_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(NailedLog);

        OvergrownLog = SimpleEntrySet.builder(WoodType.class, "log", "overgrown",
                        getModBlock("overgrown_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/overgrown_oak_log"),
                        EveryCompat.res("block/ch/logs/overgrown_oak_log_m"))
                .addTextureM(modRes("block/oak_log/overgrown_oak_log_top"),
                        EveryCompat.res("block/ch/logs/overgrown_oak_log_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(OvergrownLog);

        PlankedLog = SimpleEntrySet.builder(WoodType.class, "log", "planked",
                        getModBlock("planked_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(PlankedLog);

        ReinforcedLog = SimpleEntrySet.builder(WoodType.class, "log", "reinforced",
                        getModBlock("reinforced_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/reinforced_oak_log"),
                        EveryCompat.res("block/ch/logs/reinforced_oak_log_m"))
                .addTexture(modRes("block/oak_log/reinforced_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ReinforcedLog);

/// ────────────────────────────────────────────── STRIPPED_LOG ───────────────────────────────────────────────

        carvedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "carved_stripped",
                        getModBlock("carved_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/carved_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/carved_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(carvedStrippedLog);

        ChippedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "chipped_stripped",
                        getModBlock("chipped_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/chipped_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/chipped_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ChippedStrippedLog);

        DSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "d_sign_stripped",
                        getModBlock("d_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/d_sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/d_sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(DSignStrippedLog);

        edgedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "edged_stripped",
                        getModBlock("edged_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/edged_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/edged_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(edgedStrippedLog);

        FSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "f_sign_stripped",
                        getModBlock("f_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/f_sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/f_sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FSignStrippedLog);

        ISignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "i_sign_stripped",
                        getModBlock("i_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/i_sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/i_sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ISignStrippedLog);

        KSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "k_sign_stripped",
                        getModBlock("k_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/k_sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/k_sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(KSignStrippedLog);

        knottedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "knotted_stripped",
                        getModBlock("knotted_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/knotted_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/knotted_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(knottedStrippedLog);

        LSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "l_sign_stripped",
                        getModBlock("l_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/l_sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/l_sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LSignStrippedLog);

        LayeredStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "layered_stripped",
                        getModBlock("layered_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/layered_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/layered_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LayeredStrippedLog);

        LumpyStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "lumpy_stripped",
                        getModBlock("lumpy_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/lumpy_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/lumpy_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LumpyStrippedLog);

        PatientStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "patient_stripped",
                        getModBlock("patient_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/patient_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/patient_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(PatientStrippedLog);

        ReinforcedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "reinforced_stripped",
                        getModBlock("reinforced_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/reinforced_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/reinforced_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ReinforcedStrippedLog);

        signStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "sign_stripped",
                        getModBlock("sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/sign_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/sign_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(signStrippedLog);

        sternStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "stern_stripped",
                        getModBlock("stern_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/stern_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/stern_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(sternStrippedLog);

        wiseStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "wise_stripped",
                        getModBlock("wise_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/wise_stripped_oak_log"), lightPalette)
                .addTexture(modRes("block/stripped_oak_log/wise_stripped_oak_log_top"), lightPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(wiseStrippedLog);

    }

    public static final PaletteStrategy lightPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, STRIPPED_LOG, null, p -> {
                int leftover = p.size() - 1;

                if (leftover > 2) {
                    p.reduceDown();
                }
                else { // paletteColor must have 2 colors
                    PaletteColor paletteColor = p.get(0);
                    paletteColor.getDarkened();
                    p.add(paletteColor);
                }
            })
    );

    public BlockBehaviour.Properties copyStrippedLogProperties(WoodType woodType) {
        return Utils.copyPropertySafe(Objects.requireNonNull(woodType.getBlockOfThis(STRIPPED_LOG)));
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, handler) -> {
            String PlankedLogFilename = "planked_oak_log";
            ResourceLocation innerSideM_ResLoc = EveryCompat.res(PlankedLogFilename.concat("_inner_m")).withPrefix("block/ch/");
            ResourceLocation outerSideM_ResLoc = EveryCompat.res(PlankedLogFilename.concat("_outer_m")).withPrefix("block/ch/");
            ResourceLocation innerTopM_ResLoc = EveryCompat.res(PlankedLogFilename.concat("_top_inner_m")).withPrefix("block/ch/");
            ResourceLocation outerTopM_ResLoc = EveryCompat.res(PlankedLogFilename.concat("_top_outer_m")).withPrefix("block/ch/");

            //REASON: The generated textures are not correct, so below is the best way to get the correct generated texture
            createLogTexture(modRes(PlankedLogFilename).withPrefix("block/oak_log/"), innerSideM_ResLoc, outerSideM_ResLoc,
                    PlankedLogFilename, "log", "", PlankedLog, "planks", "log",
                    handler, manager);

            createLogTexture(modRes(PlankedLogFilename + "_top").withPrefix("block/oak_log/"), innerTopM_ResLoc, outerTopM_ResLoc,
                    PlankedLogFilename, "log", "top", PlankedLog, "planks", "log",
                    handler, manager);
        });
    }

}