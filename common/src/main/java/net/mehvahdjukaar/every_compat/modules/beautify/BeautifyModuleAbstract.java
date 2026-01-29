package net.mehvahdjukaar.every_compat.modules.beautify;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

// See Inheritors' supported version
public abstract class BeautifyModuleAbstract extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> tellis;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> picture_frames;

    public BeautifyModuleAbstract(String modId) {
        super(modId, "bd");

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        getModBlock("oak_trellis"), () -> VanillaWoodTypes.OAK,
                        this::newTrellis
                )
                //TEXTURES: logs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTabKey(getTabKey())
                .defaultRecipe()
                //REASON: take a look at their //TEXTURES, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        getModBlock("oak_blinds"), () -> VanillaWoodTypes.OAK,
                        this::newBlinds
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(getTabKey())
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        getModBlock("oak_picture_frame"), () -> VanillaWoodTypes.OAK,
                        this::newPictureFrame
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/oak_frame_texture"))
                .setTabKey(getTabKey())
                .defaultRecipe()
                .build();
        this.addEntry(picture_frames);

    }

    public abstract ResourceLocation getTabKey();

    public abstract Block newTrellis(WoodType woodType);
    public abstract Block newBlinds(WoodType woodType);
    public abstract Block newPictureFrame(WoodType woodType);
}