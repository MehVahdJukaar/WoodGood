package net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate;

import com.github.suel_ki.beautify.common.block.Blinds;
import com.github.suel_ki.beautify.common.block.PictureFrame;
import com.github.suel_ki.beautify.common.block.Trellis;
import com.github.suel_ki.beautify.core.init.BlockInit;
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

//SUPPORT: v1.1.1+
public class BeautifyRefabricatedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Trellis> tellis;
    public final SimpleEntrySet<WoodType, Blinds> blinds;
    public final SimpleEntrySet<WoodType, PictureFrame> picture_frames;

    public BeautifyRefabricatedModule(String modId) {
        super(modId, "bd");

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        () -> BlockInit.OAK_TRELLIS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Trellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO).noOcclusion()
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                // sakura_log's texture is not a standard 16x16, take a look, you'll see why
                .addCondition(woodType -> !woodType.getId().toString().equals("terrestria:sakura")) // Excluded
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        () -> BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Blinds(Utils.copyPropertySafe(w.planks)
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
                        () -> BlockInit.OAK_PICTURE_FRAME, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PictureFrame(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion()
                        )
                )
                .requiresChildren("slab") // Recipes
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> s.replace(
                        "\"beautify:blocks/oak_frame_texture\"",
                        "\""+ EveryCompat.MOD_ID + ":blocks/" + shortenedId() + "/" + w.getAppendableId() + "_frame_texture\""
                )))
                .addTexture(modRes("blocks/oak_frame_texture"))
                .defaultRecipe()
                .build();
        this.addEntry(picture_frames);


    }
}