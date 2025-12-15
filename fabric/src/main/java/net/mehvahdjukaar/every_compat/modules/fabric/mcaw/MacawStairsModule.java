package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import com.mcwstairs.kikoz.objects.BalconyRailing;
import com.mcwstairs.kikoz.objects.StairPlatform;
import com.mcwstairs.kikoz.objects.StairRailing;
import com.mcwstairs.kikoz.objects.stair_types.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawStairsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;

//SUPPORT: v1.0.0+
public class MacawStairsModule extends MacawStairsModuleAbstract {

    public MacawStairsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newTerraceStairs(WoodType woodType) {
        return new TerraceStairs(copyProperties());
    }

    @Override
    public Block newSkylineStairs(WoodType woodType) {
        return new SkylineStairs(copyProperties());
    }

    @Override
    public Block newCompactStairs(WoodType woodType) {
        return new CompactStairs(copyProperties());
    }

    @Override
    public Block newBulkStairs(WoodType woodType) {
        return new BulkStairs(copyProperties());
    }

    @Override
    public Block newLoftStairs(WoodType woodType) {
        return new LoftStairs(copyProperties());
    }

    @Override
    public Block newBalconyRailing(WoodType woodType) {
        return new BalconyRailing(copyProperties());
    }

    @Override
    public Block newStairRailing(WoodType woodType) {
        return new StairRailing(copyProperties());
    }

    @Override
    public Block newStairPlatform(WoodType woodType) {
        return new StairPlatform(copyProperties());
    }

}