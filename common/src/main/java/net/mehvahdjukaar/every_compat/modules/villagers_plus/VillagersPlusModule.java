package net.mehvahdjukaar.every_compat.modules.villagers_plus;

import com.lion.villagersplus.blocks.HorticulturistTableBlock;
import com.lion.villagersplus.init.VPBlockEntities;
import com.lion.villagersplus.init.VPBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

//SUPPORT: v3.1+
public class VillagersPlusModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tub;

    public VillagersPlusModule(String modId) {
        super(modId, "vp");

        tub = SimpleEntrySet.builder(WoodType.class, "horticulturist_table",
                        VPBlocks.OAK_HORTICULTURIST_TABLE_BLOCK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HorticulturistTableBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().ignitedByLava())
                )
                .addTextureM(modRes("block/oak_horticulturist_workstation"),
                        EveryCompat.res("block/vp/oak_horticulturist_workstation_m"))
                .addTile(VPBlockEntities.HORTICULTURIST_TABLE_BLOCK_ENTITY)
                .build();
        this.addEntry(tub);

    }
}
