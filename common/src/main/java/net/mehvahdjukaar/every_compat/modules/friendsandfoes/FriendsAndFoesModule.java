package net.mehvahdjukaar.every_compat.modules.friendsandfoes;

import com.google.common.collect.ImmutableSet;
import net.mehvahdjukaar.every_compat.ECRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.VanillaWoods;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;
import java.util.function.Supplier;

public class FriendsAndFoesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> beehives;

    public FriendsAndFoesModule(String modId) {
        super(modId, "faf");

        beehives = SimpleEntrySet.builder(WoodType.class, "beehive",
                        getModBlock("spruce_beehive"),
                        () -> WoodTypeRegistry.getValue(VanillaWoods.SPRUCE),
                        w -> new BeehiveBlock(Utils.copyPropertySafe(Blocks.BEEHIVE)))
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
    public void onModSetup() {
        super.onModSetup();
        RegHelper.addBlocksToPOI(PoiTypes.BEEHIVE, beehives.blocks.values());
    }
}