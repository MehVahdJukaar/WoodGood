package net.mehvahdjukaar.every_compat.modules.fabric.red_bits;

import net.darktree.redbits.RedBits;
import net.darktree.redbits.blocks.LargeButtonBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.BUTTON;

//SUPPORT v1.16.1+
public class RedBitsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> large_buttons;

    public RedBitsModule(String modId) {
        super(modId, "rb");

        large_buttons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        () -> RedBits.OAK_LARGE_BUTTON, () -> VanillaWoodTypes.OAK,
                        wood -> new LargeButtonBlock(true, new BlockSetType(wood.getTypeName()), BlockBehaviour.Properties.of()
                                .noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY))
                )
                .requiresChildren(BUTTON) // Recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("large_buttons"), Registries.BLOCK)
                .addTag(modRes("large_wooden_buttons"), Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(large_buttons);
    }
}