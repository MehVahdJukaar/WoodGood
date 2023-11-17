package net.mehvahdjukaar.every_compat.modules.forge.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import com.jaquadro.minecraft.storagedrawers.block.tile.BlockEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.BlockEntityDrawersStandard;
import com.jaquadro.minecraft.storagedrawers.client.renderer.BlockEntityDrawersRenderer;
import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


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
      var  tab = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation("storagedrawers", "storagedrawers"));

        FULL_DRAWERS_1 = SimpleEntrySet.builder(WoodType.class, "full_drawers_1",
                        ModBlocks.OAK_FULL_DRAWERS_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(1, false, Utils.copyPropertySafe(ModBlocks.OAK_FULL_DRAWERS_1.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatStandardDrawersEntity1::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_1"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(FULL_DRAWERS_1);

        FULL_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "full_drawers_2",
                        ModBlocks.OAK_FULL_DRAWERS_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(2, false, Utils.copyPropertySafe(ModBlocks.OAK_FULL_DRAWERS_2.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatStandardDrawersEntity2::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_2"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(FULL_DRAWERS_2);

        FULL_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "full_drawers_4",
                        ModBlocks.OAK_FULL_DRAWERS_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(4, false, Utils.copyPropertySafe(ModBlocks.OAK_FULL_DRAWERS_4.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatStandardDrawersEntity4::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_4"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(FULL_DRAWERS_4);

        HALF_DRAWERS_1 = SimpleEntrySet.builder(WoodType.class, "half_drawers_1",
                        ModBlocks.OAK_HALF_DRAWERS_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(1, true, Utils.copyPropertySafe(ModBlocks.OAK_HALF_DRAWERS_1.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatHalfDrawersEntity4::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_1"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_side_h"))
                .addTexture(modRes("block/drawers_oak_side_v"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(HALF_DRAWERS_1);

        HALF_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "half_drawers_2",
                        ModBlocks.OAK_HALF_DRAWERS_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(2, true, Utils.copyPropertySafe(ModBlocks.OAK_HALF_DRAWERS_2.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatHalfDrawersEntity4::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_2"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_side_h"))
                .addTexture(modRes("block/drawers_oak_side_v"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(HALF_DRAWERS_2);

        HALF_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "half_drawers_4",
                        ModBlocks.OAK_HALF_DRAWERS_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatStandardDrawers(4, true, Utils.copyPropertySafe(ModBlocks.OAK_HALF_DRAWERS_4.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTabKey(()->tab)
                .defaultRecipe()
                .addTile(CompatHalfDrawersEntity4::new)
                .createPaletteFromOak(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_4"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_side_h"))
                .addTexture(modRes("block/drawers_oak_side_v"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(HALF_DRAWERS_4);

        TRIMS = SimpleEntrySet.builder(WoodType.class, "trim",
                        ModBlocks.OAK_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockTrim(Utils.copyPropertySafe(ModBlocks.OAK_TRIM.get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(()->tab)
                .defaultRecipe()
                .createPaletteFromOak(this::trimPalette)
                .addTexture(modRes("block/drawers_oak_trim"))
                .addModelTransform(m -> m.replaceGenericType("oak", "blocks"))
                .build();

        this.addEntry(TRIMS);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        initDrawerClientData(FULL_DRAWERS_1.blocks.values(), FULL_DRAWERS_1.getBaseBlock());
        initDrawerClientData(FULL_DRAWERS_2.blocks.values(), FULL_DRAWERS_2.getBaseBlock());
        initDrawerClientData(FULL_DRAWERS_4.blocks.values(), FULL_DRAWERS_4.getBaseBlock());
        initDrawerClientData(HALF_DRAWERS_1.blocks.values(), HALF_DRAWERS_1.getBaseBlock());
        initDrawerClientData(HALF_DRAWERS_2.blocks.values(), HALF_DRAWERS_2.getBaseBlock());
        initDrawerClientData(HALF_DRAWERS_4.blocks.values(), HALF_DRAWERS_4.getBaseBlock());
    }

    private void initDrawerClientData(Collection<? extends BlockDrawers> drawers, BlockDrawers base){
        drawers.forEach((b)->{
            System.arraycopy(base.labelGeometry, 0, b.labelGeometry, 0, base.labelGeometry.length);
            System.arraycopy(base.countGeometry, 0, b.countGeometry, 0, base.countGeometry.length);
            System.arraycopy(base.indBaseGeometry, 0, b.indBaseGeometry, 0, base.indBaseGeometry.length);
            System.arraycopy(base.slotGeometry, 0, b.slotGeometry, 0, base.slotGeometry.length);
            System.arraycopy(base.indGeometry, 0, b.indGeometry, 0, base.indGeometry.length);
        });
    }

    private void drawersPalette(Palette p) {
        p.remove(p.getLightest());
        p.increaseInner();
        p.increaseInner();
        p.increaseInner();
        p.increaseUp();
    }

    private void trimPalette(Palette p) {
        p.remove(p.getLightest());
        p.increaseInner();
        p.increaseUp();
    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        //TODO: somehow put this in builder instead
        FULL_DRAWERS_1.registerTileRenderer(event, BlockEntityDrawersRenderer::new);
        event.register((BlockEntityType<CompatStandardDrawersEntity2>) (FULL_DRAWERS_2.getTileHolder().get()), BlockEntityDrawersRenderer::new);
        event.register((BlockEntityType<CompatStandardDrawersEntity4>) (FULL_DRAWERS_4.getTileHolder().get()), BlockEntityDrawersRenderer::new);
        event.register((BlockEntityType<CompatHalfDrawersEntity1>) (HALF_DRAWERS_1.getTileHolder().get()), BlockEntityDrawersRenderer::new);
        event.register((BlockEntityType<CompatHalfDrawersEntity2>) (HALF_DRAWERS_2.getTileHolder().get()), BlockEntityDrawersRenderer::new);
        event.register((BlockEntityType<CompatHalfDrawersEntity4>) (HALF_DRAWERS_4.getTileHolder().get()), BlockEntityDrawersRenderer::new);
    }

    private class CompatStandardDrawers extends BlockStandardDrawers {
        public CompatStandardDrawers(int drawerCount, boolean halfDepth, Properties properties) {
            super(drawerCount, halfDepth, properties);
        }

        public BlockEntityDrawers newBlockEntity(BlockPos pos, BlockState state) {
            if (this.isHalfDepth()) {
                return switch (this.getDrawerCount()) {
                    case 1 -> new CompatHalfDrawersEntity1(pos, state);
                    case 2 -> new CompatHalfDrawersEntity2(pos, state);
                    case 4 -> new CompatHalfDrawersEntity4(pos, state);
                    default -> null;
                };
            }
            return switch (this.getDrawerCount()) {
                case 1 -> new CompatStandardDrawersEntity1(pos, state);
                case 2 -> new CompatStandardDrawersEntity2(pos, state);
                case 4 -> new CompatStandardDrawersEntity4(pos, state);
                default -> null;
            };
        }
    }

    class CompatStandardDrawersEntity1 extends BlockEntityDrawersStandard.Slot1 {

        public CompatStandardDrawersEntity1(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return FULL_DRAWERS_1.getTile(BlockEntity.class);
        }
    }

    class CompatStandardDrawersEntity2 extends BlockEntityDrawersStandard.Slot2 {

        public CompatStandardDrawersEntity2(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return FULL_DRAWERS_2.getTileHolder().get();
        }
    }

    class CompatStandardDrawersEntity4 extends BlockEntityDrawersStandard.Slot4 {

        public CompatStandardDrawersEntity4(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return FULL_DRAWERS_4.getTileHolder().get();
        }

    }

    class CompatHalfDrawersEntity1 extends BlockEntityDrawersStandard.Slot1 {

        public CompatHalfDrawersEntity1(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return HALF_DRAWERS_1.getTileHolder().get();
        }
    }

    class CompatHalfDrawersEntity2 extends BlockEntityDrawersStandard.Slot2 {

        public CompatHalfDrawersEntity2(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return HALF_DRAWERS_2.getTileHolder().get();
        }
    }

    class CompatHalfDrawersEntity4 extends BlockEntityDrawersStandard.Slot4 {

        public CompatHalfDrawersEntity4(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return HALF_DRAWERS_4.getTileHolder().get();
        }

    }
}