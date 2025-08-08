package net.mehvahdjukaar.every_compat.common_classes;

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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TagUtility {

    /**
     * Get the namespace:WoodType_logs tag from the wood mods or create a new tag if not available
     *
     * @return ResourceLocation
     **/
    public static ResourceLocation getATagOrCreateANew(String suffixTag, String suffixAlt, BlockType blockType, ResourceSink sink, ResourceManager manager) {
        String resLocMOD = blockType.getNamespace() + ":" + blockType.getTypeName();

        // ResourceLocation
        ResourceLocation RLocLogs = new ResourceLocation(resLocMOD + "_" + suffixTag); // modId:TYPE_suffix
        ResourceLocation RLocStems = new ResourceLocation(resLocMOD + "_" + suffixAlt);
        ResourceLocation RLocFolders = new ResourceLocation(blockType.getNamespace() + ":" + suffixTag + "/" + blockType.getTypeName()); // modId:suffix/TYPE
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

    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(
            Map<T, B> blocks, String nameStone, String modId, String tag,
            boolean includeBlock, boolean includeItem, ResourceSink pack
    ) {
        addTagToAllBlocks(blocks, nameStone, modId,
                TagKey.create(Registries.BLOCK, new ResourceLocation(tag)),
                includeBlock, includeItem, pack);
    }

    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(
            Map<T, B> blocks, String nameStone, String modId, TagKey<Block> tag,
            boolean includeBlock, boolean includeItem, ResourceSink pack
    ) {
        addTagToAllBlocks(blocks, nameStone, modId,
                tag, includeBlock, includeItem, pack, null);
    }

    /// The tag will be added if the mod is loaded
    public static <T extends BlockType, B extends Block> void addTagToAllBlocks(
            Map<T, B> blocks, String nameStone, String modId,
            TagKey<Block> tag, boolean includeBlock, boolean includeItem, ResourceSink pack,
            @Nullable String regexBlockId
    ) {
        if (PlatHelper.isModLoaded(modId)) {
            boolean isTagCreated = false;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(tag);
            for (Map.Entry<T, B> entry : blocks.entrySet()) {
                T stoneType = entry.getKey();
                B block = entry.getValue();

                String blockPath = Utils.getID(block).getPath();
                String blockId = blockPath.substring(blockPath.lastIndexOf("/") + 1);

                if (stoneType.getTypeName().equals(nameStone) &&
                        (regexBlockId == null || blockId.matches(regexBlockId))) {
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

    @SuppressWarnings("SameParameterValue")
    /// @return c:tagPath for FABRIC or forge:tagPath for FORGE
    private static ResourceLocation platformTag(String tagPath) {
        return platformTag(tagPath, tagPath);
    }

    public static ResourceLocation platformTag(String fabric, String forge) {
        return PlatHelper.getPlatform().isFabric() ? fabricTag(fabric) : forgeTag(forge);
    }

    /// @return c:tagPath
    public static ResourceLocation fabricTag(String tagPath) {
        return new ResourceLocation("c", tagPath);
    }

    /// @return forge:tagPath
    public static ResourceLocation forgeTag(String tagPath) {
        return new ResourceLocation("forge", tagPath);
    }



    public static final ResourceLocation SILICA_TAG = platformTag("silica_glass", "silica");
    public static final ResourceLocation GLASS_TAG = platformTag("glass_blocks", "glass");
    public static final ResourceLocation GLASS_PANE_TAG = platformTag("glass_panes");

}
