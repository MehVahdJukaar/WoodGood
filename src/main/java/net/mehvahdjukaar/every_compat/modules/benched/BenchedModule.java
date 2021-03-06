package net.mehvahdjukaar.every_compat.modules.benched;

import com.supermartijn642.benched.blocks.BenchBlock;
import com.supermartijn642.benched.blocks.BenchTile;
import com.supermartijn642.benched.blocks.BenchTileRenderer;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;


public class BenchedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BENCHES;

    public BenchedModule(String modId) {
        super(modId, "bd");

        BENCHES = SimpleEntrySet.builder(WoodType.class, "bench",
                        () -> this.getModBlock("spruce_bench"), () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new CompatBenchBlock(this.shortenedId() + "/" + w.getVariantId("bench", false)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .defaultRecipe()
                .addTile(CompatBenchBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("spruce_bench"))
                .addModelTransform(m -> {
                    m.addModifier((t, i, w) -> t.replace("benched:spruce", "everycompat:" + this.shortenedId() + "/" + w.getAppendableId()));
                    //m.replaceString("\"main\": \"everycomp:","\"main\": \"everycomp:block/");
                })
                .build();

        this.addEntry(BENCHES);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        //super.addStaticClientResources(handler, manager);
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<? extends BenchTile>) (BENCHES.getTileHolder().tile), context -> new BenchTileRenderer());
    }

    class CompatBenchBlockEntity extends BenchTile {

        public CompatBenchBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return BENCHES.getTileHolder().tile;
        }
    }

    private class CompatBenchBlock extends BenchBlock {
        public CompatBenchBlock(String registryName) {
            super(registryName);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatBenchBlockEntity(pos, state);
        }
    }
}
