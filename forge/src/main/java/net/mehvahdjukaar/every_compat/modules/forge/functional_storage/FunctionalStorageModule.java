package net.mehvahdjukaar.every_compat.modules.forge.functional_storage;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.block.DrawerBlock;
import com.buuz135.functionalstorage.util.IWoodType;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;

public class FunctionalStorageModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> drawer_1;

    public FunctionalStorageModule(String modId) {
        super(modId, "fs");


        drawer_1 = SimpleEntrySet.builder(WoodType.class, "1",
                        getModBlock("oak_1"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_1, Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_front_1"))
                .addTexture(modRes("block/oak_side"))
                .build();
        this.addEntry(drawer_1);

    }


    private final Map<WoodType, WoodTypeWrapper> woodTypeWrappers = new HashMap<>();

    private WoodTypeWrapper wrap(WoodType woodType) {
        return woodTypeWrappers.computeIfAbsent(woodType, WoodTypeWrapper::new);
    }

    public record WoodTypeWrapper(WoodType woodType) implements IWoodType {

        public Block getWood() {
            return woodType.log;
        }

        public Block getPlanks() {
            return woodType.planks;
        }

        public String getName() {
            return woodType.getTypeName();
        }
    }

}