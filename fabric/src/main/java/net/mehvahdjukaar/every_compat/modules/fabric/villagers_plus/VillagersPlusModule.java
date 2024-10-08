package net.mehvahdjukaar.every_compat.modules.fabric.villagers_plus;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.blocks.HorticulturistTableBlock;
import com.finallion.villagersplus.init.ModBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

//SUPPORT: v1.9.2-FINAL
public class VillagersPlusModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tubs;

    public VillagersPlusModule(String modId) {
        super(modId, "vp");

        tubs = SimpleEntrySet.builder(WoodType.class, "horticulturist_table",
                        () -> getModBlock("oak_horticulturist_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HorticulturistTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F).noOcclusion())
                )
                .addTile(() -> ModBlocks.HORTICULTURIST_TABLE_BLOCK_ENTITY)
                .requiresChildren("stripped_log") //REASON: Recipes & Textures
                .createPaletteFromChild(p -> {}, "stripped_log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
                .addTextureM(modRes("block/oak_horticulturist_workstation"),
                        EveryCompat.res("block/vp/oak_horticulturist_workstation_m"))
                .setTab(() -> VillagersPlus.ITEM_GROUP)
                .defaultRecipe()
                .build();
        this.addEntry(tubs);

    }
}
