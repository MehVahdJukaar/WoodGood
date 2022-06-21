package net.mehvahdjukaar.every_compat.mixins;

import com.google.gson.JsonElement;
import net.dark_roleplay.marg.client.generators.textures.generator.TextureGenerator;
import net.dark_roleplay.marg.client.listeners.TextureProcessorsReloadListener;
import net.dark_roleplay.projectbrazier.ProjectBrazier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/*
@Pseudo
@Mixin(targets = "net.dark_roleplay.marg.client.listeners.TextureProcessorsReloadListener")
public class ProjectBrazierMixin {

    @Inject(method = "loadGenerator", at = @At("RETURN"))
    public void add(Map<ResourceLocation, JsonElement> objects, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<Map<ResourceLocation, TextureGenerator>> cir){
        ProjectBrazierModule.generateStuff(cir.getReturnValue(), resourceManager);
    }

}*/
