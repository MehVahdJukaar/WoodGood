package net.mehvahdjukaar.every_compat.mixins.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.ModelWarningSuppressor;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockStateModelLoader.class)
public class BlockStateModelLoaderMixin {

    @WrapOperation(method = "lambda$loadBlockStateDefinitions$10",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", ordinal = 0),
            require = 0)
    private void everycompat$suppressMissingVariantSpam(Logger logger, String message, Object blockstateFile, Object variant, Operation<Void> original) {
        if (blockstateFile instanceof ResourceLocation res && EveryCompat.isMyIdOrAddon(res.getNamespace())) {
            ModelWarningSuppressor.missingVariantBlockstates.add(res);
            return;
        }
        original.call(logger, message, blockstateFile, variant);
    }
}
