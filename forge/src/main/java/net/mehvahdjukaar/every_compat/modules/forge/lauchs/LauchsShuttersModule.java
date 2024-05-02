package net.mehvahdjukaar.every_compat.modules.forge.lauchs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.stehschnitzel.shutter.common.blocks.Shutter;
import net.stehschnitzel.shutter.init.BlockInit;
import net.stehschnitzel.shutter.init.CreativTabInit;

import java.util.List;

//SUPPORT: v2.0.2+
public class LauchsShuttersModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Shutter> shutters;

    public LauchsShuttersModule(String modId) {
        super(modId, "ls");

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        BlockInit.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
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
                .setRenderType(() -> RenderType::cutout)
                .setTab(CreativTabInit.SHUTTER_TAB)
                .defaultRecipe()
                .build();

        this.addEntry(shutters);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "ecologics", "quark", "twigs",
                "autumnity", "environments", "goodending"
        );
    }
}
