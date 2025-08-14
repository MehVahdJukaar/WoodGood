package net.mehvahdjukaar.every_compat.modules.forge.blocks_plus;

import blocks_plus.blocks.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.*;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;

//SUPPORT: v1.7+
public class BlocksPlusModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cracked_planks;
    public final SimpleEntrySet<WoodType, Block> mossy_planks;
    public final SimpleEntrySet<WoodType, Block> crafting_table;
    public final SimpleEntrySet<WoodType, Block> chest;
    public final SimpleEntrySet<WoodType, Block> trapped_chest;
    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> wooden_crate;
    public final SimpleEntrySet<WoodType, Block> ladder;
    public final SimpleEntrySet<WoodType, Block> mosaic,
                                                 mosaic_stairs,
                                                 mosaic_slab,
                                                 mosaic_pressure_plate;
    public final SimpleEntrySet<WoodType, Block> cracked_mosaic;
    public final SimpleEntrySet<WoodType, Block> mossy_mosaic;

    public BlocksPlusModule(String modId) {
        super(modId, "bp");
        var tab = modRes("creative_tab");

        cracked_planks = SimpleEntrySet.builder(WoodType.class, "planks", "cracked",
                        getModBlock("cracked_acacia_planks"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPCrackedMossyPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/cracked_acacia_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_planks);

        mossy_planks = SimpleEntrySet.builder(WoodType.class, "planks", "mossy",
                        getModBlock("mossy_acacia_planks"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPCrackedMossyPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTextureM(modRes("block/mossy_acacia_planks"), EveryCompat.res("block/bp/mossy_acacia_planks_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_planks);

        crafting_table = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("acacia_crafting_table"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPCraftingTable(Utils.copyPropertySafe(Blocks.CRAFTING_TABLE).mapColor(w.getColor()))
                )
                .addTextureM(modRes("block/acacia_crafting_table_side"), EveryCompat.res("block/vanilla_crafting_table_side_m"))
                .addTextureM(modRes("block/acacia_crafting_table_front"), EveryCompat.res("block/vanilla_crafting_table_front_m"))
                .addTextureM(modRes("block/acacia_crafting_table_top"), EveryCompat.res("block/vanilla_crafting_table_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crafting_table);

        chest = SimpleEntrySet.builder(WoodType.class, "chest",
                        getModBlock("acacia_chest"), () -> VanillaWoodTypes.ACACIA,
                        w -> new CompatChestBlock(this::getChestTile, Utils.copyPropertySafe(Blocks.CHEST).mapColor(w.getColor()))
                )
                //TEXTURES: planks
                .addTile(bpChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(chest);

        trapped_chest = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        getModBlock("acacia_trapped_chest"), () -> VanillaWoodTypes.ACACIA,
                        w -> new CompatTrappedChestBlock(this::getTrappedTile, Utils.copyPropertySafe(Blocks.TRAPPED_CHEST).mapColor(w.getColor()))
                )
                //TEXTURES: planks
                .addTile(bpTrappedBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chests"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(trapped_chest);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("acacia_bookshelf"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPPlankBookshelf(Utils.copyPropertySafe(Blocks.BOOKSHELF).mapColor(w.getColor()))
                )
                .addTextureM(modRes("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelf);

        wooden_crate = SimpleEntrySet.builder(WoodType.class, "wooden_crate",
                        getModBlock("acacia_wooden_crate"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/acacia_wooden_crate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wooden_crate);

        ladder = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("acacia_ladder"), () -> VanillaWoodTypes.ACACIA,
                        w -> new LadderBlock(Utils.copyPropertySafe(Blocks.LADDER).mapColor(w.getColor()))
                )
                .addTexture(modRes("block/acacia_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(ladder);

        mosaic = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        getModBlock("acacia_mosaic"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/acacia_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic);

        mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        getModBlock("acacia_mosaic_stairs"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPPlankStairs(mosaic.blocks.get(w).defaultBlockState(),
                                Utils.copyPropertySafe(getBlockSafe(w, "stairs")).mapColor(w.getColor()))
                )
                .requiresChildren("stairs")
                .requiresFromMap(mosaic.blocks) //REASON: textures, recipes
                //TEXTURES: mosaic
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_stairs);

        mosaic_slab = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("acacia_mosaic_slab"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPPlankSlab(Utils.copyPropertySafe(getBlockSafe(w, "slab")).mapColor(w.getColor()))
                )
                .requiresChildren("slab")
                .requiresFromMap(mosaic.blocks) //REASON: textures, recipes
                //TEXTURES: mosaic
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_slab);

        mosaic_pressure_plate = SimpleEntrySet.builder(WoodType.class, "mosaic_pressure_plate",
                        getModBlock("acacia_mosaic_pressure_plate"), () -> VanillaWoodTypes.ACACIA,
                        w -> new PressurePlateBlock(
                                PressurePlateBlock.Sensitivity.EVERYTHING,
                                Utils.copyPropertySafe(getBlockSafe(w, "pressure_plate")).mapColor(w.getColor()),
                                BlockSetType.ACACIA
                        )
                )
                .requiresFromMap(mosaic.blocks) //REASON: textures, recipes
                //TEXTURES: mosaic
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_pressure_plate);

        cracked_mosaic = SimpleEntrySet.builder(WoodType.class, "mosaic", "cracked",
                        getModBlock("cracked_acacia_mosaic"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPCrackedMossyPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/cracked_acacia_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_mosaic);

        mossy_mosaic = SimpleEntrySet.builder(WoodType.class, "mosaic", "mossy",
                        getModBlock("mossy_acacia_mosaic"), () -> VanillaWoodTypes.ACACIA,
                        w -> new BPCrackedMossyPlanks(Utils.copyPropertySafe(w.planks))
                )
                .addTextureM(modRes("block/mossy_acacia_mosaic"), EveryCompat.res("block/bp/mossy_acacia_mosaic_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_mosaic);

    }

    public Block getBlockSafe(WoodType type, String childkey) {
        Block block = type.getBlockOfThis(childkey);
        if (Objects.nonNull(block)) return block;
        else {
            switch (childkey) {
                case "pressure_plate" -> block = Blocks.ACACIA_PRESSURE_PLATE;
                case "stairs" -> block = Blocks.ACACIA_STAIRS;
                case "slab" -> block = Blocks.ACACIA_SLAB;
            }
            return block;
        }
    }

    // GetTile
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return chest.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return trapped_chest.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity
    private class bpChestBlockEntity extends CompatChestBlockEntity {
        public bpChestBlockEntity(BlockPos pos, BlockState state) {
            super(chest.getTile(), pos, state);
        }
    }

    private class bpTrappedBlockEntity extends CompatChestBlockEntity {
        public bpTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(trapped_chest.getTile(), pos, state);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chest.getTile(CompatChestBlockEntity.class), shortenedId());
        CompatChestBlockRenderer.register(event, trapped_chest.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    @Override
    // Textures
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) ->
            trapped_chest.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia/acacia"),
                        EveryCompat.res("entity/bp/chest_normal_m"),
                        EveryCompat.res("model/oak_chest_normal_o"),
                        EveryCompat.res("model/trapped_chest_normal")
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia/left"),
                        EveryCompat.res("entity/bp/chest_left_m"),
                        EveryCompat.res("model/oak_chest_left_o"),
                        EveryCompat.res("model/trapped_chest_left")
                );
                // RIGHT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/acacia/right"),
                        EveryCompat.res("entity/bp/chest_right_m"),
                        EveryCompat.res("model/oak_chest_right_o"),
                        EveryCompat.res("model/trapped_chest_right")
                );
            })
        );
    }
}