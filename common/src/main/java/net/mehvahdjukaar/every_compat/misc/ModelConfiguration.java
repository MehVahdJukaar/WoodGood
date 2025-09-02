package net.mehvahdjukaar.every_compat.misc;

import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//what is this??
/// Add models/block & models/item that need to be generated - Used by Gems-Realm
//name isnt great. ExtraModels would have been better
public record ModelConfiguration(Set<ResourceLocation> blockModel, Set<ResourceLocation> itemModel,
                             //whats this boolean for? why not just not add models instead if they should not generated. if it does other stuff name shoul be better
                             @Deprecated(forRemoval = true)    boolean includeInGeneration) {

    public static final ModelConfiguration EMPTY = new ModelConfiguration(Set.of(), Set.of(), false);

    public static ModelConfiguration createNew() {
        return new ModelConfiguration(new HashSet<>(), new HashSet<>(), false);
    }

    public static ModelConfiguration createNew(boolean includeInGeneration) {
        return new ModelConfiguration(new HashSet<>(), new HashSet<>(), includeInGeneration);
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

