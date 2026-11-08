package net.mehvahdjukaar.every_compat.mixins.client;

import net.mehvahdjukaar.every_compat.ECModules;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mehvahdjukaar.every_compat.misc.ModelWarningSuppressor;
import net.minecraft.client.resources.model.ModelDiscovery;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelDiscovery.class)
public class ModelDiscoveryMixin {

    @WrapOperation(method = "lambda$new$0",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"))
    private void everycompat$suppressUnableToLoadSpam(Logger logger, String message, Object arg, Operation<Void> original) {
        if (arg instanceof Identifier res && ECModules.isMyIdOrAddon(res.getNamespace())) {
            ModelWarningSuppressor.UNLOADABLE_MODELS.add(res);
            return;
        }
        original.call(logger, message, arg);
    }
}
