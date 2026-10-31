package net.mehvahdjukaar.every_compat.modules.neoforge.functional_storage;

import com.buuz135.functionalstorage.block.tile.DrawerTile;
import com.buuz135.functionalstorage.client.DrawerRenderer;
import net.mehvahdjukaar.every_compat.api.SimpleModuleClient;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;

public class FunctionalStorageClient extends SimpleModuleClient<FunctionalStorageModule> {

    public FunctionalStorageClient(FunctionalStorageModule module) {
        super(module);
    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(module.drawer_1.getTile(DrawerTile.class), c -> new DrawerRenderer());
        event.register(module.drawer_2.getTile(DrawerTile.class), c -> new DrawerRenderer());
        event.register(module.drawer_4.getTile(DrawerTile.class), c -> new DrawerRenderer());
    }
}
