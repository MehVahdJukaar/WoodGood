package net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.button.WoodenButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;

//SUPPORT: v4.0.5+
public class InfinityButtonsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> largeButtons;
    public final SimpleEntrySet<WoodType, Block> plankSecretButtons;

    public InfinityButtonsModule(String modId) {
        super(modId, "ib");
        ResourceLocation tab = modRes(modId);

        largeButtons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        () -> InfinityButtonsBlocks.OAK_LARGE_BUTTON, () -> VanillaWoodTypes.OAK,
                        woodType -> new WoodenButton(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON)
                                .strength(0.5f).collidable(false).nonOpaque().sounds(SoundType.WOOD)
                                .pistonBehavior(PushReaction.DESTROY),
                                true, woodType.canBurn())
                )
                .addTag(modRes("wooden_large_buttons"), Registries.BLOCK)
                .addTag(modRes("wooden_large_buttons"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(largeButtons);

        plankSecretButtons = SimpleEntrySet.builder(WoodType.class, "plank_secret_button",
                        () -> InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON, () -> VanillaWoodTypes.OAK,
                        woodType -> new PlankSecretButton(FabricBlockSettings.copyOf(woodType.planks)
                                .strength(2.0f, 3.0f).burnable()
                                .nonOpaque().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY),
                                woodType.planks)
                )
                .addTag(modRes("wooden_secret_buttons"), Registries.BLOCK)
                .addTag(modRes("wooden_secret_buttons"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(plankSecretButtons);
    }
}
