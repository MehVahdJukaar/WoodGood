package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import earth.terrarium.handcrafted.client.block.counter.ShelfRenderer;
import earth.terrarium.handcrafted.common.block.ItemHoldingBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlock;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlock;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlock;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.woodenbench.WoodenBenchBlock;
import earth.terrarium.handcrafted.common.block.chair.woodenbench.WoodenBenchBlockEntity;
import earth.terrarium.handcrafted.common.block.counter.*;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlock;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlockEntity;
import earth.terrarium.handcrafted.common.block.property.DirectionalBlockSide;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlock;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlockEntity;
import earth.terrarium.handcrafted.common.block.table.nightstand.NightstandBlock;
import earth.terrarium.handcrafted.common.block.table.nightstand.NightstandBlockEntity;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlock;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlockEntity;
import earth.terrarium.handcrafted.common.block.table.table.TableBlock;
import earth.terrarium.handcrafted.common.block.table.table.TableBlockEntity;
import earth.terrarium.handcrafted.common.block.trim.CornerTrimBlock;
import earth.terrarium.handcrafted.common.block.trim.TrimBlock;
import earth.terrarium.handcrafted.common.item.*;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import earth.terrarium.handcrafted.common.registry.ModTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.handcrafted.client.*;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@SuppressWarnings("DataFlowIssue")
//SUPPORT: v2.0.6+
public class HandcraftedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> bench;
    public final SimpleEntrySet<WoodType, Block> couch;
    public final SimpleEntrySet<WoodType, Block> fancyBed;
    public final SimpleEntrySet<WoodType, Block> diningBench;
    public final SimpleEntrySet<WoodType, Block> nightstand;
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> sideTable;

    public final ItemOnlyEntrySet<WoodType, Item> counter;
    public final SimpleEntrySet<WoodType, Block> counter_1, counter_2, counter_3;

    public final ItemOnlyEntrySet<WoodType, Item> cupboard;
    public final SimpleEntrySet<WoodType, Block> cupboard_1, cupboard_2;

    public final ItemOnlyEntrySet<WoodType, Item> drawer;
    public final SimpleEntrySet<WoodType, Block> drawer_1, drawer_2, drawer_3, drawer_4;

    public final ItemOnlyEntrySet<WoodType, Item> shelf;
    public final SimpleEntrySet<WoodType, Block> shelf_1;

    public final SimpleEntrySet<WoodType, Block> pillarTrim;
    public final SimpleEntrySet<WoodType, Block> cornerTrim;

    public final ItemOnlyEntrySet<WoodType, Item> board;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        CreativeModeTab tab = ModItems.ITEM_GROUP;

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatChairEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/chair/chair/oak_chair"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.ChairItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(chair);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatTableEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.TABLE_ATTACHMENTS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/table/table/oak_table"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.TableItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(table);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        ModBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatBenchEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("benches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("benches"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/chair/bench/oak_bench"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.BenchItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(bench);

        couch = SimpleEntrySet.builder(WoodType.class, "couch",
                        ModBlocks.OAK_COUCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatCouchEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("couches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("couches"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/chair/couch/oak_couch"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.CouchItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(couch);

        fancyBed = SimpleEntrySet.builder(WoodType.class, "fancy_bed",
                        ModBlocks.OAK_FANCY_BED, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatFancyBedBlock(Utils.copyPropertySafe(Blocks.WHITE_BED)))
                .addTile(CompatFancyBedEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("fancy_beds"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("fancy_beds"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.BEDS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/bed/single/oak_fancy_bed"))
                .addTexture(modRes("block/bed/double/oak_fancy_bed"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.FancyBedItem(b, p))
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(fancyBed);

        diningBench = SimpleEntrySet.builder(WoodType.class, "dining_bench",
                        ModBlocks.OAK_DINING_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatDiningBenchEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("dining_benches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("dining_benches"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/chair/dining_bench/oak_dining_bench"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.DiningBenchItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(diningBench);

        nightstand = SimpleEntrySet.builder(WoodType.class, "nightstand",
                        ModBlocks.OAK_NIGHTSTAND, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatNightstandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatNightstandEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.TABLE_ATTACHMENTS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("nightstands"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nightstands"), Registry.ITEM_REGISTRY)
                .addTextureM(modRes("block/table/nightstand/oak_nightstand"), EveryCompat.res("block/hc/table/oak_nightstand_m"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.NightstandItem(b, p))
                .defaultRecipe()
                .build();

        this.addEntry(nightstand);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDeskBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatDeskEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.TABLE_ATTACHMENTS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("desks"), Registry.ITEM_REGISTRY)
                .addTextureM(modRes("block/table/desk/oak_desk"), EveryCompat.res("block/hc/table/oak_desk_m"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CompatModItems.DeskItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(desk);

        sideTable = SimpleEntrySet.builder(WoodType.class, "side_table",
                        ModBlocks.OAK_SIDE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatSideTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(CompatSideTableEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("side_tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("side_tables"), Registry.ITEM_REGISTRY)
                .addTextureM(modRes("block/table/side_table/oak_side_table"), EveryCompat.res("block/hc/table/oak_side_table_m"))
                .setTab(() -> tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new CompatModItems.SideTableItem(b, p))
                .build();
        this.addEntry(sideTable);

        counter_1 = SimpleEntrySet.builder(WoodType.class, "counter_1",
                        ModBlocks.OAK_COUNTER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatCounterEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counters"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/counter/oak_counter_1"), EveryCompat.res("block/hc/counter/oak_counter_1_m"))
                .noItem()
                .build();
        this.addEntry(counter_1);

        counter_2 = SimpleEntrySet.builder(WoodType.class, "counter_2",
                        ModBlocks.OAK_COUNTER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(counter_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counters"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/counter/oak_counter_2"), EveryCompat.res("block/hc/counter/oak_counter_2_m"))
                .noItem()
                .build();
        this.addEntry(counter_2);

        counter_3 = SimpleEntrySet.builder(WoodType.class, "counter_3",
                        ModBlocks.OAK_COUNTER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(counter_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counters"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/counter/oak_counter_3"), EveryCompat.res("block/hc/counter/oak_counter_3_m"))
                .noItem()
                .build();
        this.addEntry(counter_3);

        counter = ItemOnlyEntrySet.builder(WoodType.class, "counter",
                        ModItems.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatModItems.CounterItem(counter_1.blocks.get(w), new Item.Properties().tab(tab))
                )
                .addTag(modRes("counters"), Registry.ITEM_REGISTRY)
                .addCondition(counter_1.blocks::containsKey)
                .addRecipe(modRes("oak_counter"))
                .build();
        this.addEntry(counter);

        cupboard_1 = SimpleEntrySet.builder(WoodType.class, "cupboard_1",
                        ModBlocks.OAK_CUPBOARD_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatStorageEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("cupboards"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/cupboard/oak/cupboard_1"), EveryCompat.res("block/hc/cupboard/cupboard_1_m"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(cupboard_1);

        cupboard_2 = SimpleEntrySet.builder(WoodType.class, "cupboard_2",
                        ModBlocks.OAK_CUPBOARD_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("cupboards"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/cupboard/oak/cupboard_2"), EveryCompat.res("block/hc/cupboard/cupboard_2_m"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(cupboard_2);

        cupboard = ItemOnlyEntrySet.builder(WoodType.class, "cupboard",
                        ModItems.OAK_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HammerableBlockItem(cupboard_1.blocks.get(w), new Item.Properties().tab(tab))
                )
                .addTag(modRes("cupboards"), Registry.ITEM_REGISTRY)
                .addCondition(cupboard_1.blocks::containsKey)
                .addRecipe(modRes("oak_counter"))
                .build();
        this.addEntry(cupboard);

        drawer_1 = SimpleEntrySet.builder(WoodType.class, "drawer_1",
                        ModBlocks.OAK_DRAWER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawers"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/drawer/oak/front_1/drawer_left"), EveryCompat.res("block/hc/drawer/drawer_1_left_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_1/drawer_middle"), EveryCompat.res("block/hc/drawer/drawer_1_middle_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_1/drawer_right"), EveryCompat.res("block/hc/drawer/drawer_1_right_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_1/drawer_single"), EveryCompat.res("block/hc/drawer/drawer_1_single_m"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(drawer_1);

        drawer_2 = SimpleEntrySet.builder(WoodType.class, "drawer_2",
                        ModBlocks.OAK_DRAWER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawers"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/drawer/oak/front_2/drawer_left"), EveryCompat.res("block/hc/drawer/drawer_2_left_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_2/drawer_middle"), EveryCompat.res("block/hc/drawer/drawer_2_middle_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_2/drawer_right"), EveryCompat.res("block/hc/drawer/drawer_2_right_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_2/drawer_single"), EveryCompat.res("block/hc/drawer/drawer_2_single_m"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(drawer_2);

        drawer_3 = SimpleEntrySet.builder(WoodType.class, "drawer_3",
                        ModBlocks.OAK_DRAWER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawers"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/drawer/oak/front_3/drawer_left"), EveryCompat.res("block/hc/drawer/drawer_3_left_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_3/drawer_middle"), EveryCompat.res("block/hc/drawer/drawer_3_middle_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_3/drawer_right"), EveryCompat.res("block/hc/drawer/drawer_3_right_m"))
                .addTextureM(modRes("block/counter/drawer/oak/front_3/drawer_single"), EveryCompat.res("block/hc/drawer/drawer_3_single_m"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(drawer_3);

        drawer_4 = SimpleEntrySet.builder(WoodType.class, "drawer_4",
                        ModBlocks.OAK_DRAWER_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatDrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawers"), Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/counter/drawer/oak/front_4/drawer"), EveryCompat.res("block/hc/drawer/drawer_4_m"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(drawer_4);

        drawer = ItemOnlyEntrySet.builder(WoodType.class, "drawer",
                        ModItems.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HammerableBlockItem(drawer_1.blocks.get(w), new Item.Properties().tab(tab))
                )
                .addTag(modRes("drawers"), Registry.ITEM_REGISTRY)
                .addCondition(cupboard_1.blocks::containsKey)
                .addRecipe(modRes("oak_drawer"))
                .build();
        this.addEntry(drawer);

        shelf_1 = SimpleEntrySet.builder(WoodType.class, "shelf_1",
                        ModBlocks.OAK_SHELF_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatShelfEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("shelves"), Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/shelf/oak/shelf_back"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_middle"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_single"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_top"))
                .setTab(() -> tab)
                .noItem()
                .build();
        this.addEntry(shelf_1);

        shelf = ItemOnlyEntrySet.builder(WoodType.class, "shelf",
                        ModItems.OAK_SHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlockItem(shelf_1.blocks.get(w), new Item.Properties().tab(tab))
                )
                .addTag(modRes("shelves"), Registry.ITEM_REGISTRY)
                .addCondition(shelf_1.blocks::containsKey)
                .addRecipe(modRes("oak_shelf"))
                .build();
        this.addEntry(shelf);

        pillarTrim = SimpleEntrySet.builder(WoodType.class, "pillar_trim",
                        ModBlocks.OAK_PILLAR_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("trims"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wood_trims"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("trims"), Registry.ITEM_REGISTRY)
                .addTag(modRes("wood_trims"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim_2"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new HammerableBlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(pillarTrim);

        cornerTrim = SimpleEntrySet.builder(WoodType.class, "corner_trim",
                        ModBlocks.OAK_CORNER_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CornerTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(() -> RenderType::cutout)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("trims"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wood_trims"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("trims"), Registry.ITEM_REGISTRY)
                .addTag(modRes("wood_trims"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/trim/corner/oak_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thicc_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thin_corner_trim"))
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new HammerableBlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(cornerTrim);

        board = ItemOnlyEntrySet.builder(WoodType.class, "board",
                        ModItems.OAK_BOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BoardItem(new Item.Properties().tab(tab))
                )
                .addTag(ModTags.BOARDS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("item/board/oak_board"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> s.replace(
            "\"handcrafted:item/board/oak_board\"",
        "\""+ EveryCompat.res("item/board/" + shortenedId() + "/" + w.getAppendableId() + "_board") + "\"" ))
                )
                .build();
        this.addEntry(board);


    }

    //TYPE: METHODS

    @Override
    // Blockstates
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        // correcting "model" via blockstates for drawers
        for (WoodType w : WoodTypeRegistry.getTypes()) {
            if (w.isVanilla()) continue;

            for (int num = 2; num < 5; num++) {
                ResourceLocation resLoc = EveryCompat.res(shortenedId() + "/" + w.getAppendableId() + "_drawer_"+num);

                try (InputStream stream = manager.getResource(ResType.BLOCKSTATES.getPath(resLoc)).orElseThrow().open()) {
                    JsonObject blockstate = RPUtils.deserializeJson(stream);

                    // Editing the json
                    String s = blockstate.toString().replaceAll("(drawer|drawer_\\w+)_1", "$1_" + num);

                    Gson gson = new GsonBuilder().create();
                    blockstate = gson.fromJson(s, JsonObject.class);

                    // Adding to the resource
                    handler.dynamicPack.addJson(resLoc, blockstate, ResType.BLOCKSTATES);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to open the file: {} with {}", resLoc, e);
                }
            }
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        event.register(((BlockEntityType) chair.getTileHolder().get()), OptimizedChairRenderer::new);
        event.register(((BlockEntityType) table.getTileHolder().get()), OptimizedTableRenderer::new);
        event.register(((BlockEntityType) bench.getTileHolder().get()), OptimizedBenchRenderer::new);
        event.register(((BlockEntityType) couch.getTileHolder().get()), OptimizedCouchRenderer::new);
        event.register(((BlockEntityType) fancyBed.getTileHolder().get()), OptimizedFancyBedRenderer::new);
        event.register(((BlockEntityType) diningBench.getTileHolder().get()), OptimizedDiningBenchRenderer::new);
        event.register((BlockEntityType) nightstand.getTileHolder().get(), OptimizedNightstandRenderer::new);
        event.register((BlockEntityType) desk.getTileHolder().get(), OptimizedDeskRenderer::new);
        event.register((BlockEntityType) sideTable.getTileHolder().get(), OptimizedSideTableRenderer::new);
        event.register((BlockEntityType) counter_1.getTileHolder().get(), OptimizedCounterRenderer::new);
        event.register((BlockEntityType) counter_2.getTileHolder().get(), OptimizedCounterRenderer::new);
        event.register((BlockEntityType) counter_3.getTileHolder().get(), OptimizedCounterRenderer::new);
        event.register((BlockEntityType) shelf_1.getTileHolder().get(), OptimizedShelfRenderer::new);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void stitchAtlasTextures(ClientPlatformHelper.AtlasTextureEvent event) {
        String hcFolder = "block/" + shortenedId() + "/";

        if (OptimizedTableRenderer.OBJECT_TO_TEXTURE.isEmpty()) {

            //==================================== CLOTHING | CUSHION ==================================================
                // CLOTHING - used by TABLE, NIGHTSTAND, DESK, SIDE_TABLE
            for (var dye : DyeColor.values()) {
                Item sheetItem = Registry.ITEM.get(this.modRes(dye.getName() + "_sheet"));
                String dyeName = dye.getName() + "_sheet";
                if (sheetItem != Items.AIR) {
                    var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(sheetItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/table/table_cloth/" + dyeName)));
                    event.addSprite(texture.texture());

                // SHEET - used by FANCY_BED
                    var bed_sheet = OptimizedFancyBedRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(sheetItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/bed/sheet/" + dyeName)));
                    event.addSprite(bed_sheet.texture());
                }
            }

                // CUSHION - used by CHAIR, BENCH, COUCH
            for (var dye : DyeColor.values()) {
                String dyeName = dye.getName() + "_cushion";
                Item cushionItem = Registry.ITEM.get(this.modRes(dyeName));

                if (cushionItem != Items.AIR) {
                    // CHAIR
                    var chairTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/chair/chair/cushion/" + dyeName))
                    );
                    event.addSprite(chairTexture.texture());

                    // BENCH
                    var benchTexture = OptimizedBenchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/chair/bench/cushion/" + dyeName))
                    );
                    event.addSprite(benchTexture.texture());

                    // COUCH
                    var couchTexture = OptimizedCouchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/chair/couch/cushion/" + dyeName))
                    );
                    event.addSprite(couchTexture.texture());

                // CUSHION - used by FANCY_BED
                    var bed_cushion = OptimizedFancyBedRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, ignored ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/bed/cushion/" + dyeName))
                    );
                    event.addSprite(bed_cushion.texture());
                }
            }
            // ======================================== TEXTURE FOR BLOCKS =============================================
                // TABLE
            for (var t : table.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/table/table/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // CHAIR
            for (var t : chair.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/chair/chair/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // BENCH
            for (var t : bench.items.values()) {
                var texture = OptimizedBenchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/chair/bench/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // COUCH
            for (var t : couch.items.values()) {
                var texture = OptimizedCouchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/chair/couch/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // FANCY BED
            for (var t : fancyBed.items.values()) {
                var singleBed = OptimizedFancyBedRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ignored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/bed/single/" + s[2]));
                });
                event.addSprite(singleBed.texture());

                var doubleBed = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ignored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/bed/double/" + s[2]));
                });
                event.addSprite(doubleBed.texture());
            }

                // DINING_BENCH
            for (var t : diningBench.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/chair/dining_bench/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // NIGHTSTAND
            for (var t : nightstand.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/table/nightstand/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // DESK
            for (var t : desk.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/table/desk/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

                // SIDE_TABLE
            for (var t : sideTable.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, ingored -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/table/side_table/" + s[2]));
                });
                event.addSprite(texture.texture());
            }


                // COUNTER
            for (var entry : counter.items.entrySet()) {
                ResourceLocation itemId = Registry.ITEM.getKey(entry.getValue());
                var itemTexture = OptimizedCounterRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(itemId, ignored -> {
                    var s = itemId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/counter/counter/" + s[2] + "_1"));
                });
                event.addSprite(itemTexture.texture());
            }

            for (var entry : counter_1.blocks.entrySet()) {
                ResourceLocation blockId = Registry.BLOCK.getKey(entry.getValue());
                var blockTexture = OptimizedCounterRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(blockId, ignored -> {
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/counter/counter/" + s[2]));
                });
                event.addSprite(blockTexture.texture());
            }

            for (var entry : counter_2.blocks.entrySet()) {
                ResourceLocation blockId = Registry.BLOCK.getKey(entry.getValue());
                var blockTexture = OptimizedCounterRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(blockId, ignored -> {
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/counter/counter/" + s[2]));
                });
                event.addSprite(blockTexture.texture());
            }

            for (var entry : counter_3.blocks.entrySet()) {
                ResourceLocation blockId = Registry.BLOCK.getKey(entry.getValue());
                var blockTexture = OptimizedCounterRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(blockId, ignored -> {
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res(hcFolder + s[1] + "/counter/counter/" + s[2]));
                });
                event.addSprite(blockTexture.texture());
            }

        }
    }


    //TYPE: Block
    public class compatChairBlock extends ChairBlock {
        public compatChairBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatChairEntity(pos, state);
        }
    }

    public class compatTableBlock extends TableBlock {
        public compatTableBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatTableEntity(pos, state);
        }
    }

    public class compatBenchBlock extends WoodenBenchBlock {
        public compatBenchBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatBenchEntity(pos, state);
        }
    }

    public class compatCouchBlock extends CouchBlock {
        public compatCouchBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatCouchEntity(pos, state);
        }
    }

    public class compatFancyBedBlock extends FancyBedBlock {
        public compatFancyBedBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatFancyBedEntity(pos, state);
        }
    }

    public class compatDiningBenchBlock extends DiningBenchBlock {
        public compatDiningBenchBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatDiningBenchEntity(pos, state);
        }
    }

    public class compatNightstandBlock extends NightstandBlock {
        public compatNightstandBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatNightstandEntity(pos, state);
        }
    }

    public class compatDeskBlock extends DeskBlock {
        public compatDeskBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatDeskEntity(pos, state);
        }
    }

    public class compatSideTableBlock extends SideTableBlock {
        public compatSideTableBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatSideTableEntity(pos, state);
        }
    }

    public class compatCounterBlock extends CounterBlock {
        public compatCounterBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatCounterEntity(pos, state);
        }

        @Override
        public @NotNull ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
            ResourceLocation id = Registry.BLOCK.getKey(state.getBlock());
            return (Registry.ITEM.get(EveryCompat.res(
                    id.getPath().substring(0, id.getPath().length() - 2)))).getDefaultInstance();
        }
    }

    public class compatCupboardBlock extends CupboardBlock {
        public compatCupboardBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatStorageEntity(pos, state);
        }

        @Override
        public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                              InteractionHand hand, BlockHitResult hit) {
            if (player.getMainHandItem().getItem() instanceof HammerItem) {
                return InteractionResult.PASS;
            } else if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof compatStorageEntity storage) {
                    player.openMenu(storage);
                }

                return InteractionResult.CONSUME;
            }
        }

        @Override
        public @NotNull ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
            ResourceLocation id = Registry.BLOCK.getKey(state.getBlock());
            return (Registry.ITEM.get(EveryCompat.res(
                    id.getPath().substring(0, id.getPath().length() - 2)))).getDefaultInstance();
        }
    }

    public class compatDrawerBlock extends compatCupboardBlock {
        public compatDrawerBlock(Properties properties) {
            super(properties);
            this.registerDefaultState(this.defaultBlockState().setValue(DrawerBlock.DRAWER_SHAPE, DirectionalBlockSide.SINGLE).setValue(FACING, Direction.NORTH));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING, DrawerBlock.DRAWER_SHAPE);
        }

        @Override
        public void onHammer(Level level, BlockPos pos, BlockState state, Direction side, Player user, Vec3 hitPos) {
            Block block = state.getBlock();
            ResourceLocation id = Registry.BLOCK.getKey(block);
            Block replacement = Registry.BLOCK.get(new ResourceLocation(id.getNamespace(), id.getPath().replaceAll("\\d+",
                    String.valueOf(Integer.parseInt(id.getPath().replaceAll("\\D+", "")) + 1))));
            CompoundTag tag = null;
            if (level.getBlockEntity(pos) instanceof compatStorageEntity storage) {
                tag = storage.saveWithoutMetadata();
            }
            if (replacement == Blocks.AIR) {
                level.setBlock(pos, Registry.BLOCK.get(new ResourceLocation(id.getNamespace(), id.getPath().replaceAll("\\d+", "1"))).defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(DrawerBlock.DRAWER_SHAPE, state.getValue(DrawerBlock.DRAWER_SHAPE)), Block.UPDATE_ALL);
            } else {
                level.setBlock(pos, replacement.defaultBlockState()
                        .setValue(FACING, state.getValue(FACING)).setValue(DrawerBlock.DRAWER_SHAPE,
                                state.getValue(DrawerBlock.DRAWER_SHAPE)), Block.UPDATE_ALL);
            }
            if (tag != null) {
                if (level.getBlockEntity(pos) instanceof compatStorageEntity storage) {
                    storage.load(tag);
                }
            }
        }
    }

    public class compatShelfBlock extends ShelfBlock {
        public compatShelfBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatShelfEntity(pos, state);
        }
    }

//TYPE: BlockEntity
    public class CompatChairEntity extends ChairBlockEntity {
        public CompatChairEntity(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return chair.getTileHolder().get();
        }
    }

    public class compatTableEntity extends TableBlockEntity {
        public compatTableEntity(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return table.getTileHolder().get();
        }
    }

    public class CompatBenchEntity extends WoodenBenchBlockEntity {
        public CompatBenchEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return bench.getTileHolder().get();
        }
    }

    public class CompatCouchEntity extends CouchBlockEntity {
        public CompatCouchEntity(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return couch.getTileHolder().get();
        }
    }

    public class CompatFancyBedEntity extends FancyBedBlockEntity {
        public CompatFancyBedEntity(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return fancyBed.getTileHolder().get();
        }
    }

    public class CompatDiningBenchEntity extends DiningBenchBlockEntity {
        public CompatDiningBenchEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return diningBench.getTileHolder().get();
        }
    }

    public class compatNightstandEntity extends NightstandBlockEntity {
        public compatNightstandEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return nightstand.getTileHolder().get();
        }
    }

    public class CompatDeskEntity extends DeskBlockEntity {
        public CompatDeskEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return desk.getTileHolder().get();
        }
    }

    public class CompatSideTableEntity extends SideTableBlockEntity {
        public CompatSideTableEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return sideTable.getTileHolder().get();
        }
    }

    public class compatCounterEntity extends ItemHoldingBlockEntity  {
        public compatCounterEntity(BlockPos pos, BlockState state) {
            super(Objects.requireNonNull(counter_1.getTileHolder()).get(), pos, state);
            this.setStack(Items.CALCITE.getDefaultInstance());
        }
    }

    public class compatStorageEntity extends RandomizableContainerBlockEntity {
        private NonNullList<ItemStack> items;
        private final ContainerOpenersCounter openersCounter;

        public compatStorageEntity(BlockPos blockPos, BlockState blockState) {
            super(cupboard_1.getTileHolder().get(), blockPos, blockState);
            this.items = NonNullList.withSize(27, ItemStack.EMPTY);
            this.openersCounter = new ContainerOpenersCounter() {
                protected void onOpen(Level level, BlockPos pos, BlockState state) {
                    compatStorageEntity.this.playSound(SoundEvents.BARREL_OPEN);
                }

                protected void onClose(Level level, BlockPos pos, BlockState state) {
                    compatStorageEntity.this.playSound(SoundEvents.BARREL_CLOSE);
                }

                protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int count, int openCount) {
                }

                protected boolean isOwnContainer(Player player) {
                    if (player.containerMenu instanceof ChestMenu) {
                        Container container = ((ChestMenu)player.containerMenu).getContainer();
                        return container == compatStorageEntity.this;
                    } else {
                        return false;
                    }
                }
            };
        }

        @Override
        protected void saveAdditional(CompoundTag tag) {
            super.saveAdditional(tag);
            if (!this.trySaveLootTable(tag)) {
                ContainerHelper.saveAllItems(tag, this.items);
            }

        }

        @Override
        public void load(CompoundTag tag) {
            super.load(tag);
            this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
            if (!this.tryLoadLootTable(tag)) {
                ContainerHelper.loadAllItems(tag, this.items);
            }

        }

        @Override
        public int getContainerSize() {
            return 27;
        }

        @Override
        protected @NotNull NonNullList<ItemStack> getItems() {
            return this.items;
        }

        @Override
        protected void setItems(NonNullList<ItemStack> itemStacks) {
            this.items = itemStacks;
        }

        @Override
        protected @NotNull Component getDefaultName() {
            return this.getBlockState().getBlock().getName();
        }

        @Override
        protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
            return ChestMenu.threeRows(containerId, inventory, this);
        }

        @Override
        public void startOpen(Player player) {
            if (!this.remove && !player.isSpectator()) {
                this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
            }

        }

        @Override
        public void stopOpen(Player player) {
            if (!this.remove && !player.isSpectator()) {
                this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
            }

        }

        void playSound(SoundEvent sound) {
            BlockPos pos = this.getBlockPos();
            this.level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    public class compatShelfEntity extends ItemHoldingBlockEntity {
        public compatShelfEntity(BlockPos pos, BlockState state) {
            super(shelf_1.getTileHolder().get(), pos, state);
        }
    }
}
