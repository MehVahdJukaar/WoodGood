package net.mehvahdjukaar.every_compat.misc;

import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// ONLY Minecraft's models/block & models/item that need to be generated - Used by Gems-Realm
public record ModelConfiguration(Set<ResourceLocation> blockModel, Set<ResourceLocation> itemModel) {

    public static final ModelConfiguration EMPTY = new ModelConfiguration(Set.of(), Set.of());

    public static ModelConfiguration createNew() {
        return new ModelConfiguration(new HashSet<>(), new HashSet<>());
    }

    /**
     * @param blockModels Add one or a list of models/block files
     **/
    public void addBlockModel(ResourceLocation... blockModels) {
        this.blockModel.addAll(List.of(blockModels));
    }

    /**
     * @param itemModels Add a list of models/item files
     */
    public void addItemModel(ResourceLocation... itemModels) {
        this.itemModel.addAll(List.of(itemModels));
    }

}

