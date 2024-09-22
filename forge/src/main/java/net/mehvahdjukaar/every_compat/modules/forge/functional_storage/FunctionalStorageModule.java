package net.mehvahdjukaar.every_compat.modules.forge.functional_storage;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.block.DrawerBlock;
import com.buuz135.functionalstorage.util.IWoodType;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .addTexture(TextureInfo.of(modRes("textures/block/oak_front_1"))
                        .keepNamespace())
                .build();
        this.addEntry(drawer_1);
    }


    @Override
    public void onModSetup() {
        super.onModSetup();


        var x4 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_1);
        var tileRo = RegistryObject.create(modRes("drawer_1"), ForgeRegistries.BLOCK_ENTITY_TYPES);
        for (var block : drawer_1.blocks.values()) {
            var re = RegistryObject.create(Utils.getID(block), ForgeRegistries.BLOCKS);
            x4.add(Pair.of(re, tileRo));
        }

    }

    private final Map<WoodType, WoodTypeWrapper> woodTypeWrappers = new HashMap<>();

    private IWoodType wrap(WoodType woodType) {
        return woodTypeWrappers.computeIfAbsent(woodType, WoodTypeWrapper::new);
    }

    public record WoodTypeWrapper(WoodType woodType) implements IWoodType {

        @Override
        public Block getWood() {
            return woodType.log;
        }

        @Override
        public Block getPlanks() {
            return woodType.planks;
        }

        @Override
        public String getName() {
            return woodType.getAppendableId();
        }
    }
}