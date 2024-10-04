package net.mehvahdjukaar.every_compat.modules.forge.beautify_decorate;

import com.github.Pandarix.beautify.common.block.OakBlinds;
import com.github.Pandarix.beautify.common.block.OakPictureFrame;
import com.github.Pandarix.beautify.common.block.OakTrellis;
import com.github.Pandarix.beautify.core.init.BlockInit;
import com.github.Pandarix.beautify.core.init.ItemGroupInit;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: v2.0.2+
public class BeautifyDecorateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, OakTrellis> tellis;
    public final SimpleEntrySet<WoodType, OakBlinds> blinds;
    public final SimpleEntrySet<WoodType, OakPictureFrame> picture_frames;

    public BeautifyDecorateModule(String modId) {
        super(modId, "bd");
        var tab = ItemGroupInit.BEAUTIFY_TAB.getId();

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        BlockInit.OAK_TRELLIS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakTrellis(Utils.copyPropertySafe(w.planks)
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO).noOcclusion()
                        )
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
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
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        BlockInit.OAK_PICTURE_FRAME, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new OakPictureFrame(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion()
                        )
                )
                .requiresChildren("slab") // Recipes
                .addTexture(modRes("block/oak_frame_texture"))
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(picture_frames);


    }
}