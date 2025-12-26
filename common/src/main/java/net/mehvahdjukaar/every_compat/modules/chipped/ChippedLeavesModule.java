package net.mehvahdjukaar.every_compat.modules.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;

import java.util.Set;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.isKnownVanillaLeaves;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.LEAVES;

//See ChippedAbstractModule's SUPPORTED VERSION
public class ChippedLeavesModule extends ChippedModuleAbstract {

    public final SimpleEntrySet<LeavesType, Block> apple,
            cherry,
            frosted,
            golden_apple,
            golden_cherry,
            magenta_flower,
            white_flower,
            dead,
            golden,
            orange,
            red;

    public ChippedLeavesModule(String modId) {
        super(modId);
        ResourceLocation tab = modRes(tabPath);

        apple = SimpleEntrySet.builder(LeavesType.class, "leaves", "apple",
                        getModBlock("apple_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                    String prefix = "apple";

                    return string
                            .replace("\"minecraft:block/cube_all\"",
                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                            .replaceAll("(.*leaves\")(.*)",
                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/apple_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(apple);

        cherry = SimpleEntrySet.builder(LeavesType.class, "leaves", "cherry",
                        getModBlock("cherry_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                                    String prefix = "cherry";

                                    return string
                                            .replace("\"minecraft:block/cube_all\"",
                                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                                            .replaceAll("(.*leaves\")(.*)",
                                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/cherry_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(cherry);

        // \"all\":\"chipped:block/oak_leaves/" + prefix + "_oak
        frosted = SimpleEntrySet.builder(LeavesType.class, "leaves", "frosted",
                        getModBlock("frosted_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, leavesType) -> {
                        String overlayId = leavesType.createFullIdWith(EveryCompat.MOD_ID, "block", shortenedId(),  leavesType.getTypeName() + "_leaves/frosted_", "overlay");
                        String topId = leavesType.createFullIdWith(EveryCompat.MOD_ID, "block", shortenedId(),  leavesType.getTypeName() + "_leaves/frosted_", "leaves_top");

                        return string
                                .replace("\"minecraft:block/cube_column\"",
                                        "\"" + EveryCompat.res("block/chipped/frosted_leaves_column") + "\"")

                                .replaceAll("(.*\"side\":\"chipped:block/oak_leaves/frosted_oak_leaves\")(.*)",
                                        "$1,\"overlay\":\"" + overlayId + "\"$2")
                                .replace("chipped:block/oak_leaves/frosted_oak_leaves_top", topId);
                    })
                    .replaceWithTextureFromChild("chipped:block/oak_leaves/frosted_oak_leaves", LEAVES,
                            CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(frosted);

        golden_apple = SimpleEntrySet.builder(LeavesType.class, "leaves", "golden_apple",
                        getModBlock("golden_apple_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                                    String prefix = "golden_apple";

                                    return string
                                            .replace("\"minecraft:block/cube_all\"",
                                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                                            .replaceAll("(.*leaves\")(.*)",
                                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/golden_apple_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(golden_apple);

        golden_cherry = SimpleEntrySet.builder(LeavesType.class, "leaves", "golden_cherry",
                        getModBlock("golden_cherry_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                                    String prefix = "golden_cherry";

                                    return string
                                            .replace("\"minecraft:block/cube_all\"",
                                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                                            .replaceAll("(.*leaves\")(.*)",
                                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/golden_cherry_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(golden_cherry);

        magenta_flower = SimpleEntrySet.builder(LeavesType.class, "leaves", "magenta_flower",
                        getModBlock("magenta_flower_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                                    String prefix = "magenta_flower";

                                    return string
                                            .replace("\"minecraft:block/cube_all\"",
                                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                                            .replaceAll("(.*leaves\")(.*)",
                                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/magenta_flower_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(magenta_flower);

        white_flower = SimpleEntrySet.builder(LeavesType.class, "leaves", "white_flower",
                        getModBlock("white_flower_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                // Below insert a new key "overlay" in models/block files
                .addModelTransform(m -> m.addModifier((string, ignored, ignored2) -> {
                                    String prefix = "white_flower";

                                    return string
                                            .replace("\"minecraft:block/cube_all\"",
                                                    "\"" + EveryCompat.res("block/chipped/overlayed_leaves") + "\"")

                                            .replaceAll("(.*leaves\")(.*)",
                                                    "$1,\"overlay\":\"" + EveryCompat.res("block/ch/oak_leaves/" + prefix + "_overlay") + "\"$2");
                                })
                                .replaceWithTextureFromChild("chipped:block/oak_leaves/white_flower_oak_leaves", LEAVES,
                                        CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE)
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .copyParentTint()
                .build();
        this.addEntry(white_flower);

        dead = SimpleEntrySet.builder(LeavesType.class, "leaves", "dead",
                        getModBlock("dead_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(dead);

        golden = SimpleEntrySet.builder(LeavesType.class, "leaves", "golden",
                        getModBlock("golden_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(golden);

        orange = SimpleEntrySet.builder(LeavesType.class, "leaves", "orange",
                        getModBlock("orange_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(orange);

        red = SimpleEntrySet.builder(LeavesType.class, "leaves", "red",
                        getModBlock("red_oak_leaves"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new LeavesBlock(Utils.copyPropertySafe(leavesType.leaves))
                )
                //TEXTURES: See addDynamicClientResources (below)
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.REPLACEABLE_BY_TREES, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.SWORD_EFFICIENT, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(red);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> addBotanistRecipe(sink));
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        Set<String> prefixes = Set.of(
                "dead",
                "golden",
                "orange",
                "red"
        );

        executor.accept((manager, sink) -> {

            for (String prefix : prefixes) {
                generateLeaves(modRes("block/oak_leaves/" + prefix + "_oak_leaves"), sink, manager);
            }
            generateFrostedLeaves(modRes("block/oak_leaves/frosted_oak_leaves_top"), sink, manager);
        });
    }

    /// Generate textures using opposite of standard texture generation
    public void generateLeaves(ResourceLocation targetResLoc, ResourceSink sink, ResourceManager manager) {
        for (LeavesType leavesType : LeavesTypeRegistry.INSTANCE) {

            if (isKnownVanillaLeaves(leavesType) || leavesType.getNamespace().equals("chipped")) continue;

            try (
                    TextureImage leavesTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, leavesType.leaves));
                    TextureImage targetTexture = TextureImage.open(manager, targetResLoc) // Using chipped's leaves' texture instead of leaves' texture
            ) {
                String path = targetResLoc.getPath();
                String infix = shortenedId()+"/"+leavesType.getNamespace()+"/";

                String newPath = path.substring(0, 6) + infix + path.substring(6);
                newPath = newPath.replace("oak", leavesType.getTypeName());

                ResourceLocation newResLoc = EveryCompat.res(newPath);

                sink.addTextureIfNotPresent(manager, newResLoc, () -> {
                    Respriter respriter = Respriter.of(leavesTexture);
                    return respriter.recolorWithAnimationOf(targetTexture);
                });
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to generate Leave's texture for {} : {}", leavesType.getId(), e);
            }
        }
    }

    /// Generate top & overlay for frosted_leaves
    public void generateFrostedLeaves(ResourceLocation targetResLoc, ResourceSink sink, ResourceManager manager) {
        for (LeavesType leavesType : LeavesTypeRegistry.INSTANCE) {

            if (isKnownVanillaLeaves(leavesType) || leavesType.getNamespace().equals("chipped")) continue;

            try (
                    TextureImage leavesTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, leavesType.leaves));
                    TextureImage targetTexture = TextureImage.open(manager, targetResLoc); // Using chipped's leaves' texture instead of leaves' texture
                    TextureImage bottomMask = TextureImage.open(manager, EveryCompat.res("block/ch/oak_leaves/frosted_oak_leaves_m"))
            ) {
                String path = targetResLoc.getPath();
                String infix = shortenedId()+"/"+leavesType.getNamespace()+"/";

                String topPath = path.substring(0, 6) + infix + path.substring(6);

                // block/ch/namespace/oak_leaves/frosted_oak_leaves_top
                topPath = topPath.replace("oak", leavesType.getTypeName());
                ResourceLocation topResLoc = EveryCompat.res(topPath);

                // block/ch/namespace/oak_leaves/frosted_oak_overlay
                ResourceLocation overlayResLoc = EveryCompat.res(topPath.replace("leaves_top", "overlay"));

                Respriter respriter = Respriter.of(leavesTexture);
                TextureImage frostedTexture = respriter.recolorWithAnimationOf(targetTexture);
                TextureImage overlayTexture = frostedTexture.makeCopy();

                // Add top texture
                sink.addTextureIfNotPresent(manager, topResLoc, () -> frostedTexture);

                // Add overlay texture
                sink.addTextureIfNotPresent(manager, overlayResLoc, () -> {
                    TextureOps.applyMask(overlayTexture, bottomMask);
                    return overlayTexture;
                });
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to generate Leave's texture for {} : {}", leavesType.getId(), e);
            }
        }
    }
}