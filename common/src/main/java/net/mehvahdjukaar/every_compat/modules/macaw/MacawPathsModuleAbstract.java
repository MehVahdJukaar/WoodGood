package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

///SUPPORT: (FB)-v1.1.2+ | (FG)-v1.1.1+
public abstract class MacawPathsModuleAbstract extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> planks_path;

    public MacawPathsModuleAbstract(String modId) {
        super(modId, "mcp");

        var tab = (PlatHelper.getPlatform().isFabric())
                ? modRes("pathgroup")
                : modRes("pathsitemgroup");

        planks_path = SimpleEntrySet.builder(WoodType.class, "planks_path",
                        getModBlock("oak_planks_path"), () -> VanillaWoodTypes.OAK,
                        this::newFacingPathBlock
                )
                .addTexture(modRes("block/oak_planks_path"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(planks_path);
    }

    public abstract Block newFacingPathBlock(WoodType woodType);
}
