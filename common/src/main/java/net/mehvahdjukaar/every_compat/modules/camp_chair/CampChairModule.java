package net.mehvahdjukaar.every_compat.modules.camp_chair;

import dlovin.smalls.campchair.core.blocks.CampChairBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CampChairModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, CampChairBlock> campChairs;

    public CampChairModule(String modId) {
        super(modId, "cc");

        campChairs = SimpleEntrySet.builder(WoodType.class, "camp_chair",
                        getModBlock("oak_camp_chair", CampChairBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CampChairBlock(BlockBehaviour.Properties.of()
                                .mapColor(w.getColor())
                                .sound(w.getSound())
                                .strength(2.0F, 3.0F).sound(SoundType.WOOD)))
                .addTag(modRes("camp_chairs"), Registries.BLOCK)
                .addTag(modRes("camp_chairs"), Registries.ITEM)
                .setTabKey(() -> CreativeModeTabs.FOOD_AND_DRINKS)
                .defaultRecipe()
                .build();

        this.addEntry(campChairs);
    }


}
