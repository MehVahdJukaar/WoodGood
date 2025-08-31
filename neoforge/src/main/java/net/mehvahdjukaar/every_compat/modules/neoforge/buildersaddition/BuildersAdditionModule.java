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
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.LEAVES;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//TODO: Test this module
//SUPPORT: v2.1.0+
public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> panels,
            arcades,
            bedsideTables,
            benches,
            bookshelves,
            cabinets,
            chairs,
            counters,
            countersAndesite,
            countersBlackstone,
            countersDeepslate,
            countersDiorite,
            countersGranite,
            countersBasal,
            cupboards,
            shelves,
            stools,
            supportBeams,
            tables,
            shopSigns,
            posts,
            stripped_fences;
    public final SimpleEntrySet<LeavesType, Block> hedges;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        ResourceLocation tab = modRes("builders_addition_group");


        panels = SimpleEntrySet.builder(WoodType.class, "panel",
                        getModBlock("oak_panel"), () -> VanillaWoodTypes.OAK,
                        (w) -> new PanelBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(panels);

        tables = SimpleEntrySet.builder(WoodType.class,"table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tables);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> VanillaWoodTypes.OAK,
                        w -> new StoolBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stools);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chairs);

        counters = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(counters);

        countersAndesite = SimpleEntrySet.builder(WoodType.class, "andesite_counter",
                        getModBlock("oak_andesite_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersAndesite);

        countersDiorite = SimpleEntrySet.builder(WoodType.class, "diorite_counter",
                        getModBlock("oak_diorite_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersDiorite);

        countersGranite = SimpleEntrySet.builder(WoodType.class, "granite_counter",
                        getModBlock("oak_granite_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersGranite);

        countersBlackstone = SimpleEntrySet.builder(WoodType.class, "blackstone_counter",
                        getModBlock("oak_blackstone_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersBlackstone);

        countersDeepslate = SimpleEntrySet.builder(WoodType.class, "deepslate_counter",
                        getModBlock("oak_deepslate_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersDeepslate);

        countersBasal = SimpleEntrySet.builder(WoodType.class, "basal_counter",
                        getModBlock("oak_basal_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(countersBasal);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("oak_bookshelf"), () -> VanillaWoodTypes.OAK,
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG, "slab") //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelves);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
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
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinets);

        cupboards = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        getModBlock("oak_cupboard"), () -> VanillaWoodTypes.OAK,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("cupboard"))
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cupboard/cupboard_oak_left"))
                .addRecipe(modRes("cupboard/cupboard_oak_right"))
                .build();
        this.addEntry(cupboards);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(benches);

        supportBeams = SimpleEntrySet.builder(WoodType.class, "support_bracket",
                        getModBlock("oak_support_bracket"), () -> VanillaWoodTypes.OAK,
                        w -> new SupportBeamBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(supportBeams);

        bedsideTables = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        getModBlock("oak_bedside_table"), () -> VanillaWoodTypes.OAK,
                        w -> new BedsideTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bedsideTables);

        arcades = SimpleEntrySet.builder(WoodType.class, "arcade",
                        getModBlock("oak_arcade"), () -> VanillaWoodTypes.OAK,
                        w -> new ArcadeBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(arcades);

        shopSigns = SimpleEntrySet.builder(WoodType.class, "shop_sign",
                        getModBlock("oak_shop_sign"), () -> VanillaWoodTypes.OAK,
                        w -> new ShopSignBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(shopSigns);

        posts = SimpleEntrySet.builder(WoodType.class, "post",
                        getModBlock("oak_post"), () -> VanillaWoodTypes.OAK,
                        w -> new PostBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(posts);

        stripped_fences = SimpleEntrySet.builder(WoodType.class, "stripped_fence",
                        getModBlock("oak_stripped_fence"), () -> VanillaWoodTypes.OAK,
                        w -> new StrippedFenceBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: planks, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_fences);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        getModBlock("oak_hedge"), () -> VanillaLeavesTypes.OAK,
                        w -> new HedgeBlock(Utils.copyPropertySafe(w.leaves))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: leaves
                .requiresChildren(LEAVES) // Reason: RECIPES
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        LEAVES, CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
//                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK)
                .addTag(ItemTags.LEAVES, Registries.ITEM)
                .copyParentTint()
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(hedges);
    }

}
