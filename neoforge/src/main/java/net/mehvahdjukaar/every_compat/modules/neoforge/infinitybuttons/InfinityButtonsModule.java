package net.mehvahdjukaar.every_compat.modules.neoforge.infinitybuttons;

import net.larsmans.infinitybuttons.block.custom.button.WoodenButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: //!! NOT AVAILABLE
public class InfinityButtonsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> largeButtons;
    public final SimpleEntrySet<WoodType, Block> plankSecretButtons;

    public InfinityButtonsModule(String modId) {
        super(modId, "ib");
        ResourceLocation tab = modRes(modId);

        largeButtons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        getModBlock("oak_large_button"), () -> VanillaWoodTypes.OAK,
                        w -> new WoodenButton(Utils.copyPropertySafe(Blocks.OAK_BUTTON)
                                .strength(0.5f).noCollission().sound(SoundType.WOOD),
                                true, w.canBurn())
                )
                //TEXTURES: planks
                .addTag(modRes("wooden_large_buttons"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(largeButtons);

        plankSecretButtons = SimpleEntrySet.builder(WoodType.class, "plank_secret_button",
                        getModBlock("oak_plank_secret_button"), () -> VanillaWoodTypes.OAK,
                        w -> new PlankSecretButton(Utils.copyPropertySafe(w.planks)
                                .strength(2.0f, 3.0f).noOcclusion()
                                .sound(SoundType.WOOD), w.planks)
                )
                //TEXTURES: planks
                .addTag(modRes("wooden_secret_buttons"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(plankSecretButtons);
    }
}
