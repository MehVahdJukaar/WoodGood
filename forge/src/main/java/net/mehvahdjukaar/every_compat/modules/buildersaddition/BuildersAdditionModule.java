package net.mehvahdjukaar.every_compat.modules.buildersaddition;

import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.Chair;
import com.mrh0.buildersaddition.blocks.Hedge;
import com.mrh0.buildersaddition.blocks.Stool;
import com.mrh0.buildersaddition.blocks.Table;
import com.mrh0.buildersaddition.blocks.VerticalSlab;
import com.mrh0.buildersaddition.itemgroup.ModGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vazkii.quark.base.Quark;


public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CHAIR;
    public final SimpleEntrySet<LeavesType, Block> HEDGE;
    public final SimpleEntrySet<WoodType, Block> PANEL;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> TABLE;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        CreativeModeTab tab = ModGroup.MAIN;


        PANEL = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(new ResourceLocation(Quark.MOD_ID + ":wooden_vertical_slabs"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":wooden_vertical_slabs"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":vertical_slab"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/vertical_slab/oak_vertical_slab"))
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .build();

        this.addEntry(PANEL);

        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        Index.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/table/table_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(TABLE);

        STOOL = SimpleEntrySet.builder(WoodType.class, "stool",
                        Index.STOOL_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("stool/stool_oak"))
                .addRecipe(modRes("block/stool/stool_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(STOOL);

        CHAIR = SimpleEntrySet.builder(WoodType.class, "chair",
                        Index.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("chair/chair_oak"))
                .addRecipe(modRes("block/chair/chair_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CHAIR);

        HEDGE = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        Index.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new Hedge(shortenedId() + "/" + w.getAppendableId(), l);
                        })
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("hedge/hedge_oak"))
                .addRecipe(modRes("block/hedge/hedge_oak"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(HEDGE);
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        HEDGE.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        HEDGE.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        });
    }
}
