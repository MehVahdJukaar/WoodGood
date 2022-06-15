package net.mehvahdjukaar.every_compat.modules.project_brazier;

import net.dark_roleplay.projectbrazier.feature.blocks.templates.HAxisDecoBlock;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierBlocks;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierCreativeTabs;
import net.dark_roleplay.projectbrazier.util.MaterialRegistryObject;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;


public class ProjectBrazierModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FIREWOODS;
    public static final RegistryObject<Block> FIREWOOD = null;

    public ProjectBrazierModule(String modId) {
        super(modId, "pb");

        FIREWOODS = SimpleEntrySet.builder(WoodType.class,"firewood",
                        FIREWOOD, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.planks).strength(1.5f, 2.3f), "full_block"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs.DECORATION.get())
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(FIREWOODS);
    }
}
