package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwpaths.kikoz.MacawsPaths;
import com.mcwpaths.kikoz.init.BlockInit;
import com.mcwpaths.kikoz.objects.FacingPathBlock;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;


public class MacawPathsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> PLANKS_PATHS;

    public MacawPathsModule(String modId) {
        super(modId, "mcp");
        CreativeModeTab tab;
        try {
            var f = ObfuscationReflectionHelper.findField(MacawsPaths.class, "PathsItemGroup");
            tab = (CreativeModeTab) f.get(null);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to initialize {}", this);
            PLANKS_PATHS = null;
            return;
        }

        PLANKS_PATHS = SimpleEntrySet.builder(WoodType.class,"planks_path",
                        BlockInit.OAK_PLANKS_PATH, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FacingPathBlock(WoodGood.copySafe(w.planks).strength(1.5f, 2.3f)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("block/oak_planks_path"))
                .build();

        this.addEntry(PLANKS_PATHS);
    }
}
