package net.mehvahdjukaar.every_compat.modules.decorative_blocks;

import lilypuree.decorative_blocks.blocks.BeamBlock;
import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.blocks.SeatBlock;
import lilypuree.decorative_blocks.blocks.SupportBlock;
import lilypuree.decorative_blocks.items.SeatItem;
import lilypuree.decorative_blocks.items.SupportItem;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//NAME: Decorative Blocks Reborn
//SUPPORT: v6.0.1+
public class DecorativeBlocksModule extends EveryCompatModule {

    public final Map<WoodType, net.minecraft.world.level.block.state.properties.WoodType> wtConversion = new HashMap<>();
    public final SimpleEntrySet<WoodType, Block> beams;
    public final SimpleEntrySet<WoodType, Block> palisades;
    public final SimpleEntrySet<WoodType, Block> supports;
    public final SimpleEntrySet<WoodType, Block> seats;

    public DecorativeBlocksModule(String modId) {
        super(modId, "db");
        ResourceLocation tab = modRes("general");

        beams = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> VanillaWoodTypes.OAK,
                        w -> new BeamBlock(wtConversion.get(w),
                                copyStandardProperties(w, 1.2F, 0)
                        )
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(modRes("beams_that_burn"), Registries.BLOCK)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(modRes("beams"), Registries.ITEM)
                .addTag(modRes("beams_that_burn"), Registries.ITEM)
                .defaultRecipe()
                .setTab(getModTab(tab))
                .addTexture(modRes("block/oak_beam_end"), PaletteStrategies.SIGN_LIKE)
                .addTexture(modRes("block/oak_beam_side"), PaletteStrategies.SIGN_LIKE)
                .build();
        this.addEntry(beams);


        palisades = SimpleEntrySet.builder(WoodType.class, "palisade",
                        getModBlock("oak_palisade"), () -> VanillaWoodTypes.OAK,
                        w -> new PalisadeBlock(wtConversion.get(w),
                                copyStandardProperties(w, 2.0F, 4.0F)
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("palisades"), Registries.BLOCK)
                .addTag(modRes("palisades_that_burn"), Registries.BLOCK)
                .addTag(modRes("palisades"), Registries.ITEM)
                .addTag(modRes("palisades_that_burn"), Registries.ITEM)
                .defaultRecipe()
                .setTab(getModTab(tab))
                .addTexture(modRes("block/oak_palisade_end"), PaletteStrategies.SIGN_LIKE)
                .addTexture(modRes("block/oak_palisade_side"), PaletteStrategies.SIGN_LIKE)
                .build();
        this.addEntry(palisades);


        supports = SimpleEntrySet.builder(WoodType.class, "support",
                        getModBlock("oak_support"), () -> VanillaWoodTypes.OAK,
                        w -> new SupportBlock(wtConversion.get(w),
                                copyStandardProperties(w, 1.2F, 0)
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("supports"), Registries.BLOCK)
                .addTag(modRes("supports_that_burn"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.ITEM)
                .addTag(modRes("supports_that_burn"), Registries.ITEM)
                .addCustomItem((w, b, p) -> new SupportItem(b, p))
                .defaultRecipe()
                .setTab(getModTab(tab))
                .addTexture(modRes("block/oak_support_end"), PaletteStrategies.SIGN_LIKE)
                .addTexture(modRes("block/oak_support_side"), PaletteStrategies.SIGN_LIKE)
                .build();
        this.addEntry(supports);


        seats = SimpleEntrySet.builder(WoodType.class, "seat",
                        getModBlock("oak_seat"), () -> VanillaWoodTypes.OAK,
                        w -> new SeatBlock(wtConversion.get(w),
                                copyStandardProperties(w, 1.2F, 0)
                        )
                )
                .requiresChildren(FENCE, SLAB) //REASON: recipes
                .addTexture(modRes("block/oak_seat"), PaletteStrategies.SIGN_LIKE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("seats_that_burn"), Registries.BLOCK)
                .addTag(modRes("seats"), Registries.BLOCK)
                .addTag(modRes("seats"), Registries.ITEM)
                .defaultRecipe()
                .setTab(getModTab(tab))
                .addCustomItem((w, b, p) -> new SeatItem(b, p))
                .build();
        this.addEntry(seats);

    }

    public BlockBehaviour.Properties copyStandardProperties(WoodType woodType, float destroyTimeOrStrength, float explosiveResistence) {
        if (explosiveResistence != 0)
            return BlockBehaviour.Properties.of().mapColor(woodType.getColor()).sound(woodType.getSound())
                    .strength(destroyTimeOrStrength, explosiveResistence)
                    .ignitedByLava();
        else
            return BlockBehaviour.Properties.of().mapColor(woodType.getColor()).sound(woodType.getSound())
                    .strength(destroyTimeOrStrength)
                    .ignitedByLava();
    }

    @Override
    public <T extends BlockType> void registerBlocks(Class<T> typeClass,
                                                     Registrator<Block> registry, Collection<T> types) {
        if (typeClass == WoodType.class) {
            types.forEach(w -> wtConversion.put((WoodType) w,
                    new net.minecraft.world.level.block.state.properties.WoodType(w.getTypeName(), ((WoodType) w).toVanillaOrOak().setType())));
        }
        super.registerBlocks(typeClass, registry, types);
    }

}