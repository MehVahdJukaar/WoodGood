package net.mehvahdjukaar.every_compat.modules.fabric.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.block.connected.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class CreateClientModule {

    public static void onClientSetup(CreateModule module) {
        module.windows.blocks.forEach((w, b) -> {
            String path = "block/" + module.shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";

            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
                    EveryCompat.res(path), EveryCompat.res(path + "_connected"));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(b),
                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(module.windowPanes.blocks.get(w)),
                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
        });

    }

    //we could also remove this and run getCT before client setup
    public static void onTextureStitch(ClientPlatformHelper.AtlasTextureEvent event, CreateModule module) {
        module.windows.blocks.forEach((w, b) -> {
            String path = "block/" + module.shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window_connected";
            event.addSprite(EveryCompat.res(path));
        });
    }
}
