package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwpaths.kikoz.init.BlockInit;
import com.mcwpaths.kikoz.init.TabInit;
import com.mcwpaths.kikoz.objects.FacingPathBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


//SUPPORT v1.0.4+
public class MacawPathsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> PLANKS_PATHS;

    public MacawPathsModule(String modId) {
        super(modId, "mcp");
        var tab = modRes("pathsitemgroup");


        PLANKS_PATHS = SimpleEntrySet.builder(WoodType.class, "planks_path",
                        BlockInit.OAK_PLANKS_PATH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FacingPathBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/oak_planks_path"))
                .build();
        this.addEntry(PLANKS_PATHS);
    }
}
