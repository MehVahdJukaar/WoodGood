package net.mehvahdjukaar.every_compat.modules.abnormal;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintLadderBlock;
import com.teamabnormals.blueprint.common.block.BookshelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;


public class WoodworksModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final SimpleEntrySet<WoodType, Block> BOARDS;
    public final SimpleEntrySet<WoodType, Block> LADDERS;
    public final SimpleEntrySet<WoodType, Block> BEEHIVES;
    public final SimpleEntrySet<WoodType, Block> CHESTS;
    public final SimpleEntrySet<WoodType, Block> TRAPPED_CHESTS;
    public final SimpleEntrySet<LeavesType, Block> LEAF_PILES;


    public static BlockEntityType<? extends ChestBlockEntity> CHEST_TILE;
    public static BlockEntityType<? extends ChestBlockEntity> TRAPPED_CHEST_TILE;


    public WoodworksModule(String modId) {
        super(modId, "abnww");

        BOOKSHELVES = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .build();

        this.addEntry(BOOKSHELVES);


        BOARDS = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_boards"))
                .defaultRecipe()
                .build();

        this.addEntry(BOARDS);

        LADDERS = SimpleEntrySet.builder(WoodType.class, "ladder",
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintLadderBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.ladder()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:ladders"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();

        this.addEntry(LADDERS);

        BEEHIVES = SimpleEntrySet.builder(WoodType.class, "beehive",
                        () -> getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintBeehiveBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.beehive()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .build();

        this.addEntry(BEEHIVES);

        CHESTS = SimpleEntrySet.builder(WoodType.class, "chest",
                        () -> getModBlock("oak_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintChestBlock(WoodTypeRegistry.OAK_TYPE.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:revertable_chests"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:boatable_chests"), Registry.ITEM_REGISTRY)
                .addTile(BlueprintChestBlockEntity::new)
                //.addCustomItem((w, b, p) -> new BEWLRBlockItem(b, p, null))
                .defaultRecipe()
                .build();

        //this.addEntry(CHESTS);

        TRAPPED_CHESTS = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        () -> getModBlock("oak_trapped_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintTrappedChestBlock(WoodTypeRegistry.OAK_TYPE.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registry.ITEM_REGISTRY)
                .addTile(BlueprintTrappedChestBlockEntity::new)
                .defaultRecipe()
                .build();

        //this.addEntry(TRAPPED_CHESTS);


        LEAF_PILES = SimpleEntrySet.builder(LeavesType.class, "leaf_pile",
                        WoodworksBlocks.OAK_LEAF_PILE, () -> LeavesTypeRegistry.OAK_TYPE,
                        (w) -> {
                            if (w.getWoodType() == null) return null;
                            return new LeafPileBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.leafPile());
                        })
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("leaf_piles"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .useLootFromBase()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(LEAF_PILES);
    }

    @Override
    public void registerTiles (Registrator < BlockEntityType < ? >> registry){
        super.registerTiles(registry);
        //CHEST_TILE = (BlockEntityType<? extends ChestBlockEntity>) CHESTS.getTileHolder().tile;
        //TRAPPED_CHEST_TILE = (BlockEntityType<? extends ChestBlockEntity>) TRAPPED_CHESTS.getTileHolder().tile;
    }


    public void onFirstClientTick1 () {
        var ic = Minecraft.getInstance().getItemColors();
        var bc = Minecraft.getInstance().getBlockColors();
        LEAF_PILES.blocks.forEach((t, b) -> {
            var leaf = t.getChild("leaves");
            if (leaf instanceof Block block) {
                bc.register((s, l, p, i) -> bc.getColor(block.defaultBlockState(), l, p, i), b);
                ic.register((stack, tintIndex) -> ic.getColor(new ItemStack(block.asItem()), tintIndex), b.asItem());
            }
        });
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event){
        super.registerBlockColors(event);
    }

    @Override
    public void registerItemColors (ClientPlatformHelper.ItemColorEvent event){
        LEAF_PILES.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
            //blockColor.register((s, l, p, i) -> blockColor.getColor(bl.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerBlockEntityRenderers (ClientPlatformHelper.BlockEntityRendererEvent event){
        super.registerBlockEntityRenderers(event);
        //event.register(CHEST_TILE, ChestRenderer::new);
        //event.register(TRAPPED_CHEST_TILE, ChestRenderer::new);
    }

    @Override
    public void onClientInit () {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch);
    }

    private static final ResourceLocation CHEST_SHEET = new ResourceLocation("textures/atlas/chest.png");
    @EventCalled
    public void onTextureStitch (TextureStitchEvent.Pre event){
        if (event.getAtlas().location().equals(CHEST_SHEET)) {
            // TODO chest renderer stitch thing here instead of quarks. idk
            //CHESTS.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
            //TRAPPED_CHESTS.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
        }

    }

    //TODO: if this is to be added should be merge with quark module since textures are the same
    @Override
    public void addDynamicClientResources (ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage normal = TextureImage.open(manager, modRes("entity/chest/oak/normal"));
             TextureImage normal_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_o"));
             TextureImage left = TextureImage.open(manager, modRes("entity/chest/oak/normal_left"));
             TextureImage left_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_o"));
             TextureImage right = TextureImage.open(manager, modRes("entity/chest/oak/normal_right"));
             TextureImage right_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_o"));
             TextureImage left_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_left"));
             TextureImage right_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_right"));
             TextureImage normal_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_normal"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            CHESTS.blocks.forEach((wood, block) -> {

                BlueprintChestBlock b = (BlueprintChestBlock) block;

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : targetPalette) {
                        var d1 = p.getDarkest();
                        p.remove(d1);
                        var d2 = p.getDarkest();
                        p.remove(d2);
                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    {
                        ResourceLocation res = modRes("entity/chest/" + b.getChestType() + "normal");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes("entity/chest/" + b.getChestType() + "trapped");

                            var img = respriterNormal.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterNormalO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();

                            trapped.applyOverlayOnExisting(normal_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    {
                        ResourceLocation res = modRes("entity/chest/" + b.getChestType() + "normal_left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes("entity/chest/" + b.getChestType() + "trapped_left");

                            var img = respriterLeft.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterLeftO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(left_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    {
                        ResourceLocation res = modRes("entity/chest/" + b.getChestType() + "normal_right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes("entity/chest/" + b.getChestType() + "trapped_right");

                            var img = respriterRight.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterRightO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(right_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }
}
