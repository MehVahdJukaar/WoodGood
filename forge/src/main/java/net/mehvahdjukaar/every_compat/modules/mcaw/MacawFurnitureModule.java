package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwfurnitures.kikoz.MacawsFurnitures;
import com.mcwfurnitures.kikoz.init.BlockInit;
import com.mcwfurnitures.kikoz.objects.TallFurniture;
import com.mcwfurnitures.kikoz.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CUPBOARD_COUNTER;
    public final SimpleEntrySet<WoodType, Block> MODERN_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> WARDROBE;

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        CreativeModeTab tab = MacawsFurnitures.FURNITUREITEMGROUP;


        CUPBOARD_COUNTER = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        BlockInit.OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(CUPBOARD_COUNTER);

        WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        BlockInit.OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(WARDROBE);

        MODERN_WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        BlockInit.OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MODERN_WARDROBE);
    }
}
