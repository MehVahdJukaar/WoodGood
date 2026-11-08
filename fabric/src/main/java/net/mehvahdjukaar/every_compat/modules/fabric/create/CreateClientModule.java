package net.mehvahdjukaar.every_compat.modules.fabric.create;

import com.zurrtum.create.client.AllModels;
import com.zurrtum.create.client.foundation.block.connected.AllCTTypes;
import com.zurrtum.create.client.foundation.block.connected.CTSpriteShiftEntry;
import com.zurrtum.create.client.foundation.block.connected.CTSpriteShifter;
import com.zurrtum.create.client.foundation.block.connected.GlassPaneCTBehaviour;
import com.zurrtum.create.client.foundation.block.connected.HorizontalCTBehaviour;
import com.zurrtum.create.client.infrastructure.model.CTModel;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleModuleClient;

public class CreateClientModule extends SimpleModuleClient<CreateModule> {

    public CreateClientModule(CreateModule module) {
        super(module);
    }

    @Override
    public void onClientSetup() {
        super.onClientSetup();
        module.windows.blocks.forEach((w, b) -> {
            String path = "block/" + module.shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";

            // _connected/1.._connected/3
            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
                    EveryCompat.res(path), EveryCompat.res(path + "_connected"));

            AllModels.register(b, CTModel.of(new HorizontalCTBehaviour(spriteShift)));
            var pane = module.windowPanes.blocks.get(w);
            if (pane != null) AllModels.register(pane, CTModel.of(new GlassPaneCTBehaviour(spriteShift)));
        });
    }
}
