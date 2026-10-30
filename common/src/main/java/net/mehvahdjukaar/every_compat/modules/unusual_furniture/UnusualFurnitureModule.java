package net.mehvahdjukaar.every_compat.modules.unusual_furniture;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.toopa.unusual_furniture.common.block.BeamBlock;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.ChairBlock;
import net.toopa.unusual_furniture.common.block.CoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.OpenRiserStairBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.ShelfBlock;
import net.toopa.unusual_furniture.common.block.StoolBlock;
import net.toopa.unusual_furniture.common.block.TableBlock;
import net.toopa.unusual_furniture.common.item.OpenRiserStairBlockItem;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//SUPPORT: v2.0.1+
public class UnusualFurnitureModule extends EveryCompatModule {

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

    public UnusualFurnitureModule(String modId) {
        super(modId, "uf");
        ResourceLocation furnitureTab = modRes("01_furniture");
        ResourceLocation buildingTab = modRes("03_building");

        carved = SimpleEntrySet.builder(WoodType.class, "planks", "carved",
                        getModBlock("carved_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new CarvedPlanksBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .dynamicShape()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/carved_oak"))
                .addTexture(modRes("block/carved_oak_top"))
                .addTexture(modRes("block/carved_oak_middle"))
                .addTexture(modRes("block/carved_oak_bottom"))
                .addTexture(modRes("block/carved_oak_single"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("craved_planks"), Registries.BLOCK)
                .setTab(getTab(buildingTab))
                .addRecipe(modRes("carved_oak_planks"))
                .build();
        this.addEntry(carved);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(STRIPPED_LOG, SLAB) //REASON: recipes
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_table"))
                .build();
        this.addEntry(table);

        coffee_table = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        getModBlock("oak_coffee_table"), () -> VanillaWoodTypes.OAK,
                        w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(STRIPPED_LOG, SLAB) //REASON: recipes
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_coffee_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_coffee_table"))
                .build();
        this.addEntry(coffee_table);

        stool = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> VanillaWoodTypes.OAK,
                        w -> new StoolBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_stool"))
                .build();
        this.addEntry(stool);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(stool.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: carved_oak, stool
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_chair"))
                .build();
        this.addEntry(chair);

        ceiling_lamp = SimpleEntrySet.builder(WoodType.class, "ceiling_lamp",
                        getModBlock("oak_ceiling_lamp"), () -> VanillaWoodTypes.OAK,
                        w -> new CeilingLampBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .dynamicShape()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                //TEXTURES: carved_oak
                .addTextureM(modRes("block/oak_ceiling_lamp"), EveryCompat.res("block/uf/oak_ceiling_lamp_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("ceiling_lamp"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_ceiling_lamp"))
                .build();
        this.addEntry(ceiling_lamp);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        getModBlock("oak_drawer"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .dynamicShape()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(() -> UFBlockEntityTypes.DRAWER_BLOCK_ENTITY)
                .addTexture(modRes("block/oak_drawer"))
                .addTexture(modRes("block/oak_drawer_be"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTab(getTab(buildingTab))
                .addRecipe(modRes("oak_drawer"))
                .build();
        this.addEntry(drawer);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .dynamicShape()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(LOG) //REASON: recipes
                //TEXTURES: carved_oak
                .addTextureM(modRes("block/oak_bench"), EveryCompat.res("block/uf/bench_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bench"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_bench"))
                .build();
        this.addEntry(bench);

        open_riser_stairs = SimpleEntrySet.builder(WoodType.class, "open_riser_stairs",
                        getModBlock("oak_open_riser_stairs"), () -> VanillaWoodTypes.OAK,
                        w -> new OpenRiserStairBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren(STAIRS) //REASON: recipes
                .addCustomItem((w, b, p) -> new OpenRiserStairBlockItem(b, p))
                //TEXTURES: carved_oak, oak_coffee_table
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("open_riser_stairs"), Registries.BLOCK)
                .setTab(getTab(buildingTab))
                .addRecipe(modRes("oak_open_riser_stairs"))
                .build();
        this.addEntry(open_riser_stairs);

        railing = SimpleEntrySet.builder(WoodType.class, "railing",
                        getModBlock("oak_railing"), () -> VanillaWoodTypes.OAK,
                        w -> new RailingBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .dynamicShape()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresChildren(STRIPPED_WOOD) //REASON: recipes
                //TEXTURES: carved_oak
                .addTexture(modRes("block/oak_railing"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railing"), Registries.BLOCK)
                .setTab(getTab(buildingTab))
                .addRecipe(modRes("oak_railing"))
                .build();
        this.addEntry(railing);

        beam = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> VanillaWoodTypes.OAK,
                        w -> new BeamBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion()
                                .ignitedByLava()
                                .instrument(NoteBlockInstrument.BASS))
                )
                .requiresFromMap(carved.blocks) //REASON: textures
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: carved_oak, oak_coffee_table
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("beam"), Registries.BLOCK)
                .setTab(getTab(buildingTab))
                .addRecipe(modRes("oak_beam"))
                .build();
        this.addEntry(beam);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)
                                .strength(1.0F, 10.0F)
                                .noOcclusion())
                )
                .requiresFromMap(coffee_table.blocks) //REASON: textures
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: planks, oak_coffee_table
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shelf"), Registries.BLOCK)
                .setTab(getTab(furnitureTab))
                .addRecipe(modRes("oak_shelf"))
                .build();
        this.addEntry(shelf);
    }
}
