package net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored;

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
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.regions_unexplored.block.RuBlocks;
import net.regions_unexplored.world.level.block.plant.branch.BranchBlock;

import java.io.IOException;
import java.util.List;

// SUPPORT: v0.5.5+
public class RegionsUnexploredModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> BRANCH;

    public RegionsUnexploredModule(String modId) {
        super(modId, "ru");

        BRANCH = SimpleEntrySet.builder(WoodType.class, "branch",
            getModBlock("oak_branch"), () -> WoodTypeRegistry.OAK_TYPE,
            w -> new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH.get()), "branch")
        )
            .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
            .addTag(modRes("branches"), Registries.BLOCK)
            .addTag(modRes("branches"), Registries.ITEM)
            .addRecipe(modRes("oak_branch_from_oak_log"))
            .build();
        this.addEntry(BRANCH);
    }

    @Override
    public void onModSetup() {
        BRANCH.blocks.forEach((woodType, block) -> ComposterBlock.COMPOSTABLES.put(block, 0.3F));
    }

    @Override
    // Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        BRANCH.blocks.forEach((wood, block) -> {
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

            BRANCH.blocks.forEach((wood, block) -> {
                try (TextureImage logSide_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                     TextureImage logTop_texture = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    ResourceLocation resLocITEM = EveryCompat.res("item/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");
                    ResourceLocation resLocBLOCK = EveryCompat.res("block/" + this.shortenedId() + "/" + wood.getAppendableId() + "_branch");

                    List<Palette> targetSide = Palette.fromAnimatedImage(logSide_texture);
                    AnimationMetadataSection metaSide = logSide_texture.getMetadata();
                    List<Palette> targetTop = Palette.fromAnimatedImage(logTop_texture);
                    AnimationMetadataSection metaTop = logTop_texture.getMetadata();

                    Respriter respriterSIDE = Respriter.of(branch_side); // ITEM
                    Respriter respriterTOP = Respriter.of(branch_top); // ITEM
                    Respriter respriterBlock = Respriter.of(branch_block); // BLOCK

                    // Recoloring ITEM textures
                    TextureImage recoloredITEM = respriterSIDE.recolorWithAnimation(targetSide, metaSide);
                    TextureImage recoloredTOP = respriterTOP.recolorWithAnimation(targetTop, metaTop);
                    recoloredITEM.applyOverlay(recoloredTOP);

                    // Recoloring BLOCK texture
                    TextureImage recolorBLOCK = respriterBlock.recolorWithAnimation(targetSide, metaSide);

                    // Block Texture
                    handler.dynamicPack.addAndCloseTexture(resLocBLOCK, recolorBLOCK);
                    // Item Texture
                    handler.dynamicPack.addAndCloseTexture(resLocITEM, recoloredITEM);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to get Log Texture for " + block + " : " + e);
                }
            });

        }
        catch (IOException e) {
            handler.getLogger().error("Failed to get Branch Item Texture for " + e);
        }
    }
}
