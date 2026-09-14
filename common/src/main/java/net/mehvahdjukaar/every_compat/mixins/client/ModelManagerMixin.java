package net.mehvahdjukaar.every_compat.mixins.client;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.ModelWarningSuppressor;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

//Prevents missing texture spam if things do fail so at least we can read the logs
@Mixin(ModelManager.class)
public class ModelManagerMixin {

    @Inject(method = "lambda$loadModels$17", at = @At("HEAD"), cancellable = true, require = 0)
    private static void everycompat$suppressMissingTextureSpam(ModelResourceLocation model, Collection<?> materials, CallbackInfo ci) {
        if (EveryCompat.isMyIdOrAddon(model.id().getNamespace())) {
            ModelWarningSuppressor.missingTextures++;
            ci.cancel();
        }
    }

    @Inject(method = "loadModels", at = @At("TAIL"))
    private void everycompat$reportSuppressedCount(CallbackInfoReturnable<?> cir) {
        ModelWarningSuppressor.report();
        ModelWarningSuppressor.reset();
    }
}
