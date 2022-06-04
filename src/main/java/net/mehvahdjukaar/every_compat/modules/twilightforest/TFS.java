package net.mehvahdjukaar.every_compat.modules.twilightforest;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TableBlock;
import net.moddingplayground.twigs.init.TwigsBlocks;
import twilightforest.block.BanisterBlock;
import twilightforest.item.TFItems;

public class TFS extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BANISTERS;

    public TFS(String modId) {
        super(modId, "tf");

        BANISTERS = SimpleEntrySet.builder("banister",
                        TwigsBlocks.OAK_TABLE, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new BanisterBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("banisters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("banisters"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(TFItems.creativeTab)
                .build();

        this.addEntry(BANISTERS);
    }
}
