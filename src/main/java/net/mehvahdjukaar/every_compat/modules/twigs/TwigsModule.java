package net.mehvahdjukaar.every_compat.modules.twigs;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TableBlock;
import net.moddingplayground.twigs.init.TwigsBlocks;

import java.util.List;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> TABLES;

    public TwigsModule(String modId) {
        super(modId, "tw");

        TABLES = SimpleEntrySet.builder("table",
                        TwigsBlocks.OAK_TABLE, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks).instabreak()))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .setTab(Twigs.ITEM_GROUP)
                .addRecipe(modRes("table/oak_table"))
                .setRenderType(()->RenderType::cutout)
                .createPaletteFromOak((p)-> p.remove(p.getDarkest()))
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_table_top"))
                .addTexture(modRes("block/oak_table_bottom"))
                .build();

        this.addEntry(TABLES);
    }

}
