package net.mehvahdjukaar.every_compat.modules.fabric.lauchs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.stehschnitzel.shutter.block.Shutter;
import net.stehschnitzel.shutter.init.ShutterItemGroup;

import java.util.List;

//SUPPORT: v2.0.2+
public class LauchsShuttersModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Shutter> shutters;

    public LauchsShuttersModule(String modId) {
        super(modId, "ls");
        var tab = "shutter_tab";

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        getModBlock("oak_shutter", Shutter.class), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setTab(() -> ShutterItemGroup.SHUTTER_GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(shutters);
    }

//    @Override
//    public List<String> getAlreadySupportedMods() {
//        return List.of(
//                "ecologics", "quark", "twigs",
//                "autumnity", "environments", "goodending"
//        );
//    }
}
