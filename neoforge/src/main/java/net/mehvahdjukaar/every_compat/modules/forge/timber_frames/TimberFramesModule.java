package net.mehvahdjukaar.every_compat.modules.forge.timber_frames;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.HitResult;
import net.rasanovum.timberframes.block.OakTimberFrameAlphaBlock;
import net.rasanovum.timberframes.block.OakTimberFrameBetaBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

//SUPPORT: v2.0+
public class TimberFramesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> frame_alpha;
    public final SimpleEntrySet<WoodType, Block> frame_beta;

    public TimberFramesModule(String modId) {
        super(modId, "tf");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        frame_alpha = SimpleEntrySet.builder(WoodType.class, "timber_frame_alpha",
                        getModBlock("oak_timber_frame_alpha"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakTimberFrameAlphaBlock()
                )
                .addTextureM(modRes("block/oak_timber_frame"),
                        EveryCompat.res("block/tf/oak_timber_frame_x_m"))
                .addTextureM(modRes("block/oak_timber_frame_blank"),
                        EveryCompat.res("block/tf/oak_timber_frame_y_m"))
                .addTextureM(modRes("block/oak_timber_frame_cross"),
                        EveryCompat.res("block/tf/oak_timber_frame_cross_m"))
                .addTextureM(modRes("block/oak_timber_frame_cross_nega"),
                        EveryCompat.res("block/tf/oak_timber_frame_cross_nega_m"))
                .addTextureM(modRes("block/oak_timber_frame_diagonal_left"),
                        EveryCompat.res("block/tf/oak_timber_frame_diagonal_left_m"))
                .addTextureM(modRes("block/oak_timber_frame_diagonal_left_nega"),
                        EveryCompat.res("block/tf/oak_timber_frame_diagonal_left_nega_m"))
                .addTextureM(modRes("block/oak_timber_frame_diagonal_right"),
                        EveryCompat.res("block/tf/oak_timber_frame_diagonal_right_m"))
                .addTextureM(modRes("block/oak_timber_frame_diagonal_right_nega"),
                        EveryCompat.res("block/tf/oak_timber_frame_diagonal_right_nega_m"))
                .addTextureM(modRes("block/oak_timber_frame_filler"),
                        EveryCompat.res("block/tf/oak_timber_frame_y_m"))
                .addTextureM(modRes("block/oak_timber_frame_nega"),
                        EveryCompat.res("block/tf/oak_timber_frame_x_m"))
                .addTag(modRes("timber_frame"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("timber_frame"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("oak_timber_frame"))
                .build();
        this.addEntry(frame_alpha);

        frame_beta = SimpleEntrySet.builder(WoodType.class, "timber_frame_beta",
                        getModBlock("oak_timber_frame_beta"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTimberFrameBetaBlock(frame_alpha.blocks.get(w))
                )
                // TEXTURES: Using the same texture above
                .addTag(modRes("timber_frame"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("timber_frame"), Registries.ITEM)
                .noTab()
                .build();
        this.addEntry(frame_beta);
    }

    // BLOCK ---

    /*
    * When the BETA is broken, you will get a drop of ALPHA instead of BETA
    * BETA doesn't have a lootable file. Below will explain everything.
    */
    public static class CompatTimberFrameBetaBlock extends OakTimberFrameBetaBlock {
        public final Block alpha;

        public CompatTimberFrameBetaBlock(Block alpha) {
            super();
            this.alpha = alpha;
        }

        @Override
        public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
            return new ItemStack(alpha);
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
            return Collections.singletonList(new ItemStack(alpha));
        }

    }

    @Override
    // MCMETA ---
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        frame_alpha.blocks.forEach((wood, block) -> {

            String[] types = {
                    "cross", "filler", "blank", "diagonal_left", "diagonal_right"
            };

            for (String type : types) {
                String path = "_timber_frame_" + type;

                try (InputStream mcmetaStream = manager.getResource(ResType.BLOCK_MCMETA.getPath(modRes("oak" + path)))
                        .orElseThrow(() -> new FileNotFoundException("failed to open the recipe file @" +
                                modRes("oak" + path))).open()
                ) {
                    JsonObject mcmeta = RPUtils.deserializeJson(mcmetaStream);

                    // Copying MCMETA file to the resources
                    String newPath = shortenedId() +"/"+ wood.getAppendableId() + path;

                    handler.dynamicPack.addJson(EveryCompat.res(newPath), mcmeta, ResType.BLOCK_MCMETA);
                }
                catch (IOException e) {
                    handler.getLogger().error("Failed to get oak_{}'s MCMETA : {}", path, e);
                }
            }
        });
    }
}