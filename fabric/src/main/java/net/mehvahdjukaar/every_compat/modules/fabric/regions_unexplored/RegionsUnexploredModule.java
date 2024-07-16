package net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored;

import io.github.uhq_games.regions_unexplored.block.RuBlocks;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.tall.ShrubBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.io.IOException;
import java.util.List;

// SUPPORT: v0.5.5+
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> branchs;
    public final SimpleEntrySet<WoodType, Block> shrubs;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");

        branchs = SimpleEntrySet.builder(WoodType.class, "branch",
                        getModBlock("oak_branch"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH),
                                BranchBlock.BranchType.BRANCH)
                )
                .addTexture(modRes("block/oak_branch"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("branches"), Registries.BLOCK)
                .addTag(modRes("branches"), Registries.ITEM)
                .addRecipe(modRes("oak_branch_from_oak_log"))
                .build();
        this.addEntry(branchs);

        shrubs = SimpleEntrySet.builder(WoodType.class, "shrub",
                        getModBlock("dark_oak_shrub"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new ShrubBlock(Utils.copyPropertySafe(RuBlocks.ACACIA_SHRUB))
                )
//                .addTexture(modRes("block/dark_oak_shrub_top"))
                .addTexture(modRes("block/dark_oak_shrub_bottom"))
                .createPaletteFromChild(p -> {}, "log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.BLOCK)
                .addTag(modRes("shrub_can_survive_on"), Registries.BLOCK)
                .addTag(modRes("shrubs"), Registries.ITEM)
                .addRecipe(modRes("dark_oak_shrub"))
                .addRecipe(modRes("dark_oak_sapling_from_dark_oak_shrub"))
                .build();
        this.addEntry(shrubs);


    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        branchs.blocks.forEach((wood, block) -> {
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(modRes("branches_can_survive_on"));
            tagBuilder.add(Utils.getID(wood.log));
            handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
        });
    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/oak_branch_side"));
             TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/oak_branch_top"));
             TextureImage branch_block = TextureImage.open(manager, modRes("block/oak_branch"))
        ) {

            branchs.blocks.forEach((wood, block) -> {
                try (TextureImage logSide_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage logTop_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    ResourceLocation resLocITEM = EveryCompat.res("item/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");
                    ResourceLocation resLocBLOCK = EveryCompat.res("block/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");

                    Respriter respriterSIDE = Respriter.of(branch_side); // ITEM
                    Respriter respriterTOP = Respriter.of(branch_top); // ITEM
                    Respriter respriterBlock = Respriter.of(branch_block); // BLOCK

                    // Recoloring ITEM textures
                    TextureImage recoloredITEM = respriterSIDE.recolorWithAnimationOf(logSide_texture);
                    TextureImage recoloredTOP = respriterTOP.recolorWithAnimationOf(logTop_texture);
                    recoloredITEM.applyOverlay(recoloredTOP);

                    // Recoloring BLOCK texture
                    TextureImage recoloredBLOCK = respriterBlock.recolorWithAnimationOf(logSide_texture);

                    // Block Texture
                    handler.dynamicPack.addAndCloseTexture(resLocBLOCK, recoloredBLOCK);
                    // Item Texture
                    handler.dynamicPack.addAndCloseTexture(resLocITEM, recoloredITEM);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get Log Texture for {} : {}", block, e);
                }
            });
        }
        catch (IOException e) {
            handler.getLogger().error("Failed to get Branch Item Texture for " + e);
        }

        try (TextureImage shrubTop = TextureImage.open(manager, modRes("block/dark_oak_shrub_top"));
             TextureImage shrubBottom = TextureImage.open(manager, modRes("block/dark_oak_shrub_bottom"));
             TextureImage leaveMask = TextureImage.open(manager, EveryCompat.res("block/regions_unexplored/dark_oak_shrub_leaves_m"));
             TextureImage logMask = TextureImage.open(manager, EveryCompat.res("block/regions_unexplored/dark_oak_shrub_logs_m"));
        ) {

            shrubs.blocks.forEach((wood, block) -> {

                try (
                        TextureImage leaveTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("leaves"), SpriteHelper.LOOKS_LIKE_LEAF_TEXTURE));
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    Respriter respriterTop = Respriter.masked(shrubTop, leaveMask);
                    Respriter respriterBottom = Respriter.of(shrubBottom);

                    // Recoloring the log top & bottom
                    TextureImage recoloredLog = respriterTop.recolorWithAnimationOf(logTexture);
                    TextureImage recoloredBottom = respriterBottom.recolorWithAnimationOf(logTexture);

                    // Recoloring the leave
                    Respriter respriterleave = Respriter.masked(recoloredLog, logMask);
                    TextureImage recoloredTop = respriterleave.recolorWithAnimationOf(leaveTexture);

                    // Adding to the resource
                    String resLoc = shortenedId() + "/" + wood.getAppendableId() + "_shrub";
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res("block/" + resLoc + "_top"), recoloredTop);
                    handler.dynamicPack.addAndCloseTexture(EveryCompat.res("block/" + resLoc + "_bottom"), recoloredBottom);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get texture for {} : {}", block, e);
                }
            });
        } catch (Exception e) {
            handler.getLogger().error("Failed to get textures for " + e);
        }
    }
}
