package net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate;

import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.mehvahdjukaar.every_compat.modules.beautify.BeautifyModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: v1.2.0+
public class BeautifyRefabricatedModule extends BeautifyModuleAbstract {

    public BeautifyRefabricatedModule(String modId) {
        super(modId);
    }

    @Override
    public Identifier getTabKey() {
        return modRes("group");
    }

    @Override
    public Block newTrellis(WoodType woodType, BlockBehaviour.Properties p) {
        return new Trellis(p
                .strength(0.3F, 0.3F)
                .sound(SoundType.BAMBOO).noOcclusion()
        );
    }

    @Override
    public Block newBlinds(WoodType woodType, BlockBehaviour.Properties p) {
        return new Blinds(p
                .noOcclusion().strength(0.4F, 0.4F)
                .sound(SoundType.WOOD)
        );
    }

    @Override
    public Block newPictureFrame(WoodType woodType, BlockBehaviour.Properties p) {
        return new PictureFrame(p
                .noOcclusion().strength(0.1F, 0.1F)
                .sound(SoundType.WOOD).noOcclusion()
        );
    }

}