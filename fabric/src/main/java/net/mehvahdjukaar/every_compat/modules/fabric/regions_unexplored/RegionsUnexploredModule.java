package net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.regions_unexplored.block.RegionsUnexploredBlocks;
import net.regions_unexplored.world.level.block.BranchBlock;

import java.io.IOException;

// SUPPORT: v0.4.1-FINAL
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> branchs;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");

        branchs = SimpleEntrySet.builder(WoodType.class, "branch",
                        () -> getModBlock("oak_branch"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BranchBlock(BlockBehaviour.Properties.copy(RegionsUnexploredBlocks.OAK_BRANCH))
                )
                .addTexture(modRes("block/oak_branch"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("branches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("branches"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("oak_branch_from_oak_log"))
                .build();
        this.addEntry(branchs);

    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        branchs.blocks.forEach((wood, block) -> {
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(modRes("branches_can_survive_on"));
            tagBuilder.add(Utils.getID(wood.log));
            handler.dynamicPack.addTag(tagBuilder, Registry.BLOCK_REGISTRY);
        });
    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage branch_side = TextureImage.open(manager, EveryCompat.res("item/regions_unexplored/oak_branch_side"));
             TextureImage branch_top = TextureImage.open(manager, EveryCompat.res("item/regions_unexplored/oak_branch_top"));
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
    }
}
