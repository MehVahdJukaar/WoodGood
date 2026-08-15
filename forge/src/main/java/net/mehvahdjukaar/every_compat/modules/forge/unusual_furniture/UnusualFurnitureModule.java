package net.mehvahdjukaar.every_compat.modules.forge.unusual_furniture;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.forge.unusual_furniture.compat_entity.*;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.toopa.unusualfurniture.block.*;

import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v1.1.1+
public class UnusualFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> carved;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> coffee_table;
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> stool;
    public final SimpleEntrySet<WoodType, Block> ceiling_lamp;
    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<WoodType, Block> bench;
    public final SimpleEntrySet<WoodType, Block> open_riser_stairs;
    public final SimpleEntrySet<WoodType, Block> railing;
    public final SimpleEntrySet<WoodType, Block> beam;
    public final SimpleEntrySet<WoodType, Block> shelf;

    public static final Map<Block, ResourceLocation> BLOCK_TO_TEXTURE_MAP = new HashMap<>();
    public final String shortenedId;

    public UnusualFurnitureModule(String modId) {
        super(modId, "uf");
        this.shortenedId = shortenedId();
        ResourceLocation tab = modRes(modId);

        carved = SimpleEntrySet.builder(WoodType.class, "", "carved",
                        getModBlock("carved_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new CarvedOakBlock()
                )
                .requiresChildren( "slab") //REASON: recipes
                .addTexture(modRes("block/carved_oak"))
                .addTexture(modRes("block/carved_oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("carved_oak_recipe"))
                .build();
        this.addEntry(carved);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        CompatTableBlock::new //TOOD CHANGE
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(STRIPPED_LOG, SLAB) //REASON: recipes
                .addTile(ufTableBlockEntity::new)
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(modRes("large_table"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_table_recipe"))
                .build();
        this.addEntry(table);

        coffee_table = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        getModBlock("oak_coffee_table"), () -> VanillaWoodTypes.OAK,
                        CompatCoffeeTableBlock::new //TOOD CHANGE
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(STRIPPED_LOG, SLAB) //REASON: recipes
                .addTile(ufCoffeeTableBlockEntity::new)
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_coffee_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_coffee_table_craft"))
                .build();
        this.addEntry(coffee_table);

        stool = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> VanillaWoodTypes.OAK,
                        w -> new OakStoolBlock()
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_stool_craft"))
                .build();
        this.addEntry(stool);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new OakChairBlock()
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(stool.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: carved_oak, stool
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_chair_craft"))
                .build();
        this.addEntry(chair);

        ceiling_lamp = SimpleEntrySet.builder(WoodType.class, "celling_lamp",
                        getModBlock("oak_celling_lamp"), () -> VanillaWoodTypes.OAK,
                        CompatCellingLampBlock::new //TOOD CHANGE
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .addTile(ufCellingLampBlockEntity::new)
                //TEXTURES: carved_oak
                .addTextureM(modRes("block/oak_celling_lamp"), EveryCompat.res("block/uf/oak_celling_lamp_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("ceiling_lamp"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_celling_lamp_craft"))
                .build();
        this.addEntry(ceiling_lamp);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        getModBlock("jungle_drawer"), () -> VanillaWoodTypes.JUNGLE,
                        CompatDrawerBlock::new //TOOD CHANGE
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(ufDrawerBlockEntity::new)
                //TEXTURES: carved_oak_top
                .addTexture(modRes("block/jungle_drawer"))
                .setTabKey(tab)
                .addRecipe(modRes("jungle_drawer_craft"))
                .build();
        this.addEntry(drawer);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        CompatBenchBlock::new //TOOD CHANGE
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .addTile(ufBenchBlockEntity::new)
                //TEXTURES: carved_oak
                .addTextureM(modRes("block/bench_oak"), EveryCompat.res("block/uf/bench_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bench"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_bench_craft"))
                .build();
        this.addEntry(bench);

        open_riser_stairs = SimpleEntrySet.builder(WoodType.class, "open_riser_stairs",
                        getModBlock("oak_open_riser_stairs"), () -> VanillaWoodTypes.OAK,
                        w -> new OakOpenRiserStairsBlock()
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren("stairs") //REASON: recipes
                //TEXTURES: carved_oak, oak_coffe_table
                .addTag(modRes("stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_open_riser_stairs_craft"))
                .build();
        this.addEntry(open_riser_stairs);

        railing = SimpleEntrySet.builder(WoodType.class, "railing",
                        getModBlock("oak_railing"), () -> VanillaWoodTypes.OAK,
                        w -> new OakRailingBlock()
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: carved_oak, oak_coffe_table
                .addTag(modRes("railing"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_railing_recipe"))
                .build();
        this.addEntry(railing);

        beam = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> VanillaWoodTypes.OAK,
                        w -> new OakBeamBlock()
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: oak_coffee_table, carved_oak_top
                .setTabKey(tab)
                .addRecipe(modRes("oak_beam_craft"))
                .build();
        this.addEntry(beam);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new OakShelfBlock()
                )
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: planks, oak_coffee_table
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_sheft_craft"))
                .build();
        this.addEntry(shelf);

    }

    // ───────────────────────────────── Getblocktile ──────────────────────────────────
    private BlockEntityType<? extends CompatTableBlockEntity> getTableTile() {
        return table.getTile(CompatTableBlockEntity.class);
    }

    private BlockEntityType<? extends CompatCoffeeTableBlockEntity> getCoffeeTableTile() {
        return coffee_table.getTile(CompatCoffeeTableBlockEntity.class);
    }

    private BlockEntityType<? extends CompatCellingLampBlockEntity> getCellingLampTile() {
        return ceiling_lamp.getTile(CompatCellingLampBlockEntity.class);
    }

    private BlockEntityType<? extends CompatDrawerBlockEntity> getDrawerTile() {
        return drawer.getTile(CompatDrawerBlockEntity.class);
    }

    private BlockEntityType<? extends CompatBenchBlockEntity> getBenchTile() {
        return bench.getTile(CompatBenchBlockEntity.class);
    }

    // ───────────────────────────────── Compat Block ──────────────────────────────────
    public class CompatTableBlock extends OakTableBlock {
        public static WoodType woodType;

        public CompatTableBlock(WoodType woodType) {
            super();
            CompatCoffeeTableBlock.woodType = woodType;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new ufTableBlockEntity(pos, state);
        }
    }

    public class CompatCoffeeTableBlock extends OakCoffeeTableBlock {
        public static WoodType woodType;

        public CompatCoffeeTableBlock(WoodType woodType) {
            super();
            CompatCoffeeTableBlock.woodType = woodType;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new ufCoffeeTableBlockEntity(pos, state);
        }
    }

    public class CompatCellingLampBlock extends OakCellingLampBlock {
        public static WoodType woodType;

        public CompatCellingLampBlock(WoodType woodType) {
            super();
            CompatCellingLampBlock.woodType = woodType;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new ufCellingLampBlockEntity(pos, state);
        }
    }

    public class CompatDrawerBlock extends JungleDrawerBlock {
        public static WoodType woodType;

        public CompatDrawerBlock(WoodType woodType) {
            super();
            CompatDrawerBlock.woodType = woodType;
            BLOCK_TO_TEXTURE_MAP.put(this, ResourceLocation.parse(
                    woodType.createFullIdWith(EveryCompat.MOD_ID, "textures/block",
                            shortenedId, "java_drawer", ".png")
            ));

        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new ufDrawerBlockEntity(pos, state);
        }
    }

    public class CompatBenchBlock extends OakBenchBlock {
        public static WoodType woodType;

        public CompatBenchBlock(WoodType woodType) {
            super();
            CompatBenchBlock.woodType = woodType;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new ufBenchBlockEntity(pos, state);
        }
    }

    // ─────────────────────────────── Compatblockentity ───────────────────────────────
    public class ufTableBlockEntity extends CompatTableBlockEntity {
        public ufTableBlockEntity(BlockPos position, BlockState state) {
            super(position, state, getTableTile());
        }
    }

    public class ufCoffeeTableBlockEntity extends CompatCoffeeTableBlockEntity {
        public ufCoffeeTableBlockEntity(BlockPos position, BlockState state) {
            super(position, state, getCoffeeTableTile());
        }
    }

    public class ufCellingLampBlockEntity extends CompatCellingLampBlockEntity {
        public ufCellingLampBlockEntity(BlockPos position, BlockState state) {
            super(position, state, getCellingLampTile());
        }
    }

    public class ufDrawerBlockEntity extends CompatDrawerBlockEntity {
        public ufDrawerBlockEntity(BlockPos position, BlockState state) {
            super(position, state, getDrawerTile());
        }
    }

    public class ufBenchBlockEntity extends CompatBenchBlockEntity {
        public ufBenchBlockEntity(BlockPos position, BlockState state) {
            super(position, state, getBenchTile());
        }
    }

// ─────────────────────────────────── Register ────────────────────────────────────


}