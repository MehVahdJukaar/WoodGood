package net.mehvahdjukaar.every_compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.block.Block;

public class ECPlatformStuff {
    @ExpectPlatform
    public static void registerStripping(Block post, Block stripped) {
        throw new AssertionError();
    }
}
