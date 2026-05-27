package net.mehvahdjukaar.every_compat.modules.lauchs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.List;

//SUPPORT: v2.0.2+
public abstract class LauchsShuttersModuleAbstract extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> shutters;

    public LauchsShuttersModuleAbstract(String modId) {
        super(modId, "ls");

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        getModBlock("oak_shutter"), () -> VanillaWoodTypes.OAK,
                        this::newShutter
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTextureM(modRes("block/oak_shutter_lower"), EveryCompat.res("block/ls/oak_shutter_lower_m"))
                .addTextureM(modRes("block/oak_shutter_normal"), EveryCompat.res("block/ls/oak_shutter_normal_m"))
                .addTextureM(modRes("block/oak_shutter_upper"), EveryCompat.res("block/ls/oak_shutter_upper_m"))
                .addTexture(modRes("block/oak_shutter_lower_big"))
                .addTexture(modRes("block/oak_shutter_normal_big"))
                .addTexture(modRes("block/oak_shutter_upper_big"))
                .addTexture(modRes("block/oak_shutter_middle"))
                .addTexture(modRes("block/oak_shutter_middle_big"))
                .addTexture(modRes("item/oak_shutter"))
                .setTab(getModTab(modId))
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(shutters);
    }

    public abstract Block newShutter(WoodType woodType);

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "ecologics", "quark", "twigs",
                "autumnity", "environments", "goodending"
        );
    }
}
