package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwlights.kikoz.objects.LightBaseShort;
import com.mcwlights.kikoz.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawLightsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Block;

//See MacawLightsModuleAbstract's SUPPORTED VERSION
public class MacawLightsModule extends MacawLightsModuleAbstract {

    public MacawLightsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newTikiTorch(WoodType woodType, ParticleOptions particleOptions) {
        return new TikiTorch(copyStandardProperties(woodType), particleOptions);
    }

    @Override
    public Block newLightBaseShort(WoodType woodType) {
        return new LightBaseShort(copyStandardProperties(woodType));
    }
}
