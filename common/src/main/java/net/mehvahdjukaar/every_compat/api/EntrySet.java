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
import java.util.HashMap;
import java.util.Map;

public abstract class EntrySet<T extends BlockType, B extends Block, I extends Item> {

    public final String typeName;
    public final Map<T, B> blocks = new HashMap<>();
    public final Map<T, I> items = new HashMap<>();


    protected EntrySet(String baseName) {
        this.typeName = baseName;
    }

    public String getName() {
        return typeName;
    }

    @NotNull
    public String makeChildKey(CompatModule module) {
        return module.getModId() + ":" + getName();
    }

    protected abstract Class<T> getTypeClass();

    public abstract void addTranslations(CompatModule module, AfterLanguageLoadEvent lang);

    @SuppressWarnings("unchecked")
    public void registerWoodBlocks(CompatModule module, Registrator<Block> registry, Collection<WoodType> woodTypes) {
        if (WoodType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) woodTypes);
        }
    }

    @SuppressWarnings("unchecked")
    public void registerLeavesBlocks(CompatModule module, Registrator<Block> registry, Collection<LeavesType> leavesTypes) {
        if (LeavesType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) leavesTypes);
        }
    }

    public abstract void registerBlocks(CompatModule module, Registrator<Block> registry, Collection<T> woodTypes);

    public abstract void registerItems(CompatModule module, Registrator<Item> registry);

    public abstract void registerTiles(CompatModule module, Registrator<BlockEntityType<?>> registry);

    public abstract void setRenderLayer();

    public abstract void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void generateLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void generateModels(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager);

    public abstract void generateTextures(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager);


    public abstract void registerEntityRenderers(CompatModule simpleModule, ClientHelper.BlockEntityRendererEvent event);

    public abstract void registerItemsToExistingTabs(SimpleModule module, RegHelper.ItemToTabEvent event);

    //used for tabs
    @Nullable
    public Item getItemOf(BlockType type) {
        return null;
    }

    @Nullable
    public Item getItemForECTab(BlockType type) {
        return this.getItemOf(type);
    }


    public void setupExistingTiles() {}
}
