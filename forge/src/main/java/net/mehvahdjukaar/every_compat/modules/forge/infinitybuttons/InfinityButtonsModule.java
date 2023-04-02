package net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.largebutton.WoodenLargeButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class InfinityButtonsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> largeButtons;
    public final SimpleEntrySet<WoodType, Block> plankSecretButtons;

    public InfinityButtonsModule(String modId) {
        super(modId, "ib");

        largeButtons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        InfinityButtonsBlocks.OAK_LARGE_BUTTON, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)))
                .addTag(modRes("wooden_large_buttons"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_large_buttons"), Registry.ITEM_REGISTRY)
                .setTab(() -> InfinityButtonsItemGroup.INFINITYBUTTONS)
                .defaultRecipe()
                .build();

        this.addEntry(largeButtons);

        plankSecretButtons = SimpleEntrySet.builder(WoodType.class, "plank_secret_button",
                        InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlankSecretButton(Utils.copyPropertySafe(w.planks).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)))
                .addTag(modRes("wooden_secret_buttons"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_secret_buttons"), Registry.ITEM_REGISTRY)
                .setTab(() -> InfinityButtonsItemGroup.INFINITYBUTTONS)
                .defaultRecipe()
                .build();

        this.addEntry(plankSecretButtons);
    }
}
