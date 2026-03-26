package net.mehvahdjukaar.every_compat.misc;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("UnusedReturnValue")
public class UtilityTag {

    /**
     * Get the namespace:WoodType_logs tag from the wood mods or create a new tag if not available
     *
     * @return ResourceLocation
     **/
    public static ResourceLocation getATagOrCreateANew(String suffixTag, String suffixAlt, BlockType blockType, ResourceSink sink, ResourceManager manager) {
        String resLocMOD = blockType.getNamespace() + ":" + blockType.getTypeName();

        // ResourceLocation
        ResourceLocation RLocLogs = ResourceLocation.parse(resLocMOD + "_" + suffixTag); // modId:TYPE_suffix
        ResourceLocation RLocStems = ResourceLocation.parse(resLocMOD + "_" + suffixAlt);
        ResourceLocation RLocFolders = ResourceLocation.parse(blockType.getNamespace() + ":" + suffixTag + "/" + blockType.getTypeName()); // modId:suffix/TYPE
        ResourceLocation RLocEC = EveryCompat.res(blockType.getAppendableId() + "_" + suffixTag); // everycomp:modId/TYPE_suffix

        if (doTagExistFor(RLocLogs, manager))
            return RLocLogs;
        else if (doTagExistFor(RLocStems, manager))
            return RLocStems;
        else if (doTagExistFor(RLocEC, manager))
            return RLocEC;
        else if (doTagExistFor(RLocFolders, manager))
            return RLocFolders;
        else // if RLocECTags is not available, then it will be generated
            createAndAddDefaultTags(RLocEC, sink, blockType);

        return RLocEC;

    }

    /**
     * The method is to create a custom Tag file with
     * DEFAULT BLOCKS - log, stripped_log, wood, stripped_wood
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddDefaultTags(ResourceLocation resLoc, ResourceSink sink, BlockType blockType, Block... blocks) {
        if (blockType instanceof WoodType woodType)
            return createAndAddCustomTags(resLoc, sink, woodType.log, woodType.getBlockOfThis("stripped_log"), woodType.getBlockOfThis("wood"), woodType.getBlockOfThis("stripped_wood"));
        else
            return createAndAddCustomTags(resLoc, sink, blocks);
    }

    /**
     * Add any blocks to newly created tag
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddCustomTags(ResourceLocation resLoc, ResourceSink sink, Block... blocks) {
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
            sink.addTag(tagBuilder, Registries.BLOCK);
            sink.addTag(tagBuilder, Registries.ITEM);
        }
        return isTagCreated;
    }

    /**
     * Add any items to newly created tag
     *
     * @return true if tag was added successfully
     **/
    public static boolean createAndAddCustomTags(ResourceLocation resLoc, ResourceSink sink, Item... items) {
        boolean isTagCreated = false;

        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(resLoc);
        // Adding blocks to tag file
        for (Item item : items) {
            if (item != null) {
                tagBuilder.addEntry(item);
                isTagCreated = true;
            }
        }
        // Adding to the resources
        if (isTagCreated) {
            sink.addTag(tagBuilder, Registries.ITEM);
        }
        return isTagCreated;
    }

    /// See {@link UtilityTag#addTagToAllBlocks(Map, String, String, String, boolean, boolean, ResourceSink, String)}'s javadoc
    public static <T extends BlockType, B extends Block>
        void addTagToAllBlocks(Map<T, B> blocks, String nameBlockTypeOrRegEx, String fromModId, String tagPathOrResLoc,
                               boolean includeBlock, boolean includeItem, ResourceSink sink
    ) {
        addTagToAllBlocks(blocks, nameBlockTypeOrRegEx, fromModId, tagPathOrResLoc, includeBlock, includeItem, sink, null);
    }

    /**
     * The tag will be added if the mod is loaded
     * @param nameBlockTypeOrRegEx name of BlockType without the modId, RegEx can be used, too
     * @param fromModId The mod that BlockType is from Or "" for any mods
     * @param regexBlockId RegEx to match EveryCompat's blocks' Id without the modId
     */
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(
            Map<T, B> blocks, String nameBlockTypeOrRegEx, String fromModId,
            String tagPathOrResLoc, boolean addToBlockTag, boolean addToItemTag, ResourceSink sink,
            @Nullable String regexBlockId
    ) {
        if (PlatHelper.isModLoaded(fromModId) || fromModId.isEmpty()) {

            if (!tagPathOrResLoc.contains(":")) tagPathOrResLoc = fromModId + ":" + tagPathOrResLoc;

            boolean isBlockTagCreated = false;
            boolean isItemTagCreated = false;
            SimpleTagBuilder blocktagBuilder = SimpleTagBuilder.of(ResourceLocation.parse(tagPathOrResLoc));
            SimpleTagBuilder itemtagBuilder = SimpleTagBuilder.of(ResourceLocation.parse(tagPathOrResLoc));
            for (Map.Entry<T, B> entry : blocks.entrySet()) {
                T blockType = entry.getKey();
                B block = entry.getValue();

                String blockPath = Utils.getID(block).getPath();
                String blockId = blockPath.substring(blockPath.lastIndexOf("/") + 1);

                if ((blockType.getId().toString().matches(fromModId +":"+ nameBlockTypeOrRegEx) || fromModId.isEmpty())
                        && (regexBlockId == null || blockId.matches(regexBlockId))) {
                    // Block Tag
                    if (addToBlockTag) {
                        blocktagBuilder.addEntry(block);
                        isBlockTagCreated = true;
                    }

                    // Item Tag
                    if (addToItemTag && block.asItem() instanceof Item item) {
                        itemtagBuilder.addEntry(item);
                        isItemTagCreated = true;
                    }
                }
            }

            if (addToBlockTag && isBlockTagCreated) sink.addTag(blocktagBuilder, Registries.BLOCK);
            if (addToItemTag && isItemTagCreated) sink.addTag(itemtagBuilder, Registries.ITEM);

        }
    }

    /**
     * Get the id tag from the mods
     *
     * @return Pair of ResourceLocation, Boolean
     **/
    public static Pair<ResourceLocation, Boolean> getATagId(String idTag, String idAlt, ResourceManager manager) {
        // ResourceLocation
        ResourceLocation RLocId = ResourceLocation.parse(idTag); // forge:suffix/EXTRA_TYPE or c:TYPE_ingot
        ResourceLocation RLocIdAlt = ResourceLocation.parse(idAlt); // forge:suffix/EXTRATYPE or c:TYPEingot

        if (doTagExistFor(RLocId, manager))
            return Pair.of(RLocId, true);
        else if (doTagExistFor(RLocIdAlt, manager))
            return Pair.of(RLocIdAlt, true);

        return Pair.of(null, false);
    }

    /// Checking if a tag exist for a block or an item
    private static boolean doTagExistFor(ResourceLocation resLoc, ResourceManager manager) {
        boolean blocksTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("blocks/"))).isPresent();
        boolean blockTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("block/"))).isPresent();
        boolean itemsTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("items/"))).isPresent();
        boolean itemTag = manager.getResource(ResType.TAGS.getPath(resLoc.withPrefix("item/"))).isPresent();

        return (blockTag || blocksTag) || (itemTag || itemsTag);
    }

    // Common tags

    @SuppressWarnings("SameParameterValue")
    /// @return c:tagPath for FABRIC or neoforge:tagPath for NEOFORGE
    public static ResourceLocation platformTag(String tagPath) {
        return platformTag(tagPath, tagPath);
    }

    public static ResourceLocation platformTag(String fabric, String neoforge) {
        return PlatHelper.getPlatform().isFabric() ? fabricTag(fabric) : neoforgeTag(neoforge);
    }

    /// @return c:tagPath
    public static ResourceLocation fabricTag(String tagPath) {
        return ResourceLocation.fromNamespaceAndPath("c", tagPath);
    }

    /// @return c:tagPath
    public static ResourceLocation neoforgeTag(String tagPath) {
        return ResourceLocation.fromNamespaceAndPath("c", tagPath);
    }



    public static final ResourceLocation SILICA_TAG = platformTag("silica_glass", "silica");
    public static final ResourceLocation GLASS_TAG = platformTag("glass_blocks", "glass");
    public static final ResourceLocation GLASS_PANE_TAG = platformTag("glass_panes");

}
