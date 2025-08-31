package net.mehvahdjukaar.every_compat.modules.neoforge.oreberries_replanted;

import com.mrbysco.oreberriesreplanted.block.OreBerryBushBlock;
import com.mrbysco.oreberriesreplanted.block.VatBlock;
import com.mrbysco.oreberriesreplanted.registry.OreBerryRegistry;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

//SUPPORT: v0.5.2+
public class OreberriesReplantedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, VatBlock> vats;

    public OreberriesReplantedModule(String modId) {
        super(modId, "or");
        ResourceLocation tab = modRes("tab");

        vats = SimpleEntrySet.builder(WoodType.class, "vat",
                        OreBerryRegistry.OAK_VAT, () -> VanillaWoodTypes.OAK,
                        w -> new VatBlock(BlockBehaviour.Properties.of()
                                .mapColor(w.planks.defaultMapColor())
                                .sound(SoundType.SWEET_BERRY_BUSH)
                                .noOcclusion()
                                .isSuffocating(OreBerryBushBlock::isntSolid)
                                .isViewBlocking(OreBerryBushBlock::isntSolid))
                )
                //TEXTURES: planks
                .addTile(OreBerryRegistry.VAT_BLOCK_ENTITY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(vats);
    }
}