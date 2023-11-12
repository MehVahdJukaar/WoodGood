package net.mehvahdjukaar.every_compat.modules.hearth_and_home;

import com.starfish_studios.hearth_and_home.block.LatticeBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

// SUPPORT v2.0.1
public class HearthAndHomeModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> sanded_wood;
    public final SimpleEntrySet<WoodType, Block> parquet;
    public final SimpleEntrySet<WoodType, Block> trim;
    public final SimpleEntrySet<WoodType, Block> vertical_trim;
    public final SimpleEntrySet<WoodType, Block> lattice;

    public HearthAndHomeModule(String modId) {
        super(modId, "hnhome");

        sanded_wood = SimpleEntrySet.builder(WoodType.class, "sanded_wood",
                        () -> getModBlock("oak_sanded_wood"), () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("sanded_wood"), Registries.BLOCK)
                .addTag(modRes("sanded_wood"), Registries.ITEM)
                .addTexture(modRes("block/sanded_wood/oak"))
                .defaultRecipe()
                .build();

        this.addEntry(sanded_wood);

        parquet = SimpleEntrySet.builder(WoodType.class, "parquet",
                        () -> getModBlock("oak_parquet"), () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("parquet"), Registries.BLOCK)
                .addTag(modRes("parquet"), Registries.ITEM)
                .addTexture(modRes("block/parquet/oak"))
                .defaultRecipe()
                .build();

        this.addEntry(parquet);

        trim = SimpleEntrySet.builder(WoodType.class, "trim",
                        () -> getModBlock("oak_trim"), () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.ITEM)
                .addTexture(modRes("block/trim/oak"))
                .defaultRecipe()
                .addRecipe(modRes("oak_trim_from_oak_vertical_trim"))
                .build();

        this.addEntry(trim);

        vertical_trim = SimpleEntrySet.builder(WoodType.class, "vertical_trim",
                        () -> getModBlock("oak_vertical_trim"), () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.ITEM)
                .addTexture(modRes("block/trim/oak"))
                .defaultRecipe()
                .build();

        this.addEntry(vertical_trim);

        lattice = SimpleEntrySet.builder(WoodType.class, "lattice",
                        () -> getModBlock("oak_lattice"), () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new LatticeBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("lattices"), Registries.BLOCK)
                .addTag(modRes("lattices"), Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/lattice/oak"))
                .addTexture(modRes("block/lattice/oak_bar"))
                .defaultRecipe()
                .build();

        this.addEntry(lattice);
    }
}
