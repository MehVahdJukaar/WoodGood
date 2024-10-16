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

    public static boolean isTagFull;

    /**
     * It is checking for namespace:TYPE_logs tag in mods and will use them as an ingredient in the recipe if
     * they are present. Otherwise, it will create a custom tag, everycomp:namespace/TYPE_logs
     * The reason why it don't have shortenedID incldued is to allow other modules to use the same tags
    **/
    public static ResourceLocation whichTags(String suffixTag, String suffixAlt, WoodType wood, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        // If a namespace:<type>_logs already exist, then it will be used as an ingredient in the recipe, otherwise will
        // generate a tag for logs/stems that don't have the tags.

        String resLocMOD = wood.getNamespace() +":"+ wood.getTypeName();

        // ResourceLocation of log/planks tags
        ResourceLocation RLocLogsTag = new ResourceLocation(resLocMOD +"_"+ suffixTag);
        // ~ of stem or others tags
        ResourceLocation RLocStemsTag = new ResourceLocation(resLocMOD +"_"+ suffixAlt);
        // ~ of logs folder tag : namespace:logs/<type>
        ResourceLocation RLocFoldersTag = new ResourceLocation(wood.getNamespace() +":"+ suffixTag +"/"+ wood.getTypeName());
        // ~ of generated tags
        ResourceLocation RLocECTag = EveryCompat.res(wood.getAppendableId() +"_"+ suffixTag);

        if (manager.getResource(ResType.TAGS.getPath(RLocLogsTag.withPrefix("blocks/"))).isPresent())
            return RLocLogsTag;
        else if (manager.getResource(ResType.TAGS.getPath(RLocStemsTag.withPrefix("blocks/"))).isPresent())
            return RLocStemsTag;
        else if (manager.getResource(ResType.TAGS.getPath(RLocECTag.withPrefix("blocks/"))).isPresent())
            return RLocECTag;
        else if (manager.getResource(ResType.TAGS.getPath(RLocFoldersTag.withPrefix("blocks/"))).isPresent())
            return RLocFoldersTag;
        else // if RLocECTags is empty, then it will be generated
            return createDefaultTags(RLocECTag, handler, wood);

    }

    /**
     * The method is to create a custom Tag file with
     * DEFAULT BLOCKS - log, stripped_log, wood, stripped_wood
    **/
    public static ResourceLocation createDefaultTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, WoodType wood) {
        return createCustomTags(resLoc, handler, wood.log, wood.getBlockOfThis("stripped_log"), wood.getBlockOfThis("wood"), wood.getBlockOfThis("stripped_wood"));
    }

    /**
     * The method is to create a custom Tag file with any blocks added.
     **/
    public static ResourceLocation createCustomTags(ResourceLocation resLoc, ServerDynamicResourcesHandler handler, Block ... blocks) {
        isTagFull = false;

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(resLoc);
        // Adding blocks to tag file
        for (Block block : blocks) {
            if (block != null) {
                tagBuilder.addEntry(block);
                isTagFull = true;
            }
        }
        // Adding to the resources
        if (isTagFull) {
            handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
            handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
        }
        return resLoc;
    }

}
