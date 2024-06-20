package net.mehvahdjukaar.every_compat.modules.handcrafted;

import earth.terrarium.handcrafted.common.item.CounterBlockItem;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class compatModItems {

    public static class TableItem extends BlockItem implements ICustomItemRendererProvider {

        public TableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.TableItemRenderer::new;
        }
    }

    public static class ChairItem extends BlockItem implements ICustomItemRendererProvider {

        public ChairItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.ChairItemRenderer::new;
        }
    }

    public static class BenchItem extends BlockItem implements ICustomItemRendererProvider {

        public BenchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.BenchItemRenderer::new;
        }
    }

    public static class CouchItem extends BlockItem implements ICustomItemRendererProvider {

        public CouchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.CouchItemRenderer::new;
        }
    }

    public static class FancyBedItem extends BlockItem implements ICustomItemRendererProvider {

        public FancyBedItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.FancyBedItemRenderer::new;
        }
    }

    public static class DiningBenchItem extends BlockItem implements ICustomItemRendererProvider {

        public DiningBenchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.DiningBenchItemRenderer::new;
        }
    }

    public static class NightstandItem extends BlockItem implements ICustomItemRendererProvider {
        public NightstandItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.NightstandItemRenderer::new;
        }
    }

    public static class DeskItem extends BlockItem implements ICustomItemRendererProvider {
        public DeskItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.DeskItemRenderer::new;
        }
    }

    public static class SideTableItem extends BlockItem implements ICustomItemRendererProvider {
        public SideTableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.SideTableItemRenderer::new;
        }
    }

    public static class CounterItem extends CounterBlockItem implements ICustomItemRendererProvider {
        public CounterItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return compatItemRenderer.CounterItemRenderer::new;
        }
    }

}
