package net.mehvahdjukaar.every_compat.modules.forge.more;

import com.google.gson.JsonObject;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.common_classes.CompatTrappedChestBlock;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;

public class MoreChestVariantsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;

    public MoreChestVariantsModule(String modID) {
        super(modID, "mcv");

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        McvBlockInit.OAK_CHEST, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChestBlock(this::getChestTile,
                                Utils.copyPropertySafe(Blocks.CHEST).mapColor(MapColor.WOOD))
                )
                .addTile(MoreChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests/wooden"), Registries.BLOCK)
                .addTag(modRes("chests/normal"), Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "cats_on_blocks/sit"), Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "fuel/wood"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(modRes("chests/normal"), Registries.ITEM)
                .addTag(modRes("chests/wooden"), Registries.ITEM)
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        McvBlockInit.OAK_TRAPPED_CHEST, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTrappedChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(Blocks.TRAPPED_CHEST).mapColor(MapColor.WOOD))
                )
                .addTile(MoreTrappedBlockEntity::new)
                .addTag(modRes("chests/wooden"), Registries.BLOCK)
                .addTag(modRes("chests/trapped"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "fuel/wood"), Registries.ITEM)
                .addTag(modRes("chests/trapped"), Registries.ITEM)
                .addTag(modRes("chests/wooden"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .build();
        this.addEntry(trappedChests);

    }

    // GetTile -----------------------------------------------------------------------------------------------------------
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return trappedChests.getTile(CompatChestBlockEntity.class);
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
    }

    // BlockEntity -----------------------------------------------------------------------------------------------------------
    private class MoreChestBlockEntity extends CompatChestBlockEntity {
        public MoreChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
        }
    }

    private class MoreTrappedBlockEntity extends CompatChestBlockEntity {
        public MoreTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(trappedChests.getTile(), pos, state);
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(chests.getTile(CompatChestBlockEntity.class), context -> new CompatChestBlockRenderer(context, shortenedId()));
        event.register(trappedChests.getTile(CompatChestBlockEntity.class), context -> new CompatChestBlockRenderer(context, shortenedId()));
    }

    @Deprecated(forRemoval = true)
    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

            trappedChests.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(handler, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak"),
                        EveryCompat.res("entity/mcv_chest_normal_m"),
                        EveryCompat.res("entity/mcv_chest_normal_o"),
                        EveryCompat.res("entity/mcv_trapped_normal_o")
                );
                // LEFT
                generateChestTexture(handler, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak_left"),
                        EveryCompat.res("entity/mcv_chest_left_m"),
                        EveryCompat.res("entity/mcv_chest_left_o"),
                        EveryCompat.res("entity/mcv_trapped_left_o")
                );
                // RIGHT
                generateChestTexture(handler, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak_right"),
                        EveryCompat.res("entity/mcv_chest_right_m"),
                        EveryCompat.res("entity/mcv_chest_right_o"),
                        EveryCompat.res("entity/mcv_trapped_right_o")
                );


                String path = shortenedId() + "/" + wood.getAppendableId() + "_chest";
                String trapped_path = shortenedId() + "/" + wood.getAppendableId() + "_trapped_chest";

                // MODEL BLOCK -----------------------------------------------------------------------------------------
                JsonObject modelBlock;
                JsonObject trappedModel;
                try (InputStream modelStream = manager.getResource(EveryCompat.res("models/block/" + path + ".json"))
                        .orElseThrow(() -> new TypeNotPresentException("Model file: " + path, new NoSuchElementException())).open();
                     InputStream trappedStream = manager.getResource(EveryCompat.res("models/block/" + trapped_path + ".json")) // TODO: correct this
                             .orElseThrow(() -> new TypeNotPresentException("Model file: " + trapped_path, new NoSuchElementException())).open()
                ) {
                    modelBlock = RPUtils.deserializeJson(modelStream);
                    trappedModel = RPUtils.deserializeJson(trappedStream);
                    String textureID = EveryCompat.MOD_ID + ":entity/chest/" + path;
                    String trappedID = EveryCompat.MOD_ID + ":entity/chest/" + trapped_path;

                    // Editing
                    modelBlock.getAsJsonObject("textures").addProperty("wood_type", textureID);

                    trappedModel.getAsJsonObject("textures").addProperty("wood_type", trappedID);

                    // Add to Resource
                    handler.dynamicPack.addJson(EveryCompat.res(path), modelBlock, ResType.BLOCK_MODELS);
                    handler.dynamicPack.addJson(EveryCompat.res(trapped_path), trappedModel, ResType.BLOCK_MODELS);
                } catch (IOException e) {
                    handler.getLogger().error("MoreChestVariantsModule - failed to open the model file: {0}", e);
                }
            });
    }
}
