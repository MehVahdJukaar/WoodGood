package net.mehvahdjukaar.every_compat.modules.storage_drawers;

import com.jaquadro.minecraft.storagedrawers.ModConstants;
import com.jaquadro.minecraft.storagedrawers.api.config.IDrawerConfig;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import com.jaquadro.minecraft.storagedrawers.config.ModCommonConfig;
import com.jaquadro.minecraft.storagedrawers.item.ItemDrawers;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;

///SUPPORT: v12.15.1+
public class StorageDrawersModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BlockStandardDrawers> FULL_DRAWERS_1;
    public final SimpleEntrySet<WoodType, BlockStandardDrawers> FULL_DRAWERS_2;
    public final SimpleEntrySet<WoodType, BlockStandardDrawers> FULL_DRAWERS_4;
    public final SimpleEntrySet<WoodType, BlockStandardDrawers> HALF_DRAWERS_1;
    public final SimpleEntrySet<WoodType, BlockStandardDrawers> HALF_DRAWERS_2;
    public final SimpleEntrySet<WoodType, BlockStandardDrawers> HALF_DRAWERS_4;
    public final SimpleEntrySet<WoodType, BlockTrim> TRIMS;

    public StorageDrawersModule(String modId) {
        super(modId, "sd");
        ResourceLocation tab = modRes(ModConstants.MOD_ID);

        FULL_DRAWERS_1 = SimpleEntrySet.builder(WoodType.class, "full_drawers_1",
                        getModBlock("oak_full_drawers_1", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 1;
                            boolean halfDepth = false;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_1"))
                .addTexture(modRes("block/drawers_oak_front_1"), DRAWERS_PALETTE)
                .addTexture(modRes("block/drawers_oak_side"), DRAWERS_PALETTE)
                .addTexture(modRes("block/drawers_oak_sort"), DRAWERS_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();
        this.addEntry(FULL_DRAWERS_1);

        FULL_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "full_drawers_2",
                        getModBlock("oak_full_drawers_2", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 2;
                            boolean halfDepth = false;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_2"))
                .addTexture(modRes("block/drawers_oak_front_2"), DRAWERS_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();
        this.addEntry(FULL_DRAWERS_2);

        FULL_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "full_drawers_4",
                        getModBlock("oak_full_drawers_4", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 4;
                            boolean halfDepth = false;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_4"))
                .addTexture(modRes("block/drawers_oak_front_4"), DRAWERS_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();

        this.addEntry(FULL_DRAWERS_4);

        HALF_DRAWERS_1 = SimpleEntrySet.builder(WoodType.class, "half_drawers_1",
                        getModBlock("oak_half_drawers_1", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 1;
                            boolean halfDepth = true;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_1"))
                .addTexture(modRes("block/drawers_oak_side_h"), DRAWERS_PALETTE)
                .addTexture(modRes("block/drawers_oak_side_v"), DRAWERS_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();
        this.addEntry(HALF_DRAWERS_1);

        HALF_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "half_drawers_2",
                        getModBlock("oak_half_drawers_2", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 2;
                            boolean halfDepth = true;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();
        this.addEntry(HALF_DRAWERS_2);

        HALF_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "half_drawers_4",
                        getModBlock("oak_half_drawers_4", BlockStandardDrawers.class), () -> VanillaWoodTypes.OAK,
                        w -> {
                            int drawerCount = 4;
                            boolean halfDepth = true;
                            Supplier<IDrawerConfig> config = getStandardConfig(drawerCount, halfDepth);
                            return new BlockStandardDrawers(drawerCount, halfDepth, config, getWoodenDrawerBlockProperties());
                        }
                )
                .addTile(getModTile("standard_drawers_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new ItemDrawers(block, properties))
                .build();
        this.addEntry(HALF_DRAWERS_4);

        TRIMS = SimpleEntrySet.builder(WoodType.class, "trim",
                        getModBlock("oak_trim", BlockTrim.class), () -> VanillaWoodTypes.OAK,
                        w -> new BlockTrim(Utils.copyPropertySafe(getModBlock("oak_trim").get()))
                )
                .addTexture(modRes("block/drawers_oak_trim"), TRIM_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("trim"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(TRIMS);
    }

    private static final PaletteStrategy DRAWERS_PALETTE = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(blockType, manager, VanillaWoodChildKeys.PLANKS, null,
                    (p) -> {
                        p.reduceDown();
                        p.reduceDown();
                        float averageStep = p.getAverageLuminanceStep();
                        p.matchLuminanceStep(averageStep * 0.5f);
                    }
            ));

    private static final PaletteStrategy TRIM_PALETTE = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(blockType, manager, VanillaWoodChildKeys.PLANKS, null,
                    (p) -> {
                        float averageStep = p.getAverageLuminanceStep();
                        //lower step = lower contrast. Tweak as needed
                        p.matchLuminanceStep(averageStep * 0.5f);
                        p.reduceDown();
                        for (int idx = 0; idx < p.size(); idx++) {
                            PaletteColor paletteColor = p.get(idx);
                            HCLColor color = paletteColor.hcl().withLuminance(paletteColor.luminance() * 0.80F);
                            p.set(idx, new PaletteColor(color));
                        }
                    }
            ));

    static BlockBehaviour.Properties getWoodenDrawerBlockProperties() {
        return BlockBehaviour.Properties.of()
                .sound(SoundType.WOOD)
                .strength(3.0F, 4.0F)
                .isSuffocating(StorageDrawersModule::predFalse)
                .isRedstoneConductor(StorageDrawersModule::predFalse);
    }

    private static Supplier<IDrawerConfig> getStandardConfig(int drawerCount, boolean halfDepth) {
        return () -> {
            ModCommonConfig.Drawers base = ModCommonConfig.INSTANCE.DRAWERS;
            if (drawerCount == 1) {
                return halfDepth ? base.halfDrawers1x1 : base.fullDrawers1x1;
            } else if (drawerCount == 2) {
                return halfDepth ? base.halfDrawers1x2 : base.fullDrawers1x2;
            } else if (drawerCount == 4) {
                return halfDepth ? base.halfDrawers2x2 : base.fullDrawers2x2;
            } else {
                return null;
            }
        };
    }

    private static boolean predFalse(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                      Dynamic Resources                                       │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    @Override
    // DATA
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, s) -> ModDrawersGeometry.loadGeometryData(this, manager));
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           OnClient                                           │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    @Override
    public void onClientInit() {
        super.onClientInit();
        ModDrawersModels.registerDrawerModelDecoration(this);
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                  ModDrawersGeometry methods                                  │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    private <B extends Block> Stream<B> getBlocksOfType(Class<B> blockClass) {
        Stream<Block> allBlocks = this.getEntries().stream().map(e -> ((SimpleEntrySet<?, B>) e)
                .blocks.values()).flatMap(Collection::stream);
        Objects.requireNonNull(blockClass);
        allBlocks = allBlocks.filter(blockClass::isInstance);
        Objects.requireNonNull(blockClass);
        return allBlocks.map(blockClass::cast);
    }

    public <BD extends BlockDrawers> Stream<BD> getDrawersOfType(Class<BD> drawerClass) {
        return getBlocksOfType(drawerClass);
    }

    public <BD extends BlockDrawers> Stream<BD> getDrawersOfTypeAndSize(Class<BD> drawerClass, int size) {
        return getDrawersOfType(drawerClass).filter((blockDrawers) -> blockDrawers.getDrawerCount() == size);
    }

    public <BD extends BlockDrawers> Stream<BD> getDrawersOfTypeAndSizeAndDepth(Class<BD> drawerClass, int size, boolean halfDepth) {
        return getDrawersOfTypeAndSize(drawerClass, size).filter((blockDrawers) -> blockDrawers.isHalfDepth() == halfDepth);
    }
}