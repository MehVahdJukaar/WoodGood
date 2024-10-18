package net.mehvahdjukaar.every_compat.modules.fabric.lightmans_currency;

import io.github.lightman314.lightmanscurrency.client.renderer.blockentity.ItemTraderBlockEntityRenderer;
import io.github.lightman314.lightmanscurrency.common.ModCreativeGroups;
import io.github.lightman314.lightmanscurrency.common.blockentity.trader.ItemTraderBlockEntity;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.CardDisplayBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.ShelfBlock;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: v1.0.2.4+
public class LightmansCurrencyModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> card_displays;

    public LightmansCurrencyModule(String modId) {
        super(modId, "lc");
        var tab = modRes("trading");

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        getModBlock("shelf_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(FabricBlockSettings.create().nonOpaque()
                                .strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                        )
                )
                .addTile(() -> ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(new ResourceLocation("create:non_movable"), Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(modRes("trader_normal"), Registries.ITEM)
                .addTag(modRes("shelf"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(shelves);

        card_displays = SimpleEntrySet.builder(WoodType.class, "", "card_display",
                        getModBlock("card_display_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CardDisplayBlock(FabricBlockSettings.create().nonOpaque()
                                .strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                        )
                )
                .addTile(() -> ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using log, log_top, & planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(new ResourceLocation("create:non_movable"), Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(modRes("trader_normal"), Registries.ITEM)
                .addTag(modRes("shelf"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(card_displays);

    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(shelves.getTile(ItemTraderBlockEntity.class), ItemTraderBlockEntityRenderer::new);
    }

}