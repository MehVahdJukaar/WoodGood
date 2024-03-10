package net.mehvahdjukaar.every_compat.modules.crayfish;

import com.mrcrayfish.furniture.refurbished.block.ChairBlock;
import com.mrcrayfish.furniture.refurbished.block.TableBlock;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RefurbishedFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> tables;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");

        // somebody else pls finish this <3
        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of().strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_chair"))
                .build();

        this.addEntry(chairs);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of()
                                .strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_table"))
                .build();

        this.addEntry(tables);
    }

}
