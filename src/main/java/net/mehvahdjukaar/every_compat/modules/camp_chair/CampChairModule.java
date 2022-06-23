package net.mehvahdjukaar.every_compat.modules.camp_chair;

import dlovin.smalls.campchair.CampChair;
import dlovin.smalls.campchair.core.blocks.CampChairBlock;
import dlovin.smalls.campchair.core.init.BlockInit;
import dlovin.smalls.campchair.core.init.ItemInit;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.item.MissingWildsItemGroup;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class CampChairModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, CampChairBlock> CAMP_CHAIRS ;

    public CampChairModule(String modId) {
        super(modId, "cc");

        CAMP_CHAIRS = SimpleEntrySet.builder(WoodType.class, "camp_chair",
                        BlockInit.OAK_CAMP_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w ->    new CampChairBlock(BlockBehaviour.Properties.of(Material.CAKE, w.material.getColor())
                                .strength(2.0F, 3.0F).sound(SoundType.WOOD)))
                .addTag(modRes("camp_chairs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("camp_chairs"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .defaultRecipe()
                .build();

        this.addEntry(CAMP_CHAIRS);
    }


}
