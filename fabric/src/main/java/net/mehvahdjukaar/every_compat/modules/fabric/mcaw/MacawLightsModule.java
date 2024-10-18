package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwlights.MacawsLights;
import net.kikoz.mcwlights.init.BlockInit;
import net.kikoz.mcwlights.objects.LightBaseShort;
import net.kikoz.mcwlights.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import java.util.function.ToIntFunction;

//SUPPORT: v1.1.0+
public class MacawLightsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SOUL_TIKI_TORCHES;
    public final SimpleEntrySet<WoodType, Block> TIKI_TORCHES;
    public final SimpleEntrySet<WoodType, Block> ceiling_fan_lights;

    public MacawLightsModule(String modId) {
        super(modId, "mcl");
        ResourceKey<CreativeModeTab> tab = MacawsLights.LIGHTSGROUP;

        SOUL_TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch", "soul",
                        () -> BlockInit.SOUL_OAK_TIKI_TORCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TikiTorch(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(10))
                                .strength(1.5F, 2.5F)
                                .sound(SoundType.WOOD)
                                .mapColor(MapColor.WOOD)
                                .noOcclusion(),
                                ParticleTypes.SOUL_FIRE_FLAME
                        )
                )
                .requiresChildren("fence") //REASON: recipes
                //TEXTURES: using oak_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(SOUL_TIKI_TORCHES);

        TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch",
                        () -> BlockInit.OAK_TIKI_TORCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TikiTorch(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(15))
                                .strength(1.5F, 2.5F)
                                .sound(SoundType.WOOD)
                                .mapColor(MapColor.WOOD)
                                .noOcclusion(),
                                ParticleTypes.FLAME
                        )
                )
                .requiresChildren("fence") //REASON: recipes
                //TEXTURES: using oak_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(TIKI_TORCHES);

        ceiling_fan_lights = SimpleEntrySet.builder(WoodType.class, "ceiling_fan_light",
                        () -> BlockInit.OAK_CEILING_FAN_LIGHT, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LightBaseShort(BlockBehaviour.Properties.of()
                                .lightLevel(blockOffLightValue(15))
                                .mapColor(MapColor.WOOD)
                                .strength(1.5F, 2.5F)
                                .sound(SoundType.WOOD)
                                .noOcclusion()
                        )
                )
                .requiresChildren("slab") //REASON: recipes
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
