package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class TagUtility {

    /**
     * Get the namespace:WoodType_logs tag from the wood mods or create a new tag if not available
     *
     * @return ResourceLocation
     **/
    public static ResourceLocation getATagOrCreateANew(String suffixTag, String suffixAlt, BlockType blockType, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        String resLocMOD = blockType.getNamespace() + ":" + blockType.getTypeName();

        // ResourceLocation
        ResourceLocation RLocLogs = ResourceLocation.parse(resLocMOD + "_" + suffixTag);
        ResourceLocation RLocStems = ResourceLocation.parse(resLocMOD + "_" + suffixAlt);
        ResourceLocation RLocFolders = ResourceLocation.parse(blockType.getNamespace() + ":" + suffixTag + "/" + blockType.getTypeName());
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

    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(Map<T, B> blocks, String nameStone, String modId, String tag, boolean includeBlock, boolean includeItem, DynamicDataPack pack) {
        if (PlatHelper.isModLoaded(modId)) {
            boolean isTagCreated = false;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(ResourceLocation.fromNamespaceAndPath(modId, tag));
            for (Map.Entry<T, B> entry : blocks.entrySet()) {
                T stoneType = entry.getKey();
                B block = entry.getValue();
                if (stoneType.getTypeName().equals(nameStone)) {
                    tagBuilder.addEntry(block);
                    isTagCreated = true;
                }
            }
            if (isTagCreated) {
                if (includeBlock) pack.addTag(tagBuilder, Registries.BLOCK);
                if (includeItem) pack.addTag(tagBuilder, Registries.ITEM);
            }
        }
    }
    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(Map<T, B> blocks, String nameStone, String modId, TagKey<Block> tag, boolean includeBlock, boolean includeItem, DynamicDataPack pack) {
        if (PlatHelper.isModLoaded(modId)) {
            boolean isTagCreated = false;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(tag);
            for (Map.Entry<T, B> entry : blocks.entrySet()) {
                T stoneType = entry.getKey();
                B block = entry.getValue();
                if (stoneType.getTypeName().equals(nameStone)) {
                    tagBuilder.addEntry(block);
                    isTagCreated = true;
                }
            }
            if (isTagCreated) {
                if (includeBlock) pack.addTag(tagBuilder, Registries.BLOCK);
                if (includeItem) pack.addTag(tagBuilder, Registries.ITEM);
            }
        }
    }
    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(Map<T, B> blocks, String regexBlockId, String nameStone, String modId, TagKey<Block> tag, boolean includeBlock, boolean includeItem, DynamicDataPack pack) {
        if (PlatHelper.isModLoaded(modId)) {
            boolean isTagCreated = false;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(tag);
            for (Map.Entry<T, B> entry : blocks.entrySet()) {
                T stoneType = entry.getKey();
                B block = entry.getValue();

                String blockPath = Utils.getID(block).getPath();
                String blockId = blockPath.substring(blockPath.lastIndexOf("/") + 1);

                if (stoneType.getTypeName().equals(nameStone) && blockId.matches(regexBlockId)) {
                    tagBuilder.addEntry(block);
                    isTagCreated = true;
                }
            }
            if (isTagCreated) {
                if (includeBlock) pack.addTag(tagBuilder, Registries.BLOCK);
                if (includeItem) pack.addTag(tagBuilder, Registries.ITEM);
            }
        }
    }

    /**
     * Get the id tag from the mods
     *
     * @return Pair of ResourceLocation, Boolean
     **/
    public static Pair<ResourceLocation, Boolean> getATagId(String idTag, String idAlt, ResourceManager manager) {
        // ResourceLocation
        ResourceLocation RLocId = new ResourceLocation(idTag); // forge:suffix/EXTRA_TYPE or c:TYPE_ingot
        ResourceLocation RLocIdAlt = new ResourceLocation(idAlt); // forge:suffix/EXTRATYPE or c:TYPEingot

        if (doTagExistFor(RLocId, manager))
            return Pair.of(RLocId, true);
        else if (doTagExistFor(RLocIdAlt, manager))
            return Pair.of(RLocIdAlt, true);

        return Pair.of(null, false);
    }

    /// Checking if a tag exist for a block or an item
    private static boolean doTagExistFor(ResourceLocation resLoc, ResourceManager manager) {
        boolean blockTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("blocks/"))).isPresent();
        boolean itemTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("items/"))).isPresent();
        return blockTag || itemTag;
    }

    // Common tags

    private static ResourceLocation commonTag(String suffix) {
        return PlatHelper.getPlatform().isFabric() ?
                new ResourceLocation("c", suffix) :
                new ResourceLocation("forge", suffix);
    }

    public static final ResourceLocation GLASS_TAG = commonTag("glass");
    public static final ResourceLocation GLASS_PANE_TAG = commonTag("glass_panes");

}
