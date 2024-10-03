package net.mehvahdjukaar.every_compat.modules.valhelsia_furniture;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.valhelsia.valhelsia_furniture.common.block.*;
import net.valhelsia.valhelsia_furniture.core.registry.ModBlockEntities;
import net.valhelsia.valhelsia_furniture.core.registry.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//SUPPORT: v1.1.3+
public class ValhelsiaFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, TableBlock> tables;
    public final SimpleEntrySet<WoodType, ChairBlock> chairs;
    public final SimpleEntrySet<WoodType, ChairBlock> hay_chairs;
    public final SimpleEntrySet<WoodType, StoolBlock> stools;
    public final SimpleEntrySet<WoodType, DeskBlock> desks;
    public final SimpleEntrySet<WoodType, DeskDrawerBlock> desk_drawers;

    public ValhelsiaFurnitureModule(String modId) {
        super(modId, "vf");

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table", TableBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/table/oak/oak_table"))
                // the oak_table_connected texutre is in desk_drawers' EntrySet
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.TABLES, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair", ChairBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatChairBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks), false)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/chair/oak/oak_chair"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.CHAIRS, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(chairs);

        hay_chairs = SimpleEntrySet.builder(WoodType.class, "chair", "hay",
                        getModBlock("hay_oak_chair", ChairBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new compatChairBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks), true)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/chair/oak/hay_oak_chair"),
                        EveryCompat.res("block/vf/hay_oak_chair_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.CHAIRS, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(hay_chairs);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool", StoolBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StoolBlock(w.toVanillaOrOak(), Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/stool/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.STOOLS, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(stools);

        desks = SimpleEntrySet.builder(WoodType.class, "desk",
                        getModBlock("oak_desk", DeskBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(w.toVanillaOrOak(), modTag(w.getTypeName() + "_desks"), Utils.copyPropertySafe(w.planks))
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
                .defaultRecipe()
                .build();
        this.addEntry(desks);

        desk_drawers = SimpleEntrySet.builder(WoodType.class, "desk_drawer",
                        getModBlock("oak_desk_drawer", DeskDrawerBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskDrawerBlock(w.toVanillaOrOak(), modTag(w.getTypeName() + "_desks"), Utils.copyPropertySafe(w.planks))
                )
                .addTile(ModBlockEntities.DESK_DRAWER)
                // Using the same textures from desk's above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.DESKS, Registries.BLOCK)
                .defaultRecipe()
                /*
                * Below is a bit special. has to be separated from the Table's EntrySet above. It has 5 color palettes
                * while the other texture for table is 7 color palettes. Below will only remove one darkest from
                * Planks' 7 color palettes to ensure the texture is not darkened
                */
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTexture(modRes("block/table/oak/oak_table_connected"))

                .build();
        this.addEntry(desk_drawers);

    }

    // Tags
    private TagKey<Block> modTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(EveryCompat.MOD_ID, name));
    }

    // Had to create this because of appendHoverText, "Hay Seat" is showing up on both chairs & hay_chairs
    // chairs shouldn't have "Hay Seat"
    public static class compatChairBlock extends ChairBlock {
        private final boolean isHayCHair;

        public compatChairBlock(net.minecraft.world.level.block.state.properties.WoodType woodType, Properties properties, boolean isHayCHair) {
            super(woodType, properties);
            this.isHayCHair = isHayCHair;
        }

        @Override
        public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
            if (isHayCHair) {
                tooltip.add(Component.translatable("tooltip.valhelsia_furniture.hay_seat").withStyle(ChatFormatting.GRAY));
            }

        }
    }
}