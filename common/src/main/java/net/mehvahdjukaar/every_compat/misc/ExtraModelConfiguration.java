package net.mehvahdjukaar.every_compat.misc;

import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// Add models/block & models/item that are either vanilla or got somehow skipped. They need to be generated
/// <br><br>Used by Gems-Realm, EveryCompat's CopperAgeBackportModule
public record ExtraModelConfiguration(Set<ResourceLocation> blockModel, Set<ResourceLocation> itemModel,
                                      // Look at ResourceUtils's gatherNonVanillaModels because some non-vanilla models that somehow got skipped
                                      // I'll investigate and apply the fix later
                                      boolean includeInGeneration) {

    public static final ExtraModelConfiguration EMPTY = new ExtraModelConfiguration(Set.of(), Set.of(), false);

    public static ExtraModelConfiguration createNew() {
        return new ExtraModelConfiguration(new HashSet<>(), new HashSet<>(), false);
    }

    public static ExtraModelConfiguration createNew(boolean includeInGeneration) {
        return new ExtraModelConfiguration(new HashSet<>(), new HashSet<>(), includeInGeneration);
    }

    /**
     * @param resourceLocations Add one or a list of models/block files
     **/
    public void addModelsBlock(ResourceLocation... resourceLocations) {
        this.blockModel.addAll(List.of(resourceLocations));
    }

    /**
     * @param resourceLocations Add a list of models/item files
     */
    public void addModelsItem(ResourceLocation... resourceLocations) {
        this.itemModel.addAll(List.of(resourceLocations));
    }

}

