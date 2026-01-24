package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwlights.kikoz.init.BlockInit;
import com.mcwlights.kikoz.objects.LightBaseShort;
import com.mcwlights.kikoz.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import java.util.function.ToIntFunction;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.FENCE;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

//SUPPORT: v1.1.0+
public class MacawLightsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> soul_tiki_torches;
    public final SimpleEntrySet<WoodType, Block> tiki_torches;
    public final SimpleEntrySet<WoodType, Block> ceiling_fan_lights;

    public MacawLightsModule(String modId) {
        super(modId, "mcl");
        ResourceLocation tab = modRes(modId);

        soul_tiki_torches = SimpleEntrySet.builder(WoodType.class, "tiki_torch", "soul",
                        BlockInit.SOUL_OAK_TIKI_TORCH, () -> VanillaWoodTypes.OAK,
                        w -> new TikiTorch(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(10))
                                .strength(1.5F, 2.5F)
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .noOcclusion(),
                                ParticleTypes.SOUL_FIRE_FLAME
                        )
                )
                .requiresChildren(FENCE) //REASON: recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(soul_tiki_torches);

        tiki_torches = SimpleEntrySet.builder(WoodType.class, "tiki_torch",
                        BlockInit.OAK_TIKI_TORCH, () -> VanillaWoodTypes.OAK,
                        w -> new TikiTorch(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(15))
                                .strength(1.5F, 2.5F)
                                .mapColor(MapColor.WOOD)
                                .sound(SoundType.WOOD)
                                .noOcclusion(),
                                ParticleTypes.FLAME
                        )
                )
                .requiresChildren(FENCE) //REASON: recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(tiki_torches);

        ceiling_fan_lights = SimpleEntrySet.builder(WoodType.class, "ceiling_fan_light",
                        BlockInit.OAK_CEILING_FAN_LIGHT, () -> VanillaWoodTypes.OAK,
                        w -> new LightBaseShort(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(15))
                                .mapColor(MapColor.WOOD)
                                .strength(1.5F, 2.5F)
                                .sound(SoundType.WOOD)
                                .noOcclusion()
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTextureM(modRes("block/oak_ceiling_fan"), EveryCompat.res("block/mcw/lights/ceiling_fan_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(ceiling_fan_lights);
    }

    // METHODS
    private static ToIntFunction<BlockState> blockOffLightValue(int lightLevel) {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }
}
