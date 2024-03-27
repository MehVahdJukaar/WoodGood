package net.mehvahdjukaar.every_compat.modules.forge.abnormal;

import com.google.common.collect.ImmutableSet;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.salju.woodster.block.BookshelfBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

//SUPPORT: v??? Not available yet
public class WoodworksModule extends SimpleModule {
//    public final SimpleEntrySet<WoodType, Block> bookshelves;
//    public final SimpleEntrySet<WoodType, Block> boards;
//    public final SimpleEntrySet<WoodType, Block> ladders;
//    public final SimpleEntrySet<WoodType, Block> beehives;
//    public final SimpleEntrySet<WoodType, Block> chests;
//    public final SimpleEntrySet<WoodType, Block> trappedChests;
//    public final SimpleEntrySet<LeavesType, Block> leafPiles;


    public static BlockEntityType<? extends ChestBlockEntity> CHEST_TILE;
    public static BlockEntityType<? extends ChestBlockEntity> TRAPPED_CHEST_TILE;


    public WoodworksModule(String modId) {
        super(modId, "abnww");

/*

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.BLOCK)
                .addTag(Tags.Items.BOOKSHELVES, Registries.ITEM)
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .build();

        this.addEntry(bookshelves);


        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        WoodworksBlocks.OAK_BOARDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks)))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_boards"))
                .defaultRecipe()
                .build();

        this.addEntry(boards);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintLadderBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.ladder()))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:ladders"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:ladders"), Registries.ITEM)
                .defaultRecipe()
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();

        this.addEntry(ladders);

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BlueprintBeehiveBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.beehive()))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .defaultRecipe()
                .addTile(BlueprintBlockEntityTypes.BEEHIVE)
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .build();

        this.addEntry(beehives);

        chests = SimpleEntrySet.builder(WoodType.class, "chest",
                        getModBlock("oak_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintChestBlock(WoodTypeRegistry.OAK_TYPE.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(new ResourceLocation("quark:revertable_chests"), Registries.ITEM)
                .addTag(new ResourceLocation("quark:boatable_chests"), Registries.ITEM)
                .addTile(BlueprintChestBlockEntity::new)
                //.addCustomItem((w, b, p) -> new BEWLRBlockItem(b, p, null))
                .defaultRecipe()
                .build();

        //this.addEntry(CHESTS);

        trappedChests = SimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        getModBlock("oak_trapped_chest"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlueprintTrappedChestBlock(WoodTypeRegistry.OAK_TYPE.getTypeName(), WoodworksBlocks.WoodworksProperties.OAK_WOOD.chest()))
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS_TRAPPED, Registries.ITEM)
                .addTile(BlueprintTrappedChestBlockEntity::new)
                .defaultRecipe()
                .build();

        //this.addEntry(TRAPPED_CHESTS);


        leafPiles = SimpleEntrySet.builder(LeavesType.class, "leaf_pile",
                        WoodworksBlocks.OAK_LEAF_PILE, () -> LeavesTypeRegistry.OAK_TYPE,
                        (w) -> {
                            if (w.getWoodType() == null) return null;
                            return new LeafPileBlock(WoodworksBlocks.WoodworksProperties.OAK_WOOD.leafPile());
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("leaf_piles"), Registries.BLOCK)
                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .setRenderType(() -> RenderType::cutout)
                .copyParentDrop()
                .defaultRecipe()
                .build();

        this.addEntry(leafPiles);
    }


    protected final ResourceLocation POI_ID = EveryCompat.res("ww_beehive");
    private Supplier<PoiType> compatBeeHivePOI = RegHelper.registerPOI(POI_ID,
            () -> new PoiType(getBeehives(), 0, 1));


    private Set<BlockState> getBeehives() {
        var set = new ImmutableSet.Builder<BlockState>();
        beehives.blocks.values().forEach(b->set.addAll(b.getStateDefinition().getPossibleStates()));
        return set.build();
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        SimpleTagBuilder tb = SimpleTagBuilder.of(PoiTypeTags.BEE_HOME);

        tb.add(POI_ID);

        handler.dynamicPack.addTag(tb, Registries.POINT_OF_INTEREST_TYPE);
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
        leafPiles.blocks.forEach((t, b) -> {
            var leaf = t.getChild("leaves");
            if (leaf instanceof Block block) {
                bc.register((s, l, p, i) -> bc.getColor(block.defaultBlockState(), l, p, i), b);
                ic.register((stack, tintIndex) -> ic.getColor(new ItemStack(block.asItem()), tintIndex), b.asItem());
            }
        });
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event){
        super.registerBlockColors(event);
        leafPiles.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors (ClientHelper.ItemColorEvent event){
        leafPiles.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
            //blockColor.register((s, l, p, i) -> blockColor.getColor(bl.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerBlockEntityRenderers (ClientHelper.BlockEntityRendererEvent event){
        super.registerBlockEntityRenderers(event);
        //event.register(CHEST_TILE, ChestRenderer::new);
        //event.register(TRAPPED_CHEST_TILE, ChestRenderer::new);
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

            chests.blocks.forEach((wood, block) -> {

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
*/
    }

}
