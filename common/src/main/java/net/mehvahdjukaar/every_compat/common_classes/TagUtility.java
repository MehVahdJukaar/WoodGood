package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
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
    public static ResourceLocation getATagOrCreateANew(String suffixTag, String suffixAlt, BlockType blockType, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        String resLocMOD = blockType.getNamespace() + ":" + blockType.getTypeName();

        // ResourceLocation
        ResourceLocation RLocLogs = new ResourceLocation(resLocMOD + "_" + suffixTag);
        ResourceLocation RLocStems = new ResourceLocation(resLocMOD + "_" + suffixAlt);
        ResourceLocation RLocFolders = new ResourceLocation(blockType.getNamespace() + ":" + suffixTag + "/" + blockType.getTypeName());
        ResourceLocation RLocEC = EveryCompat.res(blockType.getAppendableId() + "_" + suffixTag);

        if (manager.getResource(ResType.TAGS.getPath(RLocLogs.withPrefix("blocks/"))).isPresent())
            return RLocLogs;
        else if (manager.getResource(ResType.TAGS.getPath(RLocStems.withPrefix("blocks/"))).isPresent())
            return RLocStems;
        else if (manager.getResource(ResType.TAGS.getPath(RLocEC.withPrefix("blocks/"))).isPresent())
            return RLocEC;
        else if (manager.getResource(ResType.TAGS.getPath(RLocFolders.withPrefix("blocks/"))).isPresent())
            return RLocFolders;
        else // if RLocECTags is not available, then it will be generated
            createAndAddDefaultTags(RLocEC, handler, blockType);

        return RLocEC;

    }

    /**
     * The method is to create a custom Tag file with
     * DEFAULT BLOCKS - log, stripped_log, wood, stripped_wood
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddDefaultTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, BlockType blockType, Block ... blocks) {
        if (blockType instanceof WoodType woodType)
            return createAndAddCustomTags(resLoc, handler, woodType.log, woodType.getBlockOfThis("stripped_log"), woodType.getBlockOfThis("wood"), woodType.getBlockOfThis("stripped_wood"));
        else
            return createAndAddCustomTags(resLoc, handler, blocks);
    }

    /**
     * Add any blocks to newly created tag
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddCustomTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, Block... blocks) {
        boolean isTagCreated = false;

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(resLoc);
        // Adding blocks to tag file
        for (Block block : blocks) {
            if (block != null) {
                tagBuilder.addEntry(block);
                isTagCreated = true;
            }
        }
        // Adding to the resources
        if (isTagCreated) {
            handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
            handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
        }
        return isTagCreated;
    }

}
