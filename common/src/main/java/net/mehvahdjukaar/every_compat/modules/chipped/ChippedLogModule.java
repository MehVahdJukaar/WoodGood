package net.mehvahdjukaar.every_compat.modules.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
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
import java.util.Set;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.*;
import static net.mehvahdjukaar.every_compat.misc.UtilityTexture.*;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.LOG;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//See ChippedAbstractModule's SUPPORTED VERSION
public class ChippedLogModule extends ChippedModuleAbstract {

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
        setBlockType("Log");

        BundledLog = SimpleEntrySet.builder(WoodType.class, "log", "bundled",
                        getModBlock("bundled_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(BundledLog);

        CenterCutLog = SimpleEntrySet.builder(WoodType.class, "log", "center_cut",
                        getModBlock("center_cut_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(CenterCutLog);

        DamagedLog = SimpleEntrySet.builder(WoodType.class, "log", "damaged",
                        getModBlock("damaged_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/damaged_oak_log_top"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(DamagedLog);

        EdgeCutLog = SimpleEntrySet.builder(WoodType.class, "log", "edge_cut",
                        getModBlock("edge_cut_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(EdgeCutLog);

        FirewoodLog = SimpleEntrySet.builder(WoodType.class, "log", "firewood",
                        getModBlock("firewood_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/firewood_oak_log"), LOG_SIDE_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FirewoodLog);

        FloweringLog = SimpleEntrySet.builder(WoodType.class, "log", "flowering",
                        getModBlock("flowering_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/flowering_oak_log"),
                        EveryCompat.res("block/ch/oak_logs/flowering_oak_log_m"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTextureM(modRes("block/oak_log/flowering_oak_log_top"),
                        EveryCompat.res("block/common_log_top_outer_m"), PaletteStrategies.PLANKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FloweringLog);

        MixedLog = SimpleEntrySet.builder(WoodType.class, "log", "mixed",
                        getModBlock("mixed_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/mixed_oak_log"), LOG_SIDE_REMOVE_2_DARKEST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(MixedLog);

        NailedLog = SimpleEntrySet.builder(WoodType.class, "log", "nailed",
                        getModBlock("nailed_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: manually generated (BELOW)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(NailedLog);

        OvergrownLog = SimpleEntrySet.builder(WoodType.class, "log", "overgrown",
                        getModBlock("overgrown_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/overgrown_oak_log"),
                        EveryCompat.res("block/ch/oak_logs/overgrown_oak_log_m"), PaletteStrategies.LOG_SIDE_STANDARD)
                //TEXTURES: manually generated (BELOW)
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
                //TEXTURES: manually generated (BELOW)
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
                .addTexture(modRes("block/stripped_oak_log/carved_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/carved_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(carvedStrippedLog);

        ChippedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "chipped_stripped",
                        getModBlock("chipped_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/chipped_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/chipped_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ChippedStrippedLog);

        DSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "d_sign_stripped",
                        getModBlock("d_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/d_sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/d_sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(DSignStrippedLog);

        edgedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "edged_stripped",
                        getModBlock("edged_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/edged_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/edged_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(edgedStrippedLog);

        FSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "f_sign_stripped",
                        getModBlock("f_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/f_sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/f_sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FSignStrippedLog);

        ISignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "i_sign_stripped",
                        getModBlock("i_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/i_sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/i_sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ISignStrippedLog);

        KSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "k_sign_stripped",
                        getModBlock("k_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/k_sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/k_sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(KSignStrippedLog);

        knottedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "knotted_stripped",
                        getModBlock("knotted_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/knotted_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/knotted_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(knottedStrippedLog);

        LSignStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "l_sign_stripped",
                        getModBlock("l_sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/l_sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/l_sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LSignStrippedLog);

        LayeredStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "layered_stripped",
                        getModBlock("layered_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/layered_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/layered_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LayeredStrippedLog);

        LumpyStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "lumpy_stripped",
                        getModBlock("lumpy_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/lumpy_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/lumpy_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(LumpyStrippedLog);

        PatientStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "patient_stripped",
                        getModBlock("patient_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/patient_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/patient_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(PatientStrippedLog);

        ReinforcedStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "reinforced_stripped",
                        getModBlock("reinforced_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTextureM(modRes("block/stripped_oak_log/reinforced_stripped_oak_log"),
                        EveryCompat.res("block/ch/stripped_oak_logs/reinforced_stripped_oak_log_m"),
                        STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/reinforced_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ReinforcedStrippedLog);

        signStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "sign_stripped",
                        getModBlock("sign_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/sign_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/sign_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(signStrippedLog);

        sternStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "stern_stripped",
                        getModBlock("stern_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/stern_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/stern_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(sternStrippedLog);

        wiseStrippedLog = SimpleEntrySet.builder(WoodType.class, "log", "wise_stripped",
                        getModBlock("wise_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(copyStrippedLogProperties(w))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(modRes("block/stripped_oak_log/wise_stripped_oak_log"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTexture(modRes("block/stripped_oak_log/wise_stripped_oak_log_top"), STRIPPED_LOG_LIGHT_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(wiseStrippedLog);

    }

    public static final PaletteStrategy DAMAGED_PALETTE = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, VanillaWoodChildKeys.LOG, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE,
                    p -> {
                        if (p.size() > 3) {
                            p.reduceDown();
                        }
                        if (p.size() < 4) p.increaseUp();
                    }));

    public static final PaletteStrategy LOG_SIDE_LIGHT_PALETTE = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, VanillaWoodChildKeys.LOG, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE,
                    p -> {
                        if (p.size() > 3) {
                            p.reduceDown();
                            p.reduceDown();
                        }
                        if (p.size() < 4) p.increaseInner();
                    }));

    public static final PaletteStrategy STRIPPED_LOG_LIGHT_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, STRIPPED_LOG, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE, p -> {
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

        String cLogTopM = "block/common_log_top_outer_m";
        String cLogTopPlanksM = "block/common_log_top_inner_m";

        String xxLogM = "block/ch/oak_logs/x_x_log_m";
        String xxLogPlanksM = "block/ch/oak_logs/x_x_log_planks_m";

        String xLogTopM = "block/ch/oak_logs/x_log_top_m";
        String xLogTopPlanksM = "block/ch/oak_logs/x_log_top_planks_m";

        //REASON: The generated textures are not correct, so below is the best way to get the correct generated texture
        executor.accept((manager, sink) -> {

            Set<TextureGroup> textures = Set.of(
                    // planked_oak_log
                    TextureGroup.of("planked_oak_log", xxLogM, xxLogPlanksM, LOG_SIDE_LIGHT_PALETTE),
                    TextureGroup.of("planked_oak_log_top", cLogTopM, cLogTopPlanksM, LOG_SIDE_LIGHT_PALETTE),

                    // nailed_oak_log
                    TextureGroup.of("nailed_oak_log", "block/ch/oak_logs/nailed_oak_log_m",
                            "", null),
                    TextureGroup.of("nailed_oak_log_top", "block/ch/oak_logs/edge_cut_log_top_m",
                            "block/ch/oak_logs/center_cut_log_top_m",
                            LOG_SIDE_STANDARD),

                    // overgrown_oak_log_top
                    TextureGroup.of("overgrown_oak_log_top", cLogTopM,
                            "block/ch/oak_logs/overgrown_oak_log_top_planks_m",
                            LOG_SIDE_LIGHT_PALETTE),

                    // reinforced_oak_log
                    TextureGroup.of("reinforced_oak_log", "block/ch/oak_logs/reinforced_oak_log_m",
                            "", null),
                    TextureGroup.of("reinforced_oak_log_top", "block/ch/oak_logs/edge_cut_log_top_m",
                            "block/ch/oak_logs/center_cut_log_top_m",
                            LOG_SIDE_STANDARD),

                    // bundled_oak_log
                    TextureGroup.of("bundled_oak_log", "block/ch/oak_logs/bundled_log_m",
                            "block/ch/oak_logs/bundled_log_planks_m",
                            LOG_SIDE_REMOVE_2_DARKEST),
                    TextureGroup.of("bundled_oak_log_top", xLogTopM, xLogTopPlanksM,
                            PaletteStrategies.removeDarkestBy(2, LOG, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)),

                    // center_cut_oak_log
                    TextureGroup.of("center_cut_oak_log", xxLogPlanksM, xxLogM,
                            LOG_SIDE_LIGHT_PALETTE),
                    TextureGroup.of("center_cut_oak_log_top", "block/ch/oak_logs/center_cut_log_top_m",
                            "block/ch/oak_logs/center_cut_log_top_planks_m",
                            LOG_SIDE_LIGHT_PALETTE),

                    // damaged_oak_log
                    TextureGroup.of("damaged_oak_log", "block/ch/oak_logs/damaged_oak_log_m",
                            "block/ch/oak_logs/damaged_oak_log_planks_m", DAMAGED_PALETTE),

                    // edge_cut_oak_log
                    TextureGroup.of("edge_cut_oak_log", xxLogM, xxLogPlanksM,
                            LOG_SIDE_LIGHT_PALETTE),
                    TextureGroup.of("edge_cut_oak_log_top", "block/ch/oak_logs/edge_cut_log_top_m",
                            "block/ch/oak_logs/edge_cut_log_top_planks_m",
                            LOG_SIDE_LIGHT_PALETTE),

                    // firewood_oak_log_top
                    TextureGroup.of("firewood_oak_log_top", xLogTopM, xLogTopPlanksM, LOG_SIDE_LIGHT_PALETTE),

                    // mixed_oak_log_top
                    TextureGroup.of("mixed_oak_log_top", xLogTopM, xLogTopPlanksM, LOG_SIDE_REMOVE_2_DARKEST)
            );

            textures.forEach(currentTextures -> {
                if (currentTextures.logPaletteStrategy() == null)
                    applyLogOverlay(
                            modRes(currentTextures.baseTexture()).withPrefix("block/oak_log/"),
                            EveryCompat.res(currentTextures.logMask()),
                            shortenedId(), "oak", sink, manager
                    );
                //REGEX: excluded edge_cut_oak_log_top & center_cut_oak_log_top
                else if (currentTextures.baseTexture().matches("^(?!\\w+cut_)\\w+_top$")
                        || currentTextures.baseTexture().contains("bundled_")
                )
                    generateLogTexture(
                            modRes(currentTextures.baseTexture()).withPrefix("block/oak_log/"),
                            EveryCompat.res(currentTextures.logMask()), EveryCompat.res(currentTextures.planksMask()),
                            shortenedId(), "oak", currentTextures.logPaletteStrategy(),
                            sink, manager
                    );
                else
                    applyLogOverlayAndswapPlanks(
                            modRes(currentTextures.baseTexture()).withPrefix("block/oak_log/"),
                            EveryCompat.res(currentTextures.logMask()), EveryCompat.res(currentTextures.planksMask()),
                            shortenedId(), "oak",
                            sink, manager
                    );
            });

        });
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            addCarpenterRecipe(sink, "log");
            addCarpenterRecipe(sink, "stripped_log");
        });
    }



    private record TextureGroup(String baseTexture, String logMask, String planksMask, PaletteStrategy logPaletteStrategy) {
        public static TextureGroup of(String baseTexture, String logMask, String planksMask, PaletteStrategy logPaletteStrategy) {
            return new TextureGroup(baseTexture, logMask, planksMask, logPaletteStrategy);
        }
    }
}