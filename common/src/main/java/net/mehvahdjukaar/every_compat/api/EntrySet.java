package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface EntrySet<T extends BlockType> {


    String getName();

    @NotNull
    default String getChildKey(CompatModule module) {
        return module.getModId() + ":" + getName();
    }

    Class<T> getTypeClass();

    void addTranslations(CompatModule module, AfterLanguageLoadEvent lang);

    default void registerWoodBlocks(CompatModule module, Registrator<Block> registry, Collection<WoodType> woodTypes) {
        if (WoodType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) woodTypes);
        }
    }

    default void registerLeavesBlocks(CompatModule module, Registrator<Block> registry, Collection<LeavesType> leavesTypes) {
        if (LeavesType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) leavesTypes);
        }
    }

    void registerBlocks(CompatModule module, Registrator<Block> registry, Collection<T> woodTypes);

    void registerItems(CompatModule module, Registrator<Item> registry);

    void registerTiles(CompatModule module, Registrator<BlockEntityType<?>> registry);

    void setRenderLayer();

    void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    void generateLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    void generateModels(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager);

    void generateTextures(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager);


    void registerEntityRenderers(CompatModule simpleModule, ClientHelper.BlockEntityRendererEvent event);

    default void setupExistingTiles() {
    }

    //used for tabs
    @Nullable
    default Item getItemOf(T type){
        return null;
    }

    void registerItemColors(ClientHelper.ItemColorEvent event);

    void registerBlockColors(ClientHelper.BlockColorEvent event);

    void registerItemsToExistingTabs(RegHelper.ItemToTabEvent event);
}
