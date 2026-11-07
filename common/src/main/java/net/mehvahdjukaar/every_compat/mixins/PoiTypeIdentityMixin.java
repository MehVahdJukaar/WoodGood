package net.mehvahdjukaar.every_compat.mixins;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//1.20 only. see poi type hash depends on matching states so mods that add stuff to it will break the hash.
@Mixin(PoiType.class)
public abstract class PoiTypeIdentityMixin {

    @Inject(method = "hashCode", at = @At("HEAD"), cancellable = true)
    private void everycomp$identityHash(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(System.identityHashCode(this));
    }

    @Inject(method = "equals", at = @At("HEAD"), cancellable = true)
    private void everycomp$identityEquals(Object other, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this == other);
    }
}
