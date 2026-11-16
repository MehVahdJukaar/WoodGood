package net.mehvahdjukaar.every_compat.mixins;

import com.supermartijn642.core.block.BaseBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * BaseBlock has a method, removeDescriptionAndDropsFromProperties(properties) where it set properties's ID.
 * It won't use EveryComapt's Id, Below remove the .setId() and use EveryCompat's.
 */
@Mixin(value = BaseBlock.class, remap = false)
public abstract class RechiseledBaseBlockMixin {

    @Redirect(
            method = "removeDescriptionAndDropsFromProperties",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;setId(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"
            )
    )
    private static BlockBehaviour.Properties everyCompat$skipDummySetId(
            BlockBehaviour.Properties properties,
            ResourceKey<Block> id
    ) {
        ResourceKey<Block> resourceKey = ((BlockPropertiesAccessorMixin) properties).everyCompat$getId();
        // Keep EveryCompat's existing ID if it is already set on properties
        if (resourceKey != null && resourceKey.identifier().getNamespace().equals("everycomp")) {
            return properties;
        }
        // Otherwise, let Rechiseled apply its dummy ID as normal
        return properties.setId(id);
    }
}

