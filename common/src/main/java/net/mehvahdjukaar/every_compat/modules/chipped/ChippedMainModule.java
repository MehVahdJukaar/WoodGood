package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.PLANKS;

//TODO:
// Mcmeta files are not copied from the base block

//SUPPORT: v4.0.2+
public class ChippedMainModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> barrel,
            crate,
            reinforcedCrate,
            basketWovenPlanks,
            boxedPlanks,
            brickBondPlanks,
            brickyPlanks,
            corneredPlanks,
            cratedPlanks,
            crossLacedPlanks,
            crossedPlanks,
            detailedPlanks,
            diagonalPlanks,
            diamondPlanks,
            doubleHerringbonePlanks,
            enclosedPlanks,
            finePlanks,
            fineVerticalPlanks,
            framedPlanks,
            herringbonePlanks,
            hewnPlanks,
            lacedPlanks,
            mosaicPlanks,
            nailedPlanks,
            naturalPlanks,
            panelPlanks,
            peggedPlanks,
            polishedPlanks,
            shavingsPlanks,
            railedPlanks,
            shiftedPlanks,
            slantedPlanks,
            smoothPlanks,
            stackedPlanks,
            thinPlanks,
            tiledPlanks,
            versaillesPlanks,
            verticalPlanks,
            verticallyRailedPlanks,
            whirlwindPlanks,
            wickeredPlanks;

    public final SimpleEntrySet<WoodType, Block> torch;
    public final SimpleEntrySet<WoodType, Block> wallTorch;

    public static String tabPath = "main";

    public ChippedMainModule(String modId) {
        super(modId, "ch", EveryCompat.MOD_ID);
        ResourceLocation tab = modRes(tabPath);

        mosaicPlanks = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        getModBlock("oak_planks_mosaic"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/oak_planks_mosaic"), dullPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(mosaicPlanks);

        panelPlanks = SimpleEntrySet.builder(WoodType.class, "planks_panel",
                        getModBlock("oak_planks_panel"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/0"), panelPalette)
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/1"), panelPalette)
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/2"), panelPalette)
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/3"), panelPalette)
                .addTexture(modRes("block/oak_planks/oak_planks_panel"), panelPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(panelPlanks);

        shavingsPlanks = SimpleEntrySet.builder(WoodType.class, "planks_shavings",
                        getModBlock("oak_planks_shavings"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/oak_planks_shavings"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(shavingsPlanks);

        basketWovenPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "basket_woven",
                        getModBlock("basket_woven_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/basket_woven_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(basketWovenPlanks);

        boxedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "boxed",
                        getModBlock("boxed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/boxed_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(boxedPlanks);

        brickBondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "brick_bond",
                        getModBlock("brick_bond_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/brick_bond_oak_planks"), dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(brickBondPlanks);

        brickyPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "bricky",
                        getModBlock("bricky_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/bricky_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(brickyPlanks);

        corneredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cornered",
                        getModBlock("cornered_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/0"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/1"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/2"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/3"), darkerPalette)
                .addTexture(modRes("block/oak_planks/cornered_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(corneredPlanks);

        cratedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crated",
                        getModBlock("crated_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/common_textures/0"), dullerPalette)
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/1"), dullerPalette)
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/2"), dullerPalette)
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/3"), dullerPalette)
                .addTexture(modRes("block/oak_planks/crated_oak_planks"), dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(cratedPlanks);

        crossLacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cross_laced",
                        getModBlock("cross_laced_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/cross_laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(crossLacedPlanks);

        crossedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crossed",
                        getModBlock("crossed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/crossed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(crossedPlanks);

        detailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "detailed",
                        getModBlock("detailed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/detailed_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(detailedPlanks);

        diagonalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diagonal",
                        getModBlock("diagonal_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/diagonal_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonalPlanks);

        diamondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diamond",
                        getModBlock("diamond_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/diamond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondPlanks);

        doubleHerringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "double_herringbone",
                        getModBlock("double_herringbone_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/double_herringbone_oak_planks"), dullLuminance)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(doubleHerringbonePlanks);

        enclosedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "enclosed",
                        getModBlock("enclosed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: cratedPlanks' modRes("block/oak_planks/ctm/common_textures/0")
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/1"), dullerPalette)
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/2"), dullerPalette)
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/3"), dullerPalette)
                .addTexture(modRes("block/oak_planks/enclosed_oak_planks"), dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(enclosedPlanks);

        finePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine",
                        getModBlock("fine_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/fine_oak_planks"), dullPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(finePlanks);

        fineVerticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine_vertical",
                        getModBlock("fine_vertical_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/fine_vertical_oak_planks"), dullPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(fineVerticalPlanks);

        framedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "framed",
                        getModBlock("framed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/0"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/1"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/2"), darkerPalette)
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/3"), darkerPalette)
                .addTexture(modRes("block/oak_planks/framed_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(framedPlanks);

        herringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "herringbone",
                        getModBlock("herringbone_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/herringbone_oak_planks")/*, dullPalette*/)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(herringbonePlanks);

        hewnPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "hewn",
                        getModBlock("hewn_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/hewn_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(hewnPlanks);

        lacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "laced",
                        getModBlock("laced_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/laced_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(lacedPlanks);

        nailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "nailed",
                        getModBlock("nailed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/nailed_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(nailedPlanks);

        naturalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "natural",
                        getModBlock("natural_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/0"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/1"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/2"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/3"), darkPalette)
                .addTexture(modRes("block/oak_planks/natural_oak_planks"), darkPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(naturalPlanks);

        peggedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "pegged",
                        getModBlock("pegged_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/0"), dullPalette)
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/1"), dullPalette)
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/2"), dullPalette)
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/3"), dullPalette)
                .addTexture(modRes("block/oak_planks/pegged_oak_planks"), dullPalette )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(peggedPlanks);

        polishedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        getModBlock("polished_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/polished_oak_planks"), polishedPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(polishedPlanks);

        railedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "railed",
                        getModBlock("railed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(railedPlanks);

        shiftedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "shifted",
                        getModBlock("shifted_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/shifted_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(shiftedPlanks);

        slantedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "slanted",
                        getModBlock("slanted_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/slanted_oak_planks"), dullLuminance)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(slantedPlanks);

        smoothPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "smooth",
                        getModBlock("smooth_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/smooth_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(smoothPlanks);

        stackedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "stacked",
                        getModBlock("stacked_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/stacked_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(stackedPlanks);

        thinPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "thin",
                        getModBlock("thin_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/thin_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(thinPlanks);

        tiledPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "tiled",
                        getModBlock("tiled_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/tiled_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(tiledPlanks);

        versaillesPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "versailles",
                        getModBlock("versailles_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/versailles_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(versaillesPlanks);

        verticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        getModBlock("vertical_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(verticalPlanks);

        verticallyRailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertically_railed",
                        getModBlock("vertically_railed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/vertically_railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(verticallyRailedPlanks);

        whirlwindPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "whirlwind",
                        getModBlock("whirlwind_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/0"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/1"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/2"), darkPalette)
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/3"), darkPalette)
                .addTexture(modRes("block/oak_planks/whirlwind_oak_planks"), darkPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(whirlwindPlanks);

        wickeredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "wickered",
                        getModBlock("wickered_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/wickered_oak_planks"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(wickeredPlanks);

        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        getModBlock("oak_barrel"), () -> VanillaWoodTypes.OAK,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/barrel/oak_barrel_bottom"), darkerPalette)
                .addTexture(modRes("block/barrel/oak_barrel_top_open"), darkerPalette)
                .addTextureM(modRes("block/barrel/oak_barrel_side"), EveryCompat.res("block/ch/oak_barrel_side_m"), darkerPalette)
                .addTextureM(modRes("block/barrel/oak_barrel_top"), EveryCompat.res("block/ch/oak_barrel_top_m"), darkerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .addTile(() -> BlockEntityType.BARREL)
                .setTabKey(tab)
                .build();
        this.addEntry(barrel);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        getModBlock("oak_crate"), () -> VanillaWoodTypes.OAK,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/barrel/oak_crate_side"), darkPalette)
                .addTexture(modRes("block/barrel/oak_crate_top"), darkPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .addTile(() -> BlockEntityType.BARREL)
                .setTabKey(tab)
                .build();
        this.addEntry(crate);

        reinforcedCrate = SimpleEntrySet.builder(WoodType.class, "crate", "reinforced",
                        getModBlock("reinforced_oak_crate"), () -> VanillaWoodTypes.OAK,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/barrel/reinforced_oak_crate_side"), darkPalette)
                .addTexture(modRes("block/barrel/reinforced_oak_crate_top"), darkPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .addTile(() -> BlockEntityType.BARREL)
                .setTabKey(tab)
                .build();
        this.addEntry(reinforcedCrate);

        wallTorch = SimpleEntrySet.builder(WoodType.class, "wall_torch",
                        getModBlock("spruce_wall_torch"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new WallTorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14))
                )
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addTag(modRes("wall_torch"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noItem()
                .build();
        this.addEntry(wallTorch);

        torch = SimpleEntrySet.builder(WoodType.class, "torch",
                        getModBlock("spruce_torch"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new TorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14))
                )
                //TEXTURES: wallTorch
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, wallTorch.blocks.get(w), p, Direction.DOWN))
                .addTag(BlockTags.WALL_POST_OVERRIDE, Registries.BLOCK)
                .addTag(modRes("torch"), Registries.BLOCK)
                .addTag(modRes("torch"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(torch);

    }

    public static final PaletteStrategy lightPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                int leftover = p.size() - 1;

                if (leftover > 2) {
                    p.reduceDown();
                }
                else { // paletteColor must have 2 colors
                    PaletteColor paletteColor = p.get(0);
                    paletteColor.getDarkened();
                    p.add(paletteColor);
                }
            })
    );

    public static final PaletteStrategy dullPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                int leftover = p.size() - 3;

                if (leftover > 6) {
                    p.increaseInner();
                    p.remove(p.getLightest());
                    p.remove(p.getDarkest());
                    p.remove(p.getDarkest());
                    p.remove(p.getDarkest());
                }
            })
    );

    public static final PaletteStrategy dullerPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                int leftover = p.size() - 4;

                if (leftover > 6) {
                    p.remove(p.getLightest());
                    p.remove(p.getLightest());
                    p.remove(p.getDarkest());
                    p.remove(p.getDarkest());
                }
            })
    );

    public static final PaletteStrategy dullLuminance = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                while (p.size() < 8) p.increaseInner(); // necessary due to fewer than 7 paletteColors
                if (p.size() < 17) { // Not necessary for more than 16
                    for (int i = 0; i < 8; i++) {
                        p.increaseInner();
                    }
                    for (int i = 0; i < 4; i++) {
                        p.reduceUp();
                    }
                    p.reduceDown();
                    p.reduceDown();
                }
            })
    );

    public static final PaletteStrategy darkPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                if (p.size() > 25) {
                    while (p.size() > 6) {
                        p.reduce();
                    }
                }
                p.increaseInner();
                p.increaseInner();
                p.increaseInner();
                p.reduceUp();
                p.reduceUp();
                p.reduceDown();
            })
    );

    public static final PaletteStrategy darkerPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                p.reduceUp();
            })
    );

    public static final PaletteStrategy panelPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                p.increaseInner();
                p.reduceDown();
                p.increaseInner();
                p.reduceDown();
                p.increaseInner();
                p.reduceUp();
            })
    );

    public static final PaletteStrategy polishedPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                PaletteColor darker = p.getDarkest(); // 2nd darkest after 1st darkest
                p.reduceDown();
                p.matchLuminanceStep(0.030F);
                p.matchSize(11);
                p.add(darker);
            })
    );

    @Override
    // RECIPES, LOOT_TABLES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, handler)-> {
            // use this. also set the entry to no drop so we don't have 2.
            // why do we need this instead of copy parent drop? macaw has doors too and they work
            // chipped adds their loot not via loot table. this is why we need this. no other mod should need this stuff
            // this shouldnt be needed.... why isnt copy parent loot working?
            List<EntrySet<?>> doors = this.getEntries().stream().filter(
                    e -> e.getName().contains("door") && !e.getName().contains("trapdoor")).toList();
            for (var e : doors) {
                if (e instanceof SimpleEntrySet<?, ?> se) {
                    for (var d : se.blocks.values()) {
                        handler.addLootTable(d, createDoorLoot(d));
                    }
                }
            }

            addCarpenterRecipe(handler, "planks");
            addCarpenterRecipe(handler, "door");
            addCarpenterRecipe(handler, "trapdoor");
            addCarpenterRecipe(handler, "log");
            addCarpenterRecipe(handler, "stripped_log");
        });
    }

    public static LootTable.Builder createDoorLoot(Block block) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))));
    }


    @SuppressWarnings("SameParameterValue")
    private void addCarpenterRecipe(ResourceSink pack, String identifier) {
        JsonArray jsonArray = new JsonArray();

        for (var woodType : WoodTypeRegistry.INSTANCE) {
            if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

            boolean isTagCreated = false;
            String suffixedFile = (identifier.equals("stripped_log"))
                    ? woodType.getAppendableIdWith("stripped", "log")
                    : woodType.getAppendableIdWith(identifier);

            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(
                    shortenedId() + "/" + suffixedFile));

            for (var entry : this.getEntries()) {
                String name = entry.getName();

                boolean isStrippedLog = identifier.equals("stripped_log") && name.contains("stripped");

                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*") || isStrippedLog) {
                    if (identifier.equals("door") && name.matches(".*(_trapdoor|trapdoor_).*")) continue;
                    if (identifier.equals("log") && name.matches(".*(_stripped_log|stripped_).*")) continue;
                    if (identifier.equals("stripped_log") && !name.contains("stripped")) continue;
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(woodType);
                    if (item != null) {
                        isTagCreated = true;
                        tagBuilder.addEntry(item);
                    }
                }
            }


            // Checking for Child of wood type exist
            if (woodType.getChild(identifier) != null) {
                switch (identifier) { // Adds normal or modded blockType
                    case "planks" -> tagBuilder.addEntry(woodType.planks);
                    case "door" -> tagBuilder.addEntry(woodType.getChild("door"));
                    case "trapdoor" -> tagBuilder.addEntry(woodType.getChild("trapdoor"));
                    case "log" -> tagBuilder.addEntry(woodType.log);
                    case "stripped_log" -> tagBuilder.addEntry(woodType.getChild("stripped_log"));
                }
            }

            if (isTagCreated) {
                pack.addTag(tagBuilder, Registries.ITEM);
                pack.addTag(tagBuilder, Registries.BLOCK);
                jsonArray.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + "carpenters_table");
        jo.add("tags", jsonArray);
        pack.addJson(EveryCompat.res(shortenedId() + "/" + "carpenters_table" + "_" + identifier), jo, ResType.RECIPES);

    }

    public void createLogTexture(ResourceLocation textureResLoc, ResourceLocation innerMaskResLoc, ResourceLocation outerMaskResLoc,
                                 String textureFilename, String folderName, String suffix,
                                 SimpleEntrySet<WoodType, Block> mainBlock, String innerType, String outerType,
                                 ResourceSink sink, ResourceManager manager) {
        try (
             TextureImage mainTexture = TextureImage.open(manager, textureResLoc);
             TextureImage innerMask = TextureImage.open(manager, innerMaskResLoc);
             TextureImage outerMask = TextureImage.open(manager, outerMaskResLoc)
        ) {
            // Recoloring the texture - TOP
            Respriter innerResprite = Respriter.masked(mainTexture, innerMask);

            mainBlock.blocks.forEach((woodType, block) -> {
                try (
                     TextureImage innerColoring = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, woodType.getBlockOfThis(innerType)));
                     TextureImage outerColoring = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, woodType.getBlockOfThis(outerType), CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {

                    // Adding to the resource
                    String suffixed = "";
                    if (!suffix.isEmpty()) suffixed = "_"+suffix;
                    String newPath = "block/"+ shortenedId()+"/"+woodType.getAppendableId() +"_"+folderName+"/"+ textureFilename.replace("oak", woodType.getTypeName()) + suffixed;

                    sink.addTextureIfNotPresent(manager, newPath, () -> {
                        try(TextureImage recoloredInner = innerResprite.recolorWithAnimationOf(innerColoring)) {
                            Respriter outerResprite = Respriter.masked(recoloredInner, outerMask);
                            return outerResprite.recolorWithAnimationOf(outerColoring);
                        }
                    });
                }
                catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to generate planked_log texture for {} : {}", woodType.getId(), String.valueOf(e));
                }
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to get textures for planked_logs: {}", String.valueOf(e));
        }

    }
}
