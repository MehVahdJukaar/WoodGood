package net.mehvahdjukaar.every_compat.modules.forge.beautify_decorate;

import com.github.Pandarix.beautify.common.block.OakBlinds;
import com.github.Pandarix.beautify.common.block.OakPictureFrame;
import com.github.Pandarix.beautify.common.block.OakTrellis;
import com.github.Pandarix.beautify.core.init.BlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

//SUPPORT: v1.4.3+
public class BeautifyDecorateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, OakTrellis> tellis;
    public final SimpleEntrySet<WoodType, OakBlinds> blinds;
    public final SimpleEntrySet<WoodType, OakPictureFrame> picture_frames;

    public BeautifyDecorateModule(String modId) {
        super(modId, "bd");

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        BlockInit.OAK_TRELLIS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO).noOcclusion()
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakBlinds(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.4F, 0.4F)
                                .sound(SoundType.WOOD)
                        )
                )
                .requiresChildren("slab") // Recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        BlockInit.OAK_PICTURE_FRAME, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion()
                        )
                )
                .requiresChildren("slab") // Recipes
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> s.replace(
                        "\"beautify:blocks/oak_frame_texture\"",
                         "\""+EveryCompat.MOD_ID + ":blocks/" + shortenedId() + "/" + w.getAppendableId() + "_frame_texture\""
                )))
                .addTexture(modRes("blocks/oak_frame_texture"))
                .defaultRecipe()
                .build();
        this.addEntry(picture_frames);

    }

}