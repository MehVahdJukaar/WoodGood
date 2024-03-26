package net.mehvahdjukaar.every_compat.dynamicpack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.misc.StrOpt;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ExtraTextureGenerator {

    private static final Gson GSON = new Gson();


    // only works for wood.
    public record RecolorableTexture(List<Text> textures, boolean mergePalette, Block block) {

        public static final Codec<RecolorableTexture> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Text.TEXT_CODEC.listOf().fieldOf("textures").forGetter(RecolorableTexture::textures),
                StrOpt.of(Codec.BOOL, "merge_palette", true).forGetter(RecolorableTexture::mergePalette),
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("planks_block").forGetter(RecolorableTexture::block)
        ).apply(instance, RecolorableTexture::new));
    }

    public record Text(ResourceLocation text, @Nullable ResourceLocation mask) {
        public static final Codec<Text> TEXT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("texture").forGetter(Text::text),
                StrOpt.of(ResourceLocation.CODEC, "mask").forGetter(t -> Optional.ofNullable(t.mask))
        ).apply(instance, (a, b) -> new Text(a, b.orElse(null))));
    }

    public static void generateExtraTextures(DynClientResourcesGenerator generator, ResourceManager manager) {
        Map<ResourceLocation, JsonElement> map = new HashMap();
        SimpleJsonResourceReloadListener.scanDirectory(manager, "recolorable_textures", GSON, map);
        var extraTextures = new ArrayList<RecolorableTexture>();
        map.forEach((id, json) -> {

            RecolorableTexture modifier = RecolorableTexture.CODEC.decode(JsonOps.INSTANCE, json)
                    .getOrThrow(false, errorMsg -> EveryCompat.LOGGER.warn("Failed to load recolorable texture {}: {}", id, errorMsg))
                    .getFirst();
            extraTextures.add(modifier);
        });

        for (var v : extraTextures) {

            WoodType baseWood = WoodTypeRegistry.INSTANCE.getBlockTypeOf(v.block);
            SimpleEntrySet.Builder<WoodType, Block> dummy = SimpleEntrySet.builder(WoodType.class, "dummy",
                    () -> v.block, () -> baseWood,
                    w -> null);

            for (var t : v.textures) {
                if (t.mask != null) dummy.addTextureM(t.text, t.mask);
                else dummy.addTexture(t.text);
            }
            if (v.mergePalette) dummy.useMergedPalette();

            dummy.build().generateTextures(null, generator, manager);
        }
    }
}
