package net.mehvahdjukaar.every_compat.modules.valhelsia_furniture;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.valhelsia.valhelsia_furniture.common.block.*;
import net.valhelsia.valhelsia_furniture.core.registry.ModBlockEntities;
import net.valhelsia.valhelsia_furniture.core.registry.ModTags;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

///SUPPORT: v1.1.1+
public class ValhelsiaFurnitureModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, TableBlock> tables;
    public final SimpleEntrySet<WoodType, ChairBlock> chairs;
    public final SimpleEntrySet<WoodType, ChairBlock> hay_chairs;
    public final SimpleEntrySet<WoodType, StoolBlock> stools;
    public final SimpleEntrySet<WoodType, DeskBlock> desks;
    public final SimpleEntrySet<WoodType, DeskDrawerBlock> desk_drawers;

    public ValhelsiaFurnitureModule(String modId) {
        super(modId, "vf");
        Supplier<CreativeModeTab> tab = getTab(modRes("main"));

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table", TableBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/table/oak/oak_table"))
                .addTexture(modRes("block/table/oak/oak_table_connected"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.TABLES, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair", ChairBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new CompatChairBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks), false)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/chair/oak/oak_chair"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.CHAIRS, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(chairs);

        hay_chairs = SimpleEntrySet.builder(WoodType.class, "chair", "hay",
                        getModBlock("hay_oak_chair", ChairBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new CompatChairBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks), true)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/chair/oak/hay_oak_chair"),
                        EveryCompat.res("block/vf/hay_oak_chair_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.CHAIRS, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(hay_chairs);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool", StoolBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new StoolBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/stool/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.STOOLS, Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(stools);

        desks = SimpleEntrySet.builder(WoodType.class, "desk",
                        getModBlock("oak_desk", DeskBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new DeskBlock(w.toVanillaOrOak(), modTag(w.getAppendableId() + "_desks"), Utils.copyPropertySafe(w.planks))
                )
                .addTextureM(modRes("block/desk/oak/front"),
                        EveryCompat.res("block/vf/desk_front_m"))
                .addTexture(modRes("block/desk/oak/middle"))
                .addTexture(modRes("block/desk/oak/side"))
                .addTexture(modRes("block/desk/oak/top"))
                .addTexture(modRes("block/desk/oak/top_middle"))
                .addTexture(modRes("block/desk/oak/top_side"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.DESKS, Registries.BLOCK)
                .addTag(ModTags.Items.DESKS, Registries.ITEM)
                .defaultRecipe()
                .setTab(tab)
                .build();
        this.addEntry(desks);

        desk_drawers = SimpleEntrySet.builder(WoodType.class, "desk_drawer",
                        getModBlock("oak_desk_drawer", DeskDrawerBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new DeskDrawerBlock(w.toVanillaOrOak(), modTag(w.getAppendableId() + "_desks"), Utils.copyPropertySafe(w.planks))
                )
                .addTile(ModBlockEntities.DESK_DRAWER)
                // Using the same textures from desk's above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.DESKS, Registries.BLOCK)
                .addTag(ModTags.Items.DESKS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(desk_drawers);

    }

    private TagKey<Block> modTag(String name) {
        return TagKey.create(Registries.BLOCK, EveryCompat.res(name));
    }

    @Override
    // TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink)->

            desks.blocks.forEach((woodType, block) -> {
                UtilityTag.createAndAddCustomTags(EveryCompat.res(woodType.createPathWith("", "desks")), sink,
                        block, desk_drawers.blocks.get(woodType));
                UtilityTag.createAndAddCustomTags(EveryCompat.res(woodType.createPathWith("", "chairs")), sink,
                        chairs.blocks.get(woodType), hay_chairs.blocks.get(woodType));
                UtilityTag.createAndAddCustomTags(EveryCompat.res(woodType.createPathWith("", "tables")), sink,
                        tables.blocks.get(woodType));
            })

        );
    }

    //REASON: Had to create this because of appendHoverText, "Hay Seat" is showing up on both chairs & hay_chairs
    // Chairs shouldn't have "Hay Seat"
    public static class CompatChairBlock extends ChairBlock {
        private final boolean isHayCHair;

        public CompatChairBlock(net.minecraft.world.level.block.state.properties.WoodType woodType, Properties properties, boolean isHayCHair) {
            super(woodType, properties);
            this.isHayCHair = isHayCHair;
        }

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
            if (isHayCHair) {
                tooltipComponents.add(Component.translatable("tooltip.valhelsia_furniture.hay_seat").withStyle(ChatFormatting.GRAY));
            }

        }
    }
}