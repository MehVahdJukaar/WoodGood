package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwstairs.kikoz.objects.BalconyRailing;
import com.mcwstairs.kikoz.objects.StairPlatform;
import com.mcwstairs.kikoz.objects.StairRailing;
import com.mcwstairs.kikoz.objects.stair_types.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawStairsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

//SUPPORT: v1.0.0+
public class MacawStairsModule extends MacawStairsModuleAbstract {

    public MacawStairsModule(String modId) {
        super(modId);

    }

    @Override
    public Block newTerraceStairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new TerraceStairs(p);
    }

    @Override
    public Block newSkylineStairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new SkylineStairs(p);
    }

    @Override
    public Block newCompactStairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new CompactStairs(p);
    }

    @Override
    public Block newBulkStairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new BulkStairs(p);
    }

    @Override
    public Block newLoftStairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new LoftStairs(p);
    }

    @Override
    public Block newBalconyRailing(WoodType woodType, BlockBehaviour.Properties p) {
        return new BalconyRailing(p);
    }

    @Override
    public Block newStairRailing(WoodType woodType, BlockBehaviour.Properties p) {
        return new StairRailing(p);
    }

    @Override
    public Block newStairPlatform(WoodType woodType, BlockBehaviour.Properties p) {
        return new StairPlatform(p);
    }
}