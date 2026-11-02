package net.mehvahdjukaar.every_compat.modules.forge.create;

import net.mehvahdjukaar.every_compat.api.SimpleModule;

public class CreateModule extends SimpleModule {

//    public final SimpleEntrySet<WoodType, Block> windows;
//    public final SimpleEntrySet<WoodType, Block> windowPanes;


    public CreateModule(String modId) {
        super(modId, "c");

//        windows = SimpleEntrySet.builder(WoodType.class, "window",
//                        () -> getModBlock("oak_window"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW
//                        this::makeWindow)
//                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
//                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
//                .defaultRecipe()
//                .setRenderType(() -> RenderType::cutout)
//                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
//                .addTextureM(EveryCompat.res("block/palettes/oak_window"), EveryCompat.res("block/palettes/oak_window_m"))
//                .addTextureM(EveryCompat.res("block/palettes/oak_window_connected"), EveryCompat.res("block/palettes/oak_window_connected_m"))
//                .build();
//
//        this.addEntry(windows);
//
//
//        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
//                        () -> getModBlock("oak_window_pane"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW_PANE
//                        s -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE)))
//                .addTag(Tags.Items.GLASS_PANES, Registries.BLOCK)
//                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
//                .defaultRecipe()
//                .setRenderType(() -> RenderType::cutout)
//                .build();
//
//        this.addEntry(windowPanes);

    }

//    private WindowBlock makeWindow(WoodType w) {
//        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
//                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
//                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public void onClientSetup() {
//        super.onClientSetup();
//        windows.blocks.forEach((w, b) -> {
//            String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";
//
//            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
//                    EveryCompat.res(path), EveryCompat.res(path + "_connected"));
//
//            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(b),
//                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
//            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(windowPanes.blocks.get(w)),
//                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
//        });
//    }
//
//    public void onClientInit() {
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch);
//    }
//
//
//    @OnlyIn(Dist.CLIENT)
//    //we could also remove this and run getCT before client setup
//    public void onTextureStitch(TextureStitchEvent.Pre event) {
//        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
//            windows.blocks.forEach((w, b) -> {
//                String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window_connected";
//                event.addSprite(EveryCompat.res(path));
//            });
//        }
//    }

}