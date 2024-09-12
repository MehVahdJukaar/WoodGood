package net.mehvahdjukaar.every_compat.modules.fabric.red_bits;

import net.darktree.redbits.RedBits;
import net.darktree.redbits.blocks.LargeButtonBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

//SUPPORT v1.16.1+
public class RedBitsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> large_buttons;

    public RedBitsModule(String modId) {
        super(modId, "rb");

        large_buttons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        () -> RedBits.OAK_LARGE_BUTTON, () -> WoodTypeRegistry.OAK_TYPE,
                        wood -> new LargeButtonBlock(true, BlockBehaviour.Properties.of(Material.DECORATION)
                                    .noCollission().strength(0.5F).sound(SoundType.WOOD)
                        )
                )
                .requiresChildren("button") // Recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("large_buttons"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("large_wooden_buttons"), Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .build();
        this.addEntry(large_buttons);


    }
}