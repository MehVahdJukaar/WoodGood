package net.mehvahdjukaar.every_compat.modules.villagers_plus;

import com.lion.villagersplus.blocks.HorticulturistTableBlock;
import com.lion.villagersplus.init.VPBlockEntities;
import com.lion.villagersplus.init.VPBlocks;
import com.lion.villagersplus.init.VPItemGroups;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

//SUPPORT: v3.1+
public class VillagersPlusModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> tub;

    public VillagersPlusModule(String modId) {
        super(modId, "vp");

        tub = SimpleEntrySet.builder(WoodType.class, "horticulturist_table",
                        VPBlocks.OAK_HORTICULTURIST_TABLE_BLOCK, () -> VanillaWoodTypes.OAK,
                        w -> new HorticulturistTableBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().ignitedByLava())
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                .addTextureM(modRes("block/oak_horticulturist_workstation"),
                        EveryCompat.res("block/vp/oak_horticulturist_workstation_m"),
                        PaletteStrategies.STRIPPED_LOG_SIDE_STANDARD)
                .addTile(VPBlockEntities.HORTICULTURIST_TABLE_BLOCK_ENTITY)
                .setTabKey(VPItemGroups.ITEM_GROUP)
                .defaultRecipe()
                .build();
        this.addEntry(tub);

    }
}
