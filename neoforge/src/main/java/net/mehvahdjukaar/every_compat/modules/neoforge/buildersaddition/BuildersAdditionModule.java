package net.mehvahdjukaar.every_compat.modules.neoforge.buildersaddition;

import github.mrh0.buildersaddition2.blocks.arcade.ArcadeBlock;
import github.mrh0.buildersaddition2.blocks.bedside_table.BedsideTableBlock;
import github.mrh0.buildersaddition2.blocks.bench.BenchBlock;
import github.mrh0.buildersaddition2.blocks.bookshelf.BookshelfBlock;
import github.mrh0.buildersaddition2.blocks.cabinet.CabinetBlock;
import github.mrh0.buildersaddition2.blocks.chair.ChairBlock;
import github.mrh0.buildersaddition2.blocks.counter.CounterBlock;
import github.mrh0.buildersaddition2.blocks.cupboard.CupboardBlock;
import github.mrh0.buildersaddition2.blocks.hedge.HedgeBlock;
import github.mrh0.buildersaddition2.blocks.panel.PanelBlock;
import github.mrh0.buildersaddition2.blocks.post.PostBlock;
import github.mrh0.buildersaddition2.blocks.shelf.ShelfBlock;
import github.mrh0.buildersaddition2.blocks.shop_sign.ShopSignBlock;
import github.mrh0.buildersaddition2.blocks.stool.StoolBlock;
import github.mrh0.buildersaddition2.blocks.stripped_fence.StrippedFenceBlock;
import github.mrh0.buildersaddition2.blocks.support_beam.SupportBeamBlock;
import github.mrh0.buildersaddition2.blocks.table.TableBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

//TODO: Test this module
//SUPPORT: v2.1.0+
public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> panels;
    public final SimpleEntrySet<WoodType, Block> arcades;
    public final SimpleEntrySet<WoodType, Block> bedsideTables;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> cabinets;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> counters;
    public final SimpleEntrySet<WoodType, Block> countersAndesite;
    public final SimpleEntrySet<WoodType, Block> countersBlackstone;
    public final SimpleEntrySet<WoodType, Block> countersDeepslate;
    public final SimpleEntrySet<WoodType, Block> countersDiorite;
    public final SimpleEntrySet<WoodType, Block> countersGranite;
    public final SimpleEntrySet<WoodType, Block> countersBasal;
    public final SimpleEntrySet<WoodType, Block> cupboards;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> stools;
    public final SimpleEntrySet<WoodType, Block> supportBeams;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> shopSigns;
    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> stripped_fences;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        ResourceLocation tab = modRes("builders_addition_group");


        panels = SimpleEntrySet.builder(WoodType.class, "panel",
                        getModBlock("oak_panel"), () -> WoodTypeRegistry.OAK_TYPE,
                        (w) -> new PanelBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(panels);

        tables = SimpleEntrySet.builder(WoodType.class,"table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tables);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StoolBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stools);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chairs);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        getModBlock("oak_hedge"), () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> new HedgeBlock(Utils.copyPropertySafe(w.leaves))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: leaves
                .requiresChildren("leaves") // Reason: RECIPES
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", SpriteHelper.LOOKS_LIKE_LEAF_TEXTURE))
//                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK)
                .addTag(ItemTags.LEAVES, Registries.ITEM)
                .copyParentTint()
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(hedges);

        counters = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(counters);

        countersAndesite = SimpleEntrySet.builder(WoodType.class, "andesite_counter",
                        getModBlock("oak_andesite_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersAndesite);

        countersDiorite = SimpleEntrySet.builder(WoodType.class, "diorite_counter",
                        getModBlock("oak_diorite_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersDiorite);

        countersGranite = SimpleEntrySet.builder(WoodType.class, "granite_counter",
                        getModBlock("oak_granite_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersGranite);

        countersBlackstone = SimpleEntrySet.builder(WoodType.class, "blackstone_counter",
                        getModBlock("oak_blackstone_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersBlackstone);

        countersDeepslate = SimpleEntrySet.builder(WoodType.class, "deepslate_counter",
                        getModBlock("oak_deepslate_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersDeepslate);

        countersBasal = SimpleEntrySet.builder(WoodType.class, "basal_counter",
                        getModBlock("oak_basal_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersBasal);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("oak_bookshelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log", "slab") //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelves);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTile(getModTile("shelf"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(shelves);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinets);

        cupboards = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        getModBlock("oak_cupboard"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("cupboard"))
                .requiresChildren("stripped_log")
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cupboard/cupboard_oak_left"))
                .addRecipe(modRes("cupboard/cupboard_oak_right"))
                .build();
        this.addEntry(cupboards);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(benches);

        supportBeams = SimpleEntrySet.builder(WoodType.class, "support_bracket",
                        getModBlock("oak_support_bracket"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBeamBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(supportBeams);

        bedsideTables = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        getModBlock("oak_bedside_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bedsideTables);

        arcades = SimpleEntrySet.builder(WoodType.class, "arcade",
                        getModBlock("oak_arcade"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ArcadeBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(arcades);

        shopSigns = SimpleEntrySet.builder(WoodType.class, "shop_sign",
                        getModBlock("oak_shop_sign"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShopSignBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(shopSigns);

        posts = SimpleEntrySet.builder(WoodType.class, "post",
                        getModBlock("oak_post"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PostBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(posts);

        stripped_fences = SimpleEntrySet.builder(WoodType.class, "stripped_fence",
                        getModBlock("oak_stripped_fence"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StrippedFenceBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_fences);


    }

}
