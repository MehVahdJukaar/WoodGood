package net.mehvahdjukaar.every_compat.modules.lieonlion;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.common_classes.CompatTrappedChestBlock;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;

//SUPPORT: FABRIC-v1.5.8+ | NEOFORGE-v1.5.9+
public class MoreChestVariantsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;

    public MoreChestVariantsModule(String modID) {
        super(modID, "mcv");
        ResourceKey<CreativeModeTab> functionalTab = CreativeModeTabs.FUNCTIONAL_BLOCKS;
        ResourceKey<CreativeModeTab> redstoneTab = CreativeModeTabs.REDSTONE_BLOCKS;


        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        getModBlock("oak_chest"), () -> VanillaWoodTypes.OAK,
                        w -> new CompatChestBlock(this::getChestTile,
                                Utils.copyPropertySafe(Blocks.CHEST).mapColor(MapColor.WOOD))
                )
                //REASON: ensure Chest's texture in inventory is corrected
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"lolmcv:entity/chest/oak\"",
                                "\""+woodType.createFullIdWith(EveryCompat.MOD_ID, "entity/chest", shortenedId(), "", "chest")+"\"" )
                ))
                .addTile(MoreChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(ResourceLocation.parse("lieonstudio:chests/wooden"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("lieonstudio:chests/normal"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("quad:cats_on_blocks/sit"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("quad:fuel/wood"), Registries.ITEM)
                .setTabKey(functionalTab)
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        getModBlock("oak_trapped_chest"), () -> VanillaWoodTypes.OAK,
                        w -> new CompatTrappedChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(Blocks.TRAPPED_CHEST).mapColor(MapColor.WOOD))
                )
                //REASON: ensure Chest's texture in inventory is corrected
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"lolmcv:entity/chest/trapped/oak\"",
                                "\""+woodType.createFullIdWith(EveryCompat.MOD_ID, "entity/chest", shortenedId(), "", "trapped_chest")+"\"" )
                ))
                .addTile(MoreTrappedBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(ResourceLocation.parse("lieonstudio:chests/wooden"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("lieonstudio:chests/trapped"), Registries.BLOCK, Registries.ITEM)
                .addTag(ResourceLocation.parse("quad:fuel/wood"), Registries.ITEM)
                .setTabKey(redstoneTab)
                .defaultRecipe()
                .build();
        this.addEntry(trappedChests);

    }

    // GetTile ---------------------------------------------------------------------------------------------------------
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return trappedChests.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity -----------------------------------------------------------------------------------------------------
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
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        CompatChestBlockRenderer.register(event, trappedChests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) -> {
            trappedChests.blocks.forEach((wood, block) -> {
                // SINGLE
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak"),
                        EveryCompat.res("entity/mcv_chest_normal_m"),
                        EveryCompat.res("entity/mcv_chest_normal_o"),
                        EveryCompat.res("entity/mcv_trapped_normal_o"), 0
                );
                // LEFT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak_left"),
                        EveryCompat.res("entity/mcv_chest_left_m"),
                        EveryCompat.res("entity/mcv_chest_left_o"),
                        EveryCompat.res("entity/mcv_trapped_left_o"), 0
                );
                // RIGHT
                generateChestTexture(sink, manager, shortenedId(), wood, block,
                        modRes("entity/chest/oak_right"),
                        EveryCompat.res("entity/mcv_chest_right_m"),
                        EveryCompat.res("entity/mcv_chest_right_o"),
                        EveryCompat.res("entity/mcv_trapped_right_o"), 0
                );

            });

        });
    }

}
