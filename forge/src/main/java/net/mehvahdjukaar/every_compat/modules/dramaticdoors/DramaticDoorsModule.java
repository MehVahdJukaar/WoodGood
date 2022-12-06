package net.mehvahdjukaar.every_compat.modules.dramaticdoors;

import com.fizzware.dramaticdoors.DramaticDoors;
import com.fizzware.dramaticdoors.blocks.DDBlocks;
import com.fizzware.dramaticdoors.blocks.TallDoorBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DramaticDoorsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> TALL_DOORS;
    public DramaticDoorsModule(String modId) {
        super(modId, "dd");

        TALL_DOORS = SimpleEntrySet.builder(WoodType.class, "door", "tall",
                        DDBlocks.TALL_SPRUCE_DOOR,
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TallDoorBlock(Blocks.SPRUCE_DOOR))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("dramaticdoors", "tall_wooden_doors"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("dramaticdoors", "tall_wooden_doors"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("item/tall_spruce_door"))
                .addTexture(modRes("block/tall_spruce_door_bottom"))
                .addTexture(modRes("block/tall_spruce_door_middle"))
                .addTexture(modRes("block/tall_spruce_door_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> DramaticDoors.MAIN_TAB)
                .defaultRecipe()
                .useLootFromBase()
                .build();

        this.addEntry(TALL_DOORS);
    }
}
