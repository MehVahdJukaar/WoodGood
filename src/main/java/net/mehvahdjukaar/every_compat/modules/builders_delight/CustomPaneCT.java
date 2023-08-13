package net.mehvahdjukaar.every_compat.modules.builders_delight;

import com.tynoxs.buildersdelight.content.block.connected.IConnectedTextureBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.IronBarsBlock;

public class CustomPaneCT extends IronBarsBlock implements IConnectedTextureBlock {

    private final ResourceLocation texture;

    public CustomPaneCT(Properties properties, String texture) {
        super(properties);
        this.texture = new ResourceLocation("everycomp", texture);
    }

    @Override
    public ResourceLocation getTexture(){
        return this.texture;
    }

    @Override
    public boolean isPane(){
        return true;
    }
}

