package net.mehvahdjukaar.every_compat.mixins.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.ModelWarningSuppressor;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {

    @WrapOperation(method = "getModel",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;[Ljava/lang/Object;)V"))
    private void everycompat$suppressUnableToLoadSpam(Logger logger, String message, Object[] args, Operation<Void> original) {
        if (args.length > 0 && args[0] instanceof ResourceLocation res && EveryCompat.isMyIdOrAddon(res.getNamespace())) {
            ModelWarningSuppressor.unloadableModels.add(res);
            return;
        }
        original.call(logger, message, args);
    }
}
