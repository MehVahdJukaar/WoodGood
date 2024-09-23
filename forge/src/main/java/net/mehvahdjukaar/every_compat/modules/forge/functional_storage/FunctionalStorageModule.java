package net.mehvahdjukaar.every_compat.modules.forge.functional_storage;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.block.DrawerBlock;
import com.buuz135.functionalstorage.block.tile.DrawerTile;
import com.buuz135.functionalstorage.client.DrawerRenderer;
import com.buuz135.functionalstorage.util.IWoodType;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class FunctionalStorageModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> drawer_1;
    public final SimpleEntrySet<WoodType, Block> drawer_2;
    public final SimpleEntrySet<WoodType, Block> drawer_4;

    public FunctionalStorageModule(String modId) {
        super(modId, "fs");

        drawer_1 = SimpleEntrySet.builder(WoodType.class, "1",
                        getModBlock("oak_1"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_1, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("oak_1"))
                .addTexture(modRes("block/oak_front_1"))
                .addTextureM(modRes("block/oak_side"), EveryCompat.res("block/fs/oak_side_m"))
                .addTexture(TextureInfo.of(modRes("block/oak_front_1"))
                        .forEntityOrGui()
                        .keepNamespace())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .setTab(getModTab("main"))
                .defaultRecipe()
                .build();
        this.addEntry(drawer_1);

        drawer_2 = SimpleEntrySet.builder(WoodType.class, "2",
                        getModBlock("oak_2"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_2, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("oak_2"))
                .addTexture(modRes("block/oak_front_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .setTab(getModTab("main"))
                .defaultRecipe()
                .build();
        this.addEntry(drawer_2);

        drawer_4 = SimpleEntrySet.builder(WoodType.class, "4",
                        getModBlock("oak_4"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_4, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("oak_4"))
                .addTexture(modRes("block/oak_front_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .setTab(getModTab("main"))
                .defaultRecipe()
                .build();
        this.addEntry(drawer_4);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(drawer_1.getTile(DrawerTile.class), c -> new DrawerRenderer());
        event.register(drawer_2.getTile(DrawerTile.class), c -> new DrawerRenderer());
        event.register(drawer_4.getTile(DrawerTile.class), c -> new DrawerRenderer());
    }

    @Override
    public void onModSetup() {
        super.onModSetup();
        var x_1 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_1);
        var tileRO_1 = RegistryObject.create(modRes("oak_1"), ForgeRegistries.BLOCK_ENTITY_TYPES);
        var x_2 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_2);
        var tileRO_2 = RegistryObject.create(modRes("oak_2"), ForgeRegistries.BLOCK_ENTITY_TYPES);
        var x_4 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_4);
        var tileRO_4 = RegistryObject.create(modRes("oak_4"), ForgeRegistries.BLOCK_ENTITY_TYPES);


        for (var block : drawer_1.blocks.values()) {
            var re = RegistryObject.create(Utils.getID(block), ForgeRegistries.BLOCKS);
            x_1.add(Pair.of(re, tileRO_1));
        }

        for (var block : drawer_2.blocks.values()) {
            var re = RegistryObject.create(Utils.getID(block), ForgeRegistries.BLOCKS);
            x_2.add(Pair.of(re, tileRO_2));
        }

        for (var block : drawer_4.blocks.values()) {
            var re = RegistryObject.create(Utils.getID(block), ForgeRegistries.BLOCKS);
            x_4.add(Pair.of(re, tileRO_4));
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
            return "fs/" + woodType.getAppendableId();
        }
    }
}