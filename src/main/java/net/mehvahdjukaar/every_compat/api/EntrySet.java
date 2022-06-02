package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.CompatCabinetBlockTile;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class EntrySet<T extends BlockType, B extends Block> {

    public final String baseName;
    public final Map<T, B> blocks = new HashMap<>();
    public final Map<T, Item> items = new HashMap<>();


    public EntrySet(String baseName) {
        this.baseName = baseName;
    }

    public String getName() {
        return baseName;
    }

    protected abstract Class<T> getType();

    public abstract void addTranslations(AfterLanguageLoadEvent lang);

    public void registerWoodBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        if (WoodType.class == getType()) {
            registerBlocks(module, registry, (Collection<T>) woodTypes);
        }
    }

    public void registerLeavesBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {
        if (LeavesType.class == getType()) {
            registerBlocks(module, registry, (Collection<T>) leavesTypes);
        }
    }

    public abstract void registerBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<T> woodTypes);

    public abstract void registerItems(CompatModule module, IForgeRegistry<Item> registry);

    public abstract void registerTiles(CompatModule module, IForgeRegistry<BlockEntityType<?>> registry);

    public abstract void setRenderLayer();

    public abstract void addTags(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void addLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void addRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager);

    public abstract void addModels(CompatModule module, DynamicTexturePack pack, ResourceManager manager);

    public abstract void addTextures(CompatModule module, RPAwareDynamicTextureProvider handler, ResourceManager manager);


    public abstract void registerEntityRenderers(CompatModule simpleModule, EntityRenderersEvent.RegisterRenderers event);
}
