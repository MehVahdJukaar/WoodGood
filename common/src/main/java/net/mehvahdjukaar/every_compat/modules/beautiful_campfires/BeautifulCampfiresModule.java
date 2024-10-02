package net.mehvahdjukaar.every_compat.modules.beautiful_campfires;

import com.arcanc.bc.content.BlockDatabase;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.io.IOException;

//SUPPORT: v1.0.2+
public class BeautifulCampfiresModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, CampfireBlock> campfire;
    public final SimpleEntrySet<WoodType, CampfireBlock> soul_campfire;

    public BeautifulCampfiresModule(String modId) {
        super(modId, "bc");
        var tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        campfire = SimpleEntrySet.builder(WoodType.class, "campfire",
                        () -> BlockDatabase.CAMPFIRE_ACACIA, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CampfireBlock(true, 1,
                                Utils.copyPropertySafe(BlockDatabase.CAMPFIRE_ACACIA))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                .addTextureM(modRes("item/acacia_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTextureM(modRes("item/acacia_soul_campfire"), EveryCompat.res("item/bc/campfire_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(campfire);

        soul_campfire = SimpleEntrySet.builder(WoodType.class, "soul_campfire",
                        () -> BlockDatabase.SOUL_CAMPFIRE_ACACIA, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new CampfireBlock(true, 2,
                                Utils.copyPropertySafe(BlockDatabase.SOUL_CAMPFIRE_ACACIA))
                )
                .addTile(() -> BlockEntityType.CAMPFIRE)
                .createPaletteFromChild(palette -> {}, "log")
                .addTextureM(modRes("block/acacia_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"))
                .addTextureM(modRes("block/acacia_soul_campfire_log_lit"), EveryCompat.res("block/bc/campfire_log_lit_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CAMPFIRES, Registries.BLOCK)
                .addTag(BlockTags.PIGLIN_REPELLENTS, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(soul_campfire);

    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        String campfirePath = "block/acacia_campfire_log";
        ResourceLocation campfireImage = modRes(campfirePath);
        ResourceLocation targetLogMask = EveryCompat.res("block/bc/campfire_log_m"); // Focus on the log part
        ResourceLocation targetPlankMask = EveryCompat.res("block/bc/campfire_plank_m"); // Focus on the plank part

        try (TextureImage textureImage = TextureImage.open(manager, campfireImage);
             TextureImage targetLogImage = TextureImage.open(manager, targetLogMask);
             TextureImage targetPlankImage = TextureImage.open(manager, targetPlankMask)
        ) {
            campfire.blocks.forEach((wood, block) -> {
                ResourceLocation id = Utils.getID(block);

                try (
                        TextureImage plankTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.planks));
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, wood.log))
                ) {
                    ResourceLocation newResLoc = EveryCompat.res(BlockTypeResTransformer.replaceTypeNoNamespace(campfirePath, wood, id, "acacia"));

                    // Recoloring the log part
                    Respriter respriterLog = Respriter.masked(textureImage, targetLogImage);

                    TextureImage recoloredLog = respriterLog.recolorWithAnimationOf(logTexture);

                    // Recoloring the plank part
                    Respriter respriterPlank = Respriter.masked(recoloredLog, targetPlankImage);

                    TextureImage finishedImage = respriterPlank.recolorWithAnimationOf(plankTexture);

                    // Adding to the resource
                    handler.dynamicPack.addAndCloseTexture(newResLoc, finishedImage);

                } catch (IOException e) {
                    handler.getLogger().error("Failed to open log/plank texture file: ", e);
                }
            });
        } catch (IOException e) {
            handler.getLogger().error("Failed to open texture file: ", e);
        }

    }
}