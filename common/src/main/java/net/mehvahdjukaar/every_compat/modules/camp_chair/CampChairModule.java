package net.mehvahdjukaar.every_compat.modules.camp_chair;
// DISABLED: Camp Chair mod has no 1.21.1 release on CurseForge.
/*
import dlovin.smalls.campchair.core.blocks.CampChairBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CampChairModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, CampChairBlock> campChairs;

    public CampChairModule(String modId) {
        super(modId, "cc");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FOOD_AND_DRINKS;

        campChairs = SimpleEntrySet.builder(WoodType.class, "camp_chair",
                        getModBlock("oak_camp_chair", CampChairBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new CampChairBlock(BlockBehaviour.Properties.of()
                                .mapColor(w.getColor())
                                .sound(w.getSound())
                                .strength(2.0F, 3.0F).sound(SoundType.WOOD))
                )
                .addTag(modRes("camp_chairs"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))                .defaultRecipe()
                .build();
        this.addEntry(campChairs);
    }

}
*/
