package net.mehvahdjukaar.wood_good.modules.deco_block;

import lilypuree.decorative_blocks.blocks.types.IWoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.world.level.block.Block;

public class DBWoodType implements IWoodType {

    private final WoodType woodType;

    public DBWoodType(WoodType type){
        this.woodType = type;
    }

    public WoodType type() {
        return woodType;
    }

    @Override
    public String namespace() {
        return woodType.getNamespace();
    }

    @Override
    public Block getLog() {
        return woodType.log;
    }

    @Override
    public Block getStrippedLog() {
        return woodType.strippedLog;
    }

    @Override
    public Block getSlab() {
        return woodType.slab;
    }

    @Override
    public Block getFence() {
        return woodType.fence;
    }

    @Override
    public Block getPlanks() {
        return woodType.planks;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean isFlammable() {
        return woodType.canBurn();
    }
}
