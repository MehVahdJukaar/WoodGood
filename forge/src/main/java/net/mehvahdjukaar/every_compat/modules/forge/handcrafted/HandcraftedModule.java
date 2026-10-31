package net.mehvahdjukaar.every_compat.modules.forge.handcrafted;

import net.mehvahdjukaar.every_compat.modules.handcrafted.CompatItemRenderer;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/// Only for registering BlockItem's Renderer Classes
public class HandcraftedModule extends HandcraftedModuleAbstract {

    public HandcraftedModule(String modId) {
        super(modId);
    }

    @Override
    public BlockItem RenderedChairItem(WoodType woodType, Block block, Item.Properties properties) {
        return new ChairItem(block, properties);
    }

    @Override
    public BlockItem RenderedTableItem(WoodType woodType, Block block, Item.Properties properties) {
        return new TableItem(block, properties);
    }

    @Override
    public BlockItem RenderedBenchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BenchItem(block, properties);
    }

    @Override
    public BlockItem RenderedCouchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new CouchItem(block, properties);
    }

    @Override
    public BlockItem RenderedFancyBedItem(WoodType woodType, Block block, Item.Properties properties) {
        return new FancyBedItem(block, properties);
    }

    @Override
    public BlockItem RenderedDiningBenchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new DiningBenchItem(block, properties);
    }

    @Override
    public BlockItem RenderedNightstandItem(WoodType woodType, Block block, Item.Properties properties) {
        return new NightstandItem(block, properties);
    }

    @Override
    public BlockItem RenderedDeskItem(WoodType woodType, Block block, Item.Properties properties) {
        return new DeskItem(block, properties);
    }

    @Override
    public BlockItem RenderedSideTableItem(WoodType woodType, Block block, Item.Properties properties) {
        return new SideTableItem(block, properties);
    }

    @Override
    public Item RenderedCounterItem(Block block, CreativeModeTab tab) {
        return new CounterItem(block, new Item.Properties().tab(tab));
    }



    public static class ChairItem extends BlockItem {

        public ChairItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.ChairItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.ChairItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class TableItem extends BlockItem {

        public TableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.TableItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.TableItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class BenchItem extends BlockItem {

        public BenchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.BenchItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.BenchItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class CouchItem extends BlockItem {

        public CouchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.CouchItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.CouchItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class FancyBedItem extends BlockItem {

        public FancyBedItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.FancyBedItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.FancyBedItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class DiningBenchItem extends BlockItem {

        public DiningBenchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.DiningBenchItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.DiningBenchItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class NightstandItem extends BlockItem {

        public NightstandItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.NightstandItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.NightstandItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class DeskItem extends BlockItem {

        public DeskItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.DeskItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.DeskItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class SideTableItem extends BlockItem {

        public SideTableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.SideTableItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.SideTableItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

    public static class CounterItem extends BlockItem {

        public CounterItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                private CompatItemRenderer.CounterItemRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    if (renderer == null) {
                        renderer = new CompatItemRenderer.CounterItemRenderer();
                    }
                    return renderer;
                }
            });
        }
    }

}