package net.mehvahdjukaar.every_compat.modules.twigs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.moddingplayground.twigs.block.TableBlock;
import net.moddingplayground.twigs.init.TwigsBlocks;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> TABLES;

    public TwigsModule(String modId) {
        super(modId, "tw");

        TABLES = SimpleEntrySet.builder(WoodType.class, "table",
                        TwigsBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).instabreak()))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak((p) -> p.remove(p.getDarkest()))
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_table_top"))
                .addTexture(modRes("block/oak_table_bottom"))
                .build();

        this.addEntry(TABLES);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);


        try (TextureImage hammock = TextureImage.open(manager, EveryCompat.res("hammock"));
             TextureImage mask = TextureImage.open(manager, EveryCompat.res("hammock_overlay"));
             TextureImage bed_mask = TextureImage.open(manager, EveryCompat.res("bed_mask"))
        ) {


            for (var  d : DyeColor.values()                                   ) {
                var r = Sheets.BED_TEXTURES[d.getId()];

                try (TextureImage bedTexture = TextureImage.open(manager, r.texture())) {

                    Palette p = Palette.fromImage(bedTexture, bed_mask);

                    Respriter res = Respriter.of(hammock);

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation("hammocks",
                            "item/" + d.getName()), res.recolor(p));

                } catch (Exception ignored) {
                }
            }
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Tiki torch item texture : ", ex);
        }
    }
}
