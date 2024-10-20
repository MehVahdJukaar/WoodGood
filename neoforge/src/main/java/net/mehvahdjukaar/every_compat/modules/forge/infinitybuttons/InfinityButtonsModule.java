package net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.button.WoodenButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.larsmans.infinitybuttons.events.CreativeTabEvents;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public class InfinityButtonsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> largeButtons;
    public final SimpleEntrySet<WoodType, Block> plankSecretButtons;

    public InfinityButtonsModule(String modId) {
        super(modId, "ib");
        RegistryObject<CreativeModeTab> tab = CreativeTabEvents.INFINITYBUTTONS;

        largeButtons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        InfinityButtonsBlocks.OAK_LARGE_BUTTON, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenButton(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                                .strength(0.5f).noCollission().sound(SoundType.WOOD),
                                true, w.canBurn()))
                .addTag(modRes("wooden_large_buttons"), Registries.BLOCK)
                .addTag(modRes("wooden_large_buttons"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(largeButtons);

        plankSecretButtons = SimpleEntrySet.builder(WoodType.class, "plank_secret_button",
                        InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlankSecretButton(Utils.copyPropertySafe(w.planks)
                                .strength(2.0f, 3.0f).noOcclusion()
                                .sound(SoundType.WOOD), w.planks))
                .addTag(modRes("wooden_secret_buttons"), Registries.BLOCK)
                .addTag(modRes("wooden_secret_buttons"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(plankSecretButtons);
    }
}
