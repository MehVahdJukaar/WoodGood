package net.mehvahdjukaar.every_compat.modules.forge.oreberries_replanted;

import com.mrbysco.oreberriesreplanted.block.OreBerryBushBlock;
import com.mrbysco.oreberriesreplanted.block.VatBlock;
import com.mrbysco.oreberriesreplanted.blockentity.VatBlockEntity;
import com.mrbysco.oreberriesreplanted.registry.OreBerryRegistry;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

//SUPPORT: v0.5.2+
public class OreberriesReplantedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> vats;

    public OreberriesReplantedModule(String modId) {
        super(modId, "or");

        vats = SimpleEntrySet.builder(WoodType.class, "vat",
                        OreBerryRegistry.OAK_VAT, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new VatBlock(BlockBehaviour.Properties.of()
                                .mapColor(w.planks.defaultMapColor())
                                .sound(SoundType.SWEET_BERRY_BUSH)
                                .noOcclusion()
                                .isSuffocating(OreBerryBushBlock::isntSolid)
                                .isViewBlocking(OreBerryBushBlock::isntSolid))
                )
                .addTile(OreBerryRegistry.VAT_BLOCK_ENTITY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(vats);


    }
}