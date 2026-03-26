package net.mehvahdjukaar.every_compat.modules.copper_age_backport;

import com.github.smallinger.copperagebackport.block.shelf.ShelfBlock;
import com.github.smallinger.copperagebackport.registry.ModBlockEntities;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v0.1.4+
public class CopperAgeBackportModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> shelf;

    public CopperAgeBackportModule(String modId) {
        super(modId, "cab");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        () -> BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace("oak_shelf")), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(BlockBehaviour.Properties.of()
                                .strength(2.0F, 3.0F)
                                .sound(SoundType.WOOD)
                                .noOcclusion()
                        )
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTexture(ResourceLocation.withDefaultNamespace("block/oak_shelf"), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .includeModelsBlock(
                        ResourceLocation.withDefaultNamespace("block/oak_shelf"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_center"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_inventory"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_left"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_right"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_unconnected"),
                        ResourceLocation.withDefaultNamespace("block/oak_shelf_unpowered")
                )
                // Also modify Blockstates, too
                .addModelTransform(m ->
                        m.addModifier((s, blockId, woodType) ->
                                s.replace(
                                        "\"minecraft:block/oak_shelf\"",
                                        "\"" + blockId.withPrefix("block/") + "\""
                                )
                                        .replaceAll("\"minecraft:block/oak_shelf_([a-z]+)\"",
                                                "\"" +blockId.withPrefix("block/") + "_$1\"")
                        )
                )
                .addTile(ModBlockEntities.SHELF_BLOCK_ENTITY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ResourceLocation.withDefaultNamespace("wooden_shelves"), Registries.BLOCK)
                .addTag(modRes("wooden_shelves"), Registries.BLOCK)
                .setTab(getModTab(tab))
                .addRecipe(ResourceLocation.withDefaultNamespace("oak_shelf"))
                .build();
        this.addEntry(shelf);

    }

    private Supplier<Block> getShelfBlock() {
        return () -> BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace("oak_shelf"));
    }
}