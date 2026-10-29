package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.regex.Pattern;

/// Tags every entry made from a block type matching the arguments
public record BlockTypeTag(Class<? extends BlockType> typeClass, Pattern typeId, @Nullable Pattern entryName, TagKey<?> tag) {

    public void addTo(AbstractSimpleEntrySet<?, ?, ?> entrySet, ResourceSink sink) {
        if (!typeClass.isAssignableFrom(entrySet.getTypeClass())) return;
        Map<? extends BlockType, ? extends ItemLike> entries;
        if (tag.registry().equals(Registries.BLOCK)) entries = entrySet.blocks;
        else entries = entrySet.items;

        SimpleTagBuilder builder = SimpleTagBuilder.of(tag.location());
        boolean hasEntries = false;
        for (var e : entries.entrySet()) {
            BlockType type = e.getKey();
            ItemLike entry = e.getValue();
            if (matches(type, entry) && ModEntriesConfigs.isEntryEnabled(type, entry)) {
                builder.addEntry(entry);
                hasEntries = true;
            }
        }
        if (hasEntries) sink.addTag(builder, tag.registry());
    }

    private boolean matches(BlockType type, ItemLike entry) {
        if (!typeId.matcher(type.getId().toString()).matches()) return false;
        if (entryName == null) return true;

        ResourceLocation id = entry instanceof Block b ? BuiltInRegistries.BLOCK.getKey(b) : BuiltInRegistries.ITEM.getKey(entry.asItem());
        String path = id.getPath();
        return entryName.matcher(path.substring(path.lastIndexOf('/') + 1)).matches();
    }


}