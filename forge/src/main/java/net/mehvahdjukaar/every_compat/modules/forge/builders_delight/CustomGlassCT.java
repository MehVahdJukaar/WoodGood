package net.mehvahdjukaar.every_compat.modules.forge.builders_delight;

import com.mojang.datafixers.util.Pair;
import com.tynoxs.buildersdelight.content.block.connected.IConnectedTextureBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

public class CustomGlassCT extends AbstractGlassBlock implements IConnectedTextureBlock {

    @Nullable
    private final ResourceLocation texture;
    protected final Set<Pair<ResourceLocation, @org.jetbrains.annotations.Nullable ResourceLocation>> textures = new HashSet<>();

    public CustomGlassCT(BlockBehaviour.Properties properties, String texture) {
        super(properties);
        this.texture = new ResourceLocation("everycomp", texture);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter world, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType){
        return false;
    }

    @Override
    public ResourceLocation getTexture(){
        return this.texture;
    }
}