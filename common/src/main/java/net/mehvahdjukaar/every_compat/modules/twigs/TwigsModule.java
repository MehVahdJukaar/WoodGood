package net.mehvahdjukaar.every_compat.modules.twigs;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.moddingplayground.twigs.block.TableBlock;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;

    public TwigsModule(String modId) {
        super(modId, "tw");

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
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

        this.addEntry(tables);
    }

}
