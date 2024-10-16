package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

public class TagUtility {

    /**
     * Get the namespace:WoodType_logs tag from the wood mods or create a new tag if not available
     *
     * @return ResourceLocation
     **/
    public static ResourceLocation getATagOrCreateANew(String suffixTag, String suffixAlt, WoodType wood, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        String resLocMOD = wood.getNamespace() + ":" + wood.getTypeName();

        // ResourceLocation
        ResourceLocation RLocLogs = new ResourceLocation(resLocMOD + "_" + suffixTag);
        ResourceLocation RLocStems = new ResourceLocation(resLocMOD + "_" + suffixAlt);
        ResourceLocation RLocFolders = new ResourceLocation(wood.getNamespace() + ":" + suffixTag + "/" + wood.getTypeName());
        ResourceLocation RLocEC = EveryCompat.res(wood.getAppendableId() + "_" + suffixTag);

        if (manager.getResource(ResType.TAGS.getPath(RLocLogs.withPrefix("blocks/"))).isPresent())
            return RLocLogs;
        else if (manager.getResource(ResType.TAGS.getPath(RLocStems.withPrefix("blocks/"))).isPresent())
            return RLocStems;
        else if (manager.getResource(ResType.TAGS.getPath(RLocEC.withPrefix("blocks/"))).isPresent())
            return RLocEC;
        else if (manager.getResource(ResType.TAGS.getPath(RLocFolders.withPrefix("blocks/"))).isPresent())
            return RLocFolders;
        else // if RLocECTags is not available, then it will be generated
            createAndAddDefaultTags(RLocEC, handler, wood);

        return RLocEC;

    }

    /**
     * The method is to create a custom Tag file with
     * DEFAULT BLOCKS - log, stripped_log, wood, stripped_wood
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddDefaultTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, WoodType wood) {
        return createAndAddCustomTags(resLoc, handler, wood.log, wood.getBlockOfThis("stripped_log"), wood.getBlockOfThis("wood"), wood.getBlockOfThis("stripped_wood"));
    }

    /**
     * Add any blocks to newly created tag
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddCustomTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, Block... blocks) {
        boolean containsSomething = false;

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(resLoc);
        // Adding blocks to tag file
        for (Block block : blocks) {
            if (block != null) {
                tagBuilder.addEntry(block);
                containsSomething = true;
            }
        }
        // Adding to the resources
        if (containsSomething) {
            handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
            handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
        }
        return containsSomething;
    }

}
