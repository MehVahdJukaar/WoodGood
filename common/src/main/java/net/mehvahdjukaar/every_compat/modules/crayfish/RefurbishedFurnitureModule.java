package net.mehvahdjukaar.every_compat.modules.crayfish;

import com.mrcrayfish.furniture.refurbished.block.CeilingFanBlock;
import com.mrcrayfish.furniture.refurbished.block.ChairBlock;
import com.mrcrayfish.furniture.refurbished.block.MetalType;
import com.mrcrayfish.furniture.refurbished.block.TableBlock;
import com.mrcrayfish.furniture.refurbished.core.ModBlockEntities;
import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import com.mrcrayfish.furniture.refurbished.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RefurbishedFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> darkFans;
    public final SimpleEntrySet<WoodType, Block> lightFans;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");

        // somebody else pls finish this <3
        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of().strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_chair"))
                .build();

        this.addEntry(chairs);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of()
                                .strength(2.0F))))
                .defaultRecipe()
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_particle"))
                .build();

        this.addEntry(tables);

        darkFans = SimpleEntrySet.builder(WoodType.class, "dark_ceiling_fan",
                        () -> getModBlock("oak_dark_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .defaultRecipe()
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_dark_ceiling_fan"))
                .build();

        this.addEntry(darkFans);

        lightFans = SimpleEntrySet.builder(WoodType.class, "light_ceiling_fan",
                        () -> getModBlock("oak_light_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .defaultRecipe()
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_light_ceiling_fan"))
                .build();

        this.addEntry(lightFans);
    }

}
