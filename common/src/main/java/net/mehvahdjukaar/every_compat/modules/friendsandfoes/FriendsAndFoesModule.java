package net.mehvahdjukaar.every_compat.modules.friendsandfoes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

//SUPPORT: v3.0.9+
public class FriendsAndFoesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> beehives;

    public FriendsAndFoesModule(String modId) {
        super(modId, "faf");

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new BeehiveBlock(Utils.copyPropertySafe(Blocks.BEEHIVE))
                )
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front"), EveryCompat.res("block/spruce_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_side"), EveryCompat.res("block/spruce_beehive_side_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.BEEHIVES, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .addTile(() -> BlockEntityType.BEEHIVE)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(beehives);

    }

    @Override
    public void onModInit() {
        super.onModInit();
        RegHelper.addExtraPOIStatesRegistration(event ->
                beehives.blocks.values().forEach(b ->
                        event.addBlockToPoi(PoiTypes.BEEHIVE, b)
                ));

    }

}