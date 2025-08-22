package net.mehvahdjukaar.every_compat.modules.forge.smidgeon_o_bliss;

import com.syndicatemc.sob.block.CounterBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: v1.1.1+
public class SmidgeonOBlissModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> counter;

    public SmidgeonOBlissModule(String modId) {
        super(modId, "sob");
        ResourceLocation tab = modRes(modId);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> VanillaWoodTypes.OAK,
                        woodType -> new CounterBlock(Utils.copyPropertySafe(woodType.planks))
                )
                //TEXTURES: oak_cabinet_top from Farmer's Delight
                .addModelTransform(m -> m.addModifier((s, resourceLocation, woodType) ->
                        s.replace("farmersdelight:block/oak_cabinet_top",
                                woodType.createFullIdWith(EveryCompat.MOD_ID, "block", "fd", "", "cabinet_top")
                        )
                ))
                .addTexture(modRes("block/oak_counter"))
                //REASON: Using the texture via FarmersDelightModule BUT it's meant to generate the texture in case FarmersDelightModule's CABINET is not generated
                .addTexture(TextureInfo.of(new ResourceLocation("farmersdelight:block/oak_cabinet_top")).replacePath(shortenedId(), "fd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("crafting/oak_counter"))
                .build();
        this.addEntry(counter);

    }
}