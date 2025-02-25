package net.mehvahdjukaar.every_compat.modules.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.ModConstants;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

//SUPPORT: v12.10.4+ (FABRIC) | 12.9.12+ (FORGE)
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
                        getModBlock("oak_full_drawers_1", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(1, false, Utils.copyPropertySafe(getModBlock("oak_full_drawers_1").get()))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("full_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_1"))
                .createPaletteFromPlanks(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_1"))
                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_sort"))
                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(FULL_DRAWERS_1);

        FULL_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "full_drawers_2",
                        getModBlock("oak_full_drawers_2", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(2, false, Utils.copyPropertySafe(getModBlock("oak_full_drawers_2").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("full_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_2"))
                .createPaletteFromPlanks(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_2"))
//                .addTexture(modRes("block/drawers_oak_side"))
//                .addTexture(modRes("block/drawers_oak_sort"))
//                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(FULL_DRAWERS_2);

        FULL_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "full_drawers_4",
                        getModBlock("oak_full_drawers_4", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(4, false, Utils.copyPropertySafe(getModBlock("oak_full_drawers_4").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("full_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("full_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_4"))
                .createPaletteFromPlanks(this::drawersPalette)
                .addTexture(modRes("block/drawers_oak_front_4"))
//                .addTexture(modRes("block/drawers_oak_side"))
//                .addTexture(modRes("block/drawers_oak_sort"))
//                .addTexture(modRes("block/drawers_oak_trim"))
                .build();

        this.addEntry(FULL_DRAWERS_4);

        HALF_DRAWERS_1 = SimpleEntrySet.builder(WoodType.class, "half_drawers_1",
                        getModBlock("oak_half_drawers_1", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(1, true, Utils.copyPropertySafe(getModBlock("oak_half_drawers_1").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("half_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_1"))
                .createPaletteFromPlanks(this::drawersPalette)
//                .addTexture(modRes("block/drawers_oak_front_1"))
//                .addTexture(modRes("block/drawers_oak_side"))
                .addTexture(modRes("block/drawers_oak_side_h"))
                .addTexture(modRes("block/drawers_oak_side_v"))
//                .addTexture(modRes("block/drawers_oak_sort"))
//                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(HALF_DRAWERS_1);

        HALF_DRAWERS_2 = SimpleEntrySet.builder(WoodType.class, "half_drawers_2",
                        getModBlock("oak_half_drawers_2", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(2, true, Utils.copyPropertySafe(getModBlock("oak_half_drawers_2").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("half_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_2"))
                .createPaletteFromPlanks(this::drawersPalette)
//                .addTexture(modRes("block/drawers_oak_front_2"))
//                .addTexture(modRes("block/drawers_oak_side"))
//                .addTexture(modRes("block/drawers_oak_side_h"))
//                .addTexture(modRes("block/drawers_oak_side_v"))
//                .addTexture(modRes("block/drawers_oak_sort"))
//                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(HALF_DRAWERS_2);

        HALF_DRAWERS_4 = SimpleEntrySet.builder(WoodType.class, "half_drawers_4",
                        getModBlock("oak_half_drawers_4", BlockStandardDrawers.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStandardDrawers(4, true, Utils.copyPropertySafe(getModBlock("oak_half_drawers_4").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("half_drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .addTag(modRes("half_drawers"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addTile(getModTile("standard_drawers_4"))
                .createPaletteFromPlanks(this::drawersPalette)
//                .addTexture(modRes("block/drawers_oak_front_4"))
//                .addTexture(modRes("block/drawers_oak_side"))
//                .addTexture(modRes("block/drawers_oak_side_h"))
//                .addTexture(modRes("block/drawers_oak_side_v"))
//                .addTexture(modRes("block/drawers_oak_sort"))
//                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(HALF_DRAWERS_4);

        TRIMS = SimpleEntrySet.builder(WoodType.class, "trim",
                        getModBlock("oak_trim", BlockTrim.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockTrim(Utils.copyPropertySafe(getModBlock("oak_trim").get())))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .createPaletteFromPlanks(this::trimPalette)
                .addTexture(modRes("block/drawers_oak_trim"))
                .build();
        this.addEntry(TRIMS);
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
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        ModDrawersGeometry.loadGeometryData(this, manager);
    }

    private <B extends Block> Stream<B> getBlocksOfType(Class<B> blockClass) {
        Stream<Block> allBlocks = this.getEntries().stream().map(e-> ((SimpleEntrySet<?, B>) e)
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