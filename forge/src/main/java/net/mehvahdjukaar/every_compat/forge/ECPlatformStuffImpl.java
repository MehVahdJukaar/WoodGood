package net.mehvahdjukaar.every_compat.forge;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import org.violetmoon.quark.base.handler.ToolInteractionHandler;

public class ECPlatformStuffImpl {

    public static void registerStripping(Block post, Block stripped) {
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP,post, stripped);
    }
}
