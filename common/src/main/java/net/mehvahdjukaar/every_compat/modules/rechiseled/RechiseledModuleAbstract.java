package net.mehvahdjukaar.every_compat.modules.rechiseled;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

///SUPPORT: v1.2.5+
public abstract class RechiseledModuleAbstract extends EveryCompatModule {

    public final ResourceLocation tab = modRes(getModId());

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
    }

    public Block getParentBlock(WoodType woodType, String path) {
        return woodType.getBlockOfThis(getModId() + ":" + path);
    }

    public BlockState getParentBlockState(WoodType woodType, String path) {
        return Objects.requireNonNull(woodType.getBlockOfThis(getModId() + ":" + path)).defaultBlockState();
    }

    public String createStandardId(WoodType woodType, String prefix, String suffix) {
        return woodType.createFullIdWith(EveryCompat.MOD_ID, "", shortenedId(), prefix, suffix);
    }
}