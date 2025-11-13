package net.mehvahdjukaar.every_compat.modules.fabric.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.modules.create.CreateModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

//SUPPORT: v6.0.8+
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
    }

    protected WindowBlock makeWindow(WoodType w) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false)
                .isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false)
                .isViewBlocking((s, l, ps) -> false),
                false);
    }

    @Override
    protected Block makeConnectedGlassPane(WoodType woodType) {
        return new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.clientStuff(this);
    }

    @Environment(EnvType.CLIENT)
    private static class CreateClientModule {
        private static void clientStuff(CreateModule module) {
            module.windows.blocks.forEach((woodType, block) -> {
                String path = woodType.createFullIdWith("", "block", module.shortenedId(), "palettes/", "window");

                CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
                        EveryCompat.res(path), EveryCompat.res(path.concat("_connected")));

                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                        model -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(module.windowPanes.blocks.get(woodType)),
                        model -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
            });
        }
    }

}