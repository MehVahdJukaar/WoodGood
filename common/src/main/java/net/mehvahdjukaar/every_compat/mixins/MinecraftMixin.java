package net.mehvahdjukaar.every_compat.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.mehvahdjukaar.every_compat.misc.TimeTest;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @ModifyExpressionValue(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/Util;getNanos()J"))
    private long ec$deleteMeLater(long original) {
        TimeTest.start();
        return original;
    }

    @Inject(method = "onGameLoadFinished", at = @At("HEAD"))
    private void ec$deleteMeLater2(CallbackInfo ci) {
        TimeTest.stopAndPrint();
        System.exit(0);
    }
}
