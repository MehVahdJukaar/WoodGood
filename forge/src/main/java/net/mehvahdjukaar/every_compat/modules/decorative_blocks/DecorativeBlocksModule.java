package net.mehvahdjukaar.every_compat.modules.decorative_blocks;


import com.mojang.datafixers.util.Pair;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import lilypuree.decorative_blocks.core.DBItems;
import lilypuree.decorative_blocks.items.SeatItem;
import lilypuree.decorative_blocks.items.SupportItem;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecorativeBlocksModule extends SimpleModule {

    public final Map<WoodType, DBWoodType> WT_CONVERSION = new HashMap<>();
    public final SimpleEntrySet<WoodType, Block> BEAMS;
    public final SimpleEntrySet<WoodType, Block> PALISADES;
    public final SimpleEntrySet<WoodType, Block> SUPPORTS;
    public final SimpleEntrySet<WoodType, Block> SEATS;

    public DecorativeBlocksModule(String modId) {
        super(modId, "db");


        BEAMS = SimpleEntrySet.builder(WoodType.class, "beam",
                        () -> DBBlocks.BEAMS.get(VanillaWoodTypes.OAK).get(), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> DBBlocks.createDecorativeBlock(WT_CONVERSION.get(w), WoodDecorativeBlockTypes.BEAM))
                .addTag(modRes("beams"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("beams"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(() -> DBItems.ITEM_GROUP)
                .setPalette(this::makeDBPalette)
                .addTexture(modRes("block/oak_beam_end"))
                .addTexture(modRes("block/oak_beam_side"))
                .build();

        this.addEntry(BEAMS);


        PALISADES = SimpleEntrySet.builder(WoodType.class, "palisade",
                        () -> DBBlocks.PALISADES.get(VanillaWoodTypes.OAK).get(), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> DBBlocks.createDecorativeBlock(WT_CONVERSION.get(w), WoodDecorativeBlockTypes.PALISADE))
                .addTag(modRes("palisades"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("palisades"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(() -> DBItems.ITEM_GROUP)
                .setPalette(this::makeDBPalette)
                .addTexture(modRes("block/oak_palisade_end"))
                .addTexture(modRes("block/oak_palisade_side"))
                .build();

        this.addEntry(PALISADES);


        SUPPORTS = SimpleEntrySet.builder(WoodType.class, "support",
                        () -> DBBlocks.SUPPORTS.get(VanillaWoodTypes.OAK).get(), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> DBBlocks.createDecorativeBlock(WT_CONVERSION.get(w), WoodDecorativeBlockTypes.SUPPORT))
                .addTag(modRes("supports"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("supports"), Registry.ITEM_REGISTRY)
                .addCustomItem((w, b, p) -> new SupportItem(b, p))
                .defaultRecipe()
                .setTab(() -> DBItems.ITEM_GROUP)
                .setPalette(this::makeDBPalette)
                .addTexture(modRes("block/oak_support_end"))
                .addTexture(modRes("block/oak_support_side"))
                .build();


        this.addEntry(SUPPORTS);


        SEATS = SimpleEntrySet.builder(WoodType.class, "seat",
                        () -> DBBlocks.SEATS.get(VanillaWoodTypes.OAK).get(), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> DBBlocks.createDecorativeBlock(WT_CONVERSION.get(w), WoodDecorativeBlockTypes.SEAT))
                .addTag(modRes("seats"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("seats"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new SeatItem(b, p))
                .setTab(() -> DBItems.ITEM_GROUP)
                .setPalette(this::makeDBPalette)
                .addTexture(modRes("block/oak_seat"))
                .build();

        this.addEntry(SEATS);

    }

    public Pair<List<Palette>, AnimationMetadataSection> makeDBPalette(WoodType woodType, ResourceManager manager) {
        try (TextureImage plankTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, woodType.planks))) {

            List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);
            return Pair.of(targetPalette, plankTexture.getMetadata());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes) {
        woodTypes.forEach(w -> WT_CONVERSION.put(w, new DBWoodType(w)));
        super.registerWoodBlocks(registry, woodTypes);
    }

}