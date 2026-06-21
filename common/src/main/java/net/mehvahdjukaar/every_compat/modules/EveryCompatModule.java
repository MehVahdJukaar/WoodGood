package net.mehvahdjukaar.every_compat.modules;

import com.google.common.base.Suppliers;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

// Module for every compat mod. Just has extra utilities so we can easily query external mods.
// If you are an addon extend CompatModule instead (and just stuff in the API package)
// Its fine to extend for our own addon mods I guess
@SuppressWarnings("removal")
public abstract class EveryCompatModule extends SimpleModule {

    //DEFAULT: using EveryCompat.MOD_ID as myNamespace
    public EveryCompatModule(String modId, String shortId) {
        super(modId, shortId, EveryCompat.MOD_ID);
    }

    public EveryCompatModule(String modId, String shortId, String myNamespace) {
        super(modId, shortId, myNamespace);
    }

    protected final <T extends Block> Supplier<T> getModBlock(String id, Class<T> blockClass) {
        return memor(id, BuiltInRegistries.BLOCK);
    }

    protected final Supplier<CreativeModeTab> getModTab(String id) {
        return memor(id, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    protected final Supplier<CreativeModeTab> getTab(ResourceLocation id) {
        return Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.get(id));
    }

    protected final Supplier<CreativeModeTab> getTab(ResourceKey<CreativeModeTab> id) {
        return Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.get(id));
    }

    //internal use only. If you are a mod adding a module in your mod use by reference
    protected final Supplier<Block> getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    protected final Supplier<Item> getModItem(String id) {
        return memor(id, BuiltInRegistries.ITEM);
    }

    protected final <B extends BlockEntity> Supplier<BlockEntityType<B>> getModTile(String id, Class<B> tileEntityClass) {
        return memor(id, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    protected final Supplier<BlockEntityType<BlockEntity>> getModTile(String id) {
        return getModTile(id, BlockEntity.class);
    }

    @SuppressWarnings("unchecked")
    private <T> Supplier<T> memor(String id, Registry<?> reg) {
        return Suppliers.memoize(() -> {
            try {
                return (id.contains(":"))
                        ? (T) reg.getOptional(ResourceLocation.parse(id)).orElseThrow()
                        : (T) reg.getOptional(modRes(id)).orElseThrow();
            } catch (Throwable e) {
                throw new IllegalStateException("Could not find \"" + id + "\" in " + reg + ". This likely means that the reigstry entry was renamed in the original mod and EC needs updating. " +
                        "Is the mod, " + this.getModName().toUpperCase() + " up to date, if yes, then downgrade to the previous version & wait for an Every Compat update. Otherwise, update the mod to the latest version.");
            }
        });
    }


}
