package net.mehvahdjukaar.every_compat.modules.forge.gensokyo_delight;

import dev.xkmc.youkaishomecoming.content.block.furniture.WoodChairBlock;
import dev.xkmc.youkaishomecoming.content.block.furniture.WoodTableBlock;
import dev.xkmc.youkaishomecoming.content.block.variants.MultiFenceBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;

//SUPPORT: v2.4.15+
//NOTE: aka Youkai's Homecoming YoukaisHomecoming
public class YoukaisHomecomingModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> handrail;
    public final SimpleEntrySet<WoodType, Block> dining_table;
    public final SimpleEntrySet<WoodType, Block> dining_chair;

    public YoukaisHomecomingModule(String modId) {
        super(modId, "ykh");
        ResourceLocation tab = modRes("youkais_homecoming");

        handrail = SimpleEntrySet.builder(WoodType.class, "handrail",
                        getModBlock("oak_handrail"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MultiFenceBlock(Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis("fence"))).noOcclusion())
                )
                .requiresChildren("fence") //REASON: properties
                .addTexture(modRes("block/wooden/oak_handrail"))
                .addTexture(modRes("item/handrail/oak_handrail"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("oak_handrail_from_oak_planks_stonecutting"))
                .build();
        this.addEntry(handrail);

        dining_table = SimpleEntrySet.builder(WoodType.class, "dining_table",
                        getModBlock("oak_dining_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_wood", "slab") //REASON: recipes
                .addTexture(modRes("block/wooden/oak_dining_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(dining_table);

        dining_chair = SimpleEntrySet.builder(WoodType.class, "dining_chair",
                        getModBlock("oak_dining_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodChairBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTextureM(modRes("block/wooden/oak_dining_chair"), EveryCompat.res("block/ykh/oak_dining_chair_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(dining_chair);

    }

    public BlockBehaviour.Properties copyFenceSafe(WoodType woodType) {
        Block fence = woodType.getBlockOfThis("fence");
        return (Objects.nonNull(fence)) ? Utils.copyPropertySafe(fence) : Utils.copyPropertySafe(Blocks.OAK_FENCE);
    }
}