package net.mehvahdjukaar.every_compat.mixins.client;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.ModelWarningSuppressor;
import net.minecraft.client.resources.model.ModelManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

//Prevents missing texture spam if things do fail so at least we can read the logs
@Mixin(ModelManager.class)
public class ModelManagerMixin {

    @Inject(method = {"lambda$loadModels$2", "lambda$loadModels$4"}, at = @At("HEAD"), cancellable = true, require = 0)
    private static void everycompat$suppressMissingTextureSpam(String modelName, Collection<?> missing, CallbackInfo ci) {
        int colon = modelName.indexOf(':');
        if (colon > 0 && EveryCompat.isMyIdOrAddon(modelName.substring(0, colon))) {
            ModelWarningSuppressor.missingTextures++;
            ci.cancel();
        }
    }

    @Inject(method = "lambda$loadModels$1", at = @At("TAIL"), require = 0)
    private static void everycompat$reportSuppressedCount(CallbackInfoReturnable<?> cir) {
        ModelWarningSuppressor.report();
        ModelWarningSuppressor.reset();
    }
}
