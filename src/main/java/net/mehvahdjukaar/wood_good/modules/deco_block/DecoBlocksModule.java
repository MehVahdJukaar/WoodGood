package net.mehvahdjukaar.wood_good.modules.deco_block;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.BeamBlock;
import lilypuree.decorative_blocks.blocks.PalisadeBlock;
import lilypuree.decorative_blocks.core.DBItems;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.StaticResource;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DecoBlocksModule extends CompatModule {

    public DecoBlocksModule(String modId) {
        super(modId);
    }

    private final Map<WoodType, DBWoodType> DB_WOOD_TYPES = new HashMap<>();

    private static final String PALISADE_NAME = "palisade";
    public Map<WoodType, PalisadeBlock> PALISADES = new HashMap<>();
    public Map<WoodType, Item> PALISADE_ITEMS = new HashMap<>();

    private static final String BEAM_NAME = "beam";
    public Map<WoodType, BeamBlock> BEAMS = new HashMap<>();
    public Map<WoodType, Item> BEAM_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        woodTypes.forEach(w -> DB_WOOD_TYPES.put(w, new DBWoodType(w)));

        for (WoodType w : woodTypes) {
            String name = w.getVariantId(BEAM_NAME, false);
            if (!shouldRegisterEntry(name, registry) || w.isVanilla()) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            BlockBehaviour.Properties woodProperty = BlockBehaviour.Properties.of(wood.getMaterial(), wood.getMaterialColor()).strength(1.2F).sound(wood.getSoundType());
            BeamBlock block = new BeamBlock(woodProperty, wood);
            BEAMS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }

    }

    private final Item.Properties modItemProperties = (new Item.Properties()).tab(Constants.ITEM_GROUP);

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        BEAMS.forEach((key, value) -> {
            Item i = new BlockItem(value, modItemProperties);
            BEAM_ITEMS.put(key,i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager, LangBuilder langBuilder) {
        StaticResource beamItemModel = handler.getResOrLog(manager, ResType.ITEM_MODELS.getPath(modRes("oak_beam")));
        StaticResource beamBlockModel = handler.getResOrLog(manager, ResType.BLOCK_MODELS.getPath(modRes("oak_beam_y")));

        BEAM_ITEMS.forEach((wood, value) -> {
            String id = value.getRegistryName().getPath();
            langBuilder.addEntry(value, wood.getNameForTranslation(BEAM_NAME));

            try {
                handler.dynamicPack.addSimilarJsonResource(beamItemModel, "decorative_blocks:block/oak_beam",
                         "wood_good:block/"+ id);
                handler.dynamicPack.addSimilarJsonResource(beamBlockModel, "oak_beam", id);
            } catch (Exception ex) {
                handler.getLogger().error("Failed to generate Hanging Sign blockstate definition for {} : {}", value, ex);
            }


        });

    }
}
