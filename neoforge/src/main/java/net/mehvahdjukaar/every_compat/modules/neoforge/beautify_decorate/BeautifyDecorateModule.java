package net.mehvahdjukaar.every_compat.modules.neoforge.beautify_decorate;

import com.github.Pandarix.beautify.Beautify;
import com.github.Pandarix.beautify.common.block.Blinds;
import com.github.Pandarix.beautify.common.block.PictureFrame;
import com.github.Pandarix.beautify.common.block.Trellis;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

//SUPPORT: v2.0.2+
public class BeautifyDecorateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tellis;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> picture_frames;

    public BeautifyDecorateModule(String modId) {
        super(modId, "bd");
        ResourceLocation tab = modRes(Beautify.MODID);

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        getModBlock("oak_trellis"), () -> VanillaWoodTypes.OAK,
                        w -> new Trellis(Utils.copyPropertySafe(w.planks)
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO).noOcclusion()
                        )
                )
                //TEXTURES: logs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        getModBlock("oak_blinds"), () -> VanillaWoodTypes.OAK,
                        w -> new Blinds(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.4F, 0.4F)
                                .sound(SoundType.WOOD)
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        getModBlock("oak_picture_frame"), () -> VanillaWoodTypes.OAK,
                        w -> new PictureFrame(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion()
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/oak_frame_texture"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(picture_frames);


    }
}