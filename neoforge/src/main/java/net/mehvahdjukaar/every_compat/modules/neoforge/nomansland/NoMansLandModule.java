package net.mehvahdjukaar.every_compat.modules.neoforge.nomansland;

import com.farcr.nomansland.common.block.TrimmedPlankBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class NoMansLandModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> trimmedPlanks,
            bookshelf;

    public NoMansLandModule(String modId) {
        super(modId, "nml", EveryCompat.MOD_ID);

        ResourceLocation tab = modRes(modId);

        trimmedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "trimmed",
                        getModBlock("trimmed_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new TrimmedPlankBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/vanilla_woods/trimmed_oak_planks"))
                .addTexture(modRes("block/vanilla_woods/trimmed_oak_planks_lower"))
                .addTexture(modRes("block/vanilla_woods/trimmed_oak_planks_middle"))
                .addTexture(modRes("block/vanilla_woods/trimmed_oak_planks_upper"))
                .addTexture(modRes("block/vanilla_woods/trimmed_oak_planks_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("trimmed_planks"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("wood/vanilla_woods/trimmed_oak_planks"))
                .setTabKey(tab)
                .build();
        this.addEntry(trimmedPlanks);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("acacia_bookshelf"), () -> VanillaWoodTypes.ACACIA,
                        w -> new Block(Utils.copyPropertySafe(w.planks).strength(1.5F)))
                .addTextureCM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"), "block/vanilla_woods/acacia_bookshelf")
                .addTextureCM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"), "block/vanilla_woods/acacia_bookshelf_alt")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelves"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("wood/vanilla_woods/acacia_bookshelf"))
                .copyParentDrop()
                .setTabKey(tab)
                .build();
        this.addEntry(bookshelf);

    }

}
