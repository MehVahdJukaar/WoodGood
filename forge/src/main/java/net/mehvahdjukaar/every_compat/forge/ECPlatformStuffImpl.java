package net.mehvahdjukaar.every_compat.forge;

import com.mrcrayfish.furniture.refurbished.core.ModBlocks;
import net.mehvahdjukaar.moonlight.core.mixins.forge.FireBlockMixin;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import org.violetmoon.zeta.util.handler.ToolInteractionHandler;

public class ECPlatformStuffImpl {

    public static void registerStripping(Block post, Block stripped) {
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP,post, stripped);
    }
}
