package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import com.mcwpaths.kikoz.MacawsPaths;
import com.mcwpaths.kikoz.init.BlockInit;
import com.mcwpaths.kikoz.objects.FacingPathBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT v1.0.4+
public class MacawPathsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> PLANKS_PATHS;

    public MacawPathsModule(String modId) {
        super(modId, "mcp");

        PLANKS_PATHS = SimpleEntrySet.builder(WoodType.class, "planks_path",
                        () -> BlockInit.OAK_PLANKS_PATH, () -> VanillaWoodTypes.OAK,
                        w -> new FacingPathBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks_path"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(MacawsPaths.PATHGROUP)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(PLANKS_PATHS);
    }
}
