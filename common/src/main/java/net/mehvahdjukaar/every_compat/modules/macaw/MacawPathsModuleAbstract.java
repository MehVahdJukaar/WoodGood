package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

///SUPPORT: v1.1.1+
public abstract class MacawPathsModuleAbstract extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> planks_path;

    public MacawPathsModuleAbstract(String modId) {
        super(modId, "mcp");

        ResourceLocation temp; //TODO: Remove when the fix is applied to MacawPaths, should be v1.1.2 or newer
        if (BuiltInRegistries.CREATIVE_MODE_TAB.containsKey(modRes("pathgroup")))
            temp = modRes("pathgroup");
        else
            temp = ResourceLocation.parse("mod_id:pathgroup");

        Supplier<CreativeModeTab> tab = (PlatHelper.getPlatform().isFabric()) ? getTab(temp) : getModTab("pathsitemgroup");

        planks_path = SimpleEntrySet.builder(WoodType.class, "planks_path",
                        getModBlock("oak_planks_path"), () -> VanillaWoodTypes.OAK,
                        this::newFacingPathBlock
                )
                .addTexture(modRes("block/oak_planks_path"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(planks_path);
    }

    public abstract Block newFacingPathBlock(WoodType woodType);
}
