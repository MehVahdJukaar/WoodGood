package net.mehvahdjukaar.every_compat.modules.project_brazier;

import net.dark_roleplay.marg.common.material.MargMaterial;
import net.dark_roleplay.marg.common.material.MaterialCondition;
import net.dark_roleplay.projectbrazier.feature.blocks.templates.HAxisDecoBlock;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierBlocks;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierCreativeTabs;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;


public class ProjectBrazierModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FIREWOODS;
//    public static final RegistryObject<Block> FIREWOOD = null;

    public ProjectBrazierModule(String modId) {
        super(modId, "pb");

        FIREWOODS = SimpleEntrySet.builder(WoodType.class, "firewood",
                        () -> this.getModBlock("oak_firewood"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.FIREWOOD_CON, w ->
                                new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.log)
                                        .strength(2.0F, 3.0F)
                                        .noOcclusion(),
                                        "full_block")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(()->BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();
        
        this.addEntry(FIREWOODS);
    }

    public <T extends BlockType, B extends Block> Function<T, @Nullable B> ifCond(MaterialCondition cond, Function<T, B> supplier) {
        return ifHasChild(supplier, cond.getTextures().toArray(String[]::new));
    }
}
