package net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored;

import io.github.uhq_games.regions_unexplored.block.RuBlocks;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.tall.ShrubBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.createAndAddCustomTags;

// SUPPORT: v0.5.6+
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> branchs;
    public final SimpleEntrySet<LeavesType, Block> shrubs;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");
        ResourceLocation tab = modRes("main");

        branchs = SimpleEntrySet.builder(WoodType.class, "branch",
                        getModBlock("oak_branch"), () -> VanillaWoodTypes.OAK,
                        w -> new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH),
                                BranchBlock.BranchType.BRANCH)
                )
                .addTexture(modRes("block/oak_branch"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("branches"), Registries.BLOCK)
                .addTag(modRes("branches"), Registries.ITEM)
                .addRecipe(modRes("oak_branch_from_oak_log"))
                .setTabKey(tab)
                .build();
        this.addEntry(branchs);

        shrubs = SimpleEntrySet.builder(LeavesType.class, "shrub",
                        getModBlock("dark_oak_shrub"),
                        () -> LeavesTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        l -> new ShrubBlock(Utils.copyPropertySafe(l.leaves).pushReaction(PushReaction.DESTROY)
                                .ignitedByLava().noCollission().instabreak().sound(SoundType.AZALEA)
                                .offsetType(BlockBehaviour.OffsetType.XZ))
                )
                .addCondition(l -> {
                    boolean log = l.getWoodType() != null; //REASON: textures
                    boolean sapling = l.getItemOfThis("sapling") != null; //REASON: recipes
                    return log && sapling;
                })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.BLOCK)
                .addTag(modRes("shrub_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.ITEM)
                .addRecipe(modRes("dark_oak_shrub"))
                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .copyParentDrop()
                .setTabKey(tab)
                .build();
        this.addEntry(shrubs);
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        for (Map.Entry<LeavesType, Block> entry : shrubs.blocks.entrySet()) {
            LeavesType type = entry.getKey();
            Block block = entry.getValue();
            event.register((stack, tintIndex) -> {
                if (tintIndex > 0) return 0xFFFFFFFF;
                return event.getColor(new ItemStack(type.leaves), tintIndex);
            }, block);
        }
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : shrubs.blocks.entrySet()) {
            LeavesType type = entry.getKey();
            Block b = entry.getValue();
            event.register((blockState, tintGetter, pos, index) ->
                    event.getColor(type.leaves.defaultBlockState(), tintGetter, pos, index), b);
        }
    }

    @Override
    //TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            branchs.blocks.forEach((wood, block) -> {
                SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(modRes("branches_can_survive_on"));
                tagBuilder.add(Utils.getID(wood.log));
                sink.addTag(tagBuilder, Registries.BLOCK);

                //Tagging the planks as ingredient to get painted_planks
                createAndAddCustomTags(new ResourceLocation("planks"), sink, wood.planks);
                createAndAddCustomTags(new ResourceLocation("c:planks"), sink, wood.planks);
            });

        });
    }

    @Override
    //TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

// Generating branch textures ==========================================================================================
        executor.accept((manager, sink) -> {
            try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/oak_branch_side"));
                 TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/oak_branch_top"));
                 TextureImage branch_block = TextureImage.open(manager, modRes("block/oak_branch"))
            ) {

                branchs.blocks.forEach((wood, block) -> {
                    try (TextureImage logSide_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage logTop_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                        String resLocITEM = "item/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch";
                        String resLocBLOCK = "block/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch";

                        Respriter respriterSIDE = Respriter.of(branch_side); // ITEM
                        Respriter respriterTOP = Respriter.of(branch_top); // ITEM
                        Respriter respriterBlock = Respriter.of(branch_block); // BLOCK

                        List<Palette> list_logSide = Palette.fromAnimatedImage(logSide_texture);
                        List<Palette> list_logTop = Palette.fromAnimatedImage(logTop_texture);

                        // Recoloring ITEM textures
                        TextureImage recoloredITEM = respriterSIDE.recolor(list_logSide);
                        TextureImage recoloredTOP = respriterTOP.recolor(list_logTop);
                        recoloredITEM.applyOverlay(recoloredTOP);

                        // Recoloring BLOCK texture
                        TextureImage recoloredBLOCK = respriterBlock.recolor(list_logSide);

                        // Block Texture
                        sink.addTextureIfNotPresent(manager, resLocBLOCK, () -> recoloredBLOCK);
                        recoloredBLOCK.close();
                        // Item Texture
                        sink.addTextureIfNotPresent(manager, resLocITEM, () -> recoloredITEM);
                        recoloredITEM.close();

                    } catch (IOException e) {
                        EveryCompat.LOGGER.error("Failed to get Log Texture for {} : {}", block, e);
                    }
                });
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to get Branch Item Texture for ", e);
            }

// Generating shrub textures ===========================================================================================
            try (
                    // middle is the bark part of shrub_top's
                    TextureImage shrubTop = TextureImage.open(manager, modRes("block/dark_oak_shrub_top"));
                    TextureImage shrubBottom = TextureImage.open(manager, modRes("block/dark_oak_shrub_bottom"));
                    TextureImage shrubTopMask = TextureImage.open(manager, EveryCompat.res("block/ru/mask_shrub_top"));
                    TextureImage shrubMiddleMask = TextureImage.open(manager, EveryCompat.res("block/ru/mask_shrub_middle"))
            ) {

                shrubs.blocks.forEach((leavesType, block) -> {
                    String shrubPath = leavesType.createPathWith(shortenedId(), "shrub");

                    // Generating textures for shrubs
                    try (TextureImage logTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, leavesType.getWoodType().log,
                                    SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage leavesTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, leavesType.leaves,
                                         SpriteHelper.LOOKS_LIKE_LEAF_TEXTURE))
                    ) {
                        Respriter respriterBottom = Respriter.of(shrubBottom);
                        Respriter respriterTop = Respriter.masked(shrubTop, shrubMiddleMask);

                        List<Palette> list_logSide = Palette.fromAnimatedImage(logTexture);
                        List<Palette> list_leaves = Palette.fromAnimatedImage(leavesTexture);

                        // Recoloring the shrub's Bottom
                        TextureImage finishedShrubBottom = respriterBottom.recolor(list_logSide);

                        // Recoloring the shrub's Top (the leaves part)
                        TextureImage recoloredShrubTop = respriterTop.recolor(list_leaves);

                        // Recoloring the shrub's Middle (the bark part)
                        Respriter respriterMiddle = Respriter.masked(recoloredShrubTop, shrubTopMask);

                        TextureImage finishedShrub = respriterMiddle.recolor(list_logSide);

                        // Adding to the resource
                        String resLoc = "block/" + shrubPath;
                        sink.addTextureIfNotPresent(manager, resLoc + "_bottom", () -> finishedShrubBottom);
                        finishedShrubBottom.close();
                        sink.addTextureIfNotPresent(manager, resLoc + "_top", () -> finishedShrub);
                        finishedShrub.close();

                    } catch (IOException e) {
                        EveryCompat.LOGGER.error("Failed to get texture for {} : {}", block.toString(), e.getMessage());
                    }
                });
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to open textures for: {}", e.getMessage());
            }

        });
    }
}
