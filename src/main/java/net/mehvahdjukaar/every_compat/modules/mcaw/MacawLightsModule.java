package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwlights.kikoz.MacawsLights;
import com.mcwlights.kikoz.init.BlockInit;
import com.mcwlights.kikoz.objects.SoulTikiTorch;
import com.mcwlights.kikoz.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;


public class MacawLightsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SOUL_TIKI_TORCHES;
    public final SimpleEntrySet<WoodType, Block> TIKI_TORCHES;

    public MacawLightsModule(String modId) {
        super(modId, "mcp");
        CreativeModeTab tab;
        try {
            var f = ObfuscationReflectionHelper.findField(MacawsLights.class, "LightsItemGroup");
            tab = (CreativeModeTab) f.get(null);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to initialize {}", this);
            SOUL_TIKI_TORCHES = null;
            TIKI_TORCHES = null;
            return;
        }

        SOUL_TIKI_TORCHES = SimpleEntrySet.builder("tiki_torch", "soul",
                        BlockInit.SOUL_OAK_TIKI_TORCH, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SoulTikiTorch(BlockBehaviour.Properties.copy(w.planks).strength(0.2f, 2.5f), (ParticleOptions)null))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(()-> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();

        this.addEntry(SOUL_TIKI_TORCHES);

        TIKI_TORCHES = SimpleEntrySet.builder("tiki_torch",
                        BlockInit.OAK_TIKI_TORCH, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TikiTorch(BlockBehaviour.Properties.copy(w.planks).strength(0.2f, 2.5f), (ParticleOptions)null))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(()-> RenderType::cutout)
                .defaultRecipe()
                .setTab(tab)
                .build();

        this.addEntry(TIKI_TORCHES);
    }
}
