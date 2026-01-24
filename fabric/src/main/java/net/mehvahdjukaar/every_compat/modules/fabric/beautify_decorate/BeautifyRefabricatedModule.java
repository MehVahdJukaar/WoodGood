package net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate;

import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

//SUPPORT: v1.2.0+
public class BeautifyRefabricatedModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Trellis> tellis;
    public final SimpleEntrySet<WoodType, Blinds> blinds;
    public final SimpleEntrySet<WoodType, PictureFrame> picture_frames;

    public BeautifyRefabricatedModule(String modId) {
        super(modId, "bd");
        ResourceLocation tab = modRes("group");

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        () -> BlockInit.OAK_TRELLIS, () -> VanillaWoodTypes.OAK,
                        w -> new Trellis(BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO)
                                .noOcclusion()
                        )
                )
                //TEXTURES: loglogs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                //REASON: take a look at their //TEXTURES, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        () -> BlockInit.OAK_BLINDS, () -> VanillaWoodTypes.OAK,
                        w -> new Blinds(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.4F, 0.4F)
                                .sound(SoundType.WOOD)
                        )
                )
                //TEXTURES: planks
                .requiresChildren(SLAB) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        () -> BlockInit.OAK_PICTURE_FRAME, () -> VanillaWoodTypes.OAK,
                        w -> new PictureFrame(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion()
                                .pushReaction(PushReaction.DESTROY)
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/oak_frame_texture"))
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(picture_frames);


    }
}