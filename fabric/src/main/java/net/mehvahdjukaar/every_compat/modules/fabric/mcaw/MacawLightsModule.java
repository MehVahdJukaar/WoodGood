package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwlights.MacawsLights;
import net.kikoz.mcwlights.init.BlockInit;
import net.kikoz.mcwlights.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


public class MacawLightsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SOUL_TIKI_TORCHES;
    public final SimpleEntrySet<WoodType, Block> TIKI_TORCHES;

    public MacawLightsModule(String modId) {
        super(modId, "mcl");

        SOUL_TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch", "soul",
                        () -> BlockInit.SOUL_OAK_TIKI_TORCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TikiTorch(Utils.copyPropertySafe(w.planks)
                                .strength(0.2f, 2.5f), ParticleTypes.SOUL_FIRE_FLAME))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTabKey(() -> MacawsLights.LIGHTSGROUP)
                .build();

        this.addEntry(SOUL_TIKI_TORCHES);

        TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch",
                        () -> BlockInit.OAK_TIKI_TORCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TikiTorch(Utils.copyPropertySafe(w.planks)
                                .strength(0.2f, 2.5f), ParticleTypes.FLAME))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTabKey(() -> MacawsLights.LIGHTSGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(TIKI_TORCHES);
    }
}
