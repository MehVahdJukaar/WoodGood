package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.ToIntFunction;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.FENCE;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

///SUPPORT: v1.1.0+
public abstract class MacawLightsModuleAbstract extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SOUL_TIKI_TORCH, TIKI_TORCH, CEILING_FAN_LIGHTS;

    public MacawLightsModuleAbstract(String modId) {
        super(modId, "mcl");
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric())
                ? modRes("lightsgroup")
                : modRes(modId);

        SOUL_TIKI_TORCH = SimpleEntrySet.builder(WoodType.class, "tiki_torch", "soul",
                        getModBlock("soul_oak_tiki_torch"), () -> VanillaWoodTypes.OAK,
                        w -> newTikiTorch(w, ParticleTypes.SOUL_FIRE_FLAME)
                )
                .requiresChildren(FENCE) //REASON: recipes
                //TEXTURES: using oak_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(SOUL_TIKI_TORCH);

        TIKI_TORCH = SimpleEntrySet.builder(WoodType.class, "tiki_torch",
                        getModBlock("oak_tiki_torch"), () -> VanillaWoodTypes.OAK,
                        w -> newTikiTorch(w, ParticleTypes.FLAME)
                )
                .requiresChildren(FENCE) //REASON: recipes
                //TEXTURES: using oak_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(TIKI_TORCH);

        CEILING_FAN_LIGHTS = SimpleEntrySet.builder(WoodType.class, "ceiling_fan_light",
                        getModBlock("oak_ceiling_fan_light"), () -> VanillaWoodTypes.OAK,
                        this::newLightBaseShort
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTextureM(modRes("block/oak_ceiling_fan"), EveryCompat.res("block/mcw/lights/ceiling_fan_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(CEILING_FAN_LIGHTS);
    }

    // METHODS
    protected BlockBehaviour.Properties copyStandardProperties(WoodType woodType) {
        return BlockBehaviour.Properties.of()
                .lightLevel(blockOffLightValue(15))
                .mapColor(woodType.getColor())
                .strength(1.5F, 2.5F)
                .sound(woodType.getSound())
                .noOcclusion();
    }

    private static ToIntFunction<BlockState> blockOffLightValue(int lightLevel) {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

    public abstract Block newTikiTorch(WoodType woodType, ParticleOptions particleOptions);
    public abstract Block newLightBaseShort(WoodType woodType);
}
