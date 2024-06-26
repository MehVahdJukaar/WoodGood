package net.mehvahdjukaar.every_compat.modules.handcrafted;

import earth.terrarium.handcrafted.common.block.ItemHoldingBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlock;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlock;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlock;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.woodenbench.WoodenBenchBlock;
import earth.terrarium.handcrafted.common.block.chair.woodenbench.WoodenBenchBlockEntity;
import earth.terrarium.handcrafted.common.block.counter.CounterBlock;
import earth.terrarium.handcrafted.common.block.counter.CupboardBlock;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlock;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlockEntity;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlock;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlockEntity;
import earth.terrarium.handcrafted.common.block.table.nightstand.NightstandBlock;
import earth.terrarium.handcrafted.common.block.table.nightstand.NightstandBlockEntity;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlock;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlockEntity;
import earth.terrarium.handcrafted.common.block.table.table.TableBlock;
import earth.terrarium.handcrafted.common.block.table.table.TableBlockEntity;
import earth.terrarium.handcrafted.common.item.HammerableBlockItem;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import earth.terrarium.handcrafted.common.registry.ModTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.client.*;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


@SuppressWarnings("DataFlowIssue")
public class HandcraftedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chair; // CUSHION
    public final SimpleEntrySet<WoodType, Block> table; // CLOTHINGS
    public final SimpleEntrySet<WoodType, Block> bench; // CUSHION  DONE
    public final SimpleEntrySet<WoodType, Block> couch; // CUSHION, Multiple-Model DONE
    public final SimpleEntrySet<WoodType, Block> fancyBed; // CUSHION & CLOTHINGS DONE
    public final SimpleEntrySet<WoodType, Block> diningBench; // Multiple-Model
    public final SimpleEntrySet<WoodType, Block> nightstand; // CLOTHINGS  DONE
    public final SimpleEntrySet<WoodType, Block> desk; // CLOTHINGS  DONE
    public final SimpleEntrySet<WoodType, Block> sideTable; // CLOTHINGS  DONE

    public final ItemOnlyEntrySet<WoodType, Item> counter;
    public final SimpleEntrySet<WoodType, Block> counter_1, counter_2, counter_3;

    public final ItemOnlyEntrySet<WoodType, Item> cupboard;
    public final SimpleEntrySet<WoodType, Block> cupboard_1, cupboard_2;

/*
    public final SimpleEntrySet<WoodType, Block> DRAWER_1, DRAWER_2, DRAWER_3, DRAWER_4;

    public final SimpleEntrySet<WoodType, Block> SHELF_1; // DONE
    public final SimpleEntrySet<WoodType, Block> PILLAR_TRIM; // DONE
    public final SimpleEntrySet<WoodType, Block> CORNER_TRIM; // DONE

    public final ItemOnlyEntrySet<WoodType, Item> BOARD;
*/

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        CreativeModeTab tab = ModItems.ITEM_GROUP;
        {
            chair = SimpleEntrySet.builder(WoodType.class, "chair",
                            ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatChairEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/chair/chair/oak_chair"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.ChairItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(chair);

            table = SimpleEntrySet.builder(WoodType.class, "table",
                            ModBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(compatTableEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/table/table/oak_table"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .addTag(ModTags.TABLE_ATTACHMENTS, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.TableItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(table);

            bench = SimpleEntrySet.builder(WoodType.class, "bench",
                            ModBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatBenchEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/chair/bench/oak_bench"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.BenchItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(bench);

            couch = SimpleEntrySet.builder(WoodType.class, "couch",
                            ModBlocks.OAK_COUCH, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatCouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatCouchEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/chair/couch/oak_couch"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.CouchItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(couch);

            fancyBed = SimpleEntrySet.builder(WoodType.class, "fancy_bed",
                            ModBlocks.OAK_FANCY_BED, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatFancyBedBlock(Utils.copyPropertySafe(Blocks.WHITE_BED)))
                    .addTile(CompatFancyBedEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/bed/single/oak_fancy_bed"))
                    .addTexture(modRes("block/bed/double/oak_fancy_bed"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.FancyBedItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(fancyBed);

            diningBench = SimpleEntrySet.builder(WoodType.class, "dining_bench",
                            ModBlocks.OAK_DINING_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatDiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatDiningBenchEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTexture(modRes("block/chair/dining_bench/oak_dining_bench"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.DiningBenchItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(diningBench);

            nightstand = SimpleEntrySet.builder(WoodType.class, "nightstand",
                            ModBlocks.OAK_NIGHTSTAND, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatNightstandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(compatNightstandEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTextureM(modRes("block/table/nightstand/oak_nightstand"), EveryCompat.res("block/hc/table/oak_nightstand_m"))
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.NightstandItem(b, p))
//                  .defaultRecipe()
                    .build();

            this.addEntry(nightstand);

            desk = SimpleEntrySet.builder(WoodType.class, "desk",
                            ModBlocks.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatDeskBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatDeskEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .addTextureM(modRes("block/table/desk/oak_desk"), EveryCompat.res("block/hc/table/oak_desk_m"))
                    .setTab(() -> tab)
                    .addCustomItem((w, b, p) -> new CompatModItems.DeskItem(b, p))
//                  .defaultRecipe()
                    .build();
            this.addEntry(desk);

            sideTable = SimpleEntrySet.builder(WoodType.class, "side_table",
                            ModBlocks.OAK_SIDE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                            w -> new compatSideTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                    )
                    .addTile(CompatSideTableEntity::new)
                    .setRenderType(() -> RenderType::cutout)
                    .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                    .addTextureM(modRes("block/table/side_table/oak_side_table"), EveryCompat.res("block/hc/table/oak_side_table_m"))
                    .setTab(() -> tab)
//                  .defaultRecipe()
                    .addCustomItem((w, b, p) -> new CompatModItems.SideTableItem(b, p))
                    .build();
            this.addEntry(sideTable);

        }
        counter_1 = SimpleEntrySet.builder(WoodType.class, "counter_1",
                        ModBlocks.OAK_COUNTER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_1"))
                .addTexture(modRes("block/counter/counter/overlay/oak_planks"))
                .setRenderType(() -> RenderType::cutout)
                .addTile(compatCounterEntity::new)
                .noItem()
                .build();
        this.addEntry(counter_1);

        counter_2 = SimpleEntrySet.builder(WoodType.class, "counter_2",
                        ModBlocks.OAK_COUNTER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_2"))
                .setRenderType(() -> RenderType::cutout)
                .addTile(counter_1.getTileHolder()::get)
                .noItem()
                .build();
        this.addEntry(counter_2);

        counter_3 = SimpleEntrySet.builder(WoodType.class, "counter_3",
                        ModBlocks.OAK_COUNTER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatCounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_3"))
                .setRenderType(() -> RenderType::cutout)
                .addTile(counter_1.getTileHolder()::get)
                .noItem()
                .build();
        this.addEntry(counter_3);

        counter = ItemOnlyEntrySet.builder(WoodType.class, "counter",
                        ModItems.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatModItems.CounterItem(counter_1.blocks.get(w), new Item.Properties().tab(tab))
                )
//                .addRecipe(modRes("oak_counter"))
                .addCondition(counter_1.blocks::containsKey)
                .build();
        this.addEntry(counter);

        cupboard_1 = SimpleEntrySet.builder(WoodType.class, "cupboard_1",
                        ModBlocks.OAK_CUPBOARD_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(compatCupboardEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_1"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_2"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setTab(() -> tab)
//                .defaultRecipe()
                .noItem()
                .build();
        this.addEntry(cupboard_1);

        cupboard_2 = SimpleEntrySet.builder(WoodType.class, "cupboard_2",
                        ModBlocks.OAK_CUPBOARD_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTile(cupboard_1.getTileHolder()::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_2"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setTab(() -> tab)
//                .defaultRecipe()
                .noItem()
                .build();
        this.addEntry(cupboard_2);

        cupboard = ItemOnlyEntrySet.builder(WoodType.class, "cupboard",
                        ModItems.OAK_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HammerableBlockItem(cupboard_1.blocks.get(w), new Item.Properties().tab(tab))
                )
//                .addRecipe(modRes("oak_counter"))
                .addCondition(cupboard_1.blocks::containsKey)
                .build();
        this.addEntry(cupboard);



        DRAWER_1 = SimpleEntrySet.builder(WoodType.class, "drawer_1",
                        ModBlocks.OAK_DRAWER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/front_4/drawer"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .addTile(cupboard_1.getTileHolder()::get)
                .addCustomItem((w, b, p) -> new HammerableBlockItem(b, p))
                .build();
        this.addEntry(DRAWER_1);

        DRAWER_2 = SimpleEntrySet.builder(WoodType.class, "drawer_2",
                        ModBlocks.OAK_DRAWER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addTile(cupboard_1.getTileHolder()::get)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_2);

        DRAWER_3 = SimpleEntrySet.builder(WoodType.class, "drawer_3",
                        ModBlocks.OAK_DRAWER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addTile(cupboard_1.getTileHolder()::get)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_3);

        DRAWER_4 = SimpleEntrySet.builder(WoodType.class, "drawer_4",
                        ModBlocks.OAK_DRAWER_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_4/drawer"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addTile(cupboard_1.getTileHolder()::get)

//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_4);

/*
        SHELF_1 = SimpleEntrySet.builder(WoodType.class, "shelf",
                        ModBlocks.OAK_SHELF_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/shelf/oak/shelf_back"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_middle"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_single"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .addCustomItem((w, b, p) -> new ShelfBlockItem(b, p))
                .build();
        this.addEntry(SHELF_1);


        PILLAR_TRIM = SimpleEntrySet.builder(WoodType.class, "pillar_trim",
                        ModBlocks.OAK_PILLAR_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim_2"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .addCustomItem((w, b, p) -> new HammerableBlockItem(b, p))
                .build();
        this.addEntry(PILLAR_TRIM);

        CORNER_TRIM = SimpleEntrySet.builder(WoodType.class, "corner_trim",
                        ModBlocks.OAK_CORNER_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CornerTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/trim/corner/oak_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thicc_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thin_corner_trim"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .addCustomItem((w, b, p) -> new HammerableBlockItem(b, p))
                .build();
        this.addEntry(CORNER_TRIM);

        BOARD = ItemOnlyEntrySet.builder(WoodType.class, "board",
                        ModItems.OAK_BOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BoardItem(new Item.Properties())
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("item/board/oak_board"))
//                .defaultRecipe()
                .build();
        this.addEntry(BOARD);


*/
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
    }

    //TYPE: ================ stitchAtlasTextures
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

                    // OVERLAY
                WoodType woodType = entry.getKey();
                ResourceLocation planksFromMods = Utils.getID(woodType.planks);
                var oTexture = OptimizedCounterRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(planksFromMods, ignored -> {
                        return new Material(TextureAtlas.LOCATION_BLOCKS,
                                EveryCompat.res(hcFolder + woodType.getNamespace() + "/counter/counter/overlay/" + woodType.getTypeName() + "_planks"));
                        });
                event.addSprite(oTexture.texture());

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
//        String vanillaOverlay[] = {
//                "acacia_planks", "andesite", "birch_planks", "blackstone", "bricks", "cacite", "crimson_planks",
//                "dark_oak_planks", "deepslate", "diorite", "dripsone_block", "granite", "jungle_planks", "magrove_planks",
//                "oak_planks", "quartz_block", "smooth_stone", "spruce_planks", "warped_planks"
//        };


    }


    //TYPE: ================ Block
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

    public class CompatCupboardBlock extends CupboardBlock {
        public CompatCupboardBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new compatCupboardEntity(pos, state);
        }

        @Override
        public @NotNull ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
            ResourceLocation id = Registry.BLOCK.getKey(state.getBlock());
            return (Registry.ITEM.get(EveryCompat.res(
                    id.getPath().substring(0, id.getPath().length() - 2)))).getDefaultInstance();
        }
    }

//TYPE: ================ BlockEntity
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

    @MethodsReturnNonnullByDefault
    public class compatCupboardEntity extends RandomizableContainerBlockEntity {
        private NonNullList<ItemStack> items;
        private final ContainerOpenersCounter openersCounter;

        public compatCupboardEntity(BlockPos blockPos, BlockState blockState) {
            super(cupboard_1.getTileHolder().get(), blockPos, blockState);
            this.items = NonNullList.withSize(27, ItemStack.EMPTY);
            this.openersCounter = new ContainerOpenersCounter() {
                protected void onOpen(Level level, BlockPos pos, BlockState state) {
                    compatCupboardEntity.this.playSound(SoundEvents.BARREL_OPEN);
                }

                protected void onClose(Level level, BlockPos pos, BlockState state) {
                    compatCupboardEntity.this.playSound(SoundEvents.BARREL_CLOSE);
                }

                protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int count, int openCount) {
                }

                protected boolean isOwnContainer(Player player) {
                    if (player.containerMenu instanceof ChestMenu) {
                        Container container = ((ChestMenu)player.containerMenu).getContainer();
                        return container == compatCupboardEntity.this;
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
}
