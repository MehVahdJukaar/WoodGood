package net.mehvahdjukaar.every_compat.modules.neoforge.functional_storage;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.block.DrawerBlock;
import com.buuz135.functionalstorage.block.tile.DrawerTile;
import com.buuz135.functionalstorage.client.DrawerRenderer;
import com.buuz135.functionalstorage.client.item.DrawerISTER;
import com.buuz135.functionalstorage.inventory.item.DrawerStackItemHandler;
import com.buuz135.functionalstorage.util.IWoodType;
import com.hrznstudio.titanium.module.BlockWithTile;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//SUPPORT: v1.5.4+
public class FunctionalStorageModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, DrawerBlock> drawer_1;
    public final SimpleEntrySet<WoodType, DrawerBlock> drawer_2;
    public final SimpleEntrySet<WoodType, DrawerBlock> drawer_4;

    public FunctionalStorageModule(String modId) {
        super(modId, "fs");
        ResourceLocation tab = modRes("main");

        drawer_1 = SimpleEntrySet.builder(WoodType.class, "1",
                        getModBlock("spruce_1", DrawerBlock.class), () -> VanillaWoodTypes.SPRUCE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_1, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("spruce_1"))
                .addTexture(modRes("block/spruce_front_1"))
                .addTextureM(modRes("block/spruce_side"), EveryCompat.res("block/fs/spruce_side_m"))
                .addTexture(TextureInfo.of(modRes("block/spruce_front_1"))
                        .forEntityOrGui()
                        .keepNamespace())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(UtilityTag.neoforgeTag("relocation_not_supported"), Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .addTag(modRes("drawer_1x1"), Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new CompatDrawerItem(block, properties))
                .build();
        this.addEntry(drawer_1);

        drawer_2 = SimpleEntrySet.builder(WoodType.class, "2",
                        getModBlock("spruce_2", DrawerBlock.class), () -> VanillaWoodTypes.SPRUCE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_2, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("spruce_2"))
                .addTexture(modRes("block/spruce_front_2"))
                .addTexture(TextureInfo.of(modRes("block/spruce_front_2"))
                        .forEntityOrGui()
                        .keepNamespace())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(UtilityTag.neoforgeTag("relocation_not_supported"), Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .addTag(modRes("drawer_1x2"), Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new CompatDrawerItem(block, properties))
                .build();
        this.addEntry(drawer_2);

        drawer_4 = SimpleEntrySet.builder(WoodType.class, "4",
                        getModBlock("spruce_4", DrawerBlock.class), () -> VanillaWoodTypes.SPRUCE,
                        w -> new DrawerBlock(wrap(w), FunctionalStorage.DrawerType.X_4, Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("spruce_4"))
                .addTexture(modRes("block/spruce_front_4"))
                .addTexture(TextureInfo.of(modRes("block/spruce_front_4"))
                        .forEntityOrGui()
                        .keepNamespace())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(UtilityTag.neoforgeTag("relocation_not_supported"), Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.ITEM)
                .addTag(modRes("drawer_2x2"), Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new CompatDrawerItem(block, properties))
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
        var     x_1 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_1);
        var tileRO_1 = DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, modRes("spruce_1"));
        var x_2 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_2);
        var tileRO_2 = DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, modRes("spruce_2"));
        var x_4 = FunctionalStorage.DRAWER_TYPES.get(FunctionalStorage.DrawerType.X_4);
        var tileRO_4 = DeferredHolder.create(Registries.BLOCK_ENTITY_TYPE, modRes("spruce_4"));


        for (var block : drawer_1.blocks.values()) {
            var re = DeferredHolder.create(Registries.BLOCK, Utils.getID(block));
            x_1.add(new BlockWithTile(re, tileRO_1));
        }

        for (var block : drawer_2.blocks.values()) {
            var re = DeferredHolder.create(Registries.BLOCK, Utils.getID(block));
            x_2.add(new BlockWithTile(re, tileRO_2));
        }

        for (var block : drawer_4.blocks.values()) {
            var re = DeferredHolder.create(Registries.BLOCK, Utils.getID(block));
            x_4.add(new BlockWithTile(re, tileRO_4));
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

    public static class CompatDrawerItem extends DrawerBlock.DrawerItem {

        private final DrawerBlock drawerBlock;

        public CompatDrawerItem(DrawerBlock drawerBlock, Properties properties) {
            super(drawerBlock, properties, FunctionalStorage.TAB);
            this.drawerBlock = drawerBlock;
        }

        @Nullable
        public IItemHandler initCapabilities(ItemStack stack) {
            return new DrawerStackItemHandler(stack, this.drawerBlock.getType());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @Override
                public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    return switch (drawerBlock.getType()){
                        case X_2 -> DrawerISTER.SLOT_2;
                        case X_4 -> DrawerISTER.SLOT_4;
                        default -> DrawerISTER.SLOT_1;
                    };
                }
            });
        }

    }

}