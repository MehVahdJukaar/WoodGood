package net.mehvahdjukaar.every_compat.modules.forge.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.List;

public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");
        ResourceLocation tab = modRes(modId);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        () -> getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("cabinets"), Registries.BLOCK)
                .addTag(modRes("cabinets"), Registries.ITEM)
                .addTag(modRes("cabinets/wooden"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .addTile(ModBlockEntityTypes.CABINET)
                .setTabKey(tab)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(EveryCompat.res("block/oak_cabinet_front"))
                .addTexture(EveryCompat.res("block/oak_cabinet_side"))
                .addTexture(EveryCompat.res("block/oak_cabinet_top"))
                .addTextureM(EveryCompat.res("block/oak_cabinet_front_open"), EveryCompat.res("block/oak_cabinet_front_open_m"))
                .build();
        this.addEntry(cabinets);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        // abnormals_delight supports caverns_and_chasms
        if (PlatHelper.isModLoaded("abnormals_delight")) return List.of("caverns_and_chasms");

        return List.of();
    }
}
