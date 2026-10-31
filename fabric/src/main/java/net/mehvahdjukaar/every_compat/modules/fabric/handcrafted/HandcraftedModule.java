package net.mehvahdjukaar.every_compat.modules.fabric.handcrafted;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.handcrafted.CompatItemRenderer;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/// This is only for registering Item's Custom Renderer Class
public class HandcraftedModule extends HandcraftedModuleAbstract {

    public HandcraftedModule(String modId) {
        super(modId);
    }

    @Override
    public void onClientInit() {
        super.onClientInit();
        registerItemRenderer(chair, new CompatItemRenderer.ChairItemRenderer());
        registerItemRenderer(table, new CompatItemRenderer.TableItemRenderer());
        registerItemRenderer(bench, new CompatItemRenderer.BenchItemRenderer());
        registerItemRenderer(couch, new CompatItemRenderer.CouchItemRenderer());
        registerItemRenderer(nightstand, new CompatItemRenderer.NightstandItemRenderer());
        registerItemRenderer(fancyBed, new CompatItemRenderer.FancyBedItemRenderer());
        registerItemRenderer(diningBench, new CompatItemRenderer.DiningBenchItemRenderer());
        registerItemRenderer(desk, new CompatItemRenderer.DeskItemRenderer());
        registerItemRenderer(sideTable, new CompatItemRenderer.SideTableItemRenderer());

        counter.items.values().forEach(item ->
                BuiltinItemRendererRegistry.INSTANCE.register(item, new CompatItemRenderer.CounterItemRenderer()::renderByItem));
    }

    private void registerItemRenderer(SimpleEntrySet<WoodType, Block> EntrySet, BlockEntityWithoutLevelRenderer render) {
        EntrySet.blocks.values().forEach(block ->
                BuiltinItemRendererRegistry.INSTANCE.register(block.asItem(), render::renderByItem)
        );
    }

    // ─────────────────────────────────────────────────────────────────────────────────
    @Override
    public BlockItem RenderedChairItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedTableItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedBenchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedCouchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedFancyBedItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedDiningBenchItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedNightstandItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedDeskItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public BlockItem RenderedSideTableItem(WoodType woodType, Block block, Item.Properties properties) {
        return new BlockItem(block, properties);
    }

    @Override
    public Item RenderedCounterItem(Block block, CreativeModeTab tab) {
        return new BlockItem(block, new Item.Properties().tab(tab));
    }
}