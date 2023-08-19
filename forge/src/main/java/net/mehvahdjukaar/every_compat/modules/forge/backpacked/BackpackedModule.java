package net.mehvahdjukaar.every_compat.modules.forge.backpacked;

import com.mrcrayfish.backpacked.block.ShelfBlock;
import com.mrcrayfish.backpacked.core.ModBlockEntities;
import com.mrcrayfish.backpacked.core.ModBlocks;
import com.mrcrayfish.backpacked.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//credit to WenXin2
public class BackpackedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> shelves;

    public BackpackedModule(String modId) {
        super(modId, "bp");

        shelves = SimpleEntrySet.builder(WoodType.class, "backpack_shelf",
                        ModBlocks.OAK_BACKPACK_SHELF::get, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(ModCreativeTabs.MAIN::get)
                .addRecipe(modRes("oak_backpack_shelf"))
                .addTile(ModBlockEntities.SHELF::get)
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(shelves);
    }
}
